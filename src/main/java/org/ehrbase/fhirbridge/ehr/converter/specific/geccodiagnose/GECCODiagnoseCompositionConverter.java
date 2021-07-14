package org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.GECCODiagnoseComposition;
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
        Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnose = getVorliegendeDiagnose(resource);
        if (resource.getVerificationStatus().isEmpty()) {
            composition.setUnbekannteDiagnose(new UnbekannteDiagnoseEvaluationConverter().convert(resource));
        } else {
            mapVerficationStatus(resource, vorliegendeDiagnose, composition);
        }
        mapCategoryCoding(resource, composition);
        return composition;
    }

    private void mapCategoryCoding(Condition resource, GECCODiagnoseComposition composition) {
        Coding categoryCoding = resource.getCategory().get(0).getCoding().get(0);
        if (categoryCoding.getSystem().equals(CodeSystem.SNOMED.getUrl()) && GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().containsKey(categoryCoding.getCode())) {
            composition.setKategorie(DvCodedTextParser.parseDefiningCode(GeccoDiagnoseCodeDefiningCodeMaps.getKategorieMap().get(categoryCoding.getCode())));
        } else {
            throw new UnprocessableEntityException("Category has either no or an unsupported SNOMED code");
        }
    }

    private void mapVerficationStatus(Condition resource, Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnose, GECCODiagnoseComposition composition) {
        for (Coding coding : resource.getVerificationStatus().getCoding()) {
            if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
               determineIfDiagnosePresent(coding, vorliegendeDiagnose, composition, resource);
            }
        }
    }

    private void determineIfDiagnosePresent(Coding coding, Optional<VorliegendeDiagnoseEvaluation> vorliegendeDiagnose, GECCODiagnoseComposition composition, Condition resource) {
        if (coding.getCode().equals(VERIFICATION_STATUS_PRESENT_CODE)) {
            vorliegendeDiagnose.ifPresent(composition::setVorliegendeDiagnose);
        } else if (coding.getCode().equals(VERIFICATION_STATUS_ABSENT_CODE)) {
            composition.setAusgeschlosseneDiagnose(new AusgeschlosseneDiagnoseConverter().convert(resource));
        } else {
            throw new UnprocessableEntityException("SNOMED code is invalid in VerificationStatus.coding.code");
        }
    }

    private Optional<VorliegendeDiagnoseEvaluation> getVorliegendeDiagnose(Condition resource) {
        VorliegendeDiagnoseEvaluationConverter vorliegendeDiagnoseEvaluationConverter = new VorliegendeDiagnoseEvaluationConverter();
        VorliegendeDiagnoseEvaluation vorliegendeDiagnose = vorliegendeDiagnoseEvaluationConverter.convert(resource);
        if (vorliegendeDiagnoseEvaluationConverter.getIsEmpty()) {
            return Optional.empty();
        }
        return Optional.of(vorliegendeDiagnose);
    }

}

