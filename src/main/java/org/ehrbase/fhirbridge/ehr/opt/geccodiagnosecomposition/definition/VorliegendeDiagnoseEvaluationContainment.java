package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;

public class VorliegendeDiagnoseEvaluationContainment extends Containment {
  public SelectAqlField<VorliegendeDiagnoseEvaluation> VORLIEGENDE_DIAGNOSE_EVALUATION = new AqlFieldImp<VorliegendeDiagnoseEvaluation>(VorliegendeDiagnoseEvaluation.class, "", "VorliegendeDiagnoseEvaluation", VorliegendeDiagnoseEvaluation.class, this);

  public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DES_AUFTRETENS_DER_ERSTDIAGNOSE_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0077]/value|value", "datumZeitpunktDesAuftretensDerErstdiagnoseValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(VorliegendeDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public ListSelectAqlField<KorperstelleCluster> KORPERSTELLE = new ListAqlFieldImp<KorperstelleCluster>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1 and name/value='Körperstelle']", "korperstelle", KorperstelleCluster.class, this);

  public SelectAqlField<SchweregradDefiningcode> SCHWEREGRAD_DEFININGCODE = new AqlFieldImp<SchweregradDefiningcode>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "schweregradDefiningcode", SchweregradDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_ZEITPUNKT_DER_GENESUNG_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0030]/value|value", "datumZeitpunktDerGenesungValue", TemporalAccessor.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0069]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesProblemsDerDiagnoseValue", String.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VorliegendeDiagnoseEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(VorliegendeDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(VorliegendeDiagnoseEvaluation.class, "/data[at0001]/items[at0043]", "spezifischeAngaben", Cluster.class, this);

  private VorliegendeDiagnoseEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static VorliegendeDiagnoseEvaluationContainment getInstance() {
    return new VorliegendeDiagnoseEvaluationContainment();
  }
}
