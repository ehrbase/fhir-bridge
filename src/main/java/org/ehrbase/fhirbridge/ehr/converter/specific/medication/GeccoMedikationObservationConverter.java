package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class GeccoMedikationObservationConverter<E extends EntryEntity> extends MedicationStatementToObservationConverter<E> {

    @Override
    public E convert(@NonNull MedicationStatement resource) {
        E entryEntity = super.convert(resource);
        invokeStatus(entryEntity, resource);
        return entryEntity;
    }

    public void invokeStatus(E entryEntity, MedicationStatement resource) {
        try {
            Method setStatus = entryEntity.getClass().getMethod("setStatus", StatusCluster.class);
            setStatus.invoke(entryEntity, mapStatus(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    protected StatusCluster mapStatus(MedicationStatement resource) {
        StatusCluster statusCluster = new StatusCluster();
        String fhirStatus = resource.getStatusElement().getCode();
        if (fhirStatus.equals(StatusDefiningCode.ABGESCHLOSSEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.ABGESCHLOSSEN);
        } else if (fhirStatus.equals(StatusDefiningCode.AKTIV.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.AKTIV);
        } else if (fhirStatus.equals(StatusDefiningCode.ANGEHALTEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.ANGEHALTEN);
        } else if (fhirStatus.equals(StatusDefiningCode.BEABSICHTIGT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.BEABSICHTIGT);
        } else if (fhirStatus.equals(StatusDefiningCode.FEHLERHAFTE_ANWENDUNG.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.FEHLERHAFTE_ANWENDUNG);
        } else if (fhirStatus.equals(StatusDefiningCode.GESTOPPT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.GESTOPPT);
        } else if (fhirStatus.equals(StatusDefiningCode.NICHT_GENOMMEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.NICHT_GENOMMEN);
        } else if (fhirStatus.equals(StatusDefiningCode.UNBEKANNT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.UNBEKANNT);
        } else {
            throw new UnprocessableEntityException("Invalid Status code " + fhirStatus);
        }
        return statusCluster;
    }


}
