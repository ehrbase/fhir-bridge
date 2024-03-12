package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
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
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.GeschlechtEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.PersonendatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition.VitalstatusEvaluation;

public class KDSPersonCompositionContainment extends Containment {
  public SelectAqlField<KDSPersonComposition> K_D_S_PERSON_COMPOSITION = new AqlFieldImp<KDSPersonComposition>(KDSPersonComposition.class, "", "KDSPersonComposition", KDSPersonComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(KDSPersonComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<DvIdentifier> PATIENT_ID = new AqlFieldImp<DvIdentifier>(KDSPersonComposition.class, "/context/other_context[at0003]/items[at0004]/value", "patientId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> PATIENT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KDSPersonComposition.class, "/context/other_context[at0003]/items[at0004]/null_flavour|defining_code", "patientIdNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KDSPersonComposition.class, "/context/other_context[at0003]/items[at0005]", "erweiterung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSPersonComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(KDSPersonComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KDSPersonComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(KDSPersonComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(KDSPersonComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(KDSPersonComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<GeschlechtEvaluation> GESCHLECHT = new AqlFieldImp<GeschlechtEvaluation>(KDSPersonComposition.class, "/content[openEHR-EHR-EVALUATION.gender.v1]", "geschlecht", GeschlechtEvaluation.class, this);

  public SelectAqlField<PersonendatenAdminEntry> PERSONENDATEN = new AqlFieldImp<PersonendatenAdminEntry>(KDSPersonComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.person_data.v0]", "personendaten", PersonendatenAdminEntry.class, this);

  public SelectAqlField<VitalstatusEvaluation> VITALSTATUS = new AqlFieldImp<VitalstatusEvaluation>(KDSPersonComposition.class, "/content[openEHR-EHR-EVALUATION.vital_status.v1]", "vitalstatus", VitalstatusEvaluation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(KDSPersonComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KDSPersonComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KDSPersonComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(KDSPersonComposition.class, "/territory", "territory", Territory.class, this);

  private KDSPersonCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.person.v0");
  }

  public static KDSPersonCompositionContainment getInstance() {
    return new KDSPersonCompositionContainment();
  }
}
