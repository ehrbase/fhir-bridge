package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.EignungZumTestenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeIdentifikatorDerUebergeordnetenProbeElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeProbenentahmebedingungElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbenartDefiningCode;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Specimen;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpecimenConverter {

    public ProbeCluster convert(Specimen specimenTarget) {
        ProbeCluster probe = new ProbeCluster();
        setProbenart(probe, specimenTarget);
        probe.setLaborprobenidentifikator(mapIdentifier(specimenTarget.getAccessionIdentifier()));
        probe.setExternerIdentifikator(mapIdentifier(specimenTarget.getIdentifier().get(0)));
        probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(specimenTarget.getReceivedTime())).getValueAsCalendar().toZonedDateTime());
        setZeitpunktDerEntnahme(probe, specimenTarget);
        probe.setIdentifikatorDesProbennehmers(mapIdentifier(specimenTarget.getCollection().getCollector().getIdentifier()));
        mapParentsOfProbe(probe, specimenTarget);
        setProbeEntnahmeBedingung(probe, specimenTarget);
        probe.setProbenentnahmemethodeValue(specimenTarget.getCollection().getMethod().getText());
        probe.setProbenentnahmemethodeValue(specimenTarget.getCollection().getBodySite().getText());
        probe.setEignungZumTesten(getEignungZumTesten(specimenTarget));
        if (!specimenTarget.getNote().isEmpty()) {
            probe.setKommentarValue(specimenTarget.getNote().get(0).getText());
        }
        return probe;
    }

    private ProbeEignungZumTestenChoice getEignungZumTesten(Specimen specimenTarget) {
        EignungZumTestenDefiningCode eignungZumTestenDefiningcode;
        switch (specimenTarget.getStatus()) {
            case UNSATISFACTORY:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_VERARBEITET;
                break;
            case ENTEREDINERROR:
            case UNAVAILABLE:
            case NULL:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_NICHT_VERARBEITET;
                break;
            default:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.ZUFRIEDENSTELLEND;
                break;
        }

        ProbeEignungZumTestenDvCodedText eignungZumTesten = new ProbeEignungZumTestenDvCodedText();
        eignungZumTesten.setEignungZumTestenDefiningCode(eignungZumTestenDefiningcode);
        return eignungZumTesten;
    }

    private void setProbeEntnahmeBedingung(ProbeCluster probe, Specimen specimenTarget) {
        for (CodeableConcept codeableConcept : specimenTarget.getCondition()) {
            if (!codeableConcept.getCoding().isEmpty()) {
                ProbeProbenentahmebedingungElement bedingung = new ProbeProbenentahmebedingungElement();
                bedingung.setValue(codeableConcept.getCoding().get(0).getDisplay());
                probe.getProbenentahmebedingung().add(bedingung);
            }
        }
    }

    private void mapParentsOfProbe(ProbeCluster probe, Specimen specimenTarget) {
        for (Reference reference : specimenTarget.getParent()) {
            ProbeIdentifikatorDerUebergeordnetenProbeElement identifikator = new ProbeIdentifikatorDerUebergeordnetenProbeElement();
            identifikator.setValue(mapIdentifier(reference.getIdentifier()));
            probe.getIdentifikatorDerUebergeordnetenProbe().add(identifikator);
        }
    }

    private void setZeitpunktDerEntnahme(ProbeCluster probe, Specimen specimenTarget) {
        if (specimenTarget.getCollection().getCollectedPeriod().hasStart() && specimenTarget.getCollection().getCollectedPeriod().hasEnd()) {
            Date start = specimenTarget.getCollection().getCollectedPeriod().getStart();
            Date end = specimenTarget.getCollection().getCollectedPeriod().getEnd();
            probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(start)).getValueAsCalendar().toZonedDateTime());
            probe.setZeitpunktDesProbeneingangsValue((new DateTimeType(end)).getValueAsCalendar().toZonedDateTime());
        } else {
            DateTimeType date = specimenTarget.getCollection().getCollectedDateTimeType();
            probe.setZeitpunktDerProbenentnahmeValue(date.getValueAsCalendar().toZonedDateTime());
        }

    }

    private void setProbenart(ProbeCluster probe, Specimen specimenTarget) {
        if (!specimenTarget.getType().getCoding().isEmpty()) {
            ProbenartDefiningCode probenart = null;

            if (specimenTarget.getType().getCoding().get(0).getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0487")) {
                String code = specimenTarget.getType().getCoding().get(0).getCode();
                probenart = ProbenartDefiningCode.getCodesAsMap().get(code);
            }
            if (probenart == null) {
                throw new ConversionException("Probenart not defined in specimen");
            }

            probe.setProbenartDefiningCode(probenart);
        }
    }


    private DvIdentifier mapIdentifier(Identifier identifier) {
        if (identifier == null) {
            throw new ConversionException("Unknown identifier");
        }

        DvIdentifier dvIdentifier = new DvIdentifier();

        dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        dvIdentifier.setId(identifier.getId());
        dvIdentifier.setType(identifier.getType().getText());

        return dvIdentifier;
    }
}
