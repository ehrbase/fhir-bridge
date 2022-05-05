package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pulse.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-05T11:59:39.153858+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PulsfrequenzHerzfrequenzObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Ruhepuls
   * Description: Ein Standardwert, ein spezifizierter Zeitpunkt oder ein Intervallereignis, welches explizit in einem Template oder während der Laufzeit definiert werden kann.
   */
  @Path("/data[at0002]/events[at0003 and name/value='Ruhepuls']")
  private PulsfrequenzHerzfrequenzRuhepulsEvent ruhepuls;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Mittlere Herzfrequenz
   * Description: Ein Standardwert, ein spezifizierter Zeitpunkt oder ein Intervallereignis, welches explizit in einem Template oder während der Laufzeit definiert werden kann.
   */
  @Path("/data[at0002]/events[at0003 and name/value='Mittlere Herzfrequenz']")
  private PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent mittlereHerzfrequenz;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Methode
   * Description: Die Methode, mit der die Puls- oder Herzfrequenz gemessen wurde.
   * Comment: Zum Beispiel auskultatorisch oder über elektronisches Monitoring, z.B. mit einem Blutdruckmessgerät.
   */
  @Path("/protocol[at0010]/items[at1019]/value|defining_code")
  private MethodeDefiningCode methodeDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/List/Methode/null_flavour
   */
  @Path("/protocol[at0010]/items[at1019]/null_flavour|defining_code")
  private NullFlavour methodeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Gerät
   * Description: Informationen zu dem Gerät, welches zur Messung der Puls- oder der Herzfrequenz verwendet wurde.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster geraet;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis
   * Description: Ein Standardwert, ein spezifizierter Zeitpunkt oder ein Intervallereignis, welches explizit in einem Template oder während der Laufzeit definiert werden kann.
   */
  @Path("/data[at0002]/events[at0003 and name/value='Jedes Ereignis']")
  @Choice
  private List<PulsfrequenzHerzfrequenzJedesEreignisChoice> jedesEreignis;

  public void setRuhepuls(PulsfrequenzHerzfrequenzRuhepulsEvent ruhepuls) {
     this.ruhepuls = ruhepuls;
  }

  public PulsfrequenzHerzfrequenzRuhepulsEvent getRuhepuls() {
     return this.ruhepuls ;
  }

  public void setMittlereHerzfrequenz(
      PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent mittlereHerzfrequenz) {
     this.mittlereHerzfrequenz = mittlereHerzfrequenz;
  }

  public PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent getMittlereHerzfrequenz() {
     return this.mittlereHerzfrequenz ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setMethodeDefiningCode(MethodeDefiningCode methodeDefiningCode) {
     this.methodeDefiningCode = methodeDefiningCode;
  }

  public MethodeDefiningCode getMethodeDefiningCode() {
     return this.methodeDefiningCode ;
  }

  public void setMethodeNullFlavourDefiningCode(NullFlavour methodeNullFlavourDefiningCode) {
     this.methodeNullFlavourDefiningCode = methodeNullFlavourDefiningCode;
  }

  public NullFlavour getMethodeNullFlavourDefiningCode() {
     return this.methodeNullFlavourDefiningCode ;
  }

  public void setGeraet(Cluster geraet) {
     this.geraet = geraet;
  }

  public Cluster getGeraet() {
     return this.geraet ;
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

  public void setJedesEreignis(List<PulsfrequenzHerzfrequenzJedesEreignisChoice> jedesEreignis) {
     this.jedesEreignis = jedesEreignis;
  }

  public List<PulsfrequenzHerzfrequenzJedesEreignisChoice> getJedesEreignis() {
     return this.jedesEreignis ;
  }
}
