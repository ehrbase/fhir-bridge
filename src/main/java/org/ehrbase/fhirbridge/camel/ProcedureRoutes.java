package org.ehrbase.fhirbridge.camel;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import ca.uhn.fhir.rest.api.MethodOutcome;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.fhirbridge.camel.component.ehr.aql.AqlConstants;
import org.ehrbase.fhirbridge.camel.processor.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.processor.PatientIdProcessor;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Procedure;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

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
        from("proc-create:fhirConsumer?fhirContext=#fhirContext")
            .process(requestValidator)
            .bean(procedureDao, "create(${body})")
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity&converter=#prozedurCompositionConverter")
            .setBody(exchange -> new MethodOutcome().setResource(exchange.getIn().getBody(IBaseResource.class)));

        from("fhir-find-procedure:/service?fhirContext=#fhirContext&audit=false")
            .setHeader(AqlConstants.AQL_QUERY, aqlQuery())
            .to("ehr-aql:aqlProducer?rowMapper=#fhirProcedureRowMapper");

        from("direct:search-procedure")
            .setHeader(AqlConstants.AQL_QUERY, aqlQuery())
            .to("ehr-aql:aqlProducer?rowMapper=#procedureRowMapper");
        // @formatter:on
    }

    private Supplier<Object> aqlQuery() {
        // @formatter:off
        return () -> Query.buildNativeQuery(
            "SELECT c "+
            "FROM EHR e CONTAINS COMPOSITION c " +
            "WHERE c/archetype_details/template_id/value = 'Prozedur' " +
                "AND e/ehr_status/subject/external_ref/id/value = $subjectId", ProzedurComposition.class);
        // @formatter:on
    }

    @Bean
    public FhirProcedureRowMapper fhirProcedureRowMapper() {
        return new FhirProcedureRowMapper();
    }

    @Bean
    public ProcedureRowMapper procedureRowMapper() {
        return new ProcedureRowMapper();
    }
}
