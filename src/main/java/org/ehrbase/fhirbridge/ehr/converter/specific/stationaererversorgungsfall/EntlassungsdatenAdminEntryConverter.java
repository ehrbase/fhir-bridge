package org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall;

import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.ArtDerEntlassungDefiningCode;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Coding;
import java.util.Optional;


public class EntlassungsdatenAdminEntryConverter extends EncounterToAdminEntryConverter<EntlassungsdatenAdminEntry>{

    private static final String ART_DER_ENTLASSUNG_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Entlassungsgrund";

    @Override
    protected EntlassungsdatenAdminEntry convertInternal(Encounter encounter) {

        EntlassungsdatenAdminEntry entlassungsdatenAdminEntry = new EntlassungsdatenAdminEntry();

        convertArtDerEntlassungDefiningCode(encounter).ifPresent(entlassungsdatenAdminEntry::setArtDerEntlassungDefiningCode);

        return entlassungsdatenAdminEntry;
    }

    private Optional<ArtDerEntlassungDefiningCode> convertArtDerEntlassungDefiningCode(Encounter encounter) {

        if (!encounter.hasHospitalization()) {
            return Optional.empty();
        }

        if (encounter.getHospitalization().getDischargeDisposition() == null) {
            return Optional.empty();
        }

        Coding artDerEntlassung = encounter.getHospitalization().getDischargeDisposition().getCoding().get(0);

        if (artDerEntlassung.getSystem().equals(ART_DER_ENTLASSUNG_CODE_SYSTEM)
                && StationaererVersorgungsfallDefiningCodeMaps.getArtDerEntlassungMap().containsKey(artDerEntlassung.getCode())) {

            return Optional.of(StationaererVersorgungsfallDefiningCodeMaps.getArtDerEntlassungMap().get(artDerEntlassung.getCode()));
        } else {
            throw new UnprocessableEntityException("Invalid Code " + artDerEntlassung.getCode() +
                    " or Code System for mapping of 'art der Entlassung'.");
        }
    }
}