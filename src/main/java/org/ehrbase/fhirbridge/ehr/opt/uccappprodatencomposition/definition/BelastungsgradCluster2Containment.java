package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PhaseDefiningCode;

public class BelastungsgradCluster2Containment extends Containment {
  public SelectAqlField<BelastungsgradCluster2> BELASTUNGSGRAD_CLUSTER2 = new AqlFieldImp<BelastungsgradCluster2>(BelastungsgradCluster2.class, "", "BelastungsgradCluster2", BelastungsgradCluster2.class, this);

  public SelectAqlField<PhaseDefiningCode> PHASE_DEFINING_CODE = new AqlFieldImp<PhaseDefiningCode>(BelastungsgradCluster2.class, "/items[at0009]/value|defining_code", "phaseDefiningCode", PhaseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PHASE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BelastungsgradCluster2.class, "/items[at0009]/null_flavour|defining_code", "phaseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BelastungsgradCluster2.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BelastungsgradCluster2Containment() {
    super("openEHR-EHR-CLUSTER.level_of_exertion.v0");
  }

  public static BelastungsgradCluster2Containment getInstance() {
    return new BelastungsgradCluster2Containment();
  }
}
