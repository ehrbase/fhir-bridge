package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.GeccoProzedurKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.NichtDurchgefuehrteProzedurEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.ProzedurAction;
import org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition.UnbekannteProzedurEvaluation;

public class GECCOProzedurCompositionContainment extends Containment {
  public SelectAqlField<GECCOProzedurComposition> G_E_C_C_O_PROZEDUR_COMPOSITION = new AqlFieldImp<GECCOProzedurComposition>(GECCOProzedurComposition.class, "", "GECCOProzedurComposition", GECCOProzedurComposition.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GECCOProzedurComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public ListSelectAqlField<GeccoProzedurKategorieElement> KATEGORIE = new ListAqlFieldImp<GeccoProzedurKategorieElement>(GECCOProzedurComposition.class, "/context/other_context[at0001]/items[at0005]", "kategorie", GeccoProzedurKategorieElement.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOProzedurComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(GECCOProzedurComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOProzedurComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(GECCOProzedurComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(GECCOProzedurComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(GECCOProzedurComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<ProzedurAction> PROZEDUR = new AqlFieldImp<ProzedurAction>(GECCOProzedurComposition.class, "/content[openEHR-EHR-ACTION.procedure.v1]", "prozedur", ProzedurAction.class, this);

  public SelectAqlField<NichtDurchgefuehrteProzedurEvaluation> NICHT_DURCHGEFUEHRTE_PROZEDUR = new AqlFieldImp<NichtDurchgefuehrteProzedurEvaluation>(GECCOProzedurComposition.class, "/content[openEHR-EHR-EVALUATION.exclusion_specific.v1]", "nichtDurchgefuehrteProzedur", NichtDurchgefuehrteProzedurEvaluation.class, this);

  public SelectAqlField<UnbekannteProzedurEvaluation> UNBEKANNTE_PROZEDUR = new AqlFieldImp<UnbekannteProzedurEvaluation>(GECCOProzedurComposition.class, "/content[openEHR-EHR-EVALUATION.absence.v2]", "unbekannteProzedur", UnbekannteProzedurEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(GECCOProzedurComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GECCOProzedurComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GECCOProzedurComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(GECCOProzedurComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(GECCOProzedurComposition.class, "/territory", "territory", Territory.class, this);

  private GECCOProzedurCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static GECCOProzedurCompositionContainment getInstance() {
    return new GECCOProzedurCompositionContainment();
  }
}
