package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class AntikoagulanzienObservationContainment extends Containment {
  public SelectAqlField<AntikoagulanzienObservation> ANTIKOAGULANZIEN_OBSERVATION = new AqlFieldImp<AntikoagulanzienObservation>(AntikoagulanzienObservation.class, "", "AntikoagulanzienObservation", AntikoagulanzienObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AntikoagulanzienObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<StatusCluster> STATUS = new AqlFieldImp<StatusCluster>(AntikoagulanzienObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0]", "status", StatusCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AntikoagulanzienObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AntikoagulanzienObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AntikoagulanzienObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<AntikoagulanzienBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<AntikoagulanzienBeliebigesEreignisChoice>(AntikoagulanzienObservation.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", AntikoagulanzienBeliebigesEreignisChoice.class, this);

  private AntikoagulanzienObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_statement.v0");
  }

  public static AntikoagulanzienObservationContainment getInstance() {
    return new AntikoagulanzienObservationContainment();
  }
}
