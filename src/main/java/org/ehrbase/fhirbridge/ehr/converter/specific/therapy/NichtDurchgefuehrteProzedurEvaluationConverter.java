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

package org.ehrbase.fhirbridge.ehr.converter.specific.therapy;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NichtDurchgefuehrteProzedurEvaluation;
import org.hl7.fhir.r4.model.Procedure;

@SuppressWarnings("java:S6212")
public class NichtDurchgefuehrteProzedurEvaluationConverter
        extends EntryEntityConverter<Procedure, NichtDurchgefuehrteProzedurEvaluation>
        implements TherapyConverter {

    @Override
    protected NichtDurchgefuehrteProzedurEvaluation convertInternal(Procedure procedure) {
        NichtDurchgefuehrteProzedurEvaluation result = new NichtDurchgefuehrteProzedurEvaluation();
        result.setAussageUeberDenAusschlussValue(Procedure.ProcedureStatus.NOTDONE.getDisplay());
        result.setEingriffDefiningCode(convertCode(procedure));
        return result;
    }
}
