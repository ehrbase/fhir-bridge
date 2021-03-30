package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ErgebnisStatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.InterpretationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytErgebnisStatusDvCodedText;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytKommentarElement;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytMesswertChoice;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytMesswertDvQuantity;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.UntersuchterAnalytDefiningCode;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import java.util.HashMap;
import java.util.Map;

public class LaborAnalytConverter {

    public ProLaboranalytCluster convert(Observation observation) {
        ProLaboranalytCluster proLaboranalytCluster = new ProLaboranalytCluster();
        ProLaboranalytErgebnisStatusDvCodedText ergebnisStatus = new ProLaboranalytErgebnisStatusDvCodedText();
        ergebnisStatus.setErgebnisStatusDefiningCode(getLaboranalyteStatusDefiningCode(observation));
        proLaboranalytCluster.setErgebnisStatus(ergebnisStatus);
        setKommentarElement(proLaboranalytCluster, observation);
        setMesswert(observation, proLaboranalytCluster);
        proLaboranalytCluster.setUntersuchterAnalytDefiningCode(getUntersuchterAnalyt(observation));
        setInterpretationDefiningCode(observation, proLaboranalytCluster);
        proLaboranalytCluster.setZeitpunktValidationValue(TimeConverter.convertObservationTime(observation));
        setZeitpunktErgebnisStatus(observation, proLaboranalytCluster);
        return proLaboranalytCluster;
    }

    private void setMesswert(Observation observation, ProLaboranalytCluster proLaboranalytCluster) {
        if (observation.hasValueQuantity() && observation.getValueQuantity().hasValue()) {
            Quantity valueQuantity = observation.getValueQuantity();
            proLaboranalytCluster.setMesswert(getLaborAnalytResultat(valueQuantity));
        } else {
            throw new ConversionException("Value is required in FHIR Observation and should be Quantity");
        }
    }

    private void setInterpretationDefiningCode(Observation observation, ProLaboranalytCluster proLaboranalytCluster) {
        if (!observation.getInterpretation().isEmpty() && observation.getInterpretation().get(0).getCoding().get(0).getSystem().equals("http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation")) {
            String code = observation.getInterpretation().get(0).getCoding().get(0).getCode();
            proLaboranalytCluster.setInterpretationDefiningCode(InterpretationDefiningCode.getCodesAsMap().get(code));
        }
    }

    private UntersuchterAnalytDefiningCode getUntersuchterAnalyt(Observation observation) {
        if (observation.getCode().getCoding().get(0).getSystem().equals("http://loinc.org")) {
            String code = observation.getCode().getCoding().get(0).getCode();
            return  UntersuchterAnalytDefiningCode.getCodesAsMap().get(code);
        }else{
            throw new ConversionException("untersuchterAnalyt is required in FHIR Observation");
        }
    }

    private ProLaboranalytMesswertChoice getLaborAnalytResultat(Quantity quantity) {
        ProLaboranalytMesswertDvQuantity laboranalytResultat = new ProLaboranalytMesswertDvQuantity();
        laboranalytResultat.setMesswertMagnitude(quantity.getValue().doubleValue());
        laboranalytResultat.setMesswertUnits(quantity.getUnit());
        return laboranalytResultat;
    }


    private void setKommentarElement(ProLaboranalytCluster proLaboranalytCluster, Observation observation) {
        if (!observation.getNote().isEmpty()) {
            ProLaboranalytKommentarElement kommentarElement = new ProLaboranalytKommentarElement();
            kommentarElement.setValue(observation.getNote().get(0).getText());
            proLaboranalytCluster.getKommentar().add(kommentarElement);
        }
    }

    private void setZeitpunktErgebnisStatus(Observation observation, ProLaboranalytCluster proLaboranalytCluster) {
        if (!observation.getIssuedElement().isEmpty()) {
            DateTimeType issued = new DateTimeType(observation.getIssued());
            proLaboranalytCluster.setZeitpunktErgebnisStatusValue(issued.getValueAsCalendar().toZonedDateTime());
        }
    }


    private ErgebnisStatusDefiningCode getLaboranalyteStatusDefiningCode(Observation observation) {
        switch (observation.getStatus()) {
            case FINAL:
                return ErgebnisStatusDefiningCode.ENDBEFUND;
            case REGISTERED:
                return ErgebnisStatusDefiningCode.ERFASST;
            case AMENDED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_GEAENDERT;
            case CORRECTED:
                return ErgebnisStatusDefiningCode.ENDBEFUND_KORRIGIERT;
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
}
