/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.fhirbridge.camel.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("java:S1192")
public class ResourceRouteBuilder extends RouteBuilder {

    @Value("${fhir-bridge.debug.enabled:false}")
    private boolean debug;

    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler()
                .logStackTrace(debug)
                .logExhaustedMessageHistory(debug));

        onException(Exception.class)
                .process("defaultExceptionHandler");

        configureAuditEvent();
        configureCondition();
        configureConsent();
        configureDiagnosticReport();
        configureEncounter();
        configureImmunization();
        configureMedicationStatement();
        configureObservation();
        configurePatient();
        configureProcedure();
        configureQuestionnaireResponse();
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.AuditEvent AuditEvent} resource.
     */
    private void configureAuditEvent() {
        from("audit-event-find:auditEventEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Condition Condition} resource.
     */
    private void configureCondition() {
        from("condition-provide:conditionEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("condition-find:conditionEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Consent Consent} resource.
     */
    private void configureConsent() {
        from("consent-provide:consentEndpoint?fhirContext=#fhirContext")
                .throwException(UnsupportedOperationException.class, "Not yet implemented");

        from("consent-find:consentEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.DiagnosticReport DiagnosticReport} resource.
     */
    private void configureDiagnosticReport() {
        from("diagnostic-report-provide:diagnosticReportEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("diagnostic-report-find:diagnosticReportEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Encounter Encounter} resource.
     */
    private void configureEncounter() {
        from("encounter-provide:encounterEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("encounter-find:encounterEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Immunization Immunization} resource.
     */
    private void configureImmunization() {
        from("immunization-provide:immunizationEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("immunization-find:immunizationEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.MedicationStatement MedicationStatement} resource.
     */
    private void configureMedicationStatement() {
        from("medication-statement-provide:medicationStatementEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("medication-statement-find:medicationStatementEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Observation Observation} resource.
     */
    private void configureObservation() {
        from("observation-provide:observationEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("observation-find:observationEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Patient Patient} resource.
     */
    private void configurePatient() {
        from("patient-provide:patientEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("patient-find:patientEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Procedure Procedure} resource.
     */
    private void configureProcedure() {
        from("procedure-provide:procedureEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("procedure-find:procedureEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.QuestionnaireResponse QuestionnaireResponse}
     * resource.
     */
    private void configureQuestionnaireResponse() {
        from("questionnaire-response-provide:questionnaireResponseEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("questionnaire-response-find:questionnaireResponseEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process("resourcePersistenceProcessor");
    }
}
