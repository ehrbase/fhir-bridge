package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.637392+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public interface KoerpertemperaturBeliebigesEreignisChoice {
  String getTemperaturUnits();

  void setTemperaturUnits(String temperaturUnits);

  List<Cluster> getUmweltbedingungen();

  void setUmweltbedingungen(List<Cluster> umweltbedingungen);

  NullFlavour getBeschreibungDerWaermebelastungNullFlavourDefiningCode();

  void setBeschreibungDerWaermebelastungNullFlavourDefiningCode(
      NullFlavour beschreibungDerWaermebelastungNullFlavourDefiningCode);

  Double getTemperaturMagnitude();

  void setTemperaturMagnitude(Double temperaturMagnitude);

  NullFlavour getKommentarNullFlavourDefiningCode();

  void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  Cluster getBetaetigung();

  void setBetaetigung(Cluster betaetigung);

  KoerpertemperaturKoerperexpositionChoice getKoerperexposition();

  void setKoerperexposition(KoerpertemperaturKoerperexpositionChoice koerperexposition);

  NullFlavour getKoerperexpositionNullFlavourDefiningCode();

  void setKoerperexpositionNullFlavourDefiningCode(
      NullFlavour koerperexpositionNullFlavourDefiningCode);

  Long getAktuellerTagDesMenstruationszyklusMagnitude();

  void setAktuellerTagDesMenstruationszyklusMagnitude(
      Long aktuellerTagDesMenstruationszyklusMagnitude);

  NullFlavour getTemperaturNullFlavourDefiningCode();

  void setTemperaturNullFlavourDefiningCode(NullFlavour temperaturNullFlavourDefiningCode);

  NullFlavour getAktuellerTagDesMenstruationszyklusNullFlavourDefiningCode();

  void setAktuellerTagDesMenstruationszyklusNullFlavourDefiningCode(
      NullFlavour aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode);

  String getKommentarValue();

  void setKommentarValue(String kommentarValue);

  String getBeschreibungDerWaermebelastungValue();

  void setBeschreibungDerWaermebelastungValue(String beschreibungDerWaermebelastungValue);
}
