package org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;

public class KennzeichnungErregernachweisSARSCoV2CompositionContainment extends Containment {
  public SelectAqlField<KennzeichnungErregernachweisSARSCoV2Composition> KENNZEICHNUNG_ERREGERNACHWEIS_S_A_R_S_CO_V2_COMPOSITION = new AqlFieldImp<KennzeichnungErregernachweisSARSCoV2Composition>(KennzeichnungErregernachweisSARSCoV2Composition.class, "", "KennzeichnungErregernachweisSARSCoV2Composition", KennzeichnungErregernachweisSARSCoV2Composition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> BERICHT_NAME_VALUE = new AqlFieldImp<String>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtNameValue", String.class, this);

  public SelectAqlField<NullFlavour> BERICHT_NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "berichtNameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<KennzeichnungErregernachweisEvaluation> KENNZEICHNUNG_ERREGERNACHWEIS = new AqlFieldImp<KennzeichnungErregernachweisEvaluation>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/content[openEHR-EHR-EVALUATION.flag_pathogen.v0]", "kennzeichnungErregernachweis", KennzeichnungErregernachweisEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(KennzeichnungErregernachweisSARSCoV2Composition.class, "/territory", "territory", Territory.class, this);

  private KennzeichnungErregernachweisSARSCoV2CompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static KennzeichnungErregernachweisSARSCoV2CompositionContainment getInstance() {
    return new KennzeichnungErregernachweisSARSCoV2CompositionContainment();
  }
}
