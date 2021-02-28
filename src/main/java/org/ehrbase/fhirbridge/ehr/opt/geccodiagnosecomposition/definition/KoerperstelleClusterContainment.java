package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class KoerperstelleClusterContainment extends Containment {
  public SelectAqlField<KoerperstelleCluster> KOERPERSTELLE_CLUSTER = new AqlFieldImp<KoerperstelleCluster>(KoerperstelleCluster.class, "", "KoerperstelleCluster", KoerperstelleCluster.class, this);

  public SelectAqlField<NameDerKoerperstelleDefiningCode> NAME_DER_KOERPERSTELLE_DEFINING_CODE = new AqlFieldImp<NameDerKoerperstelleDefiningCode>(KoerperstelleCluster.class, "/items[at0001]/value|defining_code", "nameDerKoerperstelleDefiningCode", NameDerKoerperstelleDefiningCode.class, this);

  public SelectAqlField<NullFlavour> NAME_DER_KOERPERSTELLE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KoerperstelleCluster.class, "/items[at0001]/null_flavour|defining_code", "nameDerKoerperstelleNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<LateralitaetDefiningCode> LATERALITAET_DEFINING_CODE = new AqlFieldImp<LateralitaetDefiningCode>(KoerperstelleCluster.class, "/items[at0002]/value|defining_code", "lateralitaetDefiningCode", LateralitaetDefiningCode.class, this);

  public SelectAqlField<NullFlavour> LATERALITAET_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KoerperstelleCluster.class, "/items[at0002]/null_flavour|defining_code", "lateralitaetNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ALTERNATIVE_STRUKTUR = new ListAqlFieldImp<Cluster>(KoerperstelleCluster.class, "/items[at0053]", "alternativeStruktur", Cluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(KoerperstelleCluster.class, "/items[at0054]", "multimedialeDarstellung", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KoerperstelleCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KoerperstelleClusterContainment() {
    super("openEHR-EHR-CLUSTER.anatomical_location.v1");
  }

  public static KoerperstelleClusterContainment getInstance() {
    return new KoerperstelleClusterContainment();
  }
}
