package org.ehrbase.fhirbridge.ehr.converter.specific.patient.personendaten;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.*;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;

import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdresseConverter {

    public List<AdresseCluster> convert(Patient resource) {
        List<AdresseCluster> adresseClusters = new ArrayList<>();
        if (resource.hasContact()) {
            for (Patient.ContactComponent contact : resource.getContact()) {
                if (contact.hasAddress()) {
                    convertAdresseCluster(contact.getAddress()).ifPresent(adresseClusters::add);
                }
            }
        }
        return adresseClusters;
    }

    private Optional<AdresseCluster> convertAdresseCluster(Address address) {
        AdresseCluster adresseCluster = new AdresseCluster();
        mapVerwendung(address).ifPresent(adresseCluster::setVerwendungDefiningCode);
        mapTypDerAdresse(address).ifPresent(adresseCluster::setTypDerAdresseDefiningCode);
        mapText(address).ifPresent(adresseCluster::setTextValue);
        mapZeile(address).ifPresent(adresseCluster::setZeile);
        mapStadt(address).ifPresent(adresseCluster::setStadtValue);
        mapBezirk(address).ifPresent(adresseCluster::setBezirkValue);
        mapPostleitzahl(address).ifPresent(adresseCluster::setPostleitzahlValue);
        mapLand(address).ifPresent(adresseCluster::setLandValue);
        mapBeginnDerGueltigkeitsdauer(address).ifPresent(adresseCluster::setBeginnDerGueltigkeitsdauerValue);
        mapEndeDerGueltigkeitsdauer(address).ifPresent(adresseCluster::setEndeDerGueltigkeitsdauerValue);
        if (!getJavers().compare(adresseCluster, new AdresseCluster()).hasChanges()) { // not necessary since HAPI does not allow empty arrays. Nevertheless double checks do no harm.
            return Optional.empty();
        } else {
            return Optional.of(adresseCluster);
        }
    }

    private Optional<TemporalAccessor> mapEndeDerGueltigkeitsdauer(Address address) {
        if (address.hasPeriod() && address.getPeriod().hasEnd()) {
            return Optional.of(address.getPeriod().getEndElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    private Optional<TemporalAccessor> mapBeginnDerGueltigkeitsdauer(Address address) {
        if (address.hasPeriod() && address.getPeriod().hasStart()) {
            return Optional.of(address.getPeriod().getStartElement().getValueAsCalendar().toZonedDateTime());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapLand(Address address) {
        if (address.hasCountry()) {
            return Optional.of(address.getCountry());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapPostleitzahl(Address address) {
        if (address.hasPostalCode()) {
            return Optional.of(address.getPostalCode());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapBezirk(Address address) {
        if (address.hasDistrict()) {
            return Optional.of(address.getDistrict());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapStadt(Address address) {
        if (address.hasCity()) {
            return Optional.of(address.getCity());
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<AdresseZeileElement>> mapZeile(Address address) {
        if (address.hasLine()) {
            return convertAdresseZeile(address);
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<AdresseZeileElement>> convertAdresseZeile(Address address) {
        List<AdresseZeileElement> adresseZeileElements = new ArrayList<>();
        for (StringType line : address.getLine()) {
            AdresseZeileElement adresseZeileElement = new AdresseZeileElement();
            adresseZeileElement.setValue(line.getValue());
            adresseZeileElements.add(adresseZeileElement);
        }
        return Optional.of(adresseZeileElements);
    }

    private Optional<String> mapText(Address address) {
        if (address.hasText()) {
            return Optional.of(address.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<TypDerAdresseDefiningCode> mapTypDerAdresse(Address address) {
        if (address.hasType() && address.getTypeElement().hasCode()) {
            switch (address.getTypeElement().getCode()) {
                case "postal":
                    return Optional.of(TypDerAdresseDefiningCode.POSTADRESSEN);
                case "physical":
                    return Optional.of(TypDerAdresseDefiningCode.PHYSISCH);
                case "both":
                    return Optional.of(TypDerAdresseDefiningCode.BEIDES);
                default:
                    throw new ConversionException("The fhir-bridge does not support this code " + address.getTypeElement().getCode() + "please enter a code supported by the FHIR profile");
            }
        } else {
            return Optional.empty();
        }
    }

    private Optional<VerwendungDefiningCode> mapVerwendung(Address address) {
        if (address.hasUse()) {
            switch (address.getUseElement().getCode()) {
                case "home":
                    return Optional.of(VerwendungDefiningCode.HEIMAT);
                case "work":
                    return Optional.of(VerwendungDefiningCode.ARBEIT);
                case "temp":
                    return Optional.of(VerwendungDefiningCode.VORLAEUFIG);
                case "old":
                    return Optional.of(VerwendungDefiningCode.ALT);
                case "billing":
                    return Optional.of(VerwendungDefiningCode.HEIMAT);
                default:
                    throw new ConversionException("The fhir-bridge does not support " + address.getUse() + " please enter either home, work, temp, old or billing as defined by FHIR profile");
            }
        } else {
            return Optional.empty();
        }
    }


    public Javers getJavers() {
        return JaversBuilder.javers()
                .registerValueObject(AdresseCluster.class)
                .build();
    }
}
