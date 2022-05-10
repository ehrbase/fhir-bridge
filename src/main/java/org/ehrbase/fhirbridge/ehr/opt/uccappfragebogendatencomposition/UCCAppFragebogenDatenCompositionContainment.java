package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition.GesamtergebnisObservation;

public class UCCAppFragebogenDatenCompositionContainment extends Containment {
  public SelectAqlField<UCCAppFragebogenDatenComposition> U_C_C_APP_FRAGEBOGEN_DATEN_COMPOSITION = new AqlFieldImp<UCCAppFragebogenDatenComposition>(UCCAppFragebogenDatenComposition.class, "", "UCCAppFragebogenDatenComposition", UCCAppFragebogenDatenComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(UCCAppFragebogenDatenComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UCCAppFragebogenDatenComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppFragebogenDatenComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(UCCAppFragebogenDatenComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(UCCAppFragebogenDatenComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(UCCAppFragebogenDatenComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(UCCAppFragebogenDatenComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(UCCAppFragebogenDatenComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<GesamtergebnisObservation> GESAMTERGEBNIS = new AqlFieldImp<GesamtergebnisObservation>(UCCAppFragebogenDatenComposition.class, "/content[openEHR-EHR-OBSERVATION.questionnaire_entry.v0]", "gesamtergebnis", GesamtergebnisObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(UCCAppFragebogenDatenComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UCCAppFragebogenDatenComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UCCAppFragebogenDatenComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(UCCAppFragebogenDatenComposition.class, "/territory", "territory", Territory.class, this);

  private UCCAppFragebogenDatenCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.self_monitoring.v0");
  }

  public static UCCAppFragebogenDatenCompositionContainment getInstance() {
    return new UCCAppFragebogenDatenCompositionContainment();
  }
}
