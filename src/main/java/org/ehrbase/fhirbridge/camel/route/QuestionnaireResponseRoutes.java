package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.processor.EhrIdLookupProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourceResponseProcessor;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.hl7.fhir.r4.model.QuestionnaireResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireResponseRoutes extends RouteBuilder {

    private final IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao;

    private final EhrIdLookupProcessor ehrIdLookupProcessor;

    private final ResourceResponseProcessor resourceResponseProcessor;

    public QuestionnaireResponseRoutes(IFhirResourceDao<QuestionnaireResponse> questionnaireResponseDao,
                                       EhrIdLookupProcessor ehrIdLookupProcessor,
                                       ResourceResponseProcessor resourceResponseProcessor) {
        this.questionnaireResponseDao = questionnaireResponseDao;
        this.ehrIdLookupProcessor = ehrIdLookupProcessor;
        this.resourceResponseProcessor = resourceResponseProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-create-questionnaire-response:fhirConsumer?fhirContext=#fhirContext")
            .onCompletion()
                .process("auditCreateResourceProcessor")
            .end()
            .onException(Exception.class)
                .process("defaultExceptionHandler")
            .end()
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, method(questionnaireResponseDao, "create"))
            .process(ehrIdLookupProcessor)
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity&compositionConverter=#d4lQuestionnaireCompositionConverter")
            .process(resourceResponseProcessor);
        // TODO Call Camel Composition component?
        // @formatter:on
    }

    // TODO: Update when Apache Camel > 3.x
    @Bean
    public D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter() {
        return new D4lQuestionnaireCompositionConverter();
    }
}
