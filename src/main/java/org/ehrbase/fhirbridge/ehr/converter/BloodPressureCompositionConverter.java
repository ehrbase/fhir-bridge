package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BloodPressureCompositionConverter extends CompositionConverter<Observation, BlutdruckComposition> {

    @Override
    public BlutdruckComposition convertInternal(@NonNull Observation resource) {
        BlutdruckComposition composition = new BlutdruckComposition();
        BlutdruckObservation observation = new BlutdruckObservation();

        DateTimeType fhirEffectiveDateTime = resource.getEffectiveDateTimeType();

        try {
            double systolicBPValue = resource.getComponent().get(0).getValueQuantity().getValue().doubleValue();
            String systolicBPUnit = resource.getComponent().get(0).getValueQuantity().getCode(); //mmHg, mm[Hg]

            observation.setSystolischMagnitude(systolicBPValue);
            observation.setSystolischUnits(systolicBPUnit);

            double diastolicBPValue = resource.getComponent().get(1).getValueQuantity().getValue().doubleValue();
            String diastolicBPUnit = resource.getComponent().get(1).getValueQuantity().getCode();

            observation.setDiastolischMagnitude(diastolicBPValue);
            observation.setDiastolischUnits(diastolicBPUnit);


        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        observation.setSubject(new PartySelf());
        observation.setLanguage(Language.DE);
        observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        composition.setBlutdruck(observation);

        // ======================================================================================
        // Required fields by API

        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }
}
