package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.BundeslandRegionDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.LandDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieBestimmtesReisezielCluster;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL;

public class ReisehistorieAdminEntryConverter extends EntryEntityConverter<Observation, ReisehistorieAdminEntry> {

    private static final Map<String, LandDefiningCode> countryMap = new HashMap<>();

    private static final Map<String, BundeslandRegionDefiningCode> regionMap = new HashMap<>();


    @Override
    protected ReisehistorieAdminEntry convertInternal(Observation resource) {
        ReisehistorieAdminEntry adminEntry = new ReisehistorieAdminEntry();

        for (LandDefiningCode item : LandDefiningCode.values()) {
            countryMap.put(item.getCode(), item);
        }

        for (BundeslandRegionDefiningCode item : BundeslandRegionDefiningCode.values()) {
            regionMap.put(item.getCode(), item);
        }

        adminEntry.setReiseAngetretenDefiningCode(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE);
        adminEntry.setBestimmtesReiseziel(convertInternalEvents(resource));
        return adminEntry;
    }

    private List<ReisehistorieBestimmtesReisezielCluster> convertInternalEvents(Observation observation) {

        ReisehistorieBestimmtesReisezielCluster travel = new ReisehistorieBestimmtesReisezielCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : observation.getComponent()) {

            Coding coding = observationComponent.getCode().getCoding().get(0);
            checkForLoincSystem(coding.getSystem());

            String code = coding.getCode();
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
                throw new ConversionException("Expected loinc-code for history of travel, but got '" + coding.getSystem() + ":" + code + "' instead");
            }
        }
        return List.of(travel);
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
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + LOINC.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

}
