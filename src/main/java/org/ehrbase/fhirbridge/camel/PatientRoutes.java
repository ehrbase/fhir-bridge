package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientRoutes extends RouteBuilder {

    private final IFhirResourceDao<Patient> patientDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    public PatientRoutes(IFhirResourceDao<Patient> patientDao, DefaultCreateResourceRequestValidator requestValidator) {
        this.patientDao = patientDao;
        this.requestValidator = requestValidator;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("pt-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-patient")
            .process(requestValidator)
            .bean(patientDao, "create(${body})")
            .to("log:create-patient?showAll=true");
        // @formatter:on
    }
}
