package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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

public class SekundaercodeEvaluationContainment extends Containment {
  public SelectAqlField<SekundaercodeEvaluation> SEKUNDAERCODE_EVALUATION = new AqlFieldImp<SekundaercodeEvaluation>(SekundaercodeEvaluation.class, "", "SekundaercodeEvaluation", SekundaercodeEvaluation.class, this);

  public SelectAqlField<DvCodedText> KODIERTE_DIAGNOSE = new AqlFieldImp<DvCodedText>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0002]/value", "kodierteDiagnose", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> KODIERTE_DIAGNOSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "kodierteDiagnoseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FREITEXTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0009]/value|value", "freitextbeschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> FREITEXTBESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0009]/null_flavour|defining_code", "freitextbeschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<PrimaercodeKoerperstelleElement> KOERPERSTELLE = new ListAqlFieldImp<PrimaercodeKoerperstelleElement>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0012]", "koerperstelle", PrimaercodeKoerperstelleElement.class, this);

  public ListSelectAqlField<PrimaercodeSeitenlokalisationElement> SEITENLOKALISATION = new ListAqlFieldImp<PrimaercodeSeitenlokalisationElement>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0012]", "seitenlokalisation", PrimaercodeSeitenlokalisationElement.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0039]", "anatomischeStelleStrukturiert", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> KLINISCH_RELEVANTER_ZEITRAUM_ZEITPUNKT_DES_AUFTRETENS_VALUE = new AqlFieldImp<TemporalAccessor>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0077]/value|value", "klinischRelevanterZeitraumZeitpunktDesAuftretensValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> KLINISCH_RELEVANTER_ZEITRAUM_ZEITPUNKT_DES_AUFTRETENS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0077]/null_flavour|defining_code", "klinischRelevanterZeitraumZeitpunktDesAuftretensNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> FESTSTELLUNGSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0003]/value|value", "feststellungsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> FESTSTELLUNGSDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "feststellungsdatumNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<LebensphaseCluster> LEBENSPHASE = new AqlFieldImp<LebensphaseCluster>(SekundaercodeEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.lebensphase.v0]", "lebensphase", LebensphaseCluster.class, this);

  public SelectAqlField<MehrfachkodierungskennzeichenIcd10GmCluster> MEHRFACHKODIERUNGSKENNZEICHEN_ICD10_GM = new AqlFieldImp<MehrfachkodierungskennzeichenIcd10GmCluster>(SekundaercodeEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1]", "mehrfachkodierungskennzeichenIcd10Gm", MehrfachkodierungskennzeichenIcd10GmCluster.class, this);

  public SelectAqlField<TemporalAccessor> KLINISCH_RELEVANTER_ZEITRAUM_ZEITPUNKT_DER_GENESUNG_VALUE = new AqlFieldImp<TemporalAccessor>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0030]/value|value", "klinischRelevanterZeitraumZeitpunktDerGenesungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> KLINISCH_RELEVANTER_ZEITRAUM_ZEITPUNKT_DER_GENESUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0030]/null_flavour|defining_code", "klinischRelevanterZeitraumZeitpunktDerGenesungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<KlinischerStatusCluster> KLINISCHER_STATUS = new AqlFieldImp<KlinischerStatusCluster>(SekundaercodeEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v2]", "klinischerStatus", KlinischerStatusCluster.class, this);

  public SelectAqlField<DvCodedText> DIAGNOSESICHERHEIT = new AqlFieldImp<DvCodedText>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0073]/value", "diagnosesicherheit", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> DIAGNOSESICHERHEIT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0073]/null_flavour|defining_code", "diagnosesicherheitNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> DIAGNOSEERLAEUTERUNG_VALUE = new AqlFieldImp<String>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0069]/value|value", "diagnoseerlaeuterungValue", String.class, this);

  public SelectAqlField<NullFlavour> DIAGNOSEERLAEUTERUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0069]/null_flavour|defining_code", "diagnoseerlaeuterungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> LETZTES_DOKUMENTATIONSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(SekundaercodeEvaluation.class, "/protocol[at0032]/items[at0070]/value|value", "letztesDokumentationsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> LETZTES_DOKUMENTATIONSDATUM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SekundaercodeEvaluation.class, "/protocol[at0032]/items[at0070]/null_flavour|defining_code", "letztesDokumentationsdatumNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(SekundaercodeEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(SekundaercodeEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SekundaercodeEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(SekundaercodeEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<PrimaercodeSchweregradNullFlavourChoice> SCHWEREGRAD_NULL_FLAVOUR = new AqlFieldImp<PrimaercodeSchweregradNullFlavourChoice>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0005]/null_flavour", "schweregradNullFlavour", PrimaercodeSchweregradNullFlavourChoice.class, this);

  public SelectAqlField<PrimaercodeSchweregradChoice> SCHWEREGRAD = new AqlFieldImp<PrimaercodeSchweregradChoice>(SekundaercodeEvaluation.class, "/data[at0001]/items[at0005]/value", "schweregrad", PrimaercodeSchweregradChoice.class, this);

  private SekundaercodeEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static SekundaercodeEvaluationContainment getInstance() {
    return new SekundaercodeEvaluationContainment();
  }
}
