package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class KontaktpersonClusterContainment extends Containment {
  public SelectAqlField<KontaktpersonCluster> KONTAKTPERSON_CLUSTER = new AqlFieldImp<KontaktpersonCluster>(KontaktpersonCluster.class, "", "KontaktpersonCluster", KontaktpersonCluster.class, this);

  public SelectAqlField<String> NAME_VALUE = new AqlFieldImp<String>(KontaktpersonCluster.class, "/items[at0001]/value|value", "nameValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KontaktpersonCluster.class, "/items[at0001]/null_flavour|defining_code", "nameNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> NAME_STRUKTURIERT = new ListAqlFieldImp<Cluster>(KontaktpersonCluster.class, "/items[at0002]", "nameStrukturiert", Cluster.class, this);

  public ListSelectAqlField<KontaktpersonRolleRelationshipElement> ROLLE_RELATIONSHIP = new ListAqlFieldImp<KontaktpersonRolleRelationshipElement>(KontaktpersonCluster.class, "/items[at0004]", "rolleRelationship", KontaktpersonRolleRelationshipElement.class, this);

  public SelectAqlField<AdresseCluster> ADRESSE = new AqlFieldImp<AdresseCluster>(KontaktpersonCluster.class, "/items[openEHR-EHR-CLUSTER.address.v1]", "adresse", AdresseCluster.class, this);

  public ListSelectAqlField<ElektronischeKommunikationCluster> ELEKTRONISCHE_KOMMUNIKATION = new ListAqlFieldImp<ElektronischeKommunikationCluster>(KontaktpersonCluster.class, "/items[openEHR-EHR-CLUSTER.electronic_communication.v1]", "elektronischeKommunikation", ElektronischeKommunikationCluster.class, this);

  public SelectAqlField<OrganisationCluster> ORGANISATION = new AqlFieldImp<OrganisationCluster>(KontaktpersonCluster.class, "/items[openEHR-EHR-CLUSTER.organisation.v1]", "organisation", OrganisationCluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(KontaktpersonCluster.class, "/items[at0008]", "zusaetzlicheAngaben", Cluster.class, this);

  public ListSelectAqlField<Cluster> FOTO = new ListAqlFieldImp<Cluster>(KontaktpersonCluster.class, "/items[at0009]", "foto", Cluster.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(KontaktpersonCluster.class, "/items[at0010]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KontaktpersonCluster.class, "/items[at0010]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KontaktpersonCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KontaktpersonClusterContainment() {
    super("openEHR-EHR-CLUSTER.person.v1");
  }

  public static KontaktpersonClusterContainment getInstance() {
    return new KontaktpersonClusterContainment();
  }
}
