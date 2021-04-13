package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class BeschaeftigungClusterContainment extends Containment {
  public SelectAqlField<BeschaeftigungCluster> BESCHAEFTIGUNG_CLUSTER = new AqlFieldImp<BeschaeftigungCluster>(BeschaeftigungCluster.class, "", "BeschaeftigungCluster", BeschaeftigungCluster.class, this);

  public SelectAqlField<BerufsbereichDefiningCode> BERUFSBEREICH_DEFINING_CODE = new AqlFieldImp<BerufsbereichDefiningCode>(BeschaeftigungCluster.class, "/items[at0005]/value|defining_code", "berufsbereichDefiningCode", BerufsbereichDefiningCode.class, this);

  public SelectAqlField<NullFlavour> BERUFSBEREICH_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BeschaeftigungCluster.class, "/items[at0005]/null_flavour|defining_code", "berufsbereichNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_ORGANISATION = new ListAqlFieldImp<Cluster>(BeschaeftigungCluster.class, "/items[at0004]", "angabenZurOrganisation", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(BeschaeftigungCluster.class, "/items[at0018]", "zusaetzlicheAngaben", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BeschaeftigungCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BeschaeftigungClusterContainment() {
    super("openEHR-EHR-CLUSTER.occupation_record.v1");
  }

  public static BeschaeftigungClusterContainment getInstance() {
    return new BeschaeftigungClusterContainment();
  }
}
