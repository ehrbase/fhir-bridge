package org.ehrbase.fhirbridge.openehr.web;

import org.ehrbase.fhirbridge.openehr.EhrStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/ehr-status/_process")
public class EhrStatusController {

    private final EhrStatusService ehrStatusService;

    public EhrStatusController(EhrStatusService ehrStatusService) {
        this.ehrStatusService = ehrStatusService;
    }

    @PostMapping
    public ResponseEntity<Void> process() throws InterruptedException {
        ehrStatusService.updateEhrStatus();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
