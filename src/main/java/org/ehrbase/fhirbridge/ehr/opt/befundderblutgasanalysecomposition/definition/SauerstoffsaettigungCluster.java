package org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
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
    date = "2021-03-09T12:08:29.697719+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class SauerstoffsaettigungCluster implements LocatableEntity {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/untersuchter Analyt
   * Description: Die Bezeichnung des Analyt-Resultats
   * Comment: Der Wert dieses Elements wird normalerweise meist durch eine Spezialisierung, durch einer Vorlage oder zur Laufzeit geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum','Hämoglobin'.
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024]/value|defining_code")
  private UntersuchterAnalytDefiningCode4 untersuchterAnalytDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/Sauerstoffsättigung/untersuchter Analyt/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour untersuchterAnalytNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyt-Resultat
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. '7.3 mmol/l', 'Erhöht'. Der 'Any'-Datentyp wird dann
   * durch eine Spezialisierung, eine Vorlage oder zur Laufzeit
   * auf einen passenden Datentypen eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der 'Quantity'-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001]/value|magnitude")
  private Double analytResultatMagnitude;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyt-Resultat
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. '7.3 mmol/l', 'Erhöht'. Der 'Any'-Datentyp wird dann
   * durch eine Spezialisierung, eine Vorlage oder zur Laufzeit
   * auf einen passenden Datentypen eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der 'Quantity'-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001]/value|units")
  private String analytResultatUnits;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/Sauerstoffsättigung/Analyt-Resultat/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour analytResultatNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   * Comment: Die Werte wurden analog zum HL7 FHIR Diagnostic Report gewählt, die wiederum aus der HL7-Praxis stammen. Andere Codes/Ausdrücke können über den Text 'choice' verwendet werden.
   */
  @Path("/items[at0005]/value|value")
  private String ergebnisStatusValue;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/Sauerstoffsättigung/Ergebnis-Status/null_flavour
   */
  @Path("/items[at0005]/null_flavour|defining_code")
  private NullFlavour ergebnisStatusNullFlavourDefiningCode;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffsättigung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setUntersuchterAnalytDefiningCode(
      UntersuchterAnalytDefiningCode4 untersuchterAnalytDefiningCode) {
     this.untersuchterAnalytDefiningCode = untersuchterAnalytDefiningCode;
  }

  public UntersuchterAnalytDefiningCode4 getUntersuchterAnalytDefiningCode() {
     return this.untersuchterAnalytDefiningCode ;
  }

  public void setUntersuchterAnalytNullFlavourDefiningCode(
      NullFlavour untersuchterAnalytNullFlavourDefiningCode) {
     this.untersuchterAnalytNullFlavourDefiningCode = untersuchterAnalytNullFlavourDefiningCode;
  }

  public NullFlavour getUntersuchterAnalytNullFlavourDefiningCode() {
     return this.untersuchterAnalytNullFlavourDefiningCode ;
  }

  public void setAnalytResultatMagnitude(Double analytResultatMagnitude) {
     this.analytResultatMagnitude = analytResultatMagnitude;
  }

  public Double getAnalytResultatMagnitude() {
     return this.analytResultatMagnitude ;
  }

  public void setAnalytResultatUnits(String analytResultatUnits) {
     this.analytResultatUnits = analytResultatUnits;
  }

  public String getAnalytResultatUnits() {
     return this.analytResultatUnits ;
  }

  public void setAnalytResultatNullFlavourDefiningCode(
      NullFlavour analytResultatNullFlavourDefiningCode) {
     this.analytResultatNullFlavourDefiningCode = analytResultatNullFlavourDefiningCode;
  }

  public NullFlavour getAnalytResultatNullFlavourDefiningCode() {
     return this.analytResultatNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
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
