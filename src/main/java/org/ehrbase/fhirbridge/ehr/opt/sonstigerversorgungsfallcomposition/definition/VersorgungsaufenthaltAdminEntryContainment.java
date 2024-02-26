package org.ehrbase.fhirbridge.ehr.opt.sonstigerversorgungsfallcomposition.definition;

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

public class VersorgungsaufenthaltAdminEntryContainment extends Containment {
  public SelectAqlField<VersorgungsaufenthaltAdminEntry> VERSORGUNGSAUFENTHALT_ADMIN_ENTRY = new AqlFieldImp<VersorgungsaufenthaltAdminEntry>(VersorgungsaufenthaltAdminEntry.class, "", "VersorgungsaufenthaltAdminEntry", VersorgungsaufenthaltAdminEntry.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_VALUE = new AqlFieldImp<TemporalAccessor>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0004]/value|value", "beginnValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> BEGINN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0004]/null_flavour|defining_code", "beginnNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ENDE_VALUE = new AqlFieldImp<TemporalAccessor>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0005]/value|value", "endeValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ENDE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "endeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> GRUND_DES_AUFENTHALTES_VALUE = new AqlFieldImp<String>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0006]/value|value", "grundDesAufenthaltesValue", String.class, this);

  public SelectAqlField<NullFlavour> GRUND_DES_AUFENTHALTES_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0006]/null_flavour|defining_code", "grundDesAufenthaltesNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Cluster> STANDORT = new AqlFieldImp<Cluster>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0008]", "standort", Cluster.class, this);

  public ListSelectAqlField<Cluster> VERANTWORTLICHE_ORGANISATIONSEINHEIT = new ListAqlFieldImp<Cluster>(VersorgungsaufenthaltAdminEntry.class, "/data[at0001]/items[at0013]", "verantwortlicheOrganisationseinheit", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(VersorgungsaufenthaltAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VersorgungsaufenthaltAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VersorgungsaufenthaltAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VersorgungsaufenthaltAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.hospitalization.v0");
  }

  public static VersorgungsaufenthaltAdminEntryContainment getInstance() {
    return new VersorgungsaufenthaltAdminEntryContainment();
  }
}
