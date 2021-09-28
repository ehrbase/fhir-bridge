package org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
    date = "2021-09-28T15:04:42.652180+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ProAnalytCluster implements LocatableEntity {
  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Virusnachweistest
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024 and name/value='Virusnachweistest']/value|defining_code")
  private VirusnachweistestDefiningCode virusnachweistestDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Virusnachweistest/null_flavour
   */
  @Path("/items[at0024 and name/value='Virusnachweistest']/null_flavour|defining_code")
  private NullFlavour virusnachweistestNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Nachweis
   * Description: (Mess-)Wert des Analyt-Ergebnisses.
   * Comment: Z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Nachweis']/value")
  private DvCodedText nachweis;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Nachweis/null_flavour
   */
  @Path("/items[at0001 and name/value='Nachweis']/null_flavour|defining_code")
  private NullFlavour nachweisNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Analyseergebnis-Detail
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetail;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   * Comment: Die Werte wurden speziell so ausgewählt, dass sie mit denen im HL7 FHIR-Diagnosebericht übereinstimmen, der ursprünglich aus der HL7v2-Praxis abgeleitet wurde. Andere lokale Codes / Begriffe können über die Textauswahl verwendet werden.
   *
   * Dieses Element ermöglicht mehrere Vorkommen, um Anwendungsfälle zu unterstützen, wo mehr als eine Art von Status implementiert werden muss.
   */
  @Path("/items[at0005]/value|value")
  private String ergebnisStatusValue;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Ergebnis-Status/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour ergebnisStatusNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("/items[at0028]/value")
  @Choice
  private ProAnalytTestmethodeChoice testmethode;

  public void setVirusnachweistestDefiningCode(
      VirusnachweistestDefiningCode virusnachweistestDefiningCode) {
     this.virusnachweistestDefiningCode = virusnachweistestDefiningCode;
  }

  public VirusnachweistestDefiningCode getVirusnachweistestDefiningCode() {
     return this.virusnachweistestDefiningCode ;
  }

  public void setVirusnachweistestNullFlavourDefiningCode(
      NullFlavour virusnachweistestNullFlavourDefiningCode) {
     this.virusnachweistestNullFlavourDefiningCode = virusnachweistestNullFlavourDefiningCode;
  }

  public NullFlavour getVirusnachweistestNullFlavourDefiningCode() {
     return this.virusnachweistestNullFlavourDefiningCode ;
  }

  public void setNachweis(DvCodedText nachweis) {
     this.nachweis = nachweis;
  }

  public DvCodedText getNachweis() {
     return this.nachweis ;
  }

  public void setNachweisNullFlavourDefiningCode(NullFlavour nachweisNullFlavourDefiningCode) {
     this.nachweisNullFlavourDefiningCode = nachweisNullFlavourDefiningCode;
  }

  public NullFlavour getNachweisNullFlavourDefiningCode() {
     return this.nachweisNullFlavourDefiningCode ;
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

  public void setErgebnisStatusValue(String ergebnisStatusValue) {
     this.ergebnisStatusValue = ergebnisStatusValue;
  }

  public String getErgebnisStatusValue() {
     return this.ergebnisStatusValue ;
  }

  public void setErgebnisStatusNullFlavourDefiningCode(
      NullFlavour ergebnisStatusNullFlavourDefiningCode) {
     this.ergebnisStatusNullFlavourDefiningCode = ergebnisStatusNullFlavourDefiningCode;
  }

  public NullFlavour getErgebnisStatusNullFlavourDefiningCode() {
     return this.ergebnisStatusNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTestmethode(ProAnalytTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public ProAnalytTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }
}
