package org.ehrbase.fhirbridge.ehr.converter.specific.antibodypanel;

import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.VirusnachweistestDefiningCode;
import org.hl7.fhir.r4.model.Observation;

public class Immunoassay {
    private final Observation observation;
    private final VirusnachweistestDefiningCode virusnachweistestDefiningCode;


    public Immunoassay(Observation observation, VirusnachweistestDefiningCode virusnachweistestDefiningCode) {
        this.observation = observation;
        this.virusnachweistestDefiningCode = virusnachweistestDefiningCode;
    }

    public Observation getObservation() {
        return observation;
    }

    public VirusnachweistestDefiningCode getVirusnachweistestDefiningCode() {
        return virusnachweistestDefiningCode;
    }
}
