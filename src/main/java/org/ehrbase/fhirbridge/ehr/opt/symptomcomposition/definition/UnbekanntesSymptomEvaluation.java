package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.absence.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-28T16:11:42.498962+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class UnbekanntesSymptomEvaluation implements EntryEntity {
  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Unbekanntes Symptom
   * Description: Positive Aussage, dass keine Informationen verfügbar sind.
   * Comment: Zum Beispiel: "Es liegen keine Informationen über Nebenwirkungen vor"; "Es liegen keine Informationen über Probleme oder Diagnosen vor"; "Es liegen keine Informationen über vorangegangene Verfahren vor"; oder "Es liegen keine Informationen über verwendete Medikamente vor".
   */
  @Path("/data[at0001]/items[at0002 and name/value='Unbekanntes Symptom']/value")
  private DvCodedText unbekanntesSymptom;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Baum/Unbekanntes Symptom/null_flavour
   */
  @Path("/data[at0001]/items[at0002 and name/value='Unbekanntes Symptom']/null_flavour|defining_code")
  private NullFlavour unbekanntesSymptomNullFlavourDefiningCode;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Aussage über die fehlende Information
   * Description: Beschreibung des Grundes, warum keine Informationen vorhanden sind.
   * Comment: Zum Beispiel: Der Patient ist bewusstlos oder weigert sich Informationen preiszugeben. Die Codierung mit einer Terminologie wird empfohlen, wenn möglich.
   */
  @Path("/data[at0001]/items[at0005 and name/value='Aussage über die fehlende Information']")
  private List<UnbekanntesSymptomAussageUeberDieFehlendeInformationElement> aussageUeberDieFehlendeInformation;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Kommentar: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0003]/items[at0006]")
  private List<Cluster> erweiterung;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setUnbekanntesSymptom(DvCodedText unbekanntesSymptom) {
     this.unbekanntesSymptom = unbekanntesSymptom;
  }

  public DvCodedText getUnbekanntesSymptom() {
     return this.unbekanntesSymptom ;
  }

  public void setUnbekanntesSymptomNullFlavourDefiningCode(
      NullFlavour unbekanntesSymptomNullFlavourDefiningCode) {
     this.unbekanntesSymptomNullFlavourDefiningCode = unbekanntesSymptomNullFlavourDefiningCode;
  }

  public NullFlavour getUnbekanntesSymptomNullFlavourDefiningCode() {
     return this.unbekanntesSymptomNullFlavourDefiningCode ;
  }

  public void setAussageUeberDieFehlendeInformation(
      List<UnbekanntesSymptomAussageUeberDieFehlendeInformationElement> aussageUeberDieFehlendeInformation) {
     this.aussageUeberDieFehlendeInformation = aussageUeberDieFehlendeInformation;
  }

  public List<UnbekanntesSymptomAussageUeberDieFehlendeInformationElement> getAussageUeberDieFehlendeInformation(
      ) {
     return this.aussageUeberDieFehlendeInformation ;
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
