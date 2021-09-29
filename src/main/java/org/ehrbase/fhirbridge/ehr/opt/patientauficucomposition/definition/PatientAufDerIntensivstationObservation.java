package org.ehrbase.fhirbridge.ehr.opt.patientauficucomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
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
@Archetype("openEHR-EHR-OBSERVATION.management_screening.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-14T15:13:11.186633+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PatientAufDerIntensivstationObservation implements EntryEntity {
  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/Beliebiges Ereignis/Management-/Behandlungsaktivität/Name der Aktivität
   * Description: Name der geprüften Management- oder Behandlungsaktivität.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value|value")
  private String nameDerAktivitaetValue;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/History/Beliebiges Ereignis/Tree/Management-/Behandlungsaktivität/Name der Aktivität/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/null_flavour|defining_code")
  private NullFlavour nameDerAktivitaetNullFlavourDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/Beliebiges Ereignis/Management-/Behandlungsaktivität/Wird/Wurde die Aktivität durchgeführt?
   * Description: Aktueller Status der spezifischen Aktivität.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005 and name/value='Wird/Wurde die Aktivität durchgeführt?']/value|defining_code")
  private WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode wirdWurdeDieAktivitaetDurchgefuehrtDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/History/Beliebiges Ereignis/Tree/Management-/Behandlungsaktivität/Wird/Wurde die Aktivität durchgeführt?/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005 and name/value='Wird/Wurde die Aktivität durchgeführt?']/null_flavour|defining_code")
  private NullFlavour wirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/Beliebiges Ereignis/Management-/Behandlungsaktivität/Detaillierte Angaben zur Aktivität
   * Description: Zusätzliche detaillierte Angaben zu der spezifischen Aktivität.
   * Comment: Zum Beispiel: Details zur Sauerstofftherapie.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0036]")
  private List<Cluster> detaillierteAngabenZurAktivitaet;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0007]/items[at0021]")
  private List<Cluster> erweiterung;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Patient auf der Intensivstation/Patient auf der Intensivstation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerAktivitaetValue(String nameDerAktivitaetValue) {
     this.nameDerAktivitaetValue = nameDerAktivitaetValue;
  }

  public String getNameDerAktivitaetValue() {
     return this.nameDerAktivitaetValue ;
  }

  public void setNameDerAktivitaetNullFlavourDefiningCode(
      NullFlavour nameDerAktivitaetNullFlavourDefiningCode) {
     this.nameDerAktivitaetNullFlavourDefiningCode = nameDerAktivitaetNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerAktivitaetNullFlavourDefiningCode() {
     return this.nameDerAktivitaetNullFlavourDefiningCode ;
  }

  public void setWirdWurdeDieAktivitaetDurchgefuehrtDefiningCode(
      WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode wirdWurdeDieAktivitaetDurchgefuehrtDefiningCode) {
     this.wirdWurdeDieAktivitaetDurchgefuehrtDefiningCode = wirdWurdeDieAktivitaetDurchgefuehrtDefiningCode;
  }

  public WirdWurdeDieAktivitaetDurchgefuehrtDefiningCode getWirdWurdeDieAktivitaetDurchgefuehrtDefiningCode(
      ) {
     return this.wirdWurdeDieAktivitaetDurchgefuehrtDefiningCode ;
  }

  public void setWirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode(
      NullFlavour wirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode) {
     this.wirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode = wirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode;
  }

  public NullFlavour getWirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode() {
     return this.wirdWurdeDieAktivitaetDurchgefuehrtNullFlavourDefiningCode ;
  }

  public void setDetaillierteAngabenZurAktivitaet(List<Cluster> detaillierteAngabenZurAktivitaet) {
     this.detaillierteAngabenZurAktivitaet = detaillierteAngabenZurAktivitaet;
  }

  public List<Cluster> getDetaillierteAngabenZurAktivitaet() {
     return this.detaillierteAngabenZurAktivitaet ;
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
