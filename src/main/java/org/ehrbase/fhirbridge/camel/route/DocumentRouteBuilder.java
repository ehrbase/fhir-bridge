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

package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.hl7v2.model.v25.message.MDM_T02;
import ca.uhn.hl7v2.model.v25.segment.EVN;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * @since 1.3.0
 */
@Component
public class DocumentRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("mllp://0.0.0.0:8889?hl7TransactionConfig=#transactionConfiguration")
                .process(exchange -> {
                    MDM_T02 message = exchange.getIn().getBody(MDM_T02.class);
                    EVN evn = message.getEVN();
                    message.getOBXNTE();
                });
    }
}
