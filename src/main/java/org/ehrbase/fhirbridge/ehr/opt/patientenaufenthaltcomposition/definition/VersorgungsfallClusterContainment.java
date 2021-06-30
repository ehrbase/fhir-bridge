package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class VersorgungsfallClusterContainment extends Containment {
  public SelectAqlField<VersorgungsfallCluster> VERSORGUNGSFALL_CLUSTER = new AqlFieldImp<VersorgungsfallCluster>(VersorgungsfallCluster.class, "", "VersorgungsfallCluster", VersorgungsfallCluster.class, this);

  public SelectAqlField<String> ZUGEHOERIGER_VERSORGUNGSFALL_KENNUNG_VALUE = new AqlFieldImp<String>(VersorgungsfallCluster.class, "/items[at0001]/value|value", "zugehoerigerVersorgungsfallKennungValue", String.class, this);

  public SelectAqlField<NullFlavour> ZUGEHOERIGER_VERSORGUNGSFALL_KENNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VersorgungsfallCluster.class, "/items[at0001]/null_flavour|defining_code", "zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VersorgungsfallCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VersorgungsfallClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static VersorgungsfallClusterContainment getInstance() {
    return new VersorgungsfallClusterContainment();
  }
}
