package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class GesamtstatusClusterContainment extends Containment {
  public SelectAqlField<GesamtstatusCluster> GESAMTSTATUS_CLUSTER = new AqlFieldImp<GesamtstatusCluster>(GesamtstatusCluster.class, "", "GesamtstatusCluster", GesamtstatusCluster.class, this);

  public SelectAqlField<DvIdentifier> ELEMENT_ID = new AqlFieldImp<DvIdentifier>(GesamtstatusCluster.class, "/items[at0002]/value", "elementId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> ELEMENT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GesamtstatusCluster.class, "/items[at0002]/null_flavour|defining_code", "elementIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FRAGE_VALUE = new AqlFieldImp<String>(GesamtstatusCluster.class, "/items[at0001]/value|value", "frageValue", String.class, this);

  public SelectAqlField<NullFlavour> FRAGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GesamtstatusCluster.class, "/items[at0001]/null_flavour|defining_code", "frageNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ANTWORT_VALUE = new AqlFieldImp<String>(GesamtstatusCluster.class, "/items[at0003]/value|value", "antwortValue", String.class, this);

  public SelectAqlField<NullFlavour> ANTWORT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GesamtstatusCluster.class, "/items[at0003]/null_flavour|defining_code", "antwortNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> SCORE_MAGNITUDE = new AqlFieldImp<Double>(GesamtstatusCluster.class, "/items[at0004]/value|magnitude", "scoreMagnitude", Double.class, this);

  public SelectAqlField<String> SCORE_UNITS = new AqlFieldImp<String>(GesamtstatusCluster.class, "/items[at0004]/value|units", "scoreUnits", String.class, this);

  public SelectAqlField<NullFlavour> SCORE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GesamtstatusCluster.class, "/items[at0004]/null_flavour|defining_code", "scoreNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Frage1Cluster> FRAGE1 = new AqlFieldImp<Frage1Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage1", Frage1Cluster.class, this);

  public SelectAqlField<Frage2Cluster> FRAGE2 = new AqlFieldImp<Frage2Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage2", Frage2Cluster.class, this);

  public SelectAqlField<Frage3Cluster> FRAGE3 = new AqlFieldImp<Frage3Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage3", Frage3Cluster.class, this);

  public SelectAqlField<Frage4Cluster> FRAGE4 = new AqlFieldImp<Frage4Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage4", Frage4Cluster.class, this);

  public SelectAqlField<Frage5Cluster> FRAGE5 = new AqlFieldImp<Frage5Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage5", Frage5Cluster.class, this);

  public SelectAqlField<Frage6Cluster> FRAGE6 = new AqlFieldImp<Frage6Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage6", Frage6Cluster.class, this);

  public SelectAqlField<Frage7Cluster> FRAGE7 = new AqlFieldImp<Frage7Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage7", Frage7Cluster.class, this);

  public SelectAqlField<Frage8Cluster> FRAGE8 = new AqlFieldImp<Frage8Cluster>(GesamtstatusCluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage8", Frage8Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GesamtstatusCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GesamtstatusClusterContainment() {
    super("openEHR-EHR-CLUSTER.questionnaire_item.v0");
  }

  public static GesamtstatusClusterContainment getInstance() {
    return new GesamtstatusClusterContainment();
  }
}
