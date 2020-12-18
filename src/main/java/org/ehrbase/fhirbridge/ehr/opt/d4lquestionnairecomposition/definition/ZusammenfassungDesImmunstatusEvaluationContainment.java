package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ZusammenfassungDesImmunstatusEvaluationContainment extends Containment {
  public SelectAqlField<ZusammenfassungDesImmunstatusEvaluation> ZUSAMMENFASSUNG_DES_IMMUNSTATUS_EVALUATION = new AqlFieldImp<ZusammenfassungDesImmunstatusEvaluation>(ZusammenfassungDesImmunstatusEvaluation.class, "", "ZusammenfassungDesImmunstatusEvaluation", ZusammenfassungDesImmunstatusEvaluation.class, this);

  public ListSelectAqlField<ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement> INFEKTIONSKRANKHEIT_ODER_ERREGER = new ListAqlFieldImp<ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0002]", "infektionskrankheitOderErreger", ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_DER_LETZTEN_AUFFRISCHUNGSIMPFUNG_VALUE = new AqlFieldImp<TemporalAccessor>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0009]/value|value", "datumDerLetztenAuffrischungsimpfungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_DER_LETZTEN_AUFFRISCHUNGSIMPFUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0009]/null_flavour|defining_code", "datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ImmunsstatusDefiningCode> IMMUNSSTATUS_DEFINING_CODE = new AqlFieldImp<ImmunsstatusDefiningCode>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0010]/value|defining_code", "immunsstatusDefiningCode", ImmunsstatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> IMMUNSSTATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0010]/null_flavour|defining_code", "immunsstatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AelterOderGleich65JahreAltDefiningCode> HABEN_SIE_SICH_IM_ZEITRAUM_VOM1_AUGUST2020_BIS_HEUTE_GEGEN_GRIPPE_IMPFEN_LASSEN_DEFINING_CODE = new AqlFieldImp<AelterOderGleich65JahreAltDefiningCode>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0016]/value|defining_code", "habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode", AelterOderGleich65JahreAltDefiningCode.class, this);

  public SelectAqlField<NullFlavour> HABEN_SIE_SICH_IM_ZEITRAUM_VOM1_AUGUST2020_BIS_HEUTE_GEGEN_GRIPPE_IMPFEN_LASSEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ZusammenfassungDesImmunstatusEvaluation.class, "/data[at0001]/items[at0016]/null_flavour|defining_code", "habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(ZusammenfassungDesImmunstatusEvaluation.class, "/protocol[at0013]", "tree", ItemTree.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ZusammenfassungDesImmunstatusEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ZusammenfassungDesImmunstatusEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ZusammenfassungDesImmunstatusEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ZusammenfassungDesImmunstatusEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.immunisation_summary.v0");
  }

  public static ZusammenfassungDesImmunstatusEvaluationContainment getInstance() {
    return new ZusammenfassungDesImmunstatusEvaluationContainment();
  }
}
