package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AngabenZumTodClusterContainment extends Containment {
  public SelectAqlField<AngabenZumTodCluster> ANGABEN_ZUM_TOD_CLUSTER = new AqlFieldImp<AngabenZumTodCluster>(AngabenZumTodCluster.class, "", "AngabenZumTodCluster", AngabenZumTodCluster.class, this);

  public SelectAqlField<TemporalAccessor> STERBEDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(AngabenZumTodCluster.class, "/items[at0001]/value|value", "sterbedatumValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> STERBEDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AngabenZumTodCluster.class, "/items[at0001]/null_flavour|defining_code", "sterbedatumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> TODESDIAGNOSE = new AqlFieldImp<DvCodedText>(AngabenZumTodCluster.class, "/items[at0003]/value", "todesdiagnose", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> TODESDIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AngabenZumTodCluster.class, "/items[at0003]/null_flavour|defining_code", "todesdiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(AngabenZumTodCluster.class, "/items[at0005]", "spezifischeAngaben", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AngabenZumTodCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AngabenZumTodClusterContainment() {
    super("openEHR-EHR-CLUSTER.death_details.v1");
  }

  public static AngabenZumTodClusterContainment getInstance() {
    return new AngabenZumTodClusterContainment();
  }
}
