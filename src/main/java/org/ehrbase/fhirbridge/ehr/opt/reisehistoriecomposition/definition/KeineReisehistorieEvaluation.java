package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.exclusion_specific.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-09T16:49:28.248247+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class KeineReisehistorieEvaluation implements EntryEntity {
  /**
   * Path: Reisehistorie/Keine Reisehistorie/Aussage über den Ausschluss
   * Description: Ein Bericht über den Ausschluss eines/r bestimmten Problems/Diagnose, familiäre Krankengeschichte, Medikation, Verfahren, Nebenwirkung oder eines anderen klinischen Ereignisses.
   * Comment: Diese Beschreibung muss in Verbindung mit dem Datenelement "ausgeschlossene Kategorie" verwendet werden. Zum Beispiel: Dieses Datenelement kann zur Erfassung einer allgemeinen Aussage, wie z.B. "keine bekannte Vorgeschichte über..." verwendet werden. Die "ausgeschlossene Kategorie" spezifiziert die Aussage, in dem eine Zuordnung zu z.B. Diagnose oder Medikation vorgenommen werden kann. Wird bereits die "ausgeschlossene Kategorie" dafür genutzt, durch eine präkoordinierte Bezeichnung das Vorliegen von Diabetes in der familiären Krankengeschichte auszuschließen, ist der Eintrag in diesem Datenelement redundant.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private AussageUeberDenAusschlussDefiningCode aussageUeberDenAusschlussDefiningCode;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/Tree/Aussage über den Ausschluss/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour aussageUeberDenAusschlussNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/Problem/Diagnose
   * Description: Benennung der Kategorie, des ausgeschlossenen Sachverhalts.
   * Comment: Dieses Item kann unterschiedlich genutzt werden. Zum Beispiel: "Familiäre Vorgeschichte Diabetes":
   *
   * (1) Einschränkung des Namens der "ausgeschlossenen Kategorie" zur Laufzeit über einen "Name constraint" (in diesem Fall "familiäre Probleme/Diagnosen") und Speicherung von "Diabetes" als Wert dieses Datenelements. 
   * oder
   * (2) Belegung des Wertes mit Hilfe von präkoordinierten Benennungen, z.B. "keine familiäre Diabetes-Vorgeschichte".
   *
   * Die Kodierung des Datenelements "ausgeschlossene Kategorie", z.B. durch präkoordinierte Benennungen oder Terminologien, ist wünschenswert.
   *
   * Wird das Datenelement wie in Beispiel (2) kodiert, ist eine weitere Spezifikation im Feld "Aussage über den Ausschluss" nicht notwendig.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Problem/Diagnose']/value|defining_code")
  private ProblemDiagnoseDefiningCode problemDiagnoseDefiningCode;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/Tree/Problem/Diagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Problem/Diagnose']/null_flavour|defining_code")
  private NullFlavour problemDiagnoseNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokale Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> erweiterung;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Reisehistorie/Keine Reisehistorie/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAussageUeberDenAusschlussDefiningCode(
      AussageUeberDenAusschlussDefiningCode aussageUeberDenAusschlussDefiningCode) {
     this.aussageUeberDenAusschlussDefiningCode = aussageUeberDenAusschlussDefiningCode;
  }

  public AussageUeberDenAusschlussDefiningCode getAussageUeberDenAusschlussDefiningCode() {
     return this.aussageUeberDenAusschlussDefiningCode ;
  }

  public void setAussageUeberDenAusschlussNullFlavourDefiningCode(
      NullFlavour aussageUeberDenAusschlussNullFlavourDefiningCode) {
     this.aussageUeberDenAusschlussNullFlavourDefiningCode = aussageUeberDenAusschlussNullFlavourDefiningCode;
  }

  public NullFlavour getAussageUeberDenAusschlussNullFlavourDefiningCode() {
     return this.aussageUeberDenAusschlussNullFlavourDefiningCode ;
  }

  public void setProblemDiagnoseDefiningCode(
      ProblemDiagnoseDefiningCode problemDiagnoseDefiningCode) {
     this.problemDiagnoseDefiningCode = problemDiagnoseDefiningCode;
  }

  public ProblemDiagnoseDefiningCode getProblemDiagnoseDefiningCode() {
     return this.problemDiagnoseDefiningCode ;
  }

  public void setProblemDiagnoseNullFlavourDefiningCode(
      NullFlavour problemDiagnoseNullFlavourDefiningCode) {
     this.problemDiagnoseNullFlavourDefiningCode = problemDiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getProblemDiagnoseNullFlavourDefiningCode() {
     return this.problemDiagnoseNullFlavourDefiningCode ;
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
