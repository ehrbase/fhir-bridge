package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.checkerframework.checker.nullness.Opt;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachabteilungsschluesselDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FachlicheOrganisationseinheitConverter {

    private static boolean isEmpty = true;

    private static final String FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Fachabteilungsschluessel";

    public static Optional<List<FachlicheOrganisationseinheitCluster>> convert(Encounter encounter) {
        List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheitClusters = new ArrayList<>();
        if (encounter.hasServiceType() && encounter.getServiceType().hasCoding()) {
            fachlicheOrganisationseinheitClusters.add(convertFachlicheOrganisationsEinheit(encounter));
        }
        if (fachlicheOrganisationseinheitClusters.size() == 1) {
            return Optional.of(fachlicheOrganisationseinheitClusters);
        } else {
            return Optional.empty();
        }
    }

    private static FachlicheOrganisationseinheitCluster convertFachlicheOrganisationsEinheit(Encounter encounter) {
        FachlicheOrganisationseinheitCluster fachlicheOrganisationseinheitCluster = new FachlicheOrganisationseinheitCluster();
        mapFachabteilungsSchluessel(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setFachabteilungsschluesselDefiningCode);
        mapTyp(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setTypValue);
        mapName(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setNameValue);
        mapAktiv(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setAktivInaktivValue);
        mapZusaetzlicheBeschreibung(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setZusaetzlicheBeschreibungValue);
        return fachlicheOrganisationseinheitCluster;
    }

    private static Optional<FachabteilungsschluesselDefiningCode> mapFachabteilungsSchluessel(Encounter encounter) {
        if (encounter.hasServiceType() && encounter.getServiceType().hasCoding()) {
            for (Coding coding : encounter.getServiceType().getCoding()) {
                return convertFachabteilungsSchluessel(coding);
            }
        }
        return Optional.empty();
    }

    private static Optional<FachabteilungsschluesselDefiningCode> convertFachabteilungsSchluessel(Coding coding) {
        if (coding.hasSystem() && coding.hasCode() && coding.getSystem().equals(FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM) &&
                FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().containsKey(coding.getCode())) {
            isEmpty = false;
            return Optional.of(FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().get(coding.getCode()));
        } else {
            return Optional.empty();
        }
    }


}
