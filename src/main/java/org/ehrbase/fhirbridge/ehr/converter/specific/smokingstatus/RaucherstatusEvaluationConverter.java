package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RauchverhaltenDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class RaucherstatusEvaluationConverter extends EntryEntityConverter<Observation, RaucherstatusEvaluation> {
    @Override
    protected RaucherstatusEvaluation convertInternal(Observation resource) {
        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();
        if (resource.hasValueCodeableConcept() && resource.getValueCodeableConcept().hasCoding()) {
            for (Coding coding : resource.getValueCodeableConcept().getCoding()) {
                convertRaucherStatusDefiningCode(coding, evaluation);
            }
            return evaluation;
        } else {
            evaluation.setRauchverhaltenNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return evaluation;
    }

    private void convertRaucherStatusDefiningCode(Coding coding, RaucherstatusEvaluation evaluation) {
        RauchverhaltenDefiningCode rauchverhaltenDefiningcode;
        switch (coding.getCode()) {
            case "LA18976-3":
                rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.JA;
                break;
            case "LA18978-9":
                rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.NICHTRAUCHER;
                break;
            case "LA15920-4":
                rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.EHEMALIGER_RAUCHER;
                break;
            case "LA18980-5":
                rauchverhaltenDefiningcode = RauchverhaltenDefiningCode.UNBEKANNT;
                break;
            default:
                throw new ConversionException("Unexpected value: " + coding.getCode());
        }
        evaluation.setRauchverhaltenDefiningCode(rauchverhaltenDefiningcode);
    }
}
