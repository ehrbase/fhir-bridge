package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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
    date = "2023-11-24T14:43:52.675729039+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
public class ProbenmaterialCluster implements LocatableEntity {
  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Probenart
   * Description: Die Art der Probe.
   * Comment: Zum Beispiel: Venöses Blut, Bakterienkultur, Zytologie oder Gewebeprobe. Nach Möglichkeit wird die Codierung der Probenart mit einer Terminologie bevorzugt.
   */
  @Path("/items[at0029]/value")
  private DvCodedText probenart;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Probenart/null_flavour
   */
  @Path("/items[at0029]/null_flavour|defining_code")
  private NullFlavour probenartNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Beschreibung der Probe
   * Description: Beschreibung der untersuchten Probe.
   * Comment: Zum Beispiel: die Farbe und Viskosität einer Flüssigkeit, die Form eines festen Gewebes oder eine Beschreibung von Gewebefragmenten.
   */
  @Path("/items[at0097]/value")
  private DvCodedText beschreibungDerProbe;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Beschreibung der Probe/null_flavour
   */
  @Path("/items[at0097]/null_flavour|defining_code")
  private NullFlavour beschreibungDerProbeNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Laborprobenidentifikator
   * Description: Eine eindeutige Kennung der Probe, die normalerweise vom Labor vergeben wird.
   * Comment: Wird manchmal als "Accession Identifier" bezeichnet. Probenbehälter, z. B. Vakuumröhrchen oder Gewebekassetten, haben ihre eigenen Kennungen, die im Element "Behälter-Identifikator" des Archetyps "Probenbehälter" eingetragen werden können.
   */
  @Path("/items[at0001]/value")
  private DvIdentifier laborprobenidentifikator;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Laborprobenidentifikator/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour laborprobenidentifikatorNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Externer Identifikator
   * Description: Eine eindeutige Kennung der Probe, die von einer Organisation außerhalb des Labors wie dem Auftraggeber zugeordnet wurde.
   * Comment: Zum Beispiel: "Anforderer-ID", "Archiv-ID", "Biobank-ID".
   */
  @Path("/items[at0088]/value")
  private DvIdentifier externerIdentifikator;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Externer Identifikator/null_flavour
   */
  @Path("/items[at0088]/null_flavour|defining_code")
  private NullFlavour externerIdentifikatorNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt des Probeneingangs
   * Description: Datum und Uhrzeit des Eingangs der Probe im Labor.
   */
  @Path("/items[at0034]/value|value")
  private TemporalAccessor zeitpunktDesProbeneingangsValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Zeitpunkt des Probeneingangs/null_flavour
   */
  @Path("/items[at0034]/null_flavour|defining_code")
  private NullFlavour zeitpunktDesProbeneingangsNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Probenentahmebedingung
   * Description: Der Kontext, in dem die Probe entnommen wird.
   * Comment: Zum Beispiel: "Fasten", "volle Blase", "steriles Feld" oder spezielle Anweisungen zur Handhabung oder sofortigen Verarbeitung der Probe, zum Beispiel "Nach Erhalt zentrifugieren". Kann auch verwendet werden, um bekannte Abweichungen von Entnahme- oder Handhabungsanweisungen zu dokumentieren, z.B. dass der Patient nicht gefastet hat. Wenn möglich, wird eine Kodierung der Probenentahmebedingung mit einer Terminologie bevorzugt. 
   *
   * Ob dieses Element Bedingungen enthält, die während der Probeentnahme vorhanden sein sollten oder waren, hängt vom Kontext des Container-Archetyps ab, in der Regel ein INSTRUCTION-Archetyp. Der Inhalt dieses Elements im Kontext eines ACTION-Archetyps in einem abgeschlossenen Zustand kann verwendet werden, um zu entscheiden, ob signifikante Störfaktoren im Zusammenhang mit der Entnahme aufgetreten sind, die zum einpflegen des Elements "Störfaktoren" des Archetyps OBSERVATION.laboratory_test_result verwendet werden können.
   */
  @Path("/items[at0008]")
  private List<ProbenmaterialProbenentahmebedingungElement> probenentahmebedingung;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Physikalische Eigenschaften
   * Description: Physikalische Größen, Masse oder nicht messbare Eigenschaften der Probe.
   * Comment: Zum Beispiel: Volumen, Masse, Umfang, Farbe, Geruch, Trübung. Mit diesem Element können die Eigenschaften der zu entnehmenden Probe im Kontext eines INSTRUCTION-Archetyps oder die Eigenschaften der zu entnehmenden Probe im Kontext eines ACTION- oder OBSERVATION-Archetyps angegeben werden. Beispielsweise kann eine INSTRUCTION die Entnahme von 20 ml Blut verlangen, während die entsprechende ACTION aufzeichnet, dass nur 15 ml entnommen wurden.
   */
  @Path("/items[at0027]")
  private List<Cluster> physikalischeEigenschaften;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Probenentnahmemethode
   * Description: Die Methode der Probenentnahme, die angewendet wird oder wurde.
   * Comment: Zum Beispiel: Venenpunktion, Biopsie, Resektion. Nach Möglichkeit wird die Codierung der Probenentnahmemethode mit einer Terminologie bevorzugt. Wenn die Probenentnahmemethode über vorkoordinierte Codes in das Element "Probenart" einbezogen wird, wird dieses Datenelement überflüssig.
   */
  @Path("/items[at0007]/value|value")
  private String probenentnahmemethodeValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Probenentnahmemethode/null_flavour
   */
  @Path("/items[at0007]/null_flavour|defining_code")
  private NullFlavour probenentnahmemethodeNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Körperstelle
   * Description: Identifizierung der Körperstelle oder einer anderen Stelle, an der die Probe entnommen wird.
   * Comment: Zum Beispiel: „Wunde an linker Wade“, „IV-Kanüle rechter Arm“, „rechte Niere“. 
   * Nach Möglichkeit wird die Codierung des Namens der Probenentnahmestelle mit einer Terminologie bevorzugt. Verwenden Sie dieses Datenelement, um vorkoordinierte Probenentnahmestellen zu erfassen. Wenn die Anforderungen für die Darstellung der Probenentnahmestelle zur Laufzeit der Anwendung bestimmt werden oder diese eine komplexere Modellierung erfordern, z.B. relative Positionen, verwenden Sie in diesem Archetyp den SLOT "Probenentnahmestelle strukturiert". Wenn die Probenentnahmestelle über vorab koordinierte Kodes in das Element "Probenart" einbezogen wird, wird dieses Datenelement überflüssig.
   */
  @Path("/items[at0087 and name/value='Körperstelle']/value|value")
  private String koerperstelleValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Körperstelle/null_flavour
   */
  @Path("/items[at0087 and name/value='Körperstelle']/null_flavour|defining_code")
  private NullFlavour koerperstelleNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Probenentnahmestelle strukturiert
   * Description: Strukturierte Angaben zur Körperstelle, an der die Probe entnommen wurde.
   * Comment: Verwenden Sie diesen Archetypen zur Beschreibung strukturierter oder komplexerer anatomischen Stellen oder zur Unterstützung der Darstellung der Entnahmestelle während der Anwendung. Wenn die Körperstelle über vorab koordinierte Codes in das Element "Probenentnahmestelle" einbezogen wird, wird die Verwendung dieses SLOTs überflüssig.
   */
  @Path("/items[at0013]")
  private List<Cluster> probenentnahmestelleStrukturiert;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Zeitpunkt der Probenentnahme/null_flavour
   */
  @Path("/items[at0015]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerProbenentnahmeNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Identifikator des Probenehmers
   * Description: Identifizierung der für die Entnahme der Probe verantwortliche Person oder Organisation.
   * Comment: Dieses Element soll verwendet werden, wenn die Probe bereits entnommen wurde und der tatsächliche Probenehmer bekannt ist.
   */
  @Path("/items[at0070]/value")
  private DvIdentifier identifikatorDesProbenehmers;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Identifikator des Probenehmers/null_flavour
   */
  @Path("/items[at0070]/null_flavour|defining_code")
  private NullFlavour identifikatorDesProbenehmersNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Angaben zum Probenehmer
   * Description: Die für die Entnahme der Probe verantwortliche Person oder Organisation.
   */
  @Path("/items[at0071]")
  private List<Cluster> angabenZumProbenehmer;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zusätzliche Angaben
   * Description: Zusätzliche strukturierte Angaben zu der Probe.
   * Comment: Zum Beispiel Details zu Nadelbiopsien bei Prostatakrebs, bei denen sowohl die Anforderung als auch die Beschreibung über die Probe detailliert und spezifisch sind.
   */
  @Path("/items[at0083]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Behälter Details
   * Description: Details über Behälter, der/die verwendet wurde/n.
   */
  @Path("/items[at0085]")
  private List<Cluster> behaelterDetails;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Angaben zur Verarbeitung
   * Description: Strukturierte Angaben zur Vorbereitung oder Verarbeitung der Probe.
   * Comment: Zum Beispiel: Färbung oder Fixierung.
   */
  @Path("/items[at0068]")
  private List<Cluster> angabenZurVerarbeitung;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Angaben zur Lagerung
   * Description: Strukturierte Angaben zur Probenaufbewahrung.
   */
  @Path("/items[at0100]")
  private List<Cluster> angabenZurLagerung;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Angaben zum Transport
   * Description: Strukturierte Angaben zum Transport der Probe.
   */
  @Path("/items[at0093]")
  private List<Cluster> angabenZumTransport;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Digitale Darstellung
   * Description: Strukturierte Details über eine digitale Darstellung der Probe.
   * Comment: Zum Beispiel das gescannte Bild eines Histopathologie-Dias.
   */
  @Path("/items[at0096]")
  private List<Cluster> digitaleDarstellung;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Identifikator der übergeordneten Probe
   * Description: Eindeutige Kennung der übergeordneten Probe(n), wobei die Probe in Teilproben aufgeteilt wird.
   * Comment: Zum Beispiel: eine bestimmte Probe eines Objektträgers für die Histologie würde einen bestimmten Paraffinwachsblock als Ausgangsprobe haben.
   */
  @Path("/items[at0003]")
  private List<ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement> identifikatorDerUebergeordnetenProbe;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Eignung zur Analyse
   * Description: Angabe darüber, ob die Probe für die Untersuchung geeignet war.
   * Comment: Dieses Element soll verwendet werden, um zu beurteilen, ob die Probenqualität für die Prüfung ausreichend war oder nicht und ob eine Prüfung durchgeführt wurde oder nicht. Die Codierung der Eignung zum Testen mit einer Terminologie wird nach Möglichkeit bevorzugt.
   */
  @Path("/items[at0041]/value|defining_code")
  private EignungZurAnalyseDefiningCode eignungZurAnalyseDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Eignung zur Analyse/null_flavour
   */
  @Path("/items[at0041]/null_flavour|defining_code")
  private NullFlavour eignungZurAnalyseNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Kommentar
   * Description: Zusätzliche Beschreibungen der Probe, die nicht in anderen Feldern abgebildet werden können.
   */
  @Path("/items[at0045]/value|value")
  private String kommentarValue;

  /**
   * Path: Laborbericht/Laborbefund/Event Series/Jedes Ereignis/Tree/Probenmaterial/Kommentar/null_flavour
   */
  @Path("/items[at0045]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Laborbericht/Laborbefund/Jedes Ereignis/Probenmaterial/Zeitpunkt der Probenentnahme
   * Description: Das Datum und die Uhrzeit, zu der die Probennahme angeordnet wurde oder stattfand.
   * Comment: Dieser Zeitpunkt wird hauptsächlich in den Zeitangaben, von INSTRUCTION, ACTION oder OBSERVATION erfasst. Da es sich jedoch um eine wichtige Information handelt, kann es nützlich sein, diese auch direkt mit der Probe selbst zu verknüpfen.
   */
  @Path("/items[at0015]/value")
  @Choice
  private ProbenmaterialZeitpunktDerProbenentnahmeChoice zeitpunktDerProbenentnahme;

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

  public void setBeschreibungDerProbe(DvCodedText beschreibungDerProbe) {
     this.beschreibungDerProbe = beschreibungDerProbe;
  }

  public DvCodedText getBeschreibungDerProbe() {
     return this.beschreibungDerProbe ;
  }

  public void setBeschreibungDerProbeNullFlavourDefiningCode(
      NullFlavour beschreibungDerProbeNullFlavourDefiningCode) {
     this.beschreibungDerProbeNullFlavourDefiningCode = beschreibungDerProbeNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungDerProbeNullFlavourDefiningCode() {
     return this.beschreibungDerProbeNullFlavourDefiningCode ;
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
      List<ProbenmaterialProbenentahmebedingungElement> probenentahmebedingung) {
     this.probenentahmebedingung = probenentahmebedingung;
  }

  public List<ProbenmaterialProbenentahmebedingungElement> getProbenentahmebedingung() {
     return this.probenentahmebedingung ;
  }

  public void setPhysikalischeEigenschaften(List<Cluster> physikalischeEigenschaften) {
     this.physikalischeEigenschaften = physikalischeEigenschaften;
  }

  public List<Cluster> getPhysikalischeEigenschaften() {
     return this.physikalischeEigenschaften ;
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

  public void setProbenentnahmestelleStrukturiert(List<Cluster> probenentnahmestelleStrukturiert) {
     this.probenentnahmestelleStrukturiert = probenentnahmestelleStrukturiert;
  }

  public List<Cluster> getProbenentnahmestelleStrukturiert() {
     return this.probenentnahmestelleStrukturiert ;
  }

  public void setZeitpunktDerProbenentnahmeNullFlavourDefiningCode(
      NullFlavour zeitpunktDerProbenentnahmeNullFlavourDefiningCode) {
     this.zeitpunktDerProbenentnahmeNullFlavourDefiningCode = zeitpunktDerProbenentnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDerProbenentnahmeNullFlavourDefiningCode() {
     return this.zeitpunktDerProbenentnahmeNullFlavourDefiningCode ;
  }

  public void setIdentifikatorDesProbenehmers(DvIdentifier identifikatorDesProbenehmers) {
     this.identifikatorDesProbenehmers = identifikatorDesProbenehmers;
  }

  public DvIdentifier getIdentifikatorDesProbenehmers() {
     return this.identifikatorDesProbenehmers ;
  }

  public void setIdentifikatorDesProbenehmersNullFlavourDefiningCode(
      NullFlavour identifikatorDesProbenehmersNullFlavourDefiningCode) {
     this.identifikatorDesProbenehmersNullFlavourDefiningCode = identifikatorDesProbenehmersNullFlavourDefiningCode;
  }

  public NullFlavour getIdentifikatorDesProbenehmersNullFlavourDefiningCode() {
     return this.identifikatorDesProbenehmersNullFlavourDefiningCode ;
  }

  public void setAngabenZumProbenehmer(List<Cluster> angabenZumProbenehmer) {
     this.angabenZumProbenehmer = angabenZumProbenehmer;
  }

  public List<Cluster> getAngabenZumProbenehmer() {
     return this.angabenZumProbenehmer ;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
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

  public void setAngabenZurLagerung(List<Cluster> angabenZurLagerung) {
     this.angabenZurLagerung = angabenZurLagerung;
  }

  public List<Cluster> getAngabenZurLagerung() {
     return this.angabenZurLagerung ;
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
      List<ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement> identifikatorDerUebergeordnetenProbe) {
     this.identifikatorDerUebergeordnetenProbe = identifikatorDerUebergeordnetenProbe;
  }

  public List<ProbenmaterialIdentifikatorDerUebergeordnetenProbeElement> getIdentifikatorDerUebergeordnetenProbe(
      ) {
     return this.identifikatorDerUebergeordnetenProbe ;
  }

  public void setEignungZurAnalyseDefiningCode(
      EignungZurAnalyseDefiningCode eignungZurAnalyseDefiningCode) {
     this.eignungZurAnalyseDefiningCode = eignungZurAnalyseDefiningCode;
  }

  public EignungZurAnalyseDefiningCode getEignungZurAnalyseDefiningCode() {
     return this.eignungZurAnalyseDefiningCode ;
  }

  public void setEignungZurAnalyseNullFlavourDefiningCode(
      NullFlavour eignungZurAnalyseNullFlavourDefiningCode) {
     this.eignungZurAnalyseNullFlavourDefiningCode = eignungZurAnalyseNullFlavourDefiningCode;
  }

  public NullFlavour getEignungZurAnalyseNullFlavourDefiningCode() {
     return this.eignungZurAnalyseNullFlavourDefiningCode ;
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

  public void setZeitpunktDerProbenentnahme(
      ProbenmaterialZeitpunktDerProbenentnahmeChoice zeitpunktDerProbenentnahme) {
     this.zeitpunktDerProbenentnahme = zeitpunktDerProbenentnahme;
  }

  public ProbenmaterialZeitpunktDerProbenentnahmeChoice getZeitpunktDerProbenentnahme() {
     return this.zeitpunktDerProbenentnahme ;
  }
}
