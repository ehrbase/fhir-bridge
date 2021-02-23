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
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

public class EinwilligungserklaerungActionContainment extends Containment {
  public SelectAqlField<EinwilligungserklaerungAction> EINWILLIGUNGSERKLAERUNG_ACTION = new AqlFieldImp<EinwilligungserklaerungAction>(EinwilligungserklaerungAction.class, "", "EinwilligungserklaerungAction", EinwilligungserklaerungAction.class, this);

  public SelectAqlField<String> PROZEDUR_STUDIE_AKTIVITAET_VALUE = new AqlFieldImp<String>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0002]/value|value", "prozedurStudieAktivitaetValue", String.class, this);

  public SelectAqlField<NullFlavour> PROZEDUR_STUDIE_AKTIVITAET_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0002]/null_flavour|defining_code", "prozedurStudieAktivitaetNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> BESCHREIBUNG_DER_EINWILLIGUNG_VALUE = new AqlFieldImp<String>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0011]/value|value", "beschreibungDerEinwilligungValue", String.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_DER_EINWILLIGUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0011]/null_flavour|defining_code", "beschreibungDerEinwilligungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AdresseCluster> ADRESSE = new AqlFieldImp<AdresseCluster>(EinwilligungserklaerungAction.class, "/description[at0001]/items[openEHR-EHR-CLUSTER.address.v0]", "adresse", AdresseCluster.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0036]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0036]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANGABEN_ZUM_EINWILLIGUNGSNACHWEIS = new ListAqlFieldImp<Cluster>(EinwilligungserklaerungAction.class, "/description[at0001]/items[at0037]", "angabenZumEinwilligungsnachweis", Cluster.class, this);

  public ListSelectAqlField<Cluster> ANFORDERER_DER_EINWILLIGUNGSERKLAERUNG = new ListAqlFieldImp<Cluster>(EinwilligungserklaerungAction.class, "/protocol[at0024]/items[at0028]", "anfordererDerEinwilligungserklaerung", Cluster.class, this);

  public ListSelectAqlField<Cluster> PATIENT_EINWILLIGENDE_PERSON = new ListAqlFieldImp<Cluster>(EinwilligungserklaerungAction.class, "/protocol[at0024]/items[at0029]", "patientEinwilligendePerson", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(EinwilligungserklaerungAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(EinwilligungserklaerungAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(EinwilligungserklaerungAction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(EinwilligungserklaerungAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<CareflowStepDefiningCode> CAREFLOW_STEP_DEFINING_CODE = new AqlFieldImp<CareflowStepDefiningCode>(EinwilligungserklaerungAction.class, "/ism_transition/careflow_step|defining_code", "careflowStepDefiningCode", CareflowStepDefiningCode.class, this);

  public SelectAqlField<CurrentStateDefiningCode> CURRENT_STATE_DEFINING_CODE = new AqlFieldImp<CurrentStateDefiningCode>(EinwilligungserklaerungAction.class, "/ism_transition/current_state|defining_code", "currentStateDefiningCode", CurrentStateDefiningCode.class, this);

  public SelectAqlField<Transition> TRANSITION_DEFINING_CODE = new AqlFieldImp<Transition>(EinwilligungserklaerungAction.class, "/ism_transition/transition|defining_code", "transitionDefiningCode", Transition.class, this);

  private EinwilligungserklaerungActionContainment() {
    super("openEHR-EHR-ACTION.informed_consent.v0");
  }

  public static EinwilligungserklaerungActionContainment getInstance() {
    return new EinwilligungserklaerungActionContainment();
  }
}
