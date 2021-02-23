package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.informed_consent.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:41.185736+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class EinwilligungserklaerungAction implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Prozedur / Studie / Aktivität
   * Description: Identifizierung der Prozedur, klinischen Studie oder der gesundheitsbezogenen Aktivität (einschließlich der richtigen Stelle / des richtigen Standorts, falls zutreffend), für die der Einwilligungsstatus und Details aufgezeichnet werden.
   */
  @Path("/description[at0001]/items[at0002]/value|value")
  private String prozedurStudieAktivitaetValue;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Tree/Prozedur / Studie / Aktivität/null_flavour
   */
  @Path("/description[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour prozedurStudieAktivitaetNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Beschreibung der Einwilligung
   * Description: Beschreibung der Einwilligungserklärung, die erforderlich ist oder aufgezeichnet werden soll, bevor die vorgeschlagene Prozedur, klinische Prüfung oder gesundheitsbezogene Aktivitäten durchgeführt werden.
   */
  @Path("/description[at0001]/items[at0011]/value|value")
  private String beschreibungDerEinwilligungValue;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Tree/Beschreibung der Einwilligung/null_flavour
   */
  @Path("/description[at0001]/items[at0011]/null_flavour|defining_code")
  private NullFlavour beschreibungDerEinwilligungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Adresse
   * Description: Zur Dokumentation einer oder mehrerer Postadressen einer Person oder Einrichtung
   */
  @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.address.v0]")
  private AdresseCluster adresse;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Kommentar
   * Description: Zusätzliche Beschreibung der Prozedur der Einwilligungserklärung, die in anderen Bereichen nicht erfasst wurde.
   */
  @Path("/description[at0001]/items[at0036]/value|value")
  private String kommentarValue;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Tree/Kommentar/null_flavour
   */
  @Path("/description[at0001]/items[at0036]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Angaben zum Einwilligungsnachweis
   * Description: Digitale Darstellung des Einwilligungsnachweises.
   * Comment: Zum Beispiel: ein PDF eines Einwilligungsformulars oder eine Audiodatei.
   */
  @Path("/description[at0001]/items[at0037]")
  private List<Cluster> angabenZumEinwilligungsnachweis;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Anforderer der Einwilligungserklärung
   * Description: Angaben zum Gesundheitsdienstleister, der die Einwilligung anfordert oder aufzeichnet.
   * Comment: Fügen Sie archetypisierte demografische Details in diesen SLOT ein, wenn es nicht möglich ist, other_participations zu verwenden.
   */
  @Path("/protocol[at0024]/items[at0028]")
  private List<Cluster> anfordererDerEinwilligungserklaerung;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Patient/Einwilligende Person
   * Description: Details zu der Person (oder dem Vertreter der Person), die um die Einwilligung zur Prozedur, klinischen Prüfung oder gesundheitsbezogenen Aktivität gebeten wird oder diese erteilt.
   * Comment: Fügen Sie archetypisierte demografische Details in diesen SLOT ein, wenn es nicht möglich ist, other_participations zu verwenden.
   */
  @Path("/protocol[at0024]/items[at0029]")
  private List<Cluster> patientEinwilligendePerson;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/ism_transition/Careflow_step
   */
  @Path("/ism_transition/careflow_step|defining_code")
  private CareflowStepDefiningCode careflowStepDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/ism_transition/Current_state
   */
  @Path("/ism_transition/current_state|defining_code")
  private CurrentStateDefiningCode currentStateDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/ism_transition/transition
   */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setProzedurStudieAktivitaetValue(String prozedurStudieAktivitaetValue) {
     this.prozedurStudieAktivitaetValue = prozedurStudieAktivitaetValue;
  }

  public String getProzedurStudieAktivitaetValue() {
     return this.prozedurStudieAktivitaetValue ;
  }

  public void setProzedurStudieAktivitaetNullFlavourDefiningCode(
      NullFlavour prozedurStudieAktivitaetNullFlavourDefiningCode) {
     this.prozedurStudieAktivitaetNullFlavourDefiningCode = prozedurStudieAktivitaetNullFlavourDefiningCode;
  }

  public NullFlavour getProzedurStudieAktivitaetNullFlavourDefiningCode() {
     return this.prozedurStudieAktivitaetNullFlavourDefiningCode ;
  }

  public void setBeschreibungDerEinwilligungValue(String beschreibungDerEinwilligungValue) {
     this.beschreibungDerEinwilligungValue = beschreibungDerEinwilligungValue;
  }

  public String getBeschreibungDerEinwilligungValue() {
     return this.beschreibungDerEinwilligungValue ;
  }

  public void setBeschreibungDerEinwilligungNullFlavourDefiningCode(
      NullFlavour beschreibungDerEinwilligungNullFlavourDefiningCode) {
     this.beschreibungDerEinwilligungNullFlavourDefiningCode = beschreibungDerEinwilligungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungDerEinwilligungNullFlavourDefiningCode() {
     return this.beschreibungDerEinwilligungNullFlavourDefiningCode ;
  }

  public void setAdresse(AdresseCluster adresse) {
     this.adresse = adresse;
  }

  public AdresseCluster getAdresse() {
     return this.adresse ;
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

  public void setAngabenZumEinwilligungsnachweis(List<Cluster> angabenZumEinwilligungsnachweis) {
     this.angabenZumEinwilligungsnachweis = angabenZumEinwilligungsnachweis;
  }

  public List<Cluster> getAngabenZumEinwilligungsnachweis() {
     return this.angabenZumEinwilligungsnachweis ;
  }

  public void setAnfordererDerEinwilligungserklaerung(
      List<Cluster> anfordererDerEinwilligungserklaerung) {
     this.anfordererDerEinwilligungserklaerung = anfordererDerEinwilligungserklaerung;
  }

  public List<Cluster> getAnfordererDerEinwilligungserklaerung() {
     return this.anfordererDerEinwilligungserklaerung ;
  }

  public void setPatientEinwilligendePerson(List<Cluster> patientEinwilligendePerson) {
     this.patientEinwilligendePerson = patientEinwilligendePerson;
  }

  public List<Cluster> getPatientEinwilligendePerson() {
     return this.patientEinwilligendePerson ;
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

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setCareflowStepDefiningCode(CareflowStepDefiningCode careflowStepDefiningCode) {
     this.careflowStepDefiningCode = careflowStepDefiningCode;
  }

  public CareflowStepDefiningCode getCareflowStepDefiningCode() {
     return this.careflowStepDefiningCode ;
  }

  public void setCurrentStateDefiningCode(CurrentStateDefiningCode currentStateDefiningCode) {
     this.currentStateDefiningCode = currentStateDefiningCode;
  }

  public CurrentStateDefiningCode getCurrentStateDefiningCode() {
     return this.currentStateDefiningCode ;
  }

  public void setTransitionDefiningCode(Transition transitionDefiningCode) {
     this.transitionDefiningCode = transitionDefiningCode;
  }

  public Transition getTransitionDefiningCode() {
     return this.transitionDefiningCode ;
  }
}
