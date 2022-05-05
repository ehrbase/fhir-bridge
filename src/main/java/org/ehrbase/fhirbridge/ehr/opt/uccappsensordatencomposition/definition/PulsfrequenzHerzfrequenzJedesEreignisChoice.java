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
    date = "2022-05-05T11:59:39.273132+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public interface PulsfrequenzHerzfrequenzJedesEreignisChoice {
  NullFlavour getRegelmaessigkeitNullFlavourDefiningCode();

  void setRegelmaessigkeitNullFlavourDefiningCode(
      NullFlavour regelmaessigkeitNullFlavourDefiningCode);

  UnregelmaessigerTypDefiningCode getUnregelmaessigerTypDefiningCode();

  void setUnregelmaessigerTypDefiningCode(
      UnregelmaessigerTypDefiningCode unregelmaessigerTypDefiningCode);

  List<PulsfrequenzHerzfrequenzStoerfaktorenElement> getStoerfaktoren();

  void setStoerfaktoren(List<PulsfrequenzHerzfrequenzStoerfaktorenElement> stoerfaktoren);

  NullFlavour getFrequenzNullFlavourDefiningCode();

  void setFrequenzNullFlavourDefiningCode(NullFlavour frequenzNullFlavourDefiningCode);

  KoerperhaltungDefiningCode getKoerperhaltungDefiningCode();

  void setKoerperhaltungDefiningCode(KoerperhaltungDefiningCode koerperhaltungDefiningCode);

  RegelmaessigkeitDefiningCode getRegelmaessigkeitDefiningCode();

  void setRegelmaessigkeitDefiningCode(RegelmaessigkeitDefiningCode regelmaessigkeitDefiningCode);

  NullFlavour getUnregelmaessigerTypNullFlavourDefiningCode();

  void setUnregelmaessigerTypNullFlavourDefiningCode(
      NullFlavour unregelmaessigerTypNullFlavourDefiningCode);

  NullFlavour getVorhandenseinNullFlavourDefiningCode();

  void setVorhandenseinNullFlavourDefiningCode(NullFlavour vorhandenseinNullFlavourDefiningCode);

  NullFlavour getKlinischeBeschreibungNullFlavourDefiningCode();

  void setKlinischeBeschreibungNullFlavourDefiningCode(
      NullFlavour klinischeBeschreibungNullFlavourDefiningCode);

  VorhandenseinDefiningCode getVorhandenseinDefiningCode();

  void setVorhandenseinDefiningCode(VorhandenseinDefiningCode vorhandenseinDefiningCode);

  List<PulsfrequenzHerzfrequenzKlinischeInterpretationElement> getKlinischeInterpretation();

  void setKlinischeInterpretation(
      List<PulsfrequenzHerzfrequenzKlinischeInterpretationElement> klinischeInterpretation);

  NullFlavour getKoerperhaltungNullFlavourDefiningCode();

  void setKoerperhaltungNullFlavourDefiningCode(NullFlavour koerperhaltungNullFlavourDefiningCode);

  String getKlinischeBeschreibungValue();

  void setKlinischeBeschreibungValue(String klinischeBeschreibungValue);

  String getFrequenzUnits();

  void setFrequenzUnits(String frequenzUnits);

  NullFlavour getKommentarNullFlavourDefiningCode();

  void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Double getFrequenzMagnitude();

  void setFrequenzMagnitude(Double frequenzMagnitude);

  List<PulsfrequenzHerzfrequenzMerkmalElement> getMerkmal();

  void setMerkmal(List<PulsfrequenzHerzfrequenzMerkmalElement> merkmal);

  List<BelastungsgradChoice> getBelastungsgrad();

  void setBelastungsgrad(List<BelastungsgradChoice> belastungsgrad);

  String getKommentarValue();

  void setKommentarValue(String kommentarValue);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);
}
