package org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition;

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

public class EntlassungsartAdminEntryContainment extends Containment {
  public SelectAqlField<EntlassungsartAdminEntry> ENTLASSUNGSART_ADMIN_ENTRY = new AqlFieldImp<EntlassungsartAdminEntry>(EntlassungsartAdminEntry.class, "", "EntlassungsartAdminEntry", EntlassungsartAdminEntry.class, this);

  public SelectAqlField<ArtDerEntlassungDefiningCode> ART_DER_ENTLASSUNG_DEFINING_CODE = new AqlFieldImp<ArtDerEntlassungDefiningCode>(EntlassungsartAdminEntry.class, "/data[at0001]/items[at0040]/value|defining_code", "artDerEntlassungDefiningCode", ArtDerEntlassungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_DER_ENTLASSUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EntlassungsartAdminEntry.class, "/data[at0001]/items[at0040]/null_flavour|defining_code", "artDerEntlassungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> LETZTER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(EntlassungsartAdminEntry.class, "/data[at0001]/items[at0066]", "letzterPatientenstandort", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUGEWIESENER_PATIENTENSTANDORT = new ListAqlFieldImp<Cluster>(EntlassungsartAdminEntry.class, "/data[at0001]/items[at0067]", "zugewiesenerPatientenstandort", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(EntlassungsartAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(EntlassungsartAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(EntlassungsartAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EntlassungsartAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0");
  }

  public static EntlassungsartAdminEntryContainment getInstance() {
    return new EntlassungsartAdminEntryContainment();
  }
}
