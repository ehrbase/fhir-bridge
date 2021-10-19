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

import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.hl7.fhir.r4.model.Procedure;

import java.util.Optional;

public interface TherapyConverter {

    default Optional<DvCodedText> convertCode(Procedure procedure) {
        return DvCodedTextParser.getInstance().parseFHIRCoding(procedure.getCode().getCodingFirstRep());
    }
}
