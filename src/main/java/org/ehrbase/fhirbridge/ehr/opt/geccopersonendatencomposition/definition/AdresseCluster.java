package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-30T16:13:57.794283+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class AdresseCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Art
   * Description: Art der Adresse.
   * Comment: Zum Beispiel: Hausadresse oder postalische Adresse.
   */
  @Path("/items[at0001]/value|defining_code")
  private ArtDefiningCode artDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Art/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour artNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Verwendung/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour verwendungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Adresse unstrukturiert
   * Description: Eine unstrukturierte Adresse oder eine Verkettung einer oder mehrerer Komponenten aus CLUSTER.structured_address.
   * Comment: Diese Adresszeile stellt eine niedrige geografische/physische Beschreibung eines Standorts dar, die in Verbindung mit den anderen übergeordneten Adresskomponenten, d. h. „Vorort/Stadt/Ort“, „Postleitzahl“ und „Staat/Land/Bundesland“, bildet eine vollständige geografische/physische Adresse. Mehrfaches Vorkommen erlaubt beliebig viele "Adresszeilen". Beispiel: 4 Adresszeilen dargestellt als
   * Wohnung 7A,
   * 52 Davis-Straße,
   * Carlton Nord,
   * Victoria, Australien 3042.
   * Dieses Datenelement kann auch verwendet werden, um ein Wahrzeichen darzustellen, wie zum Beispiel "Das zweite Haus nördlich des Hauptgeschäftes" oder "An der Ecke Smith & Brown Streets".
   */
  @Path("/items[at0009]")
  private List<AdresseAdresseUnstrukturiertElement> adresseUnstrukturiert;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Strukturierte Adressdaten
   * Description: Zusätzliche strukturierte geografische/physische Details auf niedriger Ebene zu einem Standort, die verkettet werden können, um eine oder mehrere Zeilen der „Adresse unstrukturiert“ zu bilden.
   */
  @Path("/items[at0014]")
  private List<Cluster> strukturierteAdressdaten;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Stadt
   * Description: Der Name des Vorortes, der Stadt, des Dorfes, der Gemeinde oder des Ortes der untersten Ebene der Adresse.
   * Comment: Die Codierung mit einer externen Terminologie wird nach Möglichkeit bevorzugt. Zum Beispiel: Fitzroy, Calgary, Bergen.
   */
  @Path("/items[at0015]")
  private List<AdresseStadtElement> stadt;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Bezirk / Landkreis
   * Description: Der Name eines internen politischen oder geografischen Bezirks oder Gebiets innerhalb eines Staates, Territoriums oder einer Provinz, der die Adresse enthält.
   * Comment: Wenn möglich, wird die Codierung mit einer externen Terminologie bevorzugt.
   */
  @Path("/items[at0016]")
  private List<AdresseBezirkLandkreisElement> bezirkLandkreis;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Staat / Land / Bundesland
   * Description: Der Name einer internen politischen oder geografischen Abteilung eines Landes, das die Adresse enthält.
   * Comment: Wenn möglich, wird die Codierung mit einer externen Terminologie bevorzugt. Zum Beispiel: Victoria, Alberta.
   */
  @Path("/items[at0017]/value|value")
  private String staatLandBundeslandValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Staat / Land / Bundesland/null_flavour
   */
  @Path("/items[at0017]/null_flavour|defining_code")
  private NullFlavour staatLandBundeslandNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Postleitzahl
   * Description: Der Code für einen Postzustellungsbereich, der die Adresse enthält, ausgerichtet auf Ort, Vorort oder Ort für eine Adresse, wie vom jeweiligen Postzustelldienst definiert.
   * Comment: Auch als Postleitzahl PLZ bekannt.
   */
  @Path("/items[at0018]/value|value")
  private String postleitzahlValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Postleitzahl/null_flavour
   */
  @Path("/items[at0018]/null_flavour|defining_code")
  private NullFlavour postleitzahlNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Land
   * Description: Der Name des Landes, in dem sich die Adresse befindet.
   * Comment: Wenn möglich, wird die Codierung mit einer externen Terminologie bevorzugt. Zum Beispiel: Australien, Kanada.
   */
  @Path("/items[at0019]/value|value")
  private String landValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Land/null_flavour
   */
  @Path("/items[at0019]/null_flavour|defining_code")
  private NullFlavour landNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Postfach
   * Description: Eine eindeutige Nummer, die einem Postzustellungspunkt zugewiesen ist, wie vom jeweiligen Postzustelldienst definiert.
   */
  @Path("/items[at0020]/value")
  private DvIdentifier postfach;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Postfach/null_flavour
   */
  @Path("/items[at0020]/null_flavour|defining_code")
  private NullFlavour postfachNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Zusätzliche Details
   * Description: Weitere Angaben zur Adresse.
   * Comment: Beispiel: Geolokalisierung.
   */
  @Path("/items[at0024]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Kommentar
   * Description: Zusätzliche Erläuterungen zur Adresse, die nicht in anderen Feldern erfasst werden.
   */
  @Path("/items[at0023]/value|value")
  private String kommentarValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Kommentar/null_flavour
   */
  @Path("/items[at0023]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Verwendung
   * Description: Der primäre Zweck oder die primäre Verwendung der Adresse.
   */
  @Path("/items[at0005]/value")
  @Choice
  private AdresseVerwendungChoice verwendung;

  public void setArtDefiningCode(ArtDefiningCode artDefiningCode) {
     this.artDefiningCode = artDefiningCode;
  }

  public ArtDefiningCode getArtDefiningCode() {
     return this.artDefiningCode ;
  }

  public void setArtNullFlavourDefiningCode(NullFlavour artNullFlavourDefiningCode) {
     this.artNullFlavourDefiningCode = artNullFlavourDefiningCode;
  }

  public NullFlavour getArtNullFlavourDefiningCode() {
     return this.artNullFlavourDefiningCode ;
  }

  public void setVerwendungNullFlavourDefiningCode(NullFlavour verwendungNullFlavourDefiningCode) {
     this.verwendungNullFlavourDefiningCode = verwendungNullFlavourDefiningCode;
  }

  public NullFlavour getVerwendungNullFlavourDefiningCode() {
     return this.verwendungNullFlavourDefiningCode ;
  }

  public void setAdresseUnstrukturiert(
      List<AdresseAdresseUnstrukturiertElement> adresseUnstrukturiert) {
     this.adresseUnstrukturiert = adresseUnstrukturiert;
  }

  public List<AdresseAdresseUnstrukturiertElement> getAdresseUnstrukturiert() {
     return this.adresseUnstrukturiert ;
  }

  public void setStrukturierteAdressdaten(List<Cluster> strukturierteAdressdaten) {
     this.strukturierteAdressdaten = strukturierteAdressdaten;
  }

  public List<Cluster> getStrukturierteAdressdaten() {
     return this.strukturierteAdressdaten ;
  }

  public void setStadt(List<AdresseStadtElement> stadt) {
     this.stadt = stadt;
  }

  public List<AdresseStadtElement> getStadt() {
     return this.stadt ;
  }

  public void setBezirkLandkreis(List<AdresseBezirkLandkreisElement> bezirkLandkreis) {
     this.bezirkLandkreis = bezirkLandkreis;
  }

  public List<AdresseBezirkLandkreisElement> getBezirkLandkreis() {
     return this.bezirkLandkreis ;
  }

  public void setStaatLandBundeslandValue(String staatLandBundeslandValue) {
     this.staatLandBundeslandValue = staatLandBundeslandValue;
  }

  public String getStaatLandBundeslandValue() {
     return this.staatLandBundeslandValue ;
  }

  public void setStaatLandBundeslandNullFlavourDefiningCode(
      NullFlavour staatLandBundeslandNullFlavourDefiningCode) {
     this.staatLandBundeslandNullFlavourDefiningCode = staatLandBundeslandNullFlavourDefiningCode;
  }

  public NullFlavour getStaatLandBundeslandNullFlavourDefiningCode() {
     return this.staatLandBundeslandNullFlavourDefiningCode ;
  }

  public void setPostleitzahlValue(String postleitzahlValue) {
     this.postleitzahlValue = postleitzahlValue;
  }

  public String getPostleitzahlValue() {
     return this.postleitzahlValue ;
  }

  public void setPostleitzahlNullFlavourDefiningCode(
      NullFlavour postleitzahlNullFlavourDefiningCode) {
     this.postleitzahlNullFlavourDefiningCode = postleitzahlNullFlavourDefiningCode;
  }

  public NullFlavour getPostleitzahlNullFlavourDefiningCode() {
     return this.postleitzahlNullFlavourDefiningCode ;
  }

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }

  public void setLandNullFlavourDefiningCode(NullFlavour landNullFlavourDefiningCode) {
     this.landNullFlavourDefiningCode = landNullFlavourDefiningCode;
  }

  public NullFlavour getLandNullFlavourDefiningCode() {
     return this.landNullFlavourDefiningCode ;
  }

  public void setPostfach(DvIdentifier postfach) {
     this.postfach = postfach;
  }

  public DvIdentifier getPostfach() {
     return this.postfach ;
  }

  public void setPostfachNullFlavourDefiningCode(NullFlavour postfachNullFlavourDefiningCode) {
     this.postfachNullFlavourDefiningCode = postfachNullFlavourDefiningCode;
  }

  public NullFlavour getPostfachNullFlavourDefiningCode() {
     return this.postfachNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheDetails(List<Cluster> zusaetzlicheDetails) {
     this.zusaetzlicheDetails = zusaetzlicheDetails;
  }

  public List<Cluster> getZusaetzlicheDetails() {
     return this.zusaetzlicheDetails ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setVerwendung(AdresseVerwendungChoice verwendung) {
     this.verwendung = verwendung;
  }

  public AdresseVerwendungChoice getVerwendung() {
     return this.verwendung ;
  }
}
