package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.diagnose_details.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.860989+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class DiagnosedetailsCluster implements LocatableEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Begründung von Ausnahmen
   * Description: Das Auftreten einer Diagnose muss in manchen Fällen zu Abrechnungszwecken begründet werden, z.B. für geschlechtsspezifische Plausibilitätsprüfungen.
   */
  @Path("/items[at0001]/value|value")
  private String begruendungVonAusnahmenValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnosedetails/Begründung von Ausnahmen/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour begruendungVonAusnahmenNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Diagnosetyp
   * Description: Art der Diagnose. Bei der Angabe des Diagnosetyps ist darauf zu achten, dass dieser auch im richtigen Kontext verwendet wird. Zum Beispiel wird es bei der Beschreibung einer Diagnose im Rahmen einer Überweisung nicht den Diagnosetyp "Entlassdiagnose" geben.
   */
  @Path("/items[at0002]/value|value")
  private String diagnosetypValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnosedetails/Diagnosetyp/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour diagnosetypNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Aufnahme
   * Description: Ist die Diagnose bei der Aufnahme in die Gesundheiteinrichtung vorhanden?
   */
  @Path("/items[at0016]/value|value")
  private Boolean vorhandenBeiAufnahmeValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnosedetails/Vorhanden bei Aufnahme/null_flavour
   */
  @Path("/items[at0016]/null_flavour|defining_code")
  private NullFlavour vorhandenBeiAufnahmeNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/Vorhanden bei Entlassung
   * Description: Ist die Diagnose bei Entlassung aus der Gesundheiteinrichtung vorhanden?
   */
  @Path("/items[at0017]/value|value")
  private Boolean vorhandenBeiEntlassungValue;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Diagnosedetails/Vorhanden bei Entlassung/null_flavour
   */
  @Path("/items[at0017]/null_flavour|defining_code")
  private NullFlavour vorhandenBeiEntlassungNullFlavourDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Diagnosedetails/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBegruendungVonAusnahmenValue(String begruendungVonAusnahmenValue) {
     this.begruendungVonAusnahmenValue = begruendungVonAusnahmenValue;
  }

  public String getBegruendungVonAusnahmenValue() {
     return this.begruendungVonAusnahmenValue ;
  }

  public void setBegruendungVonAusnahmenNullFlavourDefiningCode(
      NullFlavour begruendungVonAusnahmenNullFlavourDefiningCode) {
     this.begruendungVonAusnahmenNullFlavourDefiningCode = begruendungVonAusnahmenNullFlavourDefiningCode;
  }

  public NullFlavour getBegruendungVonAusnahmenNullFlavourDefiningCode() {
     return this.begruendungVonAusnahmenNullFlavourDefiningCode ;
  }

  public void setDiagnosetypValue(String diagnosetypValue) {
     this.diagnosetypValue = diagnosetypValue;
  }

  public String getDiagnosetypValue() {
     return this.diagnosetypValue ;
  }

  public void setDiagnosetypNullFlavourDefiningCode(
      NullFlavour diagnosetypNullFlavourDefiningCode) {
     this.diagnosetypNullFlavourDefiningCode = diagnosetypNullFlavourDefiningCode;
  }

  public NullFlavour getDiagnosetypNullFlavourDefiningCode() {
     return this.diagnosetypNullFlavourDefiningCode ;
  }

  public void setVorhandenBeiAufnahmeValue(Boolean vorhandenBeiAufnahmeValue) {
     this.vorhandenBeiAufnahmeValue = vorhandenBeiAufnahmeValue;
  }

  public Boolean isVorhandenBeiAufnahmeValue() {
     return this.vorhandenBeiAufnahmeValue ;
  }

  public void setVorhandenBeiAufnahmeNullFlavourDefiningCode(
      NullFlavour vorhandenBeiAufnahmeNullFlavourDefiningCode) {
     this.vorhandenBeiAufnahmeNullFlavourDefiningCode = vorhandenBeiAufnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getVorhandenBeiAufnahmeNullFlavourDefiningCode() {
     return this.vorhandenBeiAufnahmeNullFlavourDefiningCode ;
  }

  public void setVorhandenBeiEntlassungValue(Boolean vorhandenBeiEntlassungValue) {
     this.vorhandenBeiEntlassungValue = vorhandenBeiEntlassungValue;
  }

  public Boolean isVorhandenBeiEntlassungValue() {
     return this.vorhandenBeiEntlassungValue ;
  }

  public void setVorhandenBeiEntlassungNullFlavourDefiningCode(
      NullFlavour vorhandenBeiEntlassungNullFlavourDefiningCode) {
     this.vorhandenBeiEntlassungNullFlavourDefiningCode = vorhandenBeiEntlassungNullFlavourDefiningCode;
  }

  public NullFlavour getVorhandenBeiEntlassungNullFlavourDefiningCode() {
     return this.vorhandenBeiEntlassungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
