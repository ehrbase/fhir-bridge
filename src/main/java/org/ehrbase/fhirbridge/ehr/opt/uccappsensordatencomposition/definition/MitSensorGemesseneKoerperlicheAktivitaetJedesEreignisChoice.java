package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Item;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-06-30T13:56:48.488831+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public interface MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice {
  NullFlavour getAnzahlStockwerkeNullFlavourDefiningCode();

  void setAnzahlStockwerkeNullFlavourDefiningCode(
      NullFlavour anzahlStockwerkeNullFlavourDefiningCode);

  List<Item> getGeschwindigkeit();

  void setGeschwindigkeit(List<Item> geschwindigkeit);

  Long getAnzahlStockwerkeMagnitude();

  void setAnzahlStockwerkeMagnitude(Long anzahlStockwerkeMagnitude);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  String getZurueckgelegteDistanzUnits();

  void setZurueckgelegteDistanzUnits(String zurueckgelegteDistanzUnits);

  NullFlavour getZurueckgelegteDistanzNullFlavourDefiningCode();

  void setZurueckgelegteDistanzNullFlavourDefiningCode(
      NullFlavour zurueckgelegteDistanzNullFlavourDefiningCode);

  NullFlavour getAnzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode();

  void setAnzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode(
      NullFlavour anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode);

  Double getRuheenergieMagnitude();

  void setRuheenergieMagnitude(Double ruheenergieMagnitude);

  NullFlavour getRuheenergieNullFlavourDefiningCode();

  void setRuheenergieNullFlavourDefiningCode(NullFlavour ruheenergieNullFlavourDefiningCode);

  String getRuheenergieUnits();

  void setRuheenergieUnits(String ruheenergieUnits);

  List<Item> getPace();

  void setPace(List<Item> pace);

  NullFlavour getAktivitaetNullFlavourDefiningCode();

  void setAktivitaetNullFlavourDefiningCode(NullFlavour aktivitaetNullFlavourDefiningCode);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  List<Item> getHoehe();

  void setHoehe(List<Item> hoehe);

  Long getAnzahlDerZurueckgelegtenSchritteMagnitude();

  void setAnzahlDerZurueckgelegtenSchritteMagnitude(Long anzahlDerZurueckgelegtenSchritteMagnitude);

  Double getZurueckgelegteDistanzMagnitude();

  void setZurueckgelegteDistanzMagnitude(Double zurueckgelegteDistanzMagnitude);

  Double getAktivitaetsenergieMagnitude();

  void setAktivitaetsenergieMagnitude(Double aktivitaetsenergieMagnitude);

  NullFlavour getAktivitaetsenergieNullFlavourDefiningCode();

  void setAktivitaetsenergieNullFlavourDefiningCode(
      NullFlavour aktivitaetsenergieNullFlavourDefiningCode);

  String getAktivitaetValue();

  void setAktivitaetValue(String aktivitaetValue);

  String getAktivitaetsenergieUnits();

  void setAktivitaetsenergieUnits(String aktivitaetsenergieUnits);
}
