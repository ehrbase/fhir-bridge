package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.personendaten;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationKontaktdatenCluster;
import org.hl7.fhir.r4.model.ContactPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class KontaktDatenConverter {

    private static final Logger LOG = LoggerFactory.getLogger(KontaktDatenConverter.class);

    static boolean isEmpty = true;

    public static Optional<List<EinzelheitenDerKommunikationKontaktdatenCluster>> convert(ContactPoint telecom) {
        EinzelheitenDerKommunikationKontaktdatenCluster einzelheitenDerKommunikationKontaktdatenCluster = new EinzelheitenDerKommunikationKontaktdatenCluster();
        mapNummer(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setNummerValue);
        mapKontakttyp(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setKontakttypValue);
        if (!isEmpty) {
            return Optional.of(List.of(einzelheitenDerKommunikationKontaktdatenCluster));
        } else {
            return Optional.empty();
        }
    }


    private static Optional<String> mapNummer(ContactPoint telecom) {
        if (telecom.hasValue()) {
            isEmpty = false;
            return Optional.of(telecom.getValue());
        } else {
            return Optional.empty();
        }
    }

    private static Optional<String> mapKontakttyp(ContactPoint telecom) {
        if (telecom.hasSystem()) {
            return Optional.of(telecom.getSystemElement().getCode());
        }
        if (isEmpty) {
            return Optional.empty();
        } else {
            throw new ConversionException("Empty telecom.system, the fhir bridge needs an value here otherwise it cannot map.");
        }
    }

}
