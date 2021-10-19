package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.CodingToDvCodedTextConverter;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class RaucherstatusEvaluationConverter extends EntryEntityConverter<Observation, RaucherstatusEvaluation> {
    @Override
    protected RaucherstatusEvaluation convertInternal(Observation resource) {
        RaucherstatusEvaluation evaluation = new RaucherstatusEvaluation();
        if (resource.hasValueCodeableConcept() && resource.getValueCodeableConcept().hasCoding()) {
            for (Coding coding : resource.getValueCodeableConcept().getCoding()) {
                evaluation.setRauchverhalten(CodingToDvCodedTextConverter.getInstance().convert(coding));
            }
            return evaluation;
        } else {
            evaluation.setRauchverhaltenNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return evaluation;
    }

}
