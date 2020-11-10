package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.camel.processor.EhrIdProcessor;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Component;

@Component
public class ConditionRoutes extends RouteBuilder {

    private final IFhirResourceDao<Condition> conditionDao;

    public ConditionRoutes(IFhirResourceDao<Condition> conditionDao) {
        this.conditionDao = conditionDao;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("cond-create:/service?audit=false")
            .routeId("create-condition")
            .process(new EhrIdProcessor())
            .bean(conditionDao, "create(${body})")
            .to("log:create-condition?showAll=true");

        from("cond-read:/service?audit=false")
            .routeId("read-condition")
            .to("log:read-condition?showAll=true");

        from("cond-search:/service?audit=false")
            .routeId("search-condition")
            .to("log:search-condition?showAll=true");
        // @formatter:on
    }
}
