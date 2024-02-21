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

public class StrassenanschriftClusterContainment extends Containment {
  public SelectAqlField<StrassenanschriftCluster> STRASSENANSCHRIFT_CLUSTER = new AqlFieldImp<StrassenanschriftCluster>(StrassenanschriftCluster.class, "", "StrassenanschriftCluster", StrassenanschriftCluster.class, this);

  public ListSelectAqlField<StrassenanschriftStrasseElement> STRASSE = new ListAqlFieldImp<StrassenanschriftStrasseElement>(StrassenanschriftCluster.class, "/items[at0001]", "strasse", StrassenanschriftStrasseElement.class, this);

  public ListSelectAqlField<StrassenanschriftHausnummerElement> HAUSNUMMER = new ListAqlFieldImp<StrassenanschriftHausnummerElement>(StrassenanschriftCluster.class, "/items[at0001]", "hausnummer", StrassenanschriftHausnummerElement.class, this);

  public ListSelectAqlField<StrassenanschriftAdresszusatzElement> ADRESSZUSATZ = new ListAqlFieldImp<StrassenanschriftAdresszusatzElement>(StrassenanschriftCluster.class, "/items[at0001]", "adresszusatz", StrassenanschriftAdresszusatzElement.class, this);

  public SelectAqlField<String> STADTTEIL_VALUE = new AqlFieldImp<String>(StrassenanschriftCluster.class, "/items[at0002]/value|value", "stadtteilValue", String.class, this);

  public SelectAqlField<NullFlavour> STADTTEIL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0002]/null_flavour|defining_code", "stadtteilNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> STADT_VALUE = new AqlFieldImp<String>(StrassenanschriftCluster.class, "/items[at0002]/value|value", "stadtValue", String.class, this);

  public SelectAqlField<NullFlavour> STADT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0002]/null_flavour|defining_code", "stadtNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> GEMEINDESCHLUESSEL_VALUE = new AqlFieldImp<String>(StrassenanschriftCluster.class, "/items[at0002]/value|value", "gemeindeschluesselValue", String.class, this);

  public SelectAqlField<NullFlavour> GEMEINDESCHLUESSEL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0002]/null_flavour|defining_code", "gemeindeschluesselNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> BUNDESLAND = new AqlFieldImp<DvCodedText>(StrassenanschriftCluster.class, "/items[at0004]/value", "bundesland", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> BUNDESLAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0004]/null_flavour|defining_code", "bundeslandNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> POSTLEITZAHL_VALUE = new AqlFieldImp<String>(StrassenanschriftCluster.class, "/items[at0005]/value|value", "postleitzahlValue", String.class, this);

  public SelectAqlField<NullFlavour> POSTLEITZAHL_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0005]/null_flavour|defining_code", "postleitzahlNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> LAND = new AqlFieldImp<DvCodedText>(StrassenanschriftCluster.class, "/items[at0006]/value", "land", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> LAND_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0006]/null_flavour|defining_code", "landNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<ArtDefiningCode> ART_DEFINING_CODE = new AqlFieldImp<ArtDefiningCode>(StrassenanschriftCluster.class, "/items[at0010]/value|defining_code", "artDefiningCode", ArtDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0010]/null_flavour|defining_code", "artNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<VerwendungDefiningCode> VERWENDUNG_DEFINING_CODE = new AqlFieldImp<VerwendungDefiningCode>(StrassenanschriftCluster.class, "/items[at0014]/value|defining_code", "verwendungDefiningCode", VerwendungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> VERWENDUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0014]/null_flavour|defining_code", "verwendungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(StrassenanschriftCluster.class, "/items[at0018]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(StrassenanschriftCluster.class, "/items[at0018]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(StrassenanschriftCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StrassenanschriftClusterContainment() {
    super("openEHR-EHR-CLUSTER.address.v1");
  }

  public static StrassenanschriftClusterContainment getInstance() {
    return new StrassenanschriftClusterContainment();
  }
}
