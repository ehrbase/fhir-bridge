package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProbenmaterialClusterContainment extends Containment {
  public SelectAqlField<ProbenmaterialCluster> PROBENMATERIAL_CLUSTER = new AqlFieldImp<ProbenmaterialCluster>(ProbenmaterialCluster.class, "", "ProbenmaterialCluster", ProbenmaterialCluster.class, this);

  public SelectAqlField<DvCodedText> PROBENART = new AqlFieldImp<DvCodedText>(ProbenmaterialCluster.class, "/items[at0029]/value", "probenart", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> PROBENART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0029]/null_flavour|defining_code", "probenartNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> BESCHREIBUNG_DER_PROBE = new AqlFieldImp<DvCodedText>(ProbenmaterialCluster.class, "/items[at0097]/value", "beschreibungDerProbe", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_DER_PROBE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0097]/null_flavour|defining_code", "beschreibungDerProbeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> LABORPROBENIDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbenmaterialCluster.class, "/items[at0001]/value", "laborprobenidentifikator", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> LABORPROBENIDENTIFIKATOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0001]/null_flavour|defining_code", "laborprobenidentifikatorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> EXTERNER_IDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbenmaterialCluster.class, "/items[at0088]/value", "externerIdentifikator", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> EXTERNER_IDENTIFIKATOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0088]/null_flavour|defining_code", "externerIdentifikatorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_PROBENEINGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(ProbenmaterialCluster.class, "/items[at0034]/value|value", "zeitpunktDesProbeneingangsValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DES_PROBENEINGANGS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0034]/null_flavour|defining_code", "zeitpunktDesProbeneingangsNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ProbenmaterialProbenentahmebedingungElement> PROBENENTAHMEBEDINGUNG = new ListAqlFieldImp<ProbenmaterialProbenentahmebedingungElement>(ProbenmaterialCluster.class, "/items[at0008]", "probenentahmebedingung", ProbenmaterialProbenentahmebedingungElement.class, this);

  public ListSelectAqlField<Cluster> PHYSIKALISCHE_EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0027]", "physikalischeEigenschaften", Cluster.class, this);

  public SelectAqlField<String> PROBENENTNAHMEMETHODE_VALUE = new AqlFieldImp<String>(ProbenmaterialCluster.class, "/items[at0007]/value|value", "probenentnahmemethodeValue", String.class, this);

  public SelectAqlField<NullFlavour> PROBENENTNAHMEMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0007]/null_flavour|defining_code", "probenentnahmemethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOERPERSTELLE_VALUE = new AqlFieldImp<String>(ProbenmaterialCluster.class, "/items[at0087]/value|value", "koerperstelleValue", String.class, this);

  public SelectAqlField<NullFlavour> KOERPERSTELLE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0087]/null_flavour|defining_code", "koerperstelleNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> PROBENENTNAHMESTELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0013]", "probenentnahmestelleStrukturiert", Cluster.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DER_PROBENENTNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0015]/null_flavour|defining_code", "zeitpunktDerProbenentnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DES_PROBENEHMERS = new AqlFieldImp<DvIdentifier>(ProbenmaterialCluster.class, "/items[at0070]/value", "identifikatorDesProbenehmers", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATOR_DES_PROBENEHMERS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0070]/null_flavour|defining_code", "identifikatorDesProbenehmersNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_PROBENEHMER = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0071]", "angabenZumProbenehmer", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0083]", "zusaetzlicheAngaben", Cluster.class, this);

  public ListSelectAqlField<Cluster> BEHAELTER_DETAILS = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0085]", "behaelterDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_VERARBEITUNG = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0068]", "angabenZurVerarbeitung", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_LAGERUNG = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0100]", "angabenZurLagerung", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_TRANSPORT = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0093]", "angabenZumTransport", Cluster.class, this);

  public ListSelectAqlField<Cluster> DIGITALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(ProbenmaterialCluster.class, "/items[at0096]", "digitaleDarstellung", Cluster.class, this);

  public ListSelectAqlField<ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement> IDENTIFIKATOR_DER_UEBERGEORDNETEN_PROBE = new ListAqlFieldImp<ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement>(ProbenmaterialCluster.class, "/items[at0003]", "identifikatorDerUebergeordnetenProbe", ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement.class, this);

  public SelectAqlField<EignungZurAnalyseDefiningCode> EIGNUNG_ZUR_ANALYSE_DEFINING_CODE = new AqlFieldImp<EignungZurAnalyseDefiningCode>(ProbenmaterialCluster.class, "/items[at0041]/value|defining_code", "eignungZurAnalyseDefiningCode", EignungZurAnalyseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> EIGNUNG_ZUR_ANALYSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0041]/null_flavour|defining_code", "eignungZurAnalyseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProbenmaterialCluster.class, "/items[at0045]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbenmaterialCluster.class, "/items[at0045]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProbenmaterialCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProbenmaterialZeitpunktDerProbenentnahmeChoice> ZEITPUNKT_DER_PROBENENTNAHME = new AqlFieldImp<ProbenmaterialZeitpunktDerProbenentnahmeChoice>(ProbenmaterialCluster.class, "/items[at0015]/value", "zeitpunktDerProbenentnahme", ProbenmaterialZeitpunktDerProbenentnahmeChoice.class, this);

  private ProbenmaterialClusterContainment() {
    super("openEHR-EHR-CLUSTER.specimen.v1");
  }

  public static ProbenmaterialClusterContainment getInstance() {
    return new ProbenmaterialClusterContainment();
  }
}
