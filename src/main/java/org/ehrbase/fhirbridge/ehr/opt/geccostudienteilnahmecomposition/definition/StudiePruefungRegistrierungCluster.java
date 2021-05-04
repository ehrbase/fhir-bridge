package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-04T17:37:36.540709200+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
public class StudiePruefungRegistrierungCluster implements LocatableEntity {
  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Registrierung/Registername
   * Description: Studienregister, wo die Studie registriert ist und eine eindeutige Identifikationsnummer besitzt.
   * Comment: Zum Beispiel: Europäischen Arzneimittelagentur (EudraCT) oder Webseite Clinicaltrials.gov (US NCT-Nummer).
   */
  @Path("/items[at0035]/value|defining_code")
  private RegisternameDefiningCode registernameDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Studienteilnahme/Studie/Prüfung/Registrierung/Registername/null_flavour
   */
  @Path("/items[at0035]/null_flavour|defining_code")
  private NullFlavour registernameNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Registrierung/Registrierungsnummer
   * Description: Eindeutige Identifikationsnummer an dem angezeigten Register.
   * Comment: Zum Beispiel die EudraCT Nummer, die von der Europäischen Arzneimittelagentur vergeben wird, oder ISRCTN (International Standard Randomised Controlled Trial Number). 
   * Wenn die klinische Prüfung auf der Webseite Current Controlled Trials registriert ist, besitzt sie eine ISRCTN-Nummer.
   */
  @Path("/items[at0034]/value|value")
  private String registrierungsnummerValue;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Studienteilnahme/Studie/Prüfung/Registrierung/Registrierungsnummer/null_flavour
   */
  @Path("/items[at0034]/null_flavour|defining_code")
  private NullFlavour registrierungsnummerNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Registrierung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRegisternameDefiningCode(RegisternameDefiningCode registernameDefiningCode) {
     this.registernameDefiningCode = registernameDefiningCode;
  }

  public RegisternameDefiningCode getRegisternameDefiningCode() {
     return this.registernameDefiningCode ;
  }

  public void setRegisternameNullFlavourDefiningCode(
      NullFlavour registernameNullFlavourDefiningCode) {
     this.registernameNullFlavourDefiningCode = registernameNullFlavourDefiningCode;
  }

  public NullFlavour getRegisternameNullFlavourDefiningCode() {
     return this.registernameNullFlavourDefiningCode ;
  }

  public void setRegistrierungsnummerValue(String registrierungsnummerValue) {
     this.registrierungsnummerValue = registrierungsnummerValue;
  }

  public String getRegistrierungsnummerValue() {
     return this.registrierungsnummerValue ;
  }

  public void setRegistrierungsnummerNullFlavourDefiningCode(
      NullFlavour registrierungsnummerNullFlavourDefiningCode) {
     this.registrierungsnummerNullFlavourDefiningCode = registrierungsnummerNullFlavourDefiningCode;
  }

  public NullFlavour getRegistrierungsnummerNullFlavourDefiningCode() {
     return this.registrierungsnummerNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
