package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-03-09T14:41:23.600340+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@Template("Symptom")
public class SymptomComposition implements CompositionEntity {
    /**
     * Path: COVID-19 Symptom/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: COVID-19 Symptom/context/Erweiterung
     * Description: Ergänzende Angaben zum Registereintrag.
     */
    @Path("/context/other_context[at0001]/items[at0002]")
    private List<Cluster> erweiterung;

    /**
     * Path: COVID-19 Symptom/context/Status
     * Description: Status der gelieferten Daten für den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
     */
    @Path("/context/other_context[at0001]/items[at0004]/value|defining_code")
    private StatusDefiningCode statusDefiningCode;

    /**
     * Path: COVID-19 Symptom/context/Baum/Status/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0004]/null_flavour|defining_code")
    private NullFlavour statusNullFlavourDefiningCode;

    /**
     * Path: COVID-19 Symptom/context/Kategorie
     * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/context/other_context[at0001]/items[at0005]/value")
    private DvCodedText kategorie;

    /**
     * Path: COVID-19 Symptom/context/Baum/Kategorie/null_flavour
     */
    @Path("/context/other_context[at0001]/items[at0005]/null_flavour|defining_code")
    private NullFlavour kategorieNullFlavourDefiningCode;

    /**
     * Path: COVID-19 Symptom/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: COVID-19 Symptom/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: COVID-19 Symptom/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: COVID-19 Symptom/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: COVID-19 Symptom/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: COVID-19 Symptom/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: COVID-19 Symptom/Vorliegendes Symptom
     * Description: Festgestellte Beobachtung einer körperlichen oder geistigen Störung bei einer Person.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.symptom_sign.v0 and name/value='Vorliegendes Symptom']")
    private VorliegendesSymptomObservation vorliegendesSymptom;

    /**
     * Path: COVID-19 Symptom/Ausgeschlossenes Symptom
     * Description: Ein Bericht über den Ausschluss eines/r Problems/Diagnose, familiäre Krankengeschichte, Medikation, Nebenwirkung/Allergens oder eines anderen klinischen Ereignisses, welche/s zur Zeit nicht oder noch nie vorhanden war.
     */
    @Path("/content[openEHR-EHR-EVALUATION.exclusion_specific.v1 and name/value='Ausgeschlossenes Symptom']")
    private AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom;

    /**
     * Path: COVID-19 Symptom/Unbekanntes Symptom
     * Description: Aussage darüber, dass bestimmte Gesundheitsinformationen zum Zeitpukt der Erfassung nicht in der Krankenakte oder einem Schriftstück erfasst werden können, da keine Kenntnisse darüber vorhanden sind.
     */
    @Path("/content[openEHR-EHR-EVALUATION.absence.v2 and name/value='Unbekanntes Symptom']")
    private UnbekanntesSymptomEvaluation unbekanntesSymptom;

    /**
     * Path: COVID-19 Symptom/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: COVID-19 Symptom/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: COVID-19 Symptom/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: COVID-19 Symptom/territory
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

    public StatusDefiningCode getStatusDefiningCode() {
        return this.statusDefiningCode;
    }

    public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
        this.statusDefiningCode = statusDefiningCode;
    }

    public NullFlavour getStatusNullFlavourDefiningCode() {
        return this.statusNullFlavourDefiningCode;
    }

    public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
        this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
    }

    public DvCodedText getKategorie() {
        return this.kategorie;
    }

    public void setKategorie(DvCodedText kategorie) {
        this.kategorie = kategorie;
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

    public VorliegendesSymptomObservation getVorliegendesSymptom() {
        return this.vorliegendesSymptom;
    }

    public void setVorliegendesSymptom(VorliegendesSymptomObservation vorliegendesSymptom) {
        this.vorliegendesSymptom = vorliegendesSymptom;
    }

    public AusgeschlossenesSymptomEvaluation getAusgeschlossenesSymptom() {
        return this.ausgeschlossenesSymptom;
    }

    public void setAusgeschlossenesSymptom(
            AusgeschlossenesSymptomEvaluation ausgeschlossenesSymptom) {
        this.ausgeschlossenesSymptom = ausgeschlossenesSymptom;
    }

    public UnbekanntesSymptomEvaluation getUnbekanntesSymptom() {
        return this.unbekanntesSymptom;
    }

    public void setUnbekanntesSymptom(UnbekanntesSymptomEvaluation unbekanntesSymptom) {
        this.unbekanntesSymptom = unbekanntesSymptom;
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
