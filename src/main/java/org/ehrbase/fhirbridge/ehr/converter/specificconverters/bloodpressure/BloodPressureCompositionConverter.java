package org.ehrbase.fhirbridge.ehr.converter.specificconverters.bloodpressure;

import org.ehrbase.fhirbridge.ehr.converter.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.BlutdruckComposition;
import org.hl7.fhir.r4.model.Observation;

public class BloodPressureCompositionConverter extends ObservationToCompositionConverter<BlutdruckComposition> {

    @Override
    protected BlutdruckComposition convertInternal(Observation resource) {
        BlutdruckComposition result = new BlutdruckComposition();
        result.setBlutdruck(new BlutdruckObservationConverter().convert(resource));
        return result;
    }
}
