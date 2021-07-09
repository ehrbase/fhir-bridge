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
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AussageUberDenAusschlussDefiningCode;
import org.hl7.fhir.r4.model.Condition;

@SuppressWarnings("java:6212")
public class AusgeschlossenesSymptomEvaluationConverter
        extends EntryEntityConverter<Condition, AusgeschlossenesSymptomEvaluation>
        implements SymptomConverter {

    @Override
    protected AusgeschlossenesSymptomEvaluation convertInternal(Condition condition) {
        AusgeschlossenesSymptomEvaluation result = new AusgeschlossenesSymptomEvaluation();
        result.setAussageUeberDenAusschluss(AussageUberDenAusschlussDefiningCode.N410594000.toDvCodedText());
        result.setProblemDiagnose(convertCode(condition));
        return result;
    }
}
