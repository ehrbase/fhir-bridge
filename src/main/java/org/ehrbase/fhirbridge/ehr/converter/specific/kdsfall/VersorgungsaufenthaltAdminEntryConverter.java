package org.ehrbase.fhirbridge.ehr.converter.specific.kdsfall;

import org.ehrbase.fhirbridge.ehr.converter.generic.EncounterToAdminEntryConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.VersorgungsaufenthaltAdminEntry;
import org.hl7.fhir.r4.model.Encounter;

import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class VersorgungsaufenthaltAdminEntryConverter extends EncounterToAdminEntryConverter<VersorgungsaufenthaltAdminEntry> {
    @Override
    protected VersorgungsaufenthaltAdminEntry convertInternal(Encounter encounter) {
        VersorgungsaufenthaltAdminEntry versorgungsaufenthaltAdminEntry = new VersorgungsaufenthaltAdminEntry();
        mapBeginn(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setBeginnValue);
        mapEnde(encounter).ifPresent(versorgungsaufenthaltAdminEntry::setEndeValue);
        return versorgungsaufenthaltAdminEntry;
    }

    private Optional<TemporalAccessor> mapEnde(Encounter encounter) { //Exceptions in Timeconversion should not be moved to abstract converter
        if (encounter.hasPeriod()) {
            return TimeConverter.convertEncounterEndTime(encounter);
        }
        return Optional.empty();
    }

    private Optional<TemporalAccessor> mapBeginn(Encounter encounter) { //Exceptions should in Timeconversion not be moved to abstract converter
        if (encounter.hasPeriod()) {
            return Optional.of(TimeConverter.convertEncounterTime(encounter));
        }
        return Optional.empty();
    }
}
