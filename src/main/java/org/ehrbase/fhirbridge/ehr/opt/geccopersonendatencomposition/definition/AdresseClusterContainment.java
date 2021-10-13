package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

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

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<ArtDefiningCode> ART_DEFINING_CODE = new AqlFieldImp<ArtDefiningCode>(AdresseCluster.class, "/items[at0001]/value|defining_code", "artDefiningCode", ArtDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0001]/null_flavour|defining_code", "artNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> VERWENDUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0005]/null_flavour|defining_code", "verwendungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<AdresseAdresseUnstrukturiertElement> ADRESSE_UNSTRUKTURIERT = new ListAqlFieldImp<AdresseAdresseUnstrukturiertElement>(AdresseCluster.class, "/items[at0009]", "adresseUnstrukturiert", AdresseAdresseUnstrukturiertElement.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ADRESSDATEN = new ListAqlFieldImp<Cluster>(AdresseCluster.class, "/items[at0014]", "strukturierteAdressdaten", Cluster.class, this);

  public ListSelectAqlField<AdresseStadtElement> STADT = new ListAqlFieldImp<AdresseStadtElement>(AdresseCluster.class, "/items[at0015]", "stadt", AdresseStadtElement.class, this);

  public ListSelectAqlField<AdresseBezirkLandkreisElement> BEZIRK_LANDKREIS = new ListAqlFieldImp<AdresseBezirkLandkreisElement>(AdresseCluster.class, "/items[at0016]", "bezirkLandkreis", AdresseBezirkLandkreisElement.class, this);

  public SelectAqlField<String> STAAT_LAND_BUNDESLAND_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0017]/value|value", "staatLandBundeslandValue", String.class, this);

  public SelectAqlField<NullFlavour> STAAT_LAND_BUNDESLAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0017]/null_flavour|defining_code", "staatLandBundeslandNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0018]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<NullFlavour> POSTLEITZAHL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0018]/null_flavour|defining_code", "postleitzahlNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> LAND_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0019]/value|value", "landValue", String.class, this);

  public SelectAqlField<NullFlavour> LAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0019]/null_flavour|defining_code", "landNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvIdentifier> POSTFACH = new AqlFieldImp<DvIdentifier>(AdresseCluster.class, "/items[at0020]/value", "postfach", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> POSTFACH_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0020]/null_flavour|defining_code", "postfachNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(AdresseCluster.class, "/items[at0024]", "zusaetzlicheDetails", Cluster.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0023]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0023]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AdresseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<AdresseVerwendungChoice> VERWENDUNG = new AqlFieldImp<AdresseVerwendungChoice>(AdresseCluster.class, "/items[at0005]/value", "verwendung", AdresseVerwendungChoice.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v0");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
