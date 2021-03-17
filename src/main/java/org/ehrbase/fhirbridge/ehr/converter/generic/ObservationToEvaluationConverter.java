package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public abstract class ObservationToEvaluationConverter<E extends EntryEntity> extends EntryEntityConverter<Observation, E> {
    @Override
    public E convert(@NonNull Observation resource) {
        E entryEntity = super.convert(resource);

        return entryEntity;
    }
}
