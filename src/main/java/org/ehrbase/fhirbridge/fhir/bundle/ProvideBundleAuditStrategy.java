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

package org.ehrbase.fhirbridge.fhir.bundle;

import org.openehealth.ipf.commons.audit.AuditContext;
import org.openehealth.ipf.commons.audit.model.AuditMessage;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditDataset;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

/**
 * Implementation of {@link org.openehealth.ipf.commons.ihe.core.atna.AuditStrategy AuditStrategy}
 * for 'Provide Bundle' transaction.
 *
 * @since 1.0.0
 */
public class ProvideBundleAuditStrategy extends FhirAuditStrategy<FhirAuditDataset> {

    public ProvideBundleAuditStrategy() {
        super(true, OperationOutcomeOperations.INSTANCE);
    }

    @Override
    public AuditMessage[] makeAuditMessage(AuditContext auditContext, FhirAuditDataset fhirAuditDataset) {
        return new AuditMessage[0];
    }

    @Override
    public FhirAuditDataset createAuditDataset() {
        return new FhirAuditDataset(isServerSide());
    }
}
