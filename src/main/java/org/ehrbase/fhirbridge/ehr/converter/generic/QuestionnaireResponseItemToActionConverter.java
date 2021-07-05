package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseItemToActionConverter<E extends EntryEntity> extends QuestionnaireResponseItemToEntryEntityConverter<E> {

    private static final Logger LOG = LoggerFactory.getLogger(QuestionnaireResponseItemToActionConverter.class);

    @Override
    public E convert(@NonNull QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent, @NonNull Language language, @NonNull TemporalAccessor authored) {
        E entryEntity = super.convert(questionnaireResponseItemComponent, language, authored);
        invokeTimeValue(entryEntity, authored);
        return entryEntity;
    }

    protected void invokeTimeValue(E entryEntity, TemporalAccessor authored){
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, authored);
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error("Exception occured when invoking method" + exception.toString());
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}
