package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
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
@Archetype("openEHR-EHR-EVALUATION.care_activity.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.819796+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class PflegetaetigkeitEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Privat?
   * Description: Handelt es sich um eine private Pflegetätigkeit?
   */
  @Path("/data[at0001]/items[at0020]/value|value")
  private Boolean privatValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Item tree/Privat?/null_flavour
   */
  @Path("/data[at0001]/items[at0020]/null_flavour|defining_code")
  private NullFlavour privatNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Anzahl der gepflegten Personen
   * Description: Anzahl der Personen die gepflegt werden
   */
  @Path("/data[at0001]/items[at0005]/value|value")
  private String anzahlDerGepflegtenPersonenValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Item tree/Anzahl der gepflegten Personen/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour anzahlDerGepflegtenPersonenNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Frequenz der Pflege
   * Description: Die Frequenz der Pflegetätigkeit.
   */
  @Path("/data[at0001]/items[at0008]/value|value")
  private String frequenzDerPflegeValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Item tree/Frequenz der Pflege/null_flavour
   */
  @Path("/data[at0001]/items[at0008]/null_flavour|defining_code")
  private NullFlavour frequenzDerPflegeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Grund für die Tätigkeit
   * Description: Grund für die Pflegetätigkeit
   */
  @Path("/data[at0001]/items[at0011]")
  private List<PflegetaetigkeitGrundFuerDieTaetigkeitElement> grundFuerDieTaetigkeit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/Item tree
   * Description: @ internal @
   */
  @Path("/protocol[at0007]")
  private ItemTree itemTree;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Pflegetätigkeit/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPrivatValue(Boolean privatValue) {
     this.privatValue = privatValue;
  }

  public Boolean isPrivatValue() {
     return this.privatValue ;
  }

  public void setPrivatNullFlavourDefiningCode(NullFlavour privatNullFlavourDefiningCode) {
     this.privatNullFlavourDefiningCode = privatNullFlavourDefiningCode;
  }

  public NullFlavour getPrivatNullFlavourDefiningCode() {
     return this.privatNullFlavourDefiningCode ;
  }

  public void setAnzahlDerGepflegtenPersonenValue(String anzahlDerGepflegtenPersonenValue) {
     this.anzahlDerGepflegtenPersonenValue = anzahlDerGepflegtenPersonenValue;
  }

  public String getAnzahlDerGepflegtenPersonenValue() {
     return this.anzahlDerGepflegtenPersonenValue ;
  }

  public void setAnzahlDerGepflegtenPersonenNullFlavourDefiningCode(
      NullFlavour anzahlDerGepflegtenPersonenNullFlavourDefiningCode) {
     this.anzahlDerGepflegtenPersonenNullFlavourDefiningCode = anzahlDerGepflegtenPersonenNullFlavourDefiningCode;
  }

  public NullFlavour getAnzahlDerGepflegtenPersonenNullFlavourDefiningCode() {
     return this.anzahlDerGepflegtenPersonenNullFlavourDefiningCode ;
  }

  public void setFrequenzDerPflegeValue(String frequenzDerPflegeValue) {
     this.frequenzDerPflegeValue = frequenzDerPflegeValue;
  }

  public String getFrequenzDerPflegeValue() {
     return this.frequenzDerPflegeValue ;
  }

  public void setFrequenzDerPflegeNullFlavourDefiningCode(
      NullFlavour frequenzDerPflegeNullFlavourDefiningCode) {
     this.frequenzDerPflegeNullFlavourDefiningCode = frequenzDerPflegeNullFlavourDefiningCode;
  }

  public NullFlavour getFrequenzDerPflegeNullFlavourDefiningCode() {
     return this.frequenzDerPflegeNullFlavourDefiningCode ;
  }

  public void setGrundFuerDieTaetigkeit(
      List<PflegetaetigkeitGrundFuerDieTaetigkeitElement> grundFuerDieTaetigkeit) {
     this.grundFuerDieTaetigkeit = grundFuerDieTaetigkeit;
  }

  public List<PflegetaetigkeitGrundFuerDieTaetigkeitElement> getGrundFuerDieTaetigkeit() {
     return this.grundFuerDieTaetigkeit ;
  }

  public void setItemTree(ItemTree itemTree) {
     this.itemTree = itemTree;
  }

  public ItemTree getItemTree() {
     return this.itemTree ;
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
