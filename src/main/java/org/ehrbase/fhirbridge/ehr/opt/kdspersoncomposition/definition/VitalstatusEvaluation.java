package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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

@Entity
@Archetype("openEHR-EHR-EVALUATION.vital_status.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.165477204+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class VitalstatusEvaluation implements EntryEntity {
  /**
   * Path: Person/Vitalstatus/Vitalstatus
   * Description: Vitalstatus der Person.
   * Comment: Zum Beispiel: Patient lebt oder ist verstorben. Nach Möglichkeit wird die Codierung des Vitalstatus mit einer Terminologie bevorzugt.
   * fhir_mapping: Observation.value[x].coding:Vitalstatus.
   */
  @Path("/data[at0001]/items[at0006]/value|value")
  private String vitalstatusValue;

  /**
   * Path: Person/Vitalstatus/Item tree/Vitalstatus/null_flavour
   */
  @Path("/data[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour vitalstatusNullFlavourDefiningCode;

  /**
   * Path: Person/Vitalstatus/FHIR_Status der Beobachtung
   * Description: Ein Code, der den Status einer Beobachtung angibt, z.B. registriert, korrigiert oder final.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.observation_status_fhir.v1]")
  private FhirStatusDerBeobachtungCluster fhirStatusDerBeobachtung;

  /**
   * Path: Person/Vitalstatus/Zeitpunkt der Feststellung
   * Description: Die Zeit oder Zeitspanne, zu der der Vitalstatus als wahr behauptet wird.
   * Comment: Partielle Datumsangaben sind auch erlaubt. Enthält nicht den Todeszeitpunkt. fhir_mapping: Observation.effective[x].
   */
  @Path("/protocol[at0002]/items[at0018]/value|value")
  private TemporalAccessor zeitpunktDerFeststellungValue;

  /**
   * Path: Person/Vitalstatus/Item tree/Zeitpunkt der Feststellung/null_flavour
   */
  @Path("/protocol[at0002]/items[at0018]/null_flavour|defining_code")
  private NullFlavour zeitpunktDerFeststellungNullFlavourDefiningCode;

  /**
   * Path: Person/Vitalstatus/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/ Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0002]/items[at0021]")
  private List<Cluster> erweiterung;

  /**
   * Path: Person/Vitalstatus/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Person/Vitalstatus/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Person/Vitalstatus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setVitalstatusValue(String vitalstatusValue) {
     this.vitalstatusValue = vitalstatusValue;
  }

  public String getVitalstatusValue() {
     return this.vitalstatusValue ;
  }

  public void setVitalstatusNullFlavourDefiningCode(
      NullFlavour vitalstatusNullFlavourDefiningCode) {
     this.vitalstatusNullFlavourDefiningCode = vitalstatusNullFlavourDefiningCode;
  }

  public NullFlavour getVitalstatusNullFlavourDefiningCode() {
     return this.vitalstatusNullFlavourDefiningCode ;
  }

  public void setFhirStatusDerBeobachtung(
      FhirStatusDerBeobachtungCluster fhirStatusDerBeobachtung) {
     this.fhirStatusDerBeobachtung = fhirStatusDerBeobachtung;
  }

  public FhirStatusDerBeobachtungCluster getFhirStatusDerBeobachtung() {
     return this.fhirStatusDerBeobachtung ;
  }

  public void setZeitpunktDerFeststellungValue(TemporalAccessor zeitpunktDerFeststellungValue) {
     this.zeitpunktDerFeststellungValue = zeitpunktDerFeststellungValue;
  }

  public TemporalAccessor getZeitpunktDerFeststellungValue() {
     return this.zeitpunktDerFeststellungValue ;
  }

  public void setZeitpunktDerFeststellungNullFlavourDefiningCode(
      NullFlavour zeitpunktDerFeststellungNullFlavourDefiningCode) {
     this.zeitpunktDerFeststellungNullFlavourDefiningCode = zeitpunktDerFeststellungNullFlavourDefiningCode;
  }

  public NullFlavour getZeitpunktDerFeststellungNullFlavourDefiningCode() {
     return this.zeitpunktDerFeststellungNullFlavourDefiningCode ;
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
