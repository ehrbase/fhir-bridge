package org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.HashMap;

public class PatientInIcuCompositionConverter extends CompositionConverter<Observation, PatientAufICUComposition> {

    private  void setStatus(PatientAufICUComposition composition, Observation fhirObservation) {
        Observation.ObservationStatus status = fhirObservation.getStatus();

        if (status.equals(Observation.ObservationStatus.FINAL)) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else {
            throw new UnprocessableEntityException("Status has invalid code " + status.toCode());
        }
    }


    @Override
    public PatientAufICUComposition convertInternal(@NonNull Observation resource) {
        PatientAufICUComposition composition = new PatientAufICUComposition();
        setStatus(composition, resource);
        composition.setPatientAufDerIntensivstation(new PatientAufDerIntensivstationObservationConverter().convert(resource));
        composition.setStartTimeValue(resource.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());

        return composition;
    }
}