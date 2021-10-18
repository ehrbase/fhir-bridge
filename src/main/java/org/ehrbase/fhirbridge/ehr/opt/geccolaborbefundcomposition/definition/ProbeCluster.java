package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import java.lang.Boolean;
import java.lang.String;
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
@Archetype("openEHR-EHR-CLUSTER.specimen.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-18T12:28:12.507884+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ProbeCluster implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenart
   * Description: Die Art der Probe.
   * Comment: Zum Beispiel: Venöses Blut, Bakterienkultur, Zytologie oder Prostatabiopsie. Nach Möglichkeit wird die Kodierung der Probenart mit einer Terminologie bevorzugt.
   */
  @Path("/items[at0029]/value")
  private DvCodedText probenart;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Probenart/null_flavour
   */
  @Path("/items[at0029]/null_flavour|defining_code")
  private NullFlavour probenartNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Laborprobenidentifikator
   * Description: Eine eindeutige Kennung der Probe, die normalerweise vom Labor vergeben wird.
   * Comment: Wird manchmal als "Accession Identifier" bezeichnet. Probenbehälter, z. B. Vakuumflaschen oder Gewebekassetten, haben ihre eigenen Kennungen, die im Element "Container identifier" des Archetyps "Probenbehälter" eingetragen werden können.
   */
  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Laborprobenidentifikator/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour laborprobenidentifikatorNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Externer Identifikator
   * Description: Eine eindeutige Kennung der Probe, die von einer Organisation außerhalb des Labors wie dem Auftraggeber zugeordnet wurde.
   */
  @Path("/items[at0088]/value")
  private DvIdentifier externerIdentifikator;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Externer Identifikator/null_flavour
   */
  @Path("/items[at0088]/null_flavour|defining_code")
  private NullFlavour externerIdentifikatorNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt des Probeneingangs
   * Description: Datum und Uhrzeit des Eingangs der Probe im Labor.
   */
  @Path("/items[at0034]/value|value")
  private TemporalAccessor zeitpunktDesProbeneingangsValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Zeitpunkt des Probeneingangs/null_flavour
   */
  @Path("/items[at0034]/null_flavour|defining_code")
  private NullFlavour zeitpunktDesProbeneingangsNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenentahmebedingung
   * Description: Der Kontext, in dem die Probe entnommen wird.
   * Comment: Zum Beispiel: "Fasten", "volle Blase", "steriles Feld" oder spezielle Anweisungen zur Handhabung oder sofortigen Verarbeitung der Probe, zum Beispiel "Nach Erhalt zentrifugieren". Kann auch verwendet werden, um bekannte Abweichungen von Entnahme- oder Handhabungsanweisungen zu dokumentieren, z.B. dass der Patient nicht gefastet hat. Wenn möglich, wird eine Kodierung der Probenahmebedingung mit einer Terminologie bevorzugt. 
   *
   * Ob dieses Element Bedingungen enthält, die während der Probenahme vorhanden sein sollten oder waren, hängt vom Kontext des enthaltenen Archetyps ab, in der Regel ein INSTRUCTION. Der Inhalt dieses Elements im Kontext eines ACTION-Archetyps in einem abgeschlossenen Zustand kann verwendet werden, um zu entscheiden, ob signifikante Störfaktoren im Zusammenhang mit der Entnahme aufgetreten sind, die zum einpflegen des Elements 'Störfaktoren' eines OBSERVATION.laboratory_test_result-Archetyps verwendet werden können.
   */
  @Path("/items[at0008]")
  private List<ProbeProbenentahmebedingungElement> probenentahmebedingung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Physische Eigenschaften
   * Description: Physische Größen, Masse oder nicht messbare Eigenschaften der uentnehmenden Probe.
   * Comment: Zum Beispiel: Volumen, Masse, Umfang, Farbe, Geruch, Trübung. Mit diesem Element können die Eigenschaften der zu entnehmenden Probe im Kontext eines INSTRUCTION-Archetyps oder die Eigenschaften des zu entnehmenden Probe im Kontext eines ACTION- oder OBSERVATION-Archetyps angegeben werden. Beispielsweise kann eine INSTRUCTION die Entnahme von 20 ml Blut verlangen, während die entsprechende ACTION aufzeichnet, dass nur 15 ml entnommen wurden.
   */
  @Path("/items[at0027]")
  private List<Cluster> physischeEigenschaften;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenentnahmemethode
   * Description: Die Methode der Probenentnahme, die verwendet werden soll oder wurde.
   * Comment: Zum Beispiel: Venenpunktion, Biopsie, Resektion. Nach Möglichkeit wird die Kodierung der Probenentnahmemethode mit einer Terminologie bevorzugt. Wenn die Probenentnahmemethode über vorkoordinierte Kodes in das Element "Probenart" einbezogen wird, wird dieses Datenelement überflüssig.
   */
  @Path("/items[at0007]/value|value")
  private String probenentnahmemethodeValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Probenentnahmemethode/null_flavour
   */
  @Path("/items[at0007]/null_flavour|defining_code")
  private NullFlavour probenentnahmemethodeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Körperstelle
   * Description: Identifizierung der Entnahmestelle, an der die Probe entnommen wurde.
   * Comment: Nach Möglichkeit wird die Kodierung des Namens der Probenentnahmestelle mit einer Terminologie bevorzugt. Verwenden Sie dieses Datenelement, um vorkoordinierte Probenentnahmestellen zu erfassen. Wenn die Anforderungen für die Darstellung der Probenentnahmestelle zur Laufzeit der Anwendung bestimmt werden oder diese eine komplexere Modellierung erfordern, z.B. relative Positionen, verwenden Sie in diesem Archetyp den SLOT "Anatomische Entnahmestelle". Wenn die Probenentnahmestelle über vorab koordinierte Kodes in das Element "Probenart" einbezogen wird, wird dieses Datenelement überflüssig.
   */
  @Path("/items[at0087 and name/value='Körperstelle']/value|value")
  private String koerperstelleValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Körperstelle/null_flavour
   */
  @Path("/items[at0087 and name/value='Körperstelle']/null_flavour|defining_code")
  private NullFlavour koerperstelleNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Anatomische Probenentnahmestelle
   * Description: Strukturierte Angaben zur anatomischen Stelle, an der die Probe entnommen wurde.
   * Comment: Verwenden Sie diesen Archetypen zur Beschreibung strukturierter oder komplexerer anatomischen Stellen oder zur Unterstützung der Aufzeichnung des Entstehungsortes während der Anwendung. Wenn die Anatomische Entnahmestelle über vorab koordinierte Codes in das Element "Entnahmestelle" einbezogen wird, wird die Verwendung dieses SLOTs überflüssig.
   */
  @Path("/items[at0013]")
  private List<Cluster> anatomischeProbenentnahmestelle;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   */
  @Path("/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerProbenentnahmeValue;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/lower_included
   */
  @Path("/items[at0015]/value/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/upper_included
   */
  @Path("/items[at0015]/value/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/upper
   */
  @Path("/items[at0015]/value/upper")
  private DvOrdered upper;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zeitpunkt der Probenentnahme/lower
   */
  @Path("/items[at0015]/value/lower")
  private DvOrdered lower;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Zeitpunkt der Probenentnahme/null_flavour
   */
  @Path("/items[at0015]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerProbenentnahmeNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Identifikator des Probennehmers
   * Description: Die Person oder Organisation die für die Entnahme der Probe verantwortlich war.
   * Comment: Dieses Element soll verwendet werden, wenn die Probe bereits abgenommen wurde und der tatsächliche Probennehmer bekannt ist.
   */
  @Path("/items[at0070]/value")
  private DvIdentifier identifikatorDesProbennehmers;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Identifikator des Probennehmers/null_flavour
   */
  @Path("/items[at0070]/null_flavour|defining_code")
  private NullFlavour identifikatorDesProbennehmersNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zum Probennehmer
   * Description: Die Person oder Organisation, die für die Probennahme verantwortlich ist.
   */
  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbennehmer;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Zusätzliche Angaben zur Probennahme
   * Description: Zusätzliche Angaben zu bestimmten Methoden der Probennahme.
   * Comment: Zum Beispiel Details zu Nadelbiopsien bei Prostatakrebs, bei denen sowohl die Anforderung als auch die Beschreibung über die Probe detailliert und spezifisch sind.
   */
  @Path("/items[at0083]")
  private List<Cluster> zusaetzlicheAngabenZurProbennahme;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Behälter Details
   * Description: Details über Behälter, der/die verwendet wurde/n.
   */
  @Path("/items[at0085]")
  private List<Cluster> behaelterDetails;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zur Verarbeitung
   * Description: Einzelheiten zu einem normalerweise im Labor durchgeführten Vorbereitungs- oder Verarbeitungsschritt.
   * Comment: Zum Beispiel: Färbung oder Fixierung.
   */
  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Angaben zum Transport
   * Description: Angaben zum Transport der Probe.
   */
  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Digitale Darstellung
   * Description: Strukturierte Details über eine digitale Darstellung des Präparates.
   * Comment: Zum Beispiel das gescannte Bild eines Histopathologie-Dias.
   */
  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Identifikator der übergeordneten Probe
   * Description: Eindeutige Kennung der verwandten Probe(n), bei der die Probe in Teilproben aufgeteilt ist.
   * Comment: Zum Beispiel: eine bestimmte Probe eines Objektträgers für die Histologie würde einen bestimmten Paraffinwachsblock als Ausgangsprobe haben.
   */
  @Path("/items[at0003]")
  private List<ProbeIdentifikatorDerUebergeordnetenProbeElement> identifikatorDerUebergeordnetenProbe;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Eignung zum Testen/null_flavour
   */
  @Path("/items[at0041]/null_flavour|defining_code")
  private NullFlavour eignungZumTestenNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Kommentar
   * Description: Zusätzliche Beschreibungen der Probe, die nicht in anderen Feldern abgebildet werden können.
   */
  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Kommentar/null_flavour
   */
  @Path("/items[at0045]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Eignung zum Testen
   * Description: Angabe, ob die Probe für die Untersuchung geeignet war.
   */
  @Path("/items[at0041]/value")
  @Choice
  private ProbeEignungZumTestenChoice eignungZumTesten;

  public void setProbenart(DvCodedText probenart) {
     this.probenart = probenart;
  }

  public DvCodedText getProbenart() {
     return this.probenart ;
  }

  public void setProbenartNullFlavourDefiningCode(NullFlavour probenartNullFlavourDefiningCode) {
     this.probenartNullFlavourDefiningCode = probenartNullFlavourDefiningCode;
  }

  public NullFlavour getProbenartNullFlavourDefiningCode() {
     return this.probenartNullFlavourDefiningCode ;
  }

  public void setLaborprobenidentifikator(DvIdentifier laborprobenidentifikator) {
     this.laborprobenidentifikator = laborprobenidentifikator;
  }

  public DvIdentifier getLaborprobenidentifikator() {
     return this.laborprobenidentifikator ;
  }

  public void setLaborprobenidentifikatorNullFlavourDefiningCode(
      NullFlavour laborprobenidentifikatorNullFlavourDefiningCode) {
     this.laborprobenidentifikatorNullFlavourDefiningCode = laborprobenidentifikatorNullFlavourDefiningCode;
  }

  public NullFlavour getLaborprobenidentifikatorNullFlavourDefiningCode() {
     return this.laborprobenidentifikatorNullFlavourDefiningCode ;
  }

  public void setExternerIdentifikator(DvIdentifier externerIdentifikator) {
     this.externerIdentifikator = externerIdentifikator;
  }

  public DvIdentifier getExternerIdentifikator() {
     return this.externerIdentifikator ;
  }

  public void setExternerIdentifikatorNullFlavourDefiningCode(
      NullFlavour externerIdentifikatorNullFlavourDefiningCode) {
     this.externerIdentifikatorNullFlavourDefiningCode = externerIdentifikatorNullFlavourDefiningCode;
  }

  public NullFlavour getExternerIdentifikatorNullFlavourDefiningCode() {
     return this.externerIdentifikatorNullFlavourDefiningCode ;
  }

  public void setZeitpunktDesProbeneingangsValue(TemporalAccessor zeitpunktDesProbeneingangsValue) {
     this.zeitpunktDesProbeneingangsValue = zeitpunktDesProbeneingangsValue;
  }

  public TemporalAccessor getZeitpunktDesProbeneingangsValue() {
     return this.zeitpunktDesProbeneingangsValue ;
  }

  public void setZeitpunktDesProbeneingangsNullFlavourDefiningCode(
      NullFlavour zeitpunktDesProbeneingangsNullFlavourDefiningCode) {
     this.zeitpunktDesProbeneingangsNullFlavourDefiningCode = zeitpunktDesProbeneingangsNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDesProbeneingangsNullFlavourDefiningCode() {
     return this.zeitpunktDesProbeneingangsNullFlavourDefiningCode ;
  }

  public void setProbenentahmebedingung(
      List<ProbeProbenentahmebedingungElement> probenentahmebedingung) {
     this.probenentahmebedingung = probenentahmebedingung;
  }

  public List<ProbeProbenentahmebedingungElement> getProbenentahmebedingung() {
     return this.probenentahmebedingung ;
  }

  public void setPhysischeEigenschaften(List<Cluster> physischeEigenschaften) {
     this.physischeEigenschaften = physischeEigenschaften;
  }

  public List<Cluster> getPhysischeEigenschaften() {
     return this.physischeEigenschaften ;
  }

  public void setProbenentnahmemethodeValue(String probenentnahmemethodeValue) {
     this.probenentnahmemethodeValue = probenentnahmemethodeValue;
  }

  public String getProbenentnahmemethodeValue() {
     return this.probenentnahmemethodeValue ;
  }

  public void setProbenentnahmemethodeNullFlavourDefiningCode(
      NullFlavour probenentnahmemethodeNullFlavourDefiningCode) {
     this.probenentnahmemethodeNullFlavourDefiningCode = probenentnahmemethodeNullFlavourDefiningCode;
  }

  public NullFlavour getProbenentnahmemethodeNullFlavourDefiningCode() {
     return this.probenentnahmemethodeNullFlavourDefiningCode ;
  }

  public void setKoerperstelleValue(String koerperstelleValue) {
     this.koerperstelleValue = koerperstelleValue;
  }

  public String getKoerperstelleValue() {
     return this.koerperstelleValue ;
  }

  public void setKoerperstelleNullFlavourDefiningCode(
      NullFlavour koerperstelleNullFlavourDefiningCode) {
     this.koerperstelleNullFlavourDefiningCode = koerperstelleNullFlavourDefiningCode;
  }

  public NullFlavour getKoerperstelleNullFlavourDefiningCode() {
     return this.koerperstelleNullFlavourDefiningCode ;
  }

  public void setAnatomischeProbenentnahmestelle(List<Cluster> anatomischeProbenentnahmestelle) {
     this.anatomischeProbenentnahmestelle = anatomischeProbenentnahmestelle;
  }

  public List<Cluster> getAnatomischeProbenentnahmestelle() {
     return this.anatomischeProbenentnahmestelle ;
  }

  public void setZeitpunktDerProbenentnahmeValue(TemporalAccessor zeitpunktDerProbenentnahmeValue) {
     this.zeitpunktDerProbenentnahmeValue = zeitpunktDerProbenentnahmeValue;
  }

  public TemporalAccessor getZeitpunktDerProbenentnahmeValue() {
     return this.zeitpunktDerProbenentnahmeValue ;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
     this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
     return this.lowerIncluded ;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
     this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
     return this.upperIncluded ;
  }

  public void setUpper(DvOrdered upper) {
     this.upper = upper;
  }

  public DvOrdered getUpper() {
     return this.upper ;
  }

  public void setLower(DvOrdered lower) {
     this.lower = lower;
  }

  public DvOrdered getLower() {
     return this.lower ;
  }

  public void setZeitpunktDerProbenentnahmeNullFlavourDefiningCode(
      NullFlavour zeitpunktDerProbenentnahmeNullFlavourDefiningCode) {
     this.zeitpunktDerProbenentnahmeNullFlavourDefiningCode = zeitpunktDerProbenentnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDerProbenentnahmeNullFlavourDefiningCode() {
     return this.zeitpunktDerProbenentnahmeNullFlavourDefiningCode ;
  }

  public void setIdentifikatorDesProbennehmers(DvIdentifier identifikatorDesProbennehmers) {
     this.identifikatorDesProbennehmers = identifikatorDesProbennehmers;
  }

  public DvIdentifier getIdentifikatorDesProbennehmers() {
     return this.identifikatorDesProbennehmers ;
  }

  public void setIdentifikatorDesProbennehmersNullFlavourDefiningCode(
      NullFlavour identifikatorDesProbennehmersNullFlavourDefiningCode) {
     this.identifikatorDesProbennehmersNullFlavourDefiningCode = identifikatorDesProbennehmersNullFlavourDefiningCode;
  }

  public NullFlavour getIdentifikatorDesProbennehmersNullFlavourDefiningCode() {
     return this.identifikatorDesProbennehmersNullFlavourDefiningCode ;
  }

  public void setAngabenZumProbennehmer(List<Cluster> angabenZumProbennehmer) {
     this.angabenZumProbennehmer = angabenZumProbennehmer;
  }

  public List<Cluster> getAngabenZumProbennehmer() {
     return this.angabenZumProbennehmer ;
  }

  public void setZusaetzlicheAngabenZurProbennahme(
      List<Cluster> zusaetzlicheAngabenZurProbennahme) {
     this.zusaetzlicheAngabenZurProbennahme = zusaetzlicheAngabenZurProbennahme;
  }

  public List<Cluster> getZusaetzlicheAngabenZurProbennahme() {
     return this.zusaetzlicheAngabenZurProbennahme ;
  }

  public void setBehaelterDetails(List<Cluster> behaelterDetails) {
     this.behaelterDetails = behaelterDetails;
  }

  public List<Cluster> getBehaelterDetails() {
     return this.behaelterDetails ;
  }

  public void setAngabenZurVerarbeitung(List<Cluster> angabenZurVerarbeitung) {
     this.angabenZurVerarbeitung = angabenZurVerarbeitung;
  }

  public List<Cluster> getAngabenZurVerarbeitung() {
     return this.angabenZurVerarbeitung ;
  }

  public void setAngabenZumTransport(List<Cluster> angabenZumTransport) {
     this.angabenZumTransport = angabenZumTransport;
  }

  public List<Cluster> getAngabenZumTransport() {
     return this.angabenZumTransport ;
  }

  public void setDigitaleDarstellung(List<Cluster> digitaleDarstellung) {
     this.digitaleDarstellung = digitaleDarstellung;
  }

  public List<Cluster> getDigitaleDarstellung() {
     return this.digitaleDarstellung ;
  }

  public void setIdentifikatorDerUebergeordnetenProbe(
      List<ProbeIdentifikatorDerUebergeordnetenProbeElement> identifikatorDerUebergeordnetenProbe) {
     this.identifikatorDerUebergeordnetenProbe = identifikatorDerUebergeordnetenProbe;
  }

  public List<ProbeIdentifikatorDerUebergeordnetenProbeElement> getIdentifikatorDerUebergeordnetenProbe(
      ) {
     return this.identifikatorDerUebergeordnetenProbe ;
  }

  public void setEignungZumTestenNullFlavourDefiningCode(
      NullFlavour eignungZumTestenNullFlavourDefiningCode) {
     this.eignungZumTestenNullFlavourDefiningCode = eignungZumTestenNullFlavourDefiningCode;
  }

  public NullFlavour getEignungZumTestenNullFlavourDefiningCode() {
     return this.eignungZumTestenNullFlavourDefiningCode ;
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

  public void setEignungZumTesten(ProbeEignungZumTestenChoice eignungZumTesten) {
     this.eignungZumTesten = eignungZumTesten;
  }

  public ProbeEignungZumTestenChoice getEignungZumTesten() {
     return this.eignungZumTesten ;
  }
}
