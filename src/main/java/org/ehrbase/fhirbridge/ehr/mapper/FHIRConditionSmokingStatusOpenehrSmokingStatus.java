package org.ehrbase.fhirbridge.mapping;

import java.time.ZonedDateTime;

import com.nedap.archie.rm.archetyped.FeederAudit;

import org.ehrbase.fhirbridge.ehr.mapper.CommonData;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RauchverhaltenDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;

import com.nedap.archie.rm.generic.PartySelf;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;

public class FHIRConditionSmokingStatusOpenehrSmokingStatus {
    private FHIRConditionSmokingStatusOpenehrSmokingStatus() {}

    public static RaucherstatusComposition map(Observation fhirObservation) {
    
        //create composition and observation objects
    	RaucherstatusComposition composition = new RaucherstatusComposition();

        // set feeder audit
    	FeederAudit fa = CommonData.constructFeederAudit(fhirObservation);
        composition.setFeederAudit(fa);
       
        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();

        //map values of interest from FHIR observation
        ZonedDateTime effectiveDateTime = null;
        try {
        	effectiveDateTime = fhirObservation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime();

            Coding fhir_Rauchverhalten = fhirObservation.getValueCodeableConcept().getCoding().get(0);
            
            RauchverhaltenDefiningcode openEHR_Rauchverhalten;
            switch (fhir_Rauchverhalten.getCode())
            {
                case "LA18976-3":
                	openEHR_Rauchverhalten = RauchverhaltenDefiningcode.LA189763;
                break;
                case "LA18978-9":
                	openEHR_Rauchverhalten = RauchverhaltenDefiningcode.LA189789;
                break;
                case "LA15920-4":
                	openEHR_Rauchverhalten = RauchverhaltenDefiningcode.LA159204;
                break;
                case "LA18980-5":
                	openEHR_Rauchverhalten = RauchverhaltenDefiningcode.LA189805;
                break;
                default:
                    throw new UnprocessableEntityException("Unexpected value: " + fhir_Rauchverhalten.getCode());
            }
            evaluation.setRauchverhaltenDefiningcode(openEHR_Rauchverhalten);
            
            evaluation.setLanguage(Language.DE);// FIXME: we need to grab the language from the template
            evaluation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        composition.setRaucherstatus(evaluation);

        // Required fields by API
        composition.setLanguage(Language.DE); // FIXME: we need to grab the language from the template
        composition.setLocation("test"); //FIXME: sensible value
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(effectiveDateTime);
        composition.setComposer(new PartySelf()); //FIXME: sensible value

        return composition;
    }
}
