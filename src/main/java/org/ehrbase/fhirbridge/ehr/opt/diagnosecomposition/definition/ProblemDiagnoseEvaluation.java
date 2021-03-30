package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.839469+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class ProblemDiagnoseEvaluation implements EntryEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   * Comment: Wo möglich, ist die Kodierung des Problems oder der Diagnose über eine Terminologie zu bevorzugen.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Name des Problems/ der Diagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDesProblemsDerDiagnoseNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Freitextbeschreibung
   * Description: Beschreibung des Problems oder der Diagnose.
   * Comment: Wird verwendet, um Hintergrund und Kontext, einschließlich Entwicklung, Episoden oder Verschlechterungen, Fortschritt und andere relevante Details über das Problem oder die Diagnose zu liefern.
   */
  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Freitextbeschreibung/null_flavour
   */
  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/null_flavour|defining_code")
  private NullFlavour freitextbeschreibungNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Lokalisation
   * Description: Identifikation einer einfachen Körperstelle zur Lokalisierung des Problems oder der Diagnose.
   * Comment: Wo dies möglich ist, ist die Kodierung der anatomischen Lokalisation über eine Terminologie zu bevorzugen. Verwenden Sie dieses Datenelement, um vorab präkoordinierte anatomische Lokalisationen zu erfassen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung bestimmt werden oder komplexere Modellierungen, wie z.B. relative Lokalisationen erforderlich sind, dann verwenden Sie in diesem Archetyp den CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des SLOT 'Structured anatomical location'. Die Anzahl für dieses Datenelement ist unbegrenzt, um klinische Szenarien wie die Beschreibung eines Hautausschlags an mehreren Stellen zu ermöglichen, wobei jedoch alle anderen Attribute identisch sind. Falls die anatomische Lage über präkoordinierte Codes im Namen des Problems/Diagnose enthalten ist, wird dieses Datenelement überflüssig.
   */
  @Path("/data[at0001]/items[at0012 and name/value='Lokalisation']/value|value")
  private String lokalisationValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Lokalisation/null_flavour
   */
  @Path("/data[at0001]/items[at0012 and name/value='Lokalisation']/null_flavour|defining_code")
  private NullFlavour lokalisationNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Anatomische Lokalisation
   * Description: Eine physische Stelle am oder innerhalb des menschlichen Körpers.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
  private List<AnatomischeLokalisationCluster> anatomischeLokalisation;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Datum des Auftretens/der Erstdiagnose
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Krankheitsanzeichen oder Symptome zum ersten mal beobachtet wurden.
   * Comment: Datumswerte, die als "Alter zu Beginn" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0077 and name/value='Datum des Auftretens/der Erstdiagnose']/value|value")
  private TemporalAccessor datumDesAuftretensDerErstdiagnoseValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Datum des Auftretens/der Erstdiagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0077 and name/value='Datum des Auftretens/der Erstdiagnose']/null_flavour|defining_code")
  private NullFlavour datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Feststellungsdatum
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Diagnose oder das Problem von einer medizinischen Fachkraft festgestellt wurde.
   * Comment: Unvollständige Datumsangaben sind zulässig. Wenn der Patient unter einem Jahr alt ist, dann ist das vollständige Datum oder ein Minimum von Monat und Jahr notwendig, um genaue Altersberechnungen zu ermöglichen - z.B. wenn es zur Entscheidungsunterstützung verwendet wird. Datumswerte, die als "Alter zum Zeitpunkt der klinischen Feststellung" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/value|value")
  private TemporalAccessor feststellungsdatumValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Feststellungsdatum/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/null_flavour|defining_code")
  private NullFlavour feststellungsdatumNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Schweregrad/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour schweregradNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails
   * Description: Diagnosebezogene Informationen.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]")
  private DiagnosedetailsCluster diagnosedetails;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese
   * Description: Die Ursachen, Gründe oder Erklärung für das Entstehen eines bestimmten Problems/einer Diagnose, dessen/deren auslösenden Faktoren und Entwicklung.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]")
  private AetiopathogeneseCluster aetiopathogenese;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Datum/Zeitpunkt der Genesung
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der von einer medizinischen Fachkraft die Genesung oder die Remission des Problems oder der Diagnose festgestellt wurde.
   * Comment: Unvollständige Datumsangaben sind zulässig. Wenn der Patient unter einem Jahr alt ist, dann ist das vollständige Datum oder ein Minimum von Monat und Jahr notwendig, um genaue Altersberechnungen zu ermöglichen - z.B. wenn es zur Entscheidungsunterstützung verwendet wird. Datumswerte, die als "Alter zum Zeitpunkt der Genesung" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0030]/value|value")
  private TemporalAccessor datumZeitpunktDerGenesungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Datum/Zeitpunkt der Genesung/null_flavour
   */
  @Path("/data[at0001]/items[at0030]/null_flavour|defining_code")
  private NullFlavour datumZeitpunktDerGenesungNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Status
   * Description: Strukturierte Angaben zu standort-, domänen-, episoden- oder workflow-spezifischen Aspekten des diagnostischen Prozesses.
   * Comment: Verwenden Sie Status- oder Kontext-Merkmale mit Vorsicht, da sie in der Praxis variabel eingesetzt werden und die Interoperabilität nicht gewährleistet werden kann, sofern die Verwendung nicht mit der Nutzungsgemeinschaft klar abgestimmt wird. Beispiel: aktiver Status - aktiv, inaktiv, genesen, in Remission; Entwicklungsstatus - initial, interim/working, final; zeitlicher Status - aktuell, vergangen; Episodenstatus - erstmalig, neu, laufend; Aufnahmestatus - Aufnahme, Entlassung; oder Prioritätsstatus - primär, sekundär.
   */
  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnostische Sicherheit/null_flavour
   */
  @Path("/data[at0001]/items[at0073]/null_flavour|defining_code")
  private NullFlavour diagnostischeSicherheitNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnoseerläuterung
   * Description: Ergänzende Beschreibung des Problems oder der Diagnose, die nicht anderweitig erfasst wurde.
   */
  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/value|value")
  private String diagnoseerlaeuterungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnoseerläuterung/null_flavour
   */
  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/null_flavour|defining_code")
  private NullFlavour diagnoseerlaeuterungNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Letztes Dokumentationsdatum
   * Description: Datum der letzten Aktualisierung der Diagnose bzw. des Problems.
   */
  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/value|value")
  private TemporalAccessor letztesDokumentationsdatumValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Tree/Letztes Dokumentationsdatum/null_flavour
   */
  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/null_flavour|defining_code")
  private NullFlavour letztesDokumentationsdatumNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnostische Sicherheit
   * Description: Grad der Sicherheit, mit der die Diagnose festgestellt wurde.
   */
  @Path("/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0005]/value")
  @Choice
  private ProblemDiagnoseSchweregradChoice schweregrad;

  public void setNameDesProblemsDerDiagnoseDefiningCode(
      NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode) {
     this.nameDesProblemsDerDiagnoseDefiningCode = nameDesProblemsDerDiagnoseDefiningCode;
  }

  public NameDesProblemsDerDiagnoseDefiningCode getNameDesProblemsDerDiagnoseDefiningCode() {
     return this.nameDesProblemsDerDiagnoseDefiningCode ;
  }

  public void setNameDesProblemsDerDiagnoseNullFlavourDefiningCode(
      NullFlavour nameDesProblemsDerDiagnoseNullFlavourDefiningCode) {
     this.nameDesProblemsDerDiagnoseNullFlavourDefiningCode = nameDesProblemsDerDiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getNameDesProblemsDerDiagnoseNullFlavourDefiningCode() {
     return this.nameDesProblemsDerDiagnoseNullFlavourDefiningCode ;
  }

  public void setFreitextbeschreibungValue(String freitextbeschreibungValue) {
     this.freitextbeschreibungValue = freitextbeschreibungValue;
  }

  public String getFreitextbeschreibungValue() {
     return this.freitextbeschreibungValue ;
  }

  public void setFreitextbeschreibungNullFlavourDefiningCode(
      NullFlavour freitextbeschreibungNullFlavourDefiningCode) {
     this.freitextbeschreibungNullFlavourDefiningCode = freitextbeschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getFreitextbeschreibungNullFlavourDefiningCode() {
     return this.freitextbeschreibungNullFlavourDefiningCode ;
  }

  public void setLokalisationValue(String lokalisationValue) {
     this.lokalisationValue = lokalisationValue;
  }

  public String getLokalisationValue() {
     return this.lokalisationValue ;
  }

  public void setLokalisationNullFlavourDefiningCode(
      NullFlavour lokalisationNullFlavourDefiningCode) {
     this.lokalisationNullFlavourDefiningCode = lokalisationNullFlavourDefiningCode;
  }

  public NullFlavour getLokalisationNullFlavourDefiningCode() {
     return this.lokalisationNullFlavourDefiningCode ;
  }

  public void setAnatomischeLokalisation(
      List<AnatomischeLokalisationCluster> anatomischeLokalisation) {
     this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public List<AnatomischeLokalisationCluster> getAnatomischeLokalisation() {
     return this.anatomischeLokalisation ;
  }

  public void setDatumDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumDesAuftretensDerErstdiagnoseValue) {
     this.datumDesAuftretensDerErstdiagnoseValue = datumDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumDesAuftretensDerErstdiagnoseValue() {
     return this.datumDesAuftretensDerErstdiagnoseValue ;
  }

  public void setDatumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode(
      NullFlavour datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode) {
     this.datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode = datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getDatumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode() {
     return this.datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode ;
  }

  public void setFeststellungsdatumValue(TemporalAccessor feststellungsdatumValue) {
     this.feststellungsdatumValue = feststellungsdatumValue;
  }

  public TemporalAccessor getFeststellungsdatumValue() {
     return this.feststellungsdatumValue ;
  }

  public void setFeststellungsdatumNullFlavourDefiningCode(
      NullFlavour feststellungsdatumNullFlavourDefiningCode) {
     this.feststellungsdatumNullFlavourDefiningCode = feststellungsdatumNullFlavourDefiningCode;
  }

  public NullFlavour getFeststellungsdatumNullFlavourDefiningCode() {
     return this.feststellungsdatumNullFlavourDefiningCode ;
  }

  public void setSchweregradNullFlavourDefiningCode(
      NullFlavour schweregradNullFlavourDefiningCode) {
     this.schweregradNullFlavourDefiningCode = schweregradNullFlavourDefiningCode;
  }

  public NullFlavour getSchweregradNullFlavourDefiningCode() {
     return this.schweregradNullFlavourDefiningCode ;
  }

  public void setDiagnosedetails(DiagnosedetailsCluster diagnosedetails) {
     this.diagnosedetails = diagnosedetails;
  }

  public DiagnosedetailsCluster getDiagnosedetails() {
     return this.diagnosedetails ;
  }

  public void setAetiopathogenese(AetiopathogeneseCluster aetiopathogenese) {
     this.aetiopathogenese = aetiopathogenese;
  }

  public AetiopathogeneseCluster getAetiopathogenese() {
     return this.aetiopathogenese ;
  }

  public void setDatumZeitpunktDerGenesungValue(TemporalAccessor datumZeitpunktDerGenesungValue) {
     this.datumZeitpunktDerGenesungValue = datumZeitpunktDerGenesungValue;
  }

  public TemporalAccessor getDatumZeitpunktDerGenesungValue() {
     return this.datumZeitpunktDerGenesungValue ;
  }

  public void setDatumZeitpunktDerGenesungNullFlavourDefiningCode(
      NullFlavour datumZeitpunktDerGenesungNullFlavourDefiningCode) {
     this.datumZeitpunktDerGenesungNullFlavourDefiningCode = datumZeitpunktDerGenesungNullFlavourDefiningCode;
  }

  public NullFlavour getDatumZeitpunktDerGenesungNullFlavourDefiningCode() {
     return this.datumZeitpunktDerGenesungNullFlavourDefiningCode ;
  }

  public void setStatus(List<Cluster> status) {
     this.status = status;
  }

  public List<Cluster> getStatus() {
     return this.status ;
  }

  public void setDiagnostischeSicherheitNullFlavourDefiningCode(
      NullFlavour diagnostischeSicherheitNullFlavourDefiningCode) {
     this.diagnostischeSicherheitNullFlavourDefiningCode = diagnostischeSicherheitNullFlavourDefiningCode;
  }

  public NullFlavour getDiagnostischeSicherheitNullFlavourDefiningCode() {
     return this.diagnostischeSicherheitNullFlavourDefiningCode ;
  }

  public void setDiagnoseerlaeuterungValue(String diagnoseerlaeuterungValue) {
     this.diagnoseerlaeuterungValue = diagnoseerlaeuterungValue;
  }

  public String getDiagnoseerlaeuterungValue() {
     return this.diagnoseerlaeuterungValue ;
  }

  public void setDiagnoseerlaeuterungNullFlavourDefiningCode(
      NullFlavour diagnoseerlaeuterungNullFlavourDefiningCode) {
     this.diagnoseerlaeuterungNullFlavourDefiningCode = diagnoseerlaeuterungNullFlavourDefiningCode;
  }

  public NullFlavour getDiagnoseerlaeuterungNullFlavourDefiningCode() {
     return this.diagnoseerlaeuterungNullFlavourDefiningCode ;
  }

  public void setLetztesDokumentationsdatumValue(TemporalAccessor letztesDokumentationsdatumValue) {
     this.letztesDokumentationsdatumValue = letztesDokumentationsdatumValue;
  }

  public TemporalAccessor getLetztesDokumentationsdatumValue() {
     return this.letztesDokumentationsdatumValue ;
  }

  public void setLetztesDokumentationsdatumNullFlavourDefiningCode(
      NullFlavour letztesDokumentationsdatumNullFlavourDefiningCode) {
     this.letztesDokumentationsdatumNullFlavourDefiningCode = letztesDokumentationsdatumNullFlavourDefiningCode;
  }

  public NullFlavour getLetztesDokumentationsdatumNullFlavourDefiningCode() {
     return this.letztesDokumentationsdatumNullFlavourDefiningCode ;
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

  public void setDiagnostischeSicherheit(
      ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit) {
     this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiagnoseDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
     return this.diagnostischeSicherheit ;
  }

  public void setSchweregrad(ProblemDiagnoseSchweregradChoice schweregrad) {
     this.schweregrad = schweregrad;
  }

  public ProblemDiagnoseSchweregradChoice getSchweregrad() {
     return this.schweregrad ;
  }
}
