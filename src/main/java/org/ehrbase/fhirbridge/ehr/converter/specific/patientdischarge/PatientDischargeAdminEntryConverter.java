package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsartAdminEntry;
import org.hl7.fhir.r4.model.Observation;


public class PatientDischargeAdminEntryConverter extends EntryEntityConverter<Observation, EntlassungsartAdminEntry> {

    @Override
    protected EntlassungsartAdminEntry convertInternal(Observation observation) {
        EntlassungsartAdminEntry adminEntry = new EntlassungsartAdminEntry();
        convertArtDerEntlassung(observation, adminEntry);
        return adminEntry;
    }

    private void convertArtDerEntlassung(Observation observation, EntlassungsartAdminEntry entlassungsartAdminEntry) {
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(observation.getValueCodeableConcept().getCoding().get(0))
                .ifPresent(entlassungsartAdminEntry::setArtDerEntlassung);
    }
}