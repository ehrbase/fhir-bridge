package org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition.ProzedurAction;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-03-09T11:55:59.431635+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Prozedur")
public class ProzedurComposition implements CompositionEntity {
    /**
     * Path: Prozedur/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: Prozedur/context/Bericht ID
     * Description: Identifizierungsmerkmal des Berichts.
     */
    @Path("/context/other_context[at0001]/items[at0002]/value|value")
    private String berichtIdValue;

    /**
     * Path: Prozedur/context/Tree/Bericht ID/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0002]/null_flavour|defining_code")
    private NullFlavour berichtIdNullFlavourDefiningCode;

    /**
     * Path: Prozedur/context/Fallidentifikation
     * Description: Zur Erfassung von Details zur Identifikation eines Falls im Gesundheitswesen.
     */
    @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
    private FallidentifikationCluster fallidentifikation;

    /**
     * Path: Prozedur/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: Prozedur/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: Prozedur/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: Prozedur/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: Prozedur/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: Prozedur/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: Prozedur/Prozedur
     * Description: Eine klinische Aktivität, die zur Früherkennung, Untersuchung, Diagnose, Heilung, Therapie, Bewertung oder in Hinsicht auf palliative Maßnahmen durchgeführt wird.
     */
    @Path("/content[openEHR-EHR-ACTION.procedure.v1]")
    private ProzedurAction prozedur;

    /**
     * Path: Prozedur/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: Prozedur/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Prozedur/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Prozedur/territory
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

    public String getBerichtIdValue() {
        return this.berichtIdValue;
    }

    public void setBerichtIdValue(String berichtIdValue) {
        this.berichtIdValue = berichtIdValue;
    }

    public NullFlavour getBerichtIdNullFlavourDefiningCode() {
        return this.berichtIdNullFlavourDefiningCode;
    }

    public void setBerichtIdNullFlavourDefiningCode(NullFlavour berichtIdNullFlavourDefiningCode) {
        this.berichtIdNullFlavourDefiningCode = berichtIdNullFlavourDefiningCode;
    }

    public FallidentifikationCluster getFallidentifikation() {
        return this.fallidentifikation;
    }

    public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
        this.fallidentifikation = fallidentifikation;
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

    public ProzedurAction getProzedur() {
        return this.prozedur;
    }

    public void setProzedur(ProzedurAction prozedur) {
        this.prozedur = prozedur;
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
