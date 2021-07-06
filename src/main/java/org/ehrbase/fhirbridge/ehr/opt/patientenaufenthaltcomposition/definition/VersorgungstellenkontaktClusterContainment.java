package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class VersorgungstellenkontaktClusterContainment extends Containment {
  public SelectAqlField<VersorgungstellenkontaktCluster> VERSORGUNGSTELLENKONTAKT_CLUSTER = new AqlFieldImp<VersorgungstellenkontaktCluster>(VersorgungstellenkontaktCluster.class, "", "VersorgungstellenkontaktCluster", VersorgungstellenkontaktCluster.class, this);

  public SelectAqlField<String> ZUGEHOERIGER_VERSORGUNGSSTELLENKONTAKT_KENNUNG_VALUE = new AqlFieldImp<String>(VersorgungstellenkontaktCluster.class, "/items[at0001]/value|value", "zugehoerigerVersorgungsstellenkontaktKennungValue", String.class, this);

  public SelectAqlField<NullFlavour> ZUGEHOERIGER_VERSORGUNGSSTELLENKONTAKT_KENNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VersorgungstellenkontaktCluster.class, "/items[at0001]/null_flavour|defining_code", "zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VersorgungstellenkontaktCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VersorgungstellenkontaktClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static VersorgungstellenkontaktClusterContainment getInstance() {
    return new VersorgungstellenkontaktClusterContainment();
  }
}
