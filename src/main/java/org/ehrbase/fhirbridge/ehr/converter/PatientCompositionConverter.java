package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.GECCOPersonendatenComposition;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
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

public class PatientCompositionConverter implements CompositionConverter<GECCOPersonendatenComposition, Patient> {

    @Override
    public Patient fromComposition(GECCOPersonendatenComposition composition) {
        return null;
    }

    @Override
    public GECCOPersonendatenComposition toComposition(Patient fhirPatient) {
        if (fhirPatient == null) {
            return null;
        }
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        PersonendatenAdminEntry personData = new PersonendatenAdminEntry();
        composition.setAlter(getAgeFromFhir(fhirPatient));
        personData.setDatenZurGeburt(getDataOnBirth(fhirPatient));
        personData.setEthnischerHintergrund(getEthnicBackgroundData(fhirPatient));
        personData.setSubject(new PartySelf());
        personData.setLanguage(Language.DE);
        composition.setPersonendaten(personData);
        composition.setStartTimeValue(composition.getAlter().getTimeValue());
        composition = setRequiredFields(composition);
        return composition;
    }

    private GECCOPersonendatenComposition setRequiredFields(GECCOPersonendatenComposition composition) {
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf());
        return composition;
    }

    private List<EthnischerHintergrundCluster> getEthnicBackgroundData(Patient fhirPatient) {
        List<EthnischerHintergrundCluster> items = new ArrayList<>();
        try {
            Extension extensionEthnicGroup = fhirPatient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group");
            Coding ethnicGroup = (Coding) extensionEthnicGroup.getValue();
            EthnischerHintergrundCluster ec = new EthnischerHintergrundCluster();
            ec.setEthnischerHintergrundDefiningCode(EthnischerHintergrundDefiningCode.get_by_SNOMED_code(ethnicGroup.getCode()));
            items.add(ec);
        } catch (NullPointerException e) {
            throw new UnprocessableEntityException("Getting ethnicGroup failed: " + e.getMessage());
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
            throw new UnprocessableEntityException("Getting datenZurGeburt failed: " + e.getMessage());
        }
        return datenZurGeburtCluster;
    }
}
