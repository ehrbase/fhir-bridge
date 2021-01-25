package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.938814+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ProblemDiagnoseEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String nameDesProblemsDerDiagnoseValue;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Structure/Name des Problems/ der Diagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDesProblemsDerDiagnoseNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Anatomische Stelle (strukturiert)
   * Description: Eine strukturierte anatomische Lokalisation des Problems oder der Diagnose.
   * Comment: Verwenden Sie diesen SLOT, um die Archetypen CLUSTER.anatomical_location oder CLUSTER.relative_location einzufügen, wenn die Anforderungen für die Aufnahme der anatomischen Position zur Laufzeit der Anwendung bestimmt werden oder komplexere Modellierungen wie z.B. relative Positionen erforderlich sind. Ist die anatomische Lokalisation über präkoordinierte Codes im Namen des Problems/Diagnose enthalten, wird die Verwendung dieses SLOT überflüssig.
   */
  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Datum/ Zeitpunkt des Auftretens/ der Erstdiagnose
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Krankheitsanzeichen oder Symptome zum ersten mal beobachtet wurden.
   */
  @Path("/data[at0001]/items[at0077]/value|value")
  private TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Structure/Datum/ Zeitpunkt des Auftretens/ der Erstdiagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0077]/null_flavour|defining_code")
  private NullFlavour datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Fieber in den letzten 24 Stunden
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 24 Stunden']")
  private FieberInDenLetzten24StundenCluster fieberInDenLetzten24Stunden;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Fieber in den letzten 4 Tagen
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Fieber in den letzten 4 Tagen']")
  private FieberInDenLetzten4TagenCluster fieberInDenLetzten4Tagen;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Schüttelfrost in den letzten 24 Stunden
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schüttelfrost in den letzten 24 Stunden']")
  private SchuettelfrostInDenLetzten24StundenCluster schuettelfrostInDenLetzten24Stunden;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Husten in den letzten 24 Stunden
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Husten in den letzten 24 Stunden']")
  private HustenInDenLetzten24StundenCluster hustenInDenLetzten24Stunden;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Schnupfen  in den letzten 24 Stunden
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schnupfen  in den letzten 24 Stunden']")
  private SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24Stunden;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Halsschmerzen in den letzten 24 Stunden
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Halsschmerzen in den letzten 24 Stunden']")
  private HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24Stunden;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Atemprobleme
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Atemprobleme']")
  private AtemproblemeCluster atemprobleme;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Schlappheit / Angeschlagenheit
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Schlappheit / Angeschlagenheit']")
  private SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheit;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Gliederschmerzen
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Gliederschmerzen']")
  private GliederschmerzenCluster gliederschmerzen;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Durchfall
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Durchfall']")
  private DurchfallCluster durchfall;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Kopfschmerzen
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Kopfschmerzen']")
  private KopfschmerzenCluster kopfschmerzen;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Geschmacks- und/oder Geruchsverlust
   * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.symptom_sign.v1 and name/value='Geschmacks- und/oder Geruchsverlust']")
  private GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlust;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Status
   * Description: Strukturierte Angaben zu standort-, domänen-, episoden- oder workflow-spezifischen Aspekten des diagnostischen Prozesses.
   * Comment: Verwenden Sie Status- oder Kontext-Merkmale mit Vorsicht, da sie in der Praxis variabel eingesetzt werden und die Interoperabilität nicht gewährleistet werden kann, sofern die Verwendung nicht mit der Nutzungsgemeinschaft klar abgestimmt wird. Beispiel: aktiver Status - aktiv, inaktiv, genesen, in Remission; Entwicklungsstatus - initial, interim/working, final; zeitlicher Status - aktuell, vergangen; Episodenstatus - erstmalig, neu, laufend; Aufnahmestatus - Aufnahme, Entlassung; oder Prioritätsstatus - primär, sekundär.
   */
  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Symptome/Problem/Diagnose/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
     this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
     return this.nameDesProblemsDerDiagnoseValue ;
  }

  public void setNameDesProblemsDerDiagnoseNullFlavourDefiningCode(
      NullFlavour nameDesProblemsDerDiagnoseNullFlavourDefiningCode) {
     this.nameDesProblemsDerDiagnoseNullFlavourDefiningCode = nameDesProblemsDerDiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getNameDesProblemsDerDiagnoseNullFlavourDefiningCode() {
     return this.nameDesProblemsDerDiagnoseNullFlavourDefiningCode ;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
     this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
     return this.anatomischeStelleStrukturiert ;
  }

  public void setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue) {
     this.datumZeitpunktDesAuftretensDerErstdiagnoseValue = datumZeitpunktDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumZeitpunktDesAuftretensDerErstdiagnoseValue() {
     return this.datumZeitpunktDesAuftretensDerErstdiagnoseValue ;
  }

  public void setDatumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode(
      NullFlavour datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode) {
     this.datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode = datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getDatumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode() {
     return this.datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode ;
  }

  public void setFieberInDenLetzten24Stunden(
      FieberInDenLetzten24StundenCluster fieberInDenLetzten24Stunden) {
     this.fieberInDenLetzten24Stunden = fieberInDenLetzten24Stunden;
  }

  public FieberInDenLetzten24StundenCluster getFieberInDenLetzten24Stunden() {
     return this.fieberInDenLetzten24Stunden ;
  }

  public void setFieberInDenLetzten4Tagen(
      FieberInDenLetzten4TagenCluster fieberInDenLetzten4Tagen) {
     this.fieberInDenLetzten4Tagen = fieberInDenLetzten4Tagen;
  }

  public FieberInDenLetzten4TagenCluster getFieberInDenLetzten4Tagen() {
     return this.fieberInDenLetzten4Tagen ;
  }

  public void setSchuettelfrostInDenLetzten24Stunden(
      SchuettelfrostInDenLetzten24StundenCluster schuettelfrostInDenLetzten24Stunden) {
     this.schuettelfrostInDenLetzten24Stunden = schuettelfrostInDenLetzten24Stunden;
  }

  public SchuettelfrostInDenLetzten24StundenCluster getSchuettelfrostInDenLetzten24Stunden() {
     return this.schuettelfrostInDenLetzten24Stunden ;
  }

  public void setHustenInDenLetzten24Stunden(
      HustenInDenLetzten24StundenCluster hustenInDenLetzten24Stunden) {
     this.hustenInDenLetzten24Stunden = hustenInDenLetzten24Stunden;
  }

  public HustenInDenLetzten24StundenCluster getHustenInDenLetzten24Stunden() {
     return this.hustenInDenLetzten24Stunden ;
  }

  public void setSchnupfenInDenLetzten24Stunden(
      SchnupfenInDenLetzten24StundenCluster schnupfenInDenLetzten24Stunden) {
     this.schnupfenInDenLetzten24Stunden = schnupfenInDenLetzten24Stunden;
  }

  public SchnupfenInDenLetzten24StundenCluster getSchnupfenInDenLetzten24Stunden() {
     return this.schnupfenInDenLetzten24Stunden ;
  }

  public void setHalsschmerzenInDenLetzten24Stunden(
      HalsschmerzenInDenLetzten24StundenCluster halsschmerzenInDenLetzten24Stunden) {
     this.halsschmerzenInDenLetzten24Stunden = halsschmerzenInDenLetzten24Stunden;
  }

  public HalsschmerzenInDenLetzten24StundenCluster getHalsschmerzenInDenLetzten24Stunden() {
     return this.halsschmerzenInDenLetzten24Stunden ;
  }

  public void setAtemprobleme(AtemproblemeCluster atemprobleme) {
     this.atemprobleme = atemprobleme;
  }

  public AtemproblemeCluster getAtemprobleme() {
     return this.atemprobleme ;
  }

  public void setSchlappheitAngeschlagenheit(
      SchlappheitAngeschlagenheitCluster schlappheitAngeschlagenheit) {
     this.schlappheitAngeschlagenheit = schlappheitAngeschlagenheit;
  }

  public SchlappheitAngeschlagenheitCluster getSchlappheitAngeschlagenheit() {
     return this.schlappheitAngeschlagenheit ;
  }

  public void setGliederschmerzen(GliederschmerzenCluster gliederschmerzen) {
     this.gliederschmerzen = gliederschmerzen;
  }

  public GliederschmerzenCluster getGliederschmerzen() {
     return this.gliederschmerzen ;
  }

  public void setDurchfall(DurchfallCluster durchfall) {
     this.durchfall = durchfall;
  }

  public DurchfallCluster getDurchfall() {
     return this.durchfall ;
  }

  public void setKopfschmerzen(KopfschmerzenCluster kopfschmerzen) {
     this.kopfschmerzen = kopfschmerzen;
  }

  public KopfschmerzenCluster getKopfschmerzen() {
     return this.kopfschmerzen ;
  }

  public void setGeschmacksUndOderGeruchsverlust(
      GeschmacksUndOderGeruchsverlustCluster geschmacksUndOderGeruchsverlust) {
     this.geschmacksUndOderGeruchsverlust = geschmacksUndOderGeruchsverlust;
  }

  public GeschmacksUndOderGeruchsverlustCluster getGeschmacksUndOderGeruchsverlust() {
     return this.geschmacksUndOderGeruchsverlust ;
  }

  public void setStatus(List<Cluster> status) {
     this.status = status;
  }

  public List<Cluster> getStatus() {
     return this.status ;
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
