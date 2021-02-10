package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AtemproblemeClusterContainment extends Containment {
  public SelectAqlField<AtemproblemeCluster> ATEMPROBLEME_CLUSTER = new AqlFieldImp<AtemproblemeCluster>(AtemproblemeCluster.class, "", "AtemproblemeCluster", AtemproblemeCluster.class, this);

  public SelectAqlField<String> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_VALUE = new AqlFieldImp<String>(AtemproblemeCluster.class, "/items[at0001]/value|value", "nameDesSymptomsKrankheitsanzeichensValue", String.class, this);

  public SelectAqlField<NullFlavour> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AtemproblemeCluster.class, "/items[at0001]/null_flavour|defining_code", "nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<Boolean> VORHANDEN_VALUE = new AqlFieldImp<Boolean>(AtemproblemeCluster.class, "/items[at0035]/value|value", "vorhandenValue", Boolean.class, this);

  public SelectAqlField<NullFlavour> VORHANDEN_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AtemproblemeCluster.class, "/items[at0035]/null_flavour|defining_code", "vorhandenNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(AtemproblemeCluster.class, "/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(AtemproblemeCluster.class, "/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(AtemproblemeCluster.class, "/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public ListSelectAqlField<Cluster> ASSOZIIERTE_SYMPTOME_KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(AtemproblemeCluster.class, "/items[at0063]", "assoziierteSymptomeKrankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AtemproblemeCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AtemproblemeClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign.v1");
  }

  public static AtemproblemeClusterContainment getInstance() {
    return new AtemproblemeClusterContainment();
  }
}
