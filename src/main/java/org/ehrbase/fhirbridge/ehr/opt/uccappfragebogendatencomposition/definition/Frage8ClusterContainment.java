package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class Frage8ClusterContainment extends Containment {
  public SelectAqlField<Frage8Cluster> FRAGE8_CLUSTER = new AqlFieldImp<Frage8Cluster>(Frage8Cluster.class, "", "Frage8Cluster", Frage8Cluster.class, this);

  public SelectAqlField<DvIdentifier> ELEMENT_ID = new AqlFieldImp<DvIdentifier>(Frage8Cluster.class, "/items[at0002]/value", "elementId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> ELEMENT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage8Cluster.class, "/items[at0002]/null_flavour|defining_code", "elementIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FRAGE_VALUE = new AqlFieldImp<String>(Frage8Cluster.class, "/items[at0001]/value|value", "frageValue", String.class, this);

  public SelectAqlField<NullFlavour> FRAGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage8Cluster.class, "/items[at0001]/null_flavour|defining_code", "frageNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ANTWORT_VALUE = new AqlFieldImp<String>(Frage8Cluster.class, "/items[at0003]/value|value", "antwortValue", String.class, this);

  public SelectAqlField<NullFlavour> ANTWORT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage8Cluster.class, "/items[at0003]/null_flavour|defining_code", "antwortNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> SCORE_MAGNITUDE = new AqlFieldImp<Double>(Frage8Cluster.class, "/items[at0004]/value|magnitude", "scoreMagnitude", Double.class, this);

  public SelectAqlField<String> SCORE_UNITS = new AqlFieldImp<String>(Frage8Cluster.class, "/items[at0004]/value|units", "scoreUnits", String.class, this);

  public SelectAqlField<NullFlavour> SCORE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage8Cluster.class, "/items[at0004]/null_flavour|defining_code", "scoreNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Frage8aCluster> FRAGE8A = new AqlFieldImp<Frage8aCluster>(Frage8Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage8a", Frage8aCluster.class, this);

  public SelectAqlField<Frage8bCluster> FRAGE8B = new AqlFieldImp<Frage8bCluster>(Frage8Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage8b", Frage8bCluster.class, this);

  public SelectAqlField<Frage8cCluster> FRAGE8C = new AqlFieldImp<Frage8cCluster>(Frage8Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage8c", Frage8cCluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(Frage8Cluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private Frage8ClusterContainment() {
    super("openEHR-EHR-CLUSTER.questionnaire_item.v0");
  }

  public static Frage8ClusterContainment getInstance() {
    return new Frage8ClusterContainment();
  }
}
