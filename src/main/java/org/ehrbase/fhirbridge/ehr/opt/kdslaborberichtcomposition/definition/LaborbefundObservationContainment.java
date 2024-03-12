package org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LaborbefundObservationContainment extends Containment {
  public SelectAqlField<LaborbefundObservation> LABORBEFUND_OBSERVATION = new AqlFieldImp<LaborbefundObservation>(LaborbefundObservation.class, "", "LaborbefundObservation", LaborbefundObservation.class, this);

  public SelectAqlField<LabortestKategorieDefiningCode> LABORTEST_KATEGORIE_DEFINING_CODE = new AqlFieldImp<LabortestKategorieDefiningCode>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|defining_code", "labortestKategorieDefiningCode", LabortestKategorieDefiningCode.class, this);

  public SelectAqlField<NullFlavour> LABORTEST_KATEGORIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code", "labortestKategorieNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ProbenmaterialCluster> PROBENMATERIAL = new ListAqlFieldImp<ProbenmaterialCluster>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.specimen.v1]", "probenmaterial", ProbenmaterialCluster.class, this);

  public ListSelectAqlField<ProLaboranalytCluster> PRO_LABORANALYT = new ListAqlFieldImp<ProLaboranalytCluster>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]", "proLaboranalyt", ProLaboranalytCluster.class, this);

  public SelectAqlField<String> SCHLUSSFOLGERUNG_VALUE = new AqlFieldImp<String>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0057]/value|value", "schlussfolgerungValue", String.class, this);

  public SelectAqlField<NullFlavour> SCHLUSSFOLGERUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0057]/null_flavour|defining_code", "schlussfolgerungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_TESTDIAGNOSTIK = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0122]", "strukturierteTestdiagnostik", Cluster.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA_DARSTELLUNG = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0118]", "multimediaDarstellung", Cluster.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_ERFASSUNG_DER_STOERFAKTOREN = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/state[at0112]/items[at0114]", "strukturierteErfassungDerStoerfaktoren", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(LaborbefundObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> LABOR_WELCHES_DEN_UNTERSUCHUNGSAUFTRAG_ANNIMMT = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/protocol[at0004]/items[at0017]", "laborWelchesDenUntersuchungsauftragAnnimmt", Cluster.class, this);

  public SelectAqlField<NullFlavour> IDENTIFIKATION_DER_LABORANFORDERUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbefundObservation.class, "/protocol[at0004]/items[at0094]/items[at0063]/null_flavour|defining_code", "identifikationDerLaboranforderungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Cluster> EINSENDER = new AqlFieldImp<Cluster>(LaborbefundObservation.class, "/protocol[at0004]/items[at0094]/items[at0090]", "einsender", Cluster.class, this);

  public ListSelectAqlField<Cluster> VERTEILERLISTE = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/protocol[at0004]/items[at0094]/items[at0035]", "verteilerliste", Cluster.class, this);

  public ListSelectAqlField<Cluster> TEST_DETAILS = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/protocol[at0004]/items[at0110]", "testDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(LaborbefundObservation.class, "/protocol[at0004]/items[at0117]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(LaborbefundObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(LaborbefundObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LaborbefundObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<LaborbefundIdentifikationDerLaboranforderungChoice> IDENTIFIKATION_DER_LABORANFORDERUNG = new AqlFieldImp<LaborbefundIdentifikationDerLaboranforderungChoice>(LaborbefundObservation.class, "/protocol[at0004]/items[at0094]/items[at0063]/value", "identifikationDerLaboranforderung", LaborbefundIdentifikationDerLaboranforderungChoice.class, this);

  private LaborbefundObservationContainment() {
    super("openEHR-EHR-OBSERVATION.laboratory_test_result.v1");
  }

  public static LaborbefundObservationContainment getInstance() {
    return new LaborbefundObservationContainment();
  }
}
