package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AufnahmedatenAdminEntryContainment extends Containment {
  public SelectAqlField<AufnahmedatenAdminEntry> AUFNAHMEDATEN_ADMIN_ENTRY = new AqlFieldImp<AufnahmedatenAdminEntry>(AufnahmedatenAdminEntry.class, "", "AufnahmedatenAdminEntry", AufnahmedatenAdminEntry.class, this);

  public SelectAqlField<AufnahmegrundDefiningCode> AUFNAHMEGRUND_DEFINING_CODE = new AqlFieldImp<AufnahmegrundDefiningCode>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0013]/value|defining_code", "aufnahmegrundDefiningCode", AufnahmegrundDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUFNAHMEGRUND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0013]/null_flavour|defining_code", "aufnahmegrundNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AufnahmeanlassDefiningCode> AUFNAHMEANLASS_DEFINING_CODE = new AqlFieldImp<AufnahmeanlassDefiningCode>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0049]/value|defining_code", "aufnahmeanlassDefiningCode", AufnahmeanlassDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AUFNAHMEANLASS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0049]/null_flavour|defining_code", "aufnahmeanlassNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> IDENTIFIKATIONSNUMMER_DES_PATIENTEN_VOR_DER_AUFNAHME_VALUE = new AqlFieldImp<String>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0023]/value|value", "identifikationsnummerDesPatientenVorDerAufnahmeValue", String.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATIONSNUMMER_DES_PATIENTEN_VOR_DER_AUFNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0023]/null_flavour|defining_code", "identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DER_AUFNAHME_VALUE = new AqlFieldImp<TemporalAccessor>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0071]/value|value", "datumUhrzeitDerAufnahmeValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_UHRZEIT_DER_AUFNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0071]/null_flavour|defining_code", "datumUhrzeitDerAufnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[at0131]", "zugewiesenerPatientenstandort", Cluster.class, this);

  public SelectAqlField<VorherigerPatientenstandortVorAufnahmeCluster> VORHERIGER_PATIENTENSTANDORT_VOR_AUFNAHME = new AqlFieldImp<VorherigerPatientenstandortVorAufnahmeCluster>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1]", "vorherigerPatientenstandortVorAufnahme", VorherigerPatientenstandortVorAufnahmeCluster.class, this);

  public SelectAqlField<VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster> VORHERIGE_VERANTWORTLICHE_ORGANISATIONSEINHEIT_VOR_AUFNAHME = new AqlFieldImp<VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster>(AufnahmedatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.organization.v0]", "vorherigeVerantwortlicheOrganisationseinheitVorAufnahme", VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AufnahmedatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AufnahmedatenAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AufnahmedatenAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AufnahmedatenAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.admission.v0");
  }

  public static AufnahmedatenAdminEntryContainment getInstance() {
    return new AufnahmedatenAdminEntryContainment();
  }
}
