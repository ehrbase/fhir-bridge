package org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class EingeatmeterSauerstoffClusterContainment extends Containment {
  public SelectAqlField<EingeatmeterSauerstoffCluster> EINGEATMETER_SAUERSTOFF_CLUSTER = new AqlFieldImp<EingeatmeterSauerstoffCluster>(EingeatmeterSauerstoffCluster.class, "", "EingeatmeterSauerstoffCluster", EingeatmeterSauerstoffCluster.class, this);

  public SelectAqlField<DvProportion> INSPIRATORISCHE_SAUERSTOFFFRAKTION = new AqlFieldImp<DvProportion>(EingeatmeterSauerstoffCluster.class, "/items[at0053]/value", "inspiratorischeSauerstofffraktion", DvProportion.class, this);

  public SelectAqlField<NullFlavour> INSPIRATORISCHE_SAUERSTOFFFRAKTION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EingeatmeterSauerstoffCluster.class, "/items[at0053]/null_flavour|defining_code", "inspiratorischeSauerstofffraktionNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS_ZUR_SAUERSTOFFZUFUHR = new ListAqlFieldImp<Cluster>(EingeatmeterSauerstoffCluster.class, "/items[at0058]", "detailsZurSauerstoffzufuhr", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(EingeatmeterSauerstoffCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EingeatmeterSauerstoffClusterContainment() {
    super("openEHR-EHR-CLUSTER.inspired_oxygen.v1");
  }

  public static EingeatmeterSauerstoffClusterContainment getInstance() {
    return new EingeatmeterSauerstoffClusterContainment();
  }
}
