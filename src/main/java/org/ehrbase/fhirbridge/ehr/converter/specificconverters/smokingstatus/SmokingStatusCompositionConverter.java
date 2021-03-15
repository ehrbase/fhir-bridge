package org.ehrbase.fhirbridge.ehr.converter.specificconverters.smokingstatus;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.GregorianCalendar;

public class SmokingStatusCompositionConverter extends CompositionConverter<Observation, RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convertInternal(@NonNull Observation resource) {
        //create composition and observation objects
        RaucherstatusComposition composition = new RaucherstatusComposition();

        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();

        //map values of interest from FHIR observation
        GregorianCalendar effectiveDateTime = resource.getEffectiveDateTimeType().getValueAsCalendar();
        if (effectiveDateTime != null) {
            composition.setStartTimeValue(effectiveDateTime.toZonedDateTime());
        }

        try {
            Coding codin = resource.getValueCodeableConcept().getCoding().get(0);

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
                    throw new ConversionException("Unexpected value: " + codin.getCode());
            }
            evaluation.setRauchverhalten(rauchverhaltenDefiningcode.toDvCodedText());

            evaluation.setLanguage(Language.DE);
            evaluation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }

        composition.setRaucherstatus(evaluation);

        return composition;
    }
}
