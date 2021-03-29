package org.ehrbase.fhirbridge.ehr.converter.generic;

import io.micrometer.core.lang.NonNull;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Condition;

public abstract class ConditionToCompositionConverter<C extends CompositionEntity>  extends CompositionConverter<Condition, C>{

    @Override
    public C convert(@NonNull Condition resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertConditionTime(resource));
        TimeConverter.convertConditionEndTime(resource).ifPresent(composition::setEndTimeValue);
        return composition;
    }

}
