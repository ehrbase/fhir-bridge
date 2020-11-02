package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record1;
import org.ehrbase.fhirbridge.camel.processor.DiagnoseCompositionMapper;
import org.ehrbase.fhirbridge.ehr.template.diagnosecomposition.DiagnoseComposition;
import org.hl7.fhir.r4.model.IdType;
import org.springframework.stereotype.Component;

@Component
public class ConditionRoutes extends RouteBuilder {

    @Override
    public void configure() {
        // @formatter:off
        from("condition-create:/service")
            .routeId("condition-create")
            .bean("myConditionDaoR4", "create(${body})")
//            .to("ehr:ehrbase?ehrEndpointType=composition")
            .process(exchange -> exchange.getMessage().setBody(new MethodOutcome()))
            .to("log:debug?showAll=true");


        from("condition-read:/service")
            .routeId("condition-read")
            .process(exchange -> {
                IdType id = exchange.getIn().getBody(IdType.class);
                Query<Record1<DiagnoseComposition>> query = Query.buildNativeQuery(
                        "SELECT c " +
                            "FROM EHR e CONTAINS COMPOSITION c " +
                            "WHERE c/archetype_details/template_id/value = 'Diagnose' " +
                                "AND c/uid/value = '" + id.getIdPart() + "'",
                        DiagnoseComposition.class
                );
                exchange.getMessage().setBody(query);
            })
            .to("ehr:ehrbase?ehrEndpointType=aql")
            .choice()
                .when(simple("${body.size} == 0"))
                    .setBody(constant(null))
                .otherwise()
                    .setBody(simple("${body[0]}"))
                    .bean(DiagnoseCompositionMapper.class);
        // @formatter:on
    }
}
