package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.DatenZurGeburtCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.EthnischerHintergrundDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.hl7.fhir.r4.model.Age;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.lang.NonNull;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientCompositionConverter extends CompositionConverter<Patient, GECCOPersonendatenComposition> {

    @Override
    public GECCOPersonendatenComposition convertInternal(@NonNull Patient resource) {
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        composition.setAlter(getAgeFromFhir(resource));
        personData.setDatenZurGeburt(getDataOnBirth(resource));
        personData.setEthnischerHintergrund(getEthnicBackgroundData(resource));
        personData.setSubject(new PartySelf());
        personData.setLanguage(Language.DE);
        composition.setPersonendaten(personData);
        composition.setStartTimeValue(composition.getAlter().getTimeValue());
        return composition;
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

    private AlterObservation getAgeFromFhir(Patient fhirPatient) {
        AlterObservation age = new AlterObservation();
        Extension extensionAge = fhirPatient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age");
        DateTimeType dateTimeOfDocumentationDt = (DateTimeType) extensionAge.getExtensionByUrl("dateTimeOfDocumentation").getValue();
        ZonedDateTime dateTimeOfDocumentation = dateTimeOfDocumentationDt.getValueAsCalendar().toZonedDateTime();
        Age ageValue = (Age) extensionAge.getExtensionByUrl("age").getValue();
        age.setOriginValue(dateTimeOfDocumentation);
        age.setTimeValue(dateTimeOfDocumentation);
        //age - Alter (ISO8601 duration e.g. P67Y)
        age.setAlterValue(Period.ofYears(ageValue.getValue().intValue()));
        age.setSubject(new PartySelf());
        age.setLanguage(Language.DE);

        return age;
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
