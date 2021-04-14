package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AetiopathogeneseClusterContainment extends Containment {
  public SelectAqlField<AetiopathogeneseCluster> AETIOPATHOGENESE_CLUSTER = new AqlFieldImp<AetiopathogeneseCluster>(AetiopathogeneseCluster.class, "", "AetiopathogeneseCluster", AetiopathogeneseCluster.class, this);

  public ListSelectAqlField<AetiopathogeneseAetiologieDerKrankheitElement> AETIOLOGIE_DER_KRANKHEIT = new ListAqlFieldImp<AetiopathogeneseAetiologieDerKrankheitElement>(AetiopathogeneseCluster.class, "/items[at0001]", "aetiologieDerKrankheit", AetiopathogeneseAetiologieDerKrankheitElement.class, this);

  public ListSelectAqlField<AetiopathogeneseBeschreibungDesEntstehensElement> BESCHREIBUNG_DES_ENTSTEHENS = new ListAqlFieldImp<AetiopathogeneseBeschreibungDesEntstehensElement>(AetiopathogeneseCluster.class, "/items[at0017]", "beschreibungDesEntstehens", AetiopathogeneseBeschreibungDesEntstehensElement.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AetiopathogeneseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AetiopathogeneseClusterContainment() {
    super("openEHR-EHR-CLUSTER.etiology.v1");
  }

  public static AetiopathogeneseClusterContainment getInstance() {
    return new AetiopathogeneseClusterContainment();
  }
}
