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

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.DefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomAussageUeberDieFehlendeInformationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.hl7.fhir.r4.model.Condition;

import java.util.Collections;

@SuppressWarnings("java:6212")
public class UnbekanntesSymptomEvaluationConverter extends EntryEntityConverter<Condition, UnbekanntesSymptomEvaluation>
        implements SymptomConverter {

    @Override
    protected UnbekanntesSymptomEvaluation convertInternal(Condition condition) {
        UnbekanntesSymptomEvaluation result = new UnbekanntesSymptomEvaluation();
        result.setUnbekanntesSymptom(convertCode(condition));

        UnbekanntesSymptomAussageUeberDieFehlendeInformationElement element = new UnbekanntesSymptomAussageUeberDieFehlendeInformationElement();
        element.setValue(DefiningCode.N261665006.toDvCodedText());
        result.setAussageUeberDieFehlendeInformation(Collections.singletonList(element));

        return result;

    }
}
