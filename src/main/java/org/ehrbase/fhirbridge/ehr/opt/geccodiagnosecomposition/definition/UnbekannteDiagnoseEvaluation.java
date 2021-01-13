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
@Archetype("openEHR-EHR-EVALUATION.absence.v2")
public class UnbekannteDiagnoseEvaluation {
  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private AussageUberDieFehlendeInformationDefiningcode aussageUberDieFehlendeInformationDefiningcode;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String unbekannteDiagnoseValue;

  @Path("/protocol[at0003]/items[at0006]")
  private List<Cluster> erweiterung;

  @Path("/language")
  private Language language;

  public void setAussageUberDieFehlendeInformationDefiningcode(
      AussageUberDieFehlendeInformationDefiningcode aussageUberDieFehlendeInformationDefiningcode) {
     this.aussageUberDieFehlendeInformationDefiningcode = aussageUberDieFehlendeInformationDefiningcode;
  }

  public AussageUberDieFehlendeInformationDefiningcode getAussageUberDieFehlendeInformationDefiningcode(
      ) {
     return this.aussageUberDieFehlendeInformationDefiningcode ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setUnbekannteDiagnoseValue(String unbekannteDiagnoseValue) {
     this.unbekannteDiagnoseValue = unbekannteDiagnoseValue;
  }

  public String getUnbekannteDiagnoseValue() {
     return this.unbekannteDiagnoseValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
