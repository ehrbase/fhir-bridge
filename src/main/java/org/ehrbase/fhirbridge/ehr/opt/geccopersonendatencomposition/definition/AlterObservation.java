package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.age.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:53:37.003347+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class AlterObservation implements EntryEntity {
  /**
   * Path: GECCO_Personendaten/Alter/Ereigniszeitpunkt/Alter
   * Description: Zeitdauer seit der Geburt.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Alter']/value|value")
  private TemporalAmount alterValue;

  /**
   * Path: GECCO_Personendaten/Alter/Event Series/Ereigniszeitpunkt/Tree/Alter/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Alter']/null_flavour|defining_code")
  private NullFlavour alterNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Alter/Ereigniszeitpunkt/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: GECCO_Personendaten/Alter/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: GECCO_Personendaten/Alter/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0008]/items[at0009]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Personendaten/Alter/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Personendaten/Alter/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Personendaten/Alter/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAlterValue(TemporalAmount alterValue) {
     this.alterValue = alterValue;
  }

  public TemporalAmount getAlterValue() {
     return this.alterValue ;
  }

  public void setAlterNullFlavourDefiningCode(NullFlavour alterNullFlavourDefiningCode) {
     this.alterNullFlavourDefiningCode = alterNullFlavourDefiningCode;
  }

  public NullFlavour getAlterNullFlavourDefiningCode() {
     return this.alterNullFlavourDefiningCode ;
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

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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
