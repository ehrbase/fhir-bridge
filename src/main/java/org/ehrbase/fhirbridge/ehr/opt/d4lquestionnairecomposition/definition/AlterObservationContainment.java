package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class AlterObservationContainment extends Containment {
  public SelectAqlField<AlterObservation> ALTER_OBSERVATION = new AqlFieldImp<AlterObservation>(AlterObservation.class, "", "AlterObservation", AlterObservation.class, this);

  public SelectAqlField<AltersklasseDefiningCode> ALTERSKLASSE_DEFINING_CODE = new AqlFieldImp<AltersklasseDefiningCode>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|defining_code", "altersklasseDefiningCode", AltersklasseDefiningCode.class, this);

  public SelectAqlField<NullFlavour> ALTERSKLASSE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0007]/null_flavour|defining_code", "altersklasseNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<AelterOderGleich65JahreAltDefiningCode> AELTER_ODER_GLEICH65_JAHRE_ALT_DEFINING_CODE = new AqlFieldImp<AelterOderGleich65JahreAltDefiningCode>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value|defining_code", "aelterOderGleich65JahreAltDefiningCode", AelterOderGleich65JahreAltDefiningCode.class, this);

  public SelectAqlField<NullFlavour> AELTER_ODER_GLEICH65_JAHRE_ALT_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(AlterObservation.class, "/data[at0001]/events[at0002]/data[at0003]/items[at0006]/null_flavour|defining_code", "aelterOderGleich65JahreAltNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(AlterObservation.class, "/data[at0001]/events[at0002]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(AlterObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(AlterObservation.class, "/protocol[at0008]/items[at0009]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(AlterObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(AlterObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(AlterObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AlterObservationContainment() {
    super("openEHR-EHR-OBSERVATION.age.v0");
  }

  public static AlterObservationContainment getInstance() {
    return new AlterObservationContainment();
  }
}
