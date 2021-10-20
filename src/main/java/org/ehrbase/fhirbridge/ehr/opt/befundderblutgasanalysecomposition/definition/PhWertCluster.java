package org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.Double;
import java.lang.String;
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
    date = "2021-10-19T12:08:14.450132+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PhWertCluster implements LocatableEntity {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Bezeichnung des Analyts
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024]/value")
  private DvCodedText bezeichnungDesAnalyts;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/pH-Wert/Bezeichnung des Analyts/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour bezeichnungDesAnalytsNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyt-Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   * Comment: Z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001]/value|magnitude")
  private Double analytErgebnisMagnitude;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyt-Ergebnis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   * Comment: Z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001]/value|units")
  private String analytErgebnisUnits;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/pH-Wert/Analyt-Ergebnis/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour analytErgebnisNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Analyseergebnis-Detail
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetail;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/pH-Wert/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   * Comment: Die Werte wurden speziell so ausgewählt, dass sie mit denen im HL7 FHIR-Diagnosebericht übereinstimmen, der ursprünglich aus der HL7v2-Praxis abgeleitet wurde. Andere lokale Codes / Begriffe können über die Textauswahl verwendet werden.
   *
   * Dieses Element ermöglicht mehrere Vorkommen, um Anwendungsfälle zu unterstützen, wo mehr als eine Art von Status implementiert werden muss.
   */
  @Path("/items[at0005]")
  private List<SauerstoffpartialdruckErgebnisStatusElement> ergebnisStatus;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/pH-Wert/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("/items[at0028]/value")
  @Choice
  private KohlendioxidpartialdruckTestmethodeChoice testmethode;

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

  public void setAnalytErgebnisMagnitude(Double analytErgebnisMagnitude) {
     this.analytErgebnisMagnitude = analytErgebnisMagnitude;
  }

  public Double getAnalytErgebnisMagnitude() {
     return this.analytErgebnisMagnitude ;
  }

  public void setAnalytErgebnisUnits(String analytErgebnisUnits) {
     this.analytErgebnisUnits = analytErgebnisUnits;
  }

  public String getAnalytErgebnisUnits() {
     return this.analytErgebnisUnits ;
  }

  public void setAnalytErgebnisNullFlavourDefiningCode(
      NullFlavour analytErgebnisNullFlavourDefiningCode) {
     this.analytErgebnisNullFlavourDefiningCode = analytErgebnisNullFlavourDefiningCode;
  }

  public NullFlavour getAnalytErgebnisNullFlavourDefiningCode() {
     return this.analytErgebnisNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetail(List<Cluster> analyseergebnisDetail) {
     this.analyseergebnisDetail = analyseergebnisDetail;
  }

  public List<Cluster> getAnalyseergebnisDetail() {
     return this.analyseergebnisDetail ;
  }

  public void setTestmethodeNullFlavourDefiningCode(
      NullFlavour testmethodeNullFlavourDefiningCode) {
     this.testmethodeNullFlavourDefiningCode = testmethodeNullFlavourDefiningCode;
  }

  public NullFlavour getTestmethodeNullFlavourDefiningCode() {
     return this.testmethodeNullFlavourDefiningCode ;
  }

  public void setErgebnisStatus(List<SauerstoffpartialdruckErgebnisStatusElement> ergebnisStatus) {
     this.ergebnisStatus = ergebnisStatus;
  }

  public List<SauerstoffpartialdruckErgebnisStatusElement> getErgebnisStatus() {
     return this.ergebnisStatus ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTestmethode(KohlendioxidpartialdruckTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public KohlendioxidpartialdruckTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }
}
