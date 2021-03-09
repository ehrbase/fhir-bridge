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

package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.archetyped.FeederAuditDetails;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.Resource;
import org.springframework.lang.NonNull;

import java.util.Collections;

public interface Converter<S extends Resource, T> {

    T convert(@NonNull S resource);

    default Language resolveLanguage(String languageCode) {
        for (Language language : Language.values()) {
            if (StringUtils.equalsIgnoreCase(language.getCode(), languageCode)) {
                return language;
            }
        }
        return Language.DE;
    }

    default FeederAudit buildFeederAudit(S resource) {
        FeederAudit audit = new FeederAudit();

        FeederAuditDetails auditDetails = new FeederAuditDetails();
        if (resource.getMeta().hasSource()) {
            auditDetails.setSystemId(resource.getMeta().getSource());
        } else {
            auditDetails.setSystemId("FHIR-Bridge");
        }
        audit.setOriginatingSystemAudit(auditDetails);

        DvIdentifier id = new DvIdentifier();
        id.setId(resource.getId());
        id.setType("fhir_logical_id");
        audit.setOriginatingSystemItemIds(Collections.singletonList(id));

        return audit;
    }
}
