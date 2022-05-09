package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.601613827+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface PulsfrequenzHerzfrequenzJedesEreignisChoice {
  NullFlavour getFrequenzNullFlavourDefiningCode();

  void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Double getFrequenzMagnitude();

  void setFrequenzMagnitude(Double frequenzMagnitude);

  List<BelastungsgradCluster2> getBelastungsgrad();

  void setBelastungsgrad(List<BelastungsgradCluster2> belastungsgrad);

  String getFrequenzUnits();

  void setFrequenzUnits(String frequenzUnits);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);
}
