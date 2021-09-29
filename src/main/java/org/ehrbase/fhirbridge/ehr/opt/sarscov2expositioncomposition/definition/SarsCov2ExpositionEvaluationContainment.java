package org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition;

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

public class SarsCov2ExpositionEvaluationContainment extends Containment {
  public SelectAqlField<SarsCov2ExpositionEvaluation> SARS_COV2_EXPOSITION_EVALUATION = new AqlFieldImp<SarsCov2ExpositionEvaluation>(SarsCov2ExpositionEvaluation.class, "", "SarsCov2ExpositionEvaluation", SarsCov2ExpositionEvaluation.class, this);

  public SelectAqlField<String> INFEKTIONSERREGER_VALUE = new AqlFieldImp<String>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0002]/value|value", "infektionserregerValue", String.class, this);

  public SelectAqlField<NullFlavour> INFEKTIONSERREGER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0002]/null_flavour|defining_code", "infektionserregerNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> EXPOSITION_VORHANDEN = new AqlFieldImp<DvCodedText>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0003]/value", "expositionVorhanden", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> EXPOSITION_VORHANDEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0003]/null_flavour|defining_code", "expositionVorhandenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> BESCHREIBUNG_DER_EXPOSITION_VALUE = new AqlFieldImp<String>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0006]/value|value", "beschreibungDerExpositionValue", String.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_DER_EXPOSITION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0006]/null_flavour|defining_code", "beschreibungDerExpositionNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DER_EXPOSITION_VALUE = new AqlFieldImp<TemporalAccessor>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0007]/value|value", "datumUhrzeitDerExpositionValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_UHRZEIT_DER_EXPOSITION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0007]/null_flavour|defining_code", "datumUhrzeitDerExpositionNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_ANATOMISCHEN_STELLE = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0019]", "angabenZurAnatomischenStelle", Cluster.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ANGABEN_ZUM_ORT = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0026]", "strukturierteAngabenZumOrt", Cluster.class, this);

  public ListSelectAqlField<Cluster> PERSOENLICHE_SCHUTZAUSRUESTUNG = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0022]", "persoenlicheSchutzausruestung", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_PERSON_ALS_QUELLE = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0015]", "angabenZurPersonAlsQuelle", Cluster.class, this);

  public ListSelectAqlField<Cluster> EINFLUSSFAKTOREN = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0005]/items[at0016]", "einflussfaktoren", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUR_EXPONIERTEN_PERSON = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/data[at0001]/items[at0017]", "angabenZurExponiertenPerson", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(SarsCov2ExpositionEvaluation.class, "/protocol[at0031]/items[at0032]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(SarsCov2ExpositionEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SarsCov2ExpositionEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(SarsCov2ExpositionEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SarsCov2ExpositionEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.infectious_exposure.v0");
  }

  public static SarsCov2ExpositionEvaluationContainment getInstance() {
    return new SarsCov2ExpositionEvaluationContainment();
  }
}
