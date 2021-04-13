package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.AusgeschlossenesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.UnbekanntesSymptomEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition.VorliegendesSymptomObservation;

public class SymptomCompositionContainment extends Containment {
  public SelectAqlField<SymptomComposition> SYMPTOM_COMPOSITION = new AqlFieldImp<SymptomComposition>(SymptomComposition.class, "", "SymptomComposition", SymptomComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(SymptomComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(SymptomComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(SymptomComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SymptomComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> KATEGORIE = new AqlFieldImp<DvCodedText>(SymptomComposition.class, "/context/other_context[at0001]/items[at0005]/value", "kategorie", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> KATEGORIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SymptomComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "kategorieNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(SymptomComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(SymptomComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(SymptomComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(SymptomComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(SymptomComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(SymptomComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<VorliegendesSymptomObservation> VORLIEGENDES_SYMPTOM = new AqlFieldImp<VorliegendesSymptomObservation>(SymptomComposition.class, "/content[openEHR-EHR-OBSERVATION.symptom_sign.v0]", "vorliegendesSymptom", VorliegendesSymptomObservation.class, this);

  public SelectAqlField<AusgeschlossenesSymptomEvaluation> AUSGESCHLOSSENES_SYMPTOM = new AqlFieldImp<AusgeschlossenesSymptomEvaluation>(SymptomComposition.class, "/content[openEHR-EHR-EVALUATION.exclusion_specific.v1]", "ausgeschlossenesSymptom", AusgeschlossenesSymptomEvaluation.class, this);

  public SelectAqlField<UnbekanntesSymptomEvaluation> UNBEKANNTES_SYMPTOM = new AqlFieldImp<UnbekanntesSymptomEvaluation>(SymptomComposition.class, "/content[openEHR-EHR-EVALUATION.absence.v2]", "unbekanntesSymptom", UnbekanntesSymptomEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(SymptomComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SymptomComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(SymptomComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(SymptomComposition.class, "/territory", "territory", Territory.class, this);

  private SymptomCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static SymptomCompositionContainment getInstance() {
    return new SymptomCompositionContainment();
  }
}
