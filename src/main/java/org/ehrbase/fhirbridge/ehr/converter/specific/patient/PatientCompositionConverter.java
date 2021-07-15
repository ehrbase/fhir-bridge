package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import org.ehrbase.fhirbridge.ehr.converter.generic.PatientToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten.PersonenDatenAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

public class PatientCompositionConverter extends PatientToCompositionConverter<GECCOPersonendatenComposition> {

    @Override
    public GECCOPersonendatenComposition convertInternal(@NonNull Patient resource) {
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        composition.setAlter(new AlterObservationConverter().convert(resource));
        composition.setPersonendaten(new PersonenDatenAdminEntryConverter().convert(resource));
        new GeschlechtConverter().convert(resource).ifPresent(composition::setGeschlecht);
        return composition;
    }

}
