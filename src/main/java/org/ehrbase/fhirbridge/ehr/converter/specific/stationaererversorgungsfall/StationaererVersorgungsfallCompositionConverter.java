package org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.StationaererVersorgungsfallComposition;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.fhirbridge.fhir.support.KontaktebeneDefiningCode;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Coding;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

public class StationaererVersorgungsfallCompositionConverter extends EncounterToCompositionConverter<StationaererVersorgungsfallComposition> {

    private static final String AUFNAHME_GRUND_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/modul-fall/core/CodeSystem/Aufnahmegrund";
    private static final String AUFNAHME_ANLASS_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Aufnahmeanlass";
    private static final String ART_DER_ENTLASSUNG_CODE_SYSTEM = "https://www.medizininformatik-initiative.de/fhir/core/modul-fall/CodeSystem/Entlassungsgrund";
    private static final String INVALID_CODE = "Invalid Code ";

    @Override
    public StationaererVersorgungsfallComposition convertInternal(@NonNull Encounter encounter) {

        StationaererVersorgungsfallComposition retVal = new StationaererVersorgungsfallComposition();

        retVal.setFalltypValue(KontaktebeneDefiningCode.EINRICHTUNGS_KONTAKT.getValue());

        String fallClass = encounter.getClass_().getCode();
        if (fallClass.equals("normalstationaer")
        || fallClass.equals("intensivstationaer")) {

            retVal.setFallklasseValue("Stationär");
        }

        retVal.setFallstatusDefiningCode(StationaererVersorgungsfallDefiningCodeMaps.getFallStatusMap().get(encounter.getStatus()));
        retVal.setFallKennungValue(encounter.getIdentifier().get(0).getValue());

        retVal.setAufnahmedaten(createAufnahmedatenAdminEntry(encounter));

        if (encounter.getPeriod().hasEndElement()) {

            retVal.setEntlassungsdaten(createEntlassungsdatenAdminEntry(encounter));
        }

        return retVal;
    }

    private AufnahmedatenAdminEntry createAufnahmedatenAdminEntry(Encounter encounter) {
        AufnahmedatenAdminEntry aufnahmedatenAdminEntry = new AufnahmedatenAdminEntry();

        OffsetDateTime startDateTime = OffsetDateTime.from(encounter.getPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        aufnahmedatenAdminEntry.setDatumUhrzeitDerAufnahmeValue(startDateTime);

        if (encounter.getReasonCode() != null
                && encounter.getReasonCode().get(0).getCoding() != null) {

            Coding aufnahmeGrund = encounter.getReasonCode().get(0).getCoding().get(0);
            if (aufnahmeGrund.getSystem().equals(AUFNAHME_GRUND_CODE_SYSTEM)
                    && StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeGrundMap().containsKey(aufnahmeGrund.getCode())) {

                aufnahmedatenAdminEntry.setAufnahmegrundDefiningCode(StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeGrundMap().get(aufnahmeGrund.getCode()));
            } else {
                throw new IllegalStateException(INVALID_CODE + aufnahmeGrund.getCode() +
                        " or Code System for mapping of 'Aufnahmegrund', valid codes are: 01, 02, 03, 04, 05, 06, 07, 08, 10.");
            }
        }

        if (encounter.getHospitalization() != null
                && encounter.getHospitalization().getAdmitSource() != null) {

            Coding aufnahmeAnlass = encounter.getHospitalization().getAdmitSource().getCoding().get(0);

            if (aufnahmeAnlass.getSystem().equals(AUFNAHME_ANLASS_CODE_SYSTEM)
                    && StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeAnlassMap().containsKey(aufnahmeAnlass.getCode())) {

                aufnahmedatenAdminEntry.setAufnahmeanlassDefiningCode(StationaererVersorgungsfallDefiningCodeMaps.getAufnahmeAnlassMap().get(aufnahmeAnlass.getCode()));
            } else {
                throw new IllegalStateException(INVALID_CODE + aufnahmeAnlass.getCode() +
                        " or Code System for mapping of 'Aufnahmeanlass', valid codes are: N, G, E, A, V, Z, B, R.");
            }
        }
        aufnahmedatenAdminEntry.setSubject(new PartySelf());
        aufnahmedatenAdminEntry.setLanguage(Language.DE);

        return aufnahmedatenAdminEntry;
    }

    private EntlassungsdatenAdminEntry createEntlassungsdatenAdminEntry(Encounter encounter) {

        EntlassungsdatenAdminEntry entlassungsdatenAdminEntry = new EntlassungsdatenAdminEntry();

        OffsetDateTime endDateTime = OffsetDateTime.from(encounter.getPeriod().getEndElement().getValueAsCalendar().toZonedDateTime());
        entlassungsdatenAdminEntry.setDatumUhrzeitDerEntlassungValue(endDateTime);

        if (encounter.getHospitalization() != null
                && encounter.getHospitalization().getDischargeDisposition() != null) {

            Coding artDerEntlassung = encounter.getHospitalization().getDischargeDisposition().getCoding().get(0);

            if (artDerEntlassung.getSystem().equals(ART_DER_ENTLASSUNG_CODE_SYSTEM)
                    && StationaererVersorgungsfallDefiningCodeMaps.getArtDerEntlassungMap().containsKey(artDerEntlassung.getCode())) {

                entlassungsdatenAdminEntry.setArtDerEntlassungDefiningCode(StationaererVersorgungsfallDefiningCodeMaps.getArtDerEntlassungMap().get(artDerEntlassung.getCode()));
            } else {
                throw new IllegalStateException(INVALID_CODE + artDerEntlassung.getCode() +
                        " or Code System for mapping of 'art der Entlassung'.");
            }
        }

        entlassungsdatenAdminEntry.setSubject(new PartySelf());
        entlassungsdatenAdminEntry.setLanguage(Language.DE);

        return entlassungsdatenAdminEntry;
    }
}
