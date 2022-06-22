package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseToObservationConverter<E extends EntryEntity> extends EntryEntityConverter<QuestionnaireResponse, E> {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireResponseToObservationConverter.class);

    @Override
    public E convert(@NonNull QuestionnaireResponse questionnaireResponse) {
        E entryEntity = super.convert(questionnaireResponse);
        invokeTimeValues(entryEntity, questionnaireResponse);
        return entryEntity;
    }

    public void invokeTimeValues(E entryEntity, QuestionnaireResponse resource) {
        invokeOriginValue(entryEntity, resource);
        invokeSetTimeValue(entryEntity, resource);
    }

    public void invokeSetTimeValue(E entryEntity, QuestionnaireResponse resource){
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, TimeConverter.convertQuestionnaireResponseTime(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

    public void invokeOriginValue(E entryEntity, QuestionnaireResponse resource){
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, TimeConverter.convertQuestionnaireResponseTime(resource));
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}
