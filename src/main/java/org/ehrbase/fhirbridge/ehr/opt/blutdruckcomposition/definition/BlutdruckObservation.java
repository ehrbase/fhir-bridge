package org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.blood_pressure.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-09T12:53:04.961502+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class BlutdruckObservation implements EntryEntity {
  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/Systolisch
   * Description: Der höchste arterielle Blutdruck eines Zyklus - gemessen in der systolischen oder Kontraktionsphase des Herzens.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|magnitude")
  private Double systolischMagnitude;

  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/Systolisch
   * Description: Der höchste arterielle Blutdruck eines Zyklus - gemessen in der systolischen oder Kontraktionsphase des Herzens.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|units")
  private String systolischUnits;

  /**
   * Path: Blutdruck/Blutdruck/Historie/Beliebiges Ereignis/Blutdruck/Systolisch/null_flavour
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour systolischNullFlavourDefiningCode;

  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/Diastolisch
   * Description: Der minimale systemische arterielle Blutdruck eines Zyklus - gemessen in der diastolischen oder Entspannungsphase des Herzens.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolischMagnitude;

  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/Diastolisch
   * Description: Der minimale systemische arterielle Blutdruck eines Zyklus - gemessen in der diastolischen oder Entspannungsphase des Herzens.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|units")
  private String diastolischUnits;

  /**
   * Path: Blutdruck/Blutdruck/Historie/Beliebiges Ereignis/Blutdruck/Diastolisch/null_flavour
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour diastolischNullFlavourDefiningCode;

  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/Anstrengung
   * Description: Details über physische Aktivitäten zur Zeit der Blutdruckmessung.
   */
  @Path("/data[at0001]/events[at0006]/state[at0007]/items[at1030]")
  private Cluster anstrengung;

  /**
   * Path: Blutdruck/Blutdruck/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0006]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Blutdruck/Blutdruck/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Blutdruck/Blutdruck/Strukturierte Stelle der Messung
   * Description: Strukturierte Körperstelle an der der Blutdruck gemessen wurde.
   */
  @Path("/protocol[at0011]/items[at1057]")
  private List<Cluster> strukturierteStelleDerMessung;

  /**
   * Path: Blutdruck/Blutdruck/Gerät
   * Description: Details über das Sphygmomanometer oder ein anderes Gerät, dass zur Blutdruckmessung verwendet wird.
   */
  @Path("/protocol[at0011]/items[at1025]")
  private Cluster geraet;

  /**
   * Path: Blutdruck/Blutdruck/Erweiterung
   * Description: Zusätzliche Information, die für die Erfassung des lokalen Kontexts oder für die Anpassung an andere Referenzmodelle/Formalismen benötigt wird.
   * Comment: Zum Beispiel: Informationen bzgl. der lokalen Krankenhausabteilung oder zusätzliche Metadata zur Anpassung an entsprechende FHIR oder CIMI Gegenstücke.
   */
  @Path("/protocol[at0011]/items[at1058]")
  private List<Cluster> erweiterung;

  /**
   * Path: Blutdruck/Blutdruck/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Blutdruck/Blutdruck/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Blutdruck/Blutdruck/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSystolischMagnitude(Double systolischMagnitude) {
     this.systolischMagnitude = systolischMagnitude;
  }

  public Double getSystolischMagnitude() {
     return this.systolischMagnitude ;
  }

  public void setSystolischUnits(String systolischUnits) {
     this.systolischUnits = systolischUnits;
  }

  public String getSystolischUnits() {
     return this.systolischUnits ;
  }

  public void setSystolischNullFlavourDefiningCode(NullFlavour systolischNullFlavourDefiningCode) {
     this.systolischNullFlavourDefiningCode = systolischNullFlavourDefiningCode;
  }

  public NullFlavour getSystolischNullFlavourDefiningCode() {
     return this.systolischNullFlavourDefiningCode ;
  }

  public void setDiastolischMagnitude(Double diastolischMagnitude) {
     this.diastolischMagnitude = diastolischMagnitude;
  }

  public Double getDiastolischMagnitude() {
     return this.diastolischMagnitude ;
  }

  public void setDiastolischUnits(String diastolischUnits) {
     this.diastolischUnits = diastolischUnits;
  }

  public String getDiastolischUnits() {
     return this.diastolischUnits ;
  }

  public void setDiastolischNullFlavourDefiningCode(
      NullFlavour diastolischNullFlavourDefiningCode) {
     this.diastolischNullFlavourDefiningCode = diastolischNullFlavourDefiningCode;
  }

  public NullFlavour getDiastolischNullFlavourDefiningCode() {
     return this.diastolischNullFlavourDefiningCode ;
  }

  public void setAnstrengung(Cluster anstrengung) {
     this.anstrengung = anstrengung;
  }

  public Cluster getAnstrengung() {
     return this.anstrengung ;
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
}
