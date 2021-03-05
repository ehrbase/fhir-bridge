package org.ehrbase.fhirbridge.ehr.converter.historyOfTravel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.CommonData;
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

import static org.ehrbase.fhirbridge.ehr.converter.convertercodes.CodeSystem.LOINC;
import static org.ehrbase.fhirbridge.ehr.converter.convertercodes.CodeSystem.SNOMED;
import static org.ehrbase.fhirbridge.ehr.converter.historyOfTravel.HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.historyOfTravel.HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED;
import static org.ehrbase.fhirbridge.ehr.converter.historyOfTravel.HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.historyOfTravel.HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION;
import static org.ehrbase.fhirbridge.ehr.converter.historyOfTravel.HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryOfTravelConverter implements CompositionConverter<ReisehistorieComposition, Observation> {
    private static final Logger LOG = LoggerFactory.getLogger(HistoryOfTravelConverter.class);

    private static final Map<String, String> loincTravelCodesMap = new HashMap<>();
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
    public Observation fromComposition(ReisehistorieComposition composition) throws CompositionConversionException {
        //your mapping code
        // return the mapped Observation of body temp
        return null;
    }

    @Override
    public ReisehistorieComposition toComposition(Observation observation) throws CompositionConversionException {

        if (null == observation) {
            throw new UnprocessableEntityException("Observation is null. Couldn't proceed.");
        }

        ReisehistorieComposition composition;

        String code = getSnomedCodeObservation(observation);
        // check for general travel state

        if (code.equals(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE.getCode())) {
            composition = map_yes(observation, ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE);
        } else if (code.equals(AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE.getCode())) {
            composition = map_no(observation, AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE);
        } else if (code.equals(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE.getCode())) {
            composition = map_unknown(observation, AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE);
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
        composition.setLanguage(org.ehrbase.client.classgenerator.shareddefinition.Language.EN); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(org.ehrbase.client.classgenerator.shareddefinition.Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        composition.setComposer(new PartySelf());

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        composition.setFeederAudit(fa);

        return composition;
    }

    private ReisehistorieComposition map_yes(Observation observation, ReiseAngetretenDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        ReisehistorieAdminEntry adminentry = new ReisehistorieAdminEntry();

        adminentry.setReiseAngetretenDefiningCode(reiseCode);
        adminentry = mapInternalEvents(adminentry, observation);

        adminentry.setLanguage(Language.DE);
        adminentry.setSubject(new PartySelf());

        composition.setReisehistorie(adminentry);

        return composition;
    }

    private ReisehistorieAdminEntry mapInternalEvents(ReisehistorieAdminEntry adminentry, Observation observation) {

        ReisehistorieBestimmtesReisezielCluster travel = new ReisehistorieBestimmtesReisezielCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : observation.getComponent()) {

            Coding coding = observationComponent.getCode().getCoding().get(0);
            String system = coding.getSystem();
            String code = coding.getCode();

            checkForLoincSystem(system);

            if (code.equals(LOINC_DATE_TRAVEL_STARTED.getCode())) {
                travel.setEinreisedatumValue(getDate(observationComponent));
            } else if (code.equals(LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION.getCode())) {
                travel.setAbfahrtsdatumValue(getDate(observationComponent));
            } else if (code.equals(LOINC_CITY_OF_TRAVEL.getCode())) {
                travel.setStadtValue(getCity(observationComponent));
            } else if (code.equals(LOINC_STATE_OF_TRAVEL.getCode())) {
                travel.setBundeslandRegionDefiningCode(getBundeslandRegion(observationComponent));
            } else if (code.equals(LOINC_COUNTRY_OF_TRAVEL.getCode())) {
                travel.setLandDefiningCode(getLand(observationComponent));
            } else {
                throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + system + ":"+code+"' instead");
            }
        }
        adminentry.setBestimmtesReiseziel(List.of(travel));

        return adminentry;
    }

    private TemporalAccessor getDate(Observation.ObservationComponentComponent observationComponent) {

        Coding coding = observationComponent.getCode().getCoding().get(0);
        TemporalAccessor date = observationComponent.getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
        return date;
    }

    private String getCity(Observation.ObservationComponentComponent observationComponent) {
        String city = observationComponent.getValueStringType().getValue();
        return city;
    }

    private LandDefiningCode getLand(Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getValueCodeableConcept().getCoding().get(0);
        LandDefiningCode country = countryMap.get(coding.getCode());
        return country;
    }

    private BundeslandRegionDefiningCode getBundeslandRegion(Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getValueCodeableConcept().getCoding().get(0);
        BundeslandRegionDefiningCode region = regionMap.get(coding.getCode());
        return region;
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

    private String getCodeOfConcept(Observation.ObservationComponentComponent codingObject) {
        return codingObject.getCode().getCoding().get(0).getCode();
    }

    private Observation.ObservationComponentComponent getCodingObject(Observation observation, int i) {
        return observation.getComponent().get(i);
    }


    //###################################################################################

    private ReisehistorieComposition map_no(Observation observation, AussageUeberDenAusschlussDefiningCode reiseCode) {

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

    private ReisehistorieComposition map_unknown(Observation observation, AussageUeberDieFehlendeInformationDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        UnbekannteReisehistorieEvaluation evaluation = new UnbekannteReisehistorieEvaluation();

        evaluation.setFehlendeInformationDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        evaluation.setAussageUeberDieFehlendeInformationDefiningCode(reiseCode);
        evaluation.setLanguage(Language.DE);
        evaluation.setSubject(new PartySelf());

        composition.setUnbekannteReisehistorie(evaluation);

        return composition;
    }
}
