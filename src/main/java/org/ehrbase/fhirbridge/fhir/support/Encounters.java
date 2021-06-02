package org.ehrbase.fhirbridge.fhir.support;

import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Encounter;
import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.KONTAKT_EBENE;

public class Encounters {

    private Encounters() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean isNotEmpty(List list) {

        return list != null && list.size() > 0;
    }

    public static Profile getProfileByKontaktEbene(Encounter encounter) {

        if (isNotEmpty(encounter.getType())) {

            Coding coding = encounter.getType().get(0).getCoding().get(0);

            return getProfileByCoding(coding);
        }

        return Profile.KONTAKT_GESUNDHEIT_EINRICHTUNG;
    }

    private static Profile getProfileByCoding(Coding coding) {

        if (coding.getSystem().equals(KONTAKT_EBENE.getUrl())) {

            if (coding.getCode().equals(KontaktebeneDefiningCode.ABTEILUNGS_KONTAKT.getCode())
                    || coding.getCode().equals(KontaktebeneDefiningCode.VERSORGUNGS_STELLEN_KONTAKT.getCode())) {

                return Profile.KONTAKT_GESUNDHEIT_ABTEILUNG;
            }
        } else {
            throw new UnprocessableEntityException("Invalid Code system " + coding.getSystem() +
                    " valid code system: " + KONTAKT_EBENE.getUrl());
        }

        return Profile.KONTAKT_GESUNDHEIT_EINRICHTUNG;
    }
}
