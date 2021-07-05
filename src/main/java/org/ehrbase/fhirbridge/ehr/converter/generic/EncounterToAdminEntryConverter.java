package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class EncounterToAdminEntryConverter<E extends EntryEntity> extends EntryEntityConverter<Encounter, E> {

    @Override
    public E convert(@NonNull Encounter resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

    public void invokeTimeValues(E entryEntity, Encounter resource) {
        if (!resource.getLocation().isEmpty()) {
            invokeSetBeginEndValue(entryEntity, resource);
        }

        invokeSetAufnahmeValue(entryEntity, resource);
        invokeSetEntlassungValue(entryEntity, resource);
    }

    public void invokeSetBeginEndValue(E entryEntity, Encounter resource) {

        try {
            Encounter.EncounterLocationComponent location = resource.getLocation().get(0);

            Method setBeginnValue = entryEntity.getClass().getMethod("setBeginnValue", TemporalAccessor.class);

            if (TimeConverter.convertEncounterLocationTime(location).isPresent()) {
                setBeginnValue.invoke(entryEntity, TimeConverter.convertEncounterLocationTime(location).get());
            }

            Method setEndValue = entryEntity.getClass().getMethod("setEndeValue", TemporalAccessor.class);

            if (TimeConverter.convertEncounterLocationEndTime(location).isPresent()) {

                setEndValue.invoke(entryEntity, TimeConverter.convertEncounterLocationEndTime(location).get());
            }
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    public void invokeSetAufnahmeValue(E entryEntity, Encounter resource) {
        try {
            Method setDatumUhrzeitDerAufnahmeValue = entryEntity.getClass().getMethod("setDatumUhrzeitDerAufnahmeValue", TemporalAccessor.class);
            setDatumUhrzeitDerAufnahmeValue.invoke(entryEntity, TimeConverter.convertEncounterTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    public void invokeSetEntlassungValue(E entryEntity, Encounter resource) {
        try {
            Method setDatumUhrzeitDerEntlassungValue = entryEntity.getClass().getMethod("setDatumUhrzeitDerEntlassungValue", TemporalAccessor.class);

            if (TimeConverter.convertEncounterEndTime(resource).isPresent()) {
                setDatumUhrzeitDerEntlassungValue.invoke(entryEntity, TimeConverter.convertEncounterEndTime(resource).get());
            }

        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }
}