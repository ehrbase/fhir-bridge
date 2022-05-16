package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class GesamtergebnisObservationContainment extends Containment {
  public SelectAqlField<GesamtergebnisObservation> GESAMTERGEBNIS_OBSERVATION = new AqlFieldImp<GesamtergebnisObservation>(GesamtergebnisObservation.class, "", "GesamtergebnisObservation", GesamtergebnisObservation.class, this);

  public SelectAqlField<GesamtstatusCluster> GESAMTSTATUS = new AqlFieldImp<GesamtstatusCluster>(GesamtergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.questionnaire_item.v0]", "gesamtstatus", GesamtstatusCluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GesamtergebnisObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(GesamtergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GesamtergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GesamtergebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GesamtergebnisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GesamtergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.questionnaire_entry.v0");
  }

  public static GesamtergebnisObservationContainment getInstance() {
    return new GesamtergebnisObservationContainment();
  }
}
