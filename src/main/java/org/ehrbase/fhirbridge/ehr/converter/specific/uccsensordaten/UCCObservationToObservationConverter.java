package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.hl7.fhir.r4.model.Composition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public abstract class UCCObservationToObservationConverter<E extends EntryEntity> extends EntryEntityConverter<Composition, E> {
    private static final Logger LOG = LoggerFactory.getLogger(CompositionToObservationConverter.class);
    List<TemporalAccessor> eventTimings = new ArrayList<>();

    @Override
    public E convert(@NonNull Composition resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        invokeOriginValue(entryEntity);
        return entryEntity;
    }


    private void invokeTimeValues(E entryEntity, Composition resource) {
        invokeTimeValue(entryEntity, resource);
    }



    private void invokeTimeValue(E entryEntity, Composition resource) {
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertCompositionTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    private void invokeOriginValue(E entryEntity) {
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, getEarliestOriginTime());
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    private TemporalAccessor getEarliestOriginTime() {
        ZonedDateTime earliest = (ZonedDateTime) eventTimings.get(0);
        for (TemporalAccessor temporalAccessor : eventTimings) {
            ZonedDateTime current = (ZonedDateTime) temporalAccessor;
            if (current.isBefore(earliest)) {
                earliest = current;
            }
        }
        return earliest;
    }

    protected void addEventTimings(TemporalAccessor temporalAccessor){
        eventTimings.add(temporalAccessor);
    }

}
