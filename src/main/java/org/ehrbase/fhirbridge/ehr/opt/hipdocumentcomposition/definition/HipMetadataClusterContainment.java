package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class HipMetadataClusterContainment extends Containment {
  public SelectAqlField<HipMetadataCluster> HIP_METADATA_CLUSTER = new AqlFieldImp<HipMetadataCluster>(HipMetadataCluster.class, "", "HipMetadataCluster", HipMetadataCluster.class, this);

  public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(HipMetadataCluster.class, "/items[at0001]/value|value", "kategorieValue", String.class, this);

  public SelectAqlField<NullFlavour> KATEGORIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(HipMetadataCluster.class, "/items[at0001]/null_flavour|defining_code", "kategorieNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<HipMetadataIdentifikatorElement> IDENTIFIKATOR = new ListAqlFieldImp<HipMetadataIdentifikatorElement>(HipMetadataCluster.class, "/items[at0002]", "identifikator", HipMetadataIdentifikatorElement.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(HipMetadataCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private HipMetadataClusterContainment() {
    super("openEHR-EHR-CLUSTER.hip_metadata.v0");
  }

  public static HipMetadataClusterContainment getInstance() {
    return new HipMetadataClusterContainment();
  }
}
