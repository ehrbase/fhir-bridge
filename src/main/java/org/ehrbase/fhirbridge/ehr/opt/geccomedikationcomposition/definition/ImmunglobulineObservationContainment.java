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

public class ImmunglobulineObservationContainment extends Containment {
  public SelectAqlField<ImmunglobulineObservation> IMMUNGLOBULINE_OBSERVATION = new AqlFieldImp<ImmunglobulineObservation>(ImmunglobulineObservation.class, "", "ImmunglobulineObservation", ImmunglobulineObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(ImmunglobulineObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<StatusCluster> STATUS = new AqlFieldImp<StatusCluster>(ImmunglobulineObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0]", "status", StatusCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(ImmunglobulineObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(ImmunglobulineObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ImmunglobulineObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<ImmunglobulineBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<ImmunglobulineBeliebigesEreignisChoice>(ImmunglobulineObservation.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", ImmunglobulineBeliebigesEreignisChoice.class, this);

  private ImmunglobulineObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_statement.v0");
  }

  public static ImmunglobulineObservationContainment getInstance() {
    return new ImmunglobulineObservationContainment();
  }
}
