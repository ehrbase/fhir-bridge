package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Item;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.Long;
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

  public SelectAqlField<Long> ANZAHL_DER_ZURUECKGELEGTEN_SCHRITTE_MAGNITUDE = new AqlFieldImp<Long>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0016]/value|magnitude", "anzahlDerZurueckgelegtenSchritteMagnitude", Long.class, this);

  public SelectAqlField<NullFlavour> ANZAHL_DER_ZURUECKGELEGTEN_SCHRITTE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0016]/null_flavour|defining_code", "anzahlDerZurueckgelegtenSchritteNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> ZURUECKGELEGTE_DISTANZ_MAGNITUDE = new AqlFieldImp<Double>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|magnitude", "zurueckgelegteDistanzMagnitude", Double.class, this);

  public SelectAqlField<String> ZURUECKGELEGTE_DISTANZ_UNITS = new AqlFieldImp<String>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0017]/value|units", "zurueckgelegteDistanzUnits", String.class, this);

  public SelectAqlField<NullFlavour> ZURUECKGELEGTE_DISTANZ_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0017]/null_flavour|defining_code", "zurueckgelegteDistanzNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Long> ANZAHL_STOCKWERKE_MAGNITUDE = new AqlFieldImp<Long>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0020]/value|magnitude", "anzahlStockwerkeMagnitude", Long.class, this);

  public SelectAqlField<NullFlavour> ANZAHL_STOCKWERKE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0020]/null_flavour|defining_code", "anzahlStockwerkeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Item> HOEHE = new ListAqlFieldImp<Item>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0062]", "hoehe", Item.class, this);

  public SelectAqlField<Double> RUHEENERGIE_MAGNITUDE = new AqlFieldImp<Double>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0023]/value|magnitude", "ruheenergieMagnitude", Double.class, this);

  public SelectAqlField<String> RUHEENERGIE_UNITS = new AqlFieldImp<String>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0023]/value|units", "ruheenergieUnits", String.class, this);

  public SelectAqlField<NullFlavour> RUHEENERGIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0023]/null_flavour|defining_code", "ruheenergieNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Double> AKTIVITAETSENERGIE_MAGNITUDE = new AqlFieldImp<Double>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value|magnitude", "aktivitaetsenergieMagnitude", Double.class, this);

  public SelectAqlField<String> AKTIVITAETSENERGIE_UNITS = new AqlFieldImp<String>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0019]/value|units", "aktivitaetsenergieUnits", String.class, this);

  public SelectAqlField<NullFlavour> AKTIVITAETSENERGIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0019]/null_flavour|defining_code", "aktivitaetsenergieNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Item> GESCHWINDIGKEIT = new ListAqlFieldImp<Item>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0063]", "geschwindigkeit", Item.class, this);

  public ListSelectAqlField<Item> PACE = new ListAqlFieldImp<Item>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0064]", "pace", Item.class, this);

  public SelectAqlField<AktivitaetDefiningCode> AKTIVITAET_DEFINING_CODE = new AqlFieldImp<AktivitaetDefiningCode>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/state[at0027]/items[at0028]/value|defining_code", "aktivitaetDefiningCode", AktivitaetDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AKTIVITAET_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/state[at0027]/items[at0028]/null_flavour|defining_code", "aktivitaetNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> GERAET = new ListAqlFieldImp<Cluster>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0015]", "geraet", Cluster.class, this);

  public SelectAqlField<String> INFORMATIONEN_ZU_HARD_UND_SOFTWARE_VALUE = new AqlFieldImp<String>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0013]/value|value", "informationenZuHardUndSoftwareValue", String.class, this);

  public SelectAqlField<NullFlavour> INFORMATIONEN_ZU_HARD_UND_SOFTWARE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/protocol[at0011]/items[at0013]/null_flavour|defining_code", "informationenZuHardUndSoftwareNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MitSensorGemesseneKoerperlicheAktivitaetObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private MitSensorGemesseneKoerperlicheAktivitaetObservationContainment() {
    super("openEHR-EHR-OBSERVATION.wearable_sensor_activity.v0");
  }

  public static MitSensorGemesseneKoerperlicheAktivitaetObservationContainment getInstance() {
    return new MitSensorGemesseneKoerperlicheAktivitaetObservationContainment();
  }
}
