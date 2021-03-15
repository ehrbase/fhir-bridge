package org.ehrbase.fhirbridge.ehr.converter.specificconverters.patient;

import org.ehrbase.fhirbridge.ehr.converter.PatientToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

public class PatientCompositionConverter extends PatientToCompositionConverter<GECCOPersonendatenComposition> {

    @Override
    public GECCOPersonendatenComposition convertInternal(@NonNull Patient resource) {
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        composition.setAlter(new AlterObservationConverter().convert(resource));
        composition.setPersonendaten(new PersonenDatenAdminEntryConverter().convert(resource));
        return composition;
    }

}
