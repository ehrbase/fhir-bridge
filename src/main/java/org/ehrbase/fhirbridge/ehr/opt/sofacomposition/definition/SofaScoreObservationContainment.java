package org.ehrbase.fhirbridge.ehr.opt.sofacomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Long;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class SofaScoreObservationContainment extends Containment {
  public SelectAqlField<SofaScoreObservation> SOFA_SCORE_OBSERVATION = new AqlFieldImp<SofaScoreObservation>(SofaScoreObservation.class, "", "SofaScoreObservation", SofaScoreObservation.class, this);

  public SelectAqlField<DvOrdinal> RESPIRATION = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value", "respiration", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> RESPIRATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code", "respirationNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvOrdinal> KARDIOVASKULAERES_SYSTEM = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0010]/value", "kardiovaskulaeresSystem", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> KARDIOVASKULAERES_SYSTEM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0010]/null_flavour|defining_code", "kardiovaskulaeresSystemNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvOrdinal> ZENTRALES_NERVENSYSTEM = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0016]/value", "zentralesNervensystem", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> ZENTRALES_NERVENSYSTEM_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0016]/null_flavour|defining_code", "zentralesNervensystemNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvOrdinal> NIERENFUNKTION = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/value", "nierenfunktion", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> NIERENFUNKTION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]/null_flavour|defining_code", "nierenfunktionNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvOrdinal> LEBERFUNKTION = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value", "leberfunktion", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> LEBERFUNKTION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0028]/null_flavour|defining_code", "leberfunktionNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvOrdinal> BLUTGERINNUNG = new AqlFieldImp<DvOrdinal>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value", "blutgerinnung", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> BLUTGERINNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0034]/null_flavour|defining_code", "blutgerinnungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Long> GESAMTERGEBNIS_MAGNITUDE = new AqlFieldImp<Long>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0041]/value|magnitude", "gesamtergebnisMagnitude", Long.class, this);

  public SelectAqlField<NullFlavour> GESAMTERGEBNIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0041]/null_flavour|defining_code", "gesamtergebnisNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(SofaScoreObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(SofaScoreObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(SofaScoreObservation.class, "/protocol[at0043]/items[at0044]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(SofaScoreObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(SofaScoreObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(SofaScoreObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private SofaScoreObservationContainment() {
    super("openEHR-EHR-OBSERVATION.sofa_score.v0");
  }

  public static SofaScoreObservationContainment getInstance() {
    return new SofaScoreObservationContainment();
  }
}
