/*
 * Copyright 2022 the original author or authors.
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

package org.ehrbase.fhirbridge.web;

import org.apache.camel.ProducerTemplate;
import org.openehealth.ipf.commons.ihe.xds.core.requests.ProvideAndRegisterDocumentSet;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Renaud Subiger
 * @since 1.6
 */
@RestController
@RequestMapping(path = "/test")
public class TestController {
    //TEST copntroller for ITI41 is working do not use productive !
    private final ProducerTemplate producerTemplate;

    public TestController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @PostMapping(path = "/iti-41")
    public ResponseEntity<RegistryResponseType> testIti41() {
        var response = producerTemplate.requestBody("direct:send-to-cdr", new ProvideAndRegisterDocumentSet(), RegistryResponseType.class);
        return ResponseEntity.ok(response);
    }
}
