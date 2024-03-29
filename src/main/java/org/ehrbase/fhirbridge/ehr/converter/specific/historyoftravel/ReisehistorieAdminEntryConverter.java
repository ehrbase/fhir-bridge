package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieBestimmtesReisezielCluster;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;

public class ReisehistorieAdminEntryConverter extends EntryEntityConverter<Observation, ReisehistorieAdminEntry> {

    private final DvCodedTextParser dvCodedTextParser = DvCodedTextParser.getInstance();

    @Override
    protected ReisehistorieAdminEntry convertInternal(Observation resource) {
        ReisehistorieAdminEntry adminEntry = new ReisehistorieAdminEntry();
        adminEntry.setReiseAngetretenDefiningCode(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE);
        if (resource.hasComponent()) {
            adminEntry.setBestimmtesReiseziel(convertReisehistorie(resource));
        }
        return adminEntry;
    }

    private List<ReisehistorieBestimmtesReisezielCluster> convertReisehistorie(Observation observation) {
        List<ReisehistorieBestimmtesReisezielCluster> reisehistorieBestimmtesReisezielClusters = new ArrayList<>();
        for (Observation.ObservationComponentComponent observationComponent
                : observation.getComponent()) {
            setReiseHistorie(reisehistorieBestimmtesReisezielClusters, observationComponent);
        }
        return reisehistorieBestimmtesReisezielClusters;
    }

    private void setReiseHistorie(List<ReisehistorieBestimmtesReisezielCluster> reisehistorieBestimmtesReisezielClusters, Observation.ObservationComponentComponent observationComponent) {
        Coding coding = observationComponent.getCode().getCoding().get(0);
        validateCodeSystemLOINC(coding.getSystem());
        String code = coding.getCode();
        if (code.equals(HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED.getCode())) {
            reisehistorieBestimmtesReisezielClusters.add(convertEinreiseDatum(observationComponent));
        } else if (code.equals(HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION.getCode())) {
            reisehistorieBestimmtesReisezielClusters.add(convertAbfahrtsDatum(observationComponent));
        } else if (code.equals(HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL.getCode())) {
            reisehistorieBestimmtesReisezielClusters.add(convertStadtValue(observationComponent));
        } else if (code.equals(HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL.getCode())) {
            reisehistorieBestimmtesReisezielClusters.add(convertBundeslandRegion(observationComponent));
        } else if (code.equals(HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL.getCode())) {
            reisehistorieBestimmtesReisezielClusters.add(convertLandCode(observationComponent));
        } else {
            throw new ConversionException("Expected loinc-code for history of travel, but got '" + coding.getSystem() + ":" + code + "' instead");
        }
    }

    private ReisehistorieBestimmtesReisezielCluster convertEinreiseDatum(Observation.ObservationComponentComponent observationComponent) {
        ReisehistorieBestimmtesReisezielCluster reisehistorieBestimmtesReisezielCluster = new ReisehistorieBestimmtesReisezielCluster();
        if (observationComponent.hasValueDateTimeType()) {
            reisehistorieBestimmtesReisezielCluster.setEinreisedatumValue(getDate(observationComponent));
        } else {
            reisehistorieBestimmtesReisezielCluster.setEinreisedatumNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return reisehistorieBestimmtesReisezielCluster;
    }

    private ReisehistorieBestimmtesReisezielCluster convertAbfahrtsDatum(Observation.ObservationComponentComponent observationComponent) {
        ReisehistorieBestimmtesReisezielCluster reisehistorieBestimmtesReisezielCluster = new ReisehistorieBestimmtesReisezielCluster();
        if (observationComponent.hasValueDateTimeType()) {
            reisehistorieBestimmtesReisezielCluster.setAbfahrtsdatumValue(getDate(observationComponent));
        } else {
            reisehistorieBestimmtesReisezielCluster.setAbfahrtsdatumNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return reisehistorieBestimmtesReisezielCluster;
    }

    private ReisehistorieBestimmtesReisezielCluster convertStadtValue(Observation.ObservationComponentComponent observationComponent) {
        ReisehistorieBestimmtesReisezielCluster reisehistorieBestimmtesReisezielCluster = new ReisehistorieBestimmtesReisezielCluster();
        if (observationComponent.hasValueStringType()) {
            reisehistorieBestimmtesReisezielCluster.setStadtValue(getCity(observationComponent));
        } else {
            reisehistorieBestimmtesReisezielCluster.setStadtNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return reisehistorieBestimmtesReisezielCluster;
    }

    private ReisehistorieBestimmtesReisezielCluster convertBundeslandRegion(Observation.ObservationComponentComponent component) {
        ReisehistorieBestimmtesReisezielCluster reisehistorieBestimmtesReisezielCluster = new ReisehistorieBestimmtesReisezielCluster();
        if (component.hasValueCodeableConcept()) {
            dvCodedTextParser.parseFHIRCoding(component.getValueCodeableConcept().getCoding().get(0))
                    .ifPresent(reisehistorieBestimmtesReisezielCluster::setBundeslandRegion);
        } else {
            reisehistorieBestimmtesReisezielCluster.setBundeslandRegionNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return reisehistorieBestimmtesReisezielCluster;
    }

    private ReisehistorieBestimmtesReisezielCluster convertLandCode(Observation.ObservationComponentComponent component) {
        ReisehistorieBestimmtesReisezielCluster reisehistorieBestimmtesReisezielCluster = new ReisehistorieBestimmtesReisezielCluster();
        if (component.hasValueCodeableConcept()) {
            dvCodedTextParser.parseFHIRCoding(component.getValueCodeableConcept().getCoding().get(0))
                    .ifPresent(reisehistorieBestimmtesReisezielCluster::setLand);
        } else {
            reisehistorieBestimmtesReisezielCluster.setLandNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return reisehistorieBestimmtesReisezielCluster;
    }

    private TemporalAccessor getDate(Observation.ObservationComponentComponent observationComponent) {
        return observationComponent.getValueDateTimeType().getValueAsCalendar().toZonedDateTime();
    }

    private String getCity(Observation.ObservationComponentComponent observationComponent) {
        return observationComponent.getValueStringType().getValue();
    }

    private void validateCodeSystemLOINC(String systemCode) {
        if (!LOINC.getUrl().equals(systemCode)) {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + LOINC.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

}
