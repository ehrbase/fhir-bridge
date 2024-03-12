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

public class PostfachClusterContainment extends Containment {
  public SelectAqlField<PostfachCluster> POSTFACH_CLUSTER = new AqlFieldImp<PostfachCluster>(PostfachCluster.class, "", "PostfachCluster", PostfachCluster.class, this);

  public ListSelectAqlField<PostfachPostfachElement> POSTFACH = new ListAqlFieldImp<PostfachPostfachElement>(PostfachCluster.class, "/items[at0001]", "postfach", PostfachPostfachElement.class, this);

  public SelectAqlField<String> STADTTEIL_VALUE = new AqlFieldImp<String>(PostfachCluster.class, "/items[at0002]/value|value", "stadtteilValue", String.class, this);

  public SelectAqlField<NullFlavour> STADTTEIL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0002]/null_flavour|defining_code", "stadtteilNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STADT_VALUE = new AqlFieldImp<String>(PostfachCluster.class, "/items[at0002]/value|value", "stadtValue", String.class, this);

  public SelectAqlField<NullFlavour> STADT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0002]/null_flavour|defining_code", "stadtNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> GEMEINDESCHLUESSEL_VALUE = new AqlFieldImp<String>(PostfachCluster.class, "/items[at0002]/value|value", "gemeindeschluesselValue", String.class, this);

  public SelectAqlField<NullFlavour> GEMEINDESCHLUESSEL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0002]/null_flavour|defining_code", "gemeindeschluesselNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> BUNDESLAND = new AqlFieldImp<DvCodedText>(PostfachCluster.class, "/items[at0004]/value", "bundesland", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> BUNDESLAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0004]/null_flavour|defining_code", "bundeslandNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(PostfachCluster.class, "/items[at0005]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<NullFlavour> POSTLEITZAHL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0005]/null_flavour|defining_code", "postleitzahlNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> LAND = new AqlFieldImp<DvCodedText>(PostfachCluster.class, "/items[at0006]/value", "land", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> LAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0006]/null_flavour|defining_code", "landNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ArtDefiningCode> ART_DEFINING_CODE = new AqlFieldImp<ArtDefiningCode>(PostfachCluster.class, "/items[at0010]/value|defining_code", "artDefiningCode", ArtDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0010]/null_flavour|defining_code", "artNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<VerwendungDefiningCode> VERWENDUNG_DEFINING_CODE = new AqlFieldImp<VerwendungDefiningCode>(PostfachCluster.class, "/items[at0014]/value|defining_code", "verwendungDefiningCode", VerwendungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> VERWENDUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0014]/null_flavour|defining_code", "verwendungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(PostfachCluster.class, "/items[at0018]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(PostfachCluster.class, "/items[at0018]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(PostfachCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PostfachClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v1");
  }

  public static PostfachClusterContainment getInstance() {
    return new PostfachClusterContainment();
  }
}
