package org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten;

import org.checkerframework.checker.nullness.Opt;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationKontaktdatenCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.KontakttypDefiningCode;
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
        mapKontakttyp(telecom).ifPresent(einzelheitenDerKommunikationKontaktdatenCluster::setKontakttypDefiningCode);
        if(!isEmpty){
            return Optional.of(List.of(einzelheitenDerKommunikationKontaktdatenCluster));
        }else{
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

    private static Optional<KontakttypDefiningCode> mapKontakttyp(ContactPoint telecom) { //TODO clean up this mess after the template is updated
        if (telecom.hasSystem()) {
            switch (telecom.getSystemElement().getCode()) {
                case "phone":
                    isEmpty = false;
                    return Optional.of(KontakttypDefiningCode.TELEFON);
                case "fax":
                    isEmpty = false;
                    return Optional.of(KontakttypDefiningCode.TELEFAX);
                case "pager":
                    isEmpty = false;
                    return Optional.of(KontakttypDefiningCode.PAGER);
                case "sms":
                    isEmpty = false;
                    return Optional.of(KontakttypDefiningCode.MOBILTELEFON);
                case "other":
                    if(isEmpty){
                        LOG.warn("currently other is not yet supported by the bridge this will be fixed asap. For now Telefon will be used as default since the Template requires a value.");
                        isEmpty = false;
                        return Optional.empty();
                    }else{
                        isEmpty = false;
                        return Optional.of(KontakttypDefiningCode.TELEFON);
                    }
                default:
                    throw new ConversionException("Code for telecom.system is not supported");
            }
        }
        if(isEmpty){
            return Optional.empty();
        }else{
            throw new ConversionException("Empty telecom.system, the fhir bridge needs an value here otherwise it cannot map.");
        }
    }

}
