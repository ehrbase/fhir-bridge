package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.FallidentifikationCluster;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturObservation;

public class IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment extends Containment {
  public SelectAqlField<IntensivmedizinischesMonitoringKorpertemperaturComposition> INTENSIVMEDIZINISCHES_MONITORING_KORPERTEMPERATUR_COMPOSITION = new AqlFieldImp<IntensivmedizinischesMonitoringKorpertemperaturComposition>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "", "IntensivmedizinischesMonitoringKorpertemperaturComposition", IntensivmedizinischesMonitoringKorpertemperaturComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> BERICHT_NAME_VALUE = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtNameValue", String.class, this);

  public SelectAqlField<NullFlavour> BERICHT_NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "berichtNameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]", "fallidentifikation", FallidentifikationCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public ListSelectAqlField<KoerpertemperaturObservation> KOERPERTEMPERATUR = new ListAqlFieldImp<KoerpertemperaturObservation>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/content[openEHR-EHR-OBSERVATION.body_temperature.v2]", "koerpertemperatur", KoerpertemperaturObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(IntensivmedizinischesMonitoringKorpertemperaturComposition.class, "/territory", "territory", Territory.class, this);

  private IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment getInstance(
      ) {
    return new IntensivmedizinischesMonitoringKorpertemperaturCompositionContainment();
  }
}
