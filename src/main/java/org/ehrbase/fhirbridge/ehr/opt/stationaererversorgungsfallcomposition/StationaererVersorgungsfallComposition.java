package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition;

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
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.FallstatusDefiningCode;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.fall.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:38.159537500+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Stationärer Versorgungsfall")
public class StationaererVersorgungsfallComposition implements CompositionEntity {
  /**
   * Path: Stationärer Versorgungsfall/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Falltyp
   * Description: Charaktierisierung des Falls, bspw. als Einrichtungskontakt, Abteilungskontakt, Versorgungsstellenkontakt.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String falltypValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Baum/Falltyp/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour falltypNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Fallklasse
   * Description: Nähere Beschreibung des Falls als Fallklasse, z.B. ambulanter Besuch,  stationärer, prä- oder nachstationärer Aufenthalt.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value|value")
  private String fallklasseValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Baum/Fallklasse/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour fallklasseNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Fallstatus
   * Description: Status des Falls
   * Comment: Status des Falls
   */
  @Path("/context/other_context[at0001]/items[at0010]/value|defining_code")
  private FallstatusDefiningCode fallstatusDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Baum/Fallstatus/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0010]/null_flavour|defining_code")
  private NullFlavour fallstatusNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Fall-Kennung
   * Description: Eindeutige Identifikation des Falls, z.B. Fallnummer.
   */
  @Path("/context/other_context[at0001]/items[at0003 and name/value='Fall-Kennung']/value|value")
  private String fallKennungValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Baum/Fall-Kennung/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0003 and name/value='Fall-Kennung']/null_flavour|defining_code")
  private NullFlavour fallKennungNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Übergeordneter Fall
   * Description: Ein anderer Fall, von dem dieser Fall ein Teil ist (administrativ oder zeitlich).
   * Comment: Ein anderer Fall, von dem dieser Fall ein Teil ist (administrativ oder zeitlich).
   */
  @Path("/context/other_context[at0001]/items[at0011]/value|value")
  private String uebergeordneterFallValue;

  /**
   * Path: Stationärer Versorgungsfall/context/Baum/Übergeordneter Fall/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0011]/null_flavour|defining_code")
  private NullFlavour uebergeordneterFallNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/context/Erweiterung
   * Description: Ergänzende Angaben zum Fall
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: Stationärer Versorgungsfall/context/Fachliche Organisationseinheit
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Fachliche Organisationseinheit']")
  private List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheit;

  /**
   * Path: Stationärer Versorgungsfall/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Stationärer Versorgungsfall/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Stationärer Versorgungsfall/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Stationärer Versorgungsfall/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Stationärer Versorgungsfall/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Stationärer Versorgungsfall/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten
   * Description: Wird nur für aufgenommene Patienten verwendet. Es signalisiert den Beginn des Aufenthalts eines Patienten in einer Gesundheitseinrichtung.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.admission.v0 and name/value='Aufnahmedaten']")
  private AufnahmedatenAdminEntry aufnahmedaten;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten
   * Description: Wird nur für entlassene Patienten verwendet.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0]")
  private EntlassungsdatenAdminEntry entlassungsdaten;

  /**
   * Path: Stationärer Versorgungsfall/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Stationärer Versorgungsfall/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Stationärer Versorgungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Stationärer Versorgungsfall/territory
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

  public void setFalltypValue(String falltypValue) {
     this.falltypValue = falltypValue;
  }

  public String getFalltypValue() {
     return this.falltypValue ;
  }

  public void setFalltypNullFlavourDefiningCode(NullFlavour falltypNullFlavourDefiningCode) {
     this.falltypNullFlavourDefiningCode = falltypNullFlavourDefiningCode;
  }

  public NullFlavour getFalltypNullFlavourDefiningCode() {
     return this.falltypNullFlavourDefiningCode ;
  }

  public void setFallklasseValue(String fallklasseValue) {
     this.fallklasseValue = fallklasseValue;
  }

  public String getFallklasseValue() {
     return this.fallklasseValue ;
  }

  public void setFallklasseNullFlavourDefiningCode(NullFlavour fallklasseNullFlavourDefiningCode) {
     this.fallklasseNullFlavourDefiningCode = fallklasseNullFlavourDefiningCode;
  }

  public NullFlavour getFallklasseNullFlavourDefiningCode() {
     return this.fallklasseNullFlavourDefiningCode ;
  }

  public void setFallstatusDefiningCode(FallstatusDefiningCode fallstatusDefiningCode) {
     this.fallstatusDefiningCode = fallstatusDefiningCode;
  }

  public FallstatusDefiningCode getFallstatusDefiningCode() {
     return this.fallstatusDefiningCode ;
  }

  public void setFallstatusNullFlavourDefiningCode(NullFlavour fallstatusNullFlavourDefiningCode) {
     this.fallstatusNullFlavourDefiningCode = fallstatusNullFlavourDefiningCode;
  }

  public NullFlavour getFallstatusNullFlavourDefiningCode() {
     return this.fallstatusNullFlavourDefiningCode ;
  }

  public void setFallKennungValue(String fallKennungValue) {
     this.fallKennungValue = fallKennungValue;
  }

  public String getFallKennungValue() {
     return this.fallKennungValue ;
  }

  public void setFallKennungNullFlavourDefiningCode(
      NullFlavour fallKennungNullFlavourDefiningCode) {
     this.fallKennungNullFlavourDefiningCode = fallKennungNullFlavourDefiningCode;
  }

  public NullFlavour getFallKennungNullFlavourDefiningCode() {
     return this.fallKennungNullFlavourDefiningCode ;
  }

  public void setUebergeordneterFallValue(String uebergeordneterFallValue) {
     this.uebergeordneterFallValue = uebergeordneterFallValue;
  }

  public String getUebergeordneterFallValue() {
     return this.uebergeordneterFallValue ;
  }

  public void setUebergeordneterFallNullFlavourDefiningCode(
      NullFlavour uebergeordneterFallNullFlavourDefiningCode) {
     this.uebergeordneterFallNullFlavourDefiningCode = uebergeordneterFallNullFlavourDefiningCode;
  }

  public NullFlavour getUebergeordneterFallNullFlavourDefiningCode() {
     return this.uebergeordneterFallNullFlavourDefiningCode ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setFachlicheOrganisationseinheit(
      List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheit) {
     this.fachlicheOrganisationseinheit = fachlicheOrganisationseinheit;
  }

  public List<FachlicheOrganisationseinheitCluster> getFachlicheOrganisationseinheit() {
     return this.fachlicheOrganisationseinheit ;
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

  public void setAufnahmedaten(AufnahmedatenAdminEntry aufnahmedaten) {
     this.aufnahmedaten = aufnahmedaten;
  }

  public AufnahmedatenAdminEntry getAufnahmedaten() {
     return this.aufnahmedaten ;
  }

  public void setEntlassungsdaten(EntlassungsdatenAdminEntry entlassungsdaten) {
     this.entlassungsdaten = entlassungsdaten;
  }

  public EntlassungsdatenAdminEntry getEntlassungsdaten() {
     return this.entlassungsdaten ;
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
