package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class UnbekanntesSymptomEvaluationContainment extends Containment {
  public SelectAqlField<UnbekanntesSymptomEvaluation> UNBEKANNTES_SYMPTOM_EVALUATION = new AqlFieldImp<UnbekanntesSymptomEvaluation>(UnbekanntesSymptomEvaluation.class, "", "UnbekanntesSymptomEvaluation", UnbekanntesSymptomEvaluation.class, this);

  public SelectAqlField<DvCodedText> UNBEKANNTES_SYMPTOM = new AqlFieldImp<DvCodedText>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0002]/value", "unbekanntesSymptom", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> UNBEKANNTES_SYMPTOM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "unbekanntesSymptomNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<UnbekanntesSymptomAussageUeberDieFehlendeInformationElement> AUSSAGE_UEBER_DIE_FEHLENDE_INFORMATION = new ListAqlFieldImp<UnbekanntesSymptomAussageUeberDieFehlendeInformationElement>(UnbekanntesSymptomEvaluation.class, "/data[at0001]/items[at0005]", "aussageUeberDieFehlendeInformation", UnbekanntesSymptomAussageUeberDieFehlendeInformationElement.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(UnbekanntesSymptomEvaluation.class, "/protocol[at0003]/items[at0006]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(UnbekanntesSymptomEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(UnbekanntesSymptomEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(UnbekanntesSymptomEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private UnbekanntesSymptomEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.absence.v2");
  }

  public static UnbekanntesSymptomEvaluationContainment getInstance() {
    return new UnbekanntesSymptomEvaluationContainment();
  }
}
