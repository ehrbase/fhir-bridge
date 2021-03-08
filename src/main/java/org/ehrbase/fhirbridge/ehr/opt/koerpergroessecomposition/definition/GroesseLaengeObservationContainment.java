package org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition;

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

public class GroesseLaengeObservationContainment extends Containment {
  public SelectAqlField<GroesseLaengeObservation> GROESSE_LAENGE_OBSERVATION = new AqlFieldImp<GroesseLaengeObservation>(GroesseLaengeObservation.class, "", "GroesseLaengeObservation", GroesseLaengeObservation.class, this);

  public SelectAqlField<Double> GROESSE_LAENGE_MAGNITUDE = new AqlFieldImp<Double>(GroesseLaengeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude", "groesseLaengeMagnitude", Double.class, this);

  public SelectAqlField<String> GROESSE_LAENGE_UNITS = new AqlFieldImp<String>(GroesseLaengeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units", "groesseLaengeUnits", String.class, this);

  public SelectAqlField<NullFlavour> GROESSE_LAENGE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GroesseLaengeObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code", "groesseLaengeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(GroesseLaengeObservation.class, "/data[at0001]/events[at0002]/state[at0013]", "tree", ItemTree.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(GroesseLaengeObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(GroesseLaengeObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(GroesseLaengeObservation.class, "/protocol[at0007]/items[at0011]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GroesseLaengeObservation.class, "/protocol[at0007]/items[at0022]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GroesseLaengeObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GroesseLaengeObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GroesseLaengeObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GroesseLaengeObservationContainment() {
    super("openEHR-EHR-OBSERVATION.height.v2");
  }

  public static GroesseLaengeObservationContainment getInstance() {
    return new GroesseLaengeObservationContainment();
  }
}
