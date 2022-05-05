package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

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

public class MitSensorGemesseneKoerperlicheAktivitaetObservationContainment extends Containment {
  public SelectAqlField<MitSensorGemesseneKoerperlicheAktivitaetObservation> MIT_SENSOR_GEMESSENE_KOERPERLICHE_AKTIVITAET_OBSERVATION = new AqlFieldImp<MitSensorGemesseneKoerperlicheAktivitaetObservation>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "", "MitSensorGemesseneKoerperlicheAktivitaetObservation", MitSensorGemesseneKoerperlicheAktivitaetObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> GERAET = new ListAqlFieldImp<Cluster>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0015]", "geraet", Cluster.class, this);

  public SelectAqlField<String> INFORMATIONEN_ZU_HARD_UND_SOFTWARE_VALUE = new AqlFieldImp<String>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0013]/value|value", "informationenZuHardUndSoftwareValue", String.class, this);

  public SelectAqlField<NullFlavour> INFORMATIONEN_ZU_HARD_UND_SOFTWARE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0013]/null_flavour|defining_code", "informationenZuHardUndSoftwareNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> JEDES_EREIGNIS = new ListAqlFieldImp<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]", "jedesEreignis", MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice.class, this);

  private MitSensorGemesseneKoerperlicheAktivitaetObservationContainment() {
    super("openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0");
  }

  public static MitSensorGemesseneKoerperlicheAktivitaetObservationContainment getInstance() {
    return new MitSensorGemesseneKoerperlicheAktivitaetObservationContainment();
  }
}
