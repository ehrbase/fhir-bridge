package org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmeanlassDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmegrundDefiningCode;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Coding;
import java.util.Optional;


public class AufnahmedatenAdminEntryConverter extends EntryEntityConverter<Encounter, AufnahmedatenAdminEntry> {

    private static final String AUFNAHME_GRUND_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/modul-fall/core/CodeSystem/Aufnahmegrund";
    private static final String AUFNAHME_ANLASS_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Aufnahmeanlass";
    private static final String INVALID_CODE = "Invalid Code ";

    @Override
    protected AufnahmedatenAdminEntry convertInternal(Encounter encounter) {

        AufnahmedatenAdminEntry aufnahmedatenAdminEntry = new AufnahmedatenAdminEntry();

        aufnahmedatenAdminEntry.setDatumUhrzeitDerAufnahmeValue(TimeConverter.convertEncounterTime(encounter));

        convertAufnahmegrundDefiningCode(encounter).ifPresent(aufnahmedatenAdminEntry::setAufnahmegrundDefiningCode);

        convertAufnahmeanlassDefiningCode(encounter).ifPresent(aufnahmedatenAdminEntry::setAufnahmeanlassDefiningCode);

        return aufnahmedatenAdminEntry;
    }

    private Optional<AufnahmegrundDefiningCode> convertAufnahmegrundDefiningCode(Encounter encounter) {

        if (encounter.getReasonCode() == null) {
            return Optional.empty();
        }

        if (encounter.getReasonCode().get(0).getCoding() == null) {
            return Optional.empty();
        }

        Coding aufnahmeGrund = encounter.getReasonCode().get(0).getCoding().get(0);

        if (aufnahmeGrund.getSystem().equals(AUFNAHME_GRUND_CODE_SYSTEM)
                && StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeGrundMap().containsKey(aufnahmeGrund.getCode())) {

            return Optional.of(StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeGrundMap().get(aufnahmeGrund.getCode()));
        } else {
            throw new UnprocessableEntityException(INVALID_CODE + aufnahmeGrund.getCode() +
                    " or Code System for mapping of 'Aufnahmegrund', valid codes are: 01, 02, 03, 04, 05, 06, 07, 08, 10.");
        }
    }

    private Optional<AufnahmeanlassDefiningCode> convertAufnahmeanlassDefiningCode(Encounter encounter) {

        if (encounter.getHospitalization() == null) {
            return Optional.empty();
        }

        if (encounter.getHospitalization().getAdmitSource() == null) {
            return Optional.empty();
        }

        Coding aufnahmeAnlass = encounter.getHospitalization().getAdmitSource().getCoding().get(0);

        if (aufnahmeAnlass.getSystem().equals(AUFNAHME_ANLASS_CODE_SYSTEM)
                && StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeAnlassMap().containsKey(aufnahmeAnlass.getCode())) {

            return Optional.of(StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeAnlassMap().get(aufnahmeAnlass.getCode()));
        } else {
            throw new UnprocessableEntityException(INVALID_CODE + aufnahmeAnlass.getCode() +
                    " or Code System for mapping of 'Aufnahmeanlass', valid codes are: N, G, E, A, V, Z, B, R.");
        }
    }
}