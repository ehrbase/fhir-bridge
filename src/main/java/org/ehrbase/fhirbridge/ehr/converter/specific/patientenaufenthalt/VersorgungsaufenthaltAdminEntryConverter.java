package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.StandortCluster;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.VersorgungsaufenthaltAdminEntry;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VersorgungsaufenthaltAdminEntryConverter extends EncounterToAdminEntryConverter<VersorgungsaufenthaltAdminEntry> {


    @Override
    protected VersorgungsaufenthaltAdminEntry convertInternal(Encounter encounter) {
        VersorgungsaufenthaltAdminEntry versorgungsaufenthaltAdminEntry = new VersorgungsaufenthaltAdminEntry();
        mapStandort(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setStandort);
        mapKommentar(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setKommentarValue);
        mapBeginn(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setBeginnValue);
        mapEnde(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setEndeValue);
        mapGrundDesAufenthalts(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setGrundDesAufenthaltesValue);
        FachlicheOrganisationseinheitConverter.convert(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setFachlicheOrganisationseinheit);
        return versorgungsaufenthaltAdminEntry;
    }


    private Optional<String> mapKommentar(Encounter encounter) {
        if (encounter.hasLocation()) {
            for (Encounter.EncounterLocationComponent location : encounter.getLocation()) {
                if (location.hasLocation() && location.getLocation().hasDisplay()) {
                    return Optional.of(location.getLocation().getDisplay());
                }
            }
        }
        return Optional.empty();
    }

    private Optional<StandortCluster> mapStandort(Encounter encounter) {
        if (encounter.hasLocation()) {
            for (Encounter.EncounterLocationComponent location : encounter.getLocation()) {
                return convertStandort(location);
            }
        }
        return Optional.empty();
    }

    private Optional<StandortCluster> convertStandort(Encounter.EncounterLocationComponent location) {
        if (location.hasPhysicalType() && location.getPhysicalType().hasCoding()) {
            for (Coding coding : location.getPhysicalType().getCoding()) {
                if (coding.hasCode() && location.getPhysicalType().hasText()) {
                    return Optional.of(mapLocationToStandortCluster(location));
                }
            }
        }
        return Optional.empty();
    }

    private StandortCluster mapLocationToStandortCluster(Encounter.EncounterLocationComponent location) {
        String locationPhysicalType = location.getPhysicalType().getCoding().get(0).getCode();
        String locationName = location.getPhysicalType().getText();
        StandortCluster standortCluster = new StandortCluster();
        switch (locationPhysicalType) {
            case "si":
                standortCluster.setCampusValue(locationName);
                break;
            case "bu":
                standortCluster.setGebaeudegruppeValue(locationName);
                break;
            case "lvl":
                standortCluster.setEbeneValue(locationName);
                break;
            case "wa":
                standortCluster.setStationValue(locationName);
                break;
            case "ro":
                standortCluster.setZimmerValue(locationName);
                break;
            case "bd":
                standortCluster.setBettplatzValue(locationName);
                break;
            default: // other types aren't needed by EHR Composition
                throw new ConversionException("unexpected location physical type " + locationPhysicalType +
                        " by EHR composition.");
        }

        return standortCluster;
    }

    private Optional<String> mapGrundDesAufenthalts(Encounter encounter) {
        if (!encounter.hasReasonCode()) {
            return Optional.empty();
        }

        return encounter.getReasonCode()
                .stream()
                .filter(CodeableConcept::hasCoding)
                .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                .map(Coding::getCode)
                .findFirst();
    }

    private Optional<TemporalAccessor> mapEnde(Encounter encounter) { //Exceptions in Timeconversion should not be moved to abstract converter
        if (encounter.hasPeriod()) {
            return Optional.of(TimeConverter.convertEncounterTime(encounter));
        }
        return Optional.empty();
    }

    private Optional<TemporalAccessor> mapBeginn(Encounter encounter) { //Exceptions should in Timeconversion not be moved to abstract converter
        if (encounter.hasPeriod()) {
            return TimeConverter.convertEncounterEndTime(encounter);
        }
        return Optional.empty();
    }
}