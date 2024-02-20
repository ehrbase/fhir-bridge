package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProAntibiotikumClusterContainment extends Containment {
  public SelectAqlField<ProAntibiotikumCluster> PRO_ANTIBIOTIKUM_CLUSTER = new AqlFieldImp<ProAntibiotikumCluster>(ProAntibiotikumCluster.class, "", "ProAntibiotikumCluster", ProAntibiotikumCluster.class, this);

  public SelectAqlField<DvCodedText> ANTIBIOTIKUM = new AqlFieldImp<DvCodedText>(ProAntibiotikumCluster.class, "/items[at0024]/value", "antibiotikum", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ANTIBIOTIKUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAntibiotikumCluster.class, "/items[at0024]/null_flavour|defining_code", "antibiotikumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> MINIMALE_HEMMKONZENTRATION_MAGNITUDE = new AqlFieldImp<Double>(ProAntibiotikumCluster.class, "/items[at0001]/value|magnitude", "minimaleHemmkonzentrationMagnitude", Double.class, this);

  public SelectAqlField<String> MINIMALE_HEMMKONZENTRATION_UNITS = new AqlFieldImp<String>(ProAntibiotikumCluster.class, "/items[at0001]/value|units", "minimaleHemmkonzentrationUnits", String.class, this);

  public SelectAqlField<NullFlavour> MINIMALE_HEMMKONZENTRATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAntibiotikumCluster.class, "/items[at0001]/null_flavour|defining_code", "minimaleHemmkonzentrationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(ProAntibiotikumCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  public SelectAqlField<DvCodedText> RESISTENZ = new AqlFieldImp<DvCodedText>(ProAntibiotikumCluster.class, "/items[at0004]/value", "resistenz", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> RESISTENZ_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAntibiotikumCluster.class, "/items[at0004]/null_flavour|defining_code", "resistenzNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> TESTMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAntibiotikumCluster.class, "/items[at0028]/null_flavour|defining_code", "testmethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProAntibiotikumCluster.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAntibiotikumCluster.class, "/items[at0003]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProAntibiotikumCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProAntibiotikumTestmethodeChoice> TESTMETHODE = new AqlFieldImp<ProAntibiotikumTestmethodeChoice>(ProAntibiotikumCluster.class, "/items[at0028]/value", "testmethode", ProAntibiotikumTestmethodeChoice.class, this);

  private ProAntibiotikumClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static ProAntibiotikumClusterContainment getInstance() {
    return new ProAntibiotikumClusterContainment();
  }
}
