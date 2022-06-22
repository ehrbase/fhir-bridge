package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T17:43:37.147974583+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("POINT_EVENT")
public class BlutdruckBeliebigesEreignisPointEvent implements PointEventEntity, BlutdruckBeliebigesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Systolisch
   * Description: Der höchste arterielle Blutdruck eines Zyklus - gemessen in der systolischen oder Kontraktionsphase des Herzens.
   */
  @Path("/data[at0003]/items[at0004]/value|magnitude")
  private Double systolischMagnitude;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Systolisch
   * Description: Der höchste arterielle Blutdruck eines Zyklus - gemessen in der systolischen oder Kontraktionsphase des Herzens.
   */
  @Path("/data[at0003]/items[at0004]/value|units")
  private String systolischUnits;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Blutdruck/Systolisch/null_flavour
   */
  @Path("/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour systolischNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Diastolisch
   * Description: Der minimale systemische arterielle Blutdruck eines Zyklus - gemessen in der diastolischen oder Entspannungsphase des Herzens.
   */
  @Path("/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolischMagnitude;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Diastolisch
   * Description: Der minimale systemische arterielle Blutdruck eines Zyklus - gemessen in der diastolischen oder Entspannungsphase des Herzens.
   */
  @Path("/data[at0003]/items[at0005]/value|units")
  private String diastolischUnits;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Blutdruck/Diastolisch/null_flavour
   */
  @Path("/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour diastolischNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/Anstrengung
   * Description: Details über physische Aktivitäten zur Zeit der Blutdruckmessung.
   */
  @Path("/state[at0007]/items[at1030]")
  private Cluster anstrengung;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Blutdruck/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

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
}
