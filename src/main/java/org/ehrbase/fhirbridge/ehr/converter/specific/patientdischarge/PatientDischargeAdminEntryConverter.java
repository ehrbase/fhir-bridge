package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.EntlassungsartAdminEntry;
import org.hl7.fhir.r4.model.Observation;


public class PatientDischargeAdminEntryConverter extends EntryEntityConverter<Observation, EntlassungsartAdminEntry> {

    @Override
    protected EntlassungsartAdminEntry convertInternal(Observation resource) {
        EntlassungsartAdminEntry adminEntry = new EntlassungsartAdminEntry();
        convertArtDerEntlassung(resource, adminEntry);
        return adminEntry;
    }

    private void convertArtDerEntlassung(Observation resource, EntlassungsartAdminEntry adminEntry) {
        adminEntry.setArtDerEntlassung(
                CodingToDvCodedTextConverter.getInstance()
                        .convert(resource.getValueCodeableConcept().getCoding().get(0)));
    }
}