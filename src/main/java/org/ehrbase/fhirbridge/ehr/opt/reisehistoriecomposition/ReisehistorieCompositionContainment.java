package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition;

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
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.StatusDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;

public class ReisehistorieCompositionContainment extends Containment {
  public SelectAqlField<ReisehistorieComposition> REISEHISTORIE_COMPOSITION = new AqlFieldImp<ReisehistorieComposition>(ReisehistorieComposition.class, "", "ReisehistorieComposition", ReisehistorieComposition.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ReisehistorieComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(ReisehistorieComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ReisehistorieComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ReisehistorieKategorieElement> KATEGORIE = new ListAqlFieldImp<ReisehistorieKategorieElement>(ReisehistorieComposition.class, "/context/other_context[at0001]/items[at0005]", "kategorie", ReisehistorieKategorieElement.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReisehistorieComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(ReisehistorieComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ReisehistorieComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(ReisehistorieComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(ReisehistorieComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(ReisehistorieComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<ReisehistorieAdminEntry> REISEHISTORIE = new AqlFieldImp<ReisehistorieAdminEntry>(ReisehistorieComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.travel_event.v0]", "reisehistorie", ReisehistorieAdminEntry.class, this);

  public SelectAqlField<KeineReisehistorieEvaluation> KEINE_REISEHISTORIE = new AqlFieldImp<KeineReisehistorieEvaluation>(ReisehistorieComposition.class, "/content[openEHR-EHR-EVALUATION.exclusion_specific.v1]", "keineReisehistorie", KeineReisehistorieEvaluation.class, this);

  public SelectAqlField<UnbekannteReisehistorieEvaluation> UNBEKANNTE_REISEHISTORIE = new AqlFieldImp<UnbekannteReisehistorieEvaluation>(ReisehistorieComposition.class, "/content[openEHR-EHR-EVALUATION.absence.v2]", "unbekannteReisehistorie", UnbekannteReisehistorieEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(ReisehistorieComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ReisehistorieComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ReisehistorieComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(ReisehistorieComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(ReisehistorieComposition.class, "/territory", "territory", Territory.class, this);

  private ReisehistorieCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static ReisehistorieCompositionContainment getInstance() {
    return new ReisehistorieCompositionContainment();
  }
}
