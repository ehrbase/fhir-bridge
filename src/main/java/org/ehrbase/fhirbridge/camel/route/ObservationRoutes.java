package org.ehrbase.fhirbridge.camel.route;

import ca.uhn.fhir.jpa.api.dao.IFhirResourceDao;
import org.apache.camel.builder.RouteBuilder;
import org.ehrbase.fhirbridge.FhirBridgeException;
import org.ehrbase.fhirbridge.camel.DefaultCreateResourceRequestValidator;
import org.ehrbase.fhirbridge.camel.FhirBridgeConstants;
import org.ehrbase.fhirbridge.camel.PatientIdProcessor;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConstants;
import org.ehrbase.fhirbridge.ehr.converter.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyHeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyTemperatureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.BodyWeightCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CoronavirusNachweisTestCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.FiO2CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.HeartRateCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.PatientInIcuCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.PregnancyStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.SmokingStatusCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.SofaScoreCompositionConverter;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.stereotype.Component;

@Component
public class ObservationRoutes extends RouteBuilder {

    private final IFhirResourceDao<Observation> observationDao;

    private final DefaultCreateResourceRequestValidator requestValidator;

    private final PatientIdProcessor patientIdProcessor;

    public ObservationRoutes(IFhirResourceDao<Observation> observationDao, DefaultCreateResourceRequestValidator requestValidator, PatientIdProcessor patientIdProcessor) {
        this.observationDao = observationDao;
        this.requestValidator = requestValidator;
        this.patientIdProcessor = patientIdProcessor;
    }

    @Override
    public void configure() {
        // @formatter:off
        from("fhir-create-observation:fhirConsumer?fhirContext=#fhirContext")
            .process(requestValidator)
            .bean(observationDao, "create(${body})")
            .setHeader(FhirBridgeConstants.METHOD_OUTCOME, body())
            .setBody(simple("${body.resource}"))
            .process(patientIdProcessor)
            .choice()
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.BLOOD_PRESSURE))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new BloodPressureCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.BODY_HEIGHT))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new BodyHeightCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.BODY_TEMP))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new BodyTemperatureCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.BODY_WEIGHT))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new BodyWeightCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.CORONARIRUS_NACHWEIS_TEST))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new CoronavirusNachweisTestCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.FIO2))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new FiO2CompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.HEART_RATE))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new HeartRateCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.OBSERVATION_LAB))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new ObservationLabCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.PATIENT_IN_ICU))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new PatientInIcuCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.PREGNANCY_STATUS))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new PregnancyStatusCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.SMOKING_STATUS))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new SmokingStatusCompositionConverter()))
                .when(header(FhirBridgeConstants.PROFILE).isEqualTo(Profile.SOFA_SCORE))
                    .setHeader(CompositionConstants.COMPOSITION_CONVERTER, constant(new SofaScoreCompositionConverter()))
                .otherwise()
                    .process(exchange -> {
                        throw new FhirBridgeException("Unsupported profile / Not yet implemented");
                    })
            .end()
            .to("ehr-composition:compositionProducer?operation=mergeCompositionEntity")
            .setBody(header(FhirBridgeConstants.METHOD_OUTCOME));
        // @formatter:on
    }
}
