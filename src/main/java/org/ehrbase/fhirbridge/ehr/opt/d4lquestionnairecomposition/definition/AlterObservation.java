package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
@Archetype("openEHR-EHR-OBSERVATION.age.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.762116+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class AlterObservation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Ereigniszeitpunkt/Altersklasse
   * Description: Zeitdauer seit der Geburt, ausgedrückt als festgelegte Altersspanne oder Gruppierung.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value|defining_code")
  private AltersklasseDefiningCode altersklasseDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Event Series/Ereigniszeitpunkt/Tree/Altersklasse/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/null_flavour|defining_code")
  private NullFlavour altersklasseNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Ereigniszeitpunkt/Älter oder gleich 65 Jahre alt?
   * Description: Beschreibung des Alters einer Person, das nicht in anderen Datenelementen dargestellt wird.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006 and name/value='Älter oder gleich 65 Jahre alt?']/value|defining_code")
  private AelterOderGleich65JahreAltDefiningCode aelterOderGleich65JahreAltDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Event Series/Ereigniszeitpunkt/Tree/Älter oder gleich 65 Jahre alt?/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006 and name/value='Älter oder gleich 65 Jahre alt?']/null_flavour|defining_code")
  private NullFlavour aelterOderGleich65JahreAltNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Ereigniszeitpunkt/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0008]/items[at0009]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Alter/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAltersklasseDefiningCode(AltersklasseDefiningCode altersklasseDefiningCode) {
     this.altersklasseDefiningCode = altersklasseDefiningCode;
  }

  public AltersklasseDefiningCode getAltersklasseDefiningCode() {
     return this.altersklasseDefiningCode ;
  }

  public void setAltersklasseNullFlavourDefiningCode(
      NullFlavour altersklasseNullFlavourDefiningCode) {
     this.altersklasseNullFlavourDefiningCode = altersklasseNullFlavourDefiningCode;
  }

  public NullFlavour getAltersklasseNullFlavourDefiningCode() {
     return this.altersklasseNullFlavourDefiningCode ;
  }

  public void setAelterOderGleich65JahreAltDefiningCode(
      AelterOderGleich65JahreAltDefiningCode aelterOderGleich65JahreAltDefiningCode) {
     this.aelterOderGleich65JahreAltDefiningCode = aelterOderGleich65JahreAltDefiningCode;
  }

  public AelterOderGleich65JahreAltDefiningCode getAelterOderGleich65JahreAltDefiningCode() {
     return this.aelterOderGleich65JahreAltDefiningCode ;
  }

  public void setAelterOderGleich65JahreAltNullFlavourDefiningCode(
      NullFlavour aelterOderGleich65JahreAltNullFlavourDefiningCode) {
     this.aelterOderGleich65JahreAltNullFlavourDefiningCode = aelterOderGleich65JahreAltNullFlavourDefiningCode;
  }

  public NullFlavour getAelterOderGleich65JahreAltNullFlavourDefiningCode() {
     return this.aelterOderGleich65JahreAltNullFlavourDefiningCode ;
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
