package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class EthnischerHintergrundClusterContainment extends Containment {
  public SelectAqlField<EthnischerHintergrundCluster> ETHNISCHER_HINTERGRUND_CLUSTER = new AqlFieldImp<EthnischerHintergrundCluster>(EthnischerHintergrundCluster.class, "", "EthnischerHintergrundCluster", EthnischerHintergrundCluster.class, this);

  public SelectAqlField<DvCodedText> ETHNISCHER_HINTERGRUND = new AqlFieldImp<DvCodedText>(EthnischerHintergrundCluster.class, "/items[at0002]/value", "ethnischerHintergrund", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ETHNISCHER_HINTERGRUND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EthnischerHintergrundCluster.class, "/items[at0002]/null_flavour|defining_code", "ethnischerHintergrundNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(EthnischerHintergrundCluster.class, "/items[at0003]", "details", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(EthnischerHintergrundCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EthnischerHintergrundClusterContainment() {
    super("openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0");
  }

  public static EthnischerHintergrundClusterContainment getInstance() {
    return new EthnischerHintergrundClusterContainment();
  }
}
