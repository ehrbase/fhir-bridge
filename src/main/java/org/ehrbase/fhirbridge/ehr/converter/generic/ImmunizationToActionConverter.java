package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.Immunization;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class ImmunizationToActionConverter<E extends EntryEntity> extends EntryEntityConverter<Immunization, E>{

    private static final Logger LOG = LoggerFactory.getLogger(ImmunizationToActionConverter.class);

    @Override
    public E convert(@NonNull Immunization resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

    protected void invokeTimeValues(E entryEntity, Immunization resource){
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertImmunizationTime(resource));
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method, error: " + exception.toString());
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}
