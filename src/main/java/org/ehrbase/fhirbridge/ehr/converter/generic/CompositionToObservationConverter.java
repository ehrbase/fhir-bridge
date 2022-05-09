package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class CompositionToObservationConverter <E extends EntryEntity> extends EntryEntityConverter<Composition, E> {
    private static final Logger LOG = LoggerFactory.getLogger(ConditionToObservationConverter.class);

    @Override
    public E convert(@NonNull Composition resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

    public void invokeTimeValues(E entryEntity, Composition resource) {
        invokeOriginValue(entryEntity, resource);
        invokeSetTimeValue(entryEntity, resource);
    }

    public void invokeSetTimeValue(E entryEntity, Composition resource) {
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, TimeConverter.convertCompositionTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

    public void invokeOriginValue(E entryEntity, Composition resource) {
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertCompositionTime(resource));
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }
}
