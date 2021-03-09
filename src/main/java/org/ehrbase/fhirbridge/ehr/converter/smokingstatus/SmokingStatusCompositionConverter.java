package org.ehrbase.fhirbridge.ehr.converter.smokingstatus;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.GregorianCalendar;

public class SmokingStatusCompositionConverter extends AbstractCompositionConverter<Observation, RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convert(@NonNull Observation observation) {
        //create composition and observation objects
        RaucherstatusComposition result = new RaucherstatusComposition();
        mapCommonAttributes(observation, result);

        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();

        //map values of interest from FHIR observation
        GregorianCalendar effectiveDateTime = observation.getEffectiveDateTimeType().getValueAsCalendar();
        if (effectiveDateTime != null) {
            result.setStartTimeValue(effectiveDateTime.toZonedDateTime());
        }

        try {
            Coding codin = observation.getValueCodeableConcept().getCoding().get(0);

            RauchverhaltenDefiningCode rauchverhaltenDefiningcode;
            switch (codin.getCode()) {
                case "LA18976-3":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.LA189763;
                    break;
                case "LA18978-9":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.LA189789;
                    break;
                case "LA15920-4":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.LA159204;
                    break;
                case "LA18980-5":
                    rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.LA189805;
                    break;
                default:
                    throw new UnprocessableEntityException("Unexpected value: " + codin.getCode());
            }
            evaluation.setRauchverhalten(rauchverhaltenDefiningcode.toDvCodedText());

            evaluation.setLanguage(Language.DE);
            evaluation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        result.setRaucherstatus(evaluation);

        return result;
    }
}
