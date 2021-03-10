package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

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
    date = "2021-02-26T00:40:41.813373+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class VorliegendeDiagnoseEvaluation implements EntryEntity {
  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Name des Problems/ der Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Structure/Name des Problems/ der Diagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDesProblemsDerDiagnoseNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Körperstelle
   * Description: Eine physische Stelle am oder innerhalb des menschlichen Körpers.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1 and name/value='Körperstelle']")
  private List<KoerperstelleCluster> koerperstelle;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Datum/ Zeitpunkt des Auftretens/ der Erstdiagnose
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Krankheitsanzeichen oder Symptome zum ersten mal beobachtet wurden.
   */
  @Path("/data[at0001]/items[at0077]/value|value")
  private TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Structure/Datum/ Zeitpunkt des Auftretens/ der Erstdiagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0077]/null_flavour|defining_code")
  private NullFlavour datumZeitpunktDesAuftretensDerErstdiagnoseNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   */
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private SchweregradDefiningCode schweregradDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Structure/Schweregrad/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour schweregradNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Spezifische Angaben
   * Description: Zusätzlich benötigte Angaben, welche als eindeutige Merkmale des Problem/der Diagnose erfasst werden sollten.
   * Comment: Hier können strukturierte Angaben über die Einstufung oder das Stadium der Diagnose enthalten sein; diagnostische Kriterien, Klassifizierungskriterien oder formale Bewertungen des Schweregrades wie z.B. "Common Terminology Criteria for Adverse Events".
   */
  @Path("/data[at0001]/items[at0043]")
  private List<Cluster> spezifischeAngaben;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Datum/Zeitpunkt der Genesung
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der von einer medizinischen Fachkraft die Genesung oder die Remission des Problems oder der Diagnose festgestellt wurde.
   */
  @Path("/data[at0001]/items[at0030]/value|value")
  private TemporalAccessor datumZeitpunktDerGenesungValue;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Structure/Datum/Zeitpunkt der Genesung/null_flavour
   */
  @Path("/data[at0001]/items[at0030]/null_flavour|defining_code")
  private NullFlavour datumZeitpunktDerGenesungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Status
   * Description: Strukturierte Angaben zu standort-, domänen-, episoden- oder workflow-spezifischen Aspekten des diagnostischen Prozesses.
   * Comment: Verwenden Sie Status- oder Kontext-Merkmale mit Vorsicht, da sie in der Praxis variabel eingesetzt werden und die Interoperabilität nicht gewährleistet werden kann, sofern die Verwendung nicht mit der Nutzungsgemeinschaft klar abgestimmt wird. Beispiel: aktiver Status - aktiv, inaktiv, genesen, in Remission; Entwicklungsstatus - initial, interim/working, final; zeitlicher Status - aktuell, vergangen; Episodenstatus - erstmalig, neu, laufend; Aufnahmestatus - Aufnahme, Entlassung; oder Prioritätsstatus - primär, sekundär.
   */
  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Kommentar
   * Description: Ergänzende Beschreibung des Problems oder der Diagnose, die nicht anderweitig erfasst wurde.
   */
  @Path("/data[at0001]/items[at0069]/value|value")
  private String kommentarValue;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Structure/Kommentar/null_flavour
   */
  @Path("/data[at0001]/items[at0069]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Diagnose/Vorliegende Diagnose/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setKoerperstelle(List<KoerperstelleCluster> koerperstelle) {
     this.koerperstelle = koerperstelle;
  }

  public List<KoerperstelleCluster> getKoerperstelle() {
     return this.koerperstelle ;
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

  public void setSchweregradDefiningCode(SchweregradDefiningCode schweregradDefiningCode) {
     this.schweregradDefiningCode = schweregradDefiningCode;
  }

  public SchweregradDefiningCode getSchweregradDefiningCode() {
     return this.schweregradDefiningCode ;
  }

  public void setSchweregradNullFlavourDefiningCode(
      NullFlavour schweregradNullFlavourDefiningCode) {
     this.schweregradNullFlavourDefiningCode = schweregradNullFlavourDefiningCode;
  }

  public NullFlavour getSchweregradNullFlavourDefiningCode() {
     return this.schweregradNullFlavourDefiningCode ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
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
