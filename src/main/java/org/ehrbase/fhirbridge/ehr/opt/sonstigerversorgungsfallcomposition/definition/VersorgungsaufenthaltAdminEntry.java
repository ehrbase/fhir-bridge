package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

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
@Archetype("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.505298751+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class VersorgungsaufenthaltAdminEntry implements EntryEntity {
  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Beginn
   * Description: Zeitlicher Beginn des Aufenthaltes am beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0004]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Baum/Beginn/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour beginnNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Ende
   * Description: Zeitliches Ende des Aufenthaltes am beschriebenen Ort.
   */
  @Path("/data[at0001]/items[at0005]/value|value")
  private TemporalAccessor endeValue;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Baum/Ende/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour endeNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Grund des Aufenthaltes
   * Description: Grund des Aufenthaltes
   */
  @Path("/data[at0001]/items[at0006]/value|value")
  private String grundDesAufenthaltesValue;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Baum/Grund des Aufenthaltes/null_flavour
   */
  @Path("/data[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour grundDesAufenthaltesNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Standort
   * Description: Dieser Slot dient der Erfassung des Standortes des Patienten während des Krankenhausaufenthaltes.
   */
  @Path("/data[at0001]/items[at0008]")
  private Cluster standort;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/Verantwortliche Organisationseinheit
   * Description: Verantwortliche Organisationseinheit, z.B. die fachliche Organisationseinheit (Fachabteilung).
   */
  @Path("/data[at0001]/items[at0013]")
  private List<Cluster> verantwortlicheOrganisationseinheit;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt/feeder_audit
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

  public void setStandort(Cluster standort) {
     this.standort = standort;
  }

  public Cluster getStandort() {
     return this.standort ;
  }

  public void setVerantwortlicheOrganisationseinheit(
      List<Cluster> verantwortlicheOrganisationseinheit) {
     this.verantwortlicheOrganisationseinheit = verantwortlicheOrganisationseinheit;
  }

  public List<Cluster> getVerantwortlicheOrganisationseinheit() {
     return this.verantwortlicheOrganisationseinheit ;
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
