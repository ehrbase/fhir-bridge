package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:54:08.628200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("POINT_EVENT")
public class KoerpertemperaturBeliebigesEreignisPointEvent implements PointEventEntity, KoerpertemperaturBeliebigesEreignisChoice {
  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Temperatur
   * Description: Die gemessene Körpertemperatur (als Surrogat für den gesamten Körper).
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double temperaturMagnitude;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Temperatur
   * Description: Die gemessene Körpertemperatur (als Surrogat für den gesamten Körper).
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String temperaturUnits;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Single/Temperatur/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour temperaturNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Kommentar
   * Description: Zusätzliche Informationen über die Körpertemperatur, die nicht in anderen Bereichen dargestellt wurden.
   */
  @Path("/data[at0001]/items[at0063]/value|value")
  private String kommentarValue;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Single/Kommentar/null_flavour
   */
  @Path("/data[at0001]/items[at0063]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Status/Körperexposition/null_flavour
   */
  @Path("/state[at0029]/items[at0030]/null_flavour|defining_code")
  private NullFlavour koerperexpositionNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Beschreibung der Wärmebelastung
   * Description: Beschreibung von Bedingungen, denen die Person ausgesetzt ist, welche die gemessene Körpertemperatur beeinflussen könnten.
   */
  @Path("/state[at0029]/items[at0041]/value|value")
  private String beschreibungDerWaermebelastungValue;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Status/Beschreibung der Wärmebelastung/null_flavour
   */
  @Path("/state[at0029]/items[at0041]/null_flavour|defining_code")
  private NullFlavour beschreibungDerWaermebelastungNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Aktueller Tag des Menstruationszyklus
   * Description: Anzahl der Tage seit Beginn der letzten normalen Menstruation.
   */
  @Path("/state[at0029]/items[at0065]/value|magnitude")
  private Long aktuellerTagDesMenstruationszyklusMagnitude;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Status/Aktueller Tag des Menstruationszyklus/null_flavour
   */
  @Path("/state[at0029]/items[at0065]/null_flavour|defining_code")
  private NullFlavour aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Umweltbedingungen
   * Description: Details über die Umweltbedingungen zum Zeitpunkt der Temperaturmessung
   */
  @Path("/state[at0029]/items[at0056]")
  private List<Cluster> umweltbedingungen;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Betätigung
   * Description: Details über die Betätigung der Person zum Zeitpunkt der Messung der Temperatur.
   */
  @Path("/state[at0029]/items[at0057]")
  private Cluster betaetigung;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Bericht/Körpertemperatur/Beliebiges Ereignis/Körperexposition
   * Description: Die thermale Situation der Person, deren Temperatur gemessen wird.
   */
  @Path("/state[at0029]/items[at0030]/value")
  @Choice
  private KoerpertemperaturKoerperexpositionChoice koerperexposition;

  public void setTemperaturMagnitude(Double temperaturMagnitude) {
     this.temperaturMagnitude = temperaturMagnitude;
  }

  public Double getTemperaturMagnitude() {
     return this.temperaturMagnitude ;
  }

  public void setTemperaturUnits(String temperaturUnits) {
     this.temperaturUnits = temperaturUnits;
  }

  public String getTemperaturUnits() {
     return this.temperaturUnits ;
  }

  public void setTemperaturNullFlavourDefiningCode(NullFlavour temperaturNullFlavourDefiningCode) {
     this.temperaturNullFlavourDefiningCode = temperaturNullFlavourDefiningCode;
  }

  public NullFlavour getTemperaturNullFlavourDefiningCode() {
     return this.temperaturNullFlavourDefiningCode ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setKoerperexpositionNullFlavourDefiningCode(
      NullFlavour koerperexpositionNullFlavourDefiningCode) {
     this.koerperexpositionNullFlavourDefiningCode = koerperexpositionNullFlavourDefiningCode;
  }

  public NullFlavour getKoerperexpositionNullFlavourDefiningCode() {
     return this.koerperexpositionNullFlavourDefiningCode ;
  }

  public void setBeschreibungDerWaermebelastungValue(String beschreibungDerWaermebelastungValue) {
     this.beschreibungDerWaermebelastungValue = beschreibungDerWaermebelastungValue;
  }

  public String getBeschreibungDerWaermebelastungValue() {
     return this.beschreibungDerWaermebelastungValue ;
  }

  public void setBeschreibungDerWaermebelastungNullFlavourDefiningCode(
      NullFlavour beschreibungDerWaermebelastungNullFlavourDefiningCode) {
     this.beschreibungDerWaermebelastungNullFlavourDefiningCode = beschreibungDerWaermebelastungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungDerWaermebelastungNullFlavourDefiningCode() {
     return this.beschreibungDerWaermebelastungNullFlavourDefiningCode ;
  }

  public void setAktuellerTagDesMenstruationszyklusMagnitude(
      Long aktuellerTagDesMenstruationszyklusMagnitude) {
     this.aktuellerTagDesMenstruationszyklusMagnitude = aktuellerTagDesMenstruationszyklusMagnitude;
  }

  public Long getAktuellerTagDesMenstruationszyklusMagnitude() {
     return this.aktuellerTagDesMenstruationszyklusMagnitude ;
  }

  public void setAktuellerTagDesMenstruationszyklusNullFlavourDefiningCode(
      NullFlavour aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode) {
     this.aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode = aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode;
  }

  public NullFlavour getAktuellerTagDesMenstruationszyklusNullFlavourDefiningCode() {
     return this.aktuellerTagDesMenstruationszyklusNullFlavourDefiningCode ;
  }

  public void setUmweltbedingungen(List<Cluster> umweltbedingungen) {
     this.umweltbedingungen = umweltbedingungen;
  }

  public List<Cluster> getUmweltbedingungen() {
     return this.umweltbedingungen ;
  }

  public void setBetaetigung(Cluster betaetigung) {
     this.betaetigung = betaetigung;
  }

  public Cluster getBetaetigung() {
     return this.betaetigung ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setKoerperexposition(KoerpertemperaturKoerperexpositionChoice koerperexposition) {
     this.koerperexposition = koerperexposition;
  }

  public KoerpertemperaturKoerperexpositionChoice getKoerperexposition() {
     return this.koerperexposition ;
  }
}
