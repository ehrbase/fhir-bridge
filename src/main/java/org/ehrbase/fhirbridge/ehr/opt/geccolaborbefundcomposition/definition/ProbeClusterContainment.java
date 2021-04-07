package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProbeClusterContainment extends Containment {
  public SelectAqlField<ProbeCluster> PROBE_CLUSTER = new AqlFieldImp<ProbeCluster>(ProbeCluster.class, "", "ProbeCluster", ProbeCluster.class, this);

  public SelectAqlField<ProbenartDefiningCode> PROBENART_DEFINING_CODE = new AqlFieldImp<ProbenartDefiningCode>(ProbeCluster.class, "/items[at0029]/value|defining_code", "probenartDefiningCode", ProbenartDefiningCode.class, this);

  public SelectAqlField<NullFlavour> PROBENART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0029]/null_flavour|defining_code", "probenartNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> LABORPROBENIDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbeCluster.class, "/items[at0001]/value", "laborprobenidentifikator", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> LABORPROBENIDENTIFIKATOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0001]/null_flavour|defining_code", "laborprobenidentifikatorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> EXTERNER_IDENTIFIKATOR = new AqlFieldImp<DvIdentifier>(ProbeCluster.class, "/items[at0088]/value", "externerIdentifikator", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> EXTERNER_IDENTIFIKATOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0088]/null_flavour|defining_code", "externerIdentifikatorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DES_PROBENEINGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster.class, "/items[at0034]/value|value", "zeitpunktDesProbeneingangsValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DES_PROBENEINGANGS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0034]/null_flavour|defining_code", "zeitpunktDesProbeneingangsNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ProbeProbenentahmebedingungElement> PROBENENTAHMEBEDINGUNG = new ListAqlFieldImp<ProbeProbenentahmebedingungElement>(ProbeCluster.class, "/items[at0008]", "probenentahmebedingung", ProbeProbenentahmebedingungElement.class, this);

  public ListSelectAqlField<Cluster> PHYSISCHE_EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0027]", "physischeEigenschaften", Cluster.class, this);

  public SelectAqlField<String> PROBENENTNAHMEMETHODE_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0007]/value|value", "probenentnahmemethodeValue", String.class, this);

  public SelectAqlField<NullFlavour> PROBENENTNAHMEMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0007]/null_flavour|defining_code", "probenentnahmemethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOERPERSTELLE_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0087]/value|value", "koerperstelleValue", String.class, this);

  public SelectAqlField<NullFlavour> KOERPERSTELLE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0087]/null_flavour|defining_code", "koerperstelleNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_PROBENENTNAHMESTELLE = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0013]", "anatomischeProbenentnahmestelle", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_PROBENENTNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(ProbeCluster.class, "/items[at0015]/value|value", "zeitpunktDerProbenentnahmeValue", TemporalAccessor.class, this);

  public SelectAqlField<Boolean> LOWER_INCLUDED = new AqlFieldImp<Boolean>(ProbeCluster.class, "/items[at0015]/value/lower_included", "lowerIncluded", Boolean.class, this);

  public SelectAqlField<Boolean> UPPER_INCLUDED = new AqlFieldImp<Boolean>(ProbeCluster.class, "/items[at0015]/value/upper_included", "upperIncluded", Boolean.class, this);

  public SelectAqlField<DvOrdered> UPPER = new AqlFieldImp<DvOrdered>(ProbeCluster.class, "/items[at0015]/value/upper", "upper", DvOrdered.class, this);

  public SelectAqlField<DvOrdered> LOWER = new AqlFieldImp<DvOrdered>(ProbeCluster.class, "/items[at0015]/value/lower", "lower", DvOrdered.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DER_PROBENENTNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0015]/null_flavour|defining_code", "zeitpunktDerProbenentnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> IDENTIFIKATOR_DES_PROBENNEHMERS = new AqlFieldImp<DvIdentifier>(ProbeCluster.class, "/items[at0070]/value", "identifikatorDesProbennehmers", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATOR_DES_PROBENNEHMERS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0070]/null_flavour|defining_code", "identifikatorDesProbennehmersNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_PROBENNEHMER = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0071]", "angabenZumProbennehmer", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN_ZUR_PROBENNAHME = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0083]", "zusaetzlicheAngabenZurProbennahme", Cluster.class, this);

  public ListSelectAqlField<Cluster> BEHAELTER_DETAILS = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0085]", "behaelterDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_VERARBEITUNG = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0068]", "angabenZurVerarbeitung", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_TRANSPORT = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0093]", "angabenZumTransport", Cluster.class, this);

  public ListSelectAqlField<Cluster> DIGITALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(ProbeCluster.class, "/items[at0096]", "digitaleDarstellung", Cluster.class, this);

  public ListSelectAqlField<ProbeIdentifikatorDerUebergeordnetenProbeElement> IDENTIFIKATOR_DER_UEBERGEORDNETEN_PROBE = new ListAqlFieldImp<ProbeIdentifikatorDerUebergeordnetenProbeElement>(ProbeCluster.class, "/items[at0003]", "identifikatorDerUebergeordnetenProbe", ProbeIdentifikatorDerUebergeordnetenProbeElement.class, this);

  public SelectAqlField<NullFlavour> EIGNUNG_ZUM_TESTEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0041]/null_flavour|defining_code", "eignungZumTestenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProbeCluster.class, "/items[at0045]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProbeCluster.class, "/items[at0045]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProbeCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProbeEignungZumTestenChoice> EIGNUNG_ZUM_TESTEN = new AqlFieldImp<ProbeEignungZumTestenChoice>(ProbeCluster.class, "/items[at0041]/value", "eignungZumTesten", ProbeEignungZumTestenChoice.class, this);

  private ProbeClusterContainment() {
    super("openEHR-EHR-CLUSTER.specimen.v1");
  }

  public static ProbeClusterContainment getInstance() {
    return new ProbeClusterContainment();
  }
}
