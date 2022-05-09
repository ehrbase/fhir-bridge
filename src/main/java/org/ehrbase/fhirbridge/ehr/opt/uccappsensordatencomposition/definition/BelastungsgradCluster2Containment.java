package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class BelastungsgradCluster2Containment extends Containment {
  public SelectAqlField<BelastungsgradCluster2> BELASTUNGSGRAD_CLUSTER2 = new AqlFieldImp<BelastungsgradCluster2>(BelastungsgradCluster2.class, "", "BelastungsgradCluster2", BelastungsgradCluster2.class, this);

  public SelectAqlField<String> BESCHREIBUNG_VALUE = new AqlFieldImp<String>(BelastungsgradCluster2.class, "/items[at0010]/items[at0016]/value|value", "beschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BelastungsgradCluster2.class, "/items[at0010]/items[at0016]/null_flavour|defining_code", "beschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BelastungsgradCluster2.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BelastungsgradCluster2Containment() {
    super("openEHR-EHR-CLUSTER.level_of_exertion.v0");
  }

  public static BelastungsgradCluster2Containment getInstance() {
    return new BelastungsgradCluster2Containment();
  }
}
