package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.advance_care_directive.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-10T09:43:22.149215900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class DnrAnordnungEvaluation implements EntryEntity {
  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Art der Richtlinie
   * Description: Die Art der Patientenverfügung.
   * Comment: Eine kurze schriftliche Beschreibung der Art der Patientenverfügung. Eine Kodierung mit einer Terminologie wird, bevorzugt. Es wird erwartet, dass diese weitgehend lokalisiert ist, um die lokale Politik und Gesetzgebung widerzuspiegeln.
   *
   * In den Niederlanden beispielsweise umfassen die Arten von Patientenverfügungen unter anderem "Behandlungsverbot", " Behandlungsverbot mit Beendigung des abgeschlossenen Lebens", "Euthanasieantrag" und "Lebenserklärung".
   *
   * Im Vereinigten Königreich gehören zu den Arten von Patientenverfügungen im Rahmen der medizinischen Versorgung die "Vorabentscheidung", die "Patientenverfügung" und die " Voraberklärung".
   */
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private ArtDerRichtlinieDefiningCode artDerRichtlinieDefiningCode;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Item tree/Art der Richtlinie/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour artDerRichtlinieNullFlavourDefiningCode;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Beschreibung
   * Description: Beschreibung der allgemeinen Patientenverfügung.
   * Comment: Kann verwendet werden, um eine Übersicht über die gesamte Patientenverfügung zu erfassen, die durch strukturierte Daten unterstützt werden kann. Angaben zu bestimmten strukturierten Befunden können unter Verwendung von CLUSTER-Archetypen in den Slot "Einzelheiten zur Richtlinie" aufgenommen werden. Dieses Datenelement kann verwendet werden, um Altdaten zu erfassen, die nicht in einem strukturierten Format verfügbar sind.
   */
  @Path("/data[at0001]/items[at0006]/value|defining_code")
  private BeschreibungDefiningCode beschreibungDefiningCode;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Item tree/Beschreibung/null_flavour
   */
  @Path("/data[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour beschreibungNullFlavourDefiningCode;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Einzelheiten zur Richtlinie
   * Description: Strukturierte Angaben zu den Entscheidungen der Patientenverfügung.
   * Comment: Dieser SLOT sollte auch dazu verwendet werden, Angaben für spezifische Bedingungen oder gemäß nationalen oder anderen lokalen Anforderungen zu notieren. Zum Beispiel kann es im Vereinigten Königreich eine spezifische Aussage darüber geben, ob das Leben aktiv verlängert werden soll. Dies gilt nur während der Schwangerschaft.
   */
  @Path("/data[at0001]/items[at0052]")
  private List<Cluster> einzelheitenZurRichtlinie;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Zeuge
   * Description: Persönliche Angaben zu einer Person, die den Abschluss der Patientenverfügung bezeugt.
   * Comment: Zum Beispiel 'John Smith, Rechtsanwalt'.
   */
  @Path("/protocol[at0010]/items[at0025]")
  private List<Cluster> zeuge;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0010]/items[at0061]")
  private List<Cluster> erweiterung;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: DNR-Anordnung/DNR-Anordnung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerRichtlinieDefiningCode(
      ArtDerRichtlinieDefiningCode artDerRichtlinieDefiningCode) {
     this.artDerRichtlinieDefiningCode = artDerRichtlinieDefiningCode;
  }

  public ArtDerRichtlinieDefiningCode getArtDerRichtlinieDefiningCode() {
     return this.artDerRichtlinieDefiningCode ;
  }

  public void setArtDerRichtlinieNullFlavourDefiningCode(
      NullFlavour artDerRichtlinieNullFlavourDefiningCode) {
     this.artDerRichtlinieNullFlavourDefiningCode = artDerRichtlinieNullFlavourDefiningCode;
  }

  public NullFlavour getArtDerRichtlinieNullFlavourDefiningCode() {
     return this.artDerRichtlinieNullFlavourDefiningCode ;
  }

  public void setBeschreibungDefiningCode(BeschreibungDefiningCode beschreibungDefiningCode) {
     this.beschreibungDefiningCode = beschreibungDefiningCode;
  }

  public BeschreibungDefiningCode getBeschreibungDefiningCode() {
     return this.beschreibungDefiningCode ;
  }

  public void setBeschreibungNullFlavourDefiningCode(
      NullFlavour beschreibungNullFlavourDefiningCode) {
     this.beschreibungNullFlavourDefiningCode = beschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungNullFlavourDefiningCode() {
     return this.beschreibungNullFlavourDefiningCode ;
  }

  public void setEinzelheitenZurRichtlinie(List<Cluster> einzelheitenZurRichtlinie) {
     this.einzelheitenZurRichtlinie = einzelheitenZurRichtlinie;
  }

  public List<Cluster> getEinzelheitenZurRichtlinie() {
     return this.einzelheitenZurRichtlinie ;
  }

  public void setZeuge(List<Cluster> zeuge) {
     this.zeuge = zeuge;
  }

  public List<Cluster> getZeuge() {
     return this.zeuge ;
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
}
