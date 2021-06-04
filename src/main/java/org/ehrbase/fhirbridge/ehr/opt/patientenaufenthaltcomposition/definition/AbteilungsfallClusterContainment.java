package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AbteilungsfallClusterContainment extends Containment {
  public SelectAqlField<AbteilungsfallCluster> ABTEILUNGSFALL_CLUSTER = new AqlFieldImp<AbteilungsfallCluster>(AbteilungsfallCluster.class, "", "AbteilungsfallCluster", AbteilungsfallCluster.class, this);

  public SelectAqlField<String> ZUGEHOERIGER_ABTEILUNGSFALL_KENNUNG_VALUE = new AqlFieldImp<String>(AbteilungsfallCluster.class, "/items[at0001]/value|value", "zugehoerigerAbteilungsfallKennungValue", String.class, this);

  public SelectAqlField<NullFlavour> ZUGEHOERIGER_ABTEILUNGSFALL_KENNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AbteilungsfallCluster.class, "/items[at0001]/null_flavour|defining_code", "zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AbteilungsfallCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AbteilungsfallClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static AbteilungsfallClusterContainment getInstance() {
    return new AbteilungsfallClusterContainment();
  }
}
