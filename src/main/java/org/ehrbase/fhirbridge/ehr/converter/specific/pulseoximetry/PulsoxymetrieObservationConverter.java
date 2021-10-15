package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class PulsoxymetrieObservationConverter extends ObservationToObservationConverter<PulsoxymetrieObservation> {
    @Override
    protected PulsoxymetrieObservation convertInternal(Observation resource) {
        PulsoxymetrieObservation pulsoxymetrieObservation = new PulsoxymetrieObservation();
        mapSpo(resource).ifPresentOrElse(
                pulsoxymetrieObservation::setSpo,
                () -> {
                        pulsoxymetrieObservation.setSpoNullFlavourDefiningCode(NullFlavour.UNKNOWN);
                }
        );
        return pulsoxymetrieObservation;
    }

    private Optional<DvProportion> mapSpo(Observation resource) {
        if (resource.hasValueQuantity()) {
            return Optional.of(new DvProportion(resource.getValueQuantity().getValue().doubleValue(), 100.0, 2L));
        }
        return Optional.empty();
    }
}
