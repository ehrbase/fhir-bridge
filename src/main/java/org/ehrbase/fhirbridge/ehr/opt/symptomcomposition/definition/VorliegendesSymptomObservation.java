package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
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
@Archetype("openEHR-EHR-OBSERVATION.symptom_sign.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-28T16:11:42.466616+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class VorliegendesSymptomObservation implements EntryEntity {
  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Name des Symptoms/Krankheitsanzeichens
   * Description: Der Name des berichteten Symptoms/Krankheitsanzeichens.
   * Comment: Der Name des Symptoms sollte, wenn möglich, mit einer Terminologie kodiert werden.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0001]/value")
  private DvCodedText nameDesSymptomsKrankheitsanzeichens;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/Event Series/*Any event(en)/Tree/Name des Symptoms/Krankheitsanzeichens/null_flavour
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0001]/null_flavour|defining_code")
  private NullFlavour nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Anatomische Lokalisation
   * Description: Anatomische Lokalisation des Symptoms/Anzeichens.
   * Comment: Das Auftreten dieses Datenelements wird auf 0...* gesetzt, um bei Bedarf mehrere Körperstellen im Template voneinander zu trennen. Dies ermöglicht die Darstellung klinischer Szenarien, in denen ein Symptom/Krankheitsanzeichen an mehreren Stellen erfasst werden muss oder in denen sowohl die ursprüngliche als auch die distale Stelle bei der Schmerzausbreitung identifiziert werden, aber alle anderen Attribute wie Wirkung und Dauer identisch sind. Wenn die Anforderungen an die Erfassung der Lokalisation zur Laufzeit durch die Anwendung festgelegt werden oder komplexere Modellierungen wie z.B. relative Positionen erforderlich sind, verwenden Sie CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des Slots "Spezifische anatomische Lokalisation" in diesem Archetyp. 
   * Wird die anatomische Lokalisation über vordefinierte Codes in den Symptomnamen aufgenommen, wird dieses Datenelement redundant. Wenn die anatomische Lokalisation mit dem Slot "Spezifische anatomische Lokalisation" erfasst wird, ist die Verwendung dieses Datenelements nicht erlaubt - erfassen Sie entweder die grobe "Anatomische Lokalisation" oder die "Spezifische anatomische Lokalisation", nicht beides.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0151]")
  private List<VorliegendesSymptomAnatomischeLokalisationElement> anatomischeLokalisation;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Spezifische anatomische Lokalisation
   * Description: Spezifische anatomische Lokalisation des Symptoms/Krankheitsanzeichens.
   * Comment: Wenn die anatomische Lokalisation über vordefinierte Codes in den Symptomnamen aufgenommen wird, wird die Verwendung dieses Slots überflüssig. Wenn die anatomische Lokalisation mit dem Datenelement "Anatomische Lokalisation" erfasst wird, ist die Verwendung von CLUSTER-Archetypen in diesem Slot nicht erlaubt - erfassen Sie entweder die grobe "Anatomische Lokalisation" oder die "Spezifische anatomische Lokalisation", nicht beides.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0147]")
  private List<Cluster> spezifischeAnatomischeLokalisation;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Beginn der Episode
   * Description: Der Beginn der Episode dieses Symptoms/Krankheitsanzeichens.
   * Comment: Teil-Datumsangaben sind zulässig, gegebenenfalls kann aber auch das genaue Datum und die genaue Uhrzeit des Beginns erfasst werden. Wenn das Symptom/Krankheitsanzeichen zum ersten Mal auftritt oder ein Wiederauftreten vorliegt, wird dieses Datum verwendet, um den Beginn dieser Episode darzustellen. Wenn das Symptom/Krankheitsanzeichen andauernd ist, kann dieses Datenelement redundant sein, wenn es zuvor bereits erfasst wurde.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0152]/value|value")
  private TemporalAccessor beginnDerEpisodeValue;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/Event Series/*Any event(en)/Tree/Beginn der Episode/null_flavour
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0152]/null_flavour|defining_code")
  private NullFlavour beginnDerEpisodeNullFlavourDefiningCode;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Schweregrad
   * Description: Kategorie, die den allgemeinen Schweregrad des Symptoms/Krankheitsanzeichens beschreibt.
   * Comment: Werte wie leicht, moderat oder schwer so zu definieren, dass sie auf mehrere Symptome/Befunde anwendbar sind und von verschiedenen Benutzern interpretiert und einheitlich dokumentiert werden können, ist nicht einfach. Einige Organisationen erweitern die Wertemenge, indem sie zusätzliche Werte, wie z.B. "trivial", "sehr stark", "leicht-moderat" oder "moderat-schwer", miteinbeziehen, was zu Definitionsschwierigkeiten führt und auch die Zuverlässigkeit von Aufzeichnungen von verschiedenen Protokollanten verschlechtern kann. Die Verwendung von "lebensbedrohlich" und "tödlich" wird ebenfalls oft als Teil dieser Wertemenge betrachtet, obwohl sie eher ein Ergebnis als einen Schweregrad widerspiegelt. In Anbetracht dessen wird die Einhaltung einer gut definierten, aber kürzeren Liste bevorzugt, so dass der leichte/mittlere/schwere Wertebereich angeboten wird. Die Wahl eines anderen Textes wird durch die Aufnahme anderer Wertebereiche für dieses Datenelement im Template ermöglicht. Hinweis: Eine spezifischere Einstufung des Schweregrads kann mit Hilfe der Slots "Spezifische Details" vorgenommen werden.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0021]/value")
  private DvCodedText schweregrad;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/Event Series/*Any event(en)/Tree/Schweregrad/null_flavour
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0021]/null_flavour|defining_code")
  private NullFlavour schweregradNullFlavourDefiningCode;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Spezifische Details
   * Description: Spezifische Datenelemente, die zusätzlich erforderlich sind, um eindeutige Attribute des identifizierten Symptoms/Krankheitsanzeichens zu erfassen.
   * Comment: Zum Beispiel: CTCAE Einteilung.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0153]")
  private List<Cluster> spezifischeDetails;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Datum/Uhrzeit des Rückgangs
   * Description: Der Endzeitpunkt dieser Episode des Symptoms/Krankheitsanzeichens.
   * Comment: Wenn in Systemen "Datum/Uhrzeit des Beginns" und "Dauer" verwendet werden, kann dieses Datenelement berechnet oder alternativ als redundant betrachtet werden. Teil-Datumsangaben sind zulässig, gegebenenfalls kann aber auch das genaue Datum und die genaue Uhrzeit des Rückgangs erfasst werden.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0161]/value|value")
  private TemporalAccessor datumUhrzeitDesRueckgangsValue;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/Event Series/*Any event(en)/Tree/Datum/Uhrzeit des Rückgangs/null_flavour
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0161]/null_flavour|defining_code")
  private NullFlavour datumUhrzeitDesRueckgangsNullFlavourDefiningCode;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Vorangegangene Episoden
   * Description: Strukturierte Details des Symptoms/Befundes während einer früheren Episode.
   * Comment: In vernetzten klinischen Systemen ist es möglich, dass vorangegangene Episoden bereits in der elektronischen Gesundheitsakte (engl. Electronic Health Record - EHR) erfasst wurden. Die Systeme können es dem Arzt ermöglichen, auf relevante vorangegangene Episoden zu verweisen. In einem System oder einer Nachricht ohne eine Verlinkung zu bestehenden Daten oder bei einem neuen Patienten können zusätzliche Instanzen des Symptom-Archetyps aufgenommen werden, um frühere Episoden darzustellen. Es wird empfohlen, dass neue Instanzen des Symptom-Archetyps, die in diesen Slot eingefügt werden, eine oder mehrere vorangegangene Episoden dieser Symptom-Instanz darstellen.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0146]")
  private List<Cluster> vorangegangeneEpisoden;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Assoziierte Symptome/Krankheitsanzeichen
   * Description: Strukturierte Details über alle assoziierten Symptome/Krankheitsanzeichen, die gleichzeitig auftreten.
   * Comment: In vernetzten klinischen Systemen ist es möglich, dass verbundene Symptome/Krankheitsanzeichen bereits in der elektronischen Gesundheitsakte (engl. Electronic Health Record - EHR) erfasst wurden. Die Systeme können es dem Arzt ermöglichen, auf relevante in Zusammenhang stehende Symptomen/Krankheitsanzeichen zu verweisen. In einem System oder einer Nachricht ohne eine Verlinkung zu bestehenden Daten oder bei einem neuen Patienten können zusätzliche Instanzen des Symptom-Archetyps hier aufgenommen werden, um damit verbundene Symptome/Krankheitsanzeichen darzustellen.
   */
  @Path("/data[at0190]/events[at0191]/data[at0192]/items[at0063]")
  private List<Cluster> assoziierteSymptomeKrankheitsanzeichen;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/time
   */
  @Path("/data[at0190]/events[at0191]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/origin
   */
  @Path("/data[at0190]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Extension(en)
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: *For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.(en)
   */
  @Path("/protocol[at0193]/items[at0194]")
  private List<Cluster> extensionEn;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDesSymptomsKrankheitsanzeichens(
      DvCodedText nameDesSymptomsKrankheitsanzeichens) {
     this.nameDesSymptomsKrankheitsanzeichens = nameDesSymptomsKrankheitsanzeichens;
  }

  public DvCodedText getNameDesSymptomsKrankheitsanzeichens() {
     return this.nameDesSymptomsKrankheitsanzeichens ;
  }

  public void setNameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode(
      NullFlavour nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode) {
     this.nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode = nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode;
  }

  public NullFlavour getNameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode() {
     return this.nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode ;
  }

  public void setAnatomischeLokalisation(
      List<VorliegendesSymptomAnatomischeLokalisationElement> anatomischeLokalisation) {
     this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public List<VorliegendesSymptomAnatomischeLokalisationElement> getAnatomischeLokalisation() {
     return this.anatomischeLokalisation ;
  }

  public void setSpezifischeAnatomischeLokalisation(
      List<Cluster> spezifischeAnatomischeLokalisation) {
     this.spezifischeAnatomischeLokalisation = spezifischeAnatomischeLokalisation;
  }

  public List<Cluster> getSpezifischeAnatomischeLokalisation() {
     return this.spezifischeAnatomischeLokalisation ;
  }

  public void setBeginnDerEpisodeValue(TemporalAccessor beginnDerEpisodeValue) {
     this.beginnDerEpisodeValue = beginnDerEpisodeValue;
  }

  public TemporalAccessor getBeginnDerEpisodeValue() {
     return this.beginnDerEpisodeValue ;
  }

  public void setBeginnDerEpisodeNullFlavourDefiningCode(
      NullFlavour beginnDerEpisodeNullFlavourDefiningCode) {
     this.beginnDerEpisodeNullFlavourDefiningCode = beginnDerEpisodeNullFlavourDefiningCode;
  }

  public NullFlavour getBeginnDerEpisodeNullFlavourDefiningCode() {
     return this.beginnDerEpisodeNullFlavourDefiningCode ;
  }

  public void setSchweregrad(DvCodedText schweregrad) {
     this.schweregrad = schweregrad;
  }

  public DvCodedText getSchweregrad() {
     return this.schweregrad ;
  }

  public void setSchweregradNullFlavourDefiningCode(
      NullFlavour schweregradNullFlavourDefiningCode) {
     this.schweregradNullFlavourDefiningCode = schweregradNullFlavourDefiningCode;
  }

  public NullFlavour getSchweregradNullFlavourDefiningCode() {
     return this.schweregradNullFlavourDefiningCode ;
  }

  public void setSpezifischeDetails(List<Cluster> spezifischeDetails) {
     this.spezifischeDetails = spezifischeDetails;
  }

  public List<Cluster> getSpezifischeDetails() {
     return this.spezifischeDetails ;
  }

  public void setDatumUhrzeitDesRueckgangsValue(TemporalAccessor datumUhrzeitDesRueckgangsValue) {
     this.datumUhrzeitDesRueckgangsValue = datumUhrzeitDesRueckgangsValue;
  }

  public TemporalAccessor getDatumUhrzeitDesRueckgangsValue() {
     return this.datumUhrzeitDesRueckgangsValue ;
  }

  public void setDatumUhrzeitDesRueckgangsNullFlavourDefiningCode(
      NullFlavour datumUhrzeitDesRueckgangsNullFlavourDefiningCode) {
     this.datumUhrzeitDesRueckgangsNullFlavourDefiningCode = datumUhrzeitDesRueckgangsNullFlavourDefiningCode;
  }

  public NullFlavour getDatumUhrzeitDesRueckgangsNullFlavourDefiningCode() {
     return this.datumUhrzeitDesRueckgangsNullFlavourDefiningCode ;
  }

  public void setVorangegangeneEpisoden(List<Cluster> vorangegangeneEpisoden) {
     this.vorangegangeneEpisoden = vorangegangeneEpisoden;
  }

  public List<Cluster> getVorangegangeneEpisoden() {
     return this.vorangegangeneEpisoden ;
  }

  public void setAssoziierteSymptomeKrankheitsanzeichen(
      List<Cluster> assoziierteSymptomeKrankheitsanzeichen) {
     this.assoziierteSymptomeKrankheitsanzeichen = assoziierteSymptomeKrankheitsanzeichen;
  }

  public List<Cluster> getAssoziierteSymptomeKrankheitsanzeichen() {
     return this.assoziierteSymptomeKrankheitsanzeichen ;
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

  public void setExtensionEn(List<Cluster> extensionEn) {
     this.extensionEn = extensionEn;
  }

  public List<Cluster> getExtensionEn() {
     return this.extensionEn ;
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
