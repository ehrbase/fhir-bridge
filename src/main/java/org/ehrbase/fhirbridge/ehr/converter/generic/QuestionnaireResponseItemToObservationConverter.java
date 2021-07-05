package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseItemToObservationConverter<E extends EntryEntity> extends QuestionnaireResponseItemToEntryEntityConverter<E> {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireResponseItemToObservationConverter.class);

    @Override
    public E convert(@NonNull QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent, @NonNull Language language,@NonNull TemporalAccessor authored) {
        E entryEntity = super.convert(questionnaireResponseItemComponent, language, authored);
        invokeTimeValues(entryEntity, authored);
        return entryEntity;
    }

    public void invokeTimeValues(E entryEntity, TemporalAccessor authored) {
        invokeOriginValue(entryEntity, authored);
        invokeSetTimeValue(entryEntity, authored);
    }

    public void invokeSetTimeValue(E entryEntity, TemporalAccessor authored) {
        try {
            Method setOriginValue = entryEntity.getClass().getMethod("setOriginValue", TemporalAccessor.class);
            setOriginValue.invoke(entryEntity, authored);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

    public void invokeOriginValue(E entryEntity, TemporalAccessor authored) {
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, authored);
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }
}
