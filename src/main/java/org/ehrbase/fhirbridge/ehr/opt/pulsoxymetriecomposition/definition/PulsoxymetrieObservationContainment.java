package org.ehrbase.fhirbridge.ehr.opt.pulsoxymetriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class PulsoxymetrieObservationContainment extends Containment {
  public SelectAqlField<PulsoxymetrieObservation> PULSOXYMETRIE_OBSERVATION = new AqlFieldImp<PulsoxymetrieObservation>(PulsoxymetrieObservation.class, "", "PulsoxymetrieObservation", PulsoxymetrieObservation.class, this);

  public SelectAqlField<DvProportion> SPO = new AqlFieldImp<DvProportion>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value", "spo", DvProportion.class, this);

  public SelectAqlField<NullFlavour> SPO_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/null_flavour|defining_code", "spoNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> WELLENFORM = new ListAqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0054]", "wellenform", Cluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_BILD = new ListAqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0060]", "multimediaBild", Cluster.class, this);

  public SelectAqlField<Cluster> ANSTRENGUNG = new AqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/state[at0014]/items[at0034]", "anstrengung", Cluster.class, this);

  public SelectAqlField<Cluster> EINGEATMETER_SAUERSTOFF = new AqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/state[at0014]/items[at0015]", "eingeatmeterSauerstoff", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(PulsoxymetrieObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(PulsoxymetrieObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> OXYMETRIE_GERAET = new AqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/protocol[at0007]/items[at0018]", "oxymetrieGeraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(PulsoxymetrieObservation.class, "/protocol[at0007]/items[at0059]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(PulsoxymetrieObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(PulsoxymetrieObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PulsoxymetrieObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PulsoxymetrieObservationContainment() {
    super("openEHR-EHR-OBSERVATION.pulse_oximetry.v1");
  }

  public static PulsoxymetrieObservationContainment getInstance() {
    return new PulsoxymetrieObservationContainment();
  }
}
