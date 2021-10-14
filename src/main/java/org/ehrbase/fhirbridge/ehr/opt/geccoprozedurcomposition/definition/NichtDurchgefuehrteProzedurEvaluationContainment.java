package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class NichtDurchgefuehrteProzedurEvaluationContainment extends Containment {
  public SelectAqlField<NichtDurchgefuehrteProzedurEvaluation> NICHT_DURCHGEFUEHRTE_PROZEDUR_EVALUATION = new AqlFieldImp<NichtDurchgefuehrteProzedurEvaluation>(NichtDurchgefuehrteProzedurEvaluation.class, "", "NichtDurchgefuehrteProzedurEvaluation", NichtDurchgefuehrteProzedurEvaluation.class, this);

  public SelectAqlField<String> AUSSAGE_UEBER_DEN_AUSSCHLUSS_VALUE = new AqlFieldImp<String>(NichtDurchgefuehrteProzedurEvaluation.class, "/data[at0001]/items[at0002]/value|value", "aussageUeberDenAusschlussValue", String.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DEN_AUSSCHLUSS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(NichtDurchgefuehrteProzedurEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "aussageUeberDenAusschlussNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> EINGRIFF = new AqlFieldImp<DvCodedText>(NichtDurchgefuehrteProzedurEvaluation.class, "/data[at0001]/items[at0003]/value", "eingriff", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> EINGRIFF_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(NichtDurchgefuehrteProzedurEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "eingriffNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(NichtDurchgefuehrteProzedurEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(NichtDurchgefuehrteProzedurEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(NichtDurchgefuehrteProzedurEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(NichtDurchgefuehrteProzedurEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private NichtDurchgefuehrteProzedurEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static NichtDurchgefuehrteProzedurEvaluationContainment getInstance() {
    return new NichtDurchgefuehrteProzedurEvaluationContainment();
  }
}
