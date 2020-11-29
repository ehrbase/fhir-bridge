package org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.inspired_oxygen.v1")
public class EingeatmeterSauerstoffCluster {
    @Path("/items[at0053]/value")
    private DvProportion inspiratorischeSauerstofffraktion;

    @Path("/items[at0058]")
    private List<Cluster> detailsZurSauerstoffzufuhr;

    public void setInspiratorischeSauerstofffraktion(DvProportion inspiratorischeSauerstofffraktion) {
        this.inspiratorischeSauerstofffraktion = inspiratorischeSauerstofffraktion;
    }

    public DvProportion getInspiratorischeSauerstofffraktion() {
        return this.inspiratorischeSauerstofffraktion;
    }

    public void setDetailsZurSauerstoffzufuhr(List<Cluster> detailsZurSauerstoffzufuhr) {
        this.detailsZurSauerstoffzufuhr = detailsZurSauerstoffzufuhr;
    }

    public List<Cluster> getDetailsZurSauerstoffzufuhr() {
        return this.detailsZurSauerstoffzufuhr;
    }
}
