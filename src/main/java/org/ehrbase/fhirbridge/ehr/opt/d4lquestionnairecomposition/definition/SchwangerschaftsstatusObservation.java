package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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

@Entity
@Archetype("openEHR-EHR-OBSERVATION.pregnancy_status.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.864893+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class SchwangerschaftsstatusObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/Status
   * Description: Liegt eine Schwangerschaft vor?
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/History/Beliebiges Ereignis/Tree/Status/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

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

  public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
     this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode getStatusDefiningCode() {
     return this.statusDefiningCode ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

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
}
