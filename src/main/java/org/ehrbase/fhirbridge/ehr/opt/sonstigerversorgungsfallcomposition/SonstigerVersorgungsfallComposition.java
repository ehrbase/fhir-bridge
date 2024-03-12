package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
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
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.SonstigerVersorgungsfallFallklasseChoice;
import org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition.VersorgungsaufenthaltAdminEntry;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.fall.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-26T12:59:02.466556616+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@Template("Sonstiger Versorgungsfall")
public class SonstigerVersorgungsfallComposition implements CompositionEntity {
  /**
   * Path: Sonstiger Versorgungsfall/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Falltyp
   * Description: Charaktierisierung des Falls, bspw. als Einrichtungskontakt, Abteilungskontakt, Versorgungsstellenkontakt.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value")
  private DvCodedText falltyp;

  /**
   * Path: Sonstiger Versorgungsfall/context/Baum/Falltyp/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
  private NullFlavour falltypNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Baum/Fallklasse/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour fallklasseNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Fallstatus
   * Description: Status des Falls
   * Comment: Status des Falls
   */
  @Path("/context/other_context[at0001]/items[at0010]/value")
  private DvCodedText fallstatus;

  /**
   * Path: Sonstiger Versorgungsfall/context/Baum/Fallstatus/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0010]/null_flavour|defining_code")
  private NullFlavour fallstatusNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Fall-Kennung
   * Description: Eindeutige Identifikation des Falls, z.B. Fallnummer.
   */
  @Path("/context/other_context[at0001]/items[at0003 and name/value='Fall-Kennung']/value|value")
  private String fallKennungValue;

  /**
   * Path: Sonstiger Versorgungsfall/context/Baum/Fall-Kennung/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0003 and name/value='Fall-Kennung']/null_flavour|defining_code")
  private NullFlavour fallKennungNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Übergeordneter Fall
   * Description: Ein anderer Fall, von dem dieser Fall ein Teil ist (administrativ oder zeitlich).
   * Comment: Ein anderer Fall, von dem dieser Fall ein Teil ist (administrativ oder zeitlich).
   */
  @Path("/context/other_context[at0001]/items[at0011]/value|value")
  private String uebergeordneterFallValue;

  /**
   * Path: Sonstiger Versorgungsfall/context/Baum/Übergeordneter Fall/null_flavour
   */
  @Path("/context/other_context[at0001]/items[at0011]/null_flavour|defining_code")
  private NullFlavour uebergeordneterFallNullFlavourDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/context/Erweiterung
   * Description: Ergänzende Angaben zum Fall
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> erweiterung;

  /**
   * Path: Sonstiger Versorgungsfall/context/Fachliche Organisationseinheit
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Fachliche Organisationseinheit']")
  private List<FachlicheOrganisationseinheitCluster> fachlicheOrganisationseinheit;

  /**
   * Path: Sonstiger Versorgungsfall/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: Sonstiger Versorgungsfall/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: Sonstiger Versorgungsfall/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: Sonstiger Versorgungsfall/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: Sonstiger Versorgungsfall/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: Sonstiger Versorgungsfall/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Sonstiger Versorgungsfall/Versorgungsaufenthalt
   * Description: Zur Erfassung der administrativen Aufenthaltsdaten eines Patienten.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0 and name/value='Versorgungsaufenthalt']")
  private VersorgungsaufenthaltAdminEntry versorgungsaufenthalt;

  /**
   * Path: Sonstiger Versorgungsfall/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: Sonstiger Versorgungsfall/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Sonstiger Versorgungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Sonstiger Versorgungsfall/territory
   */
  @Path("/territory")
  private Territory territory;

  /**
   * Path: Sonstiger Versorgungsfall/context/Fallklasse
   * Description: Nähere Beschreibung des Falls als Fallklasse, z.B. ambulanter Besuch,  stationärer, prä- oder nachstationärer Aufenthalt.
   */
  @Path("/context/other_context[at0001]/items[at0004]/value")
  @Choice
  private SonstigerVersorgungsfallFallklasseChoice fallklasse;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setFalltyp(DvCodedText falltyp) {
     this.falltyp = falltyp;
  }

  public DvCodedText getFalltyp() {
     return this.falltyp ;
  }

  public void setFalltypNullFlavourDefiningCode(NullFlavour falltypNullFlavourDefiningCode) {
     this.falltypNullFlavourDefiningCode = falltypNullFlavourDefiningCode;
  }

  public NullFlavour getFalltypNullFlavourDefiningCode() {
     return this.falltypNullFlavourDefiningCode ;
  }

  public void setFallklasseNullFlavourDefiningCode(NullFlavour fallklasseNullFlavourDefiningCode) {
     this.fallklasseNullFlavourDefiningCode = fallklasseNullFlavourDefiningCode;
  }

  public NullFlavour getFallklasseNullFlavourDefiningCode() {
     return this.fallklasseNullFlavourDefiningCode ;
  }

  public void setFallstatus(DvCodedText fallstatus) {
     this.fallstatus = fallstatus;
  }

  public DvCodedText getFallstatus() {
     return this.fallstatus ;
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

  public void setVersorgungsaufenthalt(VersorgungsaufenthaltAdminEntry versorgungsaufenthalt) {
     this.versorgungsaufenthalt = versorgungsaufenthalt;
  }

  public VersorgungsaufenthaltAdminEntry getVersorgungsaufenthalt() {
     return this.versorgungsaufenthalt ;
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

  public void setFallklasse(SonstigerVersorgungsfallFallklasseChoice fallklasse) {
     this.fallklasse = fallklasse;
  }

  public SonstigerVersorgungsfallFallklasseChoice getFallklasse() {
     return this.fallklasse ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
