package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class DetailsZumBettClusterContainment extends Containment {
  public SelectAqlField<DetailsZumBettCluster> DETAILS_ZUM_BETT_CLUSTER = new AqlFieldImp<DetailsZumBettCluster>(DetailsZumBettCluster.class, "", "DetailsZumBettCluster", DetailsZumBettCluster.class, this);

  public SelectAqlField<String> GERAETENAME_VALUE = new AqlFieldImp<String>(DetailsZumBettCluster.class, "/items[at0001]/value|value", "geraetenameValue", String.class, this);

  public SelectAqlField<NullFlavour> GERAETENAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DetailsZumBettCluster.class, "/items[at0001]/null_flavour|defining_code", "geraetenameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> GERAETETYP_VALUE = new AqlFieldImp<String>(DetailsZumBettCluster.class, "/items[at0003]/value|value", "geraetetypValue", String.class, this);

  public SelectAqlField<NullFlavour> GERAETETYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DetailsZumBettCluster.class, "/items[at0003]/null_flavour|defining_code", "geraetetypNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(DetailsZumBettCluster.class, "/items[at0009]", "eigenschaften", Cluster.class, this);

  public SelectAqlField<DvIdentifier> EINDEUTIGE_IDENTIFIKATIONSNUMMER_ID = new AqlFieldImp<DvIdentifier>(DetailsZumBettCluster.class, "/items[at0021]/value", "eindeutigeIdentifikationsnummerId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> EINDEUTIGE_IDENTIFIKATIONSNUMMER_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DetailsZumBettCluster.class, "/items[at0021]/null_flavour|defining_code", "eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> GERAETEVERWALTUNG = new ListAqlFieldImp<Cluster>(DetailsZumBettCluster.class, "/items[at0019]", "geraeteverwaltung", Cluster.class, this);

  public ListSelectAqlField<Cluster> KOMPONENTEN = new ListAqlFieldImp<Cluster>(DetailsZumBettCluster.class, "/items[at0018]", "komponenten", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(DetailsZumBettCluster.class, "/items[at0026]", "erweiterung", Cluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(DetailsZumBettCluster.class, "/items[at0027]", "multimedia", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(DetailsZumBettCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DetailsZumBettClusterContainment() {
    super("openEHR-EHR-CLUSTER.device.v1");
  }

  public static DetailsZumBettClusterContainment getInstance() {
    return new DetailsZumBettClusterContainment();
  }
}
