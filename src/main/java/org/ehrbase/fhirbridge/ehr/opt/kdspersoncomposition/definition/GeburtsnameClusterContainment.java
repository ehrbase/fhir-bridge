package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class GeburtsnameClusterContainment extends Containment {
  public SelectAqlField<GeburtsnameCluster> GEBURTSNAME_CLUSTER = new AqlFieldImp<GeburtsnameCluster>(GeburtsnameCluster.class, "", "GeburtsnameCluster", GeburtsnameCluster.class, this);

  public SelectAqlField<DvCodedText> NAMENSART = new AqlFieldImp<DvCodedText>(GeburtsnameCluster.class, "/items[at0001]/value", "namensart", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> NAMENSART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0001]/null_flavour|defining_code", "namensartNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> VOLLSTAENDIGER_NAME_VALUE = new AqlFieldImp<String>(GeburtsnameCluster.class, "/items[at0002]/value|value", "vollstaendigerNameValue", String.class, this);

  public SelectAqlField<NullFlavour> VOLLSTAENDIGER_NAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0002]/null_flavour|defining_code", "vollstaendigerNameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FAMILIENNAME_VALUE = new AqlFieldImp<String>(GeburtsnameCluster.class, "/items[at0005]/value|value", "familiennameValue", String.class, this);

  public SelectAqlField<NullFlavour> FAMILIENNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0005]/null_flavour|defining_code", "familiennameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FAMILIENNAME_NAMENSZUSATZ_VALUE = new AqlFieldImp<String>(GeburtsnameCluster.class, "/items[at0005]/value|value", "familiennameNamenszusatzValue", String.class, this);

  public SelectAqlField<NullFlavour> FAMILIENNAME_NAMENSZUSATZ_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0005]/null_flavour|defining_code", "familiennameNamenszusatzNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FAMILIENNAME_NACHNAME_VALUE = new AqlFieldImp<String>(GeburtsnameCluster.class, "/items[at0005]/value|value", "familiennameNachnameValue", String.class, this);

  public SelectAqlField<NullFlavour> FAMILIENNAME_NACHNAME_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0005]/null_flavour|defining_code", "familiennameNachnameNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> FAMILIENNAME_VORSATZWORT_VALUE = new AqlFieldImp<String>(GeburtsnameCluster.class, "/items[at0005]/value|value", "familiennameVorsatzwortValue", String.class, this);

  public SelectAqlField<NullFlavour> FAMILIENNAME_VORSATZWORT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeburtsnameCluster.class, "/items[at0005]/null_flavour|defining_code", "familiennameVorsatzwortNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<GeburtsnameSuffixElement> SUFFIX = new ListAqlFieldImp<GeburtsnameSuffixElement>(GeburtsnameCluster.class, "/items[at0006]", "suffix", GeburtsnameSuffixElement.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GeburtsnameCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GeburtsnameClusterContainment() {
    super("openEHR-EHR-CLUSTER.structured_name.v1");
  }

  public static GeburtsnameClusterContainment getInstance() {
    return new GeburtsnameClusterContainment();
  }
}
