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
    date = "2022-04-13T16:38:21.355059612+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface PulsfrequenzHerzfrequenzJedesEreignisChoice {
  NullFlavour getRegelmaessigkeitNullFlavourDefiningCode();

  void setRegelmaessigkeitNullFlavourDefiningCode(
      NullFlavour regelmaessigkeitNullFlavourDefiningCode);

  UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode();

  void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode);

  String getFrequenzUnits();

  void setFrequenzUnits(String frequenzUnits);

  NullFlavour getFrequenzNullFlavourDefiningCode();

  void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode);

  RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode();

  void setRegelmaessigkeitDefiningCode(RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode);

  NullFlavour getUnregelmaessigerTypNullFlavourDefiningCode();

  void setUnregelmaessigerTypNullFlavourDefiningCode(
      NullFlavour unregelmaessigerTypNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Double getFrequenzMagnitude();

  void setFrequenzMagnitude(Double frequenzMagnitude);

  List<Cluster> getAnstrengung();

  void setAnstrengung(List<Cluster> anstrengung);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);
}
