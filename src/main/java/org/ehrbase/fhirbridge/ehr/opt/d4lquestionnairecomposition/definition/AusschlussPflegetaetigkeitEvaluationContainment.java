package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AusschlussPflegetaetigkeitEvaluationContainment extends Containment {
  public SelectAqlField<AusschlussPflegetaetigkeitEvaluation> AUSSCHLUSS_PFLEGETAETIGKEIT_EVALUATION = new AqlFieldImp<AusschlussPflegetaetigkeitEvaluation>(AusschlussPflegetaetigkeitEvaluation.class, "", "AusschlussPflegetaetigkeitEvaluation", AusschlussPflegetaetigkeitEvaluation.class, this);

  public SelectAqlField<String> AUSSAGE_UEBER_DEN_AUSSCHLUSS_VALUE = new AqlFieldImp<String>(AusschlussPflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0002]/value|value", "aussageUeberDenAusschlussValue", String.class, this);

  public SelectAqlField<NullFlavour> AUSSAGE_UEBER_DEN_AUSSCHLUSS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusschlussPflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "aussageUeberDenAusschlussNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> AUSGESCHLOSSENE_KATEGORIE_VALUE = new AqlFieldImp<String>(AusschlussPflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0003]/value|value", "ausgeschlosseneKategorieValue", String.class, this);

  public SelectAqlField<NullFlavour> AUSGESCHLOSSENE_KATEGORIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AusschlussPflegetaetigkeitEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "ausgeschlosseneKategorieNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AusschlussPflegetaetigkeitEvaluation.class, "/protocol[at0009]/items[at0011]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AusschlussPflegetaetigkeitEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AusschlussPflegetaetigkeitEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AusschlussPflegetaetigkeitEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AusschlussPflegetaetigkeitEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.exclusion_specific.v1");
  }

  public static AusschlussPflegetaetigkeitEvaluationContainment getInstance() {
    return new AusschlussPflegetaetigkeitEvaluationContainment();
  }
}
