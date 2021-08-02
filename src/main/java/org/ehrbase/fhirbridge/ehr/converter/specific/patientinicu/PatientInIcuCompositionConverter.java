package org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.PatientAufICUComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PatientInIcuCompositionConverter extends ObservationToCompositionConverter<PatientAufICUComposition> {

    @Override
    public PatientAufICUComposition convertInternal(@NonNull Observation resource) {
        PatientAufICUComposition composition = new PatientAufICUComposition();
        setStatus(composition, resource);
        composition.setPatientAufDerIntensivstation(new PatientAufDerIntensivstationObservationConverter().convert(resource));
        return composition;
    }

    private void setStatus(PatientAufICUComposition composition, Observation fhirObservation) {
        Observation.ObservationStatus status = fhirObservation.getStatus();
        if (status.equals(Observation.ObservationStatus.FINAL)) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else {
            throw new ConversionException("Status has invalid code " + status.toCode());
        }
    }

}