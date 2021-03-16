package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.BundeslandRegionDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.LandDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ProblemDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieBestimmtesReisezielCluster;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ehrbase.fhirbridge.ehr.converter.specific.convertercodes.CodeSystem.LOINC;
import static org.ehrbase.fhirbridge.ehr.converter.specific.convertercodes.CodeSystem.SNOMED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL;

public class HistoryOfTravelConverter extends CompositionConverter<Observation, ReisehistorieComposition> {
    private static final Logger LOG = LoggerFactory.getLogger(HistoryOfTravelConverter.class);

    private static final Map<String, LandDefiningCode> countryMap = new HashMap<>();
    private static final Map<String, BundeslandRegionDefiningCode> regionMap = new HashMap<>();

    static {

        for (LandDefiningCode item : LandDefiningCode.values()) {
            countryMap.put(item.getCode(), item);
        }

        for (BundeslandRegionDefiningCode item : BundeslandRegionDefiningCode.values()) {
            regionMap.put(item.getCode(), item);
        }
    }

    @Override
    public ReisehistorieComposition convertInternal(@NonNull Observation resource) {

        ReisehistorieComposition composition;

        String code = getSnomedCodeObservation(resource);
        // check for general travel state

        if (code.equals(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE.getCode())) {
            composition = mapYes(resource, ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE);
        } else if (code.equals(AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE.getCode())) {
            composition = mapNo(resource, AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE);
        } else if (code.equals(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE.getCode())) {
            composition = mapUnknown(resource, AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE);
        } else {
            throw new UnprocessableEntityException("Expected snomed-code for history of travel, but got '" + code + "' instead ");
        }

        // BSa: What about nullFlavour?
        // BSa: What about identifier?
        return (composition);
    }


    private ReisehistorieComposition createCompositionAndSetDefaults(Observation observation) {
        ReisehistorieComposition composition = new ReisehistorieComposition();

        // ======================================================================================
        // Required fields by API

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        return composition;
    }

    private ReisehistorieComposition mapYes(Observation observation, ReiseAngetretenDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        ReisehistorieAdminEntry adminentry = new ReisehistorieAdminEntry();

        adminentry.setReiseAngetretenDefiningCode(reiseCode);
        adminentry = mapInternalEvents(adminentry, observation);

        adminentry.setLanguage(Language.DE);
        adminentry.setSubject(new PartySelf());

        composition.setReisehistorie(adminentry);

        return composition;
    }

    private TravelInformation getTravelInformationType(Coding coding) {

        String code = coding.getCode();
        if (code.equals(LOINC_DATE_TRAVEL_STARTED.getCode())) {
            return TravelInformation.START;
        }
        if (code.equals(LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION.getCode())) {
            return TravelInformation.END;
        }
        if (code.equals(LOINC_CITY_OF_TRAVEL.getCode())) {
            return TravelInformation.CITY;
        }
        if (code.equals(LOINC_STATE_OF_TRAVEL.getCode())) {
            return TravelInformation.REGION;
        }
        if (code.equals(LOINC_COUNTRY_OF_TRAVEL.getCode())) {
            return TravelInformation.COUNTRY;
        }

        throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + coding.getSystem() + ":" + code + "' instead");
    }

    private ReisehistorieAdminEntry mapInternalEvents(ReisehistorieAdminEntry adminentry, Observation observation) {

        ReisehistorieBestimmtesReisezielCluster travel = new ReisehistorieBestimmtesReisezielCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : observation.getComponent()) {

            Coding coding = observationComponent.getCode().getCoding().get(0);
            checkForLoincSystem(coding.getSystem());

            switch (getTravelInformationType(coding)) {
                case START:
                    travel.setEinreisedatumValue(getDate(observationComponent));
                    break;
                case END:
                    travel.setAbfahrtsdatumValue(getDate(observationComponent));
                    break;
                case CITY:
                    travel.setStadtValue(getCity(observationComponent));
                    break;
                case REGION:
                    travel.setBundeslandRegionDefiningCode(getBundeslandRegion(observationComponent));
                    break;
                case COUNTRY:
                    travel.setLandDefiningCode(getLand(observationComponent));
                    break;
                default:
                    throw new UnprocessableEntityException("Can't process travel information. Did you forget to implement a case?");
            }

        }
        adminentry.setBestimmtesReiseziel(List.of(travel));

        return adminentry;
    }

    private TemporalAccessor getDate(Observation.ObservationComponentComponent observationComponent) {
        return observationComponent.getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
    }

    private String getCity(Observation.ObservationComponentComponent observationComponent) {
        return observationComponent.getValueStringType().getValue();
    }

    private LandDefiningCode getLand(Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getValueCodeableConcept().getCoding().get(0);
        return countryMap.get(coding.getCode());
    }

    private BundeslandRegionDefiningCode getBundeslandRegion(Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getValueCodeableConcept().getCoding().get(0);
        return regionMap.get(coding.getCode());
    }

    private void checkForLoincSystem(String systemCode) {
        if (!LOINC.getUrl().equals(systemCode)) {
            throw new UnprocessableEntityException("The system is not correct. " +
                    "It should be '" + LOINC.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new UnprocessableEntityException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {

        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());

        return code.getCode();
    }

    private Observation.ObservationComponentComponent getCodingObject(Observation observation, int i) {
        return observation.getComponent().get(i);
    }

    private ReisehistorieComposition mapNo(Observation observation, AussageUeberDenAusschlussDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        KeineReisehistorieEvaluation evaluation = new KeineReisehistorieEvaluation();


        evaluation.setProblemDiagnoseDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        evaluation.setAussageUeberDenAusschlussDefiningCode(reiseCode);
        evaluation.setLanguage(Language.DE);
        evaluation.setSubject(new PartySelf());

        composition.setKeineReisehistorie(evaluation);

        //optional
        observation.getDataAbsentReason();


        return composition;
    }


    //###################################################################################

    private ReisehistorieComposition mapUnknown(Observation observation, AussageUeberDieFehlendeInformationDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        UnbekannteReisehistorieEvaluation evaluation = new UnbekannteReisehistorieEvaluation();

        evaluation.setFehlendeInformationDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        evaluation.setAussageUeberDieFehlendeInformationDefiningCode(reiseCode);
        evaluation.setLanguage(Language.DE);
        evaluation.setSubject(new PartySelf());

        composition.setUnbekannteReisehistorie(evaluation);

        return composition;
    }
    //###################################################################################

    private enum TravelInformation {

        START,
        END,
        COUNTRY,
        REGION,
        CITY;
    }
}