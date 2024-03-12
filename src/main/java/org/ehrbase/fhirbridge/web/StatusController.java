package org.ehrbase.fhirbridge.web;

import org.ehrbase.fhirbridge.core.repository.PatientEhrRepository;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class StatusController implements HealthIndicator {
    private final HealthEndpoint healthEndpoint;
    private final PatientEhrRepository patientEhrRepository;

    public StatusController(HealthEndpoint healthEndpoint, PatientEhrRepository patientEhrRepository) {
        this.healthEndpoint = healthEndpoint;
        this.patientEhrRepository = patientEhrRepository;
    }

    @GetMapping(path = "/status")
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("UP");
    }

    @GetMapping(path = "/health")
    @Override
    public Health health() {
        try {
            if (patientEhrRepository.count() >= 0) {
                return Health
                        .status(healthEndpoint.health().getStatus().getCode())
                        .build();
            } else {
                return Health.down().build();
            }
        }catch (Exception ex){
            return Health.down(ex).build();
        }
    }

}
