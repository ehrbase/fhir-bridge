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

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ProcedureToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.GECCOProzedurComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeccoProzedurKategorieElement;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Procedure;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("java:S6212")
public class TherapyCompositionConverter extends ProcedureToCompositionConverter<GECCOProzedurComposition> {

    private final UnbekannteProzedurEvaluationConverter unknownConverter = new UnbekannteProzedurEvaluationConverter();

    private final NichtDurchgefuehrteProzedurEvaluationConverter notDoneConverter = new NichtDurchgefuehrteProzedurEvaluationConverter();

    private final ProzedurActionConverter actionConverter = new ProzedurActionConverter();

    @Override
    protected GECCOProzedurComposition convertInternal(Procedure procedure) {
        GECCOProzedurComposition result = new GECCOProzedurComposition();

        List<GeccoProzedurKategorieElement> categories = convertCategory(procedure);
        result.setKategorie(categories);

        if (procedure.getStatus() == Procedure.ProcedureStatus.UNKNOWN || !procedure.hasCategory()) {
            result.setUnbekannteProzedur(unknownConverter.convert(procedure));
        } else if (procedure.getStatus() == Procedure.ProcedureStatus.NOTDONE) {
            result.setNichtDurchgefuehrteProzedur(notDoneConverter.convert(procedure));
        } else {
            result.setProzedur(actionConverter.convert(procedure));
        }

        return result;
    }

    private List<GeccoProzedurKategorieElement> convertCategory(Procedure procedure) {
        List<GeccoProzedurKategorieElement> geccoProzedurKategorieElements = new ArrayList<>();
        for (Coding coding : procedure.getCategory().getCoding()) {
            GeccoProzedurKategorieElement geccoProzedurKategorieElement = new GeccoProzedurKategorieElement();
            DvCodedTextParser.getInstance()
                    .parseFHIRCoding(coding)
                    .ifPresent(geccoProzedurKategorieElement::setValue);
            geccoProzedurKategorieElements.add(geccoProzedurKategorieElement);
        }
        return geccoProzedurKategorieElements;
    }
}
