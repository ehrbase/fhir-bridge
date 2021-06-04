package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class FachlicheOrganisationseinheitClusterContainment extends Containment {
  public SelectAqlField<FachlicheOrganisationseinheitCluster> FACHLICHE_ORGANISATIONSEINHEIT_CLUSTER = new AqlFieldImp<FachlicheOrganisationseinheitCluster>(FachlicheOrganisationseinheitCluster.class, "", "FachlicheOrganisationseinheitCluster", FachlicheOrganisationseinheitCluster.class, this);

  public SelectAqlField<String> TYP_VALUE = new AqlFieldImp<String>(FachlicheOrganisationseinheitCluster.class, "/items[at0051]/value|value", "typValue", String.class, this);

  public SelectAqlField<NullFlavour> TYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FachlicheOrganisationseinheitCluster.class, "/items[at0051]/null_flavour|defining_code", "typNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FachabteilungsschluesselDefiningCode> FACHABTEILUNGSSCHLUESSEL_DEFINING_CODE = new AqlFieldImp<FachabteilungsschluesselDefiningCode>(FachlicheOrganisationseinheitCluster.class, "/items[at0024]/value|defining_code", "fachabteilungsschluesselDefiningCode", FachabteilungsschluesselDefiningCode.class, this);

  public SelectAqlField<NullFlavour> FACHABTEILUNGSSCHLUESSEL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FachlicheOrganisationseinheitCluster.class, "/items[at0024]/null_flavour|defining_code", "fachabteilungsschluesselNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> NAME_VALUE = new AqlFieldImp<String>(FachlicheOrganisationseinheitCluster.class, "/items[at0052]/value|value", "nameValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FachlicheOrganisationseinheitCluster.class, "/items[at0052]/null_flavour|defining_code", "nameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> AKTIV_INAKTIV_VALUE = new AqlFieldImp<Boolean>(FachlicheOrganisationseinheitCluster.class, "/items[at0050]/value|value", "aktivInaktivValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> AKTIV_INAKTIV_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FachlicheOrganisationseinheitCluster.class, "/items[at0050]/null_flavour|defining_code", "aktivInaktivNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ZUSAETZLICHE_BESCHREIBUNG_VALUE = new AqlFieldImp<String>(FachlicheOrganisationseinheitCluster.class, "/items[at0046]/value|value", "zusaetzlicheBeschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> ZUSAETZLICHE_BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FachlicheOrganisationseinheitCluster.class, "/items[at0046]/null_flavour|defining_code", "zusaetzlicheBeschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(FachlicheOrganisationseinheitCluster.class, "/items[at0047]", "details", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(FachlicheOrganisationseinheitCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private FachlicheOrganisationseinheitClusterContainment() {
    super("openEHR-EHR-CLUSTER.organization.v0");
  }

  public static FachlicheOrganisationseinheitClusterContainment getInstance() {
    return new FachlicheOrganisationseinheitClusterContainment();
  }
}
