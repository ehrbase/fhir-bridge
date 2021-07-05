package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class DiagnosticReportToObservationConverter<E extends EntryEntity> extends EntryEntityConverter<DiagnosticReport, E>  {

    private static final Logger LOG = LoggerFactory.getLogger(DiagnosticReportToObservationConverter.class);

    @Override
    public E convert(@NonNull DiagnosticReport resource) {
        E entryEntity = super.convert(resource);
        invokeTimeValues(entryEntity, resource);
        return entryEntity;
    }

     public void invokeTimeValues(E entryEntity, DiagnosticReport resource) {
        invokeOriginValue(entryEntity, resource);
        invokeSetTimeValue(entryEntity, resource);
    }

     public void invokeSetTimeValue(E entryEntity, DiagnosticReport resource){
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, TimeConverter.convertDiagnosticReportTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method, error: " + exception.toString());
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

     public void invokeOriginValue(E entryEntity, DiagnosticReport resource){
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertDiagnosticReportTime(resource));
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method, error: " + exception.toString());
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}
