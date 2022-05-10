package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class Frage3ClusterContainment extends Containment {
  public SelectAqlField<Frage3Cluster> FRAGE3_CLUSTER = new AqlFieldImp<Frage3Cluster>(Frage3Cluster.class, "", "Frage3Cluster", Frage3Cluster.class, this);

  public SelectAqlField<DvIdentifier> ELEMENT_ID = new AqlFieldImp<DvIdentifier>(Frage3Cluster.class, "/items[at0002]/value", "elementId", DvIdentifier.class, this);

  public SelectAqlField<NullFlavour> ELEMENT_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage3Cluster.class, "/items[at0002]/null_flavour|defining_code", "elementIdNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FRAGE_VALUE = new AqlFieldImp<String>(Frage3Cluster.class, "/items[at0001]/value|value", "frageValue", String.class, this);

  public SelectAqlField<NullFlavour> FRAGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage3Cluster.class, "/items[at0001]/null_flavour|defining_code", "frageNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AntwortDefiningCode3> ANTWORT_DEFINING_CODE = new AqlFieldImp<AntwortDefiningCode3>(Frage3Cluster.class, "/items[at0003]/value|defining_code", "antwortDefiningCode", AntwortDefiningCode3.class, this);

  public SelectAqlField<NullFlavour> ANTWORT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage3Cluster.class, "/items[at0003]/null_flavour|defining_code", "antwortNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> SCORE_MAGNITUDE = new AqlFieldImp<Double>(Frage3Cluster.class, "/items[at0004]/value|magnitude", "scoreMagnitude", Double.class, this);

  public SelectAqlField<String> SCORE_UNITS = new AqlFieldImp<String>(Frage3Cluster.class, "/items[at0004]/value|units", "scoreUnits", String.class, this);

  public SelectAqlField<NullFlavour> SCORE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Frage3Cluster.class, "/items[at0004]/null_flavour|defining_code", "scoreNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS_ZUM_FRAGEBOGENELEMENT = new ListAqlFieldImp<Cluster>(Frage3Cluster.class, "/items[at0005]", "detailsZumFragebogenelement", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(Frage3Cluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private Frage3ClusterContainment() {
    super("openEHR-EHR-CLUSTER.questionnaire_item.v0");
  }

  public static Frage3ClusterContainment getInstance() {
    return new Frage3ClusterContainment();
  }
}
