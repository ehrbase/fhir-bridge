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

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.generic.ConditionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomAnatomischeLokalisationElement;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("java:S6212")
public class VorliegendesSymptomObservationConverter extends ConditionToObservationConverter<VorliegendesSymptomObservation> implements SymptomConverter {

    @Override
    protected VorliegendesSymptomObservation convertInternal(Condition condition) {
        VorliegendesSymptomObservation result = new VorliegendesSymptomObservation();
        convertCode(condition).ifPresent(result::setNameDesSymptomsKrankheitsanzeichens);
        result.setAnatomischeLokalisation(convertBodySites(condition));
        result.setBeginnDerEpisodeValue(TimeConverter.convertConditionOnset(condition));
        convertSeverity(condition).ifPresent(result::setSchweregrad);
        TimeConverter.convertConditionAbatementTime(condition).ifPresent(result::setDatumUhrzeitDesRueckgangsValue);
        return result;
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
        for(Coding coding: condition.getSeverity().getCoding()){
            if(coding.getSystem().equals(CodeSystem.SNOMED.getUrl())){
                return DvCodedTextParser.parseFHIRCoding(coding);
            }
        }
        return Optional.empty();
    }
}
