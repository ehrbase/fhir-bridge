package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class VorliegendesSymptomObservationContainment extends Containment {
  public SelectAqlField<VorliegendesSymptomObservation> VORLIEGENDES_SYMPTOM_OBSERVATION = new AqlFieldImp<VorliegendesSymptomObservation>(VorliegendesSymptomObservation.class, "", "VorliegendesSymptomObservation", VorliegendesSymptomObservation.class, this);

  public SelectAqlField<DvCodedText> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS = new AqlFieldImp<DvCodedText>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0001]/value", "nameDesSymptomsKrankheitsanzeichens", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> NAME_DES_SYMPTOMS_KRANKHEITSANZEICHENS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0001]/null_flavour|defining_code", "nameDesSymptomsKrankheitsanzeichensNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<VorliegendesSymptomAnatomischeLokalisationElement> ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<VorliegendesSymptomAnatomischeLokalisationElement>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0151]", "anatomischeLokalisation", VorliegendesSymptomAnatomischeLokalisationElement.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_ANATOMISCHE_LOKALISATION = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0147]", "spezifischeAnatomischeLokalisation", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> BEGINN_DER_EPISODE_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0152]/value|value", "beginnDerEpisodeValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> BEGINN_DER_EPISODE_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0152]/null_flavour|defining_code", "beginnDerEpisodeNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<DvCodedText> SCHWEREGRAD = new AqlFieldImp<DvCodedText>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0021]/value", "schweregrad", DvCodedText.class, this);

  public SelectAqlField<NullFlavour> SCHWEREGRAD_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0021]/null_flavour|defining_code", "schweregradNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> SPEZIFISCHE_DETAILS = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0153]", "spezifischeDetails", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_UHRZEIT_DES_RUECKGANGS_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0161]/value|value", "datumUhrzeitDesRueckgangsValue", TemporalAccessor.class, this);

  public SelectAqlField<NullFlavour> DATUM_UHRZEIT_DES_RUECKGANGS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0161]/null_flavour|defining_code", "datumUhrzeitDesRueckgangsNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> VORANGEGANGENE_EPISODEN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0146]", "vorangegangeneEpisoden", Cluster.class, this);

  public ListSelectAqlField<Cluster> ASSOZIIERTE_SYMPTOME_KRANKHEITSANZEICHEN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/data[at0192]/items[at0063]", "assoziierteSymptomeKrankheitsanzeichen", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> TIME_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/events[at0191]/time|value", "timeValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAccessor> ORIGIN_VALUE = new AqlFieldImp<TemporalAccessor>(VorliegendesSymptomObservation.class, "/data[at0190]/origin|value", "originValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Cluster> EXTENSION_EN = new ListAqlFieldImp<Cluster>(VorliegendesSymptomObservation.class, "/protocol[at0193]/items[at0194]", "extensionEn", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(VorliegendesSymptomObservation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(VorliegendesSymptomObservation.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(VorliegendesSymptomObservation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VorliegendesSymptomObservationContainment() {
    super("openEHR-EHR-OBSERVATION.symptom_sign.v0");
  }

  public static VorliegendesSymptomObservationContainment getInstance() {
    return new VorliegendesSymptomObservationContainment();
  }
}
