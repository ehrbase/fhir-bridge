package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.media_file.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-24T22:02:30.832177400+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class MediendateiCluster implements LocatableEntity {
  /**
   * Path: Bericht/context/Mediendatei/Inhalt
   * Description: Digitale Repräsentation der Mediendatei.
   * Comment: Wenn die Datei lokal gespeichert wird, wird der eigentliche Inhalt erfasst und mit dem Datentyp Multimedia gespeichert. Beispiel: RTF oder PDF für ein Dokument, JPG für ein Bild, MP4 für ein Video oder WAV für eine Audiodatei. Wenn die Datei an einem Ort außerhalb der Gesundheitsakte gespeichert ist, wird der detaillierte Pfad zur Datei mithilfe des URI-Attributs im Multimedia-Datentyp erfasst. Der Multimedia-Datentyp hat viele RM-Attribute wie die Größe der Datei und URI zu einer externen Quelle - siehe https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_multimedia_class.
   */
  @Path("/items[at0001]/value")
  private DvMultimedia mediendateiInhalt;

  /**
   * Path: Bericht/context/Tree/Mediendatei/Inhalt/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour mediendateiInhaltNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Mediendatei/Inhalt
   * Description: Beschreibender Name oder Titel für die Mediendatei.
   * Comment: Eine Beschreibung des Inhalts der Mediendatei. Verwenden Sie, falls verfügbar, eine vereinbarte standardisierte Namenskonvention. Zum Beispiel: „Verbrennung der rechten Hand Nr. 1“ oder „Videosprechstunde Kardiologie am 2. Mai“.
   */
  @Path("/items[at0002]/value|value")
  private String mediendateiInhaltValue;

  /**
   * Path: Bericht/context/Tree/Mediendatei/Inhalt/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour mediendateiInhaltNullFlavourDefiningCode2;

  /**
   * Path: Bericht/context/Mediendatei/Identifikator
   * Description: Eindeutige ID für die Mediendatei.
   */
  @Path("/items[at0010]")
  private List<MediendateiIdentifikatorElement> identifikator;

  /**
   * Path: Bericht/context/Mediendatei/Beschreibung
   * Description: Kurze Beschreibung der Mediendatei.
   */
  @Path("/items[at0005]/value|value")
  private String beschreibungValue;

  /**
   * Path: Bericht/context/Tree/Mediendatei/Beschreibung/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour beschreibungNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Mediendatei/Erstellt
   * Description: Datum/Uhrzeit, Teildatum oder Zeitraum, in dem die Mediendatei generiert oder verfasst wurde.
   */
  @Path("/items[at0004]/value|value")
  private TemporalAccessor erstelltValue;

  /**
   * Path: Bericht/context/Mediendatei/Erstellt/lower_included
   */
  @Path("/items[at0004]/value/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: Bericht/context/Mediendatei/Erstellt/upper_included
   */
  @Path("/items[at0004]/value/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: Bericht/context/Mediendatei/Erstellt/upper
   */
  @Path("/items[at0004]/value/upper")
  private DvOrdered upper;

  /**
   * Path: Bericht/context/Mediendatei/Erstellt/lower
   */
  @Path("/items[at0004]/value/lower")
  private DvOrdered lower;

  /**
   * Path: Bericht/context/Tree/Mediendatei/Erstellt/null_flavour
   */
  @Path("/items[at0004]/null_flavour|defining_code")
  private NullFlavour erstelltNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Mediendatei/Urheber
   * Description: Informationen zu der Person oder Organisation, die die Mediendatei generiert oder verfasst hat.
   */
  @Path("/items[at0012]")
  private List<Cluster> urheber;

  /**
   * Path: Bericht/context/Mediendatei/Quellgerät
   * Description: Informationen zu dem Gerät, mit dem die Mediendatei generiert oder erzeugt wurde.
   * Comment: Zum Beispiel: Die Kamera, mit der ein Bild aufgenommen wurde.
   */
  @Path("/items[at0011]")
  private List<Cluster> quellgeraet;

  /**
   * Path: Bericht/context/Mediendatei/Zusätzliche Informationen
   * Description: Zusätzliche strukturierte Details zur Mediendatei.
   */
  @Path("/items[at0013]")
  private List<Cluster> zusaetzlicheInformationen;

  /**
   * Path: Bericht/context/Mediendatei/Kommentar
   * Description: Zusätzliche Information zu der Mediendatei, die nicht in anderen Feldern erfasst wurde.
   */
  @Path("/items[at0007]/value|value")
  private String kommentarValue;

  /**
   * Path: Bericht/context/Tree/Mediendatei/Kommentar/null_flavour
   */
  @Path("/items[at0007]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Mediendatei/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setMediendateiInhalt(DvMultimedia mediendateiInhalt) {
     this.mediendateiInhalt = mediendateiInhalt;
  }

  public DvMultimedia getMediendateiInhalt() {
     return this.mediendateiInhalt ;
  }

  public void setMediendateiInhaltNullFlavourDefiningCode(
      NullFlavour mediendateiInhaltNullFlavourDefiningCode) {
     this.mediendateiInhaltNullFlavourDefiningCode = mediendateiInhaltNullFlavourDefiningCode;
  }

  public NullFlavour getMediendateiInhaltNullFlavourDefiningCode() {
     return this.mediendateiInhaltNullFlavourDefiningCode ;
  }

  public void setMediendateiInhaltValue(String mediendateiInhaltValue) {
     this.mediendateiInhaltValue = mediendateiInhaltValue;
  }

  public String getMediendateiInhaltValue() {
     return this.mediendateiInhaltValue ;
  }

  public void setMediendateiInhaltNullFlavourDefiningCode2(
      NullFlavour mediendateiInhaltNullFlavourDefiningCode2) {
     this.mediendateiInhaltNullFlavourDefiningCode2 = mediendateiInhaltNullFlavourDefiningCode2;
  }

  public NullFlavour getMediendateiInhaltNullFlavourDefiningCode2() {
     return this.mediendateiInhaltNullFlavourDefiningCode2 ;
  }

  public void setIdentifikator(List<MediendateiIdentifikatorElement> identifikator) {
     this.identifikator = identifikator;
  }

  public List<MediendateiIdentifikatorElement> getIdentifikator() {
     return this.identifikator ;
  }

  public void setBeschreibungValue(String beschreibungValue) {
     this.beschreibungValue = beschreibungValue;
  }

  public String getBeschreibungValue() {
     return this.beschreibungValue ;
  }

  public void setBeschreibungNullFlavourDefiningCode(
      NullFlavour beschreibungNullFlavourDefiningCode) {
     this.beschreibungNullFlavourDefiningCode = beschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungNullFlavourDefiningCode() {
     return this.beschreibungNullFlavourDefiningCode ;
  }

  public void setErstelltValue(TemporalAccessor erstelltValue) {
     this.erstelltValue = erstelltValue;
  }

  public TemporalAccessor getErstelltValue() {
     return this.erstelltValue ;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
     this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
     return this.lowerIncluded ;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
     this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
     return this.upperIncluded ;
  }

  public void setUpper(DvOrdered upper) {
     this.upper = upper;
  }

  public DvOrdered getUpper() {
     return this.upper ;
  }

  public void setLower(DvOrdered lower) {
     this.lower = lower;
  }

  public DvOrdered getLower() {
     return this.lower ;
  }

  public void setErstelltNullFlavourDefiningCode(NullFlavour erstelltNullFlavourDefiningCode) {
     this.erstelltNullFlavourDefiningCode = erstelltNullFlavourDefiningCode;
  }

  public NullFlavour getErstelltNullFlavourDefiningCode() {
     return this.erstelltNullFlavourDefiningCode ;
  }

  public void setUrheber(List<Cluster> urheber) {
     this.urheber = urheber;
  }

  public List<Cluster> getUrheber() {
     return this.urheber ;
  }

  public void setQuellgeraet(List<Cluster> quellgeraet) {
     this.quellgeraet = quellgeraet;
  }

  public List<Cluster> getQuellgeraet() {
     return this.quellgeraet ;
  }

  public void setZusaetzlicheInformationen(List<Cluster> zusaetzlicheInformationen) {
     this.zusaetzlicheInformationen = zusaetzlicheInformationen;
  }

  public List<Cluster> getZusaetzlicheInformationen() {
     return this.zusaetzlicheInformationen ;
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
}
