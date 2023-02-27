package org.ehrbase.fhirbridge;

import ch.qos.logback.classic.Level;
import org.ehrbase.fhirbridge.web.ManagementController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ManagementControllerTest {
    @Test
    public void testLogLevelSwitching() {
        ManagementController managementController = new ManagementController();

        ResponseEntity<Level> levelResponseEntity = new ResponseEntity<>(Level.TRACE, HttpStatus.OK);
        managementController.setLogLevel("TRACE");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        levelResponseEntity = new ResponseEntity<>(Level.DEBUG, HttpStatus.OK);
        managementController.setLogLevel("DEBUG");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        levelResponseEntity = new ResponseEntity<>(Level.INFO, HttpStatus.OK);
        managementController.setLogLevel("INFO");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        levelResponseEntity = new ResponseEntity<>(Level.WARN, HttpStatus.OK);
        managementController.setLogLevel("WARN");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        levelResponseEntity = new ResponseEntity<>(Level.ERROR, HttpStatus.OK);
        managementController.setLogLevel("ERROR");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        levelResponseEntity = new ResponseEntity<>(Level.OFF, HttpStatus.OK);
        managementController.setLogLevel("OFF");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        //Testing wrong value. Default value is DEBUG
        levelResponseEntity = new ResponseEntity<>(Level.DEBUG, HttpStatus.OK);
        managementController.setLogLevel("INFO2");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        managementController.setLogLevel(null);
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);

        managementController.setLogLevel("tralala");
        Assertions.assertEquals(managementController.getLogLevel(), levelResponseEntity);
    }
}
