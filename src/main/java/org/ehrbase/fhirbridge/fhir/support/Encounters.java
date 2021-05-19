package org.ehrbase.fhirbridge.fhir.support;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.KONTAKT_EBENE;

public class Encounters {

    private Encounters() {
        throw new IllegalStateException("Utility class");
    }

    public static Profile getProfileByKontaktEbene(Encounter encounter) {

        if (encounter.getType() != null && encounter.getType().size() > 0 ) {

            Coding coding = encounter.getType().get(0).getCoding().get(0);

            if (coding.getSystem().equals(KONTAKT_EBENE.getUrl())) {
                if (coding.getCode().equals(KontaktebeneDefiningCode.ABTEILUNGS_KONTAKT.getCode())
                || coding.getCode().equals(KontaktebeneDefiningCode.VERSORGUNGS_STELLEN_KONTAKT.getCode())) {

                     return Profile.PATIENTEN_AUFENTHALT;
                }
            } else {
                throw new IllegalStateException("Invalid Code system " + coding.getSystem() +
                        " valid code system: " + KONTAKT_EBENE.getUrl());
            }
        }

        return Profile.STATIONAERER_VERSORGUNGSFALL;
    }
}
