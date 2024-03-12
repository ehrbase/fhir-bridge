package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<String> ADRESSZEILE_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0001]/value|value", "adresszeileValue", String.class, this);

  public SelectAqlField<NullFlavour> ADRESSZEILE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0001]/null_flavour|defining_code", "adresszeileNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AdresseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v1");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
