package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzObservation;

public class UCCAppSensorDatenCompositionContainment extends Containment {
  public SelectAqlField<UCCAppSensorDatenComposition> U_C_C_APP_SENSOR_DATEN_COMPOSITION = new AqlFieldImp<UCCAppSensorDatenComposition>(UCCAppSensorDatenComposition.class, "", "UCCAppSensorDatenComposition", UCCAppSensorDatenComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(UCCAppSensorDatenComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UCCAppSensorDatenComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppSensorDatenComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(UCCAppSensorDatenComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppSensorDatenComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(UCCAppSensorDatenComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(UCCAppSensorDatenComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(UCCAppSensorDatenComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<PulsfrequenzHerzfrequenzObservation> PULSFREQUENZ_HERZFREQUENZ = new AqlFieldImp<PulsfrequenzHerzfrequenzObservation>(UCCAppSensorDatenComposition.class, "/content[openEHR-EHR-OBSERVATION.pulse.v2]", "pulsfrequenzHerzfrequenz", PulsfrequenzHerzfrequenzObservation.class, this);

  public SelectAqlField<MitSensorGemesseneKoerperlicheAktivitaetObservation> MIT_SENSOR_GEMESSENE_KOERPERLICHE_AKTIVITAET = new AqlFieldImp<MitSensorGemesseneKoerperlicheAktivitaetObservation>(UCCAppSensorDatenComposition.class, "/content[openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0]", "mitSensorGemesseneKoerperlicheAktivitaet", MitSensorGemesseneKoerperlicheAktivitaetObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(UCCAppSensorDatenComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UCCAppSensorDatenComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UCCAppSensorDatenComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(UCCAppSensorDatenComposition.class, "/territory", "territory", Territory.class, this);

  private UCCAppSensorDatenCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.self_monitoring.v0");
  }

  public static UCCAppSensorDatenCompositionContainment getInstance() {
    return new UCCAppSensorDatenCompositionContainment();
  }
}
