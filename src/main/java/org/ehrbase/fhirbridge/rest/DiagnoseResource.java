package org.ehrbase.fhirbridge.rest;

import org.apache.camel.ProducerTemplate;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.DiagnoseComposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DiagnoseResource {

    private final ProducerTemplate producerTemplate;

    public DiagnoseResource(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping(path = "/api/diagnose-composition/{compositionId}")
    public ResponseEntity<DiagnoseComposition> getDiagnoseComposition(@PathVariable UUID compositionId) {
        DiagnoseComposition composition = producerTemplate.requestBody(
                "direct:aql-demo-route",
                new ParameterValue<>("compositionId", compositionId),
                DiagnoseComposition.class);
        if (composition == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(composition);
    }
}
