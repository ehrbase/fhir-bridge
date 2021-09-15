package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
    date = "2021-09-08T14:37:10.991079+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ProLaboranalytCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Bezeichnung des Analyts
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024]/value|defining_code")
  private BezeichnungDesAnalytsDefiningCode bezeichnungDesAnalytsDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Bezeichnung des Analyts/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour bezeichnungDesAnalytsNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Messwert/null_flavour
   */
  @Path("/items[at0001 and name/value='Messwert']/null_flavour|defining_code")
  private NullFlavour messwertNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Analyseergebnis-Detail
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetail;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Interpretation
   * Description: Zusätzliche Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob das Ergebnis im Referenzbereich ist oder nicht.
   * Comment: z.B.: 'im Referenzbereich, bezogen auf Alter und Geschlecht'.
   */
  @Path("/items[at0004 and name/value='Interpretation']/value|defining_code")
  private InterpretationDefiningCode interpretationDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Interpretation/null_flavour
   */
  @Path("/items[at0004 and name/value='Interpretation']/null_flavour|defining_code")
  private NullFlavour interpretationNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Zeitpunkt der Validierung
   * Description: Datum und Uhrzeit der Validierung des Analyt-Ergebnisses im Labor durch einen Arzt.
   * Comment: In vielen Gerichtsbarkeiten wird angenommen, dass der "Ergebnis-Status" die medizinische Validierung einschliesst, in anderen wird diese anhand dieses Datenelements separat erfasst und befundet.
   */
  @Path("/items[at0025]/value|value")
  private TemporalAccessor zeitpunktDerValidierungValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Zeitpunkt der Validierung/null_flavour
   */
  @Path("/items[at0025]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerValidierungNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Ergebnis-Status/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour ergebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Zeitpunkt Ergebnis-Status
   * Description: Datum und/oder Zeitpunkt an dem der Status für das Analyseergebnis gesetzt wurde.
   */
  @Path("/items[at0006]/value|value")
  private TemporalAccessor zeitpunktErgebnisStatusValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Zeitpunkt Ergebnis-Status/null_flavour
   */
  @Path("/items[at0006]/null_flavour|defining_code")
  private NullFlavour zeitpunktErgebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Probe ID/null_flavour
   */
  @Path("/items[at0026 and name/value='Probe ID']/null_flavour|defining_code")
  private NullFlavour probeIdNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]")
  private List<ProLaboranalytKommentarElement> kommentar;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("/items[at0028]/value")
  @Choice
  private ProLaboranalytTestmethodeChoice testmethode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Probe ID
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   */
  @Path("/items[at0026 and name/value='Probe ID']/value")
  @Choice
  private ProLaboranalytProbeIdChoice probeId;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   */
  @Path("/items[at0005]/value")
  @Choice
  private ProLaboranalytErgebnisStatusChoice ergebnisStatus;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Messwert
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   */
  @Path("/items[at0001 and name/value='Messwert']/value")
  @Choice
  private ProLaboranalytMesswertChoice messwert;

  public void setBezeichnungDesAnalytsDefiningCode(
      BezeichnungDesAnalytsDefiningCode bezeichnungDesAnalytsDefiningCode) {
     this.bezeichnungDesAnalytsDefiningCode = bezeichnungDesAnalytsDefiningCode;
  }

  public BezeichnungDesAnalytsDefiningCode getBezeichnungDesAnalytsDefiningCode() {
     return this.bezeichnungDesAnalytsDefiningCode ;
  }

  public void setBezeichnungDesAnalytsNullFlavourDefiningCode(
      NullFlavour bezeichnungDesAnalytsNullFlavourDefiningCode) {
     this.bezeichnungDesAnalytsNullFlavourDefiningCode = bezeichnungDesAnalytsNullFlavourDefiningCode;
  }

  public NullFlavour getBezeichnungDesAnalytsNullFlavourDefiningCode() {
     return this.bezeichnungDesAnalytsNullFlavourDefiningCode ;
  }

  public void setMesswertNullFlavourDefiningCode(NullFlavour messwertNullFlavourDefiningCode) {
     this.messwertNullFlavourDefiningCode = messwertNullFlavourDefiningCode;
  }

  public NullFlavour getMesswertNullFlavourDefiningCode() {
     return this.messwertNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetail(List<Cluster> analyseergebnisDetail) {
     this.analyseergebnisDetail = analyseergebnisDetail;
  }

  public List<Cluster> getAnalyseergebnisDetail() {
     return this.analyseergebnisDetail ;
  }

  public void setInterpretationDefiningCode(InterpretationDefiningCode interpretationDefiningCode) {
     this.interpretationDefiningCode = interpretationDefiningCode;
  }

  public InterpretationDefiningCode getInterpretationDefiningCode() {
     return this.interpretationDefiningCode ;
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

  public void setTestmethode(ProLaboranalytTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public ProLaboranalytTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }

  public void setProbeId(ProLaboranalytProbeIdChoice probeId) {
     this.probeId = probeId;
  }

  public ProLaboranalytProbeIdChoice getProbeId() {
     return this.probeId ;
  }

  public void setErgebnisStatus(ProLaboranalytErgebnisStatusChoice ergebnisStatus) {
     this.ergebnisStatus = ergebnisStatus;
  }

  public ProLaboranalytErgebnisStatusChoice getErgebnisStatus() {
     return this.ergebnisStatus ;
  }

  public void setMesswert(ProLaboranalytMesswertChoice messwert) {
     this.messwert = messwert;
  }

  public ProLaboranalytMesswertChoice getMesswert() {
     return this.messwert ;
  }
}
