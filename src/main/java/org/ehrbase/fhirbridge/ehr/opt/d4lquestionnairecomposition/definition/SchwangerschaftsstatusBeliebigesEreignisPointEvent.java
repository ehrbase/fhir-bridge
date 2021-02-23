package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-18T10:30:38.687283+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@OptionFor("POINT_EVENT")
public class SchwangerschaftsstatusBeliebigesEreignisPointEvent implements PointEventEntity, SchwangerschaftsstatusBeliebigesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/Status
   * Description: Liegt eine Schwangerschaft vor?
   */
  @Path("/data[at0003]/items[at0011]/value|defining_code")
  private StatusDefiningCode statusDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/Tree/Status/null_flavour
   */
  @Path("/data[at0003]/items[at0011]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Schwangerschaftsstatus/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}
