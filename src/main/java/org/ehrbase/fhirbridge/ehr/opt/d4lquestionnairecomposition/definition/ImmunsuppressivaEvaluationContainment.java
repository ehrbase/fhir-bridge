package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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

public class ImmunsuppressivaEvaluationContainment extends Containment {
  public SelectAqlField<ImmunsuppressivaEvaluation> IMMUNSUPPRESSIVA_EVALUATION = new AqlFieldImp<ImmunsuppressivaEvaluation>(ImmunsuppressivaEvaluation.class, "", "ImmunsuppressivaEvaluation", ImmunsuppressivaEvaluation.class, this);

  public SelectAqlField<String> NAME_DES_MEDIKAMENTS_VALUE = new AqlFieldImp<String>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0002]/value|value", "nameDesMedikamentsValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_DES_MEDIKAMENTS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "nameDesMedikamentsNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<StatusDefiningCode2> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode2>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0023]/value|defining_code", "statusDefiningCode", StatusDefiningCode2.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ImmunsuppressivaEvaluation.class, "/data[at0001]/items[at0023]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_DER_LETZTEN_AKTUALISIERUNG_VALUE = new AqlFieldImp<TemporalAccessor>(ImmunsuppressivaEvaluation.class, "/protocol[at0005]/items[at0006]/value|value", "datumDerLetztenAktualisierungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_DER_LETZTEN_AKTUALISIERUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ImmunsuppressivaEvaluation.class, "/protocol[at0005]/items[at0006]/null_flavour|defining_code", "datumDerLetztenAktualisierungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ImmunsuppressivaEvaluation.class, "/protocol[at0005]/items[at0019]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ImmunsuppressivaEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ImmunsuppressivaEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ImmunsuppressivaEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ImmunsuppressivaEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.medication_summary.v0");
  }

  public static ImmunsuppressivaEvaluationContainment getInstance() {
    return new ImmunsuppressivaEvaluationContainment();
  }
}
