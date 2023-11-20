package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LebensphaseClusterContainment extends Containment {
  public SelectAqlField<LebensphaseCluster> LEBENSPHASE_CLUSTER = new AqlFieldImp<LebensphaseCluster>(LebensphaseCluster.class, "", "LebensphaseCluster", LebensphaseCluster.class, this);

  public SelectAqlField<DvCodedText> BEGINN = new AqlFieldImp<DvCodedText>(LebensphaseCluster.class, "/items[at0001]/value", "beginn", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> BEGINN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LebensphaseCluster.class, "/items[at0001]/null_flavour|defining_code", "beginnNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> ENDE = new AqlFieldImp<DvCodedText>(LebensphaseCluster.class, "/items[at0002]/value", "ende", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ENDE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LebensphaseCluster.class, "/items[at0002]/null_flavour|defining_code", "endeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LebensphaseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private LebensphaseClusterContainment() {
    super("openEHR-EHR-CLUSTER.lebensphase.v0");
  }

  public static LebensphaseClusterContainment getInstance() {
    return new LebensphaseClusterContainment();
  }
}
