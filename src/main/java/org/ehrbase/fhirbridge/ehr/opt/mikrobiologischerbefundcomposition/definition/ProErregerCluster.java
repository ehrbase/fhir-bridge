package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.Long;
import java.lang.String;
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
    date = "2024-02-22T14:23:00.208190+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class ProErregerCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Isolatnummer
   * Description: Die beabsichtigte Position dieses Analyseergebnisses in der Reihenfolge aller Analyseergebnisse.
   * Comment: z.B. '1', '2', '3'. Werden mehrere Analysenergebnisse berichtet, gibt die Analyseergebnis-Reihenfolge explizit die Reihenfolge der Analyseergebnisse an.
   */
  @Path("/items[at0027 and name/value='Isolatnummer']/value|magnitude")
  private Long isolatnummerMagnitude;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Isolatnummer/null_flavour
   */
  @Path("/items[at0027 and name/value='Isolatnummer']/null_flavour|defining_code")
  private NullFlavour isolatnummerNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Nachweis
   * Description: Der Name des untersuchten Analyts.
   * Comment: Der Wert dieses Elements wird normalerweise, meist durch eine Spezialisierung, in einem Template oder zur Laufzeit der Anwendung geliefert, um den aktuellen Analyt wiederzugeben. Zum Beispiel: 'Natrium im Serum', 'Hämoglobin'. 
   * Die Codierung mit einer externen Terminologie, wie LOINC, NPU, SNOMED-CT oder lokalen Labor-Terminologien wird dringend empfohlen.
   */
  @Path("/items[at0024 and name/value='Nachweis']/value|value")
  private String nachweisValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Nachweis/null_flavour
   */
  @Path("/items[at0024 and name/value='Nachweis']/null_flavour|defining_code")
  private NullFlavour nachweisNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregername
   * Description: (Mess-)Wert des Analyt-Resultats.
   * Comment: z.B. "7,3 mmol/l", "Erhöht". Der "Any"-Datentyp wird dann durch eine Spezialisierung, eine Vorlage oder zur Laufzeit der Anwendung auf einen passenden Datentyp eingeschränkt werden müssen, um das aktuelle Analyt-Ergebnis wiederzugeben. Der "Quantity"-Datentyp hat Referenzmodell-Attribute, wie Kennungen für normal/abnormal, Referenzbereiche und Näherungen - für weitere Details s. https://specifications.openehr.org/releases/RM/latest/data_types.html#_dv_quantity_class .
   */
  @Path("/items[at0001 and name/value='Erregername']/value")
  private DvCodedText erregername;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregername/null_flavour
   */
  @Path("/items[at0001 and name/value='Erregername']/null_flavour|defining_code")
  private NullFlavour erregernameNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails
   * Description: Detaillierte Angaben zum nachgewiesenen Erreger.
   */
  @Path("/items[openEHR-EHR-CLUSTER.erregerdetails.v1]")
  private ErregerdetailsCluster erregerdetails;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Testmethode/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour testmethodeNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Zugehörige Laborprobe/null_flavour
   */
  @Path("/items[at0026 and name/value='Zugehörige Laborprobe']/null_flavour|defining_code")
  private NullFlavour zugehoerigeLaborprobeNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/items[at0003]/value|value")
  private String kommentarValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Kommentar/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Zugehörige Laborprobe
   * Description: Kennung der Probe, die für das Analyseergebnis verwendet wurde.
   * Comment: In manchen Situationen wird ein einzelner Laborergebnis-Archetyp mehrere Probe- und Laboranalyt-Ergebnis-Archetypen enthalten. In diesen Fällen wird dieses "Probe"-Datenelement benötigt, um die Ergebnisse mit den richtigen Proben zu verknüpfen.
   */
  @Path("/items[at0026 and name/value='Zugehörige Laborprobe']/value")
  @Choice
  private ProErregerZugehoerigeLaborprobeChoice zugehoerigeLaborprobe;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   * Comment: Wenn die Testmethode für ein gesamtes Panel gilt, kann die Testmethode mithilfe des Datenelements "Testmethode" im Ergebnis OBSERVATION.laboratory_test_result erfasst werden.
   */
  @Path("/items[at0028]/value")
  @Choice
  private ProErregerTestmethodeChoice testmethode;

  public void setIsolatnummerMagnitude(Long isolatnummerMagnitude) {
     this.isolatnummerMagnitude = isolatnummerMagnitude;
  }

  public Long getIsolatnummerMagnitude() {
     return this.isolatnummerMagnitude ;
  }

  public void setIsolatnummerNullFlavourDefiningCode(
      NullFlavour isolatnummerNullFlavourDefiningCode) {
     this.isolatnummerNullFlavourDefiningCode = isolatnummerNullFlavourDefiningCode;
  }

  public NullFlavour getIsolatnummerNullFlavourDefiningCode() {
     return this.isolatnummerNullFlavourDefiningCode ;
  }

  public void setNachweisValue(String nachweisValue) {
     this.nachweisValue = nachweisValue;
  }

  public String getNachweisValue() {
     return this.nachweisValue ;
  }

  public void setNachweisNullFlavourDefiningCode(NullFlavour nachweisNullFlavourDefiningCode) {
     this.nachweisNullFlavourDefiningCode = nachweisNullFlavourDefiningCode;
  }

  public NullFlavour getNachweisNullFlavourDefiningCode() {
     return this.nachweisNullFlavourDefiningCode ;
  }

  public void setErregername(DvCodedText erregername) {
     this.erregername = erregername;
  }

  public DvCodedText getErregername() {
     return this.erregername ;
  }

  public void setErregernameNullFlavourDefiningCode(
      NullFlavour erregernameNullFlavourDefiningCode) {
     this.erregernameNullFlavourDefiningCode = erregernameNullFlavourDefiningCode;
  }

  public NullFlavour getErregernameNullFlavourDefiningCode() {
     return this.erregernameNullFlavourDefiningCode ;
  }

  public void setErregerdetails(ErregerdetailsCluster erregerdetails) {
     this.erregerdetails = erregerdetails;
  }

  public ErregerdetailsCluster getErregerdetails() {
     return this.erregerdetails ;
  }

  public void setTestmethodeNullFlavourDefiningCode(
      NullFlavour testmethodeNullFlavourDefiningCode) {
     this.testmethodeNullFlavourDefiningCode = testmethodeNullFlavourDefiningCode;
  }

  public NullFlavour getTestmethodeNullFlavourDefiningCode() {
     return this.testmethodeNullFlavourDefiningCode ;
  }

  public void setZugehoerigeLaborprobeNullFlavourDefiningCode(
      NullFlavour zugehoerigeLaborprobeNullFlavourDefiningCode) {
     this.zugehoerigeLaborprobeNullFlavourDefiningCode = zugehoerigeLaborprobeNullFlavourDefiningCode;
  }

  public NullFlavour getZugehoerigeLaborprobeNullFlavourDefiningCode() {
     return this.zugehoerigeLaborprobeNullFlavourDefiningCode ;
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

  public void setZugehoerigeLaborprobe(
      ProErregerZugehoerigeLaborprobeChoice zugehoerigeLaborprobe) {
     this.zugehoerigeLaborprobe = zugehoerigeLaborprobe;
  }

  public ProErregerZugehoerigeLaborprobeChoice getZugehoerigeLaborprobe() {
     return this.zugehoerigeLaborprobe ;
  }

  public void setTestmethode(ProErregerTestmethodeChoice testmethode) {
     this.testmethode = testmethode;
  }

  public ProErregerTestmethodeChoice getTestmethode() {
     return this.testmethode ;
  }
}
