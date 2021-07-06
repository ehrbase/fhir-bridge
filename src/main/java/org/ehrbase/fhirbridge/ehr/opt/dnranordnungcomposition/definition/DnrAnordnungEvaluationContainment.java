package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class DnrAnordnungEvaluationContainment extends Containment {
  public SelectAqlField<DnrAnordnungEvaluation> DNR_ANORDNUNG_EVALUATION = new AqlFieldImp<DnrAnordnungEvaluation>(DnrAnordnungEvaluation.class, "", "DnrAnordnungEvaluation", DnrAnordnungEvaluation.class, this);

  public SelectAqlField<ArtDerRichtlinieDefiningCode> ART_DER_RICHTLINIE_DEFINING_CODE = new AqlFieldImp<ArtDerRichtlinieDefiningCode>(DnrAnordnungEvaluation.class, "/data[at0001]/items[at0005]/value|defining_code", "artDerRichtlinieDefiningCode", ArtDerRichtlinieDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ART_DER_RICHTLINIE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DnrAnordnungEvaluation.class, "/data[at0001]/items[at0005]/null_flavour|defining_code", "artDerRichtlinieNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<BeschreibungDefiningCode> BESCHREIBUNG_DEFINING_CODE = new AqlFieldImp<BeschreibungDefiningCode>(DnrAnordnungEvaluation.class, "/data[at0001]/items[at0006]/value|defining_code", "beschreibungDefiningCode", BeschreibungDefiningCode.class, this);

  public SelectAqlField<NullFlavour> BESCHREIBUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(DnrAnordnungEvaluation.class, "/data[at0001]/items[at0006]/null_flavour|defining_code", "beschreibungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> EINZELHEITEN_ZUR_RICHTLINIE = new ListAqlFieldImp<Cluster>(DnrAnordnungEvaluation.class, "/data[at0001]/items[at0052]", "einzelheitenZurRichtlinie", Cluster.class, this);

  public ListSelectAqlField<Cluster> ZEUGE = new ListAqlFieldImp<Cluster>(DnrAnordnungEvaluation.class, "/protocol[at0010]/items[at0025]", "zeuge", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(DnrAnordnungEvaluation.class, "/protocol[at0010]/items[at0061]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(DnrAnordnungEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(DnrAnordnungEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(DnrAnordnungEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DnrAnordnungEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.advance_care_directive.v1");
  }

  public static DnrAnordnungEvaluationContainment getInstance() {
    return new DnrAnordnungEvaluationContainment();
  }
}
