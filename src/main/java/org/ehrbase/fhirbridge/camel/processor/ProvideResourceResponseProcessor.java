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

package org.ehrbase.fhirbridge.camel.processor;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.core.domain.ResourceComposition;
import org.ehrbase.fhirbridge.core.repository.ResourceCompositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("java:S6212")
public class ProvideResourceResponseProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(ProvideResourceResponseProcessor.class);

    private final ResourceCompositionRepository resourceCompositionRepository;

    public ProvideResourceResponseProcessor(ResourceCompositionRepository resourceCompositionRepository) {
        this.resourceCompositionRepository = resourceCompositionRepository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        CompositionEntity composition = exchange.getIn().getBody(CompositionEntity.class);
        MethodOutcome outcome = exchange.getProperty(CamelConstants.OUTCOME, MethodOutcome.class);

        String resourceId = outcome.getId().getIdPart();
        ResourceComposition resourceComposition = resourceCompositionRepository.findById(resourceId)
                .orElse(new ResourceComposition(resourceId));
        resourceComposition.setCompositionId(getCompositionId(composition));
        resourceCompositionRepository.save(resourceComposition);
        LOG.debug("Saved ResourceComposition: resourceId={}, compositionId={}",
                resourceComposition.getResourceId(), resourceComposition.getCompositionId());

        exchange.getMessage().setBody(outcome);
    }

    private String getCompositionId(CompositionEntity composition) {
        return composition.getVersionUid().toString();
    }
}