package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty.ClinicalFrailtyScaleScoreCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.geccoDiagnose.GECCODiagnoseCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.radiologischerBefund.RadiologischerBefundConverter;
import org.ehrbase.fhirbridge.ehr.converter.bloodgas.BloodGasPanelCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.sofascore.SofaScoreCompositionConverter;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.EnumMap;

@Component
public class CompositionConverterResolver implements InitializingBean {

    private final EnumMap<Profile, CompositionConverter<?, ?>> profiles = new EnumMap<>(Profile.class);

    @SuppressWarnings("java:S1452")
    public CompositionConverter<?, ?> resolve(Profile profile) {
        CompositionConverter<?, ?> compositionConverter = profiles.get(profile);
        if (compositionConverter == null) {
            throw new IllegalArgumentException("No CompositionConverter defines for profile '" + profile + "'");
        }
        return compositionConverter;
    }

    @Override
    public void afterPropertiesSet() {
        //diagnostic reports
        profiles.put(Profile.DIAGNOSTIC_REPORT_LAB, new DiagnosticReportLabCompositionConverter());
        profiles.put(Profile.DIAGNOSTIC_REPORT_RADIOLOGY, new RadiologischerBefundConverter());
        // Conditions
        profiles.put(Profile.DEFAULT_CONDITION, new DiagnoseCompositionConverter());
        profiles.put(Profile.SYMPTOMS_COVID_19, new SymptomCompositionConverter());
        // Observations
        profiles.put(Profile.BODY_HEIGHT, new BodyHeightCompositionConverter());
        profiles.put(Profile.BLOOD_GAS_PANEL, new BloodGasPanelCompositionConverter());
        profiles.put(Profile.BLOOD_PRESSURE, new BloodPressureCompositionConverter());
        profiles.put(Profile.BODY_TEMP, new BodyTemperatureCompositionConverter());
        profiles.put(Profile.BODY_WEIGHT, new BodyWeightCompositionConverter());
        profiles.put(Profile.CLINICAL_FRAILTY_SCALE, new ClinicalFrailtyScaleScoreCompositionConverter());
        profiles.put(Profile.CORONARIRUS_NACHWEIS_TEST, new CoronavirusNachweisTestCompositionConverter());
        profiles.put(Profile.FIO2, new FiO2CompositionConverter());
        profiles.put(Profile.HEART_RATE, new HeartRateCompositionConverter());
        profiles.put(Profile.PATIENT_IN_ICU, new PatientInIcuCompositionConverter());
        profiles.put(Profile.PREGNANCY_STATUS, new PregnancyStatusCompositionConverter());
        profiles.put(Profile.OBSERVATION_LAB, new ObservationLabCompositionConverter());
        profiles.put(Profile.RESPIRATORY_RATE, new RespiratoryRateCompositionConverter());
        profiles.put(Profile.SOFA_SCORE, new SofaScoreCompositionConverter());
        profiles.put(Profile.SMOKING_STATUS, new SmokingStatusCompositionConverter());
        profiles.put(Profile.PROCEDURE, new ProcedureCompositionConverter());
        profiles.put(Profile.TRAVEL_HISTORY, new HistoryOfTravelCompositionConverter());

        profiles.put(Profile.DIAGNOSE_LIVER_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_LUNG_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_DIABETES_MELLITUS, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_COVID_19, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_MALIGNANT_NEOPLASTIC_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_RHEUMATOLOGICAL_IMMUNOLOGICAL_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_CARDIOVASCULAR_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_CHRONIC_KIDNEY_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_CHRONIC_NEUROLOGICAL_MENTAL_DISEASE, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_GASTROINTESTINAL_ULCERS, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_HIV, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_ORGAN_RECIPIENT, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_COMPLICATIONS_COVID_19, new GECCODiagnoseCompositionConverter());
        profiles.put(Profile.DIAGNOSE_DEPENDENCE_ON_VENTILATOR, new GECCODiagnoseCompositionConverter());
    }
}
