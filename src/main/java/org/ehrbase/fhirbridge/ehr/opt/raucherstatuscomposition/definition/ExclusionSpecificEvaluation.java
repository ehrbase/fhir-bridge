package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.exclusion_specific.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-27T13:21:52.582991+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ExclusionSpecificEvaluation implements EntryEntity {
  /**
   * Path: Register entry/Exclusion - specific/Exclusion statement
   * Description: A qualifying statement about the exclusion of a Problem/diagnosis, Family history, Medication, Procedure, Adverse reaction or other clinical item.
   * Comment: This statement is to be used in conjunction with the 'Excluded concept' data element. For example: this data element can support recording general statements such as "No known history of ..." where the 'Excluded concept' identifies the specific problem, diagnosis, substance, procedure or medication. If the 'Excluded concept' data element is used to record a precoordinated term such as 'No family history of diabetes', this element is redundant.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private ExclusionStatementDefiningCode exclusionStatementDefiningCode;

  /**
   * Path: Register entry/Exclusion - specific/Tree/Exclusion statement/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour exclusionStatementNullFlavourDefiningCode;

  /**
   * Path: Register entry/Exclusion - specific/Problem/diagnosis
   * Description: The problem or diagnosis to which the 'Exclusion statement' applies. For example: 'Diabetes', 'COPD' or 'Asthma'.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Problem/diagnosis']/value|defining_code")
  private RauchverhaltenDefiningCode problemDiagnosisDefiningCode;

  /**
   * Path: Register entry/Exclusion - specific/Tree/Problem/diagnosis/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Problem/diagnosis']/null_flavour|defining_code")
  private NullFlavour problemDiagnosisNullFlavourDefiningCode;

  /**
   * Path: Register entry/Exclusion - specific/Extension
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: For example: Local information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> extension;

  /**
   * Path: Register entry/Exclusion - specific/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Register entry/Exclusion - specific/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Register entry/Exclusion - specific/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setExclusionStatementDefiningCode(
      ExclusionStatementDefiningCode exclusionStatementDefiningCode) {
     this.exclusionStatementDefiningCode = exclusionStatementDefiningCode;
  }

  public ExclusionStatementDefiningCode getExclusionStatementDefiningCode() {
     return this.exclusionStatementDefiningCode ;
  }

  public void setExclusionStatementNullFlavourDefiningCode(
      NullFlavour exclusionStatementNullFlavourDefiningCode) {
     this.exclusionStatementNullFlavourDefiningCode = exclusionStatementNullFlavourDefiningCode;
  }

  public NullFlavour getExclusionStatementNullFlavourDefiningCode() {
     return this.exclusionStatementNullFlavourDefiningCode ;
  }

  public void setProblemDiagnosisDefiningCode(
      RauchverhaltenDefiningCode problemDiagnosisDefiningCode) {
     this.problemDiagnosisDefiningCode = problemDiagnosisDefiningCode;
  }

  public RauchverhaltenDefiningCode getProblemDiagnosisDefiningCode() {
     return this.problemDiagnosisDefiningCode ;
  }

  public void setProblemDiagnosisNullFlavourDefiningCode(
      NullFlavour problemDiagnosisNullFlavourDefiningCode) {
     this.problemDiagnosisNullFlavourDefiningCode = problemDiagnosisNullFlavourDefiningCode;
  }

  public NullFlavour getProblemDiagnosisNullFlavourDefiningCode() {
     return this.problemDiagnosisNullFlavourDefiningCode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
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
