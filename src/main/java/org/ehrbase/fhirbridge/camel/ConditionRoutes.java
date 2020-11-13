package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.fhirbridge.camel.FhirBridgeHeaders;
import org.ehrbase.fhirbridge.ehr.mapper.DiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.template.diagnosecomposition.DiagnoseComposition;
import org.hl7.fhir.r4.model.Condition;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConditionRoutes extends RouteBuilder {

    private final IFhirResourceDao<Condition> conditionDao;

    private final OpenEhrClient openEhrClient;

    public ConditionRoutes(IFhirResourceDao<Condition> conditionDao, OpenEhrClient openEhrClient) {
        this.conditionDao = conditionDao;
        this.openEhrClient = openEhrClient;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("cond-create:/service?audit=false&fhirContext=#fhirContext")
            .routeId("create-condition")
            .bean(conditionDao, "create(${body})")
            .setBody(simple("${body.resource}"))
            .process(exchange -> {
                UUID ehrId = exchange.getIn().getHeader(FhirBridgeHeaders.EHR_ID, UUID.class);
                Condition condition = exchange.getIn().getBody(Condition.class);

                DiagnoseCompositionConverter converter = new DiagnoseCompositionConverter();
                DiagnoseComposition composition = converter.convertTo(condition);
                openEhrClient.compositionEndpoint(ehrId).mergeCompositionEntity(composition);
            });

        from("cond-read:/service?audit=false&fhirContext=#fhirContext")
            .routeId("read-condition")
            .to("log:read-condition?showAll=true");

        from("cond-search:/service?audit=false&fhirContext=#fhirContext")
            .routeId("search-condition")
            .to("log:search-condition?showAll=true");
        // @formatter:on
    }
}
