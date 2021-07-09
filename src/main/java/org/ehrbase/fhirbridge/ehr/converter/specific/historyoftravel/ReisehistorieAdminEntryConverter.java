package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.BundeslandRegionDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.HistoryOfTravelCode;
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
import java.util.Optional;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;


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
        convertInternalEvents(resource).ifPresent(adminEntry::setBestimmtesReiseziel);
        return adminEntry;
    }

    private Optional<List<ReisehistorieBestimmtesReisezielCluster>> convertInternalEvents(Observation observation) {
        if (observation.hasComponent()) {
            return mapReisehistorie(new ReisehistorieBestimmtesReisezielCluster(), observation);

        }
        return Optional.empty();
    }

    private Optional<List<ReisehistorieBestimmtesReisezielCluster>> mapReisehistorie(ReisehistorieBestimmtesReisezielCluster travel, Observation observation) {
        for (Observation.ObservationComponentComponent observationComponent
                : observation.getComponent()) {
            setReiseHistorie(travel, observationComponent);
        }
        return Optional.of(List.of(travel));
    }

    private void setReiseHistorie(ReisehistorieBestimmtesReisezielCluster travel, Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getCode().getCoding().get(0);
        validateCodeSystemLOINC(coding.getSystem());
        String code = coding.getCode();
        if (code.equals(HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED.getCode())) {
            travel.setEinreisedatumValue(getDate(observationComponent));
        } else if (code.equals( HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION.getCode())) {
            travel.setAbfahrtsdatumValue(getDate(observationComponent));
        } else if (code.equals( HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL.getCode())) {
            travel.setStadtValue(getCity(observationComponent));
        } else if (code.equals( HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL.getCode())) {
            travel.setBundeslandRegionDefiningCode(getBundeslandRegion(observationComponent));
        } else if (code.equals( HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL.getCode())) {
            travel.setLandDefiningCode(getLand(observationComponent));
        } else {
            throw new ConversionException("Expected loinc-code for history of travel, but got '" + coding.getSystem() + ":" + code + "' instead");
        }
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

    private void validateCodeSystemLOINC(String systemCode) {
        if (!LOINC.getUrl().equals(systemCode)) {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + LOINC.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

}
