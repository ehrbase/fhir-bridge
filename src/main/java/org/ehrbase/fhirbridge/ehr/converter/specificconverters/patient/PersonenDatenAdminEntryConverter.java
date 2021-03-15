package org.ehrbase.fhirbridge.ehr.converter.specificconverters.patient;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PersonenDatenAdminEntryConverter extends EntryEntityConverter<Patient, PersonendatenAdminEntry> {

    @Override
    protected PersonendatenAdminEntry convertInternal(Patient resource) {
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        personData.setDatenZurGeburt(getDataOnBirth(resource));
        personData.setEthnischerHintergrund(getEthnicBackgroundData(resource));
        return personData;
    }

    private List<EthnischerHintergrundCluster> getEthnicBackgroundData(Patient patient) {
        List<EthnischerHintergrundCluster> items = new ArrayList<>();
        try {
            Extension extensionEthnicGroup = patient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group");
            Coding ethnicGroup = (Coding) extensionEthnicGroup.getValue();
            EthnischerHintergrundCluster ec = new EthnischerHintergrundCluster();
            ec.setEthnischerHintergrundDefiningCode(EthnischerHintergrundDefiningCode.getBySNOMEDCode(ethnicGroup.getCode()));
            items.add(ec);
        } catch (NullPointerException e) {
            throw new ConversionException("Getting ethnicGroup failed: " + e.getMessage());
        }
        return items;
    }

    private DatenZurGeburtCluster getDataOnBirth(Patient fhirPatient) {
        DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
        try {
            //date of birth
            datenZurGeburtCluster.setGeburtsdatumValue(fhirPatient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
        } catch (NullPointerException e) {
            throw new ConversionException("Getting datenZurGeburt failed: " + e.getMessage());
        }
        return datenZurGeburtCluster;
    }
}
