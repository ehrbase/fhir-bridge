package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.Observation;

public class BlutdruckObservationConverter extends ObservationToObservationConverter<BlutdruckObservation> {
    //TODO sadly duplicated mapping opt generated templates changed therefore cannot reuse :(

    @Override
    protected BlutdruckObservation convertInternal(Observation observation) {
        BlutdruckObservation bloodPressure = new BlutdruckObservation();
        bloodPressure.setBeliebigesEreignis( new BlutdruckEreignisConverter().convert(observation));
        return bloodPressure;
    }


}

