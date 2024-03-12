package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
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
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.178910+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class BefundObservation implements EntryEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Labortest-Bezeichnung
   * Description: Name der Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde.
   * Comment: Ein Laborergebnis kann sich auf ein einzelnes Analyt oder eine Analytgruppe beziehen. Dazu zählen auch komplette Panel an Parametern. 
   * Es wird dringend empfohlen, die "Labortest-Bezeichnung" anhand einer Terminologie zu kodiereren, wie zum Beispiel LOINC oder SNOMED CT. Beispiel: "Glukose", "Harnstoff", "Abstrich", "Cortisol", "Leberbiopsie". Der Name kann u.U. auch das Probenmaterial oder den Patientenstatus (z.B. "Blutzuckerspiegel nüchtern") oder andere Informationen beinhalten wie "Kalium (Blutgas)".
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code")
  private LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Bezeichnung/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour labortestBezeichnungNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Probe
   * Description: Eine physikalische Probe zur Erforschung, Untersuchung oder Analyse, die von einer Person entnommen wurde oder die sich auf die Person bezieht.
   * Comment: Zum Beispiel: Gewebe, Körperflüssigkeit oder Lebensmittel.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]")
  private List<ProbeCluster> probe;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur
   * Description: Laborergebnis als Panel/Profil von Einzelresultaten. Verbreitet im medizinischen Labor.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Kultur']")
  private List<KulturCluster> kultur;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Strukturierte Testdiagnostik
   * Description: Eine strukturierte oder komplexe Diagnose für die Laboruntersuchung.
   * Comment: Zum Beispiel: Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie Morphologie, Ätiologie und Funktion zusammengesetzt sind.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Multimedia-Darstellung
   * Description: Bild, Video oder Diagramm zur Visualisierung des Testergebnisses.
   * Comment: Mehrere Formate sind erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
   * Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue Interpretation der Messung oder des Prüfergebnisses auswirken.
   * Comment: Zum Beispiel: Letzte normale Menstruationsperiode (LNMP).
   */
  @Path("/data[at0001]/events[at0002]/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStoerfaktoren;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Empfängerstandort
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/protocol[at0004]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Empfängerstandort']")
  private EmpfaengerstandortCluster empfaengerstandort;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Anforderung
   * Description: Name des ursprünglich angeforderten Tests.
   * Comment: Dieses Datenelement ist zu verwenden, wenn die angeforderte Testung von der tatsächlich vom Labor durchgeführten Testung abweicht.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0106 and name/value='Anforderung']")
  private List<BefundAnforderungElement> anforderung;

  /**
   * Path: Mikrobiologischer Befund/Befund/Tree/Details der Testanforderung/Auftrags-ID des anfordernden/einsendenden Systems/null_flavour
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0062]/null_flavour|defining_code")
  private NullFlavour auftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Tree/Details der Testanforderung/Auftrags-ID (Empfänger)/null_flavour
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0063]/null_flavour|defining_code")
  private NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Einsenderstandort
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/protocol[at0004]/items[at0094]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Einsenderstandort']")
  private EinsenderstandortCluster einsenderstandort;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Verteilerliste
   * Description: Details über weitere Kliniker oder Organisationen, die eine Kopie der Analyseergebnisse benötigen.
   * Comment: Die "Verteilerliste" dient nur zu Informationszwecken. Der Hauptempfänger des Berichts ist die Person, die dazu bestimmt ist, auf die Information zu reagieren.
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0035]")
  private List<Cluster> verteilerliste;

  /**
   * Path: Mikrobiologischer Befund/Befund/Test Details
   * Description: Strukturierte Details über die beim Labortest verwendete Methodik, das Gerät oder die Auswertung.
   * Comment: Zum Beispiel: "Details der ELISA/Nephelometrie".
   */
  @Path("/protocol[at0004]/items[at0110]")
  private List<Cluster> testDetails;

  /**
   * Path: Mikrobiologischer Befund/Befund/Erweiterung
   * Description: Weitere Informationen, die erforderlich sind, um lokale Inhalte abzubilden oder das Modell an andere Referenzmodelle anzupassen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten, um ein Mapping auf FHIR oder CIMI Modelle zu ermöglichen.
   */
  @Path("/protocol[at0004]/items[at0117]")
  private List<Cluster> erweiterung;

  /**
   * Path: Mikrobiologischer Befund/Befund/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Mikrobiologischer Befund/Befund/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Mikrobiologischer Befund/Befund/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Auftrags-ID des anfordernden/einsendenden Systems
   * Description: Lokale Auftrags-ID des anfordernden/einsendenden Systems.
   * Comment: Äquivalent zur "HL7 Placer Order Identifier".
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0062]/value")
  @Choice
  private BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice auftragsIdDesAnforderndenEinsendendenSystems;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Auftrags-ID (Empfänger)
   * Description: Lokale Auftrags-ID, die vom auftragsempfangendem System, gewöhnlich dem Laborinformationssystem (LIS) zugewiesen wird.
   * Comment: Die Vergabe einer solchen ID ermöglicht das Nachverfolgen des Auftragsstatus und das Verlinken der Ergebnisse zum Auftrag. Es erlaubt auch das Verwalten von weiteren Erkundigungen und Nachfragen und ist äquivalent zum "HL7 Filler Order Identifier".
   */
  @Path("/protocol[at0004]/items[at0094]/items[at0063]/value")
  @Choice
  private BefundAuftragsIdEmpfaengerChoice auftragsIdEmpfaenger;

  public void setLabortestBezeichnungDefiningCode(
      LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode) {
     this.labortestBezeichnungDefiningCode = labortestBezeichnungDefiningCode;
  }

  public LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode() {
     return this.labortestBezeichnungDefiningCode ;
  }

  public void setLabortestBezeichnungNullFlavourDefiningCode(
      NullFlavour labortestBezeichnungNullFlavourDefiningCode) {
     this.labortestBezeichnungNullFlavourDefiningCode = labortestBezeichnungNullFlavourDefiningCode;
  }

  public NullFlavour getLabortestBezeichnungNullFlavourDefiningCode() {
     return this.labortestBezeichnungNullFlavourDefiningCode ;
  }

  public void setProbe(List<ProbeCluster> probe) {
     this.probe = probe;
  }

  public List<ProbeCluster> getProbe() {
     return this.probe ;
  }

  public void setKultur(List<KulturCluster> kultur) {
     this.kultur = kultur;
  }

  public List<KulturCluster> getKultur() {
     return this.kultur ;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
     this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
     return this.strukturierteTestdiagnostik ;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
     this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
     return this.multimediaDarstellung ;
  }

  public void setStrukturierteErfassungDerStoerfaktoren(
      List<Cluster> strukturierteErfassungDerStoerfaktoren) {
     this.strukturierteErfassungDerStoerfaktoren = strukturierteErfassungDerStoerfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStoerfaktoren() {
     return this.strukturierteErfassungDerStoerfaktoren ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setEmpfaengerstandort(EmpfaengerstandortCluster empfaengerstandort) {
     this.empfaengerstandort = empfaengerstandort;
  }

  public EmpfaengerstandortCluster getEmpfaengerstandort() {
     return this.empfaengerstandort ;
  }

  public void setAnforderung(List<BefundAnforderungElement> anforderung) {
     this.anforderung = anforderung;
  }

  public List<BefundAnforderungElement> getAnforderung() {
     return this.anforderung ;
  }

  public void setAuftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode(
      NullFlavour auftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode) {
     this.auftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode = auftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode;
  }

  public NullFlavour getAuftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode() {
     return this.auftragsIdDesAnforderndenEinsendendenSystemsNullFlavourDefiningCode ;
  }

  public void setAuftragsIdEmpfaengerNullFlavourDefiningCode(
      NullFlavour auftragsIdEmpfaengerNullFlavourDefiningCode) {
     this.auftragsIdEmpfaengerNullFlavourDefiningCode = auftragsIdEmpfaengerNullFlavourDefiningCode;
  }

  public NullFlavour getAuftragsIdEmpfaengerNullFlavourDefiningCode() {
     return this.auftragsIdEmpfaengerNullFlavourDefiningCode ;
  }

  public void setEinsenderstandort(EinsenderstandortCluster einsenderstandort) {
     this.einsenderstandort = einsenderstandort;
  }

  public EinsenderstandortCluster getEinsenderstandort() {
     return this.einsenderstandort ;
  }

  public void setVerteilerliste(List<Cluster> verteilerliste) {
     this.verteilerliste = verteilerliste;
  }

  public List<Cluster> getVerteilerliste() {
     return this.verteilerliste ;
  }

  public void setTestDetails(List<Cluster> testDetails) {
     this.testDetails = testDetails;
  }

  public List<Cluster> getTestDetails() {
     return this.testDetails ;
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

  public void setAuftragsIdDesAnforderndenEinsendendenSystems(
      BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice auftragsIdDesAnforderndenEinsendendenSystems) {
     this.auftragsIdDesAnforderndenEinsendendenSystems = auftragsIdDesAnforderndenEinsendendenSystems;
  }

  public BefundAuftragsIdDesAnforderndenEinsendendenSystemsChoice getAuftragsIdDesAnforderndenEinsendendenSystems(
      ) {
     return this.auftragsIdDesAnforderndenEinsendendenSystems ;
  }

  public void setAuftragsIdEmpfaenger(BefundAuftragsIdEmpfaengerChoice auftragsIdEmpfaenger) {
     this.auftragsIdEmpfaenger = auftragsIdEmpfaenger;
  }

  public BefundAuftragsIdEmpfaengerChoice getAuftragsIdEmpfaenger() {
     return this.auftragsIdEmpfaenger ;
  }
}
