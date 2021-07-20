package org.ehrbase.fhirbridge.ehr.converter.specific.patientenaufenthalt;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.PatientenaufenthaltComposition;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.AbteilungsfallCluster;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.VersorgungsfallCluster;
import org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition.VersorgungstellenkontaktCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Identifier;
import org.springframework.lang.NonNull;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.KONTAKT_EBENE;


public class PatientenAufenthaltCompositionConverter extends EncounterToCompositionConverter<PatientenaufenthaltComposition> {

    @Override
    public PatientenaufenthaltComposition convertInternal(@NonNull Encounter encounter) {

        PatientenaufenthaltComposition patientenaufenthaltComposition = new PatientenaufenthaltComposition();
        mapFallKennung(patientenaufenthaltComposition, encounter);
        patientenaufenthaltComposition.setVersorgungsaufenthalt(new VersorgungsaufenthaltAdminEntryConverter().convert(encounter));

        return patientenaufenthaltComposition;
    }

    private void mapFallKennung(PatientenaufenthaltComposition patientenaufenthaltComposition, Encounter encounter) {
        if(encounter.hasType() && encounter.hasIdentifier()){
            for(CodeableConcept codeableConcept : encounter.getType()){
                iterateFallKennungAndConvert(codeableConcept, patientenaufenthaltComposition, encounter);
            }
        }

    }

    private void iterateFallKennungAndConvert(CodeableConcept codeableConcept, PatientenaufenthaltComposition patientenaufenthaltComposition, Encounter encounter) {
        for(Coding coding: codeableConcept.getCoding()){
            if(coding.hasSystem() && coding.getSystem().equals(KONTAKT_EBENE.getUrl())){
                convertFallKennung(patientenaufenthaltComposition, coding, encounter);
            }else{
                throw new ConversionException("Invalid Code system " + coding.getSystem() +
                        " valid code system: " + KONTAKT_EBENE.getUrl());
            }
        }
    }

    private void convertFallKennung(PatientenaufenthaltComposition patientenaufenthaltComposition, Coding coding, Encounter encounter) {
        Identifier encounterIdentifier = encounter.getIdentifier().get(0);
        String typeCode = coding.getCode();
        switch (typeCode) {
            case "einrichtungskontakt": {
                VersorgungsfallCluster versorgungsfallCluster = new VersorgungsfallCluster();
                versorgungsfallCluster.setZugehoerigerVersorgungsfallKennungValue(encounterIdentifier.getValue());
                patientenaufenthaltComposition.setVersorgungsfall(versorgungsfallCluster);
                break;
            }
            case "abteilungskontakt": {
                AbteilungsfallCluster abteilungsfallCluster = new AbteilungsfallCluster();
                abteilungsfallCluster.setZugehoerigerAbteilungsfallKennungValue(encounterIdentifier.getValue());
                patientenaufenthaltComposition.setAbteilungsfall(abteilungsfallCluster);
                break;
            }
            case "versorgungsstellenkontakt": {
                VersorgungstellenkontaktCluster versorgungstellenkontaktCluster = new VersorgungstellenkontaktCluster();
                versorgungstellenkontaktCluster.setZugehoerigerVersorgungsstellenkontaktKennungValue(encounterIdentifier.getValue());
                patientenaufenthaltComposition.setVersorgungstellenkontakt(versorgungstellenkontaktCluster);
                break;
            }
            default: {
                throw new ConversionException("Invalid Code " + typeCode +
                        " or Code System as 'Kontaktebene', valid codes are einrichtungskontakt, abteilungskontakt, versorgungsstellenkontakt.");
            }
        }
    }


}
