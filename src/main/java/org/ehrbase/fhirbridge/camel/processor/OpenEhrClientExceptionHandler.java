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

import ca.uhn.fhir.rest.server.exceptions.InternalErrorException;
import ca.uhn.fhir.rest.server.exceptions.ResourceVersionConflictException;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.client.exception.OptimisticLockException;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.springframework.util.Assert;

/**
 * {@link org.apache.camel.Processor Processor} that transforms an exception
 * thrown by the {@link org.ehrbase.client.openehrclient.OpenEhrClient OpenEhrClient} into the corresponding FHIR exception.
 *
 * @since 1.2.0
 */
public class OpenEhrClientExceptionHandler implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        Assert.notNull(ex, "Exception must not be null");

        if (ex instanceof WrongStatusCodeException) {
            handleWrongStatusCode((WrongStatusCodeException) ex);
        } else if (ex instanceof OptimisticLockException) {
            handleOptimisticLock((OptimisticLockException) ex);
        }
        handleException(ex);
    }

    private void handleWrongStatusCode(WrongStatusCodeException ex) {
        if (ex.getActualStatusCode() == 400) {
            throw new UnprocessableEntityException(ex.getMessage());
        } else {
            handleException(ex);
        }
    }

    private void handleOptimisticLock(OptimisticLockException ex) {
        throw new ResourceVersionConflictException(ex.getMessage());
    }

    private void handleException(Exception ex) {
        throw new InternalErrorException("Error occurred while merging composition in EHRbase: " + ex.getMessage(), ex);
    }
}
