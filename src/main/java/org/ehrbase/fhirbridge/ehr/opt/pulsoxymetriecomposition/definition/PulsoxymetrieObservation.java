package org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse_oximetry.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-19T12:28:18.376452+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PulsoxymetrieObservation implements EntryEntity {
  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/SpO₂
   * Description: Die Sättigung des Sauerstoffs im peripheren Blut, mittels Pulsoxymetrie gemessen.
   * Comment: SpO₂ ist definiert als der prozentuale Anteil von Oxyhämoglobin (HbO₂) an der Gesamtkonzentration von Hämoglobin (HbO₂ + Deoxyhämoglobin) im peripheren Blut.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
  private DvProportion spo;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Event Series/Beliebiges Ereignis/Baum/SpO₂/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/null_flavour|defining_code")
  private NullFlavour spoNullFlavourDefiningCode;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/Wellenform
   * Description: Eine mittels Oximetermessung verbundene Wellenformmessung.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0054]")
  private List<Cluster> wellenform;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/Multimedia-Bild
   * Description: Details einer Reihe von Oximetrie-Messwerten, die keine Wellenformen sind, ausgedrückt als Multimediabild oder Bilderserie. Wellenformen sollten unter der Verwendung des Wellenform Slots und des zugehörigen Cluster-Archetyps dargestellt werden.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0060]")
  private List<Cluster> multimediaBild;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/Anstrengung
   * Description: Details über physische Aktivitäten zur Zeit der Messung.
   */
  @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0034]")
  private Cluster anstrengung;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/Eingeatmeter Sauerstoff
   * Description: Details über die Sauerstoffmenge, die dem Probanden zum Zeitpunkt der Beobachtung zur Verfügung steht.
   * Comment: Angenommene Werte von 21% Sauerstoffkonzentration, Fi0₂ von 0,21 und Sauerstoffdurchflussrate von 0 l/min oder 0 ml/min.
   */
  @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0015]")
  private Cluster eingeatmeterSauerstoff;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Oxymetrie Gerät
   * Description: Details über das verwendeten nicht-invasiven Oximetriegeräte.
   */
  @Path("/protocol[at0007]/items[at0018]")
  private Cluster oxymetrieGeraet;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/Erweiterung
   * Description: Zusätzliche Information, die für die Erfassung des lokalen Kontexts oder für die Anpassung an andere Referenzmodelle/Formalismen benötigt wird.
   * Comment: Zum Beispiel: Informationen bzgl. der lokalen Krankenhausabteilung oder zusätzliche Metadata zur Anpassung an entsprechende FHIR oder CIMI Gegenstücke.
   */
  @Path("/protocol[at0007]/items[at0059]")
  private List<Cluster> erweiterung;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Pulsoxymetrie/Pulsoxymetrie/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSpo(DvProportion spo) {
     this.spo = spo;
  }

  public DvProportion getSpo() {
     return this.spo ;
  }

  public void setSpoNullFlavourDefiningCode(NullFlavour spoNullFlavourDefiningCode) {
     this.spoNullFlavourDefiningCode = spoNullFlavourDefiningCode;
  }

  public NullFlavour getSpoNullFlavourDefiningCode() {
     return this.spoNullFlavourDefiningCode ;
  }

  public void setWellenform(List<Cluster> wellenform) {
     this.wellenform = wellenform;
  }

  public List<Cluster> getWellenform() {
     return this.wellenform ;
  }

  public void setMultimediaBild(List<Cluster> multimediaBild) {
     this.multimediaBild = multimediaBild;
  }

  public List<Cluster> getMultimediaBild() {
     return this.multimediaBild ;
  }

  public void setAnstrengung(Cluster anstrengung) {
     this.anstrengung = anstrengung;
  }

  public Cluster getAnstrengung() {
     return this.anstrengung ;
  }

  public void setEingeatmeterSauerstoff(Cluster eingeatmeterSauerstoff) {
     this.eingeatmeterSauerstoff = eingeatmeterSauerstoff;
  }

  public Cluster getEingeatmeterSauerstoff() {
     return this.eingeatmeterSauerstoff ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setOxymetrieGeraet(Cluster oxymetrieGeraet) {
     this.oxymetrieGeraet = oxymetrieGeraet;
  }

  public Cluster getOxymetrieGeraet() {
     return this.oxymetrieGeraet ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
