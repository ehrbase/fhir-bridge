package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AlterObservationContainment extends Containment {
  public SelectAqlField<AlterObservation> ALTER_OBSERVATION = new AqlFieldImp<AlterObservation>(AlterObservation.class, "", "AlterObservation", AlterObservation.class, this);

  public SelectAqlField<TemporalAmount> ALTER_VALUE = new AqlFieldImp<TemporalAmount>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|value", "alterValue", TemporalAmount.class, this);

  public SelectAqlField<NullFlavour> ALTER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code", "alterNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AlterObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AlterObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AlterObservation.class, "/protocol[at0008]/items[at0009]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AlterObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AlterObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AlterObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AlterObservationContainment() {
    super("openEHR-EHR-OBSERVATION.age.v0");
  }

  public static AlterObservationContainment getInstance() {
    return new AlterObservationContainment();
  }
}
