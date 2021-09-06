package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
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
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.HipMetadataCluster;
import org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition.MediendateiCluster;

import java.time.temporal.TemporalAccessor;

public class HIPDocumentCompositionContainment extends Containment {
  public SelectAqlField<HIPDocumentComposition> H_I_P_DOCUMENT_COMPOSITION = new AqlFieldImp<HIPDocumentComposition>(HIPDocumentComposition.class, "", "HIPDocumentComposition", HIPDocumentComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(HIPDocumentComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[at0002]/value|value", "berichtIdValue", String.class, this);

  public SelectAqlField<NullFlavour> BERICHT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[at0002]/null_flavour|defining_code", "berichtIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "statusValue", String.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<HipMetadataCluster> HIP_METADATA = new AqlFieldImp<HipMetadataCluster>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.hip_metadata.v0]", "hipMetadata", HipMetadataCluster.class, this);

  public SelectAqlField<MediendateiCluster> MEDIENDATEI = new AqlFieldImp<MediendateiCluster>(HIPDocumentComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.media_file.v0]", "mediendatei", MediendateiCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(HIPDocumentComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(HIPDocumentComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(HIPDocumentComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(HIPDocumentComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(HIPDocumentComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(HIPDocumentComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(HIPDocumentComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HIPDocumentComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(HIPDocumentComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(HIPDocumentComposition.class, "/territory", "territory", Territory.class, this);

  private HIPDocumentCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.report.v1");
  }

  public static HIPDocumentCompositionContainment getInstance() {
    return new HIPDocumentCompositionContainment();
  }
}
