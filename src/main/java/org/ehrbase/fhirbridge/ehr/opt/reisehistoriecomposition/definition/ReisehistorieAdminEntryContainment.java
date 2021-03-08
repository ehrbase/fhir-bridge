package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ReisehistorieAdminEntryContainment extends Containment {
  public SelectAqlField<ReisehistorieAdminEntry> REISEHISTORIE_ADMIN_ENTRY = new AqlFieldImp<ReisehistorieAdminEntry>(ReisehistorieAdminEntry.class, "", "ReisehistorieAdminEntry", ReisehistorieAdminEntry.class, this);

  public SelectAqlField<ReiseAngetretenDefiningCode> REISE_ANGETRETEN_DEFINING_CODE = new AqlFieldImp<ReiseAngetretenDefiningCode>(ReisehistorieAdminEntry.class, "/data[at0001]/items[at0022]/value|defining_code", "reiseAngetretenDefiningCode", ReiseAngetretenDefiningCode.class, this);

  public SelectAqlField<NullFlavour> REISE_ANGETRETEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ReisehistorieAdminEntry.class, "/data[at0001]/items[at0022]/null_flavour|defining_code", "reiseAngetretenNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ReisehistorieBestimmtesReisezielCluster> BESTIMMTES_REISEZIEL = new ListAqlFieldImp<ReisehistorieBestimmtesReisezielCluster>(ReisehistorieAdminEntry.class, "/data[at0001]/items[at0010]", "bestimmtesReiseziel", ReisehistorieBestimmtesReisezielCluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_REISEDETAILS = new ListAqlFieldImp<Cluster>(ReisehistorieAdminEntry.class, "/data[at0001]/items[at0025]", "zusaetzlicheReisedetails", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ReisehistorieAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ReisehistorieAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ReisehistorieAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ReisehistorieAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.travel_event.v0");
  }

  public static ReisehistorieAdminEntryContainment getInstance() {
    return new ReisehistorieAdminEntryContainment();
  }
}
