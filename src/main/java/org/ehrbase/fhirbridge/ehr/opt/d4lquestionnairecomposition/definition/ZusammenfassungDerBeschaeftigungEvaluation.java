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

@Entity
@Archetype("openEHR-EHR-EVALUATION.occupation_summary.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.841358+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ZusammenfassungDerBeschaeftigungEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigungsepisode
   * Description: Strukturierte Angaben zu jedem Job oder jeder Rolle, sowohl aktuell als auch in der Vergangenheit.
   * Comment: Eine Person kann mehrere aktive Episoden eines Berufs gleichzeitig haben, wenn sie eine Vielzahl von Jobs oder Rollen hat. Zum Beispiel: Betreuer an 2 Tagen pro Woche und 3 Tage in der Woche in einem Einzelhandelsjob beschäftigt; Teilzeitbeschäftigung zur Unterstützung des Studiums.
   */
  @Path("/data[at0001]/items[at0003]")
  private List<Cluster> beschaeftigungsepisode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigung
   * Description: Ein einzelner Job oder eine einzelne Rolle, die von einer Person während eines bestimmten Zeitraums ausgeführt wurde.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.occupation_record.v1]")
  private BeschaeftigungCluster beschaeftigung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0007]/items[at0008]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeschaeftigungsepisode(List<Cluster> beschaeftigungsepisode) {
     this.beschaeftigungsepisode = beschaeftigungsepisode;
  }

  public List<Cluster> getBeschaeftigungsepisode() {
     return this.beschaeftigungsepisode ;
  }

  public void setBeschaeftigung(BeschaeftigungCluster beschaeftigung) {
     this.beschaeftigung = beschaeftigung;
  }

  public BeschaeftigungCluster getBeschaeftigung() {
     return this.beschaeftigung ;
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
