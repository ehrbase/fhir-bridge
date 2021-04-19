package org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-17T16:15:08.659586300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
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
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Nachweis']/value|defining_code")
  private NachweisDefiningCode nachweisDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Nachweis/null_flavour
   */
  @Path("/items[at0001 and name/value='Nachweis']/null_flavour|defining_code")
  private NullFlavour nachweisNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Labortest-Panel/Pro Analyt/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   * Comment: Die Werte wurden analog zum HL7 FHIR Diagnostic Report gewählt, die wiederum aus der HL7-Praxis stammen. Andere Codes/Ausdrücke können über den Text 'choice' verwendet werden.
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

  public void setNachweisDefiningCode(NachweisDefiningCode nachweisDefiningCode) {
     this.nachweisDefiningCode = nachweisDefiningCode;
  }

  public NachweisDefiningCode getNachweisDefiningCode() {
     return this.nachweisDefiningCode ;
  }

  public void setNachweisNullFlavourDefiningCode(NullFlavour nachweisNullFlavourDefiningCode) {
     this.nachweisNullFlavourDefiningCode = nachweisNullFlavourDefiningCode;
  }

  public NullFlavour getNachweisNullFlavourDefiningCode() {
     return this.nachweisNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
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
}
