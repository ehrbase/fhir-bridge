package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient;

import org.ehrbase.fhirbridge.ehr.converter.generic.PatientToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.personendaten.PersonenDatenAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

public class PatientCompositionConverter extends PatientToCompositionConverter<GECCOPersonendatenComposition> {

    @Override
    public GECCOPersonendatenComposition convertInternal(@NonNull Patient resource) {
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        if(resource.hasExtension("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age")){
            composition.setAlter(new AlterObservationConverter().convert(resource));
        }
        composition.setPersonendaten(new PersonenDatenAdminEntryConverter().convert(resource));
        return composition;
    }
}
