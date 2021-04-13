package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.AlterObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeccoPersonendatenKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition.StatusDefiningCode;

public class GECCOPersonendatenCompositionContainment extends Containment {
  public SelectAqlField<GECCOPersonendatenComposition> G_E_C_C_O_PERSONENDATEN_COMPOSITION = new AqlFieldImp<GECCOPersonendatenComposition>(GECCOPersonendatenComposition.class, "", "GECCOPersonendatenComposition", GECCOPersonendatenComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(GECCOPersonendatenComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<GeccoPersonendatenKategorieElement> KATEGORIE = new ListAqlFieldImp<GeccoPersonendatenKategorieElement>(GECCOPersonendatenComposition.class, "/context/other_context[at0001]/items[at0005]", "kategorie", GeccoPersonendatenKategorieElement.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOPersonendatenComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(GECCOPersonendatenComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOPersonendatenComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(GECCOPersonendatenComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(GECCOPersonendatenComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(GECCOPersonendatenComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<PersonendatenAdminEntry> PERSONENDATEN = new AqlFieldImp<PersonendatenAdminEntry>(GECCOPersonendatenComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.person_data.v0]", "personendaten", PersonendatenAdminEntry.class, this);

  public SelectAqlField<AlterObservation> ALTER = new AqlFieldImp<AlterObservation>(GECCOPersonendatenComposition.class, "/content[openEHR-EHR-OBSERVATION.age.v0]", "alter", AlterObservation.class, this);

  public SelectAqlField<GeschlechtEvaluation> GESCHLECHT = new AqlFieldImp<GeschlechtEvaluation>(GECCOPersonendatenComposition.class, "/content[openEHR-EHR-EVALUATION.gender.v1]", "geschlecht", GeschlechtEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(GECCOPersonendatenComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GECCOPersonendatenComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GECCOPersonendatenComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(GECCOPersonendatenComposition.class, "/territory", "territory", Territory.class, this);

  private GECCOPersonendatenCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static GECCOPersonendatenCompositionContainment getInstance() {
    return new GECCOPersonendatenCompositionContainment();
  }
}
