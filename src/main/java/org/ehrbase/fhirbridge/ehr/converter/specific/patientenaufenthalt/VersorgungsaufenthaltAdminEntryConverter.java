package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.VersorgungsaufenthaltAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.StandortCluster;
import org.ehrbase.fhirbridge.fhir.support.Encounters;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Coding;
import java.util.ArrayList;

public class VersorgungsaufenthaltAdminEntryConverter extends EntryEntityConverter<Encounter, VersorgungsaufenthaltAdminEntry> {

    private static final String FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Fachabteilungsschluessel";

    @Override
    protected VersorgungsaufenthaltAdminEntry convertInternal(Encounter encounter) {
        VersorgungsaufenthaltAdminEntry versorgungsaufenthaltAdminEntry = new VersorgungsaufenthaltAdminEntry();

        if(Encounters.isNotEmpty(encounter.getLocation())) {

            Encounter.EncounterLocationComponent location = encounter.getLocation().get(0);

            TimeConverter.convertEncounterLocationTime(location).ifPresent(versorgungsaufenthaltAdminEntry::setBeginnValue);
            TimeConverter.convertEncounterLocationEndTime(location).ifPresent(versorgungsaufenthaltAdminEntry::setEndeValue);

            versorgungsaufenthaltAdminEntry.setStandort(convertStandortCluster(location));

            versorgungsaufenthaltAdminEntry.setKommentarValue(location.getLocation().getDisplay());
        }

        versorgungsaufenthaltAdminEntry.setFachlicheOrganisationseinheit(createFachlicheOrganisationseinheitClusterList(encounter));

        return versorgungsaufenthaltAdminEntry;
    }

    private StandortCluster convertStandortCluster(Encounter.EncounterLocationComponent location) {

        // location physical type / name -> standort
        String locationPhysicalType = location.getPhysicalType().getCoding().get(0).getCode();

        String locationName = location.getPhysicalType().getText();
        StandortCluster standortCluster = new StandortCluster();

        if (locationName != null && !locationName.isEmpty()) {

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
                    throw new UnprocessableEntityException("unexpected location physical type " + locationPhysicalType +
                            " by EHR composition.");
            }
        }

        return standortCluster;
    }

    private ArrayList<FachlicheOrganisationseinheitCluster> createFachlicheOrganisationseinheitClusterList(Encounter encounter) {

        ArrayList<FachlicheOrganisationseinheitCluster> retVal = new ArrayList<>();

        if (encounter.getServiceType() != null
                && encounter.getServiceType().getCoding() != null) {

            for(Coding fachAbteilungsSchluessel : encounter.getServiceType().getCoding()) {

                FachlicheOrganisationseinheitCluster fachlicheOrganisationseinheitCluster = new FachlicheOrganisationseinheitCluster();

                if (fachAbteilungsSchluessel.getSystem().equals(FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM)
                        && FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().containsKey(fachAbteilungsSchluessel.getCode())) {

                    fachlicheOrganisationseinheitCluster.setFachabteilungsschluesselDefiningCode(FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().get(fachAbteilungsSchluessel.getCode()));
                } else {
                    throw new UnprocessableEntityException("Invalid Code " + fachAbteilungsSchluessel.getCode() +
                            " or Code System for 'Fachabteilungsschlüssel'.");
                }

                retVal.add(fachlicheOrganisationseinheitCluster);
            }
        }

        return retVal;
    }
}