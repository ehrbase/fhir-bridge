package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class Covid19TherapieObservationContainment extends Containment {
  public SelectAqlField<Covid19TherapieObservation> COVID19_THERAPIE_OBSERVATION = new AqlFieldImp<Covid19TherapieObservation>(Covid19TherapieObservation.class, "", "Covid19TherapieObservation", Covid19TherapieObservation.class, this);

  public SelectAqlField<DvCodedText> ARZNEIMITTEL_NAME = new AqlFieldImp<DvCodedText>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value", "arzneimittelName", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ARZNEIMITTEL_NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/null_flavour|defining_code", "arzneimittelNameNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> HERSTELLUNGSDETAILS = new ListAqlFieldImp<Cluster>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0007]", "herstellungsdetails", Cluster.class, this);

  public SelectAqlField<StatusDefiningCode> TREE_STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|defining_code", "treeStatusDefiningCode", StatusDefiningCode.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0008]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_DOSIS_UND_ZEITANGABEN = new ListAqlFieldImp<Cluster>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0022]", "strukturierteDosisUndZeitangaben", Cluster.class, this);

  public SelectAqlField<DvCodedText> GRUND = new AqlFieldImp<DvCodedText>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0023]/value", "grund", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> GRUND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0023]/null_flavour|defining_code", "grundNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(Covid19TherapieObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(Covid19TherapieObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<StatusCluster> ITEM_TREE_STATUS = new AqlFieldImp<StatusCluster>(Covid19TherapieObservation.class, "/protocol[at0004]/items[openEHR-EHR-CLUSTER.medication_status_fhir.v0]", "itemTreeStatus", StatusCluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(Covid19TherapieObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(Covid19TherapieObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(Covid19TherapieObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private Covid19TherapieObservationContainment() {
    super("openEHR-EHR-OBSERVATION.medication_statement.v0");
  }

  public static Covid19TherapieObservationContainment getInstance() {
    return new Covid19TherapieObservationContainment();
  }
}
