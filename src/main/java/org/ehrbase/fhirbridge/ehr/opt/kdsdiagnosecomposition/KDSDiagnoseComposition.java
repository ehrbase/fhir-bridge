package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.PrimaercodeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.ProblemDiagnoseAttributCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition.SekundaercodeEvaluation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.575838844+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@Template("KDS_Diagnose")
public class KDSDiagnoseComposition implements CompositionEntity {
  /**
   * Path: Diagnose/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Diagnose/context/Bericht ID
   * Description: Identifizierungsmerkmal des Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Path: Diagnose/context/Tree/Bericht ID/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour berichtIdNullFlavourDefiningCode;

  /**
   * Path: Diagnose/context/Status
   * Description: Der Status des gesamten Berichts. Hinweis: Dies ist nicht der Status einer Berichtskomponente.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Path: Diagnose/context/Tree/Status/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Diagnose/context/Fallidentifikation
   * Description: Zur Erfassung von Details zur Identifikation eines Falls im Gesundheitswesen.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  private FallidentifikationCluster fallidentifikation;

  /**
   * Path: Diagnose/context/Problem/Diagnose Attribut
   * Description: Kontextabhängiges oder temporäres Attribut für ein bestimmtes Problem oder eine bestimmte Diagnose.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v2]")
  private ProblemDiagnoseAttributCluster problemDiagnoseAttribut;

  /**
   * Path: Diagnose/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Diagnose/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Diagnose/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Diagnose/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Diagnose/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Diagnose/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Diagnose/Primärcode
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Primärcode']")
  private List<PrimaercodeEvaluation> primaercode;

  /**
   * Path: Diagnose/Sekundärcode
   * Description: Angaben über einen einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Sekundärcode']")
  private List<SekundaercodeEvaluation> sekundaercode;

  /**
   * Path: Diagnose/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Diagnose/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Diagnose/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Diagnose/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setBerichtIdValue(String berichtIdValue) {
     this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
     return this.berichtIdValue ;
  }

  public void setBerichtIdNullFlavourDefiningCode(NullFlavour berichtIdNullFlavourDefiningCode) {
     this.berichtIdNullFlavourDefiningCode = berichtIdNullFlavourDefiningCode;
  }

  public NullFlavour getBerichtIdNullFlavourDefiningCode() {
     return this.berichtIdNullFlavourDefiningCode ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
     this.fallidentifikation = fallidentifikation;
  }

  public FallidentifikationCluster getFallidentifikation() {
     return this.fallidentifikation ;
  }

  public void setProblemDiagnoseAttribut(ProblemDiagnoseAttributCluster problemDiagnoseAttribut) {
     this.problemDiagnoseAttribut = problemDiagnoseAttribut;
  }

  public ProblemDiagnoseAttributCluster getProblemDiagnoseAttribut() {
     return this.problemDiagnoseAttribut ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setPrimaercode(List<PrimaercodeEvaluation> primaercode) {
     this.primaercode = primaercode;
  }

  public List<PrimaercodeEvaluation> getPrimaercode() {
     return this.primaercode ;
  }

  public void setSekundaercode(List<SekundaercodeEvaluation> sekundaercode) {
     this.sekundaercode = sekundaercode;
  }

  public List<SekundaercodeEvaluation> getSekundaercode() {
     return this.sekundaercode ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
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

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
