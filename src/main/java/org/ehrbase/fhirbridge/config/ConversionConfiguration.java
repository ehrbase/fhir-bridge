package org.ehrbase.fhirbridge.config;

import org.ehrbase.fhirbridge.ehr.converter.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyTemperatureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionService;
import org.ehrbase.fhirbridge.ehr.converter.CoronavirusNachweisTestCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.DiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.DiagnosticReportLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.FiO2CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.HeartRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.PatientCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.PregnancyStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ProcedureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.RespiratoryRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty.ClinicalFrailtyScaleScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.d4lquestionnaire.D4lQuestionnaireCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.geccoDiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.historyoftravel.HistoryOfTravelConverter;
import org.ehrbase.fhirbridge.ehr.converter.observationlab.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.patientinicu.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.pulseoximetry.PulseOximetryConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.converter.smokingstatus.SmokingStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.sofascore.SofaScoreCompositionConverter;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfiguration {

    @Bean(name = "fhirResourceConversionService")
    public ConversionService conversionService() {
        ConversionService conversionService = new ConversionService();
        // Register Converters
        registerDiagnosticReportConverters(conversionService);
        registerConditionConverters(conversionService);
        registerObservationConverters(conversionService);
        registerPatientConverters(conversionService);
        registerQuestionnaireResponseConverter(conversionService);
        return conversionService;
    }

    private void registerDiagnosticReportConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DIAGNOSTIC_REPORT_LAB, new DiagnosticReportLabCompositionConverter());
        conversionService.registerConverter(Profile.DIAGNOSTIC_REPORT_RADIOLOGY, new RadiologischerBefundConverter());
    }

    private void registerObservationConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.BODY_HEIGHT, new BodyHeightCompositionConverter());
        conversionService.registerConverter(Profile.BLOOD_GAS_PANEL, new BloodGasPanelCompositionConverter());
        conversionService.registerConverter(Profile.BLOOD_PRESSURE, new BloodPressureCompositionConverter());
        conversionService.registerConverter(Profile.BODY_TEMP, new BodyTemperatureCompositionConverter());
        conversionService.registerConverter(Profile.BODY_WEIGHT, new BodyWeightCompositionConverter());
        conversionService.registerConverter(Profile.CLINICAL_FRAILTY_SCALE, new ClinicalFrailtyScaleScoreCompositionConverter());
        conversionService.registerConverter(Profile.CORONARIRUS_NACHWEIS_TEST, new CoronavirusNachweisTestCompositionConverter());
        conversionService.registerConverter(Profile.FIO2, new FiO2CompositionConverter());
        conversionService.registerConverter(Profile.HEART_RATE, new HeartRateCompositionConverter());
        conversionService.registerConverter(Profile.PATIENT_IN_ICU, new PatientInIcuCompositionConverter());
        conversionService.registerConverter(Profile.PREGNANCY_STATUS, new PregnancyStatusCompositionConverter());
        conversionService.registerConverter(Profile.OBSERVATION_LAB, new ObservationLabCompositionConverter());
        conversionService.registerConverter(Profile.RESPIRATORY_RATE, new RespiratoryRateCompositionConverter());
        conversionService.registerConverter(Profile.SOFA_SCORE, new SofaScoreCompositionConverter());
        conversionService.registerConverter(Profile.SMOKING_STATUS, new SmokingStatusCompositionConverter());
        conversionService.registerConverter(Profile.PROCEDURE, new ProcedureCompositionConverter());
        conversionService.registerConverter(Profile.TRAVEL_HISTORY, new HistoryOfTravelConverter());
        conversionService.registerConverter(Profile.OXYGEN_SATURATION, new PulseOximetryConverter());
    }

    private void registerConditionConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DEFAULT_CONDITION, new DiagnoseCompositionConverter());

        GECCODiagnoseCompositionConverter converter = new GECCODiagnoseCompositionConverter();
        conversionService.registerConverter(Profile.DIAGNOSE_LIVER_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_LUNG_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_DIABETES_MELLITUS, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_COVID_19, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_MALIGNANT_NEOPLASTIC_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_RHEUMATOLOGICAL_IMMUNOLOGICAL_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CARDIOVASCULAR_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CHRONIC_KIDNEY_DISEASE, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_CHRONIC_NEUROLOGICAL_MENTAL_DISEASE,converter);
        conversionService.registerConverter(Profile.DIAGNOSE_GASTROINTESTINAL_ULCERS, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_HIV, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_ORGAN_RECIPIENT, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_COMPLICATIONS_COVID_19, converter);
        conversionService.registerConverter(Profile.DIAGNOSE_DEPENDENCE_ON_VENTILATOR, converter);
    }

    private void registerPatientConverters(ConversionService conversionService) {
        conversionService.registerConverter(Profile.PATIENT, new PatientCompositionConverter());
    }

    private void registerQuestionnaireResponseConverter(ConversionService conversionService) {
        conversionService.registerConverter(Profile.DEFAULT_QUESTIONNAIRE_RESPONSE, new D4lQuestionnaireCompositionConverter());
    }
}
