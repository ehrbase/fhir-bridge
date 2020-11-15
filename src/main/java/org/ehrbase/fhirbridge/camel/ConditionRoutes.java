package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.component.ehr.CompositionConstants;
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


        from("cond-read:/service?audit=false&fhirContext=#fhirContext")
            .routeId("fhir:read-condition")
            .setHeader(CompositionConstants.EHR_ID, constant("07f602e0-579e-4fe3-95af-381728bf0d49"))
            .setHeader(CompositionConstants.COMPOSITION_ID, constant("66786767-93cf-41e5-8618-67ae50a3d3b9"))
            .to("ehr-composition:/test?operation=find&expectedType=org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.DiagnoseComposition&converter=#diagnoseCompositionConverter");
        // @formatter:on
    }
}
