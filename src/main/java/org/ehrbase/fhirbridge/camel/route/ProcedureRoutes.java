package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.stereotype.Component;

@Component
public class ProcedureRoutes extends RouteBuilder {

    private final IFhirResourceDao<Procedure> procedureDao;

    public ProcedureRoutes(IFhirResourceDao<Procedure> procedureDao) {
        this.procedureDao = procedureDao;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("proc-create:/service?audit=false")
            .routeId("create-procedure")
            .bean(procedureDao, "create(${body})")
            .to("log:create-procedure?showAll=true");

        from("proc-read:/service?audit=false")
            .routeId("read-procedure")
            .to("log:read-procedure?showAll=true");

        from("proc-search:/service?audit=false")
            .routeId("search-procedure")
            .to("log:search-procedure?showAll=true");
        // @formatter:on
    }
}
