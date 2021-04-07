package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

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

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.585303+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class KoerpertemperaturObservation implements EntryEntity {
  /**
   * Path: Bericht/Körpertemperatur/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Bericht/Körpertemperatur/Protocol/Lokalisation der Messung/null_flavour
   */
  @Path("/protocol[at0020]/items[at0021]/null_flavour|defining_code")
  private NullFlavour lokalisationDerMessungNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Strukturierte Lokalisation der Messung
   * Description: Strukturierte anatomische Lokalisation, an dem die Messung vorgenommen wurde.
   */
  @Path("/protocol[at0020]/items[at0064]")
  private List<Cluster> strukturierteLokalisationDerMessung;

  /**
   * Path: Bericht/Körpertemperatur/Gerät
   * Description: Details über das Gerät, das zur Temperaturmessung benutzt wurde.
   */
  @Path("/protocol[at0020]/items[at0059]")
  private Cluster geraet;

  /**
   * Path: Bericht/Körpertemperatur/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0020]/items[at0062]")
  private List<Cluster> erweiterung;

  /**
   * Path: Bericht/Körpertemperatur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Bericht/Körpertemperatur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Bericht/Körpertemperatur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Bericht/Körpertemperatur/Lokalisation der Messung
   * Description: Der anatomische Lokalisation der Temperaturmessung.
   */
  @Path("/protocol[at0020]/items[at0021]/value")
  @Choice
  private KoerpertemperaturLokalisationDerMessungChoice lokalisationDerMessung;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis
   * Description: Standardwert, ein undefinierter/s Zeitpunkt oder Intervallereignis, das explizit im Template oder zur Laufzeit der Anwendung definiert werden kann.
   */
  @Path("/data[at0002]/events[at0003]")
  @Choice
  private List<KoerpertemperaturBeliebigesEreignisChoice> beliebigesEreignis;

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setLokalisationDerMessungNullFlavourDefiningCode(
      NullFlavour lokalisationDerMessungNullFlavourDefiningCode) {
     this.lokalisationDerMessungNullFlavourDefiningCode = lokalisationDerMessungNullFlavourDefiningCode;
  }

  public NullFlavour getLokalisationDerMessungNullFlavourDefiningCode() {
     return this.lokalisationDerMessungNullFlavourDefiningCode ;
  }

  public void setStrukturierteLokalisationDerMessung(
      List<Cluster> strukturierteLokalisationDerMessung) {
     this.strukturierteLokalisationDerMessung = strukturierteLokalisationDerMessung;
  }

  public List<Cluster> getStrukturierteLokalisationDerMessung() {
     return this.strukturierteLokalisationDerMessung ;
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

  public void setLokalisationDerMessung(
      KoerpertemperaturLokalisationDerMessungChoice lokalisationDerMessung) {
     this.lokalisationDerMessung = lokalisationDerMessung;
  }

  public KoerpertemperaturLokalisationDerMessungChoice getLokalisationDerMessung() {
     return this.lokalisationDerMessung ;
  }

  public void setBeliebigesEreignis(
      List<KoerpertemperaturBeliebigesEreignisChoice> beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<KoerpertemperaturBeliebigesEreignisChoice> getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }
}
