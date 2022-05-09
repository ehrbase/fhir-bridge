package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:12:33.405098126+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice {
  List<Cluster> getAnstrengung();

  void setAnstrengung(List<Cluster> anstrengung);

  NullFlavour getFrequenzNullFlavourDefiningCode();

  void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode);

  RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode();

  void setRegelmaessigkeitDefiningCode(RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode);

  UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode();

  void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  NullFlavour getUnregelmaessigerTypNullFlavourDefiningCode();

  void setUnregelmaessigerTypNullFlavourDefiningCode(
      NullFlavour unregelmaessigerTypNullFlavourDefiningCode);

  NullFlavour getRegelmaessigkeitNullFlavourDefiningCode();

  void setRegelmaessigkeitNullFlavourDefiningCode(
      NullFlavour regelmaessigkeitNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Double getFrequenzMagnitude();

  void setFrequenzMagnitude(Double frequenzMagnitude);

  String getFrequenzUnits();

  void setFrequenzUnits(String frequenzUnits);
}
