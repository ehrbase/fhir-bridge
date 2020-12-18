package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.ehr.converter.DiagnosticReportLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QuestionnaireResponseRoutes extends RouteBuilder {

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
            .bean("myQuestionnaireResponseDaoR4","create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process("patientIdProcessor")
             .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity&compositionConverter=#d4lQuestionnaireCompositionConverter")
            .setBody(header(FhirBridgeConstants.METHOD_OUTCOME));
        // @formatter:on
    }

    // TODO: Update when Apache Camel > 3.x
    @Bean
    public D4lQuestionnaireCompositionConverter d4lQuestionnaireCompositionConverter() {
        return new D4lQuestionnaireCompositionConverter();
    }
}
