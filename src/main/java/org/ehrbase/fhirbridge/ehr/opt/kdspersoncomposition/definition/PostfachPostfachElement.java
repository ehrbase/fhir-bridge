package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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
    date = "2024-02-21T15:03:27.142753083+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class PostfachPostfachElement implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Postfach/Postfach
   * Description: Eine unstrukturierte Adresszeile, die alle relevanten Details auf Straßenebene oder zum Postfach darstellt, die die Identifizierung eines Standorts unterstützen würden.
   * Comment: Vorkommen für dieses Datenelement ist auf 0..* gesetzt, damit ein oder mehrere Freitext-„Adresszeilen“-Datenelemente die große Vielfalt an Möglichkeiten darstellen können, wie Details auf „Straßenebene“ möglicherweise im Kontext einer bestimmten "Stadt" erfasst werden müssen. Mehrere Adresszeilen können in einem Template dargestellt und in „Adresszeile 1“, „Adresszeile 2“ usw. umbenannt werden. Beispiel: „7A/52 Davis Street“ oder „Appartment 7A“ als Adresszeile 1 mit „52 Davis Street“ als Adresszeile 2; ein Postfachstandort am Straßenrand, wie z. B. „RMB 725, Princes Highway“ oder Verwendung eines beschreibenden Orientierungspunkts wie „Ecke Smith & Brown Street“ oder „Zweites Haus nördlich des Gemischtwarenladens mit der roten Tür“.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Person/Personendaten/Baum/Person/Postfach/Postfach/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Person/Personendaten/Person/Postfach/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }

  public void setValue2(NullFlavour value2) {
     this.value2 = value2;
  }

  public NullFlavour getValue2() {
     return this.value2 ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
