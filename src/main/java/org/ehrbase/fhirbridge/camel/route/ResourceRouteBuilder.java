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
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.fhirbridge.camel.CamelConstants;
import org.ehrbase.fhirbridge.camel.processor.FhirProfileValidator;
import org.ehrbase.fhirbridge.camel.processor.ProvideResourceAuditHandler;
import org.ehrbase.fhirbridge.camel.processor.PatientReferenceProcessor;
import org.ehrbase.fhirbridge.camel.processor.ResourcePersistenceProcessor;
import org.ehrbase.fhirbridge.camel.processor.OpenEhrClientExceptionHandler;
import org.ehrbase.fhirbridge.fhir.encounter.validator.KDSEncounterValidator;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.fhir.observation.validator.MibiKulturValidator;
import org.ehrbase.fhirbridge.fhir.observation.validator.MibiMolekDiagnostikValidator;
import org.ehrbase.fhirbridge.fhir.patient.validator.KdsPersonValidator;
import org.ehrbase.fhirbridge.fhir.support.Resources;
import org.springframework.stereotype.Component;


/**
 * {@link RouteBuilder} implementation that configures the routes for FHIR resources.
 *
 * @since 1.2.0
 */
@Component
@SuppressWarnings("java:S1192")
public class ResourceRouteBuilder extends AbstractRouteBuilder {

    @Override
    public void configure() throws Exception {
        super.configure();

        // @formatter:off
        from("direct:provideResource")
            .setHeader(CamelConstants.PROFILE, method(Resources.class, "getResourceProfile"))
            .routeId("provideResourceRoute")
            .onCompletion()
                .process(ProvideResourceAuditHandler.BEAN_ID)
            .end()
            .process(FhirProfileValidator.BEAN_ID)
            .choice()
                .when(header(CamelConstants.PROFILE).isEqualTo(Profile.KONTAKT_GESUNDHEIT_ABTEILUNG))
                    .bean(KDSEncounterValidator.class)
                .when(header(CamelConstants.PROFILE).isEqualTo(Profile.MIBI_KULTUR))
                    .bean(MibiKulturValidator.class)
                .when(header(CamelConstants.PROFILE).isEqualTo(Profile.MIBI_MOLEKULARE_DIAGNOSTIC))
                    .bean(MibiMolekDiagnostikValidator.class)
                .when(header(CamelConstants.PROFILE).isEqualTo(Profile.KDS_PATIENT_PSEUDO))
                    .bean(KdsPersonValidator.class)
            .end()
            .process(PatientReferenceProcessor.BEAN_ID)
            .process(ResourcePersistenceProcessor.BEAN_ID)
            .doTry()
                .to("direct:send-to-cdr")
            .doCatch(ClientException.class)
                .process(new OpenEhrClientExceptionHandler());


        // @formatter:on
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
        configureComposition();
        configureSpecimen();
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.AuditEvent AuditEvent} resource.
     */
    private void configureAuditEvent() {
        from("audit-event-find:auditEventEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Condition Condition} resource.
     */
    private void configureCondition() {
        from("condition-provide:conditionEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("condition-find:conditionEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Consent Consent} resource.
     */
    private void configureConsent() {
        from("consent-provide:consentEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("consent-find:consentEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.DiagnosticReport DiagnosticReport} resource.
     */
    private void configureDiagnosticReport() {
        from("diagnostic-report-provide:diagnosticReportEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("diagnostic-report-find:diagnosticReportEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Encounter Encounter} resource.
     */
    private void configureEncounter() {
        from("encounter-provide:encounterEndpoint?fhirContext=#fhirContext")

                .to("direct:provideResource");

        from("encounter-find:encounterEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Immunization Immunization} resource.
     */
    private void configureImmunization() {
        from("immunization-provide:immunizationEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("immunization-find:immunizationEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.MedicationStatement MedicationStatement} resource.
     */
    private void configureMedicationStatement() {
        from("medication-statement-provide:medicationStatementEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("medication-statement-find:medicationStatementEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Observation Observation} resource.
     */
    private void configureObservation() {
        from("observation-provide:observationEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("observation-find:observationEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Patient Patient} resource.
     */
    private void configurePatient() {
        from("patient-provide:patientEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("patient-find:patientEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Procedure Procedure} resource.
     */
    private void configureProcedure() {
        from("procedure-provide:procedureEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("procedure-find:procedureEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.QuestionnaireResponse QuestionnaireResponse}
     * resource.
     */
    private void configureQuestionnaireResponse() {
        from("questionnaire-response-provide:questionnaireResponseEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("questionnaire-response-find:questionnaireResponseEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }

    /**
     * Configures available endpoints for {@link org.hl7.fhir.r4.model.Composition} resource.
     */
    private void configureComposition() {
        from("composition-provide:compositionEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");
        from("composition-find:compositionEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }


    private void configureSpecimen() {
        from("specimen-provide:specimenEndpoint?fhirContext=#fhirContext")
                .to("direct:provideResource");

        from("specimen-find:specimenEndpoint?fhirContext=#fhirContext&lazyLoadBundles=true")
                .process(ResourcePersistenceProcessor.BEAN_ID);
    }


}
