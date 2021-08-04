package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.EignungZumTestenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeEignungZumTestenDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeIdentifikatorDerUebergeordnetenProbeElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbeProbenentahmebedingungElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProbenartDefiningCode;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Specimen;
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
        mapProbeEntnahmeBedingungen(specimenTarget, probe);
        mapProbeEntnahmeMethode(specimenTarget).ifPresent(probe::setProbenentnahmemethodeValue);
        mapKoerperstelle(specimenTarget).ifPresent(probe::setKoerperstelleValue);
        mapEignungZumTesten(specimenTarget).ifPresent(probe::setEignungZumTesten);
        mapKommentar(specimenTarget).ifPresent(probe::setKommentarValue);
        return probe;
    }


    private Optional<ProbenartDefiningCode> mapProbenart(Specimen specimenTarget) {
        if (specimenTarget.hasType() && specimenTarget.getType().hasCoding()) {
            return convertProbenArtDefiningCode(specimenTarget);
        }
        return Optional.empty();
    }

    private Optional<ProbenartDefiningCode> convertProbenArtDefiningCode(Specimen specimenTarget) {
        for (Coding coding : specimenTarget.getType().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/v2-0487") && coding.hasCode()) {
                return convertProbenartIfKeyPresent(coding);
            }
        }
        return Optional.empty();
    }

    private Optional<ProbenartDefiningCode> convertProbenartIfKeyPresent(Coding coding) {
        if (ProbenartDefiningCode.getCodesAsMap().containsKey(coding.getCode())) {
            return Optional.of(ProbenartDefiningCode.getCodesAsMap().get(coding.getCode()));
        }
        return Optional.empty();
    }


    private Optional<DvIdentifier> mapAccessionIdentifier(Specimen specimenTarget) {
        if (specimenTarget.hasAccessionIdentifier()) {
            return Optional.of(DvIdentifierParser.parseIdentifierIntoDvIdentifier(specimenTarget.getAccessionIdentifier()));
        } else {
            return Optional.empty();
        }
    }

    private Optional<DvIdentifier> mapIdentifier(Specimen specimenTarget) {
        if (specimenTarget.hasIdentifier()) {
            if (specimenTarget.getIdentifier().size() > 1) {
                LOG.warn("The fhir-bridge supports only one external identifier, therefore only the first one is mapped.");
            }
            return Optional.of(DvIdentifierParser.parseIdentifierIntoDvIdentifier(specimenTarget.getIdentifier().get(0)));
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

    private void mapParentsOfProbe(ProbeCluster probe, Specimen specimenTarget) {
        if (specimenTarget.hasParent()) {
            for (Reference reference : specimenTarget.getParent()) {
                if (reference.hasIdentifier()) {
                    ProbeIdentifikatorDerUebergeordnetenProbeElement identifikator = new ProbeIdentifikatorDerUebergeordnetenProbeElement();
                    identifikator.setValue(DvIdentifierParser.parseIdentifierIntoDvIdentifier(reference.getIdentifier()));
                    probe.getIdentifikatorDerUebergeordnetenProbe().add(identifikator);
                }
            }
        }
    }

    private Optional<DvIdentifier> mapIdentifikatorDesProbennehmers(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollector()) {
            return Optional.of(DvIdentifierParser.parseIdentifierIntoDvIdentifier(specimenTarget.getCollection().getCollector().getIdentifier()));
        } else {
            return Optional.empty();
        }
    }


    private void mapProbeEntnahmeBedingungen(Specimen specimenTarget, ProbeCluster probe) {
        if (specimenTarget.hasCondition()) {
            for (CodeableConcept codeableConcept : specimenTarget.getCondition()) {
                if (codeableConcept.hasCoding()) {
                    convertProbeEntnahmeBedingungen(codeableConcept, probe);
                }
            }
        }
    }

    private void convertProbeEntnahmeBedingungen(CodeableConcept codeableConcept, ProbeCluster probe) {
        ProbeProbenentahmebedingungElement bedingung = new ProbeProbenentahmebedingungElement();
        if (codeableConcept.getCoding().get(0).hasDisplay()) {
            bedingung.setValue(codeableConcept.getCoding().get(0).getDisplay());
            probe.getProbenentahmebedingung().add(bedingung);
        }
    }

    private Optional<String> mapProbeEntnahmeMethode(Specimen specimenTarget) {
        if (specimenTarget.hasCondition() && specimenTarget.getCollection().hasMethod()) {
            if (specimenTarget.getCollection().getMethod().hasText()) {
                return Optional.of(specimenTarget.getCollection().getMethod().getText());
            } else if (specimenTarget.getCollection().getMethod().hasCoding()) {
                return mapProbeEntnahmeMethodeCoding(specimenTarget);
            } else {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    private Optional<String> mapProbeEntnahmeMethodeCoding(Specimen specimenTarget) {
        for (Coding coding : specimenTarget.getCollection().getMethod().getCoding()) {
            if (coding.hasCode()) {
                return Optional.of(coding.getCode());
            } else if (coding.hasDisplay()) {
                return Optional.of(coding.getDisplay());
            }
        }
        return Optional.empty();
    }

    private Optional<String> mapKoerperstelle(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasBodySite()) {
            return Optional.of(specimenTarget.getCollection().getBodySite().getText());
        }
        return Optional.empty();
    }

    private Optional<ProbeEignungZumTestenChoice> mapEignungZumTesten(Specimen specimenTarget) {
        if (specimenTarget.hasStatus()) {
            return mapSpecimenStatus(specimenTarget);
        } else {
            return Optional.empty();
        }
    }

    private Optional<ProbeEignungZumTestenChoice> mapSpecimenStatus(Specimen specimenTarget) {
        EignungZumTestenDefiningCode eignungZumTestenDefiningcode;
        switch (specimenTarget.getStatus()) {
            case AVAILABLE:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.ZUFRIEDENSTELLEND;
                break;
            case UNSATISFACTORY:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_VERARBEITET;
                break;
            case ENTEREDINERROR:
            case UNAVAILABLE:
            case NULL:
                eignungZumTestenDefiningcode = EignungZumTestenDefiningCode.MANGELHAFT_NICHT_VERARBEITET;
                break;
            default:
                throw new ConversionException("Unsupported value for specimen status " + specimenTarget.getStatus());
        }
        ProbeEignungZumTestenDvCodedText eignungZumTesten = new ProbeEignungZumTestenDvCodedText();
        eignungZumTesten.setEignungZumTestenDefiningCode(eignungZumTestenDefiningcode);
        return Optional.of(eignungZumTesten);
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
