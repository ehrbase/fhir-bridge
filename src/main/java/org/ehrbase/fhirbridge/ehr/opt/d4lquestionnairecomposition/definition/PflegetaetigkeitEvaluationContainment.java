package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PflegetaetigkeitEvaluationContainment extends Containment {
  public SelectAqlField<PflegetaetigkeitEvaluation> PFLEGETAETIGKEIT_EVALUATION = new AqlFieldImp<PflegetaetigkeitEvaluation>(PflegetaetigkeitEvaluation.class, "", "PflegetaetigkeitEvaluation", PflegetaetigkeitEvaluation.class, this);

  public SelectAqlField<Boolean> PRIVAT_VALUE = new AqlFieldImp<Boolean>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0020]/value|value", "privatValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> PRIVAT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0020]/null_flavour|defining_code", "privatNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> ANZAHL_DER_GEPFLEGTEN_PERSONEN_VALUE = new AqlFieldImp<String>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0005]/value|value", "anzahlDerGepflegtenPersonenValue", String.class, this);

  public SelectAqlField<NullFlavour> ANZAHL_DER_GEPFLEGTEN_PERSONEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "anzahlDerGepflegtenPersonenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FREQUENZ_DER_PFLEGE_VALUE = new AqlFieldImp<String>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0008]/value|value", "frequenzDerPflegeValue", String.class, this);

  public SelectAqlField<NullFlavour> FREQUENZ_DER_PFLEGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0008]/null_flavour|defining_code", "frequenzDerPflegeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<PflegetaetigkeitGrundFuerDieTaetigkeitElement> GRUND_FUER_DIE_TAETIGKEIT = new ListAqlFieldImp<PflegetaetigkeitGrundFuerDieTaetigkeitElement>(PflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0011]", "grundFuerDieTaetigkeit", PflegetaetigkeitGrundFuerDieTaetigkeitElement.class, this);

  public SelectAqlField<ItemTree> ITEM_TREE = new AqlFieldImp<ItemTree>(PflegetaetigkeitEvaluation.class, "/protocol[at0007]", "itemTree", ItemTree.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(PflegetaetigkeitEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PflegetaetigkeitEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PflegetaetigkeitEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PflegetaetigkeitEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.care_activity.v0");
  }

  public static PflegetaetigkeitEvaluationContainment getInstance() {
    return new PflegetaetigkeitEvaluationContainment();
  }
}
