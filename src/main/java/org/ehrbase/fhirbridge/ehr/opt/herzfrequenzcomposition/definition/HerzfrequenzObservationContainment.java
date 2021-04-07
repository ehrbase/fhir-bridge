package org.ehrbase.fhirbridge.ehr.opt.herzfrequenzcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class HerzfrequenzObservationContainment extends Containment {
  public SelectAqlField<HerzfrequenzObservation> HERZFREQUENZ_OBSERVATION = new AqlFieldImp<HerzfrequenzObservation>(HerzfrequenzObservation.class, "", "HerzfrequenzObservation", HerzfrequenzObservation.class, this);

  public SelectAqlField<Double> FREQUENZ_MAGNITUDE = new AqlFieldImp<Double>(HerzfrequenzObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude", "frequenzMagnitude", Double.class, this);

  public SelectAqlField<String> FREQUENZ_UNITS = new AqlFieldImp<String>(HerzfrequenzObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units", "frequenzUnits", String.class, this);

  public SelectAqlField<NullFlavour> FREQUENZ_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(HerzfrequenzObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code", "frequenzNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANSTRENGUNG = new ListAqlFieldImp<Cluster>(HerzfrequenzObservation.class, "/data[at0002]/events[at0003]/state[at0012]/items[at1017]", "anstrengung", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(HerzfrequenzObservation.class, "/data[at0002]/events[at0003]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(HerzfrequenzObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(HerzfrequenzObservation.class, "/protocol[at0010]/items[at1013]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(HerzfrequenzObservation.class, "/protocol[at0010]/items[at1056]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(HerzfrequenzObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HerzfrequenzObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(HerzfrequenzObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private HerzfrequenzObservationContainment() {
    super("openEHR-EHR-OBSERVATION.pulse.v2");
  }

  public static HerzfrequenzObservationContainment getInstance() {
    return new HerzfrequenzObservationContainment();
  }
}
