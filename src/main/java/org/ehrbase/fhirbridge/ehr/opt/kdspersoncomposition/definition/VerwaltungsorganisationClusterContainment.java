package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class VerwaltungsorganisationClusterContainment extends Containment {
  public SelectAqlField<VerwaltungsorganisationCluster> VERWALTUNGSORGANISATION_CLUSTER = new AqlFieldImp<VerwaltungsorganisationCluster>(VerwaltungsorganisationCluster.class, "", "VerwaltungsorganisationCluster", VerwaltungsorganisationCluster.class, this);

  public SelectAqlField<String> NAMENSZEILE_VALUE = new AqlFieldImp<String>(VerwaltungsorganisationCluster.class, "/items[at0001]/value|value", "namenszeileValue", String.class, this);

  public SelectAqlField<NullFlavour> NAMENSZEILE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VerwaltungsorganisationCluster.class, "/items[at0001]/null_flavour|defining_code", "namenszeileNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<VerwaltungsorganisationIdentifierElement> IDENTIFIER = new ListAqlFieldImp<VerwaltungsorganisationIdentifierElement>(VerwaltungsorganisationCluster.class, "/items[at0003]", "identifier", VerwaltungsorganisationIdentifierElement.class, this);

  public ListSelectAqlField<Cluster> ADRESSE = new ListAqlFieldImp<Cluster>(VerwaltungsorganisationCluster.class, "/items[at0005]", "adresse", Cluster.class, this);

  public ListSelectAqlField<Cluster> ELEKTRONISCHE_KOMMUNIKATION = new ListAqlFieldImp<Cluster>(VerwaltungsorganisationCluster.class, "/items[at0022]", "elektronischeKommunikation", Cluster.class, this);

  public ListSelectAqlField<Cluster> KONTAKTPERSON = new ListAqlFieldImp<Cluster>(VerwaltungsorganisationCluster.class, "/items[at0002]", "kontaktperson", Cluster.class, this);

  public ListSelectAqlField<Cluster> MUTTERGESELLSCHAFT = new ListAqlFieldImp<Cluster>(VerwaltungsorganisationCluster.class, "/items[at0021]", "muttergesellschaft", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(VerwaltungsorganisationCluster.class, "/items[at0017]", "zusaetzlicheDetails", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VerwaltungsorganisationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VerwaltungsorganisationClusterContainment() {
    super("openEHR-EHR-CLUSTER.organisation.v1");
  }

  public static VerwaltungsorganisationClusterContainment getInstance() {
    return new VerwaltungsorganisationClusterContainment();
  }
}
