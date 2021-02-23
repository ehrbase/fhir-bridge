package org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.BildgebendesUntersuchungsergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.RadiologischerBefundKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;

public class GECCORadiologischerBefundCompositionContainment extends Containment {
  public SelectAqlField<GECCORadiologischerBefundComposition> G_E_C_C_O_RADIOLOGISCHER_BEFUND_COMPOSITION = new AqlFieldImp<GECCORadiologischerBefundComposition>(GECCORadiologischerBefundComposition.class, "", "GECCORadiologischerBefundComposition", GECCORadiologischerBefundComposition.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GECCORadiologischerBefundComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(GECCORadiologischerBefundComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GECCORadiologischerBefundComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<RadiologischerBefundKategorieElement> KATEGORIE = new ListAqlFieldImp<RadiologischerBefundKategorieElement>(GECCORadiologischerBefundComposition.class, "/context/other_context[at0001]/items[at0005]", "kategorie", RadiologischerBefundKategorieElement.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCORadiologischerBefundComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(GECCORadiologischerBefundComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCORadiologischerBefundComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(GECCORadiologischerBefundComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(GECCORadiologischerBefundComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(GECCORadiologischerBefundComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public ListSelectAqlField<BildgebendesUntersuchungsergebnisObservation> BILDGEBENDES_UNTERSUCHUNGSERGEBNIS = new ListAqlFieldImp<BildgebendesUntersuchungsergebnisObservation>(GECCORadiologischerBefundComposition.class, "/content[openEHR-EHR-OBSERVATION.imaging_exam_result.v0]", "bildgebendesUntersuchungsergebnis", BildgebendesUntersuchungsergebnisObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(GECCORadiologischerBefundComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GECCORadiologischerBefundComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GECCORadiologischerBefundComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(GECCORadiologischerBefundComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(GECCORadiologischerBefundComposition.class, "/territory", "territory", Territory.class, this);

  private GECCORadiologischerBefundCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static GECCORadiologischerBefundCompositionContainment getInstance() {
    return new GECCORadiologischerBefundCompositionContainment();
  }
}
