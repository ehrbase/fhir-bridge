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

package org.ehrbase.fhirbridge.config;

import ca.uhn.fhir.context.FhirContext;
import org.apache.http.client.HttpClient;
import org.ehrbase.fhirbridge.ehr.converter.ConversionService;
import org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel.GECCOSerologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodytemperature.KoerpertemperaturCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight.BodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty.ClinicalFrailtyScaleScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation.ClinicalTrialParticipationCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.coronavirusnachweistest.CoronavirusNachweisTestCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsdiagnose.KDSDiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.dnranordnung.DnrAnordnungCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.fio2.FiO2CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccodiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccovirologischerbefund.PCRCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.heartrate.HerzfrequenzCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.hipdocument.DocumentReferenceToHipDocumentConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.impfstatus.ImpfstatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsfall.SonstigerPatientenAufenthaltConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdslaborbefund.KDSLaborbefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.KDSPatientCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.knownexposure.SarsCov2KnownExposureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GECCOMedikationCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab.KDSObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur.MibiKulturNachweisConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik.MibiMolekDiagnostikConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.PatientCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge.PatientDischargeCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt.PatientenAufenthaltCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.patientinicu.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus.PregnancyStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.procedure.ProcedureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry.PulseOximetryCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.radiologischerbefund.RadiologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.respirationrate.RespiratoryRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.sexatbirth.SexAtBirthConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus.RaucherstatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.sofascore.SofaScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall.StationaererVersorgungsfallCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.symptom.SymptomCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.therapy.TherapyCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten.UCCAppProDatenConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.UCCSensordatenCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund.VirologischerBefundCompositionConverter;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.ehrbase.fhirbridge.service.TerminologyService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(FhirProperties.class)
@SuppressWarnings("java:S6212")
public class ConversionConfiguration {


    @Bean(name = "fhirResourceConversionService")
    public ConversionService conversionService() {
        ConversionService conversionService = new ConversionService();
        registerCompositionConverter(conversionService);
        registerDocumentReference(conversionService);
        registerConditionConverters(conversionService);
        registerConsentConverters(conversionService);
        registerDiagnosticReportConverters(conversionService);
        registerEncounterConverters(conversionService);
        registerImmunizationConverters(conversionService);
        registerMedicationStatementConverters(conversionService);
        registerObservationConverters(conversionService);
        registerPatientConverters(conversionService);
        registerProcedureConverters(conversionService);
        registerQuestionnaireResponseConverters(conversionService);
        return conversionService;
    }

    @Bean
    @ConditionalOnProperty(prefix = "fhir-bridge.fhir.terminology-server", name = "url")
    public TerminologyService myTerminologyService(HttpClient httpClient, FhirProperties properties) {
        FhirContext context = FhirContext.forR4();
        context.getRestfulClientFactory().setHttpClient(httpClient);
        return new TerminologyService(context.newRestfulGenericClient(properties.getTerminologyServer().getUrl()));
    }

    private void registerDocumentReference(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DOCUMENT_REFERENCE_DEFAULT, new DocumentReferenceToHipDocumentConverter());
    }

    private void registerCompositionConverter(ConversionService conversionService) {
        conversionService.registerConverter(Profile.UCC_SENSORDATEN_VITALSIGNS, new UCCSensordatenCompositionConverter());
        conversionService.registerConverter(Profile.UCC_SENSORDATEN_STEPS, new UCCSensordatenCompositionConverter());
        conversionService.registerConverter(Profile.UCC_APP_PRO_DATEN, new UCCAppProDatenConverter());
    }


    private void registerConditionConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.CONDITION_DEFAULT, new KDSDiagnoseCompositionConverter());
        conversionService.registerConverter(Profile.KDS_DIAGNOSE, new KDSDiagnoseCompositionConverter());

        conversionService.registerConverter(Profile.SYMPTOMS_COVID_19, new SymptomCompositionConverter());

        GECCODiagnoseCompositionConverter converter = new GECCODiagnoseCompositionConverter();
        conversionService.registerConverter(Profile.DIAGNOSE_LIVER_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_LUNG_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_DIABETES_MELLITUS, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_COVID_19, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_MALIGNANT_NEOPLASTIC_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_RHEUMATOLOGICAL_IMMUNOLOGICAL_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CARDIOVASCULAR_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CHRONIC_KIDNEY_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CHRONIC_NEUROLOGICAL_MENTAL_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_GASTROINTESTINAL_ULCERS, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_HIV, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_ORGAN_RECIPIENT, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_COMPLICATIONS_COVID_19, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_DEPENDENCE_ON_VENTILATOR, converter);
    }

    private void registerConsentConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DO_NOT_RESUSCITATE_ORDER, new DnrAnordnungCompositionConverter());
    }

    private void registerDiagnosticReportConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DIAGNOSTIC_REPORT_LAB, new KDSLaborbefundCompositionConverter());
        conversionService.registerConverter(Profile.DIAGNOSTIC_REPORT_RADIOLOGY, new RadiologischerBefundCompositionConverter());
    }

    private void registerMedicationStatementConverters(ConversionService conversionService) {
        GECCOMedikationCompositionConverter converter = new GECCOMedikationCompositionConverter();
        conversionService.registerConverter(Profile.PHARMACOLOGICAL_THERAPY, converter);
        conversionService.registerConverter(Profile.PHARMACOLOGICAL_THERAPY_ACE_INHIBITORS, converter);
        conversionService.registerConverter(Profile.PHARMACOLOGICAL_THERAPY_ANTICOAGULANTS, converter);
        conversionService.registerConverter(Profile.PHARMACOLOGICAL_THERAPY_IMMUNOGLOBULINS, converter);
    }

    private void registerObservationConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.BODY_HEIGHT, new BodyHeightCompositionConverter());
        conversionService.registerConverter(Profile.BLOOD_GAS_PANEL, new BloodGasPanelCompositionConverter());
        conversionService.registerConverter(Profile.ANTI_BODY_PANEL, new GECCOSerologischerBefundCompositionConverter());
        conversionService.registerConverter(Profile.BLOOD_PRESSURE, new BloodPressureCompositionConverter());
        conversionService.registerConverter(Profile.BODY_TEMP, new KoerpertemperaturCompositionConverter());
        conversionService.registerConverter(Profile.BODY_WEIGHT, new BodyWeightCompositionConverter());
        conversionService.registerConverter(Profile.CLINICAL_FRAILTY_SCALE, new ClinicalFrailtyScaleScoreCompositionConverter());
        conversionService.registerConverter(Profile.CLINICAL_TRIAL_PARTICIPATION, new ClinicalTrialParticipationCompositionConverter());
        conversionService.registerConverter(Profile.CLINICAL_TRIAL_DUE_TO_COVID, new ClinicalTrialParticipationCompositionConverter());
        conversionService.registerConverter(Profile.CORONAVIRUS_NACHWEIS_TEST, new CoronavirusNachweisTestCompositionConverter());
        conversionService.registerConverter(Profile.FIO2, new FiO2CompositionConverter());
        conversionService.registerConverter(Profile.HEART_RATE, new HerzfrequenzCompositionConverter());
        conversionService.registerConverter(Profile.KNOWN_EXPOSURE, new SarsCov2KnownExposureCompositionConverter());
        conversionService.registerConverter(Profile.PATIENT_DISCHARGE, new PatientDischargeCompositionConverter());
        conversionService.registerConverter(Profile.PATIENT_IN_ICU, new PatientInIcuCompositionConverter());
        conversionService.registerConverter(Profile.PCR, new PCRCompositionConverter());
        conversionService.registerConverter(Profile.PREGNANCY_STATUS, new PregnancyStatusCompositionConverter());
        conversionService.registerConverter(Profile.OBSERVATION_LAB, new KDSObservationLabCompositionConverter());
        conversionService.registerConverter(Profile.RESPIRATORY_RATE, new RespiratoryRateCompositionConverter());
        conversionService.registerConverter(Profile.SOFA_SCORE, new SofaScoreCompositionConverter());
        conversionService.registerConverter(Profile.SMOKING_STATUS, new RaucherstatusCompositionConverter());
        conversionService.registerConverter(Profile.TRAVEL_HISTORY, new HistoryOfTravelCompositionConverter());
        conversionService.registerConverter(Profile.OXYGEN_SATURATION, new PulseOximetryCompositionConverter());
        conversionService.registerConverter(Profile.OXYGEN_SATURATION, new PulseOximetryCompositionConverter());
        conversionService.registerConverter(Profile.VIROLOGISCHER_BEFUND, new VirologischerBefundCompositionConverter());
        conversionService.registerConverter(Profile.SEX_AT_BIRTH, new SexAtBirthConverter());
        conversionService.registerConverter(Profile.MIBI_KULTUR, new MibiKulturNachweisConverter());
        conversionService.registerConverter(Profile.MIBI_MOLEKULARE_DIAGNOSTIC, new MibiMolekDiagnostikConverter());
    }

    private void registerPatientConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.PATIENT, new PatientCompositionConverter());
        conversionService.registerConverter(Profile.KDS_PATIENT, new PatientCompositionConverter());
        conversionService.registerConverter(Profile.KDS_PATIENT_PSEUDO, new KDSPatientCompositionConverter());
    }

    private void registerProcedureConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.PROCEDURE, new ProcedureCompositionConverter());

        TherapyCompositionConverter converter = new TherapyCompositionConverter();
        conversionService.registerConverter(Profile.APHERESIS_PROCEDURE, converter);
        conversionService.registerConverter(Profile.DIALYSIS_PROCEDURE, converter);
        conversionService.registerConverter(Profile.EXTRACORPOREAL_MEMBRANE_OXYGENATION_PROCEDURE, converter);
        conversionService.registerConverter(Profile.PRONE_POSITION_PROCEDURE, converter);
        conversionService.registerConverter(Profile.RADIOLOGY_PROCEDURE, converter);
        conversionService.registerConverter(Profile.RESPIRATORY_THERAPIES_PROCEDURE, converter);
    }

    private void registerQuestionnaireResponseConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.QUESTIONNAIRE_RESPONSE_DEFAULT, new D4lQuestionnaireCompositionConverter());
    }

    private void registerEncounterConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.ENCOUNTER_DEFAULT, new StationaererVersorgungsfallCompositionConverter());
        conversionService.registerConverter(Profile.KONTAKT_GESUNDHEIT_ABTEILUNG, new SonstigerPatientenAufenthaltConverter());
    }

    private void registerImmunizationConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.HISTORY_OF_VACCINATION, new ImpfstatusCompositionConverter());
    }
}
