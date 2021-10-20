package org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.PulsoxymetrieObservation;
import org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition.StatusDefiningCode;

public class PulsoxymetrieCompositionContainment extends Containment {
  public SelectAqlField<PulsoxymetrieComposition> PULSOXYMETRIE_COMPOSITION = new AqlFieldImp<PulsoxymetrieComposition>(PulsoxymetrieComposition.class, "", "PulsoxymetrieComposition", PulsoxymetrieComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(PulsoxymetrieComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(PulsoxymetrieComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(PulsoxymetrieComposition.class, "/context/other_context[at0001]/items[at0004]/value|defining_code", "statusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PulsoxymetrieComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<PulsoxymetrieKategorieElement> KATEGORIE = new ListAqlFieldImp<PulsoxymetrieKategorieElement>(PulsoxymetrieComposition.class, "/context/other_context[at0001]/items[at0005]", "kategorie", PulsoxymetrieKategorieElement.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(PulsoxymetrieComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(PulsoxymetrieComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(PulsoxymetrieComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(PulsoxymetrieComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(PulsoxymetrieComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(PulsoxymetrieComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<PulsoxymetrieObservation> PULSOXYMETRIE = new AqlFieldImp<PulsoxymetrieObservation>(PulsoxymetrieComposition.class, "/content[openEHR-EHR-OBSERVATION.pulse_oximetry.v1]", "pulsoxymetrie", PulsoxymetrieObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(PulsoxymetrieComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PulsoxymetrieComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PulsoxymetrieComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(PulsoxymetrieComposition.class, "/territory", "territory", Territory.class, this);

  private PulsoxymetrieCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.registereintrag.v1");
  }

  public static PulsoxymetrieCompositionContainment getInstance() {
    return new PulsoxymetrieCompositionContainment();
  }
}
