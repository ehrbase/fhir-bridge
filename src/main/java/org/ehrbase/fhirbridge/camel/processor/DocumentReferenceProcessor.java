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
import io.minio.ObjectWriteResponse;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.minio.MinioService;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.DocumentReference;

import java.io.ByteArrayInputStream;

public class DocumentReferenceProcessor implements Processor {

    public static final String BEAN_ID = "documentReferenceProcessor";

    private final MinioService minioService;

    public DocumentReferenceProcessor(MinioService minioService) {
        this.minioService = minioService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        DocumentReference documentReference = exchange.getIn().getMandatoryBody(DocumentReference.class);
        Attachment attachment = documentReference.getContentFirstRep().getAttachment();

        ObjectWriteResponse response = minioService.uploadObject(new ByteArrayInputStream(attachment.getData()),
                attachment.getContentType());

        if (!attachment.hasSize()) {
            attachment.setSize(attachment.getData().length);
        }
        attachment.setUrl(minioService.getObjectUrl(response.object()));
        attachment.setData(null);

        exchange.getMessage().setHeader(CamelConstants.MINIO_OBJECT, response.object());

        MethodOutcome outcome = new MethodOutcome()
                .setResource(documentReference);
        exchange.setProperty(CamelConstants.OUTCOME, outcome);
    }
}
