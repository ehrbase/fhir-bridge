package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-03-09T11:54:08.533114+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Intensivmedizinisches Monitoring Korpertemperatur")
public class IntensivmedizinischesMonitoringKorpertemperaturComposition implements CompositionEntity {
    /**
     * Path: Bericht/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: Bericht/context/Bericht Name
     * Description: Identifizierungsmerkmal des Berichts.
     */
    @Path("/context/other_context[at0001]/items[at0002 and name/value='Bericht Name']/value|value")
    private String berichtNameValue;

    /**
     * Path: Bericht/context/Tree/Bericht Name/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0002 and name/value='Bericht Name']/null_flavour|defining_code")
    private NullFlavour berichtNameNullFlavourDefiningCode;

    /**
     * Path: Bericht/context/Status
     * Description: Der Status des gesamten Berichts. Hinweis: Dies ist nicht der Status einer Berichtskomponente.
     */
    @Path("/context/other_context[at0001]/items[at0005]/value|value")
    private String statusValue;

    /**
     * Path: Bericht/context/Tree/Status/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
    private NullFlavour statusNullFlavourDefiningCode;

    /**
     * Path: Bericht/context/Fallidentifikation
     * Description: Zur Erfassung von Details zur Identifikation eines Falls im Gesundheitswesen.
     */
    @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
    private FallidentifikationCluster fallidentifikation;

    /**
     * Path: Bericht/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: Bericht/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: Bericht/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: Bericht/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: Bericht/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: Bericht/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: Bericht/Körpertemperatur
     * Description: Eine Messung der Körpertemperatur an einer bestimmten Stelle als Surrogat für den gesamten Körper der Person.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.body_temperature.v2]")
    private List<KoerpertemperaturObservation> koerpertemperatur;

    /**
     * Path: Bericht/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: Bericht/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/territory
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

    public String getBerichtNameValue() {
        return this.berichtNameValue;
    }

    public void setBerichtNameValue(String berichtNameValue) {
        this.berichtNameValue = berichtNameValue;
    }

    public NullFlavour getBerichtNameNullFlavourDefiningCode() {
        return this.berichtNameNullFlavourDefiningCode;
    }

    public void setBerichtNameNullFlavourDefiningCode(
            NullFlavour berichtNameNullFlavourDefiningCode) {
        this.berichtNameNullFlavourDefiningCode = berichtNameNullFlavourDefiningCode;
    }

    public String getStatusValue() {
        return this.statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public NullFlavour getStatusNullFlavourDefiningCode() {
        return this.statusNullFlavourDefiningCode;
    }

    public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
        this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
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

    public List<KoerpertemperaturObservation> getKoerpertemperatur() {
        return this.koerpertemperatur;
    }

    public void setKoerpertemperatur(List<KoerpertemperaturObservation> koerpertemperatur) {
        this.koerpertemperatur = koerpertemperatur;
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
