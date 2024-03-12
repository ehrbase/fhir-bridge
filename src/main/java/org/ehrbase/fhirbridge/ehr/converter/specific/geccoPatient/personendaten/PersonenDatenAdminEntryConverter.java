package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient.personendaten;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AngabenZumTodCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EinzelheitenDerKommunikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonennameCluster;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Patient;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    private final String ethnischerHintergrundExtensionUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group";

    @Override
    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        mapDataOnBirth(resource).ifPresent(personData::setDatenZurGeburt);
        mapEthnischerHintergrund(resource).ifPresent(personData::setEthnischerHintergrund);
        mapAngabenZumTod(resource, personData);
        setPersonenName(resource, personData);
        setEinzelheitenZurKommunikation(resource, personData);
        return personData;
    }

    private void setEinzelheitenZurKommunikation(Patient resource, PersonendatenAdminEntry personData) {
        List<EinzelheitenDerKommunikationCluster> list = new EinzelheitenDerKommunikationConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setEinzelheitenDerKommunikation(list);
        }
    }

    private void setPersonenName(Patient resource, PersonendatenAdminEntry personData) {
        List<PersonennameCluster> list = new PersonenNameConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setPersonenname(list);
        }
    }

    private void mapAngabenZumTod(Patient resource, PersonendatenAdminEntry personData) {
        AngabenZumTodCluster angabenZumTodCluster = new AngabenZumTodCluster();
        if (resource.hasDeceased()) {
            if (resource.hasDeceasedBooleanType()) {
                personData.setVerstorbenValue(true);
            }
            if (resource.hasDeceasedDateTimeType()) {
                personData.setVerstorbenValue(true);
                angabenZumTodCluster.setSterbedatumValue(resource.getDeceasedDateTimeType().getValueAsCalendar().toZonedDateTime());
            }
        }
    }

    private Optional<List<EthnischerHintergrundCluster>> mapEthnischerHintergrund(Patient patient) {
        if (patient.hasExtension(ethnischerHintergrundExtensionUrl) && patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).hasValue()) {
            if (((Coding) patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).getValue()).hasCode()) {
                return convertEthnischerHintergrund(patient);
            }
        }
        return Optional.empty();
    }

    private Optional<List<EthnischerHintergrundCluster>> convertEthnischerHintergrund(Patient patient) {
        List<EthnischerHintergrundCluster> ethnischerHintergrundClusterList = new ArrayList<>();
        Coding coding = (Coding) patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).getValue();
        EthnischerHintergrundCluster ethnischerHintergrundCluster = new EthnischerHintergrundCluster();
        DvCodedTextParser.getInstance().parseFHIRCoding(coding)
                .ifPresent(ethnischerHintergrundCluster::setEthnischerHintergrund);
        ethnischerHintergrundClusterList.add(ethnischerHintergrundCluster);
        return Optional.of(ethnischerHintergrundClusterList);
    }

    private Optional<DatenZurGeburtCluster> mapDataOnBirth(Patient fhirPatient) {
        if (fhirPatient.hasBirthDate()) {
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            datenZurGeburtCluster.setGeburtsdatumValue(fhirPatient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
            mapMehrereGeburten(fhirPatient).ifPresent(datenZurGeburtCluster::setKodierungFuerMehrlingsgeburtenValue);
            return Optional.of(datenZurGeburtCluster);
        } else {
            return Optional.empty();
        }
    }

    private Optional<String> mapMehrereGeburten(Patient fhirPatient) {
        if (fhirPatient.hasMultipleBirth()) {
            if (fhirPatient.hasMultipleBirthBooleanType()) {
                return Optional.of(fhirPatient.getMultipleBirthBooleanType().getValue().toString());
            } else {
                return Optional.of(fhirPatient.getMultipleBirthIntegerType().getValue().toString());
            }
        } else {
            return Optional.empty();
        }
    }

}
