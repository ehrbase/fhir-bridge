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

package org.ehrbase.fhirbridge.fhir.documentreference;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

public class CreateDocumentReferenceTransactionConfiguration extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public CreateDocumentReferenceTransactionConfiguration() {
        super(
                "documentreference-create",
                "Create DocumentReference",
                false,
                null,
                new CreateDocumentReferenceAuditStrategy(),
                FhirVersionEnum.R4,
                new CreateDocumentReferenceProvider(),
                null,
                CreateDocumentReferenceValidator::new);
    }

    @Override
    public void setSupportsLazyLoading(boolean supportsLazyLoading) {
        if (supportsLazyLoading) {
            throw new IllegalArgumentException("Lazy loading is not supported");
        }
    }
}
