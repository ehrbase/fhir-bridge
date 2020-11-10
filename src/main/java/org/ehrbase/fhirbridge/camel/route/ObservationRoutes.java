package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;

@Component
public class ObservationRoutes extends RouteBuilder {

    private final IFhirResourceDao<Observation> observationDao;

    public ObservationRoutes(IFhirResourceDao<Observation> observationDao) {
        this.observationDao = observationDao;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("obs-create:/service?audit=false")
            .routeId("create-observation")
            .bean(observationDao, "create(${body})")
            .to("log:create-observation?showAll=true");

        from("obs-read:/service?audit=false")
            .routeId("read-observation")
            .to("log:read-observation?showAll=true");
        // @formatter:on
    }
}
