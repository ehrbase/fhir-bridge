package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProLaboranalytClusterContainment extends Containment {
  public SelectAqlField<ProLaboranalytCluster> PRO_LABORANALYT_CLUSTER = new AqlFieldImp<ProLaboranalytCluster>(ProLaboranalytCluster.class, "", "ProLaboranalytCluster", ProLaboranalytCluster.class, this);

  public SelectAqlField<UntersuchterAnalytDefiningCode> UNTERSUCHTER_ANALYT_DEFINING_CODE = new AqlFieldImp<UntersuchterAnalytDefiningCode>(ProLaboranalytCluster.class, "/items[at0024]/value|defining_code", "untersuchterAnalytDefiningCode", UntersuchterAnalytDefiningCode.class, this);

  public SelectAqlField<NullFlavour> UNTERSUCHTER_ANALYT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0024]/null_flavour|defining_code", "untersuchterAnalytNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> MESSWERT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0001]/null_flavour|defining_code", "messwertNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS = new ListAqlFieldImp<Cluster>(ProLaboranalytCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  public SelectAqlField<InterpretationDefiningCode> INTERPRETATION_DEFINING_CODE = new AqlFieldImp<InterpretationDefiningCode>(ProLaboranalytCluster.class, "/items[at0004]/value|defining_code", "interpretationDefiningCode", InterpretationDefiningCode.class, this);

  public SelectAqlField<NullFlavour> INTERPRETATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0004]/null_flavour|defining_code", "interpretationNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_VALIDATION_VALUE = new AqlFieldImp<TemporalAccessor>(ProLaboranalytCluster.class, "/items[at0025]/value|value", "zeitpunktValidationValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_VALIDATION_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0025]/null_flavour|defining_code", "zeitpunktValidationNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> ERGEBNIS_STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0005]/null_flavour|defining_code", "ergebnisStatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ZEITPUNKT_ERGEBNIS_STATUS_VALUE = new AqlFieldImp<TemporalAccessor>(ProLaboranalytCluster.class, "/items[at0006]/value|value", "zeitpunktErgebnisStatusValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ZEITPUNKT_ERGEBNIS_STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0006]/null_flavour|defining_code", "zeitpunktErgebnisStatusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> PROBE_ID_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProLaboranalytCluster.class, "/items[at0026]/null_flavour|defining_code", "probeIdNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<ProLaboranalytKommentarElement> KOMMENTAR = new ListAqlFieldImp<ProLaboranalytKommentarElement>(ProLaboranalytCluster.class, "/items[at0003]", "kommentar", ProLaboranalytKommentarElement.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProLaboranalytCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProLaboranalytProbeIdChoice> PROBE_ID = new AqlFieldImp<ProLaboranalytProbeIdChoice>(ProLaboranalytCluster.class, "/items[at0026]/value", "probeId", ProLaboranalytProbeIdChoice.class, this);

  public SelectAqlField<ProLaboranalytErgebnisStatusChoice> ERGEBNIS_STATUS = new AqlFieldImp<ProLaboranalytErgebnisStatusChoice>(ProLaboranalytCluster.class, "/items[at0005]/value", "ergebnisStatus", ProLaboranalytErgebnisStatusChoice.class, this);

  public SelectAqlField<ProLaboranalytMesswertChoice> MESSWERT = new AqlFieldImp<ProLaboranalytMesswertChoice>(ProLaboranalytCluster.class, "/items[at0001]/value", "messwert", ProLaboranalytMesswertChoice.class, this);

  private ProLaboranalytClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static ProLaboranalytClusterContainment getInstance() {
    return new ProLaboranalytClusterContainment();
  }
}
