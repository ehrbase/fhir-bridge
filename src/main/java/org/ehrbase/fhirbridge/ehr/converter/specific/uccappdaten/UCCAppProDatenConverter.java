package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.UCCAppPRODatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.UCCAppSensorDatenComposition;
import org.hl7.fhir.r4.model.Composition;

public class UCCAppProDatenConverter  extends CompositionToCompositionConverter<UCCAppPRODatenComposition> {

    @Override
    protected UCCAppPRODatenComposition convertInternal(Composition resource) {
        return null;
    }

}
