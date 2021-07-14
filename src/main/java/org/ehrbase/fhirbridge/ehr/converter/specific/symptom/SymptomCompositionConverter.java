/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.ehr.converter.specific.symptom;

import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.SymptomComposition;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KategorieDefiningCodeSymptom;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.codesystems.ConditionVerStatus;
import org.springframework.lang.NonNull;

import java.util.Optional;

@SuppressWarnings("java:S6212")
public class SymptomCompositionConverter extends ConditionToCompositionConverter<SymptomComposition> {

    private final VorliegendesSymptomObservationConverter confirmedConverter = new VorliegendesSymptomObservationConverter();

    private final AusgeschlossenesSymptomEvaluationConverter refutedConverter = new AusgeschlossenesSymptomEvaluationConverter();

    private final UnbekanntesSymptomEvaluationConverter unknownConverter = new UnbekanntesSymptomEvaluationConverter();

    @Override
    public SymptomComposition convertInternal(@NonNull Condition condition) {
        SymptomComposition result = new SymptomComposition();
        result.setKategorie(KategorieDefiningCodeSymptom.N753251.toDvCodedText());

        Optional<ConditionVerStatus> conditionVerStatus = resolveConditionVerStatus(condition);
        if (conditionVerStatus.isPresent() && conditionVerStatus.get() == ConditionVerStatus.CONFIRMED) {
            result.setVorliegendesSymptom(confirmedConverter.convert(condition));
        } else if (conditionVerStatus.isPresent() && conditionVerStatus.get() == ConditionVerStatus.REFUTED) {
            result.setAusgeschlossenesSymptom(refutedConverter.convert(condition));
        } else {
            result.setUnbekanntesSymptom(unknownConverter.convert(condition));
        }

        return result;
    }

    private Optional<ConditionVerStatus> resolveConditionVerStatus(Condition condition) {
        return condition.getVerificationStatus()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/condition-ver-status"))
                .map(coding -> ConditionVerStatus.fromCode(coding.getCode()))
                .findFirst();
    }
}
