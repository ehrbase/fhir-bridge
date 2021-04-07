package org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class BeobachtungenAmBeatmungsgeraetObservationContainment extends Containment {
  public SelectAqlField<BeobachtungenAmBeatmungsgeraetObservation> BEOBACHTUNGEN_AM_BEATMUNGSGERAET_OBSERVATION = new AqlFieldImp<BeobachtungenAmBeatmungsgeraetObservation>(BeobachtungenAmBeatmungsgeraetObservation.class, "", "BeobachtungenAmBeatmungsgeraetObservation", BeobachtungenAmBeatmungsgeraetObservation.class, this);

  public SelectAqlField<EingeatmeterSauerstoffCluster> EINGEATMETER_SAUERSTOFF = new AqlFieldImp<EingeatmeterSauerstoffCluster>(BeobachtungenAmBeatmungsgeraetObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.inspired_oxygen.v1]", "eingeatmeterSauerstoff", EingeatmeterSauerstoffCluster.class, this);

  public SelectAqlField<Cluster> EINSTELLUNGEN_DES_BEATMUNGSGERAETES = new AqlFieldImp<Cluster>(BeobachtungenAmBeatmungsgeraetObservation.class, "/data[at0001]/events[at0002]/state[at0010]/items[at0011]", "einstellungenDesBeatmungsgeraetes", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BeobachtungenAmBeatmungsgeraetObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BeobachtungenAmBeatmungsgeraetObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(BeobachtungenAmBeatmungsgeraetObservation.class, "/protocol[at0012]/items[at0014]", "geraet", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BeobachtungenAmBeatmungsgeraetObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BeobachtungenAmBeatmungsgeraetObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BeobachtungenAmBeatmungsgeraetObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BeobachtungenAmBeatmungsgeraetObservationContainment() {
    super("openEHR-EHR-OBSERVATION.ventilator_vital_signs.v0");
  }

  public static BeobachtungenAmBeatmungsgeraetObservationContainment getInstance() {
    return new BeobachtungenAmBeatmungsgeraetObservationContainment();
  }
}
