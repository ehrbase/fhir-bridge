package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class StudienteilnahmeClusterContainment extends Containment {
  public SelectAqlField<StudienteilnahmeCluster> STUDIENTEILNAHME_CLUSTER = new AqlFieldImp<StudienteilnahmeCluster>(StudienteilnahmeCluster.class, "", "StudienteilnahmeCluster", StudienteilnahmeCluster.class, this);

  public SelectAqlField<StudiePruefungCluster> STUDIE_PRUEFUNG = new AqlFieldImp<StudiePruefungCluster>(StudienteilnahmeCluster.class, "/items[openEHR-EHR-CLUSTER.study_details.v1]", "studiePruefung", StudiePruefungCluster.class, this);

  public ListSelectAqlField<Cluster> STUDIENZENTRUM = new ListAqlFieldImp<Cluster>(StudienteilnahmeCluster.class, "/items[at0015]", "studienzentrum", Cluster.class, this);

  public SelectAqlField<DvCodedText> BESTAETIGTE_COVID19_DIAGNOSE_ALS_HAUPTURSACHE_FUER_AUFNAHME_IN_STUDIE = new AqlFieldImp<DvCodedText>(StudienteilnahmeCluster.class, "/items[at0014]/value", "bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> BESTAETIGTE_COVID19_DIAGNOSE_ALS_HAUPTURSACHE_FUER_AUFNAHME_IN_STUDIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StudienteilnahmeCluster.class, "/items[at0014]/null_flavour|defining_code", "bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StudienteilnahmeCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StudienteilnahmeClusterContainment() {
    super("openEHR-EHR-CLUSTER.study_participation.v1");
  }

  public static StudienteilnahmeClusterContainment getInstance() {
    return new StudienteilnahmeClusterContainment();
  }
}
