package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
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
    date = "2021-09-06T16:23:11.852486+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class UnbekannteProzedurEvaluation implements EntryEntity {
  /**
   * Path: Registereintrag/Unbekannte Prozedur/Unbekannte Prozedur
   * Description: Positive Aussage, dass keine Informationen verfügbar sind.
   * Comment: Zum Beispiel: "Es liegen keine Informationen über Nebenwirkungen vor"; "Es liegen keine Informationen über Probleme oder Diagnosen vor"; "Es liegen keine Informationen über vorangegangene Verfahren vor"; oder "Es liegen keine Informationen über verwendete Medikamente vor".
   */
  @Path("/data[at0001]/items[at0002 and name/value='Unbekannte Prozedur']/value|defining_code")
  private NameDerProzedurDefiningCode unbekannteProzedurDefiningCode;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/Baum/Unbekannte Prozedur/null_flavour
   */
  @Path("/data[at0001]/items[at0002 and name/value='Unbekannte Prozedur']/null_flavour|defining_code")
  private NullFlavour unbekannteProzedurNullFlavourDefiningCode;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/Aussage über die fehlende Information
   * Description: Beschreibung des Grundes, warum keine Informationen vorhanden sind.
   * Comment: Zum Beispiel: Der Patient ist bewusstlos oder weigert sich Informationen preiszugeben. Die Codierung mit einer Terminologie wird empfohlen, wenn möglich.
   */
  @Path("/data[at0001]/items[at0005 and name/value='Aussage über die fehlende Information']/value|value")
  private String aussageUeberDieFehlendeInformationValue;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/Baum/Aussage über die fehlende Information/null_flavour
   */
  @Path("/data[at0001]/items[at0005 and name/value='Aussage über die fehlende Information']/null_flavour|defining_code")
  private NullFlavour aussageUeberDieFehlendeInformationNullFlavourDefiningCode;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Kommentar: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0003]/items[at0006]")
  private List<Cluster> erweiterung;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Registereintrag/Unbekannte Prozedur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setUnbekannteProzedurDefiningCode(
      NameDerProzedurDefiningCode unbekannteProzedurDefiningCode) {
     this.unbekannteProzedurDefiningCode = unbekannteProzedurDefiningCode;
  }

  public NameDerProzedurDefiningCode getUnbekannteProzedurDefiningCode() {
     return this.unbekannteProzedurDefiningCode ;
  }

  public void setUnbekannteProzedurNullFlavourDefiningCode(
      NullFlavour unbekannteProzedurNullFlavourDefiningCode) {
     this.unbekannteProzedurNullFlavourDefiningCode = unbekannteProzedurNullFlavourDefiningCode;
  }

  public NullFlavour getUnbekannteProzedurNullFlavourDefiningCode() {
     return this.unbekannteProzedurNullFlavourDefiningCode ;
  }

  public void setAussageUeberDieFehlendeInformationValue(
      String aussageUeberDieFehlendeInformationValue) {
     this.aussageUeberDieFehlendeInformationValue = aussageUeberDieFehlendeInformationValue;
  }

  public String getAussageUeberDieFehlendeInformationValue() {
     return this.aussageUeberDieFehlendeInformationValue ;
  }

  public void setAussageUeberDieFehlendeInformationNullFlavourDefiningCode(
      NullFlavour aussageUeberDieFehlendeInformationNullFlavourDefiningCode) {
     this.aussageUeberDieFehlendeInformationNullFlavourDefiningCode = aussageUeberDieFehlendeInformationNullFlavourDefiningCode;
  }

  public NullFlavour getAussageUeberDieFehlendeInformationNullFlavourDefiningCode() {
     return this.aussageUeberDieFehlendeInformationNullFlavourDefiningCode ;
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
