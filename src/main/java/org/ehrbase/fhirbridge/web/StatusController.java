package org.ehrbase.fhirbridge.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping(path = "/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("UP");
    }
}
