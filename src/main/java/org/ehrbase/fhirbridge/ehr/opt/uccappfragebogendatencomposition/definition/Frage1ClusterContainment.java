package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class Frage1ClusterContainment extends Containment {
  public SelectAqlField<Frage1Cluster> FRAGE1_CLUSTER = new AqlFieldImp<Frage1Cluster>(Frage1Cluster.class, "", "Frage1Cluster", Frage1Cluster.class, this);

  public SelectAqlField<DvIdentifier> ELEMENT_ID = new AqlFieldImp<DvIdentifier>(Frage1Cluster.class, "/items[at0002]/value", "elementId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> ELEMENT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage1Cluster.class, "/items[at0002]/null_flavour|defining_code", "elementIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FRAGE_VALUE = new AqlFieldImp<String>(Frage1Cluster.class, "/items[at0001]/value|value", "frageValue", String.class, this);

  public SelectAqlField<NullFlavour> FRAGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage1Cluster.class, "/items[at0001]/null_flavour|defining_code", "frageNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ANTWORT_VALUE = new AqlFieldImp<String>(Frage1Cluster.class, "/items[at0003]/value|value", "antwortValue", String.class, this);

  public SelectAqlField<NullFlavour> ANTWORT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage1Cluster.class, "/items[at0003]/null_flavour|defining_code", "antwortNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> SCORE_MAGNITUDE = new AqlFieldImp<Double>(Frage1Cluster.class, "/items[at0004]/value|magnitude", "scoreMagnitude", Double.class, this);

  public SelectAqlField<String> SCORE_UNITS = new AqlFieldImp<String>(Frage1Cluster.class, "/items[at0004]/value|units", "scoreUnits", String.class, this);

  public SelectAqlField<NullFlavour> SCORE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage1Cluster.class, "/items[at0004]/null_flavour|defining_code", "scoreNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Frage1aCluster> FRAGE1A = new AqlFieldImp<Frage1aCluster>(Frage1Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage1a", Frage1aCluster.class, this);

  public SelectAqlField<Frage1bCluster> FRAGE1B = new AqlFieldImp<Frage1bCluster>(Frage1Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage1b", Frage1bCluster.class, this);

  public SelectAqlField<Frage1cCluster> FRAGE1C = new AqlFieldImp<Frage1cCluster>(Frage1Cluster.class, "/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "frage1c", Frage1cCluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(Frage1Cluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private Frage1ClusterContainment() {
    super("openEHR-EHR-CLUSTER.questionnaire_item.v0");
  }

  public static Frage1ClusterContainment getInstance() {
    return new Frage1ClusterContainment();
  }
}
