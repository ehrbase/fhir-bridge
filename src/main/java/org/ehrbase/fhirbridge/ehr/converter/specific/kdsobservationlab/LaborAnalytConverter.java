package org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvIdentifierParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ErgebnisStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytKommentarElement;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytMesswertDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytMesswertDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytMesswertElement;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytProbeIdChoice;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytProbeIdDvIdentifier;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytProbeIdDvUri;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;

public class LaborAnalytConverter {

    private static final Logger LOG = LoggerFactory.getLogger(LaborAnalytConverter.class);

    private final DvCodedTextParser dvCodedTextParser = DvCodedTextParser.getInstance();

    private final String EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT = "Valid code coding code is missing, this field is required to be present in order to do a mapping! Please add it to the instance. This also includes the System not to be empty.";

    public ProLaboranalytCluster convert(Observation observation) {
        ProLaboranalytCluster proLaboranalytCluster = mapProAnalytCluster(observation); // deepcopy requires too much work so we have to do it allover again.
        if (observation.hasInterpretation() && observation.getInterpretation().get(0).hasCoding()) {
            mapProAnalytClusterWithInterpretation(observation, proLaboranalytCluster);
        }
        return proLaboranalytCluster;
    }

    private void mapProAnalytClusterWithInterpretation(Observation observation, ProLaboranalytCluster proLaboranalytCluster) {
        for (CodeableConcept interpretations : observation.getInterpretation()) {
            if (interpretations.hasCoding()) {
                for (Coding coding : interpretations.getCoding()) {
                    mapInterpretation(coding).ifPresent(proLaboranalytCluster::setInterpretation);
                }
            }
        }
    }

    private ProLaboranalytCluster mapProAnalytCluster(Observation observation) {
        ProLaboranalytCluster proLaboranalytCluster = new ProLaboranalytCluster();
        mapUntersuchterAnalyt(observation).ifPresent(proLaboranalytCluster::setBezeichnungDesAnalyts);
        proLaboranalytCluster.setErgebnisStatusDefiningCode(convertErgebnisStatusDefiningCode(observation));
        mapKommentar(proLaboranalytCluster, observation);
        mapMesswert(observation).ifPresent(proLaboranalytCluster::setMesswert);
        mapProbeId(observation).ifPresent(proLaboranalytCluster::setProbeId);
        mapZeitpunktderValidierung(observation).ifPresent(proLaboranalytCluster::setZeitpunktDerValidierungValue);
        mapZeitpunktDesErgebnisStatuses(observation).ifPresent(proLaboranalytCluster::setZeitpunktErgebnisStatusValue);
        return proLaboranalytCluster;
    }

    private Optional<DvCodedText> mapUntersuchterAnalyt(Observation observation) {
        if (observation.getCode().hasCoding()) {
            return dvCodedTextParser.parseFHIRCoding(observation.getCode().getCoding().get(0));
        } else {
            throw new ConversionException(EXCEPTION_MESSAGE_UNTERSUCHTER_ANALYT);
        }
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

    private Optional<List<ProLaboranalytMesswertElement>> mapMesswert(Observation observation) {
        ProLaboranalytMesswertElement proLaboranalytMesswertElement = new ProLaboranalytMesswertElement();
        if (observation.hasValue()) {
            if (observation.hasValueQuantity() && observation.getValueQuantity().hasValue()) {
                Quantity valueQuantity = observation.getValueQuantity();
                proLaboranalytMesswertElement.setValue2(getLaborAnalytResultat(valueQuantity));
            } else if (observation.hasValueCodeableConcept()) {
                ProLaboranalytMesswertDvCodedText proLaboranalytMesswertDvCodedText = new ProLaboranalytMesswertDvCodedText();
                DvCodedTextParser.getInstance().parseFHIRCoding(observation.getValueCodeableConcept().getCoding().get(0)).ifPresent(proLaboranalytMesswertDvCodedText::setMesswert);
                proLaboranalytMesswertElement.setValue2(proLaboranalytMesswertDvCodedText);
            } else {
                throw new ConversionException("valueRatio and valueRange are not supported!");
            }
            return Optional.of(List.of(proLaboranalytMesswertElement));
        }
        return Optional.empty();
    }

    private ProLaboranalytMesswertDvQuantity getLaborAnalytResultat(Quantity quantity) {
        ProLaboranalytMesswertDvQuantity laboranalytResultat = new ProLaboranalytMesswertDvQuantity();
        laboranalytResultat.setMesswertMagnitude(quantity.getValue().doubleValue());
        laboranalytResultat.setMesswertUnits(quantity.getCode());
        return laboranalytResultat;
    }

    private Optional<DvCodedText> mapInterpretation(Coding coding) {
        return dvCodedTextParser.parseFHIRCoding(coding);
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
        proLaboranalytProbeIdDvIdentifier.setProbeId(DvIdentifierParser.parseIdentifierIntoDvIdentifier(observation.getSpecimen().getIdentifier()));
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
