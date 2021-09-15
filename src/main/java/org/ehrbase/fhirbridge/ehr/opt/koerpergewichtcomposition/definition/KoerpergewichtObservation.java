package org.ehrbase.fhirbridge.ehr.opt.koerpergewichtcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Double;
import java.lang.String;
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
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-06T17:51:35.229119+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class KoerpergewichtObservation implements EntryEntity {
  /**
   * Path: Körpergewicht/Körpergewicht/Beliebiges Ereignis/Gewicht
   * Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double gewichtMagnitude;

  /**
   * Path: Körpergewicht/Körpergewicht/Beliebiges Ereignis/Gewicht
   * Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String gewichtUnits;

  /**
   * Path: Körpergewicht/Körpergewicht/History/Beliebiges Ereignis/Simple/Gewicht/null_flavour
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour gewichtNullFlavourDefiningCode;

  /**
   * Path: Körpergewicht/Körpergewicht/Beliebiges Ereignis/State structure
   * Description: @ internal @
   */
  @Path("/data[at0002]/events[at0003]/state[at0008]")
  private ItemTree stateStructure;

  /**
   * Path: Körpergewicht/Körpergewicht/Beliebiges Ereignis/time
   */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Körpergewicht/Körpergewicht/origin
   */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Körpergewicht/Körpergewicht/Gerät
   * Description: Details über die benutzte Waage.
   */
  @Path("/protocol[at0015]/items[at0020]")
  private Cluster geraet;

  /**
   * Path: Körpergewicht/Körpergewicht/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0015]/items[at0027]")
  private List<Cluster> erweiterung;

  /**
   * Path: Körpergewicht/Körpergewicht/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Körpergewicht/Körpergewicht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Körpergewicht/Körpergewicht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGewichtMagnitude(Double gewichtMagnitude) {
     this.gewichtMagnitude = gewichtMagnitude;
  }

  public Double getGewichtMagnitude() {
     return this.gewichtMagnitude ;
  }

  public void setGewichtUnits(String gewichtUnits) {
     this.gewichtUnits = gewichtUnits;
  }

  public String getGewichtUnits() {
     return this.gewichtUnits ;
  }

  public void setGewichtNullFlavourDefiningCode(NullFlavour gewichtNullFlavourDefiningCode) {
     this.gewichtNullFlavourDefiningCode = gewichtNullFlavourDefiningCode;
  }

  public NullFlavour getGewichtNullFlavourDefiningCode() {
     return this.gewichtNullFlavourDefiningCode ;
  }

  public void setStateStructure(ItemTree stateStructure) {
     this.stateStructure = stateStructure;
  }

  public ItemTree getStateStructure() {
     return this.stateStructure ;
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

  public void setGeraet(Cluster geraet) {
     this.geraet = geraet;
  }

  public Cluster getGeraet() {
     return this.geraet ;
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
