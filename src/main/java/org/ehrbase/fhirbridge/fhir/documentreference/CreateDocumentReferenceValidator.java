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

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.fhir.validation.ValidationUtils;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;
import org.springframework.util.Assert;

import java.util.Map;

@SuppressWarnings("java:S3776")
public class CreateDocumentReferenceValidator extends FhirTransactionValidator.Support {

    private final FhirContext fhirContext;

    public CreateDocumentReferenceValidator(FhirContext fhirContext) {
        this.fhirContext = fhirContext;
    }

    @Override
    public void validateRequest(Object payload, Map<String, Object> headers) {
        Assert.notNull(payload, "Payload must not be null");

        var documentReference = (DocumentReference) payload;
        var outcome = new OperationOutcome();

        validateSubject(outcome, documentReference);
        validateContent(outcome, documentReference);

        if (outcome.hasIssue()) {
            throw new UnprocessableEntityException(fhirContext, outcome);
        }
    }

    /**
     * Validates <code>DocumentReference.subject</code> element.
     *
     * @param outcome           the {@link OperationOutcome} to register issues
     * @param documentReference the resource to validate
     */
    private void validateSubject(OperationOutcome outcome, DocumentReference documentReference) {
        if (!documentReference.hasSubject()) {
            ValidationUtils.addError(outcome, "DocumentReference must have one subject", "DocumentReference");
        } else {
            var subject = documentReference.getSubject();
            if (!subject.hasReference() && !subject.hasIdentifier()) {
                ValidationUtils.addError(outcome, "Subject must have a reference or an identifier",
                        "DocumentReference.subject");
            }
            if (subject.hasIdentifier() && (!subject.getIdentifier().hasSystem() || !subject.getIdentifier().hasValue())) {
                ValidationUtils.addError(outcome, "Subject identifier must have a system and a value",
                        "DocumentReference.subject.identifier");
            }
        }
    }

    /**
     * Validates <code>DocumentReference.content</code> element.
     *
     * @param outcome           the {@link OperationOutcome} to register issues
     * @param documentReference the resource to validate
     */
    private void validateContent(OperationOutcome outcome, DocumentReference documentReference) {
        if (documentReference.getContent().size() != 1) {
            ValidationUtils.addError(outcome, "DocumentReference must have exactly one DocumentReferenceContentComponent",
                    "DocumentReference.content");
        } else {
            var attachment = documentReference.getContentFirstRep().getAttachment();
            if (!attachment.hasData() || !attachment.hasContentType()) {
                ValidationUtils.addError(outcome, "Attachment must have a content type and data",
                        "DocumentReference.content[0].attachment");
            }
        }
    }
}
