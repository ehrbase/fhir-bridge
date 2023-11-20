package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class MehrfachkodierungskennzeichenIcd10GmClusterContainment extends Containment {
  public SelectAqlField<MehrfachkodierungskennzeichenIcd10GmCluster> MEHRFACHKODIERUNGSKENNZEICHEN_ICD10_GM_CLUSTER = new AqlFieldImp<MehrfachkodierungskennzeichenIcd10GmCluster>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "", "MehrfachkodierungskennzeichenIcd10GmCluster", MehrfachkodierungskennzeichenIcd10GmCluster.class, this);

  public SelectAqlField<NullFlavour> MEHRFACHKODIERUNGKENNZEICHEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/items[at0001]/null_flavour|defining_code", "mehrfachkodierungkennzeichenNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice> MEHRFACHKODIERUNGKENNZEICHEN = new AqlFieldImp<MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice>(MehrfachkodierungskennzeichenIcd10GmCluster.class, "/items[at0001]/value", "mehrfachkodierungkennzeichen", MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice.class, this);

  private MehrfachkodierungskennzeichenIcd10GmClusterContainment() {
    super("openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1");
  }

  public static MehrfachkodierungskennzeichenIcd10GmClusterContainment getInstance() {
    return new MehrfachkodierungskennzeichenIcd10GmClusterContainment();
  }
}
