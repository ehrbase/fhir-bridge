package org.ehrbase.fhirbridge.rest;

import org.apache.camel.FluentProducerTemplate;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.ProzedurComposition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/procedures")
public class ProcedureResource {

    private final FluentProducerTemplate fluentProducerTemplate;

    public ProcedureResource(FluentProducerTemplate fluentProducerTemplate) {
        this.fluentProducerTemplate = fluentProducerTemplate;
    }

    @GetMapping
    public ResponseEntity<ProzedurComposition[]> list(@RequestParam String subject) {
        ProzedurComposition[] procedures = fluentProducerTemplate.to("direct:search-procedure")
                .withBody(new ParameterValue<>("subjectId", subject))
                .request(ProzedurComposition[].class);
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(procedures.length))
                .body(procedures);
    }
}
