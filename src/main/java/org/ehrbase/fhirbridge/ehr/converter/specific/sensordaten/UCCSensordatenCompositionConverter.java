package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.UCCAppSensorDatenComposition;
import org.hl7.fhir.r4.model.Observation;

public class UCCSensordatenCompositionConverter extends CompositionToCompositionConverter<UCCAppSensorDatenComposition> {
    @Override
    protected  convertInternal(Observation resource) {
        System.out.println("TEST");
        return null;
    }
}
