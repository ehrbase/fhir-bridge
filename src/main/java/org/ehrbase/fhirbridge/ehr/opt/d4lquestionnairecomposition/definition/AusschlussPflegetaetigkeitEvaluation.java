package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
    date = "2021-01-25T13:06:40.801753+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class AusschlussPflegetaetigkeitEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/Aussage über den Ausschluss
   * Description: Ein Bericht über den Ausschluss eines/r bestimmten Problems/Diagnose, familiäre Krankengeschichte, Medikation, Verfahren, Nebenwirkung oder eines anderen klinischen Ereignisses.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String aussageUeberDenAusschlussValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/Tree/Aussage über den Ausschluss/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour aussageUeberDenAusschlussNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/Ausgeschlossene Kategorie
   * Description: Benennung der Kategorie, des ausgeschlossenen Sachverhalts.
   */
  @Path("/data[at0001]/items[at0003]/value|value")
  private String ausgeschlosseneKategorieValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/Tree/Ausgeschlossene Kategorie/null_flavour
   */
  @Path("/data[at0001]/items[at0003]/null_flavour|defining_code")
  private NullFlavour ausgeschlosseneKategorieNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokale Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Ausschluss - Pflegetätigkeit/feeder_audit
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

  public void setAusgeschlosseneKategorieValue(String ausgeschlosseneKategorieValue) {
     this.ausgeschlosseneKategorieValue = ausgeschlosseneKategorieValue;
  }

  public String getAusgeschlosseneKategorieValue() {
     return this.ausgeschlosseneKategorieValue ;
  }

  public void setAusgeschlosseneKategorieNullFlavourDefiningCode(
      NullFlavour ausgeschlosseneKategorieNullFlavourDefiningCode) {
     this.ausgeschlosseneKategorieNullFlavourDefiningCode = ausgeschlosseneKategorieNullFlavourDefiningCode;
  }

  public NullFlavour getAusgeschlosseneKategorieNullFlavourDefiningCode() {
     return this.ausgeschlosseneKategorieNullFlavourDefiningCode ;
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
