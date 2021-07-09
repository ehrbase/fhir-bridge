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

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.KrankheitsanzeichenCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.SchweregradSymptomCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomAnatomischeLokalisationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("java:S6212")
public class VorliegendesSymptomObservationConverter extends ConditionToObservationConverter<VorliegendesSymptomObservation> {

    @Override
    protected VorliegendesSymptomObservation convertInternal(Condition condition) {
        VorliegendesSymptomObservation result = new VorliegendesSymptomObservation();
        result.setNameDesSymptomsKrankheitsanzeichens(convertCode(condition));
        result.setAnatomischeLokalisation(convertBodySites(condition));
//        result.setBeginnDerEpisodeValue(convertOnSet(condition));
        convertSeverity(condition).ifPresent(result::setSchweregrad);
//        result.setDatumUhrzeitDesRueckgangsValue(convertAbatement(condition));
        return result;
    }

    private DvCodedText convertCode(Condition condition) {
        return condition.getCode()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(coding -> KrankheitsanzeichenCode.getCodesAsMap().get(coding.getCode()))
                .orElseThrow(() -> new ConversionException("Unbekanntes <unbekanntes Symptom>"))
                .toDvCodedText();
    }

    private List<VorliegendesSymptomAnatomischeLokalisationElement> convertBodySites(Condition condition) {
        if (!condition.hasBodySite()) {
            return new ArrayList<>();
        }

        return condition.getBodySite()
                .stream()
                .flatMap(bodySite -> bodySite.getCoding().stream())
                .map(coding -> {
                    VorliegendesSymptomAnatomischeLokalisationElement result = new VorliegendesSymptomAnatomischeLokalisationElement();
                    result.setValue(coding.getCode());
                    return result;
                })
                .collect(Collectors.toList());
    }

    private Optional<DvCodedText> convertSeverity(Condition condition) {
        if (!condition.hasSeverity()) {
            return Optional.empty();
        }

        return condition.getSeverity()
                .getCoding()
                .stream()
                .filter(coding -> coding.getSystem().equals(CodeSystem.SNOMED.getUrl()))
                .findFirst()
                .map(coding -> SchweregradSymptomCode.getCodesAsMap().get(coding.getCode()))
                .map(SchweregradSymptomCode::toDvCodedText);
    }
}
