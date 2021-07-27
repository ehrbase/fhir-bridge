package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachabteilungsschluesselDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FachlicheOrganisationseinheitConverter {

    private static final String FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Fachabteilungsschluessel";

    public static Optional<List<FachlicheOrganisationseinheitCluster>> convert(Encounter encounter) {
        List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheitClusters = new ArrayList<>();
        if (encounter.hasServiceType() && encounter.getServiceType().hasCoding()) {
            fachlicheOrganisationseinheitClusters.add(convertFachlicheOrganisationsEinheit(encounter));
            return Optional.of(fachlicheOrganisationseinheitClusters);
        } else {
            return Optional.empty();
        }

    }

    private static FachlicheOrganisationseinheitCluster convertFachlicheOrganisationsEinheit(Encounter encounter) {
        FachlicheOrganisationseinheitCluster fachlicheOrganisationseinheitCluster = new FachlicheOrganisationseinheitCluster();
        mapFachabteilungsSchluessel(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setFachabteilungsschluesselDefiningCode);
        mapZusaetzlicheBeschreibung(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setZusaetzlicheBeschreibungValue);
        mapTyp(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setTypValue);
        mapName(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setNameValue);
        mapAktiv(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setAktivInaktivValue);
        mapZusaetzlicheBeschreibung(encounter).ifPresent(fachlicheOrganisationseinheitCluster::setZusaetzlicheBeschreibungValue);
        return fachlicheOrganisationseinheitCluster;
    }

    private static Optional<Boolean> mapAktiv(Encounter encounter) {
        if (encounter.hasServiceProvider() && encounter.getServiceProviderTarget().hasActive()) {
            return Optional.of(encounter.getServiceProviderTarget().getActive());
        } else {
            return Optional.empty();
        }
    }

    private static Optional<String> mapName(Encounter encounter) {
        if (encounter.hasServiceProvider() && encounter.getServiceProviderTarget().hasName()) {
            return Optional.of(encounter.getServiceProviderTarget().getName());
        } else {
            return Optional.empty();
        }
    }

    private static Optional<String> mapTyp(Encounter encounter) {
        if (encounter.hasServiceProvider() && encounter.getServiceProviderTarget().hasType()) {
            return encounter.getServiceProviderTarget().getType().stream()
                    .filter(CodeableConcept::hasCoding)
                    .flatMap(codeableConcept -> codeableConcept.getCoding().stream())
                    .map(Coding::getCode)
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }

    private static Optional<String> mapZusaetzlicheBeschreibung(Encounter encounter) {
        if (encounter.hasServiceType() && encounter.getServiceType().hasText()) {
            return Optional.of(encounter.getServiceType().getText());
        }
        return Optional.empty();
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
        if (coding.hasSystem() && coding.hasCode() && coding.getSystem().equals(FACH_ABTEILUNGS_SCHLUESSEL_CODE_SYSTEM)) {
            if(FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().containsKey(coding.getCode())){
                return Optional.of(FachAbteilungsSchluesselDefiningCodeMap.getFachAbteilungsSchluesselMap().get(coding.getCode()));
            }else{
                throw new ConversionException("Invalid Code "+coding.getCode()+" or Code System for 'Fachabteilungsschlüssel'.");
            }
        } else {
            return Optional.empty();
        }
    }


}
