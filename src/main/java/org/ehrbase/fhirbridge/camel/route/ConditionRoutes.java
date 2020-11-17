package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.PatientIdProcessor;
import org.ehrbase.fhirbridge.ehr.converter.DiagnoseCompositionConverter;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConditionRoutes extends RouteBuilder {

    private final IFhirResourceDao<Condition> conditionDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    public ConditionRoutes(IFhirResourceDao<Condition> conditionDao,
                           DefaultCreateResourceRequestValidator requestValidator,
                           PatientIdProcessor patientIdProcessor) {
        this.conditionDao = conditionDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-create-condition:/service?audit=false&fhirContext=#fhirContext")
            .process(requestValidator)
            .bean(conditionDao, "create(${body})")
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity&compositionConverter=#diagnoseCompositionConverter")
            .setBody(exchange -> new MethodOutcome().setResource(exchange.getIn().getBody(IBaseResource.class)));
        // @formatter:on
    }

    @Bean
    public DiagnoseCompositionConverter diagnoseCompositionConverter() {
        return new DiagnoseCompositionConverter();
    }
}
