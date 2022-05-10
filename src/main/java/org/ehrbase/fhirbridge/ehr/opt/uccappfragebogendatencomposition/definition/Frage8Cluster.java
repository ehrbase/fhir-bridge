package org.ehrbase.fhirbridge.ehr.opt.uccappfragebogendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.questionnaire_item.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-10T15:34:39.534812016+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class Frage8Cluster implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Element ID
   * Description: Identifikationsnummer oder Identifikationstext des Fragebogeneintrags.
   */
  @Path("/items[at0002]/value")
  private DvIdentifier elementId;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Frage 8/Element ID/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour elementIdNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Frage
   * Description: Fragebogentext der dem Patienten präsentiert wird.
   */
  @Path("/items[at0001]/value|value")
  private String frageValue;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Frage 8/Frage/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour frageNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Antwort
   * Description: Antworttext, der vom Patienten zurückgegeben oder ausgewählt wird.
   */
  @Path("/items[at0003]/value|value")
  private String antwortValue;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Frage 8/Antwort/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour antwortNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Score
   * Description: Scoringwert, der das Ergebnis numerisch repräsentiert.
   */
  @Path("/items[at0004]/value|magnitude")
  private Double scoreMagnitude;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Score
   * Description: Scoringwert, der das Ergebnis numerisch repräsentiert.
   */
  @Path("/items[at0004]/value|units")
  private String scoreUnits;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Frage 8/Score/null_flavour
   */
  @Path("/items[at0004]/null_flavour|defining_code")
  private NullFlavour scoreNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Frage 8a
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 8a']")
  private Frage8aCluster frage8a;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Frage 8b
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 8b']")
  private Frage8bCluster frage8b;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/Frage 8c
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 8c']")
  private Frage8cCluster frage8c;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setElementId(DvIdentifier elementId) {
     this.elementId = elementId;
  }

  public DvIdentifier getElementId() {
     return this.elementId ;
  }

  public void setElementIdNullFlavourDefiningCode(NullFlavour elementIdNullFlavourDefiningCode) {
     this.elementIdNullFlavourDefiningCode = elementIdNullFlavourDefiningCode;
  }

  public NullFlavour getElementIdNullFlavourDefiningCode() {
     return this.elementIdNullFlavourDefiningCode ;
  }

  public void setFrageValue(String frageValue) {
     this.frageValue = frageValue;
  }

  public String getFrageValue() {
     return this.frageValue ;
  }

  public void setFrageNullFlavourDefiningCode(NullFlavour frageNullFlavourDefiningCode) {
     this.frageNullFlavourDefiningCode = frageNullFlavourDefiningCode;
  }

  public NullFlavour getFrageNullFlavourDefiningCode() {
     return this.frageNullFlavourDefiningCode ;
  }

  public void setAntwortValue(String antwortValue) {
     this.antwortValue = antwortValue;
  }

  public String getAntwortValue() {
     return this.antwortValue ;
  }

  public void setAntwortNullFlavourDefiningCode(NullFlavour antwortNullFlavourDefiningCode) {
     this.antwortNullFlavourDefiningCode = antwortNullFlavourDefiningCode;
  }

  public NullFlavour getAntwortNullFlavourDefiningCode() {
     return this.antwortNullFlavourDefiningCode ;
  }

  public void setScoreMagnitude(Double scoreMagnitude) {
     this.scoreMagnitude = scoreMagnitude;
  }

  public Double getScoreMagnitude() {
     return this.scoreMagnitude ;
  }

  public void setScoreUnits(String scoreUnits) {
     this.scoreUnits = scoreUnits;
  }

  public String getScoreUnits() {
     return this.scoreUnits ;
  }

  public void setScoreNullFlavourDefiningCode(NullFlavour scoreNullFlavourDefiningCode) {
     this.scoreNullFlavourDefiningCode = scoreNullFlavourDefiningCode;
  }

  public NullFlavour getScoreNullFlavourDefiningCode() {
     return this.scoreNullFlavourDefiningCode ;
  }

  public void setFrage8a(Frage8aCluster frage8a) {
     this.frage8a = frage8a;
  }

  public Frage8aCluster getFrage8a() {
     return this.frage8a ;
  }

  public void setFrage8b(Frage8bCluster frage8b) {
     this.frage8b = frage8b;
  }

  public Frage8bCluster getFrage8b() {
     return this.frage8b ;
  }

  public void setFrage8c(Frage8cCluster frage8c) {
     this.frage8c = frage8c;
  }

  public Frage8cCluster getFrage8c() {
     return this.frage8c ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
