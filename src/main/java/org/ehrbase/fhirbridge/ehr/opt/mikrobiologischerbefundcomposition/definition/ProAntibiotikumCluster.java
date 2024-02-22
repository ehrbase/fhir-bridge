package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

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
    date = "2024-02-22T14:23:00.214463+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class ProAntibiotikumCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Antibiotikum
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024 and name/value='Antibiotikum']/value")
  private DvCodedText antibiotikum;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Antibiotikum/null_flavour
   */
  @Path("/items[at0024 and name/value='Antibiotikum']/null_flavour|defining_code")
  private NullFlavour antibiotikumNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Minimale Hemmkonzentration
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|magnitude")
  private Double minimaleHemmkonzentrationMagnitude;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Minimale Hemmkonzentration
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/value|units")
  private String minimaleHemmkonzentrationUnits;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Minimale Hemmkonzentration/null_flavour
   */
  @Path("/items[at0001 and name/value='Minimale Hemmkonzentration']/null_flavour|defining_code")
  private NullFlavour minimaleHemmkonzentrationNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Analyseergebnis-Details
   * Description: Weitere Details zu einem einzelnen Ergebnis.
   */
  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Resistenz
   * Description: Zusätzliche Hinweise zur Anwendbarkeit des Referenzbereichs für dieses Resultat oder (codierter) Text, ob das Resultat im Referenzbereich ist oder nicht.
   * Comment: z.B.: 'im Referenzbereich, bezogen auf Alter und Geschlecht'.
   */
  @Path("/items[at0004 and name/value='Resistenz']/value")
  private DvCodedText resistenz;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Resistenz/null_flavour
   */
  @Path("/items[at0004 and name/value='Resistenz']/null_flavour|defining_code")
  private NullFlavour resistenzNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Kommentar/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("/items[at0028]/value")
  @Choice
  private ProAntibiotikumTestmethodeChoice testmethode;

  public void setAntibiotikum(DvCodedText antibiotikum) {
     this.antibiotikum = antibiotikum;
  }

  public DvCodedText getAntibiotikum() {
     return this.antibiotikum ;
  }

  public void setAntibiotikumNullFlavourDefiningCode(
      NullFlavour antibiotikumNullFlavourDefiningCode) {
     this.antibiotikumNullFlavourDefiningCode = antibiotikumNullFlavourDefiningCode;
  }

  public NullFlavour getAntibiotikumNullFlavourDefiningCode() {
     return this.antibiotikumNullFlavourDefiningCode ;
  }

  public void setMinimaleHemmkonzentrationMagnitude(Double minimaleHemmkonzentrationMagnitude) {
     this.minimaleHemmkonzentrationMagnitude = minimaleHemmkonzentrationMagnitude;
  }

  public Double getMinimaleHemmkonzentrationMagnitude() {
     return this.minimaleHemmkonzentrationMagnitude ;
  }

  public void setMinimaleHemmkonzentrationUnits(String minimaleHemmkonzentrationUnits) {
     this.minimaleHemmkonzentrationUnits = minimaleHemmkonzentrationUnits;
  }

  public String getMinimaleHemmkonzentrationUnits() {
     return this.minimaleHemmkonzentrationUnits ;
  }

  public void setMinimaleHemmkonzentrationNullFlavourDefiningCode(
      NullFlavour minimaleHemmkonzentrationNullFlavourDefiningCode) {
     this.minimaleHemmkonzentrationNullFlavourDefiningCode = minimaleHemmkonzentrationNullFlavourDefiningCode;
  }

  public NullFlavour getMinimaleHemmkonzentrationNullFlavourDefiningCode() {
     return this.minimaleHemmkonzentrationNullFlavourDefiningCode ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }

  public void setResistenz(DvCodedText resistenz) {
     this.resistenz = resistenz;
  }

  public DvCodedText getResistenz() {
     return this.resistenz ;
  }

  public void setResistenzNullFlavourDefiningCode(NullFlavour resistenzNullFlavourDefiningCode) {
     this.resistenzNullFlavourDefiningCode = resistenzNullFlavourDefiningCode;
  }

  public NullFlavour getResistenzNullFlavourDefiningCode() {
     return this.resistenzNullFlavourDefiningCode ;
  }

  public void setTestmethodeNullFlavourDefiningCode(
      NullFlavour testmethodeNullFlavourDefiningCode) {
     this.testmethodeNullFlavourDefiningCode = testmethodeNullFlavourDefiningCode;
  }

  public NullFlavour getTestmethodeNullFlavourDefiningCode() {
     return this.testmethodeNullFlavourDefiningCode ;
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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTestmethode(ProAntibiotikumTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public ProAntibiotikumTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }
}
