package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class MedizingeraetClusterContainment extends Containment {
  public SelectAqlField<MedizingeraetCluster> MEDIZINGERAET_CLUSTER = new AqlFieldImp<MedizingeraetCluster>(MedizingeraetCluster.class, "", "MedizingeraetCluster", MedizingeraetCluster.class, this);

  public SelectAqlField<DvCodedText> GERAETENAME = new AqlFieldImp<DvCodedText>(MedizingeraetCluster.class, "/items[at0001]/value", "geraetename", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> GERAETENAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MedizingeraetCluster.class, "/items[at0001]/null_flavour|defining_code", "geraetenameNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(MedizingeraetCluster.class, "/items[at0009]", "eigenschaften", Cluster.class, this);

  public ListSelectAqlField<Cluster> GERAETEVERWALTUNG = new ListAqlFieldImp<Cluster>(MedizingeraetCluster.class, "/items[at0019]", "geraeteverwaltung", Cluster.class, this);

  public ListSelectAqlField<Cluster> KOMPONENTEN = new ListAqlFieldImp<Cluster>(MedizingeraetCluster.class, "/items[at0018]", "komponenten", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(MedizingeraetCluster.class, "/items[at0026]", "erweiterung", Cluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(MedizingeraetCluster.class, "/items[at0027]", "multimedia", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MedizingeraetCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private MedizingeraetClusterContainment() {
    super("openEHR-EHR-CLUSTER.device.v1");
  }

  public static MedizingeraetClusterContainment getInstance() {
    return new MedizingeraetClusterContainment();
  }
}
