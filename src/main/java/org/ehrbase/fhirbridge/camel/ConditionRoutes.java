package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.processor.PatientIdProcessor;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Component;

@Component
public class ConditionRoutes extends RouteBuilder {

    private final IFhirResourceDao<Condition> conditionDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    private final OpenEhrClient openEhrClient;

    public ConditionRoutes(IFhirResourceDao<Condition> conditionDao,
                           DefaultCreateResourceRequestValidator requestValidator,
                           PatientIdProcessor patientIdProcessor,
                           OpenEhrClient openEhrClient) {
        this.conditionDao = conditionDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("cond-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("fhir:create-condition")
            .process(requestValidator)
            .bean(conditionDao, "create(${body})")
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .to("ehr-composition:/test?operation=mergeCompositionEntity&converter=#diagnoseCompositionConverter")
            .process(exchange -> {
                MethodOutcome methodOutcome = new MethodOutcome();
                methodOutcome.setResource(exchange.getIn().getBody(IBaseResource.class));
                exchange.getMessage().setBody(methodOutcome);
            });


        from("direct:create-ehr-composition")
            .routeId("direct:create-condition")
            .to("ehr-composition:/test?operation=mergeCompositionEntity");
        // @formatter:on
    }
}
