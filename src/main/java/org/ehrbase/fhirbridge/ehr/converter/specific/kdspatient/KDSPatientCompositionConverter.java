package org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient;

import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.PatientToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.personendaten.PersonenDatenAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.KDSPersonComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.GeschlechtEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

public class KDSPatientCompositionConverter extends PatientToCompositionConverter<KDSPersonComposition> {

    @Override
    public KDSPersonComposition convertInternal(@NonNull Patient resource) {
        KDSPersonComposition composition = new KDSPersonComposition();
        composition.setPersonendaten(new PersonenDatenAdminEntryConverter().convert(resource));
        GeschlechtEvaluation geschlechtEvaluation = new GeschlechtEvaluation();
        Coding coding = new Coding();
        coding.setCode(resource.getGender().toCode());
        coding.setSystem(resource.getGender().getSystem());
        coding.setDisplay(resource.getGender().getDisplay());
        DvCodedTextParser.getInstance().parseFHIRCoding(coding).ifPresent(geschlechtEvaluation::setAdministrativesGeschlecht);
        geschlechtEvaluation.setLanguage(Language.DE);
        composition.setGeschlecht(geschlechtEvaluation);
        return composition;
    }
}
