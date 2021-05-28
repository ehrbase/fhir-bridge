package org.ehrbase.fhirbridge.fhir.questionnaireresponse;

import org.ehrbase.fhirbridge.fhir.AbstractTransactionIT;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Integration Tests that validate "Find Questionnaire Response" transaction.
 */
class FindQuestionnaireResponseTransactionIT extends AbstractTransactionIT {

    @Test
    void findQuestionnaireResponseRead() throws Exception {
        var outcome = create("QuestionnaireResponse/transactions/provide-questionnaire-response-create.json");
        var id = outcome.getId();

        var questionnaireResponse = read(id.getIdPart(), QuestionnaireResponse.class);

        Assertions.assertNotNull(questionnaireResponse);
        Assertions.assertNotNull(questionnaireResponse.getId(), id.getIdPart());
        Assertions.assertEquals(PATIENT_ID, questionnaireResponse.getSubject().getIdentifier().getValue());
    }

    @Test
    void findQuestionnaireResponseVRead() throws Exception {
        var outcome = create("QuestionnaireResponse/transactions/provide-questionnaire-response-create.json");
        var id = outcome.getId();

        var questionnaireResponse = vread(id.getIdPart(), id.getVersionIdPart(), QuestionnaireResponse.class);
        Assertions.assertNotNull(questionnaireResponse);
        Assertions.assertNotNull(questionnaireResponse.getId(), id.getIdPart());
        Assertions.assertNotNull(questionnaireResponse.getMeta().getVersionId(), id.getVersionIdPart());
        Assertions.assertEquals(PATIENT_ID, questionnaireResponse.getSubject().getIdentifier().getValue());
    }

    @Test
    void findQuestionnaireResponseSearch() throws Exception {
        create("QuestionnaireResponse/transactions/find-questionnaire-response-search.json");
        create("QuestionnaireResponse/transactions/find-questionnaire-response-search.json");
        create("QuestionnaireResponse/transactions/find-questionnaire-response-search.json");

        Bundle bundle = search("QuestionnaireResponse?patient.identifier=" + PATIENT_ID + "&status=entered-in-error");

        Assertions.assertEquals(3, bundle.getTotal());

        bundle.getEntry().forEach(entry -> {
            var questionnaireResponse = (QuestionnaireResponse) entry.getResource();

            Assertions.assertEquals(PATIENT_ID, questionnaireResponse.getSubject().getIdentifier().getValue());
            Assertions.assertEquals(QuestionnaireResponse.QuestionnaireResponseStatus.ENTEREDINERROR, questionnaireResponse.getStatus());
        });
    }
}
