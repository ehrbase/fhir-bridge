package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.Temporal;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class DatenZurGeburtClusterContainment extends Containment {
  public SelectAqlField<DatenZurGeburtCluster> DATEN_ZUR_GEBURT_CLUSTER = new AqlFieldImp<DatenZurGeburtCluster>(DatenZurGeburtCluster.class, "", "DatenZurGeburtCluster", DatenZurGeburtCluster.class, this);

  public SelectAqlField<Temporal> GEBURTSDATUM_VALUE = new AqlFieldImp<Temporal>(DatenZurGeburtCluster.class, "/items[at0001]/value|value", "geburtsdatumValue", Temporal.class, this);

  public SelectAqlField<NullFlavour> GEBURTSDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DatenZurGeburtCluster.class, "/items[at0001]/null_flavour|defining_code", "geburtsdatumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Cluster> LAENDERSPEZIFISCHE_DATEN = new AqlFieldImp<Cluster>(DatenZurGeburtCluster.class, "/items[at0006]", "laenderspezifischeDaten", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(DatenZurGeburtCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DatenZurGeburtClusterContainment() {
    super("openEHR-EHR-CLUSTER.person_birth_data_iso.v0");
  }

  public static DatenZurGeburtClusterContainment getInstance() {
    return new DatenZurGeburtClusterContainment();
  }
}
