package org.ehrbase.fhirbridge.ehr.converter.specific.medication;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.GrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.springframework.lang.NonNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

public abstract class GeccoMedikationPointEventConverter<P extends PointEventEntity> extends MedicationStatementToPointEventConverter<P> {

    @Override
    public P convert(@NonNull MedicationStatement resource) {
        P pointEvent = super.convert(resource);
        invokeStatus(pointEvent, resource);
        invokeGrund(pointEvent, resource);
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

    protected StatusDefiningCode2 mapStatusDefiningCode(MedicationStatement resource) {
        String status = resource.getStatusReason().get(0).getCoding().get(0).getCode();
        //TODO hier fehlt zueg auf dem template
        return StatusDefiningCode2.ABGEBROCHEN;
    }


    public void invokeGrund(P pointEventEntity, MedicationStatement resource) {
        try {
            Method setGrundDefiningCode = pointEventEntity.getClass().getMethod("setGrundDefiningCode", GrundDefiningCode.class);
            if(getGrundDefiningCode(resource).isPresent()){
                setGrundDefiningCode.invoke(pointEventEntity, getGrundDefiningCode(resource).get());
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
