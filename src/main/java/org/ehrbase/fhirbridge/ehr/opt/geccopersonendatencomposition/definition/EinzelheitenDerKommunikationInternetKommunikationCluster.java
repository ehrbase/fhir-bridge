package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-15T15:57:50.594512+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class EinzelheitenDerKommunikationInternetKommunikationCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation/Sender
   * Description: Die Art des verwendeten Kommunikationsmediums oder Dienstes.
   * Comment: Zum Beispiel E-Mail, SIP, Skype oder andere Arten von synchronen oder asynchronen internetbasierten Kommunikationsmitteln.
   */
  @Path("/items[at0021]/value|value")
  private String senderValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Internet-Kommunikation/Sender/null_flavour
   */
  @Path("/items[at0021]/null_flavour|defining_code")
  private NullFlavour senderNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Internet-Kommunikation/Adresse/null_flavour
   */
  @Path("/items[at0009]/null_flavour|defining_code")
  private NullFlavour adresseNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation/Adresse
   * Description: Die Adresse oder Kennung, die zur Kommunikation auf dem angegebenen Kommunikationsweg verwendet wird.
   */
  @Path("/items[at0009]/value")
  @Choice
  private EinzelheitenDerKommunikationAdresseChoice adresse;

  public void setSenderValue(String senderValue) {
     this.senderValue = senderValue;
  }

  public String getSenderValue() {
     return this.senderValue ;
  }

  public void setSenderNullFlavourDefiningCode(NullFlavour senderNullFlavourDefiningCode) {
     this.senderNullFlavourDefiningCode = senderNullFlavourDefiningCode;
  }

  public NullFlavour getSenderNullFlavourDefiningCode() {
     return this.senderNullFlavourDefiningCode ;
  }

  public void setAdresseNullFlavourDefiningCode(NullFlavour adresseNullFlavourDefiningCode) {
     this.adresseNullFlavourDefiningCode = adresseNullFlavourDefiningCode;
  }

  public NullFlavour getAdresseNullFlavourDefiningCode() {
     return this.adresseNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setAdresse(EinzelheitenDerKommunikationAdresseChoice adresse) {
     this.adresse = adresse;
  }

  public EinzelheitenDerKommunikationAdresseChoice getAdresse() {
     return this.adresse ;
  }
}
