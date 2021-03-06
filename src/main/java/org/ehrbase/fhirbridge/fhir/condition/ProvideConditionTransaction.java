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

package org.ehrbase.fhirbridge.fhir.condition;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditDataset;

/**
 * 'Provide Condition' {@link org.openehealth.ipf.commons.ihe.core.TransactionConfiguration TransactionConfiguration}.
 * <p>
 * Note: Server-side only
 *
 * @since 1.2.0
 */
public class ProvideConditionTransaction extends FhirTransactionConfiguration<GenericFhirAuditDataset> {

    public ProvideConditionTransaction() {
        super("condition-provide",
                "Provide Condition Transaction",
                false,
                null,
                new ProvideConditionAuditStrategy(),
                FhirVersionEnum.R4,
                new ProvideConditionProvider(),
                null,
                FhirTransactionValidator.NO_VALIDATION);
    }
}
