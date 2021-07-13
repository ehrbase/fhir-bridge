package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.BezeichnungDesAnalytsDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ErgebnisStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.InterpretationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytErgebnisStatusChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytErgebnisStatusDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytKommentarElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytMesswertChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytMesswertDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytProbeIdChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytProbeIdDvIdentifier;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytProbeIdDvUri;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class LaborAnalytConverter {

    private static final Logger LOG = LoggerFactory.getLogger(LaborAnalytConverter.class);

    private final String EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT = "Valid code coding code is missing, this field is required to be present in order to do a mapping! Please add it to the instance. This also includes the System not to be empty.";

    public ProLaboranalytCluster convert(Observation observation) {
        ProLaboranalytCluster proLaboranalytCluster = new ProLaboranalytCluster();
        proLaboranalytCluster.setBezeichnungDesAnalytsDefiningCode(mapUntersuchterAnalyt(observation));
        proLaboranalytCluster.setErgebnisStatus(mapErgebnisStatus(observation));
        mapKommentar(proLaboranalytCluster, observation);
        mapMesswert(observation).ifPresent(proLaboranalytCluster::setMesswert);
        mapInterpretation(observation).ifPresent(proLaboranalytCluster::setInterpretationDefiningCode);
        mapProbeId(observation).ifPresent(proLaboranalytCluster::setProbeId);
        mapZeitpunktderValidierung(observation).ifPresent(proLaboranalytCluster::setZeitpunktDerValidierungValue);
        mapZeitpunktDesErgebnisStatuses(observation).ifPresent(proLaboranalytCluster::setZeitpunktErgebnisStatusValue);
        return proLaboranalytCluster;
    }

    private BezeichnungDesAnalytsDefiningCode mapUntersuchterAnalyt(Observation observation) {
        if (observation.getCode().hasCoding()) {
            for (Coding coding : observation.getCode().getCoding()) {
                return converUntersuchterAnalyt(coding);
            }
        } else {
            throw new ConversionException(EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT);
        }
        throw new ConversionException(EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT);
    }

    private BezeichnungDesAnalytsDefiningCode converUntersuchterAnalyt(Coding coding) {
        if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && coding.hasCode()) {
            return getCode(coding.getCode());
        } else {
            throw new ConversionException(EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT);
        }
    }

    private BezeichnungDesAnalytsDefiningCode getCode(String code) {
        if (BezeichnungDesAnalytsDefiningCode.getCodesAsMap().containsKey(code)) {
            return BezeichnungDesAnalytsDefiningCode.getCodesAsMap().get(code);
        } else {
            throw new ConversionException("code.coding.code value is not valid");
        }
    }

    private ProLaboranalytErgebnisStatusChoice mapErgebnisStatus(Observation observation) {
        ProLaboranalytErgebnisStatusDvCodedText ergebnisStatus = new ProLaboranalytErgebnisStatusDvCodedText();
        ergebnisStatus.setErgebnisStatusDefiningCode(convertErgebnisStatusDefiningCode(observation));
        return ergebnisStatus;
    }


    private ErgebnisStatusDefiningCode convertErgebnisStatusDefiningCode(Observation observation) {
        switch (observation.getStatus()) {
            case FINAL:
                return ErgebnisStatusDefiningCode.ENDBEFUND;
            case REGISTERED:
                return ErgebnisStatusDefiningCode.REGISTRIERT;
            case AMENDED:
                return ErgebnisStatusDefiningCode.GEAENDERT;
            case CORRECTED:
                return ErgebnisStatusDefiningCode.KORRIGIERT;
            case CANCELLED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_WIDERRUFEN;
            case NULL:
                return ErgebnisStatusDefiningCode.STORNIERT;
            case PRELIMINARY:
                return ErgebnisStatusDefiningCode.VORLAEUFIG;
            default:
                return ErgebnisStatusDefiningCode.UNVOLLSTAENDIG;
        }
    }

    private void mapKommentar(ProLaboranalytCluster proLaboranalytCluster, Observation observation) {
        if (observation.hasNote()) {
            ProLaboranalytKommentarElement kommentarElement = new ProLaboranalytKommentarElement();
            kommentarElement.setValue(observation.getNote().get(0).getText());
            proLaboranalytCluster.getKommentar().add(kommentarElement);
        }
    }

    private Optional<ProLaboranalytMesswertChoice> mapMesswert(Observation observation) {
        if (observation.hasValueQuantity() && observation.getValueQuantity().hasValue()) {
            Quantity valueQuantity = observation.getValueQuantity();
            return Optional.of(getLaborAnalytResultat(valueQuantity));
        } else if (observation.hasValueCodeableConcept()) {
            LOG.warn("Entering only value[x].ValueCodeableConcept makes mapping an value impossible, therefore nothing is mapped. Please enter a value and unit in order to perform a mapping.");
            return Optional.empty();
        } else {
            return Optional.empty();
        }
    }

    private ProLaboranalytMesswertChoice getLaborAnalytResultat(Quantity quantity) {
        ProLaboranalytMesswertDvQuantity laboranalytResultat = new ProLaboranalytMesswertDvQuantity();
        laboranalytResultat.setMesswertMagnitude(quantity.getValue().doubleValue());
        laboranalytResultat.setMesswertUnits(quantity.getUnit());
        return laboranalytResultat;
    }

    private Optional<InterpretationDefiningCode> mapInterpretation(Observation observation) {
        if (observation.hasInterpretation()) {
            for (CodeableConcept interpretations : observation.getInterpretation()) {
                return convertInterpretationDefiningCode(interpretations);
            }
        }
        return Optional.empty();
    }

    private Optional<InterpretationDefiningCode> convertInterpretationDefiningCode(CodeableConcept interpretations) {
        if (interpretations.hasCoding()) {
            for (Coding coding : interpretations.getCoding()) {
                if (verifyCodingAndSystemInterpretation(coding)) {
                    return Optional.of(InterpretationDefiningCode.getCodesAsMap().get(coding.getCode()));
                }
            }
        }
        return Optional.empty();
    }

    private boolean verifyCodingAndSystemInterpretation(Coding coding) {
        return coding.hasSystem() && coding.getSystem().equals(CodeSystem.HL7_OBSERVATI0N_INTERPRETATION.getUrl()) && coding.hasCode() && InterpretationDefiningCode.getCodesAsMap().containsKey(coding.getCode());
    }

    private DvIdentifier parseIntoDvIdentifier(Identifier identifier) { //TODO duplicate needs to be fixed when bug is fixed from SDK
        DvIdentifier dvIdentifier = new DvIdentifier();
        setDvIdentifierAssinger(dvIdentifier, identifier);
        setDvIdentifierId(dvIdentifier, identifier);
        setDvIdentifierType(dvIdentifier, identifier);
        return dvIdentifier;
    }


    private void setDvIdentifierType(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasAssigner()) {
            dvIdentifier.setAssigner(identifier.getAssigner().getDisplay());
        } else {
            dvIdentifier.setAssigner("");
        }
    }

    private void setDvIdentifierId(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasId()) {
            dvIdentifier.setId(identifier.getId());
        } else {
            dvIdentifier.setId("");
        }
    }

    private void setDvIdentifierAssinger(DvIdentifier dvIdentifier, Identifier identifier) {
        if (identifier.hasType()) {
            dvIdentifier.setType(identifier.getType().getText());
        } else {
            dvIdentifier.setType("");
        }
    }


    private Optional<TemporalAccessor> mapZeitpunktderValidierung(Observation observation) {
        if (observation.hasEffectiveDateTimeType() || observation.hasValueTimeType() || observation.hasValueDateTimeType()) {
            return Optional.of(TimeConverter.convertObservationTime(observation));
        } else {
            return Optional.empty();
        }
    }

    private Optional<TemporalAccessor> mapZeitpunktDesErgebnisStatuses(Observation observation) {
        if (observation.hasIssued()) {
            return Optional.of(ZonedDateTime.ofInstant(observation.getIssued().toInstant(),
                    ZoneId.systemDefault()));
        }
        return Optional.empty();
    }

    private Optional<ProLaboranalytProbeIdChoice> mapProbeId(Observation observation) {
        if (observation.hasSpecimen() && observation.getSpecimen().hasIdentifier()) {
            return mapProAnalytProbeIdentifier(observation);
        } else if (observation.hasSpecimen() && observation.getSpecimen().hasReference()) {
            return mapProAnalytProbeUri(observation);
        }
        return Optional.empty();
    }

    private Optional<ProLaboranalytProbeIdChoice> mapProAnalytProbeIdentifier(Observation observation) {
        ProLaboranalytProbeIdDvIdentifier proLaboranalytProbeIdDvIdentifier = new ProLaboranalytProbeIdDvIdentifier();
        proLaboranalytProbeIdDvIdentifier.setProbeId(parseIntoDvIdentifier(observation.getSpecimen().getIdentifier()));
        return Optional.of(proLaboranalytProbeIdDvIdentifier);
    }

    private Optional<ProLaboranalytProbeIdChoice> mapProAnalytProbeUri(Observation observation) {
        ProLaboranalytProbeIdDvUri proLaboranalytProbeIdDvIdentifier = new ProLaboranalytProbeIdDvUri();
        try {
            proLaboranalytProbeIdDvIdentifier.setProbeIdValue(new URI(observation.getSpecimen().getReference()));
        } catch (URISyntaxException uriSyntaxException) {
            LOG.error("Error occured when mapping URI Syntax of Speciman.reference URL" + uriSyntaxException);
        }
        return Optional.of(proLaboranalytProbeIdDvIdentifier);
    }

}
