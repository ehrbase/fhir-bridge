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
    date = "2022-05-10T17:43:37.152606657+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface BlutdruckBeliebigesEreignisChoice {
  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  NullFlavour getDiastolischNullFlavourDefiningCode();

  void setDiastolischNullFlavourDefiningCode(NullFlavour diastolischNullFlavourDefiningCode);

  Cluster getAnstrengung();

  void setAnstrengung(Cluster anstrengung);

  Double getSystolischMagnitude();

  void setSystolischMagnitude(Double systolischMagnitude);

  NullFlavour getSystolischNullFlavourDefiningCode();

  void setSystolischNullFlavourDefiningCode(NullFlavour systolischNullFlavourDefiningCode);

  String getDiastolischUnits();

  void setDiastolischUnits(String diastolischUnits);

  String getSystolischUnits();

  void setSystolischUnits(String systolischUnits);

  Double getDiastolischMagnitude();

  void setDiastolischMagnitude(Double diastolischMagnitude);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);
}
