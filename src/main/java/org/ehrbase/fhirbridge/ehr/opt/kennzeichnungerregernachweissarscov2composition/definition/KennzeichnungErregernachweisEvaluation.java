package org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
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
@Archetype("openEHR-EHR-EVALUATION.flag_pathogen.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:20.638167+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class KennzeichnungErregernachweisEvaluation implements EntryEntity {
  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Erregernachweis
   * Description: Bei dem Patienten wurde ein Erreger nachgewiesen.
   */
  @Path("/data[at0001]/items[at0005]/value|value")
  private Boolean erregernachweisValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Erregernachweis/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour erregernachweisNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Erregername
   * Description: Der Name des nachgewiesenen Erregers bei dem Patienten.
   */
  @Path("/data[at0001]/items[at0012]/value|defining_code")
  private ErregernameDefiningCode erregernameDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Erregername/null_flavour
   */
  @Path("/data[at0001]/items[at0012]/null_flavour|defining_code")
  private NullFlavour erregernameNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Hinweistext
   * Description: Ein Freitext mit Hinweisen zu dem nachgewiesenen Erreger bei dem Patienten.
   */
  @Path("/data[at0001]/items[at0013]/value|value")
  private String hinweistextValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Hinweistext/null_flavour
   */
  @Path("/data[at0001]/items[at0013]/null_flavour|defining_code")
  private NullFlavour hinweistextNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Zeitpunkt der Kennzeichnung
   * Description: *
   */
  @Path("/data[at0001]/items[at0015]/value|value")
  private TemporalAccessor zeitpunktDerKennzeichnungValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Zeitpunkt der Kennzeichnung/null_flavour
   */
  @Path("/data[at0001]/items[at0015]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerKennzeichnungNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Erregernachweis in der Klinik
   * Description: Bei dem Patienten wurde der Erreger in der Klinik nachgewiesen.
   */
  @Path("/data[at0001]/items[at0011]/value|value")
  private Boolean erregernachweisInDerKlinikValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Erregernachweis in der Klinik/null_flavour
   */
  @Path("/data[at0001]/items[at0011]/null_flavour|defining_code")
  private NullFlavour erregernachweisInDerKlinikNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Zuletzt aktualisiert
   * Description: Datum der letzten Aktualisierung der Diagnose bzw. des Problems.
   */
  @Path("/protocol[at0003]/items[at0004]/value|value")
  private TemporalAccessor zuletztAktualisiertValue;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Baum/Zuletzt aktualisiert/null_flavour
   */
  @Path("/protocol[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour zuletztAktualisiertNullFlavourDefiningCode;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   */
  @Path("/protocol[at0003]/items[at0007]")
  private List<Cluster> erweiterung;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Kennzeichnung Erregernachweis SARS-CoV-2/Kennzeichnung Erregernachweis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setErregernachweisValue(Boolean erregernachweisValue) {
     this.erregernachweisValue = erregernachweisValue;
  }

  public Boolean isErregernachweisValue() {
     return this.erregernachweisValue ;
  }

  public void setErregernachweisNullFlavourDefiningCode(
      NullFlavour erregernachweisNullFlavourDefiningCode) {
     this.erregernachweisNullFlavourDefiningCode = erregernachweisNullFlavourDefiningCode;
  }

  public NullFlavour getErregernachweisNullFlavourDefiningCode() {
     return this.erregernachweisNullFlavourDefiningCode ;
  }

  public void setErregernameDefiningCode(ErregernameDefiningCode erregernameDefiningCode) {
     this.erregernameDefiningCode = erregernameDefiningCode;
  }

  public ErregernameDefiningCode getErregernameDefiningCode() {
     return this.erregernameDefiningCode ;
  }

  public void setErregernameNullFlavourDefiningCode(
      NullFlavour erregernameNullFlavourDefiningCode) {
     this.erregernameNullFlavourDefiningCode = erregernameNullFlavourDefiningCode;
  }

  public NullFlavour getErregernameNullFlavourDefiningCode() {
     return this.erregernameNullFlavourDefiningCode ;
  }

  public void setHinweistextValue(String hinweistextValue) {
     this.hinweistextValue = hinweistextValue;
  }

  public String getHinweistextValue() {
     return this.hinweistextValue ;
  }

  public void setHinweistextNullFlavourDefiningCode(
      NullFlavour hinweistextNullFlavourDefiningCode) {
     this.hinweistextNullFlavourDefiningCode = hinweistextNullFlavourDefiningCode;
  }

  public NullFlavour getHinweistextNullFlavourDefiningCode() {
     return this.hinweistextNullFlavourDefiningCode ;
  }

  public void setZeitpunktDerKennzeichnungValue(TemporalAccessor zeitpunktDerKennzeichnungValue) {
     this.zeitpunktDerKennzeichnungValue = zeitpunktDerKennzeichnungValue;
  }

  public TemporalAccessor getZeitpunktDerKennzeichnungValue() {
     return this.zeitpunktDerKennzeichnungValue ;
  }

  public void setZeitpunktDerKennzeichnungNullFlavourDefiningCode(
      NullFlavour zeitpunktDerKennzeichnungNullFlavourDefiningCode) {
     this.zeitpunktDerKennzeichnungNullFlavourDefiningCode = zeitpunktDerKennzeichnungNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDerKennzeichnungNullFlavourDefiningCode() {
     return this.zeitpunktDerKennzeichnungNullFlavourDefiningCode ;
  }

  public void setErregernachweisInDerKlinikValue(Boolean erregernachweisInDerKlinikValue) {
     this.erregernachweisInDerKlinikValue = erregernachweisInDerKlinikValue;
  }

  public Boolean isErregernachweisInDerKlinikValue() {
     return this.erregernachweisInDerKlinikValue ;
  }

  public void setErregernachweisInDerKlinikNullFlavourDefiningCode(
      NullFlavour erregernachweisInDerKlinikNullFlavourDefiningCode) {
     this.erregernachweisInDerKlinikNullFlavourDefiningCode = erregernachweisInDerKlinikNullFlavourDefiningCode;
  }

  public NullFlavour getErregernachweisInDerKlinikNullFlavourDefiningCode() {
     return this.erregernachweisInDerKlinikNullFlavourDefiningCode ;
  }

  public void setZuletztAktualisiertValue(TemporalAccessor zuletztAktualisiertValue) {
     this.zuletztAktualisiertValue = zuletztAktualisiertValue;
  }

  public TemporalAccessor getZuletztAktualisiertValue() {
     return this.zuletztAktualisiertValue ;
  }

  public void setZuletztAktualisiertNullFlavourDefiningCode(
      NullFlavour zuletztAktualisiertNullFlavourDefiningCode) {
     this.zuletztAktualisiertNullFlavourDefiningCode = zuletztAktualisiertNullFlavourDefiningCode;
  }

  public NullFlavour getZuletztAktualisiertNullFlavourDefiningCode() {
     return this.zuletztAktualisiertNullFlavourDefiningCode ;
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
