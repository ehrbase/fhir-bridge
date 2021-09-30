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
    date = "2021-09-30T16:13:57.797279+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class AdresseAdresseUnstrukturiertElement implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Adresse unstrukturiert
   * Description: Eine unstrukturierte Adresse oder eine Verkettung einer oder mehrerer Komponenten aus CLUSTER.structured_address.
   * Comment: Diese Adresszeile stellt eine niedrige geografische/physische Beschreibung eines Standorts dar, die in Verbindung mit den anderen übergeordneten Adresskomponenten, d. h. „Vorort/Stadt/Ort“, „Postleitzahl“ und „Staat/Land/Bundesland“, bildet eine vollständige geografische/physische Adresse. Mehrfaches Vorkommen erlaubt beliebig viele "Adresszeilen". Beispiel: 4 Adresszeilen dargestellt als
   * Wohnung 7A,
   * 52 Davis-Straße,
   * Carlton Nord,
   * Victoria, Australien 3042.
   * Dieses Datenelement kann auch verwendet werden, um ein Wahrzeichen darzustellen, wie zum Beispiel "Das zweite Haus nördlich des Hauptgeschäftes" oder "An der Ecke Smith & Brown Streets".
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Adresse unstrukturiert/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/feeder_audit
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
