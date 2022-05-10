package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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

public class KoerpergewichtObservationContainment extends Containment {
  public SelectAqlField<KoerpergewichtObservation> KOERPERGEWICHT_OBSERVATION = new AqlFieldImp<KoerpergewichtObservation>(KoerpergewichtObservation.class, "", "KoerpergewichtObservation", KoerpergewichtObservation.class, this);

  public SelectAqlField<Double> GEWICHT_MAGNITUDE = new AqlFieldImp<Double>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude", "gewichtMagnitude", Double.class, this);

  public SelectAqlField<String> GEWICHT_UNITS = new AqlFieldImp<String>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units", "gewichtUnits", String.class, this);

  public SelectAqlField<NullFlavour> GEWICHT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code", "gewichtNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ItemTree> STATE_STRUCTURE = new AqlFieldImp<ItemTree>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]/state[at0008]", "stateStructure", ItemTree.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(KoerpergewichtObservation.class, "/data[at0002]/events[at0003]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(KoerpergewichtObservation.class, "/data[at0002]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(KoerpergewichtObservation.class, "/protocol[at0015]/items[at0020]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(KoerpergewichtObservation.class, "/protocol[at0015]/items[at0027]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(KoerpergewichtObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(KoerpergewichtObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(KoerpergewichtObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KoerpergewichtObservationContainment() {
    super("openEHR-EHR-OBSERVATION.body_weight.v2");
  }

  public static KoerpergewichtObservationContainment getInstance() {
    return new KoerpergewichtObservationContainment();
  }
}
