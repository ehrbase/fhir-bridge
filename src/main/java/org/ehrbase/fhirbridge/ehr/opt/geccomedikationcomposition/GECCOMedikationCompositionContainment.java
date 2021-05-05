package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.KategorieDefiningCode;

public class GECCOMedikationCompositionContainment extends Containment {
  public SelectAqlField<GECCOMedikationComposition> G_E_C_C_O_MEDIKATION_COMPOSITION = new AqlFieldImp<GECCOMedikationComposition>(GECCOMedikationComposition.class, "", "GECCOMedikationComposition", GECCOMedikationComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(GECCOMedikationComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GECCOMedikationComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<KategorieDefiningCode> KATEGORIE_DEFINING_CODE = new AqlFieldImp<KategorieDefiningCode>(GECCOMedikationComposition.class, "/context/other_context[at0001]/items[at0005]/value|defining_code", "kategorieDefiningCode", KategorieDefiningCode.class, this);

  public SelectAqlField<NullFlavour> KATEGORIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GECCOMedikationComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "kategorieNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOMedikationComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(GECCOMedikationComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GECCOMedikationComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(GECCOMedikationComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(GECCOMedikationComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(GECCOMedikationComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<Covid19TherapieObservation> COVID19_THERAPIE = new AqlFieldImp<Covid19TherapieObservation>(GECCOMedikationComposition.class, "/content[openEHR-EHR-OBSERVATION.medication_statement.v0]", "covid19Therapie", Covid19TherapieObservation.class, this);

  public SelectAqlField<AceHemmerObservation> ACE_HEMMER = new AqlFieldImp<AceHemmerObservation>(GECCOMedikationComposition.class, "/content[openEHR-EHR-OBSERVATION.medication_statement.v0]", "aceHemmer", AceHemmerObservation.class, this);

  public SelectAqlField<ImmunglobulineObservation> IMMUNGLOBULINE = new AqlFieldImp<ImmunglobulineObservation>(GECCOMedikationComposition.class, "/content[openEHR-EHR-OBSERVATION.medication_statement.v0]", "immunglobuline", ImmunglobulineObservation.class, this);

  public SelectAqlField<AntikoagulanzienObservation> ANTIKOAGULANZIEN = new AqlFieldImp<AntikoagulanzienObservation>(GECCOMedikationComposition.class, "/content[openEHR-EHR-OBSERVATION.medication_statement.v0]", "antikoagulanzien", AntikoagulanzienObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(GECCOMedikationComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GECCOMedikationComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GECCOMedikationComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(GECCOMedikationComposition.class, "/territory", "territory", Territory.class, this);

  private GECCOMedikationCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static GECCOMedikationCompositionContainment getInstance() {
    return new GECCOMedikationCompositionContainment();
  }
}
