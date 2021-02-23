package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-18T10:30:38.689937+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public interface SchwangerschaftsstatusBeliebigesEreignisChoice {
  NullFlavour getStatusNullFlavourDefiningCode();

  void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  StatusDefiningCode getStatusDefiningCode();

  void setStatusDefiningCode(StatusDefiningCode statusDefiningCode);
}
