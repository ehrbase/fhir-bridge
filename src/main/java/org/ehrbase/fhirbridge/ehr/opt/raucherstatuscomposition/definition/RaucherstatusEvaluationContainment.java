package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class RaucherstatusEvaluationContainment extends Containment {
  public SelectAqlField<RaucherstatusEvaluation> RAUCHERSTATUS_EVALUATION = new AqlFieldImp<RaucherstatusEvaluation>(RaucherstatusEvaluation.class, "", "RaucherstatusEvaluation", RaucherstatusEvaluation.class, this);

  public SelectAqlField<RauchverhaltenDefiningCode> RAUCHVERHALTEN_DEFINING_CODE = new AqlFieldImp<RauchverhaltenDefiningCode>(RaucherstatusEvaluation.class, "/data[at0001]/items[at0043]/value|defining_code", "rauchverhaltenDefiningCode", RauchverhaltenDefiningCode.class, this);

  public SelectAqlField<NullFlavour> RAUCHVERHALTEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(RaucherstatusEvaluation.class, "/data[at0001]/items[at0043]/null_flavour|defining_code", "rauchverhaltenNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ALLGEMEINE_DETAILS = new ListAqlFieldImp<Cluster>(RaucherstatusEvaluation.class, "/data[at0001]/items[at0086]", "allgemeineDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(RaucherstatusEvaluation.class, "/protocol[at0021]/items[at0073]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(RaucherstatusEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(RaucherstatusEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(RaucherstatusEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private RaucherstatusEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1");
  }

  public static RaucherstatusEvaluationContainment getInstance() {
    return new RaucherstatusEvaluationContainment();
  }
}
