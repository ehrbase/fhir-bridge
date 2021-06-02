package org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition;

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
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition.HerzfrequenzObservation;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-03-09T11:53:58.149789+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Herzfrequenz")
public class HerzfrequenzComposition implements CompositionEntity {
    /**
     * Path: Herzfrequenz/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: Herzfrequenz/context/Erweiterung
     * Description: Ergänzende Angaben zum Registereintrag.
     */
    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> erweiterung;

    /**
     * Path: Herzfrequenz/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: Herzfrequenz/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: Herzfrequenz/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: Herzfrequenz/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: Herzfrequenz/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: Herzfrequenz/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: Herzfrequenz/Herzfrequenz
     * Description: Die Frequenz und zugehörige Attribute für die Puls- oder Herzfrequenz.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.pulse.v2 and name/value='Herzfrequenz']")
    private HerzfrequenzObservation herzfrequenz;

    /**
     * Path: Herzfrequenz/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: Herzfrequenz/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Herzfrequenz/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Herzfrequenz/territory
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

    public HerzfrequenzObservation getHerzfrequenz() {
        return this.herzfrequenz;
    }

    public void setHerzfrequenz(HerzfrequenzObservation herzfrequenz) {
        this.herzfrequenz = herzfrequenz;
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
