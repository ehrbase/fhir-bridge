package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BloodPressureObservation;
import org.hl7.fhir.r4.model.Observation;

public class BlutdruckObservationConverter extends ObservationToObservationConverter<BloodPressureObservation> {
    //TODO sadly duplicated mapping opt generated templates changed therefore cannot reuse :(

    @Override
    protected BloodPressureObservation convertInternal(Observation observation) {
        BloodPressureObservation bloodPressure = new BloodPressureObservation();
        bloodPressure.setAnyEvent( new BlutdruckEreignisConverter().convert(observation));
        return bloodPressure;
    }


}

