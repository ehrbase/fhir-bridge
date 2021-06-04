package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class StandortClusterContainment extends Containment {
  public SelectAqlField<StandortCluster> STANDORT_CLUSTER = new AqlFieldImp<StandortCluster>(StandortCluster.class, "", "StandortCluster", StandortCluster.class, this);

  public SelectAqlField<String> CAMPUS_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0024]/value|value", "campusValue", String.class, this);

  public SelectAqlField<NullFlavour> CAMPUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0024]/null_flavour|defining_code", "campusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> GEBAEUDEGRUPPE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0025]/value|value", "gebaeudegruppeValue", String.class, this);

  public SelectAqlField<NullFlavour> GEBAEUDEGRUPPE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0025]/null_flavour|defining_code", "gebaeudegruppeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> EBENE_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0028]/value|value", "ebeneValue", String.class, this);

  public SelectAqlField<NullFlavour> EBENE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0028]/null_flavour|defining_code", "ebeneNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STATION_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0027]/value|value", "stationValue", String.class, this);

  public SelectAqlField<NullFlavour> STATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0027]/null_flavour|defining_code", "stationNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ZIMMER_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0029]/value|value", "zimmerValue", String.class, this);

  public SelectAqlField<NullFlavour> ZIMMER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0029]/null_flavour|defining_code", "zimmerNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> BETTPLATZ_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0034]/value|value", "bettplatzValue", String.class, this);

  public SelectAqlField<NullFlavour> BETTPLATZ_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0034]/null_flavour|defining_code", "bettplatzNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ZUSAETZLICHE_BESCHREIBUNG_VALUE = new AqlFieldImp<String>(StandortCluster.class, "/items[at0046]/value|value", "zusaetzlicheBeschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> ZUSAETZLICHE_BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StandortCluster.class, "/items[at0046]/null_flavour|defining_code", "zusaetzlicheBeschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DetailsZumBettCluster> DETAILS_ZUM_BETT = new AqlFieldImp<DetailsZumBettCluster>(StandortCluster.class, "/items[openEHR-EHR-CLUSTER.device.v1]", "detailsZumBett", DetailsZumBettCluster.class, this);

  public ListSelectAqlField<Cluster> LEITENDE_ORGANISATIONSEINHEIT = new ListAqlFieldImp<Cluster>(StandortCluster.class, "/items[at0049]", "leitendeOrganisationseinheit", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StandortClusterContainment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static StandortClusterContainment getInstance() {
    return new StandortClusterContainment();
  }
}
