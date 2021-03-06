package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
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
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.KategorieDefiningCode;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-04-30T13:16:38.120261+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("GECCO_Medikation")
public class GECCOMedikationComposition implements CompositionEntity {
    /**
     * Path: Medikation/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: Medikation/context/Erweiterung
     * Description: Ergänzende Angaben zum Registereintrag.
     */
    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> erweiterung;

    /**
     * Path: Medikation/context/Kategorie
     * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/context/other_context[at0001]/items[at0005]/value|defining_code")
    private KategorieDefiningCode kategorieDefiningCode;

    /**
     * Path: Medikation/context/Baum/Kategorie/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
    private NullFlavour kategorieNullFlavourDefiningCode;

    /**
     * Path: Medikation/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: Medikation/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: Medikation/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: Medikation/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: Medikation/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: Medikation/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: Medikation/COVID-19 Therapie
     * Description: Eine Snapshot-Ansicht über die Anwendung eines bestimmten Medikaments.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.medication_statement.v0 and name/value='COVID-19 Therapie']")
    private Covid19TherapieObservation covid19Therapie;

    /**
     * Path: Medikation/ACE-Hemmer
     * Description: Eine Snapshot-Ansicht über die Anwendung eines bestimmten Medikaments.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.medication_statement.v0 and name/value='ACE-Hemmer']")
    private AceHemmerObservation aceHemmer;

    /**
     * Path: Medikation/Immunglobuline
     * Description: Eine Snapshot-Ansicht über die Anwendung eines bestimmten Medikaments.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.medication_statement.v0 and name/value='Immunglobuline']")
    private ImmunglobulineObservation immunglobuline;

    /**
     * Path: Medikation/Antikoagulanzien
     * Description: Eine Snapshot-Ansicht über die Anwendung eines bestimmten Medikaments.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.medication_statement.v0 and name/value='Antikoagulanzien']")
    private AntikoagulanzienObservation antikoagulanzien;

    /**
     * Path: Medikation/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: Medikation/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Medikation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Medikation/territory
     */
    @Path("/territory")
    private Territory territory;

    @Id
    private VersionUid versionUid;

    public Category getCategoryDefiningCode() {
        return this.categoryDefiningCode;
    }

    public void setCategoryDefiningCode(Category categoryDefiningCode) {
        this.categoryDefiningCode = categoryDefiningCode;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public KategorieDefiningCode getKategorieDefiningCode() {
        return this.kategorieDefiningCode;
    }

    public void setKategorieDefiningCode(KategorieDefiningCode kategorieDefiningCode) {
        this.kategorieDefiningCode = kategorieDefiningCode;
    }

    public NullFlavour getKategorieNullFlavourDefiningCode() {
        return this.kategorieNullFlavourDefiningCode;
    }

    public void setKategorieNullFlavourDefiningCode(NullFlavour kategorieNullFlavourDefiningCode) {
        this.kategorieNullFlavourDefiningCode = kategorieNullFlavourDefiningCode;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public List<Participation> getParticipations() {
        return this.participations;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public PartyIdentified getHealthCareFacility() {
        return this.healthCareFacility;
    }

    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    public Setting getSettingDefiningCode() {
        return this.settingDefiningCode;
    }

    public void setSettingDefiningCode(Setting settingDefiningCode) {
        this.settingDefiningCode = settingDefiningCode;
    }

    public Covid19TherapieObservation getCovid19Therapie() {
        return this.covid19Therapie;
    }

    public void setCovid19Therapie(Covid19TherapieObservation covid19Therapie) {
        this.covid19Therapie = covid19Therapie;
    }

    public AceHemmerObservation getAceHemmer() {
        return this.aceHemmer;
    }

    public void setAceHemmer(AceHemmerObservation aceHemmer) {
        this.aceHemmer = aceHemmer;
    }

    public ImmunglobulineObservation getImmunglobuline() {
        return this.immunglobuline;
    }

    public void setImmunglobuline(ImmunglobulineObservation immunglobuline) {
        this.immunglobuline = immunglobuline;
    }

    public AntikoagulanzienObservation getAntikoagulanzien() {
        return this.antikoagulanzien;
    }

    public void setAntikoagulanzien(AntikoagulanzienObservation antikoagulanzien) {
        this.antikoagulanzien = antikoagulanzien;
    }

    public PartyProxy getComposer() {
        return this.composer;
    }

    public void setComposer(PartyProxy composer) {
        this.composer = composer;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public VersionUid getVersionUid() {
        return this.versionUid;
    }

    public void setVersionUid(VersionUid versionUid) {
        this.versionUid = versionUid;
    }
}
