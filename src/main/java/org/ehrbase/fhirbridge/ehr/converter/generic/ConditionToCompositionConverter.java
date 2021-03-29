package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Condition;

public class ConditionToCompositionConverter<C extends CompositionEntity>  extends CompositionConverter<Condition, C>{

    @Override
    protected C convertInternal(Condition resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertConditionTime(resource)); // StartTimeValue
        TimeConverter.convertConditionEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue
        return composition;
    }

}
