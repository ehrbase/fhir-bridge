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
        composition.setPatientAufDerIntensivstation(new PatientAufDerIntensivstationObservationConverter().convert(resource));
        return composition;
    }

}