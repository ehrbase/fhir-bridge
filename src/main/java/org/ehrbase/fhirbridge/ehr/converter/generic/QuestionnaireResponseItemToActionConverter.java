package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseItemToActionConverter<E extends EntryEntity> extends QuestionnaireResponseItemToEntryEntityConverter<E> {

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
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

}
