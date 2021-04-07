package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

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

public class ProblemDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE_EVALUATION = new AqlFieldImp<ProblemDiagnoseEvaluation>(ProblemDiagnoseEvaluation.class, "", "ProblemDiagnoseEvaluation", ProblemDiagnoseEvaluation.class, this);

  public SelectAqlField<NameDesProblemsDerDiagnoseDefiningCode> NAME_DES_PROBLEMS_DER_DIAGNOSE_DEFINING_CODE = new AqlFieldImp<NameDesProblemsDerDiagnoseDefiningCode>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "nameDesProblemsDerDiagnoseDefiningCode", NameDesProblemsDerDiagnoseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> NAME_DES_PROBLEMS_DER_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "nameDesProblemsDerDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FREITEXTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0009]/value|value", "freitextbeschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> FREITEXTBESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0009]/null_flavour|defining_code", "freitextbeschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> LOKALISATION_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0012]/value|value", "lokalisationValue", String.class, this);

  public SelectAqlField<NullFlavour> LOKALISATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0012]/null_flavour|defining_code", "lokalisationNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<AnatomischeLokalisationCluster> ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<AnatomischeLokalisationCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]", "anatomischeLokalisation", AnatomischeLokalisationCluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_DES_AUFTRETENS_DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "datumDesAuftretensDerErstdiagnoseValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_DES_AUFTRETENS_DER_ERSTDIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/null_flavour|defining_code", "datumDesAuftretensDerErstdiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> FESTSTELLUNGSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/value|value", "feststellungsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> FESTSTELLUNGSDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "feststellungsdatumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> SCHWEREGRAD_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "schweregradNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS = new AqlFieldImp<DiagnosedetailsCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]", "diagnosedetails", DiagnosedetailsCluster.class, this);

  public SelectAqlField<AetiopathogeneseCluster> AETIOPATHOGENESE = new AqlFieldImp<AetiopathogeneseCluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]", "aetiopathogenese", AetiopathogeneseCluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DER_GENESUNG_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0030]/value|value", "datumZeitpunktDerGenesungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_ZEITPUNKT_DER_GENESUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0030]/null_flavour|defining_code", "datumZeitpunktDerGenesungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public SelectAqlField<NullFlavour> DIAGNOSTISCHE_SICHERHEIT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0073]/null_flavour|defining_code", "diagnostischeSicherheitNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> DIAGNOSEERLAEUTERUNG_VALUE = new AqlFieldImp<String>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0069]/value|value", "diagnoseerlaeuterungValue", String.class, this);

  public SelectAqlField<NullFlavour> DIAGNOSEERLAEUTERUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0069]/null_flavour|defining_code", "diagnoseerlaeuterungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> LETZTES_DOKUMENTATIONSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0070]/value|value", "letztesDokumentationsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> LETZTES_DOKUMENTATIONSDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0070]/null_flavour|defining_code", "letztesDokumentationsdatumNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ProblemDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ProblemDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProblemDiagnoseEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProblemDiagnoseDiagnostischeSicherheitChoice> DIAGNOSTISCHE_SICHERHEIT = new AqlFieldImp<ProblemDiagnoseDiagnostischeSicherheitChoice>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0073]/value", "diagnostischeSicherheit", ProblemDiagnoseDiagnostischeSicherheitChoice.class, this);

  public SelectAqlField<ProblemDiagnoseSchweregradChoice> SCHWEREGRAD = new AqlFieldImp<ProblemDiagnoseSchweregradChoice>(ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/value", "schweregrad", ProblemDiagnoseSchweregradChoice.class, this);

  private ProblemDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static ProblemDiagnoseEvaluationContainment getInstance() {
    return new ProblemDiagnoseEvaluationContainment();
  }
}
