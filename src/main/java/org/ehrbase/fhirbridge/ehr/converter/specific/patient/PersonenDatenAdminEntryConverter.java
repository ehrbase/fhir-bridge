package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    @SuppressWarnings("FieldCanBeLocal")
    private final String ethnischerHintergrundExtensionUrl = "https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group";

    @Override
    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        mapDataOnBirth(resource).ifPresent(personData::setDatenZurGeburt);
        mapEthnischerHintergrund(resource).ifPresent(personData::setEthnischerHintergrund);
        List<PersonennameCluster> list = new PersonenNameConverter().convert(resource);
        if (!list.isEmpty()) {
            personData.setPersonenname(list);
        }
        return personData;
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
        Coding codig = (Coding) patient.getExtensionByUrl(ethnischerHintergrundExtensionUrl).getValue();
        EthnischerHintergrundCluster ethnischerHintergrundCluster = new EthnischerHintergrundCluster();
        if (EthnischerHintergrundDefiningCode.getBySNOMEDCode(codig.getCode()) != null) {
            ethnischerHintergrundCluster.setEthnischerHintergrundDefiningCode(EthnischerHintergrundDefiningCode.getBySNOMEDCode(codig.getCode()));
            ethnischerHintergrundClusterList.add(ethnischerHintergrundCluster);
            return Optional.of(ethnischerHintergrundClusterList);
        } else {
            throw new ConversionException("The SNOMED code is not supported for this entry");
        }
    }

    private Optional<DatenZurGeburtCluster> mapDataOnBirth(Patient fhirPatient) {
        if (fhirPatient.hasBirthDate()) {
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            datenZurGeburtCluster.setGeburtsdatumValue(fhirPatient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
            return Optional.of(datenZurGeburtCluster);
        } else {
            return Optional.empty();
        }
    }
}
