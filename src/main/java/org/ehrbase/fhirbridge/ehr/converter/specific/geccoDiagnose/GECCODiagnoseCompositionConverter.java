package org.ehrbase.fhirbridge.ehr.converter.specific.geccoDiagnose;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
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

public class GECCODiagnoseCompositionConverter extends ConditionToCompositionConverter<GECCODiagnoseComposition> {

    private static final String VERIFICATION_STATUS_PRESENT_CODE = "410605003";

    private static final String VERIFICATION_STATUS_ABSENT_CODE = "410594000";

    @Override
    public GECCODiagnoseComposition convertInternal(@NonNull Condition resource) {
        GECCODiagnoseComposition composition = new GECCODiagnoseComposition();

        Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnose = Optional.of(new VorliegendeDiagnoseConverter().convert(resource));
        if (resource.getVerificationStatus().isEmpty()) {
            composition.setUnbekannteDiagnose(new UnbekannteDiagnoseEvaluationConverter().convert(resource));
        } else {
            Coding verficiationStatus = resource.getVerificationStatus().getCoding().get(
                    resource.getVerificationStatus().getCoding().size() - 1); // snomed code is the last element

            if (verficiationStatus.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_PRESENT_CODE)) {
                vorliegendeDiagnose.ifPresent(composition::setVorliegendeDiagnose);
            } else if (verficiationStatus.getSystem().equals(CodeSystem.SNOMED.getUrl()) &&
                    verficiationStatus.getCode().equals(VERIFICATION_STATUS_ABSENT_CODE)) {
                composition.setAusgeschlosseneDiagnose( new AusgeschlosseneDiagnoseConverter().convert(resource));
            } else {
                throw new ConversionException("Cant identify the verification status");
            }
        }

        Coding categoryCoding = resource.getCategory().get(0).getCoding().get(0);
        if (categoryCoding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().containsKey(categoryCoding.getCode())) {
            composition.setKategorieDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().get(categoryCoding.getCode()));
        } else {
            throw new ConversionException("Category not present");
        }
        return composition;
    }

}

