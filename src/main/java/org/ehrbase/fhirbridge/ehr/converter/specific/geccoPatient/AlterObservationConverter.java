package org.ehrbase.fhirbridge.ehr.converter.specific.geccoPatient;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AlterObservation;

import org.hl7.fhir.r4.model.Age;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Period;
import java.time.temporal.TemporalAccessor;

public class AlterObservationConverter extends EntryEntityConverter<Patient, AlterObservation> {
    private static final Logger LOG = LoggerFactory.getLogger(AlterObservationConverter.class);

    @Override
    protected AlterObservation convertInternal(Patient resource) {
        AlterObservation ageObservation = new AlterObservation();
        Extension extensionAge = resource.getExtensionByUrl("https://www.netzwerk-universitaetsmedizin.de/fhir/StructureDefinition/age");
        TemporalAccessor time = TimeConverter.convertAgeExtensionTime(extensionAge); //should be sth. generic in TimeConverter?
        ageObservation.setOriginValue(time);
        ageObservation.setTimeValue(time);
        setAge(extensionAge, ageObservation);
        return ageObservation;
    }

    private void setAge(Extension extensionAge, AlterObservation ageObservation) { //TODO
        if (extensionAge.getExtensionByUrl("age").hasValue()) {
           convertAgeValue(ageObservation, (Age) extensionAge.getExtensionByUrl("age").getValue());
        } else {
            LOG.warn("Patient resource contains age in another format then UCUM, therefore no age is mapped");
            ageObservation.setAlterNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }

    }

    private void convertAgeValue(AlterObservation ageObservation, Age ageValue) {
        if (ageValue.hasValue() && ageValue.getCode().equals("a")) {
            ageObservation.setAlterValue(Period.ofYears(ageValue.getValue().intValue()));
        } else if (ageValue.hasValue() && ageValue.getCode().equals("mo")) {
            ageObservation.setAlterValue(Period.ofMonths(ageValue.getValue().intValue()));
        } else if (ageValue.hasValue() && ageValue.getCode().equals("wk")) {
            ageObservation.setAlterValue(Period.ofWeeks(ageValue.getValue().intValue()));
        } else if (ageValue.hasValue() && ageValue.getCode().equals("d")) {
            ageObservation.setAlterValue(Period.ofDays(ageValue.getValue().intValue()));
        } else {
            LOG.warn("Patient resource contains age not in days, weeks, months or years, therefore no age is mapped");
            ageObservation.setAlterNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }


}

