package org.ehrbase.fhirbridge.listeners;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import org.ehrbase.fhirbridge.exception.ExceptionsTemplate;

@Configuration
public class ExceptionMapInit implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        new ExceptionsTemplate().populateExceptionMap();
    }
}
