package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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

public class ZusammenfassungRauchverhaltenEvaluationContainment extends Containment {
  public SelectAqlField<ZusammenfassungRauchverhaltenEvaluation> ZUSAMMENFASSUNG_RAUCHVERHALTEN_EVALUATION = new AqlFieldImp<ZusammenfassungRauchverhaltenEvaluation>(ZusammenfassungRauchverhaltenEvaluation.class, "", "ZusammenfassungRauchverhaltenEvaluation", ZusammenfassungRauchverhaltenEvaluation.class, this);

  public SelectAqlField<RaucherDefiningCode> RAUCHER_DEFINING_CODE = new AqlFieldImp<RaucherDefiningCode>(ZusammenfassungRauchverhaltenEvaluation.class, "/data[at0001]/items[at0089]/value|defining_code", "raucherDefiningCode", RaucherDefiningCode.class, this);

  public SelectAqlField<NullFlavour> RAUCHER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ZusammenfassungRauchverhaltenEvaluation.class, "/data[at0001]/items[at0089]/null_flavour|defining_code", "raucherNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ALLGEMEINE_DETAILS = new ListAqlFieldImp<Cluster>(ZusammenfassungRauchverhaltenEvaluation.class, "/data[at0001]/items[at0086]", "allgemeineDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ZusammenfassungRauchverhaltenEvaluation.class, "/protocol[at0021]/items[at0073]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ZusammenfassungRauchverhaltenEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ZusammenfassungRauchverhaltenEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ZusammenfassungRauchverhaltenEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ZusammenfassungRauchverhaltenEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1");
  }

  public static ZusammenfassungRauchverhaltenEvaluationContainment getInstance() {
    return new ZusammenfassungRauchverhaltenEvaluationContainment();
  }
}
