package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FhirObservationBloodPressureOpenehrBloodPressure {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationBloodPressureOpenehrBloodPressure.class);

    private FhirObservationBloodPressureOpenehrBloodPressure() {
    }

    public static BlutdruckComposition map(Observation observation) {

        BlutdruckComposition bloodPressureComposition = new BlutdruckComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        bloodPressureComposition.setFeederAudit(fa);


        BlutdruckObservation bloodPressure = new BlutdruckObservation();

        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        try {
            // set systolic BP
            double systolicBPValue = observation.getComponent().get(0).getValueQuantity().getValue().doubleValue();
            String systolicBPUnit = observation.getComponent().get(0).getValueQuantity().getCode(); //mmHg, mm[Hg]

            bloodPressure.setSystolischMagnitude(systolicBPValue);
            bloodPressure.setSystolischUnits(systolicBPUnit);

            // set diastolic BP
            double diastolicBPValue = observation.getComponent().get(1).getValueQuantity().getValue().doubleValue();
            String diastolicBPUnit = observation.getComponent().get(1).getValueQuantity().getCode();

            bloodPressure.setDiastolischMagnitude(diastolicBPValue);
            bloodPressure.setDiastolischUnits(diastolicBPUnit);


        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        bloodPressure.setSubject(new PartySelf());
        bloodPressure.setLanguage(Language.DE); // FIXME: we need to grab the language from the template

        bloodPressure.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        bloodPressure.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        bloodPressureComposition.setBlutdruck(bloodPressure);

        // ======================================================================================
        // Required fields by API
        bloodPressureComposition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        bloodPressureComposition.setLocation("test"); // FIXME: Location abfangen?
        bloodPressureComposition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        bloodPressureComposition.setTerritory(Territory.DE);
        bloodPressureComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);

        bloodPressureComposition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        bloodPressureComposition.setComposer(new PartySelf());

        return bloodPressureComposition;

    }

}
