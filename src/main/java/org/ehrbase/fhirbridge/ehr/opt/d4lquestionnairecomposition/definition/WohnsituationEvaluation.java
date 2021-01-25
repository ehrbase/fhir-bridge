package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.living_arrangement.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.787045+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class WohnsituationEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/Wohnsituation
   * Description: Einzelnes Word oder Satz, um zu beschreiben, ob eine Person für gewöhnlich allein oder mit anderen zusammen wohnt.
   */
  @Path("/data[at0001]/items[at0004]/value|defining_code")
  private WohnsituationDefiningCode wohnsituationDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/Item tree/Wohnsituation/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour wohnsituationNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/Ergänzende Details
   * Description: Weitere Details über die Wohnsituation.
   * Comment: Dieser SLOT kann verwendet werden, um weitere Archetypen zu verschachteln, die die Wohnsituation mit ergänzenden Details beschreiben und einem lokalen Zuständigkeitsbereich angehören können.
   */
  @Path("/data[at0001]/items[at0008]")
  private List<Cluster> ergaenzendeDetails;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusäztliche Metadaten zur Anpassung an FHIR.
   */
  @Path("/protocol[at0002]/items[at0011]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Wohnsituation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setWohnsituationDefiningCode(WohnsituationDefiningCode wohnsituationDefiningCode) {
     this.wohnsituationDefiningCode = wohnsituationDefiningCode;
  }

  public WohnsituationDefiningCode getWohnsituationDefiningCode() {
     return this.wohnsituationDefiningCode ;
  }

  public void setWohnsituationNullFlavourDefiningCode(
      NullFlavour wohnsituationNullFlavourDefiningCode) {
     this.wohnsituationNullFlavourDefiningCode = wohnsituationNullFlavourDefiningCode;
  }

  public NullFlavour getWohnsituationNullFlavourDefiningCode() {
     return this.wohnsituationNullFlavourDefiningCode ;
  }

  public void setErgaenzendeDetails(List<Cluster> ergaenzendeDetails) {
     this.ergaenzendeDetails = ergaenzendeDetails;
  }

  public List<Cluster> getErgaenzendeDetails() {
     return this.ergaenzendeDetails ;
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
