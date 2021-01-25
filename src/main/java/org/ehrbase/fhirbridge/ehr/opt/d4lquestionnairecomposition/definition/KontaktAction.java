package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

@Entity
@Archetype("openEHR-EHR-ACTION.contact.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.874981+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class KontaktAction implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Kontakt zu einem bestätigten Fall
   * Description: Beschreibung des Kontaktes zwischen den beiden Personen.
   */
  @Path("/description[at0001]/items[at0009 and name/value='Kontakt zu einem bestätigten Fall']/value|defining_code")
  private AelterOderGleich65JahreAltDefiningCode kontaktZuEinemBestaetigtenFallDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Baum/Kontakt zu einem bestätigten Fall/null_flavour
   */
  @Path("/description[at0001]/items[at0009 and name/value='Kontakt zu einem bestätigten Fall']/null_flavour|defining_code")
  private NullFlavour kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Beginn
   * Description: Das Datum und/oder die Uhrzeit an dem der Kontakt begonnen hat.
   */
  @Path("/description[at0001]/items[at0006]/value|value")
  private TemporalAccessor beginnValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Baum/Beginn/null_flavour
   */
  @Path("/description[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour beginnNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Ende
   * Description: Das Datum und/oder die Uhrzeit an dem der Kontakt geendet hat.
   */
  @Path("/description[at0001]/items[at0016]/value|value")
  private TemporalAccessor endeValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Baum/Ende/null_flavour
   */
  @Path("/description[at0001]/items[at0016]/null_flavour|defining_code")
  private NullFlavour endeNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/Details zum Kontakt
   * Description: Weitere Angaben zum Kontakt zwischen zwei Personen.
   */
  @Path("/description[at0001]/items[at0005]")
  private List<Cluster> detailsZumKontakt;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/ism_transition/current_state
   */
  @Path("/ism_transition/current_state")
  private DvCodedText currentState;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Kontakt/ism_transition/transition
   */
  @Path("/ism_transition/transition|defining_code")
  private Transition transitionDefiningCode;

  public void setKontaktZuEinemBestaetigtenFallDefiningCode(
      AelterOderGleich65JahreAltDefiningCode kontaktZuEinemBestaetigtenFallDefiningCode) {
     this.kontaktZuEinemBestaetigtenFallDefiningCode = kontaktZuEinemBestaetigtenFallDefiningCode;
  }

  public AelterOderGleich65JahreAltDefiningCode getKontaktZuEinemBestaetigtenFallDefiningCode() {
     return this.kontaktZuEinemBestaetigtenFallDefiningCode ;
  }

  public void setKontaktZuEinemBestaetigtenFallNullFlavourDefiningCode(
      NullFlavour kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode) {
     this.kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode = kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode;
  }

  public NullFlavour getKontaktZuEinemBestaetigtenFallNullFlavourDefiningCode() {
     return this.kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode ;
  }

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

  public void setDetailsZumKontakt(List<Cluster> detailsZumKontakt) {
     this.detailsZumKontakt = detailsZumKontakt;
  }

  public List<Cluster> getDetailsZumKontakt() {
     return this.detailsZumKontakt ;
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

  public void setCurrentState(DvCodedText currentState) {
     this.currentState = currentState;
  }

  public DvCodedText getCurrentState() {
     return this.currentState ;
  }

  public void setTransitionDefiningCode(Transition transitionDefiningCode) {
     this.transitionDefiningCode = transitionDefiningCode;
  }

  public Transition getTransitionDefiningCode() {
     return this.transitionDefiningCode ;
  }
}
