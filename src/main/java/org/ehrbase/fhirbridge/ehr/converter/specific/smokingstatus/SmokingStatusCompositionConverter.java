package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.RaucherstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class SmokingStatusCompositionConverter extends ObservationToCompositionConverter<RaucherstatusComposition> {

    @Override
    public RaucherstatusComposition convertInternal(@NonNull Observation resource) {
        RaucherstatusComposition composition = new RaucherstatusComposition();
        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();

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
            evaluation.setLanguage(resolveLanguageOrDefault(resource));
            evaluation.setSubject(new PartySelf());
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }
        composition.setRaucherstatus(evaluation);

        return composition;
    }
}
