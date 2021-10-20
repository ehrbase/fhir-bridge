package org.ehrbase.fhirbridge.ehr.converter.specific.smokingstatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.EntryEntityConverter;
import org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition.RaucherstatusEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class RaucherstatusEvaluationConverter extends EntryEntityConverter<Observation, RaucherstatusEvaluation> {

    @Override
    protected RaucherstatusEvaluation convertInternal(Observation observation) {
        RaucherstatusEvaluation raucherstatusEvaluation = new RaucherstatusEvaluation();
        if (observation.hasValueCodeableConcept() && observation.getValueCodeableConcept().hasCoding()) {
            for (Coding coding : observation.getValueCodeableConcept().getCoding()) {
                DvCodedTextParser.getInstance().parseFHIRCoding(coding)
                        .ifPresent(raucherstatusEvaluation::setRauchverhalten);
            }
            return raucherstatusEvaluation;
        } else {
            raucherstatusEvaluation.setRauchverhaltenNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return raucherstatusEvaluation;
    }

}
