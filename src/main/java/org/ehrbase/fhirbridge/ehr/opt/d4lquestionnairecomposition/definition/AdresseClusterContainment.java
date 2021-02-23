package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<ArtDefiningCode> ART_DEFINING_CODE = new AqlFieldImp<ArtDefiningCode>(AdresseCluster.class, "/items[at0001]/items[at0006]/value|defining_code", "artDefiningCode", ArtDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0001]/items[at0006]/null_flavour|defining_code", "artNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0001]/items[at0004]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<NullFlavour> POSTLEITZAHL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0001]/items[at0004]/null_flavour|defining_code", "postleitzahlNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AdresseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v0");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
