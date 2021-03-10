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

package org.ehrbase.fhirbridge.camel.component.fhir.observation;

import org.ehrbase.fhirbridge.fhir.observation.FindObservationTransaction;
import org.openehealth.ipf.commons.ihe.fhir.audit.FhirQueryAuditDataset;
import org.openehealth.ipf.platform.camel.ihe.fhir.core.custom.CustomFhirComponent;

/**
 * Camel {@link org.apache.camel.Component Component} that handles 'Find Observation' transaction.
 *
 * @since 1.0.0
 */
@SuppressWarnings({"java:S110"})
public class FindObservationComponent extends CustomFhirComponent<FhirQueryAuditDataset> {

    public FindObservationComponent() {
        super(new FindObservationTransaction());
    }
}
