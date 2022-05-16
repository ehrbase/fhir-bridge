package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

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

public class BlutdruckObservationContainment extends Containment {
  public SelectAqlField<BlutdruckObservation> BLUTDRUCK_OBSERVATION = new AqlFieldImp<BlutdruckObservation>(BlutdruckObservation.class, "", "BlutdruckObservation", BlutdruckObservation.class, this);

  public SelectAqlField<Blutdruck24StundenDurchschnittIntervalEvent> N24_STUNDEN_DURCHSCHNITT = new AqlFieldImp<Blutdruck24StundenDurchschnittIntervalEvent>(BlutdruckObservation.class, "/data[at0001]/events[at1042]", "N24StundenDurchschnitt", Blutdruck24StundenDurchschnittIntervalEvent.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(BlutdruckObservation.class, "/data[at0001]/origin|value", "originValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> STELLE_DER_MESSUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(BlutdruckObservation.class, "/protocol[at0011]/items[at0014]/null_flavour|defining_code", "stelleDerMessungNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> STRUKTURIERTE_STELLE_DER_MESSUNG = new ListAqlFieldImp<Cluster>(BlutdruckObservation.class, "/protocol[at0011]/items[at1057]", "strukturierteStelleDerMessung", Cluster.class, this);

  public SelectAqlField<Cluster> GERAET = new AqlFieldImp<Cluster>(BlutdruckObservation.class, "/protocol[at0011]/items[at1025]", "geraet", Cluster.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(BlutdruckObservation.class, "/protocol[at0011]/items[at1058]", "erweiterung", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(BlutdruckObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(BlutdruckObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(BlutdruckObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<BlutdruckBeliebigesEreignisChoice> BELIEBIGES_EREIGNIS = new AqlFieldImp<BlutdruckBeliebigesEreignisChoice>(BlutdruckObservation.class, "/data[at0001]/events[at0006]", "beliebigesEreignis", BlutdruckBeliebigesEreignisChoice.class, this);

  public SelectAqlField<BlutdruckStelleDerMessungChoice> STELLE_DER_MESSUNG = new AqlFieldImp<BlutdruckStelleDerMessungChoice>(BlutdruckObservation.class, "/protocol[at0011]/items[at0014]/value", "stelleDerMessung", BlutdruckStelleDerMessungChoice.class, this);

  private BlutdruckObservationContainment() {
    super("openEHR-EHR-OBSERVATION.blood_pressure.v2");
  }

  public static BlutdruckObservationContainment getInstance() {
    return new BlutdruckObservationContainment();
  }
}
