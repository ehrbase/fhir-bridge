package org.ehrbase.fhirbridge.ehr.converter.generic;

import org.springframework.lang.NonNull;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.hl7.fhir.r4.model.MedicationStatement;


public abstract class MedicationStatementToPointEventConverter <P extends PointEventEntity> extends EventEntityConverter<MedicationStatement, P> {

    @Override
    public P convert(@NonNull MedicationStatement resource) {
        P pointEvent = super.convert(resource);
        pointEvent.setTimeValue(TimeConverter.convertMedicationStatmentTime(resource));
        return pointEvent;
    }

}
