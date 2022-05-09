package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class BelastungsgradClusterContainment extends Containment {
  public SelectAqlField<BelastungsgradCluster> BELASTUNGSGRAD_CLUSTER = new AqlFieldImp<BelastungsgradCluster>(BelastungsgradCluster.class, "", "BelastungsgradCluster", BelastungsgradCluster.class, this);

  public SelectAqlField<PhaseDefiningCode> PHASE_DEFINING_CODE = new AqlFieldImp<PhaseDefiningCode>(BelastungsgradCluster.class, "/items[at0009]/value|defining_code", "phaseDefiningCode", PhaseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PHASE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BelastungsgradCluster.class, "/items[at0009]/null_flavour|defining_code", "phaseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BelastungsgradCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BelastungsgradClusterContainment() {
    super("openEHR-EHR-CLUSTER.level_of_exertion.v0");
  }

  public static BelastungsgradClusterContainment getInstance() {
    return new BelastungsgradClusterContainment();
  }
}
