package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class FhirStatusDerBeobachtungClusterContainment extends Containment {
  public SelectAqlField<FhirStatusDerBeobachtungCluster> FHIR_STATUS_DER_BEOBACHTUNG_CLUSTER = new AqlFieldImp<FhirStatusDerBeobachtungCluster>(FhirStatusDerBeobachtungCluster.class, "", "FhirStatusDerBeobachtungCluster", FhirStatusDerBeobachtungCluster.class, this);

  public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(FhirStatusDerBeobachtungCluster.class, "/items[at0015]/value|value", "statusValue", String.class, this);

  public SelectAqlField<NullFlavour> STATUS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FhirStatusDerBeobachtungCluster.class, "/items[at0015]/null_flavour|defining_code", "statusNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(FhirStatusDerBeobachtungCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private FhirStatusDerBeobachtungClusterContainment() {
    super("openEHR-EHR-CLUSTER.observation_status_fhir.v1");
  }

  public static FhirStatusDerBeobachtungClusterContainment getInstance() {
    return new FhirStatusDerBeobachtungClusterContainment();
  }
}
