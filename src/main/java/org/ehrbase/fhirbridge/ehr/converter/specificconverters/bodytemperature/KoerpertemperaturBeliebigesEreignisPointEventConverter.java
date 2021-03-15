package org.ehrbase.fhirbridge.ehr.converter.specificconverters.bodytemperature;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisPointEvent;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class KoerpertemperaturBeliebigesEreignisPointEventConverter extends ObservationToPointEventConverter<KoerpertemperaturBeliebigesEreignisPointEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(BodyTemperatureCompositionConverter.class);

    @Override
    protected KoerpertemperaturBeliebigesEreignisPointEvent convertInternal(Observation resource) {
        KoerpertemperaturBeliebigesEreignisPointEvent tempEvent = new KoerpertemperaturBeliebigesEreignisPointEvent();
        Quantity fhirValue = null;
        BigDecimal fhirValueNumeric = null;
        try {
            fhirValue = resource.getValueQuantity();
            fhirValueNumeric = fhirValue.getValue();
            LOG.debug("Value numeric: {}", fhirValueNumeric);
        } catch (Exception e) {
            throw new UnprocessableEntityException(e.getMessage());
        }

        if (fhirValueNumeric == null) {
            throw new UnprocessableEntityException("Value is required in FHIR Observation and should be Quantity");
        }
        tempEvent.setTemperaturMagnitude(fhirValueNumeric.doubleValue());
        tempEvent.setTemperaturUnits(fhirValue.getUnit());
        return tempEvent;
    }
}
