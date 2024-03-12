package org.ehrbase.fhirbridge.ehr.converter.specific.kdsfall;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.SonstigerVersorgungsfallComposition;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.hl7.fhir.r4.model.Encounter;

import java.util.List;

public class SonstigerPatientenAufenthaltConverter extends EncounterToCompositionConverter<SonstigerVersorgungsfallComposition> {

    @Override
    protected SonstigerVersorgungsfallComposition convertInternal(Encounter encounter) {
        SonstigerVersorgungsfallComposition sonstigerVersorgungsfallComposition = new SonstigerVersorgungsfallComposition();
        sonstigerVersorgungsfallComposition.setFallKennungValue(encounter.getIdentifier().get(0).getValue());
        sonstigerVersorgungsfallComposition.setFachlicheOrganisationseinheit(mapOrgaeinheit(encounter));
        sonstigerVersorgungsfallComposition.setVersorgungsaufenthalt(new VersorgungsaufenthaltAdminEntryConverter().convert(encounter));
        return sonstigerVersorgungsfallComposition;
    }

    private List<FachlicheOrganisationseinheitCluster> mapOrgaeinheit(Encounter encounter) {
        FachlicheOrganisationseinheitCluster fachlicheOrganisationseinheitCluster = new FachlicheOrganisationseinheitCluster();
        DvCodedTextParser.getInstance().parseFHIRCoding(encounter.getServiceType().getCoding().get(0)).ifPresent(fachlicheOrganisationseinheitCluster::setOrganisationsschluessel);
        return List.of(fachlicheOrganisationseinheitCluster);
    }


}
