package org.ehrbase.fhirbridge.ehr.converter.specific.heartrate;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.PulsfrequenzHerzfrequenzObservation;
import org.hl7.fhir.r4.model.Observation;
import java.util.Optional;

public class PulsfrequenzHerzfrequenzObservationConverter extends ObservationToObservationConverter<PulsfrequenzHerzfrequenzObservation> {

    @Override
    protected PulsfrequenzHerzfrequenzObservation convertInternal(Observation resource) {
        PulsfrequenzHerzfrequenzObservation herzfrequenzObservation = new PulsfrequenzHerzfrequenzObservation();
        if(resource.hasDataAbsentReason()){
            herzfrequenzObservation.setFrequenzNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }else{
            convertValue(resource).ifPresent(herzfrequenzObservation::setFrequenzMagnitude);
            convertUnit(resource).ifPresent(herzfrequenzObservation::setFrequenzUnits);
        }
        return herzfrequenzObservation;
    }

    private Optional<Double> convertValue(Observation resource) {
        if(resource.hasValueQuantity() ){
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        }else{
            return Optional.empty();
        }
    }

    private Optional<String> convertUnit(Observation resource) {
        if(resource.hasValueQuantity() ){
            return Optional.of(resource.getValueQuantity().getCode());
        }else{
            return Optional.empty();
        }
    }
}
