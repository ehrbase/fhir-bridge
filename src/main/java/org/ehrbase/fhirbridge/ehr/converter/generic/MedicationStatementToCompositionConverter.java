package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

public abstract class MedicationStatementToCompositionConverter<C extends CompositionEntity> extends CompositionConverter<MedicationStatement, C>{
    @Override
    public C convert(@NonNull MedicationStatement resource) {
        C composition = super.convert(resource);
        composition.setStartTimeValue(TimeConverter.convertMedicationStatmentTime(resource)); // StartTimeValue
        TimeConverter.convertMedicationStatementEndTime(resource).ifPresent(composition::setEndTimeValue);
        return composition;
    }
}
