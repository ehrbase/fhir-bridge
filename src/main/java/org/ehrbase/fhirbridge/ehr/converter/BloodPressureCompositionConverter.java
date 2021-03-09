package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodPressureCompositionConverter extends AbstractCompositionConverter<Observation, BlutdruckComposition> {

    @Override
    public BlutdruckComposition convert(@NonNull Observation observation) {
        BlutdruckComposition result = new BlutdruckComposition();

        BlutdruckObservation bloodPressure = new BlutdruckObservation();

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        try {
            double systolicBPValue = observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();
            String systolicBPUnit = observation.getComponent().get(0).getValueQuantity().getCode(); //mmHg, mm[Hg]

            bloodPressure.setSystolischMagnitude(systolicBPValue);
            bloodPressure.setSystolischUnits(systolicBPUnit);

            double diastolicBPValue = observation.getComponent().get(1).getValueQuantity().getValue().doubleValue();
            String diastolicBPUnit = observation.getComponent().get(1).getValueQuantity().getCode();

            bloodPressure.setDiastolischMagnitude(diastolicBPValue);
            bloodPressure.setDiastolischUnits(diastolicBPUnit);


        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        bloodPressure.setSubject(new PartySelf());

        bloodPressure.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        bloodPressure.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        result.setBlutdruck(bloodPressure);

        // ======================================================================================
        // Required fields by API

        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return result;
    }
}
