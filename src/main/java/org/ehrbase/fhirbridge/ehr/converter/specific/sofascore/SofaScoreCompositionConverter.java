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

package org.ehrbase.fhirbridge.ehr.converter.specific.sofascore;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition.SofaScoreKategorieElement;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("java:S6212")
public class SofaScoreCompositionConverter extends ObservationToCompositionConverter<SOFAComposition> {

    private final SofaScoreObservationConverter sofaScoreObservationConverter = new SofaScoreObservationConverter();

    @Override
    public SOFAComposition convertInternal(@NonNull Observation observation) {
        SOFAComposition result = new SOFAComposition();
        result.setKategorie(convertCategory(observation));
        result.setSofaScore(sofaScoreObservationConverter.convert(observation));
        return result;
    }

    public List<SofaScoreKategorieElement> convertCategory(Observation observation) {
        return observation.getCategory()
                .stream()
                .flatMap(concept -> concept.getCoding().stream())
                .filter(Coding::hasCode)
                .map(coding -> {
                    SofaScoreKategorieElement element = new SofaScoreKategorieElement();
                    element.setValue(coding.getCode());
                    return element;
                })
                .collect(Collectors.toList());
    }
}
