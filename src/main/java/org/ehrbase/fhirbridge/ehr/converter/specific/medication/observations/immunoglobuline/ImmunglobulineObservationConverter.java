package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations.immunoglobuline;

import org.ehrbase.fhirbridge.ehr.converter.generic.MedicationStatementToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.List;

public class ImmunglobulineObservationConverter extends MedicationStatementToObservationConverter<ImmunglobulineObservation> {
    @Override
    protected ImmunglobulineObservation convertInternal(MedicationStatement resource) {
        ImmunglobulineObservation immunglobulineObservation = new ImmunglobulineObservation();
        immunglobulineObservation.setBeliebigesEreignis(List.of(new ImmunglobulinePointEventConverter().convert(resource)));

        return immunglobulineObservation;
    }
}
