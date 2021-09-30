package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

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
    date = "2021-09-30T16:13:57.831551+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class EinzelheitenDerKommunikationKontaktdatenCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Kontakttyp
   * Description: Typ des Kontakts z.B. Telefon, Fax, Pager etc. ENV 13606-4:2000 7.11.19.
   */
  @Path("/items[at0004]/value|value")
  private String kontakttypValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Kontakttyp/null_flavour
   */
  @Path("/items[at0004]/null_flavour|defining_code")
  private NullFlavour kontakttypNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Unstrukturierte Kontaktadresse
   * Description: Eine unstrukturierte Zusammenfassung der Kontaktadresse.
   */
  @Path("/items[at0002]/value|value")
  private String unstrukturierteKontaktadresseValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Unstrukturierte Kontaktadresse/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour unstrukturierteKontaktadresseNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Landesvorwahl
   * Description: Landesvorwahl. ENV13606-4:2000 7.11.18.
   */
  @Path("/items[at0003]/items[at0005]/value|value")
  private String landesvorwahlValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Landesvorwahl/null_flavour
   */
  @Path("/items[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour landesvorwahlNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Ortsvorwahl
   * Description: Telefonvorwahl.
   */
  @Path("/items[at0003]/items[at0006]/value|value")
  private String ortsvorwahlValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Ortsvorwahl/null_flavour
   */
  @Path("/items[at0003]/items[at0006]/null_flavour|defining_code")
  private NullFlavour ortsvorwahlNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Nummer
   * Description: Telefonnummer.
   */
  @Path("/items[at0003]/items[at0007]/value|value")
  private String nummerValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Nummer/null_flavour
   */
  @Path("/items[at0003]/items[at0007]/null_flavour|defining_code")
  private NullFlavour nummerNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Durchwahl
   * Description: Durchwahl des Telefons. ENV13606-4:2000 7.11.18.
   */
  @Path("/items[at0003]/items[at0019]/value|value")
  private String durchwahlValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Kontaktdaten/Strukturierte Kontaktadresse/Durchwahl/null_flavour
   */
  @Path("/items[at0003]/items[at0019]/null_flavour|defining_code")
  private NullFlavour durchwahlNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setKontakttypValue(String kontakttypValue) {
     this.kontakttypValue = kontakttypValue;
  }

  public String getKontakttypValue() {
     return this.kontakttypValue ;
  }

  public void setKontakttypNullFlavourDefiningCode(NullFlavour kontakttypNullFlavourDefiningCode) {
     this.kontakttypNullFlavourDefiningCode = kontakttypNullFlavourDefiningCode;
  }

  public NullFlavour getKontakttypNullFlavourDefiningCode() {
     return this.kontakttypNullFlavourDefiningCode ;
  }

  public void setUnstrukturierteKontaktadresseValue(String unstrukturierteKontaktadresseValue) {
     this.unstrukturierteKontaktadresseValue = unstrukturierteKontaktadresseValue;
  }

  public String getUnstrukturierteKontaktadresseValue() {
     return this.unstrukturierteKontaktadresseValue ;
  }

  public void setUnstrukturierteKontaktadresseNullFlavourDefiningCode(
      NullFlavour unstrukturierteKontaktadresseNullFlavourDefiningCode) {
     this.unstrukturierteKontaktadresseNullFlavourDefiningCode = unstrukturierteKontaktadresseNullFlavourDefiningCode;
  }

  public NullFlavour getUnstrukturierteKontaktadresseNullFlavourDefiningCode() {
     return this.unstrukturierteKontaktadresseNullFlavourDefiningCode ;
  }

  public void setLandesvorwahlValue(String landesvorwahlValue) {
     this.landesvorwahlValue = landesvorwahlValue;
  }

  public String getLandesvorwahlValue() {
     return this.landesvorwahlValue ;
  }

  public void setLandesvorwahlNullFlavourDefiningCode(
      NullFlavour landesvorwahlNullFlavourDefiningCode) {
     this.landesvorwahlNullFlavourDefiningCode = landesvorwahlNullFlavourDefiningCode;
  }

  public NullFlavour getLandesvorwahlNullFlavourDefiningCode() {
     return this.landesvorwahlNullFlavourDefiningCode ;
  }

  public void setOrtsvorwahlValue(String ortsvorwahlValue) {
     this.ortsvorwahlValue = ortsvorwahlValue;
  }

  public String getOrtsvorwahlValue() {
     return this.ortsvorwahlValue ;
  }

  public void setOrtsvorwahlNullFlavourDefiningCode(
      NullFlavour ortsvorwahlNullFlavourDefiningCode) {
     this.ortsvorwahlNullFlavourDefiningCode = ortsvorwahlNullFlavourDefiningCode;
  }

  public NullFlavour getOrtsvorwahlNullFlavourDefiningCode() {
     return this.ortsvorwahlNullFlavourDefiningCode ;
  }

  public void setNummerValue(String nummerValue) {
     this.nummerValue = nummerValue;
  }

  public String getNummerValue() {
     return this.nummerValue ;
  }

  public void setNummerNullFlavourDefiningCode(NullFlavour nummerNullFlavourDefiningCode) {
     this.nummerNullFlavourDefiningCode = nummerNullFlavourDefiningCode;
  }

  public NullFlavour getNummerNullFlavourDefiningCode() {
     return this.nummerNullFlavourDefiningCode ;
  }

  public void setDurchwahlValue(String durchwahlValue) {
     this.durchwahlValue = durchwahlValue;
  }

  public String getDurchwahlValue() {
     return this.durchwahlValue ;
  }

  public void setDurchwahlNullFlavourDefiningCode(NullFlavour durchwahlNullFlavourDefiningCode) {
     this.durchwahlNullFlavourDefiningCode = durchwahlNullFlavourDefiningCode;
  }

  public NullFlavour getDurchwahlNullFlavourDefiningCode() {
     return this.durchwahlNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
