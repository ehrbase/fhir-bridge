package org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.ItemTree;
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
@Archetype("openEHR-EHR-OBSERVATION.imaging_exam_result.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-03T10:10:16.298539+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class BildgebendesUntersuchungsergebnisObservation implements EntryEntity {
  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/Name der Untersuchung
   * Description: Name der/des durchgeführten bildgebenden Untersuchung oder Verfahrens.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|defining_code")
  private NameDerUntersuchungDefiningCode nameDerUntersuchungDefiningCode;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Event Series/Beliebiges Ereignis/Tree/Name der Untersuchung/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour nameDerUntersuchungNullFlavourDefiningCode;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/Anatomische Position strukturiert
   * Description: Strukturierte Angabenl über die anatomische Stelle in Bezug auf das gesamte Ergebnis.
   * Comment: Die Identifizierung der anatomischen Position in Bezug auf einen bestimmten Befund wird in jeder Instanz von CLUSTER.imaging_finding aufgezeichnet.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
  private List<Cluster> anatomischePositionStrukturiert;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/Befunde
   * Description: Die Beschreibung der klinischen Befunde.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value|defining_code")
  private BefundeDefiningCode befundeDefiningCode;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Event Series/Beliebiges Ereignis/Tree/Befunde/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/null_flavour|defining_code")
  private NullFlavour befundeNullFlavourDefiningCode;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/Bildrepräsentation
   * Description: Digitales Bild oder Video, das das Untersuchungsergebnis darstellt.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0045]")
  private List<Cluster> bildrepraesentation;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/Tree
   * Description: @ internal @
   */
  @Path("/data[at0001]/events[at0002]/state[at0047]")
  private ItemTree tree;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Beliebiges Ereignis/time
   */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/origin
   */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Technisches Verfahren strukturiert
   * Description: Zusätzliche strukturierte Informationen zu technischen Details und Verfahren.
   */
  @Path("/protocol[at0025]/items[at0041]")
  private List<Cluster> technischesVerfahrenStrukturiert;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Empfangende Bildgebungsdienst
   * Description: Demografische Informationen über den Bildgebungsdienst, der die Bildgebungsuntersuchung durchführt.
   */
  @Path("/protocol[at0025]/items[at0026]")
  private List<Cluster> empfangendeBildgebungsdienst;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0025]/items[at0046]")
  private List<Cluster> erweiterung;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Radiologischer Befund/Bildgebendes Untersuchungsergebnis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerUntersuchungDefiningCode(
      NameDerUntersuchungDefiningCode nameDerUntersuchungDefiningCode) {
     this.nameDerUntersuchungDefiningCode = nameDerUntersuchungDefiningCode;
  }

  public NameDerUntersuchungDefiningCode getNameDerUntersuchungDefiningCode() {
     return this.nameDerUntersuchungDefiningCode ;
  }

  public void setNameDerUntersuchungNullFlavourDefiningCode(
      NullFlavour nameDerUntersuchungNullFlavourDefiningCode) {
     this.nameDerUntersuchungNullFlavourDefiningCode = nameDerUntersuchungNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerUntersuchungNullFlavourDefiningCode() {
     return this.nameDerUntersuchungNullFlavourDefiningCode ;
  }

  public void setAnatomischePositionStrukturiert(List<Cluster> anatomischePositionStrukturiert) {
     this.anatomischePositionStrukturiert = anatomischePositionStrukturiert;
  }

  public List<Cluster> getAnatomischePositionStrukturiert() {
     return this.anatomischePositionStrukturiert ;
  }

  public void setBefundeDefiningCode(BefundeDefiningCode befundeDefiningCode) {
     this.befundeDefiningCode = befundeDefiningCode;
  }

  public BefundeDefiningCode getBefundeDefiningCode() {
     return this.befundeDefiningCode ;
  }

  public void setBefundeNullFlavourDefiningCode(NullFlavour befundeNullFlavourDefiningCode) {
     this.befundeNullFlavourDefiningCode = befundeNullFlavourDefiningCode;
  }

  public NullFlavour getBefundeNullFlavourDefiningCode() {
     return this.befundeNullFlavourDefiningCode ;
  }

  public void setBildrepraesentation(List<Cluster> bildrepraesentation) {
     this.bildrepraesentation = bildrepraesentation;
  }

  public List<Cluster> getBildrepraesentation() {
     return this.bildrepraesentation ;
  }

  public void setTree(ItemTree tree) {
     this.tree = tree;
  }

  public ItemTree getTree() {
     return this.tree ;
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

  public void setTechnischesVerfahrenStrukturiert(List<Cluster> technischesVerfahrenStrukturiert) {
     this.technischesVerfahrenStrukturiert = technischesVerfahrenStrukturiert;
  }

  public List<Cluster> getTechnischesVerfahrenStrukturiert() {
     return this.technischesVerfahrenStrukturiert ;
  }

  public void setEmpfangendeBildgebungsdienst(List<Cluster> empfangendeBildgebungsdienst) {
     this.empfangendeBildgebungsdienst = empfangendeBildgebungsdienst;
  }

  public List<Cluster> getEmpfangendeBildgebungsdienst() {
     return this.empfangendeBildgebungsdienst ;
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
