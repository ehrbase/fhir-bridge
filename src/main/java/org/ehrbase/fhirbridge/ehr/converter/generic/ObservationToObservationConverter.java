package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;


public abstract class ObservationToObservationConverter <E extends EntryEntity> extends EntryEntityConverter<Observation, E> {

    @Override
    public E convert(@NonNull Observation resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

    protected void invokeTimeValues(E entryEntity, Observation resource) {
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
            setTimeValue.invoke(entryEntity, resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException noSuchMethodException) {
            noSuchMethodException.printStackTrace();
        }
    }
}