package org.ehrbase.fhirbridge.ehr.converter.specific.patient;

import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AlterObservation;

import org.hl7.fhir.r4.model.Age;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;

import java.time.Period;
import java.time.ZonedDateTime;

public class AlterObservationConverter extends EntryEntityConverter<Patient, AlterObservation> {

    @Override
    protected AlterObservation convertInternal(Patient resource) {
        AlterObservation age = new AlterObservation();
        Extension extensionAge = resource.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age");
        DateTimeType dateTimeOfDocumentationDt = (DateTimeType) extensionAge.getExtensionByUrl("dateTimeOfDocumentation").getValue();
        ZonedDateTime dateTimeOfDocumentation = dateTimeOfDocumentationDt.getValueAsCalendar().toZonedDateTime();
        Age ageValue = (Age) extensionAge.getExtensionByUrl("age").getValue();
        //TODO refactor
        age.setOriginValue(dateTimeOfDocumentation);
        age.setTimeValue(dateTimeOfDocumentation);
        //age - Alter (ISO8601 duration e.g. P67Y)
        age.setAlterValue(Period.ofYears(ageValue.getValue().intValue()));
        return age;
    }
}

