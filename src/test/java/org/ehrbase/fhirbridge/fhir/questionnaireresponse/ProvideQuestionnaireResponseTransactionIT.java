package org.ehrbase.fhirbridge.fhir.questionnaireresponse;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Integration Tests that validate "Provide Questionnaire Response" transaction.
 */
class ProvideQuestionnaireResponseTransactionIT extends AbstractTransactionIT {

    @Test
    void provideQuestionnaireResponseCreate() throws IOException {
        MethodOutcome outcome = create("QuestionnaireResponse/transactions/provide-questionnaire-response-create.json");

        Assertions.assertEquals(true, outcome.getCreated());
        Assertions.assertNotNull(outcome.getId().getValue());
    }

    @Test
    void provideQuestionnaireResponseConditionalUpdate() throws IOException {
        MethodOutcome outcome;

        outcome = create("QuestionnaireResponse/transactions/provide-questionnaire-response-create.json");
        IIdType id = outcome.getId();

        outcome = update("QuestionnaireResponse/transactions/provide-questionnaire-response-update.json", "QuestionnaireResponse?_id=" + id.getIdPart() + "&patient.identifier=" + PATIENT_ID);

        Assertions.assertEquals(id.getIdPart(), outcome.getId().getIdPart());
        Assertions.assertEquals(id.getVersionIdPartAsLong() + 1, outcome.getId().getVersionIdPartAsLong());

        QuestionnaireResponse questionnaireResponse = (QuestionnaireResponse) outcome.getResource();

        Assertions.assertEquals(PATIENT_ID, questionnaireResponse.getSubject().getIdentifier().getValue());
        Assertions.assertEquals(QuestionnaireResponse.QuestionnaireResponseStatus.AMENDED, questionnaireResponse.getStatus());
    }
}
