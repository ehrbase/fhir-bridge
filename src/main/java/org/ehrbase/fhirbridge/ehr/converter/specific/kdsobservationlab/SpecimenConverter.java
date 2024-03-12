package org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.EignungZurAnalyseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialProbenentahmebedingungElement;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialZeitpunktDerProbenentnahmeChoice;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialZeitpunktDerProbenentnahmeDvDateTime;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialZeitpunktDerProbenentnahmeDvIntervalDvDateTime;
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

    public ProbenmaterialCluster convert(Specimen specimenTarget) {
        ProbenmaterialCluster probe = new ProbenmaterialCluster();
        mapProbenart(specimenTarget).ifPresent(probe::setProbenart);
        mapAccessionIdentifier(specimenTarget).ifPresent(probe::setLaborprobenidentifikator);
        mapIdentifier(specimenTarget).ifPresent(probe::setExternerIdentifikator);
        mapReceivedTime(specimenTarget).ifPresent(probe::setZeitpunktDesProbeneingangsValue);
        mapZeitpunktDerEntnahme(specimenTarget).ifPresent(probe::setZeitpunktDerProbenentnahme);
        mapIdentifikatorDesProbennehmers(specimenTarget).ifPresent(probe::setIdentifikatorDesProbenehmers);
        mapParentsOfProbe(probe, specimenTarget);
        mapProbeEntnahmeBedingungen(specimenTarget, probe);
        mapProbeEntnahmeMethode(specimenTarget).ifPresent(probe::setProbenentnahmemethodeValue);
        mapKoerperstelle(specimenTarget).ifPresent(probe::setKoerperstelleValue);
        mapEignungZumTesten(specimenTarget).ifPresent(probe::setEignungZurAnalyseDefiningCode);
        mapKommentar(specimenTarget).ifPresent(probe::setKommentarValue);

        //TODO Körperstelle
        return probe;
    }

    private Optional<DvCodedText> mapProbenart(Specimen specimenTarget) {
        if (specimenTarget.hasType() && specimenTarget.getType().hasCoding() && specimenTarget.getType().getCoding().size() == 1) {
            return DvCodedTextParser.getInstance().parseFHIRCoding(specimenTarget.getType().getCoding().get(0));
        } else {
            throw new UnprocessableEntityException("The fhir bridge does not support a specimen with multiple type.codings, since a specimen cannot have several types. Please alter the data accordingly.");
        }
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

    private Optional<ProbenmaterialZeitpunktDerProbenentnahmeChoice> mapZeitpunktDerEntnahme(Specimen specimenTarget) {
        if (specimenTarget.hasCollection() && specimenTarget.getCollection().hasCollected()) {
            if(specimenTarget.getCollection().hasCollectedPeriod()){
                ProbenmaterialZeitpunktDerProbenentnahmeDvIntervalDvDateTime interval = new ProbenmaterialZeitpunktDerProbenentnahmeDvIntervalDvDateTime();
                DvDateTime startTime = new DvDateTime();
                TimeConverter.convertSpecimanCollection(specimenTarget.getCollection()).ifPresent(startTime::setValue);
                interval.setUpper(startTime);
                DvDateTime endTime = new DvDateTime();
                TimeConverter.convertSpecimanCollectionEndtime(specimenTarget.getCollection()).ifPresent(endTime::setValue);
                interval.setLower(endTime);
                return Optional.of(interval);
            } else {
                ProbenmaterialZeitpunktDerProbenentnahmeDvDateTime probenmaterialZeitpunktDerProbenentnahmeDvDateTime = new ProbenmaterialZeitpunktDerProbenentnahmeDvDateTime();
                TimeConverter.convertSpecimanCollection(specimenTarget.getCollection()).ifPresent(probenmaterialZeitpunktDerProbenentnahmeDvDateTime::setZeitpunktDerProbenentnahmeValue);
                if(probenmaterialZeitpunktDerProbenentnahmeDvDateTime.getZeitpunktDerProbenentnahmeValue()!=null){
                    return Optional.of(probenmaterialZeitpunktDerProbenentnahmeDvDateTime);
                }
            }
        }else{
            return Optional.empty();
        }
        return Optional.empty();
    }

    private void mapParentsOfProbe(ProbenmaterialCluster probe, Specimen specimenTarget) {
        if (specimenTarget.hasParent()) {
            for (Reference reference : specimenTarget.getParent()) {
                if (reference.hasIdentifier()) {
                    ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement identifikator = new ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement();
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


    private void mapProbeEntnahmeBedingungen(Specimen specimenTarget, ProbenmaterialCluster probe) {
        if (specimenTarget.hasCondition()) {
            for (CodeableConcept codeableConcept : specimenTarget.getCondition()) {
                if (codeableConcept.hasCoding()) {
                    convertProbeEntnahmeBedingungen(codeableConcept, probe);
                }
            }
        }
    }

    private void convertProbeEntnahmeBedingungen(CodeableConcept codeableConcept, ProbenmaterialCluster probe) {
        ProbenmaterialProbenentahmebedingungElement bedingung = new ProbenmaterialProbenentahmebedingungElement();
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

    private Optional<EignungZurAnalyseDefiningCode> mapEignungZumTesten(Specimen specimenTarget) {
        if (specimenTarget.hasStatus()) {
            return mapSpecimenStatus(specimenTarget);
        } else {
            return Optional.empty();
        }
    }

    private Optional<EignungZurAnalyseDefiningCode> mapSpecimenStatus(Specimen specimenTarget) {
        EignungZurAnalyseDefiningCode eignungZumTestenDefiningcode;
        switch (specimenTarget.getStatus()) {
            case AVAILABLE:
                eignungZumTestenDefiningcode = EignungZurAnalyseDefiningCode.ZUFRIEDENSTELLEND;
                break;
            case UNSATISFACTORY:
                eignungZumTestenDefiningcode = EignungZurAnalyseDefiningCode.MANGELHAFT_ANALYSIERT;
                break;
            case ENTEREDINERROR:
            case UNAVAILABLE:
            case NULL:
                eignungZumTestenDefiningcode = EignungZurAnalyseDefiningCode.MANGELHAFT_NICHT_ANALYSIERT;
                break;
            default:
                throw new ConversionException("Unsupported value for specimen status " + specimenTarget.getStatus());
        }
        return Optional.of(eignungZumTestenDefiningcode);
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
