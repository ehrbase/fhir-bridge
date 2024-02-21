package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PersonClusterContainment extends Containment {
  public SelectAqlField<PersonCluster> PERSON_CLUSTER = new AqlFieldImp<PersonCluster>(PersonCluster.class, "", "PersonCluster", PersonCluster.class, this);

  public SelectAqlField<NameCluster> NAME = new AqlFieldImp<NameCluster>(PersonCluster.class, "/items[openEHR-EHR-CLUSTER.structured_name.v1]", "name", NameCluster.class, this);

  public SelectAqlField<GeburtsnameCluster> GEBURTSNAME = new AqlFieldImp<GeburtsnameCluster>(PersonCluster.class, "/items[openEHR-EHR-CLUSTER.structured_name.v1]", "geburtsname", GeburtsnameCluster.class, this);

  public SelectAqlField<DvIdentifier> VERSICHERTEN_ID_GKV = new AqlFieldImp<DvIdentifier>(PersonCluster.class, "/items[at0003]/value", "versichertenIdGkv", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> VERSICHERTEN_ID_GKV_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonCluster.class, "/items[at0003]/null_flavour|defining_code", "versichertenIdGkvNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<PersonPidElement> PID = new ListAqlFieldImp<PersonPidElement>(PersonCluster.class, "/items[at0003]", "pid", PersonPidElement.class, this);

  public SelectAqlField<DvIdentifier> VERSICHERUNGSNUMMER_PKV = new AqlFieldImp<DvIdentifier>(PersonCluster.class, "/items[at0003]/value", "versicherungsnummerPkv", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> VERSICHERUNGSNUMMER_PKV_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PersonCluster.class, "/items[at0003]/null_flavour|defining_code", "versicherungsnummerPkvNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<StrassenanschriftCluster> STRASSENANSCHRIFT = new ListAqlFieldImp<StrassenanschriftCluster>(PersonCluster.class, "/items[openEHR-EHR-CLUSTER.address.v1]", "strassenanschrift", StrassenanschriftCluster.class, this);

  public SelectAqlField<PostfachCluster> POSTFACH = new AqlFieldImp<PostfachCluster>(PersonCluster.class, "/items[openEHR-EHR-CLUSTER.address.v1]", "postfach", PostfachCluster.class, this);

  public ListSelectAqlField<Cluster> ELEKTRONISCHE_KOMMUNIKATION = new ListAqlFieldImp<Cluster>(PersonCluster.class, "/items[at0006]", "elektronischeKommunikation", Cluster.class, this);

  public SelectAqlField<VerwaltungsorganisationCluster> VERWALTUNGSORGANISATION = new AqlFieldImp<VerwaltungsorganisationCluster>(PersonCluster.class, "/items[openEHR-EHR-CLUSTER.organisation.v1]", "verwaltungsorganisation", VerwaltungsorganisationCluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(PersonCluster.class, "/items[at0008]", "zusaetzlicheAngaben", Cluster.class, this);

  public ListSelectAqlField<Cluster> FOTO = new ListAqlFieldImp<Cluster>(PersonCluster.class, "/items[at0009]", "foto", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PersonCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PersonClusterContainment() {
    super("openEHR-EHR-CLUSTER.person.v1");
  }

  public static PersonClusterContainment getInstance() {
    return new PersonClusterContainment();
  }
}
