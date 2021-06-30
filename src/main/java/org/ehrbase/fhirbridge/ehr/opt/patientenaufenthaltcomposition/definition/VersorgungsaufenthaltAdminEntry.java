package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
@Archetype("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:02.889966500+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class VersorgungsaufenthaltAdminEntry implements EntryEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Beginn
   * Description: Zeitlicher Beginn des Aufenthaltes am beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0004]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Beginn/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour beginnNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Ende
   * Description: Zeitliches Ende des Aufenthaltes am beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0005]/value|value")
  private TemporalAccessor endeValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Ende/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour endeNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Grund des Aufenthaltes
   * Description: Grund des Aufenthaltes
   */
  @Path("/data[at0001]/items[at0006]/value|value")
  private String grundDesAufenthaltesValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Grund des Aufenthaltes/null_flavour
   */
  @Path("/data[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour grundDesAufenthaltesNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort
   * Description: Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]")
  private StandortCluster standort;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Fachliche Organisationseinheit
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Fachliche Organisationseinheit']")
  private List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheit;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Kommentar
   * Description: Zusätzliche Kommentare.
   */
  @Path("/data[at0001]/items[at0009]/value|value")
  private String kommentarValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Kommentar/null_flavour
   */
  @Path("/data[at0001]/items[at0009]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeginnValue(TemporalAccessor beginnValue) {
     this.beginnValue = beginnValue;
  }

  public TemporalAccessor getBeginnValue() {
     return this.beginnValue ;
  }

  public void setBeginnNullFlavourDefiningCode(NullFlavour beginnNullFlavourDefiningCode) {
     this.beginnNullFlavourDefiningCode = beginnNullFlavourDefiningCode;
  }

  public NullFlavour getBeginnNullFlavourDefiningCode() {
     return this.beginnNullFlavourDefiningCode ;
  }

  public void setEndeValue(TemporalAccessor endeValue) {
     this.endeValue = endeValue;
  }

  public TemporalAccessor getEndeValue() {
     return this.endeValue ;
  }

  public void setEndeNullFlavourDefiningCode(NullFlavour endeNullFlavourDefiningCode) {
     this.endeNullFlavourDefiningCode = endeNullFlavourDefiningCode;
  }

  public NullFlavour getEndeNullFlavourDefiningCode() {
     return this.endeNullFlavourDefiningCode ;
  }

  public void setGrundDesAufenthaltesValue(String grundDesAufenthaltesValue) {
     this.grundDesAufenthaltesValue = grundDesAufenthaltesValue;
  }

  public String getGrundDesAufenthaltesValue() {
     return this.grundDesAufenthaltesValue ;
  }

  public void setGrundDesAufenthaltesNullFlavourDefiningCode(
      NullFlavour grundDesAufenthaltesNullFlavourDefiningCode) {
     this.grundDesAufenthaltesNullFlavourDefiningCode = grundDesAufenthaltesNullFlavourDefiningCode;
  }

  public NullFlavour getGrundDesAufenthaltesNullFlavourDefiningCode() {
     return this.grundDesAufenthaltesNullFlavourDefiningCode ;
  }

  public void setStandort(StandortCluster standort) {
     this.standort = standort;
  }

  public StandortCluster getStandort() {
     return this.standort ;
  }

  public void setFachlicheOrganisationseinheit(
      List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheit) {
     this.fachlicheOrganisationseinheit = fachlicheOrganisationseinheit;
  }

  public List<FachlicheOrganisationseinheitCluster> getFachlicheOrganisationseinheit() {
     return this.fachlicheOrganisationseinheit ;
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
