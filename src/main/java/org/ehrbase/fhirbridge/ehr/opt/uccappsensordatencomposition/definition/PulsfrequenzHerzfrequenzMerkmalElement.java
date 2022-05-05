package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

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
    date = "2022-05-05T11:59:39.203212+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PulsfrequenzHerzfrequenzMerkmalElement implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Merkmal
   * Description: Beschreibung des Merkmals der Puls- oder Herzfrequenz.
   * Comment: Die Kodierung mit einer Terminologie ist, wenn möglich, erwünscht. Zum Beispiel: kräftig, schwach, hämmernd, langsam ansteigend oder kollabierend. Mehrere Begriffe können dokumentiert werden.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Structure/Merkmal/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/feeder_audit
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
