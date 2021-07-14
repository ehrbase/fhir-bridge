package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import org.checkerframework.checker.nullness.Opt;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.NamensartDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonennameCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonennameWeitererVornameElement;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PersonenNameConverter {

    protected List<PersonennameCluster> convert(Patient resource) {
        List<PersonennameCluster> personennameClusterList = new ArrayList<>();
        if (resource.hasName()) {
            for (HumanName name : resource.getName()) {
                convertPersonnameCluster(name).ifPresent(personennameClusterList::add);
            }
        }
        return personennameClusterList;
    }

    private Optional<PersonennameCluster> convertPersonnameCluster(HumanName name) {
        PersonennameCluster personennameCluster = new PersonennameCluster();
        mapNamensart(name).ifPresent(personennameCluster::setNamensartDefiningCode);
        mapNameUnstructured(name).ifPresent(personennameCluster::setNameUnstrukturiertValue);
        mapTitel(name).ifPresent(personennameCluster::setTitelValue);
        mapVorname(name).ifPresent(personennameCluster::setVornameValue);
        mapWeitereVornamen(name).ifPresent(personennameCluster::setWeitererVorname);
        mapNachname(name).ifPresent(personennameCluster::setNachnameValue);
        mapSuffix(name).ifPresent(personennameCluster::setSuffixValue);
        if(personennameCluster.equals(new PersonennameCluster())){
            return Optional.empty();
        }else{
            return Optional.of(personennameCluster);
        }
    }

    private Optional<String> mapSuffix(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapNachname(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<List<PersonennameWeitererVornameElement>> mapWeitereVornamen(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapVorname(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapTitel(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapNameUnstructured(HumanName name) {
        if (name.hasText()) {
            return Optional.of(name.getText());
        } else {
            return Optional.empty();
        }
    }

    private Optional<NamensartDefiningCode> mapNamensart(HumanName name) {
        if (name.hasUse() && name.getUseElement().hasCode()) {
            switch (name.getUseElement().getCode()) {
                case "usual":
                    return Optional.of(NamensartDefiningCode.GEBURTSNAME);
                case "official":
                    return Optional.of(NamensartDefiningCode.REGISTRIERTER_NAME);
                case "temp":
                    return Optional.of(NamensartDefiningCode.AKA);
                case "nickname":
                    return Optional.of(NamensartDefiningCode.ALIAS);
                case "anonymous":
                    return Optional.of(NamensartDefiningCode.BERICHTSNAME);
                case "old":
                    return Optional.of(NamensartDefiningCode.FRUEHERER_NAME);
                case "maiden":
                    return Optional.of(NamensartDefiningCode.MAEDCHENNAME);
            }
        }
        return Optional.empty();
    }

}
