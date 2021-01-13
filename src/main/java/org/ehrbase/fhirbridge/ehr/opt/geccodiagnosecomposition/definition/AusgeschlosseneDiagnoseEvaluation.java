package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.exclusion_specific.v1")
public class AusgeschlosseneDiagnoseEvaluation {
  @Path("/protocol[at0009]/items[at0011]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[at0003]/value|value")
  private String problemDiagnoseValue;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private AussageUberDenAusschlussDefiningcode aussageUberDenAusschlussDefiningcode;

  @Path("/language")
  private Language language;

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setProblemDiagnoseValue(String problemDiagnoseValue) {
     this.problemDiagnoseValue = problemDiagnoseValue;
  }

  public String getProblemDiagnoseValue() {
     return this.problemDiagnoseValue ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setAussageUberDenAusschlussDefiningcode(
      AussageUberDenAusschlussDefiningcode aussageUberDenAusschlussDefiningcode) {
     this.aussageUberDenAusschlussDefiningcode = aussageUberDenAusschlussDefiningcode;
  }

  public AussageUberDenAusschlussDefiningcode getAussageUberDenAusschlussDefiningcode() {
     return this.aussageUberDenAusschlussDefiningcode ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
