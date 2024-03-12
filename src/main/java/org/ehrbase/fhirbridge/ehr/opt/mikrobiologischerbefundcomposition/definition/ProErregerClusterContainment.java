package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ProErregerClusterContainment extends Containment {
  public SelectAqlField<ProErregerCluster> PRO_ERREGER_CLUSTER = new AqlFieldImp<ProErregerCluster>(ProErregerCluster.class, "", "ProErregerCluster", ProErregerCluster.class, this);

  public SelectAqlField<Long> ISOLATNUMMER_MAGNITUDE = new AqlFieldImp<Long>(ProErregerCluster.class, "/items[at0027]/value|magnitude", "isolatnummerMagnitude", Long.class, this);

  public SelectAqlField<NullFlavour> ISOLATNUMMER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0027]/null_flavour|defining_code", "isolatnummerNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> NACHWEIS_VALUE = new AqlFieldImp<String>(ProErregerCluster.class, "/items[at0024]/value|value", "nachweisValue", String.class, this);

  public SelectAqlField<NullFlavour> NACHWEIS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0024]/null_flavour|defining_code", "nachweisNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> ERREGERNAME = new AqlFieldImp<DvCodedText>(ProErregerCluster.class, "/items[at0001]/value", "erregername", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ERREGERNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0001]/null_flavour|defining_code", "erregernameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ErregerdetailsCluster> ERREGERDETAILS = new AqlFieldImp<ErregerdetailsCluster>(ProErregerCluster.class, "/items[openEHR-EHR-CLUSTER.erregerdetails.v1]", "erregerdetails", ErregerdetailsCluster.class, this);

  public SelectAqlField<NullFlavour> TESTMETHODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0028]/null_flavour|defining_code", "testmethodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<NullFlavour> ZUGEHOERIGE_LABORPROBE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0026]/null_flavour|defining_code", "zugehoerigeLaborprobeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ProErregerCluster.class, "/items[at0003]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ProErregerCluster.class, "/items[at0003]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ProErregerCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ProErregerZugehoerigeLaborprobeChoice> ZUGEHOERIGE_LABORPROBE = new AqlFieldImp<ProErregerZugehoerigeLaborprobeChoice>(ProErregerCluster.class, "/items[at0026]/value", "zugehoerigeLaborprobe", ProErregerZugehoerigeLaborprobeChoice.class, this);

  public SelectAqlField<ProErregerTestmethodeChoice> TESTMETHODE = new AqlFieldImp<ProErregerTestmethodeChoice>(ProErregerCluster.class, "/items[at0028]/value", "testmethode", ProErregerTestmethodeChoice.class, this);

  private ProErregerClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static ProErregerClusterContainment getInstance() {
    return new ProErregerClusterContainment();
  }
}
