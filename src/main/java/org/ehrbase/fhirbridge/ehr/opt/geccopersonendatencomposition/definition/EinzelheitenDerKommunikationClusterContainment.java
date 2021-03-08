package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class EinzelheitenDerKommunikationClusterContainment extends Containment {
  public SelectAqlField<EinzelheitenDerKommunikationCluster> EINZELHEITEN_DER_KOMMUNIKATION_CLUSTER = new AqlFieldImp<EinzelheitenDerKommunikationCluster>(EinzelheitenDerKommunikationCluster.class, "", "EinzelheitenDerKommunikationCluster", EinzelheitenDerKommunikationCluster.class, this);

  public ListSelectAqlField<EinzelheitenDerKommunikationModusElement> MODUS = new ListAqlFieldImp<EinzelheitenDerKommunikationModusElement>(EinzelheitenDerKommunikationCluster.class, "/items[at0010]", "modus", EinzelheitenDerKommunikationModusElement.class, this);

  public ListSelectAqlField<EinzelheitenDerKommunikationKontaktdatenCluster> KONTAKTDATEN = new ListAqlFieldImp<EinzelheitenDerKommunikationKontaktdatenCluster>(EinzelheitenDerKommunikationCluster.class, "/items[at0001]", "kontaktdaten", EinzelheitenDerKommunikationKontaktdatenCluster.class, this);

  public ListSelectAqlField<EinzelheitenDerKommunikationInternetKommunikationCluster> INTERNET_KOMMUNIKATION = new ListAqlFieldImp<EinzelheitenDerKommunikationInternetKommunikationCluster>(EinzelheitenDerKommunikationCluster.class, "/items[at0020]", "internetKommunikation", EinzelheitenDerKommunikationInternetKommunikationCluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(EinzelheitenDerKommunikationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EinzelheitenDerKommunikationClusterContainment() {
    super("openEHR-EHR-CLUSTER.telecom_details.v0");
  }

  public static EinzelheitenDerKommunikationClusterContainment getInstance() {
    return new EinzelheitenDerKommunikationClusterContainment();
  }
}
