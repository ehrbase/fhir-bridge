package org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-26T14:55:12.898746+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class KlinischeFrailtySkalaCfsObservation implements EntryEntity {
  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/Beliebige zeitliche Ereignisse/Beurteilung
   * Description: Beurteilung der Gebrechlichkeitsstufe.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
  private DvOrdinal beurteilung;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/History/Beliebige zeitliche Ereignisse/Tree/Beurteilung/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour beurteilungNullFlavourDefiningCode;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/Beliebige zeitliche Ereignisse/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/Erweiterungen
   * Description: Zusätzliche Informationen, die zur Erfassung lokaler Inhalte oder zur Anpassung an andere Referenzmodelle/Formalismen erforderlich sind.
   * Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten, um Verknüpfungen mit FHIR herzustellen.
   */
  @Path("/protocol[at0014]/items[at0015]")
  private List<Cluster> erweiterungen;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Klinische Frailty-Skala/Klinische Frailty-Skala (CFS)/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeurteilung(DvOrdinal beurteilung) {
     this.beurteilung = beurteilung;
  }

  public DvOrdinal getBeurteilung() {
     return this.beurteilung ;
  }

  public void setBeurteilungNullFlavourDefiningCode(
      NullFlavour beurteilungNullFlavourDefiningCode) {
     this.beurteilungNullFlavourDefiningCode = beurteilungNullFlavourDefiningCode;
  }

  public NullFlavour getBeurteilungNullFlavourDefiningCode() {
     return this.beurteilungNullFlavourDefiningCode ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setErweiterungen(List<Cluster> erweiterungen) {
     this.erweiterungen = erweiterungen;
  }

  public List<Cluster> getErweiterungen() {
     return this.erweiterungen ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
