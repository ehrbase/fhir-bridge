package org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.personendaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.PersonendatenAdminEntry;
import org.hl7.fhir.r4.model.Patient;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        mapDataOnBirth(resource).ifPresent(personData::setDatenZurGeburt);
        return personData;
    }

    private Optional<DatenZurGeburtCluster> mapDataOnBirth(Patient fhirPatient) {
        if (fhirPatient.hasBirthDate()) {
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            LocalDate birthDate = fhirPatient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate();
            datenZurGeburtCluster.setGeburtsdatumValue(LocalDate.of(birthDate.getYear(), 1, 1));
            return Optional.of(datenZurGeburtCluster);
        } else {
            return Optional.empty();
        }
    }


}
