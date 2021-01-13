package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.IntensivmedizinischesMonitoringKorpertemperaturComposition;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KorpertemperaturObservation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ProblemDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TravelHistoryCompositionConverter implements CompositionConverter<ReisehistorieComposition, Observation> {
    private static final Logger LOG = LoggerFactory.getLogger(TravelHistoryCompositionConverter.class);

    @Override
    public Observation fromComposition(ReisehistorieComposition composition) throws CompositionConversionException {
        //your mapping code
        // return the mapped Observation of body temp
        return null;
    }

    @Override
    public ReisehistorieComposition toComposition(Observation observation) throws CompositionConversionException {


        IntensivmedizinischesMonitoringKorpertemperaturComposition result = new IntensivmedizinischesMonitoringKorpertemperaturComposition();

        // ========================================================================================
        // value quantity is expected
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();


        try {
            fhirValue = observation.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            LOG.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }

        // mapping to openEHR
        KorpertemperaturBeliebigesEreignisPointEvent tempEvent = new KorpertemperaturBeliebigesEreignisPointEvent();
        tempEvent.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());


        KorpertemperaturObservation tempObs = new KorpertemperaturObservation();
        List<KorpertemperaturBeliebigesEreignisChoice> events = new ArrayList<>();
        events.add(tempEvent);
        tempObs.setBeliebigesEreignis(events);
        tempObs.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime()); // mandatory
        tempObs.setLanguage(Language.EN); // FIXME: we need to grab the language from the template
        tempObs.setSubject(new PartySelf());

        List<KorpertemperaturObservation> observations = new ArrayList<>();
        observations.add(tempObs);
        result.setKorpertemperatur(observations);


        //####################################################################

        // copied from BodTemperature Example
        if (observation == null) {
            return null;
        }

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);

        //###############################################################

        String code = getSnomedCodeObservation(observation);
        // check for general travel state
        try {
            if (code.equals(snomed_yes)) {
                return map_yes(observation, ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE);
            } else if (code.equals(snomed_no)) {
                return map_no(observation, AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE);
            } else if (code.equals(snomed_unknown)) {
                return map_unknown(observation, AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE) ;
            } else {
                throw new UnprocessableEntityException("Expected snomed-code for history of travel, but got '" + code + "' instead ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnprocessableEntityException(e.getMessage());
        }
    }
    //###################################################################################

    //source http://fhir.ch/ig/ch-ems/ValueSet-yes-no-unknown.html
    private static final String snomed_yes = "373066001";
    private static final String snomed_no = "373067005";
    private static final String snomed_unknown = "261665006";

    private static String getSnomedCodeObservation(Observation fhirObservation) {
        return  fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();
    }
    //###################################################################################


    private ReisehistorieComposition createCompositionAndSetDefaults(Observation observation) {
        ReisehistorieComposition composition = new ReisehistorieComposition();
        // ======================================================================================
        // @severin: do we need all this stuff?
        // Required fields by API
        composition.setLanguage(org.ehrbase.client.classgenerator.shareddefinition.Language.EN); // FIXME: we need to grab the language from the template
        composition.setLocation("test");
        //composition.setSettingDefiningCode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);// woher weiß ich das?
        composition.setTerritory(org.ehrbase.client.classgenerator.shareddefinition.Territory.DE);
        //composition.setCategoryDefiningCode(CategoryDefiningcode.EVENT);
        // @severin: is contained in bodytemperature, but can't find it in the "history of travel" fhir structure
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // FIXME: https://github.com/ehrbase/ehrbase_client_library/issues/31
        //        PartyProxy composer = new PartyIdentified();
        //        result.setComposer(composer);

        composition.setComposer(new PartySelf());
        //#################################################################

        return composition;
    }

    //###################################################################################
    private ReisehistorieComposition map_yes(Observation observation, ReiseAngetretenDefiningCode reiseCode) {
        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        ReisehistorieAdminEntry adminentry = new ReisehistorieAdminEntry();

        adminentry.setReiseAngetretenDefiningCode(reiseCode);
        adminentry = mapInternalEvents(adminentry, observation);

        composition.setReisehistorie(adminentry);

        return composition;
    }

    private ReisehistorieAdminEntry mapInternalEvents(ReisehistorieAdminEntry adminentry, Observation observation) {

        for (int i = 0; i < observation.getComponent().size(); i++) {
            Observation.ObservationComponentComponent codingObject = getCodingObject(observation, i);
            //adminentry = mapTravelDestination(adminentry, codingObject);
        }

        return adminentry;
    }

    private static final String loinc_url = "http://loinc.org";
    private static final String loinc_DateTravelStarted = "82752-7";
    private static final String loinc_DateOfDepartureFromTravelDestination = "91560-3";
    private static final String loinc_CityOfTravel = "94653-3";
    private static final String loinc_StateOfTravel = "82754-3";
    private static final String loinc_CountryOfTravel = "94651-7";

    private ReisehistorieAdminEntry mapTravelDestination(ReisehistorieAdminEntry adminentry, Observation.ObservationComponentComponent codingObject) {
        String codeOfConcept = getCodeOfConcept(codingObject);

        //Birgit Continue -> code worked in old fhir-bridge, but not longer
/*
        adminentry.setBestimmtesReiseziel();

        List<ReisehistorieBestimmtesReisezielCluster> travelDestinations = new ArrayList<>();

        //travelDestinations.

        ReisehistorieBestimmtesReisezielCluster a;

        a.setEinreisedatumValue();

        if (codeOfConcept.equals(loinc_DateTravelStarted)) {
            adminentry.setAbreisedatumValue(getDateTime(codingObject));
        } else if (codeOfConcept.equals(loinc_DateOfDepartureFromTravelDestination)) {
            adminentry.setRuckreisedatumValue(getDateTime(codingObject));
        } else if (codeOfConcept.equals(loinc_CityOfTravel)) {
            adminentry.setStadtValue(getString(codingObject));
        } else if (codeOfConcept.equals(loinc_StateOfTravel)) {
            adminentry.setBundeslandRegionDefiningcode(getBundeslandByCode(codingObject));
        } else if (codeOfConcept.equals(loinc_CountryOfTravel)) {
            adminentry.setLandDefiningcode(getLandByCode(codingObject));
        } else {
            throw new UnprocessableEntityException("Expected loinc-code for history of travel, but got '" + codeOfConcept + "' instead ");
        }

        travelDestinations.add(a);
        adminentry.setBestimmtesReiseziel(travelDestinations);
*/

        return adminentry;
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

        composition.setKeineReisehistorie(evaluation);

        return composition;
    }
    //###################################################################################

    private ReisehistorieComposition map_unknown(Observation observation, AussageUeberDieFehlendeInformationDefiningCode reiseCode) {

        ReisehistorieComposition composition = createCompositionAndSetDefaults(observation);
        UnbekannteReisehistorieEvaluation evaluation = new UnbekannteReisehistorieEvaluation();

        evaluation.setFehlendeInformationDefiningCode(ProblemDiagnoseDefiningCode.HISTORY_OF_TRAVEL);
        evaluation.setAussageUeberDieFehlendeInformationDefiningCode(reiseCode);

        composition.setUnbekannteReisehistorie(evaluation);

        return composition;
    }

    //@Severin: was ist mit dem feeder audit? Setzen?
}
