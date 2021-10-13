package org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
@Archetype("openEHR-EHR-EVALUATION.absence.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-13T17:05:46.220257+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class UnbekannterImpfstatusEvaluation implements EntryEntity {
  /**
   * Path: Impfstatus/Unbekannter Impfstatus/Aussage über Abwesenheit
   * Description: Positive Aussage, dass keine Informationen verfügbar sind.
   * Comment: Zum Beispiel: "Es liegen keine Informationen über Nebenwirkungen vor"; "Es liegen keine Informationen über Probleme oder Diagnosen vor"; "Es liegen keine Informationen über vorangegangene Verfahren vor"; oder "Es liegen keine Informationen über verwendete Medikamente vor".
   */
  @Path("/data[at0001]/items[at0002]/value")
  private DvCodedText aussageUeberAbwesenheit;

  /**
   * Path: Impfstatus/Unbekannter Impfstatus/Baum/Aussage über Abwesenheit/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour aussageUeberAbwesenheitNullFlavourDefiningCode;

  /**
   * Path: Impfstatus/Unbekannter Impfstatus/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Kommentar: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0003]/items[at0006]")
  private List<Cluster> erweiterung;

  /**
   * Path: Impfstatus/Unbekannter Impfstatus/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Impfstatus/Unbekannter Impfstatus/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Impfstatus/Unbekannter Impfstatus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAussageUeberAbwesenheit(DvCodedText aussageUeberAbwesenheit) {
     this.aussageUeberAbwesenheit = aussageUeberAbwesenheit;
  }

  public DvCodedText getAussageUeberAbwesenheit() {
     return this.aussageUeberAbwesenheit ;
  }

  public void setAussageUeberAbwesenheitNullFlavourDefiningCode(
      NullFlavour aussageUeberAbwesenheitNullFlavourDefiningCode) {
     this.aussageUeberAbwesenheitNullFlavourDefiningCode = aussageUeberAbwesenheitNullFlavourDefiningCode;
  }

  public NullFlavour getAussageUeberAbwesenheitNullFlavourDefiningCode() {
     return this.aussageUeberAbwesenheitNullFlavourDefiningCode ;
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
