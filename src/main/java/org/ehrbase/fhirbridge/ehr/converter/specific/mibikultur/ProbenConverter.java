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
        probe.setProbenartNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
        probe.setLaborprobenidentifikatorNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        mapZeitpunktDerEntnahme(specimenTarget).ifPresent(probe::setZeitpunktDerProbenentnahme);
        return probe;
    }

    private Optional<ProbeZeitpunktDerProbenentnahmeChoice> mapZeitpunktDerEntnahme(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollected()) {
            if (specimenTarget.getCollection().hasCollectedPeriod()) {
                ProbeZeitpunktDerProbenentnahmeDvDateTime probeZeitpunktDerProbenentnahmeDvDateTime = new ProbeZeitpunktDerProbenentnahmeDvDateTime();
                TimeConverter.convertSpecimanCollection(specimenTarget.getCollection()).ifPresent(probeZeitpunktDerProbenentnahmeDvDateTime::setZeitpunktDerProbenentnahmeValue);
                if (probeZeitpunktDerProbenentnahmeDvDateTime.getZeitpunktDerProbenentnahmeValue() != null) {
                    return Optional.of(probeZeitpunktDerProbenentnahmeDvDateTime);
                }
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
}


