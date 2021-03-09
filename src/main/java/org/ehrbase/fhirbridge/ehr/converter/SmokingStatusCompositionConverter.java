package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.GregorianCalendar;

public class SmokingStatusCompositionConverter implements CompositionConverter<RaucherstatusComposition, Observation> {

    @Override
    public Observation fromComposition(RaucherstatusComposition composition) {
        // TODO: Implement
        return null;
    }

    @Override
    public RaucherstatusComposition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }

        //create composition and observation objects
        RaucherstatusComposition result = new RaucherstatusComposition();

        // set feeder audit
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);

        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();

        //map values of interest from FHIR observation
        GregorianCalendar effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar();
        if (effectiveDateTime != null) {
            result.setStartTimeValue(effectiveDateTime.toZonedDateTime());
        }

        try {
            Coding codin = observation.getValueCodeableConcept().getCoding().get(0);

            RauchverhaltenDefiningcode rauchverhaltenDefiningcode;
            switch (codin.getCode()) {
                case "LA18976-3":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningcode.LA189763;
                    break;
                case "LA18978-9":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningcode.LA189789;
                    break;
                case "LA15920-4":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningcode.LA159204;
                    break;
                case "LA18980-5":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningcode.LA189805;
                    break;
                default:
                    throw new UnprocessableEntityException("Unexpected value: " + codin.getCode());
            }
            evaluation.setRauchverhaltenDefiningcode(rauchverhaltenDefiningcode);

            evaluation.setLanguage(Language.DE);
            evaluation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setRaucherstatus(evaluation);

        // Required fields by API
        result.setLanguage(Language.DE);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setComposer(new PartySelf());

        return result;
    }

}
