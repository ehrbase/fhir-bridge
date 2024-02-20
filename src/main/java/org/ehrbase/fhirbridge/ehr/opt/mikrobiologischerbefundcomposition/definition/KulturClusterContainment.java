package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KulturClusterContainment extends Containment {
  public SelectAqlField<KulturCluster> KULTUR_CLUSTER = new AqlFieldImp<KulturCluster>(KulturCluster.class, "", "KulturCluster", KulturCluster.class, this);

  public ListSelectAqlField<ProErregerCluster> PRO_ERREGER = new ListAqlFieldImp<ProErregerCluster>(KulturCluster.class, "/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]", "proErreger", ProErregerCluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KulturCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KulturClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_panel.v0");
  }

  public static KulturClusterContainment getInstance() {
    return new KulturClusterContainment();
  }
}
