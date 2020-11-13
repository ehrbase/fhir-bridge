package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

import java.net.URI;

@Entity
@OptionFor("DV_URI")
public class ProLaboranalytProbeDvuri implements ProLaboranalytProbeChoice {
    @Path("|value")
    private URI probeValue;

    public void setProbeValue(URI probeValue) {
        this.probeValue = probeValue;
    }

    public URI getProbeValue() {
        return this.probeValue;
    }
}
