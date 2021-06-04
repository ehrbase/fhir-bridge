package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.lang.NonNull;

public abstract class EncounterToCompositionConverter <C extends CompositionEntity> extends CompositionConverter<Encounter, C>{

    @Override
    public C convert(@NonNull Encounter resource) {
        C composition = super.convert(resource);

        // Mandatory
        composition.setStartTimeValue(TimeConverter.convertEncounterTime(resource)); // StartTimeValue
        //composition.setComposer(getComposerOrDefault(resource)); // Composer

        // Optional
        TimeConverter.convertEncounterEndTime(resource).ifPresent(composition::setEndTimeValue); // EndTimeValue

        return composition;
    }

}
