package org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition;

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
@Archetype("openEHR-EHR-ACTION.procedure.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:55:59.476769+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class ProzedurAction implements EntryEntity {
  /**
   * Path: Prozedur/Prozedur/Name der Prozedur
   * Description: Identifizierung der Prozedur über den Namen.
   * Comment: Wenn möglich wird die Kodierung der spezifischen Prozedur mit einer Terminologie bevorzugt.
   */
  @Path("/description[at0001]/items[at0002]/value|value")
  private String nameDerProzedurValue;

  /**
   * Path: Prozedur/Prozedur/Tree/Name der Prozedur/null_flavour
   */
  @Path("/description[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDerProzedurNullFlavourDefiningCode;

  /**
   * Path: Prozedur/Prozedur/Freitextbeschreibung
   * Description: Beschreibung der Prozedur, angepasst an den "Pathway"-Verlaufsschritt.
   * Comment: Zum Beispiel: Beschreibung der Durchführung und der Ergebnisse dieser Prozedur, des abgebrochenen Versuchs oder der Stornierung der Prozedur.
   */
  @Path("/description[at0001]/items[at0049 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  /**
   * Path: Prozedur/Prozedur/Tree/Freitextbeschreibung/null_flavour
   */
  @Path("/description[at0001]/items[at0049 and name/value='Freitextbeschreibung']/null_flavour|defining_code")
  private NullFlavour freitextbeschreibungNullFlavourDefiningCode;

  /**
   * Path: Prozedur/Prozedur/Details zur Körperstelle
   * Description: Eine physische Stelle am oder innerhalb des menschlichen Körpers.
   */
  @Path("/description[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1 and name/value='Details zur Körperstelle']")
  private List<DetailsZurKoerperstelleCluster> detailsZurKoerperstelle;

  /**
   * Path: Prozedur/Prozedur/Multimedia
   * Description: Multimediale Darstellung der durchgeführten Prozedur.
   */
  @Path("/description[at0001]/items[at0062]")
  private List<Cluster> multimedia;

  /**
   * Path: Prozedur/Prozedur/Durchführungsabsicht
   * Description: Grund, warum die angegebene Aktivität für diese Prozedur durchgeführt wurde.
   * Comment: Zum Beispiel: der Grund für den Abbruch oder die Unterbrechung der Prozedur.
   */
  @Path("/description[at0001]/items[at0014 and name/value='Durchführungsabsicht']")
  private List<ProzedurDurchfuehrungsabsichtElement> durchfuehrungsabsicht;

  /**
   * Path: Prozedur/Prozedur/Antragsteller
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistung anfordert.
   */
  @Path("/protocol[at0053]/items[at0055]")
  private Cluster antragsteller;

  /**
   * Path: Prozedur/Prozedur/Empfänger
   * Description: Angaben über den Gesundheitsdienstleister oder die Organisation, die die Leistungsanforderung erhält.
   */
  @Path("/protocol[at0053]/items[at0057]")
  private List<Cluster> empfaenger;

  /**
   * Path: Prozedur/Prozedur/Erweiterung
   * Description: Zusätzliche Informationen, die erforderlich sind, um lokale Inhalte zu erfassen oder mit anderen Referenzmodellen/Formalismen abzugleichen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR- oder CIMI-Äquivalente.
   */
  @Path("/protocol[at0053]/items[at0064]")
  private List<Cluster> erweiterung;

  /**
   * Path: Prozedur/Prozedur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Prozedur/Prozedur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Prozedur/Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Prozedur/Prozedur/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Prozedur/Prozedur/ism_transition/Careflow_step
   */
  @Path("/ism_transition/careflow_step|defining_code")
  private CareflowStepDefiningCode careflowStepDefiningCode;

  /**
   * Path: Prozedur/Prozedur/ism_transition/Current_state
   */
  @Path("/ism_transition/current_state|defining_code")
  private CurrentStateDefiningCode currentStateDefiningCode;

  /**
   * Path: Prozedur/Prozedur/ism_transition/transition
   */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setNameDerProzedurValue(String nameDerProzedurValue) {
     this.nameDerProzedurValue = nameDerProzedurValue;
  }

  public String getNameDerProzedurValue() {
     return this.nameDerProzedurValue ;
  }

  public void setNameDerProzedurNullFlavourDefiningCode(
      NullFlavour nameDerProzedurNullFlavourDefiningCode) {
     this.nameDerProzedurNullFlavourDefiningCode = nameDerProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerProzedurNullFlavourDefiningCode() {
     return this.nameDerProzedurNullFlavourDefiningCode ;
  }

  public void setFreitextbeschreibungValue(String freitextbeschreibungValue) {
     this.freitextbeschreibungValue = freitextbeschreibungValue;
  }

  public String getFreitextbeschreibungValue() {
     return this.freitextbeschreibungValue ;
  }

  public void setFreitextbeschreibungNullFlavourDefiningCode(
      NullFlavour freitextbeschreibungNullFlavourDefiningCode) {
     this.freitextbeschreibungNullFlavourDefiningCode = freitextbeschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getFreitextbeschreibungNullFlavourDefiningCode() {
     return this.freitextbeschreibungNullFlavourDefiningCode ;
  }

  public void setDetailsZurKoerperstelle(
      List<DetailsZurKoerperstelleCluster> detailsZurKoerperstelle) {
     this.detailsZurKoerperstelle = detailsZurKoerperstelle;
  }

  public List<DetailsZurKoerperstelleCluster> getDetailsZurKoerperstelle() {
     return this.detailsZurKoerperstelle ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }

  public void setDurchfuehrungsabsicht(
      List<ProzedurDurchfuehrungsabsichtElement> durchfuehrungsabsicht) {
     this.durchfuehrungsabsicht = durchfuehrungsabsicht;
  }

  public List<ProzedurDurchfuehrungsabsichtElement> getDurchfuehrungsabsicht() {
     return this.durchfuehrungsabsicht ;
  }

  public void setAntragsteller(Cluster antragsteller) {
     this.antragsteller = antragsteller;
  }

  public Cluster getAntragsteller() {
     return this.antragsteller ;
  }

  public void setEmpfaenger(List<Cluster> empfaenger) {
     this.empfaenger = empfaenger;
  }

  public List<Cluster> getEmpfaenger() {
     return this.empfaenger ;
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
