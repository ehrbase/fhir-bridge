package org.ehrbase.fhirbridge.ehr.converter.specific.kdspatient.personendaten;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.NameCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.PersonCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.PersonendatenAdminEntry;
import org.hl7.fhir.r4.model.Patient;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        mapDataOnBirth(resource).ifPresent(personData::setDatenZurGeburt);
        PersonCluster personCluster = new PersonCluster();
        personCluster.setVersicherungsnummerPkvNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE); // since Person has to be 1..1 create Null
        personCluster.setVersichertenIdGkvNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE); // since Person has to be 1..1 create Null
        personData.setPerson(personCluster);
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
