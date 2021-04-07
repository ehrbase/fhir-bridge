package org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition;

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

public class ProzedurActionContainment extends Containment {
  public SelectAqlField<ProzedurAction> PROZEDUR_ACTION = new AqlFieldImp<ProzedurAction>(ProzedurAction.class, "", "ProzedurAction", ProzedurAction.class, this);

  public SelectAqlField<String> NAME_DER_PROZEDUR_VALUE = new AqlFieldImp<String>(ProzedurAction.class, "/description[at0001]/items[at0002]/value|value", "nameDerProzedurValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_DER_PROZEDUR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProzedurAction.class, "/description[at0001]/items[at0002]/null_flavour|defining_code", "nameDerProzedurNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FREITEXTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(ProzedurAction.class, "/description[at0001]/items[at0049]/value|value", "freitextbeschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> FREITEXTBESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProzedurAction.class, "/description[at0001]/items[at0049]/null_flavour|defining_code", "freitextbeschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<DetailsZurKoerperstelleCluster> DETAILS_ZUR_KOERPERSTELLE = new ListAqlFieldImp<DetailsZurKoerperstelleCluster>(ProzedurAction.class, "/description[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]", "detailsZurKoerperstelle", DetailsZurKoerperstelleCluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(ProzedurAction.class, "/description[at0001]/items[at0062]", "multimedia", Cluster.class, this);

  public ListSelectAqlField<ProzedurDurchfuehrungsabsichtElement> DURCHFUEHRUNGSABSICHT = new ListAqlFieldImp<ProzedurDurchfuehrungsabsichtElement>(ProzedurAction.class, "/description[at0001]/items[at0014]", "durchfuehrungsabsicht", ProzedurDurchfuehrungsabsichtElement.class, this);

  public SelectAqlField<Cluster> ANTRAGSTELLER = new AqlFieldImp<Cluster>(ProzedurAction.class, "/protocol[at0053]/items[at0055]", "antragsteller", Cluster.class, this);

  public ListSelectAqlField<Cluster> EMPFAENGER = new ListAqlFieldImp<Cluster>(ProzedurAction.class, "/protocol[at0053]/items[at0057]", "empfaenger", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(ProzedurAction.class, "/protocol[at0053]/items[at0064]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ProzedurAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ProzedurAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProzedurAction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(ProzedurAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<CareflowStepDefiningCode> CAREFLOW_STEP_DEFINING_CODE = new AqlFieldImp<CareflowStepDefiningCode>(ProzedurAction.class, "/ism_transition/careflow_step|defining_code", "careflowStepDefiningCode", CareflowStepDefiningCode.class, this);

  public SelectAqlField<CurrentStateDefiningCode> CURRENT_STATE_DEFINING_CODE = new AqlFieldImp<CurrentStateDefiningCode>(ProzedurAction.class, "/ism_transition/current_state|defining_code", "currentStateDefiningCode", CurrentStateDefiningCode.class, this);

  public SelectAqlField<Transition> TRANSITION_DEFINING_CODE = new AqlFieldImp<Transition>(ProzedurAction.class, "/ism_transition/transition|defining_code", "transitionDefiningCode", Transition.class, this);

  private ProzedurActionContainment() {
    super("openEHR-EHR-ACTION.procedure.v1");
  }

  public static ProzedurActionContainment getInstance() {
    return new ProzedurActionContainment();
  }
}
