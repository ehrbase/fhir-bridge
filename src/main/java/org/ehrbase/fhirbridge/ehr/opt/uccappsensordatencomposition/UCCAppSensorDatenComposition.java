package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzObservation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.self_monitoring.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:12:33.305993800+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@Template("UCC_App_Sensor_Daten")
public class UCCAppSensorDatenComposition implements CompositionEntity {
  /**
   * Path: Selbstüberwachung/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Selbstüberwachung/context/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Selbstüberwachung/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Selbstüberwachung/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Selbstüberwachung/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Selbstüberwachung/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Selbstüberwachung/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz
   * Description: Die Frequenz und zugehörige Attribute für die Puls- oder Herzfrequenz.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.pulse.v2]")
  private PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenz;

  /**
   * Path: Selbstüberwachung/Mit Sensor gemessene körperliche Aktivität
   * Description: Zur Darstellung von Aktivitätsparametern, gemessen mit einem tragbaren Sensor in einem definierten Zeitraum.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0]")
  private MitSensorGemesseneKoerperlicheAktivitaetObservation mitSensorGemesseneKoerperlicheAktivitaet;

  /**
   * Path: Selbstüberwachung/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Selbstüberwachung/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setPulsfrequenzHerzfrequenz(
      PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenz) {
     this.pulsfrequenzHerzfrequenz = pulsfrequenzHerzfrequenz;
  }

  public PulsfrequenzHerzfrequenzObservation getPulsfrequenzHerzfrequenz() {
     return this.pulsfrequenzHerzfrequenz ;
  }

  public void setMitSensorGemesseneKoerperlicheAktivitaet(
      MitSensorGemesseneKoerperlicheAktivitaetObservation mitSensorGemesseneKoerperlicheAktivitaet) {
     this.mitSensorGemesseneKoerperlicheAktivitaet = mitSensorGemesseneKoerperlicheAktivitaet;
  }

  public MitSensorGemesseneKoerperlicheAktivitaetObservation getMitSensorGemesseneKoerperlicheAktivitaet(
      ) {
     return this.mitSensorGemesseneKoerperlicheAktivitaet ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
