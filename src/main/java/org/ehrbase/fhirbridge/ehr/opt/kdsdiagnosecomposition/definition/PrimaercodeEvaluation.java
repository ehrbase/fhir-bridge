package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
    date = "2023-11-22T15:55:37.598404234+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
public class PrimaercodeEvaluation implements EntryEntity {
  /**
   * Path: Diagnose/Primärcode/Kodierte Diagnose
   * Description: Namentliche Identifikation des Problems oder der Diagnose.
   * Comment: Wo möglich, ist die Kodierung des Problems oder der Diagnose über eine Terminologie zu bevorzugen.
   */
  @Path("/data[at0001]/items[at0002 and name/value='Kodierte Diagnose']/value")
  private DvCodedText kodierteDiagnose;

  /**
   * Path: Diagnose/Primärcode/Structure/Kodierte Diagnose/null_flavour
   */
  @Path("/data[at0001]/items[at0002 and name/value='Kodierte Diagnose']/null_flavour|defining_code")
  private NullFlavour kodierteDiagnoseNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Freitextbeschreibung
   * Description: Beschreibung des Problems oder der Diagnose.
   * Comment: Wird verwendet, um Hintergrund und Kontext, einschließlich Entwicklung, Episoden oder Verschlechterungen, Fortschritt und andere relevante Details über das Problem oder die Diagnose zu liefern.
   */
  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  /**
   * Path: Diagnose/Primärcode/Structure/Freitextbeschreibung/null_flavour
   */
  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/null_flavour|defining_code")
  private NullFlavour freitextbeschreibungNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Körperstelle
   * Description: Identifikation einer einfachen Körperstelle zur Lokalisierung des Problems oder der Diagnose.
   * Comment: Wo dies möglich ist, ist die Kodierung der anatomischen Lokalisation über eine Terminologie zu bevorzugen. Verwenden Sie dieses Datenelement, um vorab präkoordinierte anatomische Lokalisationen zu erfassen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung bestimmt werden oder komplexere Modellierungen, wie z.B. relative Lokalisationen erforderlich sind, dann verwenden Sie in diesem Archetyp den CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des SLOT 'Structured anatomical location'. Die Anzahl für dieses Datenelement ist unbegrenzt, um klinische Szenarien wie die Beschreibung eines Hautausschlags an mehreren Stellen zu ermöglichen, wobei jedoch alle anderen Attribute identisch sind. Falls die anatomische Lage über präkoordinierte Codes im Namen des Problems/Diagnose enthalten ist, wird dieses Datenelement überflüssig.
   */
  @Path("/data[at0001]/items[at0012 and name/value='Körperstelle']")
  private List<PrimaercodeKoerperstelleElement> koerperstelle;

  /**
   * Path: Diagnose/Primärcode/Seitenlokalisation
   * Description: Identifikation einer einfachen Körperstelle zur Lokalisierung des Problems oder der Diagnose.
   * Comment: Wo dies möglich ist, ist die Kodierung der anatomischen Lokalisation über eine Terminologie zu bevorzugen. Verwenden Sie dieses Datenelement, um vorab präkoordinierte anatomische Lokalisationen zu erfassen. Wenn die Anforderungen an die Erfassung der anatomischen Lokalisation zur Laufzeit durch die Anwendung bestimmt werden oder komplexere Modellierungen, wie z.B. relative Lokalisationen erforderlich sind, dann verwenden Sie in diesem Archetyp den CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des SLOT 'Structured anatomical location'. Die Anzahl für dieses Datenelement ist unbegrenzt, um klinische Szenarien wie die Beschreibung eines Hautausschlags an mehreren Stellen zu ermöglichen, wobei jedoch alle anderen Attribute identisch sind. Falls die anatomische Lage über präkoordinierte Codes im Namen des Problems/Diagnose enthalten ist, wird dieses Datenelement überflüssig.
   */
  @Path("/data[at0001]/items[at0012 and name/value='Seitenlokalisation']")
  private List<PrimaercodeSeitenlokalisationElement> seitenlokalisation;

  /**
   * Path: Diagnose/Primärcode/Anatomische Stelle (strukturiert)
   * Description: Eine strukturierte anatomische Lokalisation des Problems oder der Diagnose.
   * Comment: Verwenden Sie diesen SLOT, um die Archetypen CLUSTER.anatomical_location oder CLUSTER.relative_location einzufügen, wenn die Anforderungen für die Aufnahme der anatomischen Position zur Laufzeit der Anwendung bestimmt werden oder komplexere Modellierungen wie z.B. relative Positionen erforderlich sind. Ist die anatomische Lokalisation über präkoordinierte Codes im Namen des Problems/Diagnose enthalten, wird die Verwendung dieses SLOT überflüssig.
   */
  @Path("/data[at0001]/items[at0039]")
  private List<Cluster> anatomischeStelleStrukturiert;

  /**
   * Path: Diagnose/Primärcode/Klinisch relevanter Zeitraum (Zeitpunkt des Auftretens)
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Krankheitsanzeichen oder Symptome zum ersten mal beobachtet wurden.
   * Comment: Datumswerte, die als "Alter zu Beginn" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0077 and name/value='Klinisch relevanter Zeitraum (Zeitpunkt des Auftretens)']/value|value")
  private TemporalAccessor klinischRelevanterZeitraumZeitpunktDesAuftretensValue;

  /**
   * Path: Diagnose/Primärcode/Structure/Klinisch relevanter Zeitraum (Zeitpunkt des Auftretens)/null_flavour
   */
  @Path("/data[at0001]/items[at0077 and name/value='Klinisch relevanter Zeitraum (Zeitpunkt des Auftretens)']/null_flavour|defining_code")
  private NullFlavour klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Feststellungsdatum
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der die Diagnose oder das Problem von einer medizinischen Fachkraft festgestellt wurde.
   * Comment: Unvollständige Datumsangaben sind zulässig. Wenn der Patient unter einem Jahr alt ist, dann ist das vollständige Datum oder ein Minimum von Monat und Jahr notwendig, um genaue Altersberechnungen zu ermöglichen - z.B. wenn es zur Entscheidungsunterstützung verwendet wird. Datumswerte, die als "Alter zum Zeitpunkt der klinischen Feststellung" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/value|value")
  private TemporalAccessor feststellungsdatumValue;

  /**
   * Path: Diagnose/Primärcode/Structure/Feststellungsdatum/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/null_flavour|defining_code")
  private NullFlavour feststellungsdatumNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Lebensphase
   * Description: Beschreibung des ungefähren Alters, wann die Erkrankung durchgemacht wurde. Ungefähre Angabe des Alters, da häufig keine genaue Angabe des Zeitpunktes (klinisch relevanter Zeitraum) der Erkrankung möglich ist, vor allem wenn die Diagnose nicht durch die eintragende ärztliche Person erfolgt.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.lebensphase.v0]")
  private LebensphaseCluster lebensphase;

  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM
   * Description: Zusatzkennzeichen für postkoordinierte ICD-10-GM-Kodes.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1]")
  private MehrfachkodierungskennzeichenIcd10GmCluster mehrfachkodierungskennzeichenIcd10Gm;

  /**
   * Path: Diagnose/Primärcode/Klinisch relevanter Zeitraum (Zeitpunkt der Genesung)
   * Description: Geschätzte oder exakte Zeit (bzw. Datum), zu der von einer medizinischen Fachkraft die Genesung oder die Remission des Problems oder der Diagnose festgestellt wurde.
   * Comment: Unvollständige Datumsangaben sind zulässig. Wenn der Patient unter einem Jahr alt ist, dann ist das vollständige Datum oder ein Minimum von Monat und Jahr notwendig, um genaue Altersberechnungen zu ermöglichen - z.B. wenn es zur Entscheidungsunterstützung verwendet wird. Datumswerte, die als "Alter zum Zeitpunkt der Genesung" erfasst/importiert werden, sollten anhand des Geburtsdatums der Person in ein Datum umgewandelt werden.
   */
  @Path("/data[at0001]/items[at0030 and name/value='Klinisch relevanter Zeitraum (Zeitpunkt der Genesung)']/value|value")
  private TemporalAccessor klinischRelevanterZeitraumZeitpunktDerGenesungValue;

  /**
   * Path: Diagnose/Primärcode/Structure/Klinisch relevanter Zeitraum (Zeitpunkt der Genesung)/null_flavour
   */
  @Path("/data[at0001]/items[at0030 and name/value='Klinisch relevanter Zeitraum (Zeitpunkt der Genesung)']/null_flavour|defining_code")
  private NullFlavour klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Klinischer Status
   * Description: Kontextabhängiges oder temporäres Attribut für ein bestimmtes Problem oder eine bestimmte Diagnose.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v2 and name/value='Klinischer Status']")
  private KlinischerStatusCluster klinischerStatus;

  /**
   * Path: Diagnose/Primärcode/Diagnosesicherheit
   * Description: Grad der Sicherheit, mit der die Diagnose festgestellt wurde.
   * Comment: Wenn ein alternativer Wertesatz benötigt wird, können diese Werte in einem Template zum Datentyp DV_TEXT hinzugefügt werden.
   */
  @Path("/data[at0001]/items[at0073 and name/value='Diagnosesicherheit']/value")
  private DvCodedText diagnosesicherheit;

  /**
   * Path: Diagnose/Primärcode/Structure/Diagnosesicherheit/null_flavour
   */
  @Path("/data[at0001]/items[at0073 and name/value='Diagnosesicherheit']/null_flavour|defining_code")
  private NullFlavour diagnosesicherheitNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Diagnoseerläuterung
   * Description: Ergänzende Beschreibung des Problems oder der Diagnose, die nicht anderweitig erfasst wurde.
   */
  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/value|value")
  private String diagnoseerlaeuterungValue;

  /**
   * Path: Diagnose/Primärcode/Structure/Diagnoseerläuterung/null_flavour
   */
  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/null_flavour|defining_code")
  private NullFlavour diagnoseerlaeuterungNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Letztes Dokumentationsdatum
   * Description: Datum der letzten Aktualisierung der Diagnose bzw. des Problems.
   */
  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/value|value")
  private TemporalAccessor letztesDokumentationsdatumValue;

  /**
   * Path: Diagnose/Primärcode/Tree/Letztes Dokumentationsdatum/null_flavour
   */
  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/null_flavour|defining_code")
  private NullFlavour letztesDokumentationsdatumNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  /**
   * Path: Diagnose/Primärcode/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Diagnose/Primärcode/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Diagnose/Primärcode/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Diagnose/Primärcode/Structure/Structure/Schweregrad/null_flavour
   */
  @Path("/data[at0001]/items[at0005 and name/value='Schweregrad']/null_flavour")
  @Choice
  private PrimaercodeSchweregradNullFlavourChoice schweregradNullFlavour;

  /**
   * Path: Diagnose/Primärcode/Schweregrad
   * Description: Eine Gesamtbeurteilung des Schweregrades des Problems oder der Diagnose.
   * Comment: Ist der Schweregrad über vordefinierte Codes im Element "Name des Problems/ der Diagnose" enthalten, wird dieses Datenelement überflüssig. Hinweis: Eine spezifischere Einstufung des Schweregrads kann mit Hilfe des SLOTs "Spezifische Angaben" angegeben werden.
   */
  @Path("/data[at0001]/items[at0005 and name/value='Schweregrad']/value")
  @Choice
  private PrimaercodeSchweregradChoice schweregrad;

  public void setKodierteDiagnose(DvCodedText kodierteDiagnose) {
     this.kodierteDiagnose = kodierteDiagnose;
  }

  public DvCodedText getKodierteDiagnose() {
     return this.kodierteDiagnose ;
  }

  public void setKodierteDiagnoseNullFlavourDefiningCode(
      NullFlavour kodierteDiagnoseNullFlavourDefiningCode) {
     this.kodierteDiagnoseNullFlavourDefiningCode = kodierteDiagnoseNullFlavourDefiningCode;
  }

  public NullFlavour getKodierteDiagnoseNullFlavourDefiningCode() {
     return this.kodierteDiagnoseNullFlavourDefiningCode ;
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

  public void setKoerperstelle(List<PrimaercodeKoerperstelleElement> koerperstelle) {
     this.koerperstelle = koerperstelle;
  }

  public List<PrimaercodeKoerperstelleElement> getKoerperstelle() {
     return this.koerperstelle ;
  }

  public void setSeitenlokalisation(List<PrimaercodeSeitenlokalisationElement> seitenlokalisation) {
     this.seitenlokalisation = seitenlokalisation;
  }

  public List<PrimaercodeSeitenlokalisationElement> getSeitenlokalisation() {
     return this.seitenlokalisation ;
  }

  public void setAnatomischeStelleStrukturiert(List<Cluster> anatomischeStelleStrukturiert) {
     this.anatomischeStelleStrukturiert = anatomischeStelleStrukturiert;
  }

  public List<Cluster> getAnatomischeStelleStrukturiert() {
     return this.anatomischeStelleStrukturiert ;
  }

  public void setKlinischRelevanterZeitraumZeitpunktDesAuftretensValue(
      TemporalAccessor klinischRelevanterZeitraumZeitpunktDesAuftretensValue) {
     this.klinischRelevanterZeitraumZeitpunktDesAuftretensValue = klinischRelevanterZeitraumZeitpunktDesAuftretensValue;
  }

  public TemporalAccessor getKlinischRelevanterZeitraumZeitpunktDesAuftretensValue() {
     return this.klinischRelevanterZeitraumZeitpunktDesAuftretensValue ;
  }

  public void setKlinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode(
      NullFlavour klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode) {
     this.klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode = klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode() {
     return this.klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode ;
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

  public void setLebensphase(LebensphaseCluster lebensphase) {
     this.lebensphase = lebensphase;
  }

  public LebensphaseCluster getLebensphase() {
     return this.lebensphase ;
  }

  public void setMehrfachkodierungskennzeichenIcd10Gm(
      MehrfachkodierungskennzeichenIcd10GmCluster mehrfachkodierungskennzeichenIcd10Gm) {
     this.mehrfachkodierungskennzeichenIcd10Gm = mehrfachkodierungskennzeichenIcd10Gm;
  }

  public MehrfachkodierungskennzeichenIcd10GmCluster getMehrfachkodierungskennzeichenIcd10Gm() {
     return this.mehrfachkodierungskennzeichenIcd10Gm ;
  }

  public void setKlinischRelevanterZeitraumZeitpunktDerGenesungValue(
      TemporalAccessor klinischRelevanterZeitraumZeitpunktDerGenesungValue) {
     this.klinischRelevanterZeitraumZeitpunktDerGenesungValue = klinischRelevanterZeitraumZeitpunktDerGenesungValue;
  }

  public TemporalAccessor getKlinischRelevanterZeitraumZeitpunktDerGenesungValue() {
     return this.klinischRelevanterZeitraumZeitpunktDerGenesungValue ;
  }

  public void setKlinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode(
      NullFlavour klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode) {
     this.klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode = klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode() {
     return this.klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode ;
  }

  public void setKlinischerStatus(KlinischerStatusCluster klinischerStatus) {
     this.klinischerStatus = klinischerStatus;
  }

  public KlinischerStatusCluster getKlinischerStatus() {
     return this.klinischerStatus ;
  }

  public void setDiagnosesicherheit(DvCodedText diagnosesicherheit) {
     this.diagnosesicherheit = diagnosesicherheit;
  }

  public DvCodedText getDiagnosesicherheit() {
     return this.diagnosesicherheit ;
  }

  public void setDiagnosesicherheitNullFlavourDefiningCode(
      NullFlavour diagnosesicherheitNullFlavourDefiningCode) {
     this.diagnosesicherheitNullFlavourDefiningCode = diagnosesicherheitNullFlavourDefiningCode;
  }

  public NullFlavour getDiagnosesicherheitNullFlavourDefiningCode() {
     return this.diagnosesicherheitNullFlavourDefiningCode ;
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

  public void setSchweregradNullFlavour(
      PrimaercodeSchweregradNullFlavourChoice schweregradNullFlavour) {
     this.schweregradNullFlavour = schweregradNullFlavour;
  }

  public PrimaercodeSchweregradNullFlavourChoice getSchweregradNullFlavour() {
     return this.schweregradNullFlavour ;
  }

  public void setSchweregrad(PrimaercodeSchweregradChoice schweregrad) {
     this.schweregrad = schweregrad;
  }

  public PrimaercodeSchweregradChoice getSchweregrad() {
     return this.schweregrad ;
  }
}
