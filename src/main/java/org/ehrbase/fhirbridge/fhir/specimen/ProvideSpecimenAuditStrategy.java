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

package org.ehrbase.fhirbridge.fhir.specimen;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Specimen;
import org.openehealth.ipf.commons.ihe.fhir.audit.GenericFhirAuditStrategy;
import org.openehealth.ipf.commons.ihe.fhir.support.OperationOutcomeOperations;

import java.util.Optional;

/**
 * Custom implementation of {@link GenericFhirAuditStrategy} for 'Provide Specimen' transaction.
 *
 * @since 1.2.0
 */
public class ProvideSpecimenAuditStrategy extends GenericFhirAuditStrategy<Specimen> {

    public ProvideSpecimenAuditStrategy() {
        super(true, OperationOutcomeOperations.INSTANCE, specimen -> Optional.of(specimen.getSubject()));
    }
}
