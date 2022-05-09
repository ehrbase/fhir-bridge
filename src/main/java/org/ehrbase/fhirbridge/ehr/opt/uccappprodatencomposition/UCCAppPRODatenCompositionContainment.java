package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BlutdruckObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.KoerpergewichtObservation;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.PulsfrequenzHerzfrequenzObservation;

public class UCCAppPRODatenCompositionContainment extends Containment {
  public SelectAqlField<UCCAppPRODatenComposition> U_C_C_APP_P_R_O_DATEN_COMPOSITION = new AqlFieldImp<UCCAppPRODatenComposition>(UCCAppPRODatenComposition.class, "", "UCCAppPRODatenComposition", UCCAppPRODatenComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(UCCAppPRODatenComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UCCAppPRODatenComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppPRODatenComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(UCCAppPRODatenComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppPRODatenComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(UCCAppPRODatenComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(UCCAppPRODatenComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(UCCAppPRODatenComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public ListSelectAqlField<BlutdruckObservation> BLUTDRUCK = new ListAqlFieldImp<BlutdruckObservation>(UCCAppPRODatenComposition.class, "/content[openEHR-EHR-OBSERVATION.blood_pressure.v2]", "blutdruck", BlutdruckObservation.class, this);

  public ListSelectAqlField<KoerpergewichtObservation> KOERPERGEWICHT = new ListAqlFieldImp<KoerpergewichtObservation>(UCCAppPRODatenComposition.class, "/content[openEHR-EHR-OBSERVATION.body_weight.v2]", "koerpergewicht", KoerpergewichtObservation.class, this);

  public ListSelectAqlField<PulsfrequenzHerzfrequenzObservation> PULSFREQUENZ_HERZFREQUENZ = new ListAqlFieldImp<PulsfrequenzHerzfrequenzObservation>(UCCAppPRODatenComposition.class, "/content[openEHR-EHR-OBSERVATION.pulse.v2]", "pulsfrequenzHerzfrequenz", PulsfrequenzHerzfrequenzObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(UCCAppPRODatenComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UCCAppPRODatenComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UCCAppPRODatenComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(UCCAppPRODatenComposition.class, "/territory", "territory", Territory.class, this);

  private UCCAppPRODatenCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.self_monitoring.v0");
  }

  public static UCCAppPRODatenCompositionContainment getInstance() {
    return new UCCAppPRODatenCompositionContainment();
  }
}
