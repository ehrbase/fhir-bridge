package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;

public class PulsfrequenzHerzfrequenzToObservation extends ObservationToObservationConverter<PulsfrequenzHerzfrequenzObservation> {

    @Override
    protected PulsfrequenzHerzfrequenzObservation convertInternal(Observation observation) {
        PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenzObservation = new PulsfrequenzHerzfrequenzObservation();
        return pulsfrequenzHerzfrequenzObservation;
    }
}
