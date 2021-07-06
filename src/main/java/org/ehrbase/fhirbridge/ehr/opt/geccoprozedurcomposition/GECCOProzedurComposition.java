package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeccoProzedurKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NichtDurchgefuehrteProzedurEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.UnbekannteProzedurEvaluation;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-03-01T12:17:23.938964+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
@Template("GECCO_Prozedur")
public class GECCOProzedurComposition implements CompositionEntity {
    /**
     * Path: GECCO_Prozedur/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: GECCO_Prozedur/context/Erweiterung
     * Description: Ergänzende Angaben zum Registereintrag.
     */
    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> erweiterung;

    /**
     * Path: GECCO_Prozedur/context/Kategorie
     * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/context/other_context[at0001]/items[at0005]")
    private List<GeccoProzedurKategorieElement> kategorie;

    /**
     * Path: GECCO_Prozedur/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: GECCO_Prozedur/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: GECCO_Prozedur/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: GECCO_Prozedur/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: GECCO_Prozedur/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: GECCO_Prozedur/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: GECCO_Prozedur/Prozedur
     * Description: Eine klinische Aktivität, die zur Früherkennung, Untersuchung, Diagnose, Heilung, Therapie, Bewertung oder in Hinsicht auf palliative Maßnahmen durchgeführt wird.
     */
    @Path("/content[openEHR-EHR-ACTION.procedure.v1]")
    private ProzedurAction prozedur;

    /**
     * Path: GECCO_Prozedur/Nicht durchgeführte Prozedur
     * Description: Ein Bericht über den Ausschluss eines/r Problems/Diagnose, familiäre Krankengeschichte, Medikation, Nebenwirkung/Allergens oder eines anderen klinischen Ereignisses, welche/s zur Zeit nicht oder noch nie vorhanden war.
     */
    @Path("/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Nicht durchgeführte Prozedur']")
    private NichtDurchgefuehrteProzedurEvaluation nichtDurchgefuehrteProzedur;

    /**
     * Path: GECCO_Prozedur/Unbekannte Prozedur
     * Description: Aussage darüber, dass bestimmte Gesundheitsinformationen zum Zeitpukt der Erfassung nicht in der Krankenakte oder einem Schriftstück erfasst werden können, da keine Kenntnisse darüber vorhanden sind.
     */
    @Path("/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekannte Prozedur']")
    private UnbekannteProzedurEvaluation unbekannteProzedur;

    /**
     * Path: GECCO_Prozedur/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: GECCO_Prozedur/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: GECCO_Prozedur/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: GECCO_Prozedur/territory
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

    public List<GeccoProzedurKategorieElement> getKategorie() {
        return this.kategorie;
    }

    public void setKategorie(List<GeccoProzedurKategorieElement> kategorie) {
        this.kategorie = kategorie;
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

    public NichtDurchgefuehrteProzedurEvaluation getNichtDurchgefuehrteProzedur() {
        return this.nichtDurchgefuehrteProzedur;
    }

    public void setNichtDurchgefuehrteProzedur(
            NichtDurchgefuehrteProzedurEvaluation nichtDurchgefuehrteProzedur) {
        this.nichtDurchgefuehrteProzedur = nichtDurchgefuehrteProzedur;
    }

    public UnbekannteProzedurEvaluation getUnbekannteProzedur() {
        return this.unbekannteProzedur;
    }

    public void setUnbekannteProzedur(UnbekannteProzedurEvaluation unbekannteProzedur) {
        this.unbekannteProzedur = unbekannteProzedur;
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
