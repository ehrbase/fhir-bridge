package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.LoggerMessages;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode;
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
            Method setStatus = entryEntity.getClass().getMethod("setStatus", StatusCluster.class);
            setStatus.invoke(entryEntity, mapStatus(resource));
        } catch (IllegalAccessException | InvocationTargetException exception) {
            LOG.error(LoggerMessages.printInvokeError(exception));
        } catch (NoSuchMethodException ignored) {
            //ignored
        }
    }

    protected Optional<DvCodedText> getGrundDefiningCode(MedicationStatement medicationStatement) {
        if (medicationStatement.hasReasonCode() && !medicationStatement.getReasonCode().isEmpty() && medicationStatement.getReasonCode().get(0).hasCoding()) {
            return DvCodedTextParser.getInstance()
                    .parseFHIRCoding(medicationStatement.getReasonCode().get(0).getCoding().get(0));
        }
        return Optional.empty();
    }

    protected StatusCluster mapStatus(MedicationStatement resource) { //TODO also reject unfinished ones
        StatusCluster statusCluster = new StatusCluster();
        String fhirStatus = resource.getStatusElement().getCode();
        if (fhirStatus.equals(StatusDefiningCode.ABGESCHLOSSEN.getValue()) || fhirStatus.equals("completed")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.ABGESCHLOSSEN);
        } else if (fhirStatus.equals(StatusDefiningCode.AKTIV.getValue()) || fhirStatus.equals("active")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.AKTIV);
        } else if (fhirStatus.equals(StatusDefiningCode.ANGEHALTEN.getValue()) || fhirStatus.equals("on-hold")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.ANGEHALTEN);
        } else if (fhirStatus.equals(StatusDefiningCode.BEABSICHTIGT.getValue()) || fhirStatus.equals("intended")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.BEABSICHTIGT);
        } else if (fhirStatus.equals(StatusDefiningCode.FEHLERHAFTE_ANWENDUNG.getValue()) || fhirStatus.equals("entered-in-error")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.FEHLERHAFTE_ANWENDUNG);
        } else if (fhirStatus.equals(StatusDefiningCode.GESTOPPT.getValue()) || fhirStatus.equals("stopped")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.GESTOPPT);
        } else if (fhirStatus.equals(StatusDefiningCode.NICHT_GENOMMEN.getValue()) || fhirStatus.equals("not-taken")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.NICHT_GENOMMEN);
        } else if (fhirStatus.equals(StatusDefiningCode.UNBEKANNT.getValue()) || fhirStatus.equals("unknown")) {
            statusCluster.setStatusDefiningCode(StatusDefiningCode.UNBEKANNT);
        } else {
            throw new ConversionException("Invalid Status code " + fhirStatus);
        }
        return statusCluster;
    }


}
