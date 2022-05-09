package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:29.027327092+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface BloodPressureAnyEventChoice {
  String getDiastolicUnits();

  void setDiastolicUnits(String diastolicUnits);

  Cluster getExertion();

  void setExertion(Cluster exertion);

  Double getDiastolicMagnitude();

  void setDiastolicMagnitude(Double diastolicMagnitude);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  NullFlavour getDiastolicNullFlavourDefiningCode();

  void setDiastolicNullFlavourDefiningCode(NullFlavour diastolicNullFlavourDefiningCode);

  String getSystolicUnits();

  void setSystolicUnits(String systolicUnits);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  Double getSystolicMagnitude();

  void setSystolicMagnitude(Double systolicMagnitude);

  NullFlavour getSystolicNullFlavourDefiningCode();

  void setSystolicNullFlavourDefiningCode(NullFlavour systolicNullFlavourDefiningCode);
}
