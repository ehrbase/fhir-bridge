package org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class BildgebendesUntersuchungsergebnisObservationContainment extends Containment {
  public SelectAqlField<BildgebendesUntersuchungsergebnisObservation> BILDGEBENDES_UNTERSUCHUNGSERGEBNIS_OBSERVATION = new AqlFieldImp<BildgebendesUntersuchungsergebnisObservation>(BildgebendesUntersuchungsergebnisObservation.class, "", "BildgebendesUntersuchungsergebnisObservation", BildgebendesUntersuchungsergebnisObservation.class, this);

  public SelectAqlField<NameDerUntersuchungDefiningCode> NAME_DER_UNTERSUCHUNG_DEFINING_CODE = new AqlFieldImp<NameDerUntersuchungDefiningCode>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code", "nameDerUntersuchungDefiningCode", NameDerUntersuchungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> NAME_DER_UNTERSUCHUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code", "nameDerUntersuchungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANATOMISCHE_POSITION_STRUKTURIERT = new ListAqlFieldImp<Cluster>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]", "anatomischePositionStrukturiert", Cluster.class, this);

  public SelectAqlField<BefundeDefiningCode> BEFUNDE_DEFINING_CODE = new AqlFieldImp<BefundeDefiningCode>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|defining_code", "befundeDefiningCode", BefundeDefiningCode.class, this);

  public SelectAqlField<NullFlavour> BEFUNDE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/null_flavour|defining_code", "befundeNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> BILDREPRAESENTATION = new ListAqlFieldImp<Cluster>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0045]", "bildrepraesentation", Cluster.class, this);

  public SelectAqlField<ItemTree> TREE = new AqlFieldImp<ItemTree>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/state[at0047]", "tree", ItemTree.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BildgebendesUntersuchungsergebnisObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> TECHNISCHES_VERFAHREN_STRUKTURIERT = new ListAqlFieldImp<Cluster>(BildgebendesUntersuchungsergebnisObservation.class, "/protocol[at0025]/items[at0041]", "technischesVerfahrenStrukturiert", Cluster.class, this);

  public ListSelectAqlField<Cluster> EMPFANGENDE_BILDGEBUNGSDIENST = new ListAqlFieldImp<Cluster>(BildgebendesUntersuchungsergebnisObservation.class, "/protocol[at0025]/items[at0026]", "empfangendeBildgebungsdienst", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(BildgebendesUntersuchungsergebnisObservation.class, "/protocol[at0025]/items[at0046]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BildgebendesUntersuchungsergebnisObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BildgebendesUntersuchungsergebnisObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BildgebendesUntersuchungsergebnisObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BildgebendesUntersuchungsergebnisObservationContainment() {
    super("openEHR-EHR-OBSERVATION.imaging_exam_result.v0");
  }

  public static BildgebendesUntersuchungsergebnisObservationContainment getInstance() {
    return new BildgebendesUntersuchungsergebnisObservationContainment();
  }
}
