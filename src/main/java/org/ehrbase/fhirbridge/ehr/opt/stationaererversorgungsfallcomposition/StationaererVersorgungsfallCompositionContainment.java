package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition;

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
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.AufnahmedatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.EntlassungsdatenAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.FachlicheOrganisationseinheitCluster;
import org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition.FallstatusDefiningCode;

public class StationaererVersorgungsfallCompositionContainment extends Containment {
  public SelectAqlField<StationaererVersorgungsfallComposition> STATIONAERER_VERSORGUNGSFALL_COMPOSITION = new AqlFieldImp<StationaererVersorgungsfallComposition>(StationaererVersorgungsfallComposition.class, "", "StationaererVersorgungsfallComposition", StationaererVersorgungsfallComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(StationaererVersorgungsfallComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public SelectAqlField<String> FALLTYP_VALUE = new AqlFieldImp<String>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0005]/value|value", "falltypValue", String.class, this);

  public SelectAqlField<NullFlavour> FALLTYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0005]/null_flavour|defining_code", "falltypNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FALLKLASSE_VALUE = new AqlFieldImp<String>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0004]/value|value", "fallklasseValue", String.class, this);

  public SelectAqlField<NullFlavour> FALLKLASSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0004]/null_flavour|defining_code", "fallklasseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FallstatusDefiningCode> FALLSTATUS_DEFINING_CODE = new AqlFieldImp<FallstatusDefiningCode>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0010]/value|defining_code", "fallstatusDefiningCode", FallstatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> FALLSTATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0010]/null_flavour|defining_code", "fallstatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FALL_KENNUNG_VALUE = new AqlFieldImp<String>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0003]/value|value", "fallKennungValue", String.class, this);

  public SelectAqlField<NullFlavour> FALL_KENNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0003]/null_flavour|defining_code", "fallKennungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> UEBERGEORDNETER_FALL_VALUE = new AqlFieldImp<String>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0011]/value|value", "uebergeordneterFallValue", String.class, this);

  public SelectAqlField<NullFlavour> UEBERGEORDNETER_FALL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0011]/null_flavour|defining_code", "uebergeordneterFallNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[at0002]", "erweiterung", Cluster.class, this);

  public ListSelectAqlField<FachlicheOrganisationseinheitCluster> FACHLICHE_ORGANISATIONSEINHEIT = new ListAqlFieldImp<FachlicheOrganisationseinheitCluster>(StationaererVersorgungsfallComposition.class, "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.organization.v0]", "fachlicheOrganisationseinheit", FachlicheOrganisationseinheitCluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(StationaererVersorgungsfallComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(StationaererVersorgungsfallComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(StationaererVersorgungsfallComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(StationaererVersorgungsfallComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(StationaererVersorgungsfallComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(StationaererVersorgungsfallComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<AufnahmedatenAdminEntry> AUFNAHMEDATEN = new AqlFieldImp<AufnahmedatenAdminEntry>(StationaererVersorgungsfallComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.admission.v0]", "aufnahmedaten", AufnahmedatenAdminEntry.class, this);

  public SelectAqlField<EntlassungsdatenAdminEntry> ENTLASSUNGSDATEN = new AqlFieldImp<EntlassungsdatenAdminEntry>(StationaererVersorgungsfallComposition.class, "/content[openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0]", "entlassungsdaten", EntlassungsdatenAdminEntry.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(StationaererVersorgungsfallComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(StationaererVersorgungsfallComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StationaererVersorgungsfallComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(StationaererVersorgungsfallComposition.class, "/territory", "territory", Territory.class, this);

  private StationaererVersorgungsfallCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.fall.v1");
  }

  public static StationaererVersorgungsfallCompositionContainment getInstance() {
    return new StationaererVersorgungsfallCompositionContainment();
  }
}
