package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class RaucherstatusEvaluationConverter extends EntryEntityConverter<Observation, RaucherstatusEvaluation> {
    @Override
    protected RaucherstatusEvaluation convertInternal(Observation resource) {
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
        } catch (Exception e) {
            throw new ConversionException(e.getMessage());
        }
        return evaluation;
    }
}
