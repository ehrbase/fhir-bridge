package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.EignungZumTestenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeIdentifikatorDerUebergeordnetenProbeElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeProbenentahmebedingungElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbenartDefiningCode;
import org.hl7.fhir.r4.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class SpecimenConverter {
    private static final Logger LOG = LoggerFactory.getLogger(SpecimenConverter.class);

    public ProbeCluster convert(Specimen specimenTarget) {
        ProbeCluster probe = new ProbeCluster();
        mapProbenart(specimenTarget).ifPresent(probe::setProbenartDefiningCode);
        mapAccessionIdentifier(specimenTarget).ifPresent(probe::setLaborprobenidentifikator);
        mapIdentifier(specimenTarget).ifPresent(probe::setExternerIdentifikator);
        mapReceivedTime(specimenTarget).ifPresent(probe::setZeitpunktDesProbeneingangsValue);
        mapZeitpunktDerEntnahme(specimenTarget).ifPresent(probe::setZeitpunktDerProbenentnahmeValue);
        mapIdentifikatorDesProbennehmers(specimenTarget).ifPresent(probe::setIdentifikatorDesProbennehmers);


        mapParentsOfProbe(probe, specimenTarget);
        setProbeEntnahmeBedingung(probe, specimenTarget);
        probe.setProbenentnahmemethodeValue(specimenTarget.getCollection().getMethod().getText());
        probe.setEignungZumTesten(getEignungZumTesten(specimenTarget));
        if (!specimenTarget.getNote().isEmpty()) {
            probe.setKommentarValue(specimenTarget.getNote().get(0).getText());
        }
        return probe;
    }



    private Optional<ProbenartDefiningCode> mapProbenart(Specimen specimenTarget) {
        if (specimenTarget.hasType() && specimenTarget.getType().hasCoding()) {
            for (Coding coding : specimenTarget.getType().getCoding()) {
                return convertProbenArtDefiningCode(coding);
            }
        }
        return Optional.empty();
    }

    private Optional<ProbenartDefiningCode> convertProbenArtDefiningCode(Coding coding) {
        if (coding.hasSystem() && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0487") && coding.hasCode()) {
            if (ProbenartDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
                return Optional.of(ProbenartDefiningCode.getCodesAsMap().get(coding.getCode()));
            }
        }
        return Optional.empty();
    }


    private Optional<DvIdentifier> mapAccessionIdentifier(Specimen specimenTarget) {
        if (specimenTarget.hasAccessionIdentifier()) {
            return Optional.of(parseDvIdentifier(specimenTarget.getAccessionIdentifier()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<DvIdentifier> mapIdentifier(Specimen specimenTarget) {
        if (specimenTarget.hasIdentifier()) {
            if (specimenTarget.getIdentifier().size() > 1) {
                LOG.warn("The fhir-bridge supports only one external identifier, therefore only the first one is mapped.");
            }
            return Optional.of(parseDvIdentifier(specimenTarget.getIdentifier().get(0)));
        } else {
            return Optional.empty();
        }
    }

    private Optional<TemporalAccessor> mapReceivedTime(Specimen specimenTarget) {
        if (specimenTarget.hasReceivedTime()) {
            return Optional.of((new DateTimeType(specimenTarget.getReceivedTime())).getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    private Optional<TemporalAccessor> mapZeitpunktDerEntnahme(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollected()) {
            return TimeConverter.convertSpecimanCollection(specimenTarget.getCollection());
        } else {
            return Optional.empty();
        }
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

    private DvIdentifier parseDvIdentifier(Identifier identifier) {
        DvIdentifier dvIdentifier = new DvIdentifier();
        setDvIdentifierAssinger(dvIdentifier, identifier);
        setDvIdentifierId(dvIdentifier, identifier);
        setDvIdentifierType(dvIdentifier, identifier);
        return dvIdentifier;
    }

    private void setDvIdentifierType(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasAssigner()) {
            dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        }else{
            dvIdentifier.setAssigner("");
        }
    }

    private void setDvIdentifierId(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasId()) {
            dvIdentifier.setId(identifier.getId());
        }else{
            dvIdentifier.setId("");
        }
    }

    private void setDvIdentifierAssinger(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasType()) {
            dvIdentifier.setType(identifier.getType().getText());
        }else{
            dvIdentifier.setType("");
        }
    }

    private Optional<DvIdentifier> mapIdentifikatorDesProbennehmers(Specimen specimenTarget) {
        if(specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollector()){
            return Optional.of(parseDvIdentifier(specimenTarget.getCollection().getCollector().getIdentifier()));
        }else{
           return Optional.empty();
        }
    }
}
