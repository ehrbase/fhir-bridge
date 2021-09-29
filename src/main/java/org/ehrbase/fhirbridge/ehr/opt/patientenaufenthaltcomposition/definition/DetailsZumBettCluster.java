package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.device.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-27T16:16:21.072222+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class DetailsZumBettCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Gerätename
   * Description: Identifizierung des Medizingerätes, bevorzugt durch einen allgemein
   * gebräuchlichen Namen, einer formellen und vollständig beschreibenden Bezeichnung oder falls notwendig anhand einer Klasse oder Kategorie des Gerätes.
   * Comment: Dieses Datenelement erfasst den Begriff, die Phrase oder die Kategorie, die in der klinischen Praxis verwendet werden. Zum Beispiel: <Markenname> <Maschine> (XYZ-Audiometer); <Markenname> (14G Jelco IV-Katheter); oder <Markenname / Typ> <Implantat>. Die Codierung mit einer Terminologie ist nach Möglichkeit wünschenswert, auch wenn dies lokal sein kann und von den verfügbaren lokalen Lieferungen abhängt.
   */
  @Path("/items[at0001]/value|value")
  private String geraetenameValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Details zum Bett/Gerätename/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour geraetenameNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Gerätetyp
   * Description: Die Kategorie des Medizingeräts.
   * Comment: Nicht zutreffend, wenn eine Kategorie bereits unter "Gerätename" dokumentiert ist. 
   * Beispiel: Wenn das 'Gerät' als 'Harnkatheter' bezeichnet wird; der 'Typ' kann als 'Verweilkatheter' oder 'Kondom' aufgezeichnet werden. Die Codierung mit einer Terminologie ist wünschenswert, sofern dies möglich ist. Dies kann die Verwendung von GTIN- oder EAN-Nummern einschließen.
   */
  @Path("/items[at0003]/value|value")
  private String geraetetypValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Details zum Bett/Gerätetyp/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour geraetetypNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Eigenschaften
   * Description: Weitere Details zu bestimmten Eigenschaften des Medizingerätes.
   */
  @Path("/items[at0009]")
  private List<Cluster> eigenschaften;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Eindeutige Identifikationsnummer (ID)
   * Description: Eine numerische oder alphanumerische Zeichenfolge, die diesem Gerät in einem bestimmten System zugeordnet ist.
   * Comment: Oft als Barcode am Gerät befestigt.
   */
  @Path("/items[at0021]/value")
  private DvIdentifier eindeutigeIdentifikationsnummerId;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Details zum Bett/Eindeutige Identifikationsnummer (ID)/null_flavour
   */
  @Path("/items[at0021]/null_flavour|defining_code")
  private NullFlavour eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Geräteverwaltung
   * Description: Weitere Details zur Verwaltung und Wartung des Geräts.
   * Comment: Zum Beispiel: Eigentümer, Kontaktdaten, Standort, Netzwerkadresse, Ersetzungsdatum, Kalibrierungsdetails usw.
   */
  @Path("/items[at0019]")
  private List<Cluster> geraeteverwaltung;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Komponenten
   * Description: Zusätzliche strukturierte Informationen zu identifizierten Komponenten des Geräts.
   */
  @Path("/items[at0018]")
  private List<Cluster> komponenten;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Erweiterung
   * Description: Zusätzliche Informationen, die zur Erfassung des lokalen Kontexts oder
   * zur Angleichung an andere Referenzmodelle/Formalismen erforderlich sind.
   */
  @Path("/items[at0026]")
  private List<Cluster> erweiterung;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/Multimedia
   * Description: Digitale Repräsentation des Gerätes.
   * Comment: Zum Beispiel: ein technisches Diagramm eines Geräts oder ein digitales Bild.
   */
  @Path("/items[at0027]")
  private List<Cluster> multimedia;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGeraetenameValue(String geraetenameValue) {
     this.geraetenameValue = geraetenameValue;
  }

  public String getGeraetenameValue() {
     return this.geraetenameValue ;
  }

  public void setGeraetenameNullFlavourDefiningCode(
      NullFlavour geraetenameNullFlavourDefiningCode) {
     this.geraetenameNullFlavourDefiningCode = geraetenameNullFlavourDefiningCode;
  }

  public NullFlavour getGeraetenameNullFlavourDefiningCode() {
     return this.geraetenameNullFlavourDefiningCode ;
  }

  public void setGeraetetypValue(String geraetetypValue) {
     this.geraetetypValue = geraetetypValue;
  }

  public String getGeraetetypValue() {
     return this.geraetetypValue ;
  }

  public void setGeraetetypNullFlavourDefiningCode(NullFlavour geraetetypNullFlavourDefiningCode) {
     this.geraetetypNullFlavourDefiningCode = geraetetypNullFlavourDefiningCode;
  }

  public NullFlavour getGeraetetypNullFlavourDefiningCode() {
     return this.geraetetypNullFlavourDefiningCode ;
  }

  public void setEigenschaften(List<Cluster> eigenschaften) {
     this.eigenschaften = eigenschaften;
  }

  public List<Cluster> getEigenschaften() {
     return this.eigenschaften ;
  }

  public void setEindeutigeIdentifikationsnummerId(DvIdentifier eindeutigeIdentifikationsnummerId) {
     this.eindeutigeIdentifikationsnummerId = eindeutigeIdentifikationsnummerId;
  }

  public DvIdentifier getEindeutigeIdentifikationsnummerId() {
     return this.eindeutigeIdentifikationsnummerId ;
  }

  public void setEindeutigeIdentifikationsnummerIdNullFlavourDefiningCode(
      NullFlavour eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode) {
     this.eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode = eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode;
  }

  public NullFlavour getEindeutigeIdentifikationsnummerIdNullFlavourDefiningCode() {
     return this.eindeutigeIdentifikationsnummerIdNullFlavourDefiningCode ;
  }

  public void setGeraeteverwaltung(List<Cluster> geraeteverwaltung) {
     this.geraeteverwaltung = geraeteverwaltung;
  }

  public List<Cluster> getGeraeteverwaltung() {
     return this.geraeteverwaltung ;
  }

  public void setKomponenten(List<Cluster> komponenten) {
     this.komponenten = komponenten;
  }

  public List<Cluster> getKomponenten() {
     return this.komponenten ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
