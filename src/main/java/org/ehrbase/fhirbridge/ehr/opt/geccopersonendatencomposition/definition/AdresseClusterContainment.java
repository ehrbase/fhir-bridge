package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AdresseClusterContainment extends Containment {
  public SelectAqlField<AdresseCluster> ADRESSE_CLUSTER = new AqlFieldImp<AdresseCluster>(AdresseCluster.class, "", "AdresseCluster", AdresseCluster.class, this);

  public SelectAqlField<VerwendungDefiningCode> VERWENDUNG_DEFINING_CODE = new AqlFieldImp<VerwendungDefiningCode>(AdresseCluster.class, "/items[at0001]/value|defining_code", "verwendungDefiningCode", VerwendungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> VERWENDUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0001]/null_flavour|defining_code", "verwendungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TypDerAdresseDefiningCode> TYP_DER_ADRESSE_DEFINING_CODE = new AqlFieldImp<TypDerAdresseDefiningCode>(AdresseCluster.class, "/items[at0006]/value|defining_code", "typDerAdresseDefiningCode", TypDerAdresseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> TYP_DER_ADRESSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0006]/null_flavour|defining_code", "typDerAdresseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> TEXT_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0010]/value|value", "textValue", String.class, this);

  public SelectAqlField<NullFlavour> TEXT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0010]/null_flavour|defining_code", "textNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<AdresseZeileElement> ZEILE = new ListAqlFieldImp<AdresseZeileElement>(AdresseCluster.class, "/items[at0011]", "zeile", AdresseZeileElement.class, this);

  public SelectAqlField<String> STADT_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0012]/value|value", "stadtValue", String.class, this);

  public SelectAqlField<NullFlavour> STADT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0012]/null_flavour|defining_code", "stadtNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> BEZIRK_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0013]/value|value", "bezirkValue", String.class, this);

  public SelectAqlField<NullFlavour> BEZIRK_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0013]/null_flavour|defining_code", "bezirkNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0014]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<NullFlavour> POSTLEITZAHL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0014]/null_flavour|defining_code", "postleitzahlNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> LAND_VALUE = new AqlFieldImp<String>(AdresseCluster.class, "/items[at0015]/value|value", "landValue", String.class, this);

  public SelectAqlField<NullFlavour> LAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0015]/null_flavour|defining_code", "landNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_DER_GUELTIGKEITSDAUER_VALUE = new AqlFieldImp<TemporalAccessor>(AdresseCluster.class, "/items[at0016]/value|value", "beginnDerGueltigkeitsdauerValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> BEGINN_DER_GUELTIGKEITSDAUER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0016]/null_flavour|defining_code", "beginnDerGueltigkeitsdauerNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ENDE_DER_GUELTIGKEITSDAUER_VALUE = new AqlFieldImp<TemporalAccessor>(AdresseCluster.class, "/items[at0017]/value|value", "endeDerGueltigkeitsdauerValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> ENDE_DER_GUELTIGKEITSDAUER_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AdresseCluster.class, "/items[at0017]/null_flavour|defining_code", "endeDerGueltigkeitsdauerNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AdresseCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AdresseClusterContainment() {
    super("openEHR-EHR-CLUSTER.address_cc.v0");
  }

  public static AdresseClusterContainment getInstance() {
    return new AdresseClusterContainment();
  }
}
