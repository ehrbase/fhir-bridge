package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;

import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
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
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;

public class PatientCompositionConverter implements CompositionConverter<GECCOPersonendatenComposition, Patient> {

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
        AlterObservation age = new AlterObservation();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(fhir_patient);
        composition.setFeederAudit(fa);
        
        //map values of interest from FHIR observation
        ZonedDateTime date_time_of_documentation;
        
        try {
            /* NOTE There are more fields included in the FHIR resource (but not marked as "support mandatory")
            which are also considered in the template. But the example does only include age, date of birth and ethnic group.*/
            
            //get clinical values of interest from FHIR profile
            //NOTE not sure if this is the best value for composition startTime since it refers to the documentation of the age, but its the only info on time of documentation...
            //age - date_time_of_documentation
            String date_time_of_documentation_string = fhir_patient.getExtensionByUrl("dateTimeOfDocumentation").getNamedProperty("valueDateTime").toString();
            date_time_of_documentation = new SimpleDateFormat("dd/MM/yyyy").parse(date_time_of_documentation_string).toInstant().atZone(ZoneId.systemDefault());
            //age - value
            int years_int = Integer.parseInt(fhir_patient.getExtensionByUrl("age").getNamedProperty("value").toString());
            //TODO remove just in case other way to extract values from extensions does not work
            //IPrimitiveType<String> date_time_of_documentation_p = (IPrimitiveType<String>) extension_age.getValueAsPrimitive();
            //date_time_of_documentation = new SimpleDateFormat("dd/MM/yyyy").parse(date_time_of_documentation_p.getValue()).toInstant().atZone(ZoneId.systemDefault());
            //birthDate
            Date birth_date = fhir_patient.getBirthDate();
            //ethnic-group
            Extension extension_ethnic_group = fhir_patient.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/ethnic-group");
            String ethnic_group = extension_ethnic_group.getNamedProperty("display").toString();
            
            //set stuff for composition
            //age - Ereigniszeitpunkt
            age.setTimeValue(date_time_of_documentation);
            //age - Alter (ISO8601 duration e.g. P67Y)
            age.setAlterValue(Period.ofYears(years_int));
            //date of birth
            DatenZurGeburtCluster datenZurGeburtCluster = new DatenZurGeburtCluster();
            datenZurGeburtCluster.setGeburtsdatumValue((Temporal) birth_date);
            person_data.setDatenZurGeburt(datenZurGeburtCluster);
            //ethnic-group
            EthnischerHintergrundCluster ec = new EthnischerHintergrundCluster();
            ec.setEthnischerHintergrundDefiningCode(EthnischerHintergrundDefiningCode.valueOf(ethnic_group));
            List<EthnischerHintergrundCluster> items = new ArrayList<>();
            items.add(ec);
            person_data.setEthnischerHintergrund(items);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }
        //assemble composition
        composition.setPersonendaten(person_data);
        composition.setAlter(age);

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningCode(Category.EVENT);
        composition.setStartTimeValue(date_time_of_documentation);
        composition.setComposer(new PartySelf()); //FIXME: sensible value

        return composition;
    }
}
