package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-06-30T13:56:48.445741+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public interface PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice {
  NullFlavour getFrequenzNullFlavourDefiningCode();

  void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode);

  RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode();

  void setRegelmaessigkeitDefiningCode(RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode);

  UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode();

  void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<BelastungsgradChoice> getBelastungsgrad();

  void setBelastungsgrad(List<BelastungsgradChoice> belastungsgrad);

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
