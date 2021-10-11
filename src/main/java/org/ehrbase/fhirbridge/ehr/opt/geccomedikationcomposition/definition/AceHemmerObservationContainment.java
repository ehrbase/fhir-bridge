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

public class AceHemmerObservationContainment extends Containment {
  public SelectAqlField<AceHemmerObservation> ACE_HEMMER_OBSERVATION = new AqlFieldImp<AceHemmerObservation>(AceHemmerObservation.class, "", "AceHemmerObservation", AceHemmerObservation.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AceHemmerObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<StatusCluster> STATUS = new AqlFieldImp<StatusCluster>(AceHemmerObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0]", "status", StatusCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AceHemmerObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AceHemmerObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AceHemmerObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public ListSelectAqlField<AceHemmerBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new ListAqlFieldImp<AceHemmerBeliebigesEreignisChoice>(AceHemmerObservation.class, "/data[at0001]/events[at0002]", "beliebigesEreignis", AceHemmerBeliebigesEreignisChoice.class, this);

  private AceHemmerObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_statement.v0");
  }

  public static AceHemmerObservationContainment getInstance() {
    return new AceHemmerObservationContainment();
  }
}
