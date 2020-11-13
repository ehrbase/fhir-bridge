package org.ehrbase.fhirbridge.ehr.template.blutdruckcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.fhirbridge.ehr.template.blutdruckcomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.template.shareddefinition.Territory;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Template("Blutdruck")
public class BlutdruckComposition {
    @Id
    private VersionUid versionUid;

    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    @Path("/context/participations")
    private List<Participation> participations;

    @Path("/language")
    private Language language;

    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    @Path("/composer")
    private PartyProxy composer;

    @Path("/context/setting|defining_code")
    private SettingDefiningcode settingDefiningcode;

    @Path("/territory")
    private Territory territory;

    @Path("/context/location")
    private String location;

    @Path("/content[openEHR-EHR-OBSERVATION.blood_pressure.v2]")
    private BlutdruckObservation blutdruck;

    @Path("/category|defining_code")
    private CategoryDefiningcode categoryDefiningcode;

    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> erweiterung;

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }

    public VersionUid getVersionUid() {
        return this.versionUid;
    }

    public void setVersionUid(VersionUid versionUid) {
        this.versionUid = versionUid;
    }

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<Participation> getParticipations() {
        return this.participations;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    public PartyIdentified getHealthCareFacility() {
        return this.healthCareFacility;
    }

    public void setComposer(PartyProxy composer) {
        this.composer = composer;
    }

    public PartyProxy getComposer() {
        return this.composer;
    }

    public void setSettingDefiningcode(SettingDefiningcode settingDefiningcode) {
        this.settingDefiningcode = settingDefiningcode;
    }

    public SettingDefiningcode getSettingDefiningcode() {
        return this.settingDefiningcode;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setBlutdruck(BlutdruckObservation blutdruck) {
        this.blutdruck = blutdruck;
    }

    public BlutdruckObservation getBlutdruck() {
        return this.blutdruck;
    }

    public void setCategoryDefiningcode(CategoryDefiningcode categoryDefiningcode) {
        this.categoryDefiningcode = categoryDefiningcode;
    }

    public CategoryDefiningcode getCategoryDefiningcode() {
        return this.categoryDefiningcode;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }
}
