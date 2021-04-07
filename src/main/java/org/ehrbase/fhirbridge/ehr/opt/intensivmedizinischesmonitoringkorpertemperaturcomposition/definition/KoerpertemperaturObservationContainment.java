package org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

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

public class KoerpertemperaturObservationContainment extends Containment {
  public SelectAqlField<KoerpertemperaturObservation> KOERPERTEMPERATUR_OBSERVATION = new AqlFieldImp<KoerpertemperaturObservation>(KoerpertemperaturObservation.class, "", "KoerpertemperaturObservation", KoerpertemperaturObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(KoerpertemperaturObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> LOKALISATION_DER_MESSUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0021]/null_flavour|defining_code", "lokalisationDerMessungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_LOKALISATION_DER_MESSUNG = new ListAqlFieldImp<Cluster>(KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0064]", "strukturierteLokalisationDerMessung", Cluster.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0059]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0062]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KoerpertemperaturObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KoerpertemperaturObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KoerpertemperaturObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<KoerpertemperaturLokalisationDerMessungChoice> LOKALISATION_DER_MESSUNG = new AqlFieldImp<KoerpertemperaturLokalisationDerMessungChoice>(KoerpertemperaturObservation.class, "/protocol[at0020]/items[at0021]/value", "lokalisationDerMessung", KoerpertemperaturLokalisationDerMessungChoice.class, this);

  public ListSelectAqlField<KoerpertemperaturBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<KoerpertemperaturBeliebigesEreignisChoice>(KoerpertemperaturObservation.class, "/data[at0002]/events[at0003]", "beliebigesEreignis", KoerpertemperaturBeliebigesEreignisChoice.class, this);

  private KoerpertemperaturObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_temperature.v2");
  }

  public static KoerpertemperaturObservationContainment getInstance() {
    return new KoerpertemperaturObservationContainment();
  }
}
