package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class DiagnosedetailsClusterContainment extends Containment {
  public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS_CLUSTER = new AqlFieldImp<DiagnosedetailsCluster>(DiagnosedetailsCluster.class, "", "DiagnosedetailsCluster", DiagnosedetailsCluster.class, this);

  public SelectAqlField<String> BEGRUENDUNG_VON_AUSNAHMEN_VALUE = new AqlFieldImp<String>(DiagnosedetailsCluster.class, "/items[at0001]/value|value", "begruendungVonAusnahmenValue", String.class, this);

  public SelectAqlField<NullFlavour> BEGRUENDUNG_VON_AUSNAHMEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DiagnosedetailsCluster.class, "/items[at0001]/null_flavour|defining_code", "begruendungVonAusnahmenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> DIAGNOSETYP_VALUE = new AqlFieldImp<String>(DiagnosedetailsCluster.class, "/items[at0002]/value|value", "diagnosetypValue", String.class, this);

  public SelectAqlField<NullFlavour> DIAGNOSETYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DiagnosedetailsCluster.class, "/items[at0002]/null_flavour|defining_code", "diagnosetypNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> VORHANDEN_BEI_AUFNAHME_VALUE = new AqlFieldImp<Boolean>(DiagnosedetailsCluster.class, "/items[at0016]/value|value", "vorhandenBeiAufnahmeValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> VORHANDEN_BEI_AUFNAHME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DiagnosedetailsCluster.class, "/items[at0016]/null_flavour|defining_code", "vorhandenBeiAufnahmeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> VORHANDEN_BEI_ENTLASSUNG_VALUE = new AqlFieldImp<Boolean>(DiagnosedetailsCluster.class, "/items[at0017]/value|value", "vorhandenBeiEntlassungValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> VORHANDEN_BEI_ENTLASSUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DiagnosedetailsCluster.class, "/items[at0017]/null_flavour|defining_code", "vorhandenBeiEntlassungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(DiagnosedetailsCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DiagnosedetailsClusterContainment() {
    super("openEHR-EHR-CLUSTER.diagnose_details.v0");
  }

  public static DiagnosedetailsClusterContainment getInstance() {
    return new DiagnosedetailsClusterContainment();
  }
}
