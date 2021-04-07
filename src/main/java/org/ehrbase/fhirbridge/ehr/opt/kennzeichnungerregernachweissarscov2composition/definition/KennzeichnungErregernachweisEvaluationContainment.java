package org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class KennzeichnungErregernachweisEvaluationContainment extends Containment {
  public SelectAqlField<KennzeichnungErregernachweisEvaluation> KENNZEICHNUNG_ERREGERNACHWEIS_EVALUATION = new AqlFieldImp<KennzeichnungErregernachweisEvaluation>(KennzeichnungErregernachweisEvaluation.class, "", "KennzeichnungErregernachweisEvaluation", KennzeichnungErregernachweisEvaluation.class, this);

  public SelectAqlField<Boolean> ERREGERNACHWEIS_VALUE = new AqlFieldImp<Boolean>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0005]/value|value", "erregernachweisValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> ERREGERNACHWEIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "erregernachweisNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ErregernameDefiningCode> ERREGERNAME_DEFINING_CODE = new AqlFieldImp<ErregernameDefiningCode>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0012]/value|defining_code", "erregernameDefiningCode", ErregernameDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ERREGERNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0012]/null_flavour|defining_code", "erregernameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> HINWEISTEXT_VALUE = new AqlFieldImp<String>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0013]/value|value", "hinweistextValue", String.class, this);

  public SelectAqlField<NullFlavour> HINWEISTEXT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0013]/null_flavour|defining_code", "hinweistextNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_DER_KENNZEICHNUNG_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0015]/value|value", "zeitpunktDerKennzeichnungValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_DER_KENNZEICHNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0015]/null_flavour|defining_code", "zeitpunktDerKennzeichnungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> ERREGERNACHWEIS_IN_DER_KLINIK_VALUE = new AqlFieldImp<Boolean>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0011]/value|value", "erregernachweisInDerKlinikValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> ERREGERNACHWEIS_IN_DER_KLINIK_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/data[at0001]/items[at0011]/null_flavour|defining_code", "erregernachweisInDerKlinikNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE = new AqlFieldImp<TemporalAccessor>(KennzeichnungErregernachweisEvaluation.class, "/protocol[at0003]/items[at0004]/value|value", "zuletztAktualisiertValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZULETZT_AKTUALISIERT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KennzeichnungErregernachweisEvaluation.class, "/protocol[at0003]/items[at0004]/null_flavour|defining_code", "zuletztAktualisiertNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KennzeichnungErregernachweisEvaluation.class, "/protocol[at0003]/items[at0007]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KennzeichnungErregernachweisEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KennzeichnungErregernachweisEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KennzeichnungErregernachweisEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KennzeichnungErregernachweisEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.flag_pathogen.v0");
  }

  public static KennzeichnungErregernachweisEvaluationContainment getInstance() {
    return new KennzeichnungErregernachweisEvaluationContainment();
  }
}
