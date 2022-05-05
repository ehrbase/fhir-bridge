package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-05T11:59:39.246788+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("POINT_EVENT")
public class PulsfrequenzHerzfrequenzJedesEreignisPointEvent implements PointEventEntity, PulsfrequenzHerzfrequenzJedesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Vorhandensein
   * Description: Vorhandensein einer Puls- oder Herzfrequenz.
   * Comment: Eine Puls-/Herzfrequenz ist vorhanden, wenn die Frequenz > 0 Schläge pro Minute ist.
   */
  @Path("/data[at0001]/items[at1005]/value|defining_code")
  private VorhandenseinDefiningCode vorhandenseinDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Vorhandensein/null_flavour
   */
  @Path("/data[at0001]/items[at1005]/null_flavour|defining_code")
  private NullFlavour vorhandenseinNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double frequenzMagnitude;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Frequenz
   * Description: Die Frequenz, gemessen in Schlägen pro Minute.
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String frequenzUnits;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Frequenz/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour frequenzNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Regelmäßigkeit
   * Description: Regelmäßigkeit der Puls-/Herzfrequenz.
   */
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Regelmäßigkeit/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour regelmaessigkeitNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Unregelmäßiger Typ
   * Description: Ein spezifischeres Verlaufsmuster einer unregelmäßigen Puls- oder Herzfrequenz.
   * Comment: Die Auswahl eines Wertes dieser Wertemenge ist nur valide, wenn "Unregelmäßig" vom Datenelement "Regelmäßigkeit" angegeben wurde.
   */
  @Path("/data[at0001]/items[at1055]/value|defining_code")
  private UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Unregelmäßiger Typ/null_flavour
   */
  @Path("/data[at0001]/items[at1055]/null_flavour|defining_code")
  private NullFlavour unregelmaessigerTypNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Merkmal
   * Description: Beschreibung des Merkmals der Puls- oder Herzfrequenz.
   * Comment: Die Kodierung mit einer Terminologie ist, wenn möglich, erwünscht. Zum Beispiel: kräftig, schwach, hämmernd, langsam ansteigend oder kollabierend. Mehrere Begriffe können dokumentiert werden.
   */
  @Path("/data[at0001]/items[at1030]")
  private List<PulsfrequenzHerzfrequenzMerkmalElement> merkmal;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Klinische Beschreibung
   * Description: Beschreibung der Puls- oder Herzfrequenz.
   */
  @Path("/data[at0001]/items[at1022]/value|value")
  private String klinischeBeschreibungValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Klinische Beschreibung/null_flavour
   */
  @Path("/data[at0001]/items[at1022]/null_flavour|defining_code")
  private NullFlavour klinischeBeschreibungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Klinische Interpretation
   * Description: Ein einzelnes Wort, ein Satz oder eine kurze Beschreibung, welches die klinische Bedeutung und die Signifikanz der Puls- oder der Herzfrequenz, einschließlich des Rhythmus, darstellt.
   * Comment: Die Kodierung mit einer Terminologie wird, wenn möglich, bevorzugt. Zum Beispiel: Bradykardie, Extrasystolen oder Sinusrhythmus. Mehrere Aussagen sind erlaubt.
   */
  @Path("/data[at0001]/items[at1023]")
  private List<PulsfrequenzHerzfrequenzKlinischeInterpretationElement> klinischeInterpretation;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Kommentar
   * Description: Zusätzliche Informationen über die Puls- oder die Herzfrequenz, die in anderen Bereichen nicht erfasst wurden.
   */
  @Path("/data[at0001]/items[at1059]/value|value")
  private String kommentarValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Kommentar/null_flavour
   */
  @Path("/data[at0001]/items[at1059]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Körperhaltung
   * Description: Die Körperhaltung des Patienten während der Untersuchung.
   */
  @Path("/state[at0012]/items[at0013]/value|defining_code")
  private KoerperhaltungDefiningCode koerperhaltungDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/List/Körperhaltung/null_flavour
   */
  @Path("/state[at0012]/items[at0013]/null_flavour|defining_code")
  private NullFlavour koerperhaltungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Störfaktoren
   * Description: Die Beschreibung aller zufälligen Faktoren, die die Interpretation der physikalischen Ergebnisse beeinflussen können.
   * Comment: Zum Beispiel das Vorhandensein eines Herzschrittmacher; das Angstniveau; Schmerzen oder Fieber etc.
   */
  @Path("/state[at0012]/items[at1018]")
  private List<PulsfrequenzHerzfrequenzStoerfaktorenElement> stoerfaktoren;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Belastungsgrad
   * Description: Aufzeichnung von Informationen zum Belastungsgrad/Zustand des Patienten.
   */
  @Path("/state[at0012]/items[openEHR-EHR-CLUSTER.level_of_exertion.v0 and name/value='Belastungsgrad']")
  @Choice
  private List<BelastungsgradChoice> belastungsgrad;

  public void setVorhandenseinDefiningCode(VorhandenseinDefiningCode vorhandenseinDefiningCode) {
     this.vorhandenseinDefiningCode = vorhandenseinDefiningCode;
  }

  public VorhandenseinDefiningCode getVorhandenseinDefiningCode() {
     return this.vorhandenseinDefiningCode ;
  }

  public void setVorhandenseinNullFlavourDefiningCode(
      NullFlavour vorhandenseinNullFlavourDefiningCode) {
     this.vorhandenseinNullFlavourDefiningCode = vorhandenseinNullFlavourDefiningCode;
  }

  public NullFlavour getVorhandenseinNullFlavourDefiningCode() {
     return this.vorhandenseinNullFlavourDefiningCode ;
  }

  public void setFrequenzMagnitude(Double frequenzMagnitude) {
     this.frequenzMagnitude = frequenzMagnitude;
  }

  public Double getFrequenzMagnitude() {
     return this.frequenzMagnitude ;
  }

  public void setFrequenzUnits(String frequenzUnits) {
     this.frequenzUnits = frequenzUnits;
  }

  public String getFrequenzUnits() {
     return this.frequenzUnits ;
  }

  public void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode) {
     this.frequenzNullFlavourDefiningCode = frequenzNullFlavourDefiningCode;
  }

  public NullFlavour getFrequenzNullFlavourDefiningCode() {
     return this.frequenzNullFlavourDefiningCode ;
  }

  public void setRegelmaessigkeitDefiningCode(
      RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode) {
     this.regelmaessigkeitDefiningCode = regelmaessigkeitDefiningCode;
  }

  public RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode() {
     return this.regelmaessigkeitDefiningCode ;
  }

  public void setRegelmaessigkeitNullFlavourDefiningCode(
      NullFlavour regelmaessigkeitNullFlavourDefiningCode) {
     this.regelmaessigkeitNullFlavourDefiningCode = regelmaessigkeitNullFlavourDefiningCode;
  }

  public NullFlavour getRegelmaessigkeitNullFlavourDefiningCode() {
     return this.regelmaessigkeitNullFlavourDefiningCode ;
  }

  public void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode) {
     this.unregelmaessigerTypDefiningCode = unregelmaessigerTypDefiningCode;
  }

  public UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode() {
     return this.unregelmaessigerTypDefiningCode ;
  }

  public void setUnregelmaessigerTypNullFlavourDefiningCode(
      NullFlavour unregelmaessigerTypNullFlavourDefiningCode) {
     this.unregelmaessigerTypNullFlavourDefiningCode = unregelmaessigerTypNullFlavourDefiningCode;
  }

  public NullFlavour getUnregelmaessigerTypNullFlavourDefiningCode() {
     return this.unregelmaessigerTypNullFlavourDefiningCode ;
  }

  public void setMerkmal(List<PulsfrequenzHerzfrequenzMerkmalElement> merkmal) {
     this.merkmal = merkmal;
  }

  public List<PulsfrequenzHerzfrequenzMerkmalElement> getMerkmal() {
     return this.merkmal ;
  }

  public void setKlinischeBeschreibungValue(String klinischeBeschreibungValue) {
     this.klinischeBeschreibungValue = klinischeBeschreibungValue;
  }

  public String getKlinischeBeschreibungValue() {
     return this.klinischeBeschreibungValue ;
  }

  public void setKlinischeBeschreibungNullFlavourDefiningCode(
      NullFlavour klinischeBeschreibungNullFlavourDefiningCode) {
     this.klinischeBeschreibungNullFlavourDefiningCode = klinischeBeschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischeBeschreibungNullFlavourDefiningCode() {
     return this.klinischeBeschreibungNullFlavourDefiningCode ;
  }

  public void setKlinischeInterpretation(
      List<PulsfrequenzHerzfrequenzKlinischeInterpretationElement> klinischeInterpretation) {
     this.klinischeInterpretation = klinischeInterpretation;
  }

  public List<PulsfrequenzHerzfrequenzKlinischeInterpretationElement> getKlinischeInterpretation() {
     return this.klinischeInterpretation ;
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

  public void setKoerperhaltungDefiningCode(KoerperhaltungDefiningCode koerperhaltungDefiningCode) {
     this.koerperhaltungDefiningCode = koerperhaltungDefiningCode;
  }

  public KoerperhaltungDefiningCode getKoerperhaltungDefiningCode() {
     return this.koerperhaltungDefiningCode ;
  }

  public void setKoerperhaltungNullFlavourDefiningCode(
      NullFlavour koerperhaltungNullFlavourDefiningCode) {
     this.koerperhaltungNullFlavourDefiningCode = koerperhaltungNullFlavourDefiningCode;
  }

  public NullFlavour getKoerperhaltungNullFlavourDefiningCode() {
     return this.koerperhaltungNullFlavourDefiningCode ;
  }

  public void setStoerfaktoren(List<PulsfrequenzHerzfrequenzStoerfaktorenElement> stoerfaktoren) {
     this.stoerfaktoren = stoerfaktoren;
  }

  public List<PulsfrequenzHerzfrequenzStoerfaktorenElement> getStoerfaktoren() {
     return this.stoerfaktoren ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setBelastungsgrad(List<BelastungsgradChoice> belastungsgrad) {
     this.belastungsgrad = belastungsgrad;
  }

  public List<BelastungsgradChoice> getBelastungsgrad() {
     return this.belastungsgrad ;
  }
}
