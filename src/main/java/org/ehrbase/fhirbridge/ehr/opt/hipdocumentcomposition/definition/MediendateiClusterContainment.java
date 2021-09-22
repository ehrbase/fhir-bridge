package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.encapsulated.DvMultimedia;
import com.nedap.archie.rm.datavalues.quantity.DvOrdered;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

import java.time.temporal.TemporalAccessor;

public class MediendateiClusterContainment extends Containment {
  public SelectAqlField<MediendateiCluster> MEDIENDATEI_CLUSTER = new AqlFieldImp<MediendateiCluster>(MediendateiCluster.class, "", "MediendateiCluster", MediendateiCluster.class, this);

  public SelectAqlField<DvMultimedia> MEDIENDATEI_INHALT = new AqlFieldImp<DvMultimedia>(MediendateiCluster.class, "/items[at0001]/value", "mediendateiInhalt", DvMultimedia.class, this);

  public SelectAqlField<NullFlavour> MEDIENDATEI_INHALT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MediendateiCluster.class, "/items[at0001]/null_flavour|defining_code", "mediendateiInhaltNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> MEDIENDATEI_INHALT_VALUE = new AqlFieldImp<String>(MediendateiCluster.class, "/items[at0002]/value|value", "mediendateiInhaltValue", String.class, this);

  public SelectAqlField<NullFlavour> MEDIENDATEI_INHALT_NULL_FLAVOUR_DEFINING_CODE2 = new AqlFieldImp<NullFlavour>(MediendateiCluster.class, "/items[at0002]/null_flavour|defining_code", "mediendateiInhaltNullFlavourDefiningCode2", NullFlavour.class, this);

  public ListSelectAqlField<MediendateiIdentifikatorElement> IDENTIFIKATOR = new ListAqlFieldImp<MediendateiIdentifikatorElement>(MediendateiCluster.class, "/items[at0010]", "identifikator", MediendateiIdentifikatorElement.class, this);

  public SelectAqlField<String> BESCHREIBUNG_VALUE = new AqlFieldImp<String>(MediendateiCluster.class, "/items[at0005]/value|value", "beschreibungValue", String.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MediendateiCluster.class, "/items[at0005]/null_flavour|defining_code", "beschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> ERSTELLT_VALUE = new AqlFieldImp<TemporalAccessor>(MediendateiCluster.class, "/items[at0004]/value|value", "erstelltValue", TemporalAccessor.class, this);

  public SelectAqlField<Boolean> LOWER_INCLUDED = new AqlFieldImp<Boolean>(MediendateiCluster.class, "/items[at0004]/value/lower_included", "lowerIncluded", Boolean.class, this);

  public SelectAqlField<Boolean> UPPER_INCLUDED = new AqlFieldImp<Boolean>(MediendateiCluster.class, "/items[at0004]/value/upper_included", "upperIncluded", Boolean.class, this);

  public SelectAqlField<DvOrdered> UPPER = new AqlFieldImp<DvOrdered>(MediendateiCluster.class, "/items[at0004]/value/upper", "upper", DvOrdered.class, this);

  public SelectAqlField<DvOrdered> LOWER = new AqlFieldImp<DvOrdered>(MediendateiCluster.class, "/items[at0004]/value/lower", "lower", DvOrdered.class, this);

  public SelectAqlField<NullFlavour> ERSTELLT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MediendateiCluster.class, "/items[at0004]/null_flavour|defining_code", "erstelltNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> URHEBER = new ListAqlFieldImp<Cluster>(MediendateiCluster.class, "/items[at0012]", "urheber", Cluster.class, this);

  public ListSelectAqlField<Cluster> QUELLGERAET = new ListAqlFieldImp<Cluster>(MediendateiCluster.class, "/items[at0011]", "quellgeraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_INFORMATIONEN = new ListAqlFieldImp<Cluster>(MediendateiCluster.class, "/items[at0013]", "zusaetzlicheInformationen", Cluster.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(MediendateiCluster.class, "/items[at0007]/value|value", "kommentarValue", String.class, this);

  public SelectAqlField<NullFlavour> KOMMENTAR_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(MediendateiCluster.class, "/items[at0007]/null_flavour|defining_code", "kommentarNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(MediendateiCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private MediendateiClusterContainment() {
    super("openEHR-EHR-CLUSTER.media_file.v0");
  }

  public static MediendateiClusterContainment getInstance() {
    return new MediendateiClusterContainment();
  }
}
