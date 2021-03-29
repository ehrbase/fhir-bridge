package org.ehrbase.fhirbridge.ehr.converter.specific.geccoDiagnose;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AusgeschlosseneDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.UnbekannteDiagnoseEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition.VorliegendeDiagnoseEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.lang.NonNull;

import java.util.Optional;

public class GECCODiagnoseCompositionConverter extends CompositionConverter<Condition, GECCODiagnoseComposition> {

    private static final String VERIFICATION_STATUS_PRESENT_CODE = "410605003";

    private static final String VERIFICATION_STATUS_ABSENT_CODE = "410594000";

    private static final String SNOMED_SYSTEM = "http://snomed.info/sct";

    @Override
    public GECCODiagnoseComposition convertInternal(@NonNull Condition resource) {
        GECCODiagnoseComposition composition = new GECCODiagnoseComposition();

        Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnose = new VorliegendeDiagnoseConverter().map(resource);
        if (resource.getVerificationStatus().isEmpty()) {
            composition.setUnbekannteDiagnose(this.toUnbekannteDiagnose(resource));
        } else {
            Coding verficiationStatus = resource.getVerificationStatus().getCoding().get(
                    resource.getVerificationStatus().getCoding().size() - 1); // snomed code is the last element

            if (verficiationStatus.getSystem().equals(SNOMED_SYSTEM) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_PRESENT_CODE)) {
                vorliegendeDiagnose.ifPresent(composition::setVorliegendeDiagnose);
            } else if (verficiationStatus.getSystem().equals(SNOMED_SYSTEM) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_ABSENT_CODE)) {
                composition.setAusgeschlosseneDiagnose(this.toAusgeschlosseneDiagnose(resource));
            } else {
                throw new ConversionException("Cant identify the verification status");
            }
        }

        Coding categoryCoding = resource.getCategory().get(0).getCoding().get(0);
        if (categoryCoding.getSystem().equals(SNOMED_SYSTEM) && GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().containsKey(categoryCoding.getCode())) {
            composition.setKategorieDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().get(categoryCoding.getCode()));
        } else {
            throw new ConversionException("Category not present");
        }

        composition.setStartTimeValue(resource.getRecordedDateElement().getValueAsCalendar().toZonedDateTime());
        // ======================================================================================
        // Required fields by API
        return composition;
    }


    private UnbekannteDiagnoseEvaluation toUnbekannteDiagnose(Condition condition) {
        UnbekannteDiagnoseEvaluation unbekannteDiagnose = new UnbekannteDiagnoseEvaluation();
        unbekannteDiagnose.setAussageUeberDieFehlendeInformationDefiningCode(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE);
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(problem.getCode())) {
            unbekannteDiagnose.setUnbekannteDiagnoseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().get(problem.getCode()));
        }
        unbekannteDiagnose.setSubject(new PartySelf());
        unbekannteDiagnose.setLanguage(Language.DE);
        return unbekannteDiagnose;
    }


    private AusgeschlosseneDiagnoseEvaluation toAusgeschlosseneDiagnose(Condition condition) {
        AusgeschlosseneDiagnoseEvaluation ausgeschlosseneDiagnose = new AusgeschlosseneDiagnoseEvaluation();
        ausgeschlosseneDiagnose.setAussageUeberDenAusschlussDefiningCode(AussageUeberDenAusschlussDefiningCode.KNOWN_ABSENT_QUALIFIER_VALUE);
        Coding problem = condition.getCode().getCoding().get(0);
        if (problem.getSystem().equals(SNOMED_SYSTEM) &&
                GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().containsKey(problem.getCode())) {
            ausgeschlosseneDiagnose.setProblemDiagnoseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getProblemDiagnoseMap().get(problem.getCode()));
        }
        ausgeschlosseneDiagnose.setSubject(new PartySelf());
        ausgeschlosseneDiagnose.setLanguage(Language.DE);
        return ausgeschlosseneDiagnose;
    }
}

