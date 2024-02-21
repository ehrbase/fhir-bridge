package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class GeschlechtEvaluationContainment extends Containment {
  public SelectAqlField<GeschlechtEvaluation> GESCHLECHT_EVALUATION = new AqlFieldImp<GeschlechtEvaluation>(GeschlechtEvaluation.class, "", "GeschlechtEvaluation", GeschlechtEvaluation.class, this);

  public SelectAqlField<DvCodedText> ADMINISTRATIVES_GESCHLECHT = new AqlFieldImp<DvCodedText>(GeschlechtEvaluation.class, "/data[at0002]/items[at0022]/value", "administrativesGeschlecht", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> ADMINISTRATIVES_GESCHLECHT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeschlechtEvaluation.class, "/data[at0002]/items[at0022]/null_flavour|defining_code", "administrativesGeschlechtNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_DETAILS = new ListAqlFieldImp<Cluster>(GeschlechtEvaluation.class, "/data[at0002]/items[at0023]", "zusaetzlicheDetails", Cluster.class, this);

  public SelectAqlField<AnderesGeschlechtAmtlichDefiningCode> ANDERES_GESCHLECHT_AMTLICH_DEFINING_CODE = new AqlFieldImp<AnderesGeschlechtAmtlichDefiningCode>(GeschlechtEvaluation.class, "/data[at0002]/items[at0014]/value|defining_code", "anderesGeschlechtAmtlichDefiningCode", AnderesGeschlechtAmtlichDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ANDERES_GESCHLECHT_AMTLICH_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(GeschlechtEvaluation.class, "/data[at0002]/items[at0014]/null_flavour|defining_code", "anderesGeschlechtAmtlichNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(GeschlechtEvaluation.class, "/protocol[at0003]/items[at0005]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(GeschlechtEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(GeschlechtEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(GeschlechtEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private GeschlechtEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.gender.v1");
  }

  public static GeschlechtEvaluationContainment getInstance() {
    return new GeschlechtEvaluationContainment();
  }
}
