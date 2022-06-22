package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.blood_pressure.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T17:43:37.113832784+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BlutdruckObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Blutdruck/24 Stunden Durchschnitt
   * Description: Schätzung des durchschnittlichen Blutdrucks über einen 24-stündigen Zeitraum.
   */
  @Path("/data[at0001]/events[at1042]")
  private Blutdruck24StundenDurchschnittIntervalEvent N24StundenDurchschnitt;

  /**
   * Path: Selbstüberwachung/Blutdruck/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Blutdruck/Listenstruktur/Stelle der Messung/null_flavour
   */
  @Path("/protocol[at0011]/items[at0014]/null_flavour|defining_code")
  private NullFlavour stelleDerMessungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Blutdruck/Strukturierte Stelle der Messung
   * Description: Strukturierte Körperstelle an der der Blutdruck gemessen wurde.
   */
  @Path("/protocol[at0011]/items[at1057]")
  private List<Cluster> strukturierteStelleDerMessung;

  /**
   * Path: Selbstüberwachung/Blutdruck/Gerät
   * Description: Details über das Sphygmomanometer oder ein anderes Gerät, dass zur Blutdruckmessung verwendet wird.
   */
  @Path("/protocol[at0011]/items[at1025]")
  private Cluster geraet;

  /**
   * Path: Selbstüberwachung/Blutdruck/Erweiterung
   * Description: Zusätzliche Information, die für die Erfassung des lokalen Kontexts oder für die Anpassung an andere Referenzmodelle/Formalismen benötigt wird.
   * Comment: Zum Beispiel: Informationen bzgl. der lokalen Krankenhausabteilung oder zusätzliche Metadata zur Anpassung an entsprechende FHIR oder CIMI Gegenstücke.
   */
  @Path("/protocol[at0011]/items[at1058]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Blutdruck/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Blutdruck/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Blutdruck/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis
   * Description: Standardmäßiger, nicht näher beschriebener Zeitpunkt oder Intervall Ereignis welches in einem Template oder bei der Anwendung genauer definiert werden kann.
   */
  @Path("/data[at0001]/events[at0006]")
  @Choice
  private BlutdruckBeliebigesEreignisChoice beliebigesEreignis;

  /**
   * Path: Selbstüberwachung/Blutdruck/Stelle der Messung
   * Description: Einfache Körperstelle an der der Blutdruck gemessen wurde.
   */
  @Path("/protocol[at0011]/items[at0014]/value")
  @Choice
  private BlutdruckStelleDerMessungChoice stelleDerMessung;

  public void setN24StundenDurchschnitt(
      Blutdruck24StundenDurchschnittIntervalEvent N24StundenDurchschnitt) {
     this.N24StundenDurchschnitt = N24StundenDurchschnitt;
  }

  public Blutdruck24StundenDurchschnittIntervalEvent getN24StundenDurchschnitt() {
     return this.N24StundenDurchschnitt ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setStelleDerMessungNullFlavourDefiningCode(
      NullFlavour stelleDerMessungNullFlavourDefiningCode) {
     this.stelleDerMessungNullFlavourDefiningCode = stelleDerMessungNullFlavourDefiningCode;
  }

  public NullFlavour getStelleDerMessungNullFlavourDefiningCode() {
     return this.stelleDerMessungNullFlavourDefiningCode ;
  }

  public void setStrukturierteStelleDerMessung(List<Cluster> strukturierteStelleDerMessung) {
     this.strukturierteStelleDerMessung = strukturierteStelleDerMessung;
  }

  public List<Cluster> getStrukturierteStelleDerMessung() {
     return this.strukturierteStelleDerMessung ;
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

  public void setBeliebigesEreignis(BlutdruckBeliebigesEreignisChoice beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public BlutdruckBeliebigesEreignisChoice getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }

  public void setStelleDerMessung(BlutdruckStelleDerMessungChoice stelleDerMessung) {
     this.stelleDerMessung = stelleDerMessung;
  }

  public BlutdruckStelleDerMessungChoice getStelleDerMessung() {
     return this.stelleDerMessung ;
  }
}
