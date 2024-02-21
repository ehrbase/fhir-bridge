package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ElektronischeKommunikationClusterContainment extends Containment {
  public SelectAqlField<ElektronischeKommunikationCluster> ELEKTRONISCHE_KOMMUNIKATION_CLUSTER = new AqlFieldImp<ElektronischeKommunikationCluster>(ElektronischeKommunikationCluster.class, "", "ElektronischeKommunikationCluster", ElektronischeKommunikationCluster.class, this);

  public SelectAqlField<NullFlavour> ART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ElektronischeKommunikationCluster.class, "/items[at0001]/null_flavour|defining_code", "artNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> DATEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ElektronischeKommunikationCluster.class, "/items[at0002]/null_flavour|defining_code", "datenNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(ElektronischeKommunikationCluster.class, "/items[at0013]", "zusaetzlicheDetails", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ElektronischeKommunikationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ElektronischeKommunikationArtChoice> ART = new AqlFieldImp<ElektronischeKommunikationArtChoice>(ElektronischeKommunikationCluster.class, "/items[at0001]/value", "art", ElektronischeKommunikationArtChoice.class, this);

  public SelectAqlField<ElektronischeKommunikationDatenChoice> DATEN = new AqlFieldImp<ElektronischeKommunikationDatenChoice>(ElektronischeKommunikationCluster.class, "/items[at0002]/value", "daten", ElektronischeKommunikationDatenChoice.class, this);

  private ElektronischeKommunikationClusterContainment() {
    super("openEHR-EHR-CLUSTER.electronic_communication.v1");
  }

  public static ElektronischeKommunikationClusterContainment getInstance() {
    return new ElektronischeKommunikationClusterContainment();
  }
}
