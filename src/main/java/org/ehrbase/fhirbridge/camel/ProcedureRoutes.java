package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.processor.PatientIdProcessor;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureRoutes extends RouteBuilder {

    private final IFhirResourceDao<Procedure> procedureDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    public ProcedureRoutes(IFhirResourceDao<Procedure> procedureDao,
                           DefaultCreateResourceRequestValidator requestValidator,
                           PatientIdProcessor patientIdProcessor) {
        this.procedureDao = procedureDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("proc-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-procedure")
            .process(requestValidator)
            .bean(procedureDao, "create(${body})")
            .process(patientIdProcessor)
            .to("log:create-procedure?showAll=true");

        from("proc-read:/service?audit=false&fhirContext=#fhirContext")
            .routeId("read-procedure")
            .to("log:read-procedure?showAll=true");

        from("proc-search:/service?audit=false&fhirContext=#fhirContext")
            .routeId("search-procedure")
            .to("log:search-procedure?showAll=true");
        // @formatter:on
    }
}
