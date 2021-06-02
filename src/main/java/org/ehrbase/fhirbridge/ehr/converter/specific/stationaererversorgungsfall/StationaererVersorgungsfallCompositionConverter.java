package org.ehrbase.fhirbridge.ehr.converter.specific.stationaererversorgungsfall;

import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.StationaererVersorgungsfallComposition;
import org.ehrbase.fhirbridge.fhir.support.KontaktebeneDefiningCode;
import org.hl7.fhir.r4.model.Encounter;
import org.springframework.lang.NonNull;

public class StationaererVersorgungsfallCompositionConverter extends EncounterToCompositionConverter<StationaererVersorgungsfallComposition> {

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

        retVal.setAufnahmedaten(new AufnahmedatenAdminEntryConverter().convert(encounter));

        if (encounter.getPeriod().hasEndElement()) {

            retVal.setEntlassungsdaten(new EntlassungsdatenAdminEntryConverter().convert(encounter));
        }

        return retVal;
    }

}
