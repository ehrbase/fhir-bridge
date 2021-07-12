package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.GrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public abstract class GeccoMedikationObservationConverter<E extends EntryEntity> extends MedicationStatementToObservationConverter<E> {

    private static final Logger LOG = LoggerFactory.getLogger(GeccoMedikationObservationConverter.class);

    @Override
    public E convert(@NonNull MedicationStatement resource) {
        E entryEntity = super.convert(resource);
        invokeStatus(entryEntity, resource);
        return entryEntity;
    }

    public void invokeStatus(E entryEntity, MedicationStatement resource) {
        try {
            // Normal setStatus (setStatusTree) is not set since only obligatory contained in the template, the information is not contained in the fhir resource at all
            Method setStatus = entryEntity.getClass().getMethod("setItemTreeStatus", StatusCluster.class);
            setStatus.invoke(entryEntity, mapStatus(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    protected Optional<GrundDefiningCode> getGrundDefiningCode(MedicationStatement resource) {
        if (resource.hasReasonCode() && resource.getReasonCode().size()>0 && resource.getReasonCode().get(0).hasCoding()) {
            for (Coding coding : resource.getReasonCode().get(0).getCoding()) {
                if (coding.hasSystem() && coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
                    return mapGrundDefiningCode(coding);
                }
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
            throw new ConversionException("The reasonCode " + snomedCode + " is invalid !");
        }
    }

    protected StatusCluster mapStatus(MedicationStatement resource) {
        StatusCluster statusCluster = new StatusCluster();
        String fhirStatus = resource.getStatusElement().getCode();
        if (fhirStatus.equals(StatusDefiningCode2.ABGESCHLOSSEN.getValue()) || fhirStatus.equals("completed")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.ABGESCHLOSSEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.AKTIV.getValue()) || fhirStatus.equals("active")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.AKTIV);
        } else if (fhirStatus.equals(StatusDefiningCode2.ANGEHALTEN.getValue()) || fhirStatus.equals("on-hold")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.ANGEHALTEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.BEABSICHTIGT.getValue()) || fhirStatus.equals("intended")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.BEABSICHTIGT);
        } else if (fhirStatus.equals(StatusDefiningCode2.FEHLERHAFTE_ANWENDUNG.getValue()) || fhirStatus.equals("entered-in-error")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.FEHLERHAFTE_ANWENDUNG);
        } else if (fhirStatus.equals(StatusDefiningCode2.GESTOPPT.getValue()) || fhirStatus.equals("stopped")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.GESTOPPT);
        } else if (fhirStatus.equals(StatusDefiningCode2.NICHT_GENOMMEN.getValue()) || fhirStatus.equals("not-taken")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.NICHT_GENOMMEN);
        } else if (fhirStatus.equals(StatusDefiningCode2.UNBEKANNT.getValue()) || fhirStatus.equals("unknown")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode2.UNBEKANNT);
        } else {
            throw new ConversionException("Invalid Status code " + fhirStatus);
        }
        return statusCluster;
    }
}
