package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LevelOfExertionClusterContainment extends Containment {
  public SelectAqlField<LevelOfExertionCluster> LEVEL_OF_EXERTION_CLUSTER = new AqlFieldImp<LevelOfExertionCluster>(LevelOfExertionCluster.class, "", "LevelOfExertionCluster", LevelOfExertionCluster.class, this);

  public SelectAqlField<PhaseDefiningCode> PHASE_DEFINING_CODE = new AqlFieldImp<PhaseDefiningCode>(LevelOfExertionCluster.class, "/items[at0009]/value|defining_code", "phaseDefiningCode", PhaseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PHASE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LevelOfExertionCluster.class, "/items[at0009]/null_flavour|defining_code", "phaseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LevelOfExertionCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private LevelOfExertionClusterContainment() {
    super("openEHR-EHR-CLUSTER.level_of_exertion.v0");
  }

  public static LevelOfExertionClusterContainment getInstance() {
    return new LevelOfExertionClusterContainment();
  }
}
