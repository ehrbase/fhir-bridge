package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.exclusion_specific.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-11T14:34:48.640091+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class NichtDurchgefuehrteProzedurEvaluation implements EntryEntity {
  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/Aussage über den Ausschluss
   * Description: Ein Bericht über den Ausschluss eines/r bestimmten Problems/Diagnose, familiäre Krankengeschichte, Medikation, Verfahren, Nebenwirkung oder eines anderen klinischen Ereignisses.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String aussageUeberDenAusschlussValue;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/Tree/Aussage über den Ausschluss/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour aussageUeberDenAusschlussNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/Eingriff
   * Description: Benennung der Kategorie, des ausgeschlossenen Sachverhalts.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Eingriff']/value|defining_code")
  private NameDerProzedurDefiningCode eingriffDefiningCode;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/Tree/Eingriff/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Eingriff']/null_flavour|defining_code")
  private NullFlavour eingriffNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokale Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAussageUeberDenAusschlussValue(String aussageUeberDenAusschlussValue) {
     this.aussageUeberDenAusschlussValue = aussageUeberDenAusschlussValue;
  }

  public String getAussageUeberDenAusschlussValue() {
     return this.aussageUeberDenAusschlussValue ;
  }

  public void setAussageUeberDenAusschlussNullFlavourDefiningCode(
      NullFlavour aussageUeberDenAusschlussNullFlavourDefiningCode) {
     this.aussageUeberDenAusschlussNullFlavourDefiningCode = aussageUeberDenAusschlussNullFlavourDefiningCode;
  }

  public NullFlavour getAussageUeberDenAusschlussNullFlavourDefiningCode() {
     return this.aussageUeberDenAusschlussNullFlavourDefiningCode ;
  }

  public void setEingriffDefiningCode(NameDerProzedurDefiningCode eingriffDefiningCode) {
     this.eingriffDefiningCode = eingriffDefiningCode;
  }

  public NameDerProzedurDefiningCode getEingriffDefiningCode() {
     return this.eingriffDefiningCode ;
  }

  public void setEingriffNullFlavourDefiningCode(NullFlavour eingriffNullFlavourDefiningCode) {
     this.eingriffNullFlavourDefiningCode = eingriffNullFlavourDefiningCode;
  }

  public NullFlavour getEingriffNullFlavourDefiningCode() {
     return this.eingriffNullFlavourDefiningCode ;
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
