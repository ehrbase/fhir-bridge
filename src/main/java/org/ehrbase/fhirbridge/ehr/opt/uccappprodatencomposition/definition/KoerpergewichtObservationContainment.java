package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

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

public class KoerpergewichtObservationContainment extends Containment {
  public SelectAqlField<KoerpergewichtObservation> KOERPERGEWICHT_OBSERVATION = new AqlFieldImp<KoerpergewichtObservation>(KoerpergewichtObservation.class, "", "KoerpergewichtObservation", KoerpergewichtObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(KoerpergewichtObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(KoerpergewichtObservation.class, "/protocol[at0015]/items[at0020]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KoerpergewichtObservation.class, "/protocol[at0015]/items[at0027]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KoerpergewichtObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KoerpergewichtObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KoerpergewichtObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<KoerpergewichtBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<KoerpergewichtBeliebigesEreignisChoice>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]", "beliebigesEreignis", KoerpergewichtBeliebigesEreignisChoice.class, this);

  private KoerpergewichtObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_weight.v2");
  }

  public static KoerpergewichtObservationContainment getInstance() {
    return new KoerpergewichtObservationContainment();
  }
}
