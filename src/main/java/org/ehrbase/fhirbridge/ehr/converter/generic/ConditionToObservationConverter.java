package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class ConditionToObservationConverter<E extends EntryEntity> extends EntryEntityConverter<Condition, E>  {

    private static final Logger LOG = LoggerFactory.getLogger(ConditionToObservationConverter.class);

    @Override
    public E convert(@NonNull Condition resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

    public void invokeTimeValues(E entryEntity, Condition resource) {
        invokeOriginValue(entryEntity, resource);
        invokeSetTimeValue(entryEntity, resource);
    }

    public void invokeSetTimeValue(E entryEntity, Condition resource){
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, TimeConverter.convertConditionTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method, error: " + exception.toString());
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

    public void invokeOriginValue(E entryEntity, Condition resource){
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertConditionTime(resource));
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method, error: " + exception.toString());
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}

