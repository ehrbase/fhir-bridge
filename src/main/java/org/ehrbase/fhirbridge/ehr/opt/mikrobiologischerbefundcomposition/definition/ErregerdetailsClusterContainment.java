package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class ErregerdetailsClusterContainment extends Containment {
  public SelectAqlField<ErregerdetailsCluster> ERREGERDETAILS_CLUSTER = new AqlFieldImp<ErregerdetailsCluster>(ErregerdetailsCluster.class, "", "ErregerdetailsCluster", ErregerdetailsCluster.class, this);

  public ListSelectAqlField<ErregerdetailsKeimSubtypElement> KEIM_SUBTYP = new ListAqlFieldImp<ErregerdetailsKeimSubtypElement>(ErregerdetailsCluster.class, "/items[at0047]", "keimSubtyp", ErregerdetailsKeimSubtypElement.class, this);

  public SelectAqlField<Double> KEIMZAHL_MAGNITUDE = new AqlFieldImp<Double>(ErregerdetailsCluster.class, "/items[at0035]/value|magnitude", "keimzahlMagnitude", Double.class, this);

  public SelectAqlField<String> KEIMZAHL_UNITS = new AqlFieldImp<String>(ErregerdetailsCluster.class, "/items[at0035]/value|units", "keimzahlUnits", String.class, this);

  public SelectAqlField<DvOrdinal> HAEUFIGKEIT = new AqlFieldImp<DvOrdinal>(ErregerdetailsCluster.class, "/items[at0003]/value", "haeufigkeit", DvOrdinal.class, this);

  public SelectAqlField<NullFlavour> HAEUFIGKEIT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ErregerdetailsCluster.class, "/items[at0003]/null_flavour|defining_code", "haeufigkeitNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> VIRULENZFAKTOR_VALUE = new AqlFieldImp<String>(ErregerdetailsCluster.class, "/items[at0016]/value|value", "virulenzfaktorValue", String.class, this);

  public SelectAqlField<NullFlavour> VIRULENZFAKTOR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ErregerdetailsCluster.class, "/items[at0016]/null_flavour|defining_code", "virulenzfaktorNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AntibiogrammCluster> ANTIBIOGRAMM = new AqlFieldImp<AntibiogrammCluster>(ErregerdetailsCluster.class, "/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]", "antibiogramm", AntibiogrammCluster.class, this);

  public ListSelectAqlField<ErregerdetailsResistenzmechanismusCluster> RESISTENZMECHANISMUS = new ListAqlFieldImp<ErregerdetailsResistenzmechanismusCluster>(ErregerdetailsCluster.class, "/items[at0057]", "resistenzmechanismus", ErregerdetailsResistenzmechanismusCluster.class, this);

  public SelectAqlField<DvCodedText> MRE_KLASSE = new AqlFieldImp<DvCodedText>(ErregerdetailsCluster.class, "/items[at0018]/value", "mreKlasse", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> MRE_KLASSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ErregerdetailsCluster.class, "/items[at0018]/null_flavour|defining_code", "mreKlasseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(ErregerdetailsCluster.class, "/items[at0062]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(ErregerdetailsCluster.class, "/items[at0062]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> WEITERE_ERGAENZUNGEN = new ListAqlFieldImp<Cluster>(ErregerdetailsCluster.class, "/items[at0059]", "weitereErgaenzungen", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(ErregerdetailsCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<ErregerdetailsKeimzahlNullFlavourChoice> KEIMZAHL_NULL_FLAVOUR = new AqlFieldImp<ErregerdetailsKeimzahlNullFlavourChoice>(ErregerdetailsCluster.class, "/items[at0035]/null_flavour", "keimzahlNullFlavour", ErregerdetailsKeimzahlNullFlavourChoice.class, this);

  private ErregerdetailsClusterContainment() {
    super("openEHR-EHR-CLUSTER.erregerdetails.v1");
  }

  public static ErregerdetailsClusterContainment getInstance() {
    return new ErregerdetailsClusterContainment();
  }
}
