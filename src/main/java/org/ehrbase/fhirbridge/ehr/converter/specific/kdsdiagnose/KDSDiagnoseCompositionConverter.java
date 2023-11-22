package org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose;

import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.KDSDiagnoseComposition;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.PrimaercodeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.SekundaercodeEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.Extension;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class KDSDiagnoseCompositionConverter extends ConditionToCompositionConverter<KDSDiagnoseComposition> {

    @Override
    public KDSDiagnoseComposition convertInternal(@NonNull Condition resource) {
        KDSDiagnoseComposition composition = new KDSDiagnoseComposition();
        List<PrimaercodeEvaluation> primaercodeEvaluationList = new ArrayList<>();
        List<SekundaercodeEvaluation> sekundaercodeEvaluationList = new ArrayList<>();

        for(Coding code: resource.getCode().getCoding()){
            for(Extension extension: code.getExtension()){
               if(extension.getUrl().equals("http://fhir.de/StructureDefinition/icd-10-gm-primaercode")){
                   primaercodeEvaluationList.add(new PrimaercodeEvaluationConverter(extension).convert(resource));
               }
                if(extension.getUrl().equals("http://fhir.de/StructureDefinition/icd-10-gm-manifestationscode")){
                    sekundaercodeEvaluationList.add(new SekundaercodeEvaluationConverter(extension).convert(resource));
                }
            }
        }
        composition.setPrimaercode(primaercodeEvaluationList);
        composition.setSekundaercode(sekundaercodeEvaluationList);
        return composition;
    }
}
