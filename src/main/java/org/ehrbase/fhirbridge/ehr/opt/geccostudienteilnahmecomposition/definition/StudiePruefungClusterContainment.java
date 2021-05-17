package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class StudiePruefungClusterContainment extends Containment {
  public SelectAqlField<StudiePruefungCluster> STUDIE_PRUEFUNG_CLUSTER = new AqlFieldImp<StudiePruefungCluster>(StudiePruefungCluster.class, "", "StudiePruefungCluster", StudiePruefungCluster.class, this);

  public SelectAqlField<TitelDerStudiePruefungDefiningCode> TITEL_DER_STUDIE_PRUEFUNG_DEFINING_CODE = new AqlFieldImp<TitelDerStudiePruefungDefiningCode>(StudiePruefungCluster.class, "/items[at0001]/value|defining_code", "titelDerStudiePruefungDefiningCode", TitelDerStudiePruefungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> TITEL_DER_STUDIE_PRUEFUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StudiePruefungCluster.class, "/items[at0001]/null_flavour|defining_code", "titelDerStudiePruefungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> BESCHREIBUNG_VALUE = new AqlFieldImp<String>(StudiePruefungCluster.class, "/items[at0004]/value|value", "beschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StudiePruefungCluster.class, "/items[at0004]/null_flavour|defining_code", "beschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<StudiePruefungRegistrierungCluster> REGISTRIERUNG = new ListAqlFieldImp<StudiePruefungRegistrierungCluster>(StudiePruefungCluster.class, "/items[at0033]", "registrierung", StudiePruefungRegistrierungCluster.class, this);

  public ListSelectAqlField<Cluster> STUDIENZENTRUM = new ListAqlFieldImp<Cluster>(StudiePruefungCluster.class, "/items[at0023]", "studienzentrum", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(StudiePruefungCluster.class, "/items[at0014]", "zusaetzlicheDetails", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StudiePruefungCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StudiePruefungClusterContainment() {
    super("openEHR-EHR-CLUSTER.study_details.v1");
  }

  public static StudiePruefungClusterContainment getInstance() {
    return new StudiePruefungClusterContainment();
  }
}
