package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.processor.PatientIdProcessor;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientRoutes extends RouteBuilder {

    private final IFhirResourceDao<Patient> patientDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    public PatientRoutes(IFhirResourceDao<Patient> patientDao,
                         DefaultCreateResourceRequestValidator requestValidator,
                         PatientIdProcessor patientIdProcessor) {
        this.patientDao = patientDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-create-patient:fhirConsumer?fhirContext=#fhirContext")
            .process(requestValidator)
            .bean(patientDao, "create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
//            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .setBody(header(FhirBridgeConstants.METHOD_OUTCOME));
        // @formatter:on
    }
}
