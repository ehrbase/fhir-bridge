package org.ehrbase.fhirbridge.ehr.converter.specific.bodyweight;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition.KoerpergewichtObservation;
import org.hl7.fhir.r4.model.Observation;
import java.util.Optional;

public class KoerpergewichtObservationConverter extends ObservationToObservationConverter<KoerpergewichtObservation> {
    @Override
    protected KoerpergewichtObservation convertInternal(Observation resource) {
        KoerpergewichtObservation observation = new KoerpergewichtObservation();
        if (resource.hasValueQuantity()) {
            mapGewichtMagnitude(resource).ifPresent(observation::setGewichtMagnitude);
            mapGewichtUnit(resource).ifPresent(observation::setGewichtUnits);
        } else {
            observation.setGewichtNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
        return observation;
    }

    private Optional<String> mapGewichtUnit(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of("kg");
        } else {
            return Optional.empty();
        }
    }

    private Optional<Double> mapGewichtMagnitude(Observation resource) {
        if (resource.hasValueQuantity()) {
            return parseGewicht(resource);
        } else {
            return Optional.empty();
        }
    }

    private Optional<Double> parseGewicht(Observation resource) {
        if (resource.getValueQuantity().getCode().equals("kg")) {
            return Optional.of(resource.getValueQuantity().getValue().doubleValue());
        } else if (resource.getValueQuantity().getCode().equals("[lb_av]")) {
            Double poundInKg = resource.getValueQuantity().getValue().doubleValue() * 0.45359237;
            return Optional.of(poundInKg);
        } else { //gramms
            Double gInKg = resource.getValueQuantity().getValue().doubleValue() / 1000;
            return Optional.of(gInKg);
        }
    }
}
