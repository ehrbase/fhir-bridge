package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.procedure.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-01T12:17:24.146662+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ProzedurAction implements EntryEntity {
  /**
   * Path: GECCO_Prozedur/Prozedur/Name der Prozedur
   * Description: Identifizierung der Prozedur über den Namen.
   * Comment: Wenn möglich wird die Kodierung der spezifischen Prozedur mit einer Terminologie bevorzugt.
   */
  @Path("/description[at0001]/items[at0002]/value|defining_code")
  private NameDerProzedurDefiningCode nameDerProzedurDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Name der Prozedur/null_flavour
   */
  @Path("/description[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDerProzedurNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Indikation
   * Description: Der klinische oder prozessbezogene Grund für die Prozedur.
   * Comment: Die Kodierung der Indikation mit einer Terminologie wird nach Möglichkeit bevorzugt. Dieses Datenelement ermöglicht mehrere Vorkommen. Zum Beispiel: "Fehlgeschlagenen Darmvorbereitung" oder "Darmkrebsvorsorge".
   */
  @Path("/description[at0001]/items[at0070]")
  private List<ProzedurIndikationElement> indikation;

  /**
   * Path: GECCO_Prozedur/Prozedur/Körperstelle
   * Description: Anatomische Lokalisation, an der die Prozedur durchgeführt wird.
   * Comment: Das Vorkommen dieses Datenelements ist nicht eingeschränkt. Dies ermöglicht die Darstellung von klinischen Situationen, in denen alle Eigenschaften, ausgenommen die anatomische Lokalisation, identisch sind, wie z.B. das Entfernen mehrerer Hautläsionen an verschiedenen Stellen. Verwenden Sie dieses Datenelement, um einfache Begriffe oder präkoordinierte anatomische Lokalisationen aufzunehmen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung festgelegt werden oder komplexere Modellierungen wie z.B. die relative Lokalisation erforderlich sind, verwenden Sie entweder CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des Slots "Details zur Prozedur" in diesem Archetyp. Wird die anatomische Lokalisation über vordefinierte Codes in den Namen der Prozedur aufgenommen, wird dieses Datenelement redundant.
   */
  @Path("/description[at0001]/items[at0063]/value|defining_code")
  private KoerperstelleDefiningCode koerperstelleDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Körperstelle/null_flavour
   */
  @Path("/description[at0001]/items[at0063]/null_flavour|defining_code")
  private NullFlavour koerperstelleNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät
   * Description: Ein Instrument, ein Gerät, ein Implantat, ein Material oder ähnliches, das für die Bereitstellung von Gesundheitsleistungen verwendet wird. In diesem Zusammenhang umfasst ein medizinisches Gerät eine breite Palette von Geräten, die auf verschiedene physikalische, mechanische, thermische oder ähnliche Weise wirken, schließt jedoch insbesondere Geräte aus, die auf medizinischem Wege wirken, wie zum Beispiel pharmakologische, metabolische oder immunologische Methoden. Der Geltungsbereich umfasst
   * Einweggeräte sowie langlebige oder dauerhafte Geräte, die nachverfolgt,
   * gewartet oder regelmäßig kalibriert werden müssen, wobei zu berücksichtigen ist, dass für jeden Gerätetyp bestimmte Datenaufzeichnungsanforderungen gelten.
   */
  @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.device.v1]")
  private List<MedizingeraetCluster> medizingeraet;

  /**
   * Path: GECCO_Prozedur/Prozedur/Multimedia
   * Description: Multimediale Darstellung der durchgeführten Prozedur.
   */
  @Path("/description[at0001]/items[at0062]")
  private List<Cluster> multimedia;

  /**
   * Path: GECCO_Prozedur/Prozedur/Art der Prozedur
   * Description: Die Art der Prozedur.
   * Comment: Dieses pragmatische Datenelement kann zur Unterstützung der Gliederung für die Benutzeroberfläche verwendet werden.
   */
  @Path("/description[at0001]/items[at0067]/value|defining_code")
  private KategorieDefiningCode artDerProzedurDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Art der Prozedur/null_flavour
   */
  @Path("/description[at0001]/items[at0067]/null_flavour|defining_code")
  private NullFlavour artDerProzedurNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Durchführungsabsicht
   * Description: Grund, warum die angegebene Aktivität für diese Prozedur durchgeführt wurde.
   * Comment: Zum Beispiel: der Grund für den Abbruch oder die Unterbrechung der Prozedur.
   */
  @Path("/description[at0001]/items[at0014 and name/value='Durchführungsabsicht']/value|value")
  private String durchfuehrungsabsichtValue;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Durchführungsabsicht/null_flavour
   */
  @Path("/description[at0001]/items[at0014 and name/value='Durchführungsabsicht']/null_flavour|defining_code")
  private NullFlavour durchfuehrungsabsichtNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Kommentar
   * Description: Zusätzliche Beschreibung der Aktivität oder der "Pathway"-Verlaufsschritte, die in anderen Bereichen nicht erfasst wurden.
   */
  @Path("/description[at0001]/items[at0005]/value|value")
  private String kommentarValue;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Kommentar/null_flavour
   */
  @Path("/description[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Antragsteller
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistung anfordert.
   */
  @Path("/protocol[at0053]/items[at0055]")
  private Cluster antragsteller;

  /**
   * Path: GECCO_Prozedur/Prozedur/Empfänger
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistungsanforderung erhält.
   */
  @Path("/protocol[at0053]/items[at0057]")
  private List<Cluster> empfaenger;

  /**
   * Path: GECCO_Prozedur/Prozedur/Erweiterung
   * Description: Zusätzliche Informationen, die erforderlich sind, um lokale Inhalte zu erfassen oder mit anderen Referenzmodellen/Formalismen abzugleichen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR- oder CIMI-Äquivalente.
   */
  @Path("/protocol[at0053]/items[at0064]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Prozedur/Prozedur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Prozedur/Prozedur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Prozedur/Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Prozedur/Prozedur/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: GECCO_Prozedur/Prozedur/ism_transition/Careflow_step
   */
  @Path("/ism_transition/careflow_step|defining_code")
  private CareflowStepDefiningCode careflowStepDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/ism_transition/Current_state
   */
  @Path("/ism_transition/current_state|defining_code")
  private CurrentStateDefiningCode currentStateDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/ism_transition/transition
   */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setNameDerProzedurDefiningCode(
      NameDerProzedurDefiningCode nameDerProzedurDefiningCode) {
     this.nameDerProzedurDefiningCode = nameDerProzedurDefiningCode;
  }

  public NameDerProzedurDefiningCode getNameDerProzedurDefiningCode() {
     return this.nameDerProzedurDefiningCode ;
  }

  public void setNameDerProzedurNullFlavourDefiningCode(
      NullFlavour nameDerProzedurNullFlavourDefiningCode) {
     this.nameDerProzedurNullFlavourDefiningCode = nameDerProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerProzedurNullFlavourDefiningCode() {
     return this.nameDerProzedurNullFlavourDefiningCode ;
  }

  public void setIndikation(List<ProzedurIndikationElement> indikation) {
     this.indikation = indikation;
  }

  public List<ProzedurIndikationElement> getIndikation() {
     return this.indikation ;
  }

  public void setKoerperstelleDefiningCode(KoerperstelleDefiningCode koerperstelleDefiningCode) {
     this.koerperstelleDefiningCode = koerperstelleDefiningCode;
  }

  public KoerperstelleDefiningCode getKoerperstelleDefiningCode() {
     return this.koerperstelleDefiningCode ;
  }

  public void setKoerperstelleNullFlavourDefiningCode(
      NullFlavour koerperstelleNullFlavourDefiningCode) {
     this.koerperstelleNullFlavourDefiningCode = koerperstelleNullFlavourDefiningCode;
  }

  public NullFlavour getKoerperstelleNullFlavourDefiningCode() {
     return this.koerperstelleNullFlavourDefiningCode ;
  }

  public void setMedizingeraet(List<MedizingeraetCluster> medizingeraet) {
     this.medizingeraet = medizingeraet;
  }

  public List<MedizingeraetCluster> getMedizingeraet() {
     return this.medizingeraet ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }

  public void setArtDerProzedurDefiningCode(KategorieDefiningCode artDerProzedurDefiningCode) {
     this.artDerProzedurDefiningCode = artDerProzedurDefiningCode;
  }

  public KategorieDefiningCode getArtDerProzedurDefiningCode() {
     return this.artDerProzedurDefiningCode ;
  }

  public void setArtDerProzedurNullFlavourDefiningCode(
      NullFlavour artDerProzedurNullFlavourDefiningCode) {
     this.artDerProzedurNullFlavourDefiningCode = artDerProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getArtDerProzedurNullFlavourDefiningCode() {
     return this.artDerProzedurNullFlavourDefiningCode ;
  }

  public void setDurchfuehrungsabsichtValue(String durchfuehrungsabsichtValue) {
     this.durchfuehrungsabsichtValue = durchfuehrungsabsichtValue;
  }

  public String getDurchfuehrungsabsichtValue() {
     return this.durchfuehrungsabsichtValue ;
  }

  public void setDurchfuehrungsabsichtNullFlavourDefiningCode(
      NullFlavour durchfuehrungsabsichtNullFlavourDefiningCode) {
     this.durchfuehrungsabsichtNullFlavourDefiningCode = durchfuehrungsabsichtNullFlavourDefiningCode;
  }

  public NullFlavour getDurchfuehrungsabsichtNullFlavourDefiningCode() {
     return this.durchfuehrungsabsichtNullFlavourDefiningCode ;
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

  public void setAntragsteller(Cluster antragsteller) {
     this.antragsteller = antragsteller;
  }

  public Cluster getAntragsteller() {
     return this.antragsteller ;
  }

  public void setEmpfaenger(List<Cluster> empfaenger) {
     this.empfaenger = empfaenger;
  }

  public List<Cluster> getEmpfaenger() {
     return this.empfaenger ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
     this.careflowStepDefiningCode = careflowStepDefiningCode;
  }

  public CareflowStepDefiningCode getCareflowStepDefiningCode() {
     return this.careflowStepDefiningCode ;
  }

  public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
     this.currentStateDefiningCode = currentStateDefiningCode;
  }

  public CurrentStateDefiningCode getCurrentStateDefiningCode() {
     return this.currentStateDefiningCode ;
  }

  public void setTransitionDefiningCode(Transition transitionDefiningCode) {
     this.transitionDefiningCode = transitionDefiningCode;
  }

  public Transition getTransitionDefiningCode() {
     return this.transitionDefiningCode ;
  }
}
