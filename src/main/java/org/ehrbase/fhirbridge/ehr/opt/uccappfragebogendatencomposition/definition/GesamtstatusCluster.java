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
    date = "2022-05-10T15:34:39.430040960+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class GesamtstatusCluster implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Element ID
   * Description: Identifikationsnummer oder Identifikationstext des Fragebogeneintrags.
   */
  @Path("/items[at0002]/value")
  private DvIdentifier elementId;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Element ID/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour elementIdNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage
   * Description: Fragebogentext der dem Patienten präsentiert wird.
   */
  @Path("/items[at0001]/value|value")
  private String frageValue;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Frage/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour frageNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Antwort
   * Description: Antworttext, der vom Patienten zurückgegeben oder ausgewählt wird.
   */
  @Path("/items[at0003]/value|value")
  private String antwortValue;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Antwort/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour antwortNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Score
   * Description: Scoringwert, der das Ergebnis numerisch repräsentiert.
   */
  @Path("/items[at0004]/value|magnitude")
  private Double scoreMagnitude;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Score
   * Description: Scoringwert, der das Ergebnis numerisch repräsentiert.
   */
  @Path("/items[at0004]/value|units")
  private String scoreUnits;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Event Series/Jedes Ereignis/Baum/Gesamtstatus/Score/null_flavour
   */
  @Path("/items[at0004]/null_flavour|defining_code")
  private NullFlavour scoreNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 1
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 1']")
  private Frage1Cluster frage1;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 2
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 2']")
  private Frage2Cluster frage2;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 3
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 3']")
  private Frage3Cluster frage3;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 4
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 4']")
  private Frage4Cluster frage4;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 5
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 5']")
  private Frage5Cluster frage5;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 6
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 6']")
  private Frage6Cluster frage6;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 7
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 7']")
  private Frage7Cluster frage7;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/Frage 8
   * Description: Zur Abbildung insbesonde von verschachtelten Fragebogeneinträgen/-elementen und Unterpunkten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.questionnaire_item.v0 and name/value='Frage 8']")
  private Frage8Cluster frage8;

  /**
   * Path: Selbstüberwachung/Gesamtergebnis/Jedes Ereignis/Gesamtstatus/feeder_audit
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

  public void setFrage1(Frage1Cluster frage1) {
     this.frage1 = frage1;
  }

  public Frage1Cluster getFrage1() {
     return this.frage1 ;
  }

  public void setFrage2(Frage2Cluster frage2) {
     this.frage2 = frage2;
  }

  public Frage2Cluster getFrage2() {
     return this.frage2 ;
  }

  public void setFrage3(Frage3Cluster frage3) {
     this.frage3 = frage3;
  }

  public Frage3Cluster getFrage3() {
     return this.frage3 ;
  }

  public void setFrage4(Frage4Cluster frage4) {
     this.frage4 = frage4;
  }

  public Frage4Cluster getFrage4() {
     return this.frage4 ;
  }

  public void setFrage5(Frage5Cluster frage5) {
     this.frage5 = frage5;
  }

  public Frage5Cluster getFrage5() {
     return this.frage5 ;
  }

  public void setFrage6(Frage6Cluster frage6) {
     this.frage6 = frage6;
  }

  public Frage6Cluster getFrage6() {
     return this.frage6 ;
  }

  public void setFrage7(Frage7Cluster frage7) {
     this.frage7 = frage7;
  }

  public Frage7Cluster getFrage7() {
     return this.frage7 ;
  }

  public void setFrage8(Frage8Cluster frage8) {
     this.frage8 = frage8;
  }

  public Frage8Cluster getFrage8() {
     return this.frage8 ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
