package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class StatusClusterContainment extends Containment {
  public SelectAqlField<StatusCluster> STATUS_CLUSTER = new AqlFieldImp<StatusCluster>(StatusCluster.class, "", "StatusCluster", StatusCluster.class, this);

  public SelectAqlField<StatusDefiningCode2> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode2>(StatusCluster.class, "/items[at0001]/value|defining_code", "statusDefiningCode", StatusDefiningCode2.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StatusCluster.class, "/items[at0001]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(StatusCluster.class, "/items[at0014]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StatusCluster.class, "/items[at0014]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StatusCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StatusClusterContainment() {
    super("openEHR-EHR-CLUSTER.medication_status_fhir.v0");
  }

  public static StatusClusterContainment getInstance() {
    return new StatusClusterContainment();
  }
}
