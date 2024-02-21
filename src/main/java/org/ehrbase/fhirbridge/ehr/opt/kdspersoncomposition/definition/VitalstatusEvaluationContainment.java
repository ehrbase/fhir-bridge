package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class VitalstatusEvaluationContainment extends Containment {
  public SelectAqlField<VitalstatusEvaluation> VITALSTATUS_EVALUATION = new AqlFieldImp<VitalstatusEvaluation>(VitalstatusEvaluation.class, "", "VitalstatusEvaluation", VitalstatusEvaluation.class, this);

  public SelectAqlField<String> VITALSTATUS_VALUE = new AqlFieldImp<String>(VitalstatusEvaluation.class, "/data[at0001]/items[at0006]/value|value", "vitalstatusValue", String.class, this);

  public SelectAqlField<NullFlavour> VITALSTATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VitalstatusEvaluation.class, "/data[at0001]/items[at0006]/null_flavour|defining_code", "vitalstatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FhirStatusDerBeobachtungCluster> FHIR_STATUS_DER_BEOBACHTUNG = new AqlFieldImp<FhirStatusDerBeobachtungCluster>(VitalstatusEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.observation_status_fhir.v1]", "fhirStatusDerBeobachtung", FhirStatusDerBeobachtungCluster.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_FESTSTELLUNG_VALUE = new AqlFieldImp<TemporalAccessor>(VitalstatusEvaluation.class, "/protocol[at0002]/items[at0018]/value|value", "zeitpunktDerFeststellungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DER_FESTSTELLUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VitalstatusEvaluation.class, "/protocol[at0002]/items[at0018]/null_flavour|defining_code", "zeitpunktDerFeststellungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(VitalstatusEvaluation.class, "/protocol[at0002]/items[at0021]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(VitalstatusEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VitalstatusEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VitalstatusEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VitalstatusEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.vital_status.v1");
  }

  public static VitalstatusEvaluationContainment getInstance() {
    return new VitalstatusEvaluationContainment();
  }
}
