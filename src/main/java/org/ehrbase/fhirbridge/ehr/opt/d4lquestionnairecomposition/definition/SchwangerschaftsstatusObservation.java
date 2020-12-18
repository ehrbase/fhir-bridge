package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pregnancy_status.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-18T10:30:38.676227+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class SchwangerschaftsstatusObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Erweiterungen
   * Description: Zusätzliche Informationen, die zur Erfassung lokaler Inhalte oder zur Anpassung an andere Referenzmodelle/Formalismen erforderlich sind.
   * Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten, um Verknüpfungen mit FHIR herzustellen.
   */
  @Path("/protocol[at0021]/items[at0022]")
  private List<Cluster> erweiterungen;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis
   * Description: *
   */
  @Path("/data[at0001]/events[at0002]")
  @Choice
  private List<SchwangerschaftsstatusBeliebigesEreignisChoice> beliebigesEreignis;

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setErweiterungen(List<Cluster> erweiterungen) {
     this.erweiterungen = erweiterungen;
  }

  public List<Cluster> getErweiterungen() {
     return this.erweiterungen ;
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

  public void setBeliebigesEreignis(
      List<SchwangerschaftsstatusBeliebigesEreignisChoice> beliebigesEreignis) {
     this.beliebigesEreignis = beliebigesEreignis;
  }

  public List<SchwangerschaftsstatusBeliebigesEreignisChoice> getBeliebigesEreignis() {
     return this.beliebigesEreignis ;
  }
}
