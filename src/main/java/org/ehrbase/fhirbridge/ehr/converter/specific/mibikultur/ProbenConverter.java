package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeZeitpunktDerProbenentnahmeChoice;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeZeitpunktDerProbenentnahmeDvDateTime;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.ProbeZeitpunktDerProbenentnahmeDvInterval;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Specimen;
import java.util.Optional;

public class ProbenConverter {
    public ProbeCluster convert(Specimen specimenTarget) {
        ProbeCluster probe = new ProbeCluster();
        mapProbenart(specimenTarget).ifPresent(probe::setProbenart);
        probe.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        mapZeitpunktDerEntnahme(specimenTarget).ifPresent(probe::setZeitpunktDerProbenentnahme);
        if (probe.getZeitpunktDerProbenentnahme() == null) {
            probe.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        mapKommentar(specimenTarget).ifPresent(probe::setKommentarValue);
        return probe;
    }

    private Optional<DvCodedText> mapProbenart(Specimen specimenTarget) {
        if (specimenTarget.hasType() && specimenTarget.getType().hasCoding() && specimenTarget.getType().getCoding().size() == 1) {
            return DvCodedTextParser.getInstance().parseFHIRCoding(specimenTarget.getType().getCoding().get(0));
        } else {
            throw new UnprocessableEntityException("The fhir bridge does not support a specimen with multiple type.codings, since a specimen cannot have several types. Please alter the data accordingly.");
        }
    }


    private Optional<ProbeZeitpunktDerProbenentnahmeChoice> mapZeitpunktDerEntnahme(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollected()) {
            if (specimenTarget.getCollection().hasCollectedPeriod()) {
                ProbeZeitpunktDerProbenentnahmeDvInterval interval = new ProbeZeitpunktDerProbenentnahmeDvInterval();
                DvDateTime startTime = new DvDateTime();
                TimeConverter.convertSpecimanCollection(specimenTarget.getCollection()).ifPresent(startTime::setValue);
                interval.setUpper(startTime);
                DvDateTime endTime = new DvDateTime();
                TimeConverter.convertSpecimanCollectionEndtime(specimenTarget.getCollection()).ifPresent(endTime::setValue);
                interval.setLower(endTime);
                return Optional.of(interval);
            } else {
                ProbeZeitpunktDerProbenentnahmeDvDateTime probeZeitpunktDerProbenentnahmeDvDateTime = new ProbeZeitpunktDerProbenentnahmeDvDateTime();
                TimeConverter.convertSpecimanCollection(specimenTarget.getCollection()).ifPresent(probeZeitpunktDerProbenentnahmeDvDateTime::setZeitpunktDerProbenentnahmeValue);
                if (probeZeitpunktDerProbenentnahmeDvDateTime.getZeitpunktDerProbenentnahmeValue() != null) {
                    return Optional.of(probeZeitpunktDerProbenentnahmeDvDateTime);
                }
            }
        } else {
            return Optional.empty();
        }
        return Optional.empty();
    }

    private Optional<String> mapKommentar(Specimen specimenTarget) {
        if (specimenTarget.hasNote()) {
            StringBuilder kommentar = new StringBuilder();
            for (Annotation annotation : specimenTarget.getNote()) {
                kommentar.append(annotation.getText());
            }
            return Optional.of(kommentar.toString());
        }
        return Optional.empty();
    }
}


