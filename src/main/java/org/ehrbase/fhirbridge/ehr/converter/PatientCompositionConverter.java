package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientCompositionConverter implements CompositionConverter<GECCOPersonendatenComposition, Patient> {

    private static final Logger LOG = LoggerFactory.getLogger(BloodPressureCompositionConverter.class);

    @Override
    public Patient fromComposition(GECCOPersonendatenComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public GECCOPersonendatenComposition toComposition(Patient fhir_patient) {
        if (fhir_patient == null) {
            return null;
        }

        //create composition and contained archetype objects
        GECCOPersonendatenComposition composition = new GECCOPersonendatenComposition();
        PersonendatenAdminEntry person_data = new PersonendatenAdminEntry();

        // don't set feeder audit - plans to generalize it exist (mapping doesn't need to deal with it and currently if throws a null pointer exception in mapping test)
        //FeederAudit fa = CommonData.constructFeederAudit(fhir_patient);
        //composition.setFeederAudit(fa);

        /* NOTE There are more fields included in the FHIR resource (but not marked as "support mandatory")
        which are also considered in the template. But the example does only include age, date of birth and ethnic group.*/

        //assemble composition
        composition.setAlter(get_age_from_fhir(fhir_patient));
        person_data.setDatenZurGeburt(get_data_on_birth(fhir_patient));
        person_data.setEthnischerHintergrund(get_ethnic_background_data(fhir_patient));
        person_data.setSubject(new PartySelf());
        person_data.setLanguage(Language.DE);

        composition.setPersonendaten(person_data);

        //NOTE not sure if this is the best value for composition startTime since it refers to the documentation of the age, but its the only info on time of documentation...
        composition.setStartTimeValue(composition.getAlter().getTimeValue());

        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setComposer(new PartySelf()); //FIXME: sensible value

        return composition;
    }

    private List<EthnischerHintergrundCluster> get_ethnic_background_data(Patient fhir_patient) {
        List<EthnischerHintergrundCluster> items = new ArrayList<>();
        try {
            Extension extension_ethnic_group = fhir_patient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group");
            Coding ethnic_group = (Coding) extension_ethnic_group.getValue();
            EthnischerHintergrundCluster ec = new EthnischerHintergrundCluster();
            ec.setEthnischerHintergrundDefiningCode(EthnischerHintergrundDefiningCode.get_by_SNOMED_code(ethnic_group.getCode()));
            items.add(ec);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return items;
    }

    private AlterObservation get_age_from_fhir(Patient fhir_patient) {
        AlterObservation age = new AlterObservation();
        try {
            Extension extension_age = fhir_patient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age");
            //age - date_time_of_documentation
            DateTimeType date_time_of_documentation_dt = (DateTimeType) extension_age.getExtensionByUrl("dateTimeOfDocumentation").getValue();
            ZonedDateTime date_time_of_documentation = date_time_of_documentation_dt.getValueAsCalendar().toZonedDateTime();
            //age - value
            Age age_value = (Age) extension_age.getExtensionByUrl("age").getValue();
            //age - Ereigniszeitpunkt
            age.setOriginValue(date_time_of_documentation);
            age.setTimeValue(date_time_of_documentation);
            //age - Alter (ISO8601 duration e.g. P67Y)
            age.setAlterValue(Period.ofYears(age_value.getValue().intValue()));
            age.setSubject(new PartySelf());
            age.setLanguage(Language.DE);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return age;
    }

    private DatenZurGeburtCluster get_data_on_birth(Patient fhir_patient) {
        DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
        try {
            //date of birth
            datenZurGeburtCluster.setGeburtsdatumValue(fhir_patient.getBirthDate().toInstant().atZone(ZoneId.of("Europe/Berlin")).toLocalDate());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        return datenZurGeburtCluster;
    }
}
