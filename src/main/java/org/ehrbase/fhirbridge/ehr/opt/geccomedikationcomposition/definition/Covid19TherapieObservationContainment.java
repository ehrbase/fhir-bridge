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

public class Covid19TherapieObservationContainment extends Containment {
  public SelectAqlField<Covid19TherapieObservation> COVID19_THERAPIE_OBSERVATION = new AqlFieldImp<Covid19TherapieObservation>(Covid19TherapieObservation.class, "", "Covid19TherapieObservation", Covid19TherapieObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(Covid19TherapieObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<StatusCluster> STATUS = new AqlFieldImp<StatusCluster>(Covid19TherapieObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0]", "status", StatusCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(Covid19TherapieObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(Covid19TherapieObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(Covid19TherapieObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<Covid19TherapieBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<Covid19TherapieBeliebigesEreignisChoice>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", Covid19TherapieBeliebigesEreignisChoice.class, this);

  private Covid19TherapieObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_statement.v0");
  }

  public static Covid19TherapieObservationContainment getInstance() {
    return new Covid19TherapieObservationContainment();
  }
}
