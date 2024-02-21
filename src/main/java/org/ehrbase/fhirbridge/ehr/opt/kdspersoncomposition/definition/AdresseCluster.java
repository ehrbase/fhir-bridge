package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.159180260+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class AdresseCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Kontaktperson/Adresse/Adresszeile
   * Description: Eine unstrukturierte Adresszeile, die alle relevanten Details auf Straßenebene oder zum Postfach darstellt, die die Identifizierung eines Standorts unterstützen würden.
   * Comment: Vorkommen für dieses Datenelement ist auf 0..* gesetzt, damit ein oder mehrere Freitext-„Adresszeilen“-Datenelemente die große Vielfalt an Möglichkeiten darstellen können, wie Details auf „Straßenebene“ möglicherweise im Kontext einer bestimmten "Stadt" erfasst werden müssen. Mehrere Adresszeilen können in einem Template dargestellt und in „Adresszeile 1“, „Adresszeile 2“ usw. umbenannt werden. Beispiel: „7A/52 Davis Street“ oder „Appartment 7A“ als Adresszeile 1 mit „52 Davis Street“ als Adresszeile 2; ein Postfachstandort am Straßenrand, wie z. B. „RMB 725, Princes Highway“ oder Verwendung eines beschreibenden Orientierungspunkts wie „Ecke Smith & Brown Street“ oder „Zweites Haus nördlich des Gemischtwarenladens mit der roten Tür“.
   */
  @Path("/items[at0001]/value|value")
  private String adresszeileValue;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Adresse/Adresszeile/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour adresszeileNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAdresszeileValue(String adresszeileValue) {
     this.adresszeileValue = adresszeileValue;
  }

  public String getAdresszeileValue() {
     return this.adresszeileValue ;
  }

  public void setAdresszeileNullFlavourDefiningCode(
      NullFlavour adresszeileNullFlavourDefiningCode) {
     this.adresszeileNullFlavourDefiningCode = adresszeileNullFlavourDefiningCode;
  }

  public NullFlavour getAdresszeileNullFlavourDefiningCode() {
     return this.adresszeileNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
