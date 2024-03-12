package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PersonendatenAdminEntryContainment extends Containment {
  public SelectAqlField<PersonendatenAdminEntry> PERSONENDATEN_ADMIN_ENTRY = new AqlFieldImp<PersonendatenAdminEntry>(PersonendatenAdminEntry.class, "", "PersonendatenAdminEntry", PersonendatenAdminEntry.class, this);

  public SelectAqlField<PersonCluster> PERSON = new AqlFieldImp<PersonCluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.person.v1]", "person", PersonCluster.class, this);

  public SelectAqlField<DatenZurGeburtCluster> DATEN_ZUR_GEBURT = new AqlFieldImp<DatenZurGeburtCluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.person_birth_data_iso.v0]", "datenZurGeburt", DatenZurGeburtCluster.class, this);

  public SelectAqlField<Boolean> VERSTORBEN_VALUE = new AqlFieldImp<Boolean>(PersonendatenAdminEntry.class, "/data[at0001]/items[at0024]/items[at0025]/value|value", "verstorbenValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> VERSTORBEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonendatenAdminEntry.class, "/data[at0001]/items[at0024]/items[at0025]/null_flavour|defining_code", "verstorbenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AngabenZumTodCluster> ANGABEN_ZUM_TOD = new AqlFieldImp<AngabenZumTodCluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[at0024]/items[openEHR-EHR-CLUSTER.death_details.v1]", "angabenZumTod", AngabenZumTodCluster.class, this);

  public ListSelectAqlField<Cluster> ADRESSE = new ListAqlFieldImp<Cluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[at0005]", "adresse", Cluster.class, this);

  public SelectAqlField<KontaktpersonCluster> KONTAKTPERSON = new AqlFieldImp<KontaktpersonCluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.person.v1]", "kontaktperson", KontaktpersonCluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNGEN = new ListAqlFieldImp<Cluster>(PersonendatenAdminEntry.class, "/data[at0001]/items[at0007]", "erweiterungen", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(PersonendatenAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PersonendatenAdminEntry.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PersonendatenAdminEntry.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PersonendatenAdminEntryContainment() {
    super("openEHR-EHR-ADMIN_ENTRY.person_data.v0");
  }

  public static PersonendatenAdminEntryContainment getInstance() {
    return new PersonendatenAdminEntryContainment();
  }
}
