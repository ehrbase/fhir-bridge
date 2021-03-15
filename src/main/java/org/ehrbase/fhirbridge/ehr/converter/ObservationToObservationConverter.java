package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;


public abstract class ObservationToObservationConverter <E extends EntryEntity> extends EntryEntityConverter<Observation, E> {
    @Override
    public E convert(@NonNull Observation resource) {
        //TODO setOrigin and  setTimeValue
        return super.convert(resource);
    }
}
