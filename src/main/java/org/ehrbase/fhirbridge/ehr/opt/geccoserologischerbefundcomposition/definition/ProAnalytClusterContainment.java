package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProAnalytClusterContainment extends Containment {
  public SelectAqlField<ProAnalytCluster> PRO_ANALYT_CLUSTER = new AqlFieldImp<ProAnalytCluster>(ProAnalytCluster.class, "", "ProAnalytCluster", ProAnalytCluster.class, this);

  public SelectAqlField<VirusnachweistestDefiningCode> VIRUSNACHWEISTEST_DEFINING_CODE = new AqlFieldImp<VirusnachweistestDefiningCode>(ProAnalytCluster.class, "/items[at0024]/value|defining_code", "virusnachweistestDefiningCode", VirusnachweistestDefiningCode.class, this);

  public SelectAqlField<NullFlavour> VIRUSNACHWEISTEST_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAnalytCluster.class, "/items[at0024]/null_flavour|defining_code", "virusnachweistestNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NachweisDefiningCode> NACHWEIS_DEFINING_CODE = new AqlFieldImp<NachweisDefiningCode>(ProAnalytCluster.class, "/items[at0001]/value|defining_code", "nachweisDefiningCode", NachweisDefiningCode.class, this);

  public SelectAqlField<NullFlavour> NACHWEIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAnalytCluster.class, "/items[at0001]/null_flavour|defining_code", "nachweisNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> QUANTITATIVES_ERGEBNIS_MAGNITUDE = new AqlFieldImp<Double>(ProAnalytCluster.class, "/items[at0001]/value|magnitude", "quantitativesErgebnisMagnitude", Double.class, this);

  public SelectAqlField<String> QUANTITATIVES_ERGEBNIS_UNITS = new AqlFieldImp<String>(ProAnalytCluster.class, "/items[at0001]/value|units", "quantitativesErgebnisUnits", String.class, this);

  public SelectAqlField<NullFlavour> QUANTITATIVES_ERGEBNIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAnalytCluster.class, "/items[at0001]/null_flavour|defining_code", "quantitativesErgebnisNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAIL = new ListAqlFieldImp<Cluster>(ProAnalytCluster.class, "/items[at0014]", "analyseergebnisDetail", Cluster.class, this);

  public SelectAqlField<NullFlavour> TESTMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAnalytCluster.class, "/items[at0028]/null_flavour|defining_code", "testmethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ERGEBNIS_STATUS_VALUE = new AqlFieldImp<String>(ProAnalytCluster.class, "/items[at0005]/value|value", "ergebnisStatusValue", String.class, this);

  public SelectAqlField<NullFlavour> ERGEBNIS_STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProAnalytCluster.class, "/items[at0005]/null_flavour|defining_code", "ergebnisStatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProAnalytCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ProAnalytClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static ProAnalytClusterContainment getInstance() {
    return new ProAnalytClusterContainment();
  }
}
