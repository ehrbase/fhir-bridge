package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LevelOfExertionCluster2Containment extends Containment {
  public SelectAqlField<LevelOfExertionCluster2> LEVEL_OF_EXERTION_CLUSTER2 = new AqlFieldImp<LevelOfExertionCluster2>(LevelOfExertionCluster2.class, "", "LevelOfExertionCluster2", LevelOfExertionCluster2.class, this);

  public SelectAqlField<PhaseDefiningCode> PHASE_DEFINING_CODE = new AqlFieldImp<PhaseDefiningCode>(LevelOfExertionCluster2.class, "/items[at0009]/value|defining_code", "phaseDefiningCode", PhaseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PHASE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LevelOfExertionCluster2.class, "/items[at0009]/null_flavour|defining_code", "phaseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LevelOfExertionCluster2.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private LevelOfExertionCluster2Containment() {
    super("openEHR-EHR-CLUSTER.level_of_exertion.v0");
  }

  public static LevelOfExertionCluster2Containment getInstance() {
    return new LevelOfExertionCluster2Containment();
  }
}
