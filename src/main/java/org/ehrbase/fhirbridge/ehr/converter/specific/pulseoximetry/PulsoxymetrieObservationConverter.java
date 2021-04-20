package org.ehrbase.fhirbridge.ehr.converter.specific.pulseoximetry;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
import org.hl7.fhir.r4.model.Observation;

public class PulsoxymetrieObservationConverter extends ObservationToObservationConverter<PulsoxymetrieObservation> {
    @Override
    protected PulsoxymetrieObservation convertInternal(Observation resource) {
        PulsoxymetrieObservation pulsoxymetrieObservation = new PulsoxymetrieObservation();
        DvProportion dvProportion = new DvProportion(resource.getValueQuantity().getValue().doubleValue(), 100.0, 2L);
        pulsoxymetrieObservation.setSpo(dvProportion);
        return pulsoxymetrieObservation;
    }
}
