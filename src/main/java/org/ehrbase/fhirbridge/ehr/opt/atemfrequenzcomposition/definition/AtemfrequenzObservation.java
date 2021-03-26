package org.ehrbase.fhirbridge.ehr.opt.atemfrequenzcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
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
@Archetype("openEHR-EHR-OBSERVATION.respiration.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:50:22.087786+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class AtemfrequenzObservation implements EntryEntity {
  /**
   * Path: Atemfrequenz/Atemfrequenz/Beliebiges Ereignis/Messwert
   * Description: Die Frequenz der Spontanatmung.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double messwertMagnitude;

  /**
   * Path: Atemfrequenz/Atemfrequenz/Beliebiges Ereignis/Messwert
   * Description: Die Frequenz der Spontanatmung.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String messwertUnits;

  /**
   * Path: Atemfrequenz/Atemfrequenz/History/Beliebiges Ereignis/*List(en)/Messwert/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour messwertNullFlavourDefiningCode;

  /**
   * Path: Atemfrequenz/Atemfrequenz/Beliebiges Ereignis/Inspirierter Sauerstoff
   * Description: Angaben über die Sauerstoffmenge, die der Person zum Zeitpunkt der Beobachtung verabreicht wurde.
   * Comment: Es werden Werte von 21% Sauerstoffkonzentration, Fi02 von 0,21 und eine Sauerstoffflussrate von 0 l/min oder 0 ml/min angenommen.
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0055]")
  private Cluster inspirierterSauerstoff;

  /**
   * Path: Atemfrequenz/Atemfrequenz/Beliebiges Ereignis/Anwendung
   * Description: Angaben über die körperliche Anstrengung, die während der Untersuchung unternommen wurde.
   * Comment: Der Grad der Anstrengung der Person während oder kurz vor der Untersuchung.
   */
  @Path("/data[at0001]/events[at0002]/state[at0022]/items[at0037]")
  private Cluster anwendung;

  /**
   * Path: Atemfrequenz/Atemfrequenz/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Atemfrequenz/Atemfrequenz/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Atemfrequenz/Atemfrequenz/Erweiterung
   * Description: Zusätzliche Informationen, die zur Erfassung lokaler Inhalte oder zur Anpassung an andere Referenzmodelle/Formalismen erforderlich sind.
   * Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten, um Verknüpfungen mit FHIR oder CIMI Äquivalenten herzustellen.
   */
  @Path("/protocol[at0057]/items[at0058]")
  private List<Cluster> erweiterung;

  /**
   * Path: Atemfrequenz/Atemfrequenz/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Atemfrequenz/Atemfrequenz/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Atemfrequenz/Atemfrequenz/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setMesswertMagnitude(Double messwertMagnitude) {
     this.messwertMagnitude = messwertMagnitude;
  }

  public Double getMesswertMagnitude() {
     return this.messwertMagnitude ;
  }

  public void setMesswertUnits(String messwertUnits) {
     this.messwertUnits = messwertUnits;
  }

  public String getMesswertUnits() {
     return this.messwertUnits ;
  }

  public void setMesswertNullFlavourDefiningCode(NullFlavour messwertNullFlavourDefiningCode) {
     this.messwertNullFlavourDefiningCode = messwertNullFlavourDefiningCode;
  }

  public NullFlavour getMesswertNullFlavourDefiningCode() {
     return this.messwertNullFlavourDefiningCode ;
  }

  public void setInspirierterSauerstoff(Cluster inspirierterSauerstoff) {
     this.inspirierterSauerstoff = inspirierterSauerstoff;
  }

  public Cluster getInspirierterSauerstoff() {
     return this.inspirierterSauerstoff ;
  }

  public void setAnwendung(Cluster anwendung) {
     this.anwendung = anwendung;
  }

  public Cluster getAnwendung() {
     return this.anwendung ;
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
