package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.GroesseLaengeObservation;
import org.hl7.fhir.r4.model.Observation;

public class GroesseLaengeObservationConverter extends ObservationToObservationConverter<GroesseLaengeObservation> {

    @Override
    protected GroesseLaengeObservation convertInternal(Observation resource) {
        GroesseLaengeObservation groesseLaengeObservation = new GroesseLaengeObservation();
        groesseLaengeObservation.setGroesseLaengeUnits(resource.getValueQuantity().getCode());
        groesseLaengeObservation.setGroesseLaengeMagnitude(resource.getValueQuantity().getValue().doubleValue());
        return groesseLaengeObservation;
    }
}
