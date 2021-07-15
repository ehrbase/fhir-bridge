package org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten;

import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationKontaktdatenCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.KontakttypDefiningCode;
import org.hl7.fhir.r4.model.ContactPoint;

import java.util.List;
import java.util.Optional;

public class KontaktDatenConverter {
    public static Optional<List<EinzelheitenDerKommunikationKontaktdatenCluster>> convert(ContactPoint telecom) {
        EinzelheitenDerKommunikationKontaktdatenCluster einzelheitenDerKommunikationKontaktdatenCluster = new EinzelheitenDerKommunikationKontaktdatenCluster();
        mapKontakttyp(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setKontakttypDefiningCode);
        mapUnstrukturierteAdresse(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setUnstrukturierteKontaktadresseValue);
        mapNummer(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setNummerValue);

    }

    private static Optional<KontakttypDefiningCode> mapKontakttyp(ContactPoint telecom) {
        if(telecom.hasSystem()){
            phone | fax | email | pager | url | sms | other
        }
    }

}
