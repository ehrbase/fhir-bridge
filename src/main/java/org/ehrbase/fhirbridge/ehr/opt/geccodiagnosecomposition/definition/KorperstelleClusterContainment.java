package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KorperstelleClusterContainment extends Containment {
  public SelectAqlField<KorperstelleCluster> KORPERSTELLE_CLUSTER = new AqlFieldImp<KorperstelleCluster>(KorperstelleCluster.class, "", "KorperstelleCluster", KorperstelleCluster.class, this);

  public ListSelectAqlField<Cluster> ALTERNATIVE_STRUKTUR = new ListAqlFieldImp<Cluster>(KorperstelleCluster.class, "/items[at0053]", "alternativeStruktur", Cluster.class, this);

  public SelectAqlField<NameDerKorperstelleDefiningcode> NAME_DER_KORPERSTELLE_DEFININGCODE = new AqlFieldImp<NameDerKorperstelleDefiningcode>(KorperstelleCluster.class, "/items[at0001]/value|defining_code", "nameDerKorperstelleDefiningcode", NameDerKorperstelleDefiningcode.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(KorperstelleCluster.class, "/items[at0054]", "multimedialeDarstellung", Cluster.class, this);

  public SelectAqlField<LateralitatDefiningcode> LATERALITAT_DEFININGCODE = new AqlFieldImp<LateralitatDefiningcode>(KorperstelleCluster.class, "/items[at0002]/value|defining_code", "lateralitatDefiningcode", LateralitatDefiningcode.class, this);

  private KorperstelleClusterContainment() {
    super("openEHR-EHR-CLUSTER.anatomical_location.v1");
  }

  public static KorperstelleClusterContainment getInstance() {
    return new KorperstelleClusterContainment();
  }
}
