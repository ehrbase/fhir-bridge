package org.ehrbase.fhirbridge.ehr.converter.bloodpressure;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.AbstractEntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

public class BlutdruckObservationConverter extends AbstractEntryEntityConverter<Observation, BlutdruckObservation> {
    @Override
    public BlutdruckObservation convert(Observation observation) {
        BlutdruckObservation bloodPressure = new BlutdruckObservation();
        mapCommonAttributes(observation, bloodPressure);
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

        //TODO refactor
        bloodPressure.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        bloodPressure.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return bloodPressure;
    }
}
