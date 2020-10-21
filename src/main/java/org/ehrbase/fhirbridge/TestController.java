package org.ehrbase.fhirbridge;

import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ProducerTemplate producerTemplate;

    public TestController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping(path = "/test")
    public ResponseEntity<Void> test() {
        producerTemplate.sendBody("direct://test", null);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
