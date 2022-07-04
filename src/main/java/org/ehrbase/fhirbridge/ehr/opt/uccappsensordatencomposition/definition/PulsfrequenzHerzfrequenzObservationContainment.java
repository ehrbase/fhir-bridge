package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PulsfrequenzHerzfrequenzObservationContainment extends Containment {
  public SelectAqlField<PulsfrequenzHerzfrequenzObservation> PULSFREQUENZ_HERZFREQUENZ_OBSERVATION = new AqlFieldImp<PulsfrequenzHerzfrequenzObservation>(PulsfrequenzHerzfrequenzObservation.class, "", "PulsfrequenzHerzfrequenzObservation", PulsfrequenzHerzfrequenzObservation.class, this);

  public SelectAqlField<PulsfrequenzHerzfrequenzRuhepulsEvent> RUHEPULS = new AqlFieldImp<PulsfrequenzHerzfrequenzRuhepulsEvent>(PulsfrequenzHerzfrequenzObservation.class, "/data[at0002]/events[at0003]", "ruhepuls", PulsfrequenzHerzfrequenzRuhepulsEvent.class, this);

  public SelectAqlField<PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent> MITTLERE_HERZFREQUENZ = new AqlFieldImp<PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent>(PulsfrequenzHerzfrequenzObservation.class, "/data[at0002]/events[at0003]", "mittlereHerzfrequenz", PulsfrequenzHerzfrequenzMittlereHerzfrequenzEvent.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(PulsfrequenzHerzfrequenzObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<MethodeDefiningCode> METHODE_DEFINING_CODE = new AqlFieldImp<MethodeDefiningCode>(PulsfrequenzHerzfrequenzObservation.class, "/protocol[at0010]/items[at1019]/value|defining_code", "methodeDefiningCode", MethodeDefiningCode.class, this);

  public SelectAqlField<NullFlavour> METHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PulsfrequenzHerzfrequenzObservation.class, "/protocol[at0010]/items[at1019]/null_flavour|defining_code", "methodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(PulsfrequenzHerzfrequenzObservation.class, "/protocol[at0010]/items[at1013]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(PulsfrequenzHerzfrequenzObservation.class, "/protocol[at0010]/items[at1056]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(PulsfrequenzHerzfrequenzObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PulsfrequenzHerzfrequenzObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PulsfrequenzHerzfrequenzObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice> MOMENTANE_HERZFREQUENZ = new ListAqlFieldImp<PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice>(PulsfrequenzHerzfrequenzObservation.class, "/data[at0002]/events[at0003]", "momentaneHerzfrequenz", PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice.class, this);

  private PulsfrequenzHerzfrequenzObservationContainment() {
    super("openEHR-EHR-OBSERVATION.pulse.v2");
  }

  public static PulsfrequenzHerzfrequenzObservationContainment getInstance() {
    return new PulsfrequenzHerzfrequenzObservationContainment();
  }
}
