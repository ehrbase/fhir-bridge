package org.ehrbase.fhirbridge.ehr.converter.generic;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.temporal.TemporalAccessor;

public abstract class QuestionnaireResponseItemToObservationConverter<E extends EntryEntity> extends QuestionnaireResponseItemToEntryEntityConverter<E> {

    @Override
    public E convert(@NonNull QuestionnaireResponse.QuestionnaireResponseItemComponent questionnaireResponseItemComponent, Language language, TemporalAccessor authored) {
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
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored){
            //ignored
        }
    }

    public void invokeOriginValue(E entryEntity, TemporalAccessor authored) {
        try {
            Method setTimeValue = entryEntity.getClass().getMethod("setTimeValue", TemporalAccessor.class);
            setTimeValue.invoke(entryEntity, authored);
        } catch ( IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        }catch (NoSuchMethodException ignored){
            //ignored
        }
    }
}
