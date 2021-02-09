package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.client.classgenerator.shareddefinition.Transition;

public class KontaktActionContainment extends Containment {
  public SelectAqlField<KontaktAction> KONTAKT_ACTION = new AqlFieldImp<KontaktAction>(KontaktAction.class, "", "KontaktAction", KontaktAction.class, this);

  public SelectAqlField<AelterOderGleich65JahreAltDefiningCode> KONTAKT_ZU_EINEM_BESTAETIGTEN_FALL_DEFINING_CODE = new AqlFieldImp<AelterOderGleich65JahreAltDefiningCode>(KontaktAction.class, "/description[at0001]/items[at0009]/value|defining_code", "kontaktZuEinemBestaetigtenFallDefiningCode", AelterOderGleich65JahreAltDefiningCode.class, this);

  public SelectAqlField<NullFlavour> KONTAKT_ZU_EINEM_BESTAETIGTEN_FALL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KontaktAction.class, "/description[at0001]/items[at0009]/null_flavour|defining_code", "kontaktZuEinemBestaetigtenFallNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_VALUE = new AqlFieldImp<TemporalAccessor>(KontaktAction.class, "/description[at0001]/items[at0006]/value|value", "beginnValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> BEGINN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KontaktAction.class, "/description[at0001]/items[at0006]/null_flavour|defining_code", "beginnNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ENDE_VALUE = new AqlFieldImp<TemporalAccessor>(KontaktAction.class, "/description[at0001]/items[at0016]/value|value", "endeValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ENDE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KontaktAction.class, "/description[at0001]/items[at0016]/null_flavour|defining_code", "endeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS_ZUM_KONTAKT = new ListAqlFieldImp<Cluster>(KontaktAction.class, "/description[at0001]/items[at0005]", "detailsZumKontakt", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KontaktAction.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KontaktAction.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KontaktAction.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KontaktAction.class, "/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<DvCodedText> CURRENT_STATE = new AqlFieldImp<DvCodedText>(KontaktAction.class, "/ism_transition/current_state", "currentState", DvCodedText.class, this);

  public SelectAqlField<Transition> TRANSITION_DEFINING_CODE = new AqlFieldImp<Transition>(KontaktAction.class, "/ism_transition/transition|defining_code", "transitionDefiningCode", Transition.class, this);

  private KontaktActionContainment() {
    super("openEHR-EHR-ACTION.contact.v0");
  }

  public static KontaktActionContainment getInstance() {
    return new KontaktActionContainment();
  }
}
