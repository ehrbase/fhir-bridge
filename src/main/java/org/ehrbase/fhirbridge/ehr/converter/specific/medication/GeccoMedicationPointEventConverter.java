package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class GeccoMedicationPointEventConverter<P extends PointEventEntity> extends MedicationStatementToPointEventConverter<P> {

    @Override
    public P convert(@NonNull MedicationStatement resource) {
        P pointEvent = super.convert(resource);
        invokeStatus(pointEvent, resource);
        return pointEvent;
    }

    public void invokeStatus(P pointEventEntity, MedicationStatement resource) {
        try {
            Method setStatusDefiningCode = pointEventEntity.getClass().getMethod("setStatusDefiningCode", StatusDefiningCode2.class);
            setStatusDefiningCode.invoke(pointEventEntity, mapStatusDefiningCode(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    protected  StatusDefiningCode2 mapStatusDefiningCode(MedicationStatement resource){
            String status = resource.getStatusReason().get(0).getCoding().get(0).getCode();
                    //TODO hier fehlt zueg auf dem template
        return StatusDefiningCode2.ABGEBROCHEN;
     }
}
