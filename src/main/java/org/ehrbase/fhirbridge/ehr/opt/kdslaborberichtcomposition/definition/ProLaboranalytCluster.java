package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-24T14:43:52.696236166+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
public class ProLaboranalytCluster implements LocatableEntity {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Bezeichnung des Analyts
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024]/value")
  private DvCodedText bezeichnungDesAnalyts;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Bezeichnung des Analyts/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour bezeichnungDesAnalytsNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   * Comment: Z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Messwert']")
  private List<ProLaboranalytMesswertElement> messwert;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Analyseergebnis-Detail
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetail;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Interpretation
   * Description: Zusätzliche Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob das Ergebnis im Referenzbereich ist oder nicht.
   * Comment: z.B.: 'im Referenzbereich, bezogen auf Alter und Geschlecht'.
   */
  @Path("/items[at0004 and name/value='Interpretation']/value")
  private DvCodedText interpretation;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Interpretation/null_flavour
   */
  @Path("/items[at0004 and name/value='Interpretation']/null_flavour|defining_code")
  private NullFlavour interpretationNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Zeitpunkt der Validierung
   * Description: Datum und Uhrzeit der Validierung des Analyt-Ergebnisses im Labor durch einen Arzt.
   * Comment: In vielen Gerichtsbarkeiten wird angenommen, dass der "Ergebnis-Status" die medizinische Validierung einschliesst, in anderen wird diese anhand dieses Datenelements separat erfasst und befundet.
   */
  @Path("/items[at0025]/value|value")
  private TemporalAccessor zeitpunktDerValidierungValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Zeitpunkt der Validierung/null_flavour
   */
  @Path("/items[at0025]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerValidierungNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   * Comment: Die Werte wurden speziell so ausgewählt, dass sie mit denen im HL7 FHIR-Diagnosebericht übereinstimmen, der ursprünglich aus der HL7v2-Praxis abgeleitet wurde. Andere lokale Codes / Begriffe können über die Textauswahl verwendet werden.
   *
   * Dieses Element ermöglicht mehrere Vorkommen, um Anwendungsfälle zu unterstützen, wo mehr als eine Art von Status implementiert werden muss.
   */
  @Path("/items[at0005]/value|defining_code")
  private ErgebnisStatusDefiningCode ergebnisStatusDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Ergebnis-Status/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour ergebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Zeitpunkt Ergebnis-Status
   * Description: Datum und/oder Zeitpunkt an dem der Status für das Analyseergebnis gesetzt wurde.
   */
  @Path("/items[at0006]/value|value")
  private TemporalAccessor zeitpunktErgebnisStatusValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Zeitpunkt Ergebnis-Status/null_flavour
   */
  @Path("/items[at0006]/null_flavour|defining_code")
  private NullFlavour zeitpunktErgebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Probe ID/null_flavour
   */
  @Path("/items[at0026 and name/value='Probe ID']/null_flavour|defining_code")
  private NullFlavour probeIdNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]")
  private List<ProLaboranalytKommentarElement> kommentar;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
   */
  @Path("/items[at0026 and name/value='Probe ID']/value")
  @Choice
  private ProLaboranalytProbeIdChoice probeId;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Pro Laboranalyt/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   * Comment: Wenn die Testmethode für ein gesamtes Panel gilt, kann die Testmethode mithilfe des Datenelements "Testmethode" im Ergebnis OBSERVATION.laboratory_test_result erfasst werden.
   */
  @Path("/items[at0028]/value")
  @Choice
  private ProLaboranalytTestmethodeChoice testmethode;

  public void setBezeichnungDesAnalyts(DvCodedText bezeichnungDesAnalyts) {
     this.bezeichnungDesAnalyts = bezeichnungDesAnalyts;
  }

  public DvCodedText getBezeichnungDesAnalyts() {
     return this.bezeichnungDesAnalyts ;
  }

  public void setBezeichnungDesAnalytsNullFlavourDefiningCode(
      NullFlavour bezeichnungDesAnalytsNullFlavourDefiningCode) {
     this.bezeichnungDesAnalytsNullFlavourDefiningCode = bezeichnungDesAnalytsNullFlavourDefiningCode;
  }

  public NullFlavour getBezeichnungDesAnalytsNullFlavourDefiningCode() {
     return this.bezeichnungDesAnalytsNullFlavourDefiningCode ;
  }

  public void setMesswert(List<ProLaboranalytMesswertElement> messwert) {
     this.messwert = messwert;
  }

  public List<ProLaboranalytMesswertElement> getMesswert() {
     return this.messwert ;
  }

  public void setAnalyseergebnisDetail(List<Cluster> analyseergebnisDetail) {
     this.analyseergebnisDetail = analyseergebnisDetail;
  }

  public List<Cluster> getAnalyseergebnisDetail() {
     return this.analyseergebnisDetail ;
  }

  public void setInterpretation(DvCodedText interpretation) {
     this.interpretation = interpretation;
  }

  public DvCodedText getInterpretation() {
     return this.interpretation ;
  }

  public void setInterpretationNullFlavourDefiningCode(
      NullFlavour interpretationNullFlavourDefiningCode) {
     this.interpretationNullFlavourDefiningCode = interpretationNullFlavourDefiningCode;
  }

  public NullFlavour getInterpretationNullFlavourDefiningCode() {
     return this.interpretationNullFlavourDefiningCode ;
  }

  public void setTestmethodeNullFlavourDefiningCode(
      NullFlavour testmethodeNullFlavourDefiningCode) {
     this.testmethodeNullFlavourDefiningCode = testmethodeNullFlavourDefiningCode;
  }

  public NullFlavour getTestmethodeNullFlavourDefiningCode() {
     return this.testmethodeNullFlavourDefiningCode ;
  }

  public void setZeitpunktDerValidierungValue(TemporalAccessor zeitpunktDerValidierungValue) {
     this.zeitpunktDerValidierungValue = zeitpunktDerValidierungValue;
  }

  public TemporalAccessor getZeitpunktDerValidierungValue() {
     return this.zeitpunktDerValidierungValue ;
  }

  public void setZeitpunktDerValidierungNullFlavourDefiningCode(
      NullFlavour zeitpunktDerValidierungNullFlavourDefiningCode) {
     this.zeitpunktDerValidierungNullFlavourDefiningCode = zeitpunktDerValidierungNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDerValidierungNullFlavourDefiningCode() {
     return this.zeitpunktDerValidierungNullFlavourDefiningCode ;
  }

  public void setErgebnisStatusDefiningCode(ErgebnisStatusDefiningCode ergebnisStatusDefiningCode) {
     this.ergebnisStatusDefiningCode = ergebnisStatusDefiningCode;
  }

  public ErgebnisStatusDefiningCode getErgebnisStatusDefiningCode() {
     return this.ergebnisStatusDefiningCode ;
  }

  public void setErgebnisStatusNullFlavourDefiningCode(
      NullFlavour ergebnisStatusNullFlavourDefiningCode) {
     this.ergebnisStatusNullFlavourDefiningCode = ergebnisStatusNullFlavourDefiningCode;
  }

  public NullFlavour getErgebnisStatusNullFlavourDefiningCode() {
     return this.ergebnisStatusNullFlavourDefiningCode ;
  }

  public void setZeitpunktErgebnisStatusValue(TemporalAccessor zeitpunktErgebnisStatusValue) {
     this.zeitpunktErgebnisStatusValue = zeitpunktErgebnisStatusValue;
  }

  public TemporalAccessor getZeitpunktErgebnisStatusValue() {
     return this.zeitpunktErgebnisStatusValue ;
  }

  public void setZeitpunktErgebnisStatusNullFlavourDefiningCode(
      NullFlavour zeitpunktErgebnisStatusNullFlavourDefiningCode) {
     this.zeitpunktErgebnisStatusNullFlavourDefiningCode = zeitpunktErgebnisStatusNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktErgebnisStatusNullFlavourDefiningCode() {
     return this.zeitpunktErgebnisStatusNullFlavourDefiningCode ;
  }

  public void setProbeIdNullFlavourDefiningCode(NullFlavour probeIdNullFlavourDefiningCode) {
     this.probeIdNullFlavourDefiningCode = probeIdNullFlavourDefiningCode;
  }

  public NullFlavour getProbeIdNullFlavourDefiningCode() {
     return this.probeIdNullFlavourDefiningCode ;
  }

  public void setKommentar(List<ProLaboranalytKommentarElement> kommentar) {
     this.kommentar = kommentar;
  }

  public List<ProLaboranalytKommentarElement> getKommentar() {
     return this.kommentar ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setProbeId(ProLaboranalytProbeIdChoice probeId) {
     this.probeId = probeId;
  }

  public ProLaboranalytProbeIdChoice getProbeId() {
     return this.probeId ;
  }

  public void setTestmethode(ProLaboranalytTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public ProLaboranalytTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }
}
