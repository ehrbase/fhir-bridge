package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.personendaten;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationAdresseDvText;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationAdresseDvUri;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationInternetKommunikationCluster;
import org.hl7.fhir.r4.model.ContactPoint;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

public class InternetKommunikationConverter {

    private static boolean isEmpty = true;

    //TODO clean up after Template update

    public static Optional<List<EinzelheitenDerKommunikationInternetKommunikationCluster>> convert(ContactPoint telecom) {
        EinzelheitenDerKommunikationInternetKommunikationCluster einzelheitenDerKommunikationInternetKommunikationCluster = new EinzelheitenDerKommunikationInternetKommunikationCluster();
        mapSender(telecom, einzelheitenDerKommunikationInternetKommunikationCluster);
        mapAdresse(telecom, einzelheitenDerKommunikationInternetKommunikationCluster);
        if(isEmpty){
            return Optional.empty();
        }else{
            return Optional.of(List.of(einzelheitenDerKommunikationInternetKommunikationCluster));
        }
    }

    private static void mapSender(ContactPoint telecom, EinzelheitenDerKommunikationInternetKommunikationCluster einzelheitenDerKommunikationInternetKommunikationCluster) {
        if (telecom.hasSystem()) {
            einzelheitenDerKommunikationInternetKommunikationCluster.setSenderValue(telecom.getSystemElement().getCode());
        } else {
            throw new ConversionException("Currently a Telecom System is required, will be fixed asap.");
        }
    }

    private static void mapAdresse(ContactPoint telecom, EinzelheitenDerKommunikationInternetKommunikationCluster einzelheitenDerKommunikationInternetKommunikationCluster) {
        if (telecom.hasSystem()) {
            if (telecom.getSystemElement().getCode().equals("url")) {
                isEmpty = false;
                EinzelheitenDerKommunikationAdresseDvUri einzelheitenDerKommunikationAdresseDvUri = new EinzelheitenDerKommunikationAdresseDvUri();
                try {
                    einzelheitenDerKommunikationAdresseDvUri.setAdresseValue(new URI(telecom.getValue()));
                } catch (URISyntaxException e) {
                    throw new ConversionException("invalid URI entered at telecom.value  " + e.getMessage());
                }
                einzelheitenDerKommunikationInternetKommunikationCluster.setAdresse(einzelheitenDerKommunikationAdresseDvUri);
            } else {
                isEmpty = false;
                EinzelheitenDerKommunikationAdresseDvText einzelheitenDerKommunikationAdresseDvText = new EinzelheitenDerKommunikationAdresseDvText();
                einzelheitenDerKommunikationAdresseDvText.setAdresseValue(telecom.getValue());
                einzelheitenDerKommunikationInternetKommunikationCluster.setAdresse(einzelheitenDerKommunikationAdresseDvText);
            }
        } else {
            throw new ConversionException("Currently a Telecom System is required, will be fixed asap.");
        }
    }
}
