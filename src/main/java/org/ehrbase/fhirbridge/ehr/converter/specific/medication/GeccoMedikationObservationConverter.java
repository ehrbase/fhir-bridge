package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.GrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public abstract class GeccoMedikationObservationConverter<E extends EntryEntity> extends MedicationStatementToObservationConverter<E> {

    @Override
    public E convert(@NonNull MedicationStatement resource) {
        E entryEntity = super.convert(resource);
        invokeStatus(entryEntity, resource);
        invokeGrund(entryEntity, resource);
        return entryEntity;
    }

    public void invokeStatus(E entryEntity, MedicationStatement resource) {
        try {
            // Normal setStatus (setStatusTree) is not set since only obligatory contained in the template, the information is not contained in the fhir resource at all
            Method setStatus = entryEntity.getClass().getMethod("setItemTreeStatus", StatusCluster.class);
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
        if (fhirStatus.equals(StatusDefiningCode2.ABGESCHLOSSEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.ABGESCHLOSSEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.AKTIV.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.AKTIV);
        } else if (fhirStatus.equals(StatusDefiningCode2.ANGEHALTEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.ANGEHALTEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.BEABSICHTIGT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.BEABSICHTIGT);
        } else if (fhirStatus.equals(StatusDefiningCode2.FEHLERHAFTE_ANWENDUNG.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.FEHLERHAFTE_ANWENDUNG);
        } else if (fhirStatus.equals(StatusDefiningCode2.GESTOPPT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.GESTOPPT);
        } else if (fhirStatus.equals(StatusDefiningCode2.NICHT_GENOMMEN.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.NICHT_GENOMMEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.UNBEKANNT.getValue())) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.UNBEKANNT);
        } else {
            throw new UnprocessableEntityException("Invalid Status code " + fhirStatus);
        }
        return statusCluster;
    }


    public void invokeGrund(E eventEntity, MedicationStatement resource) {
        try {
            Method setGrundDefiningCode = eventEntity.getClass().getMethod("setGrundDefiningCode", GrundDefiningCode.class);
            if(getGrundDefiningCode(resource).isPresent()){
                setGrundDefiningCode.invoke(eventEntity, getGrundDefiningCode(resource).get());
            }
        } catch (IllegalAccessException | InvocationTargetException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    protected Optional<GrundDefiningCode> getGrundDefiningCode(MedicationStatement resource) {
        for (Coding coding : resource.getMedicationCodeableConcept().getCoding()) {
            if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                return mapGrundDefiningCode(coding);
            }
        }
        return Optional.empty();
    }

    protected Optional<GrundDefiningCode> mapGrundDefiningCode(Coding coding) {
        String snomedCode = coding.getCode();
        if (snomedCode.equals(GrundDefiningCode.ADJUNCT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.ADJUNCT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.ADJUVANT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.ADJUVANT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.CURATIVE_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.CURATIVE_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.NEO_ADJUVANT_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.NEO_ADJUVANT_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.PROPHYLAXIS_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.PROPHYLAXIS_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else if (snomedCode.equals(GrundDefiningCode.SUPPORTIVE_PROCEDURE_INTENT_QUALIFIER_VALUE.getCode())) {
            return Optional.of(GrundDefiningCode.SUPPORTIVE_PROCEDURE_INTENT_QUALIFIER_VALUE);
        } else {
            throw new UnprocessableEntityException("Invalid Status code " + snomedCode);
        }
    }


}
