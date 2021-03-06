package org.ehrbase.fhirbridge.ehr.opt.prozedurcomposition.definition;

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
    date = "2021-03-09T11:55:59.509120+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class ProzedurDurchfuehrungsabsichtElement implements LocatableEntity {
  /**
   * Path: Prozedur/Prozedur/Durchführungsabsicht
   * Description: Grund, warum die angegebene Aktivität für diese Prozedur durchgeführt wurde.
   * Comment: Zum Beispiel: der Grund für den Abbruch oder die Unterbrechung der Prozedur.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Prozedur/Prozedur/Tree/Durchführungsabsicht/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Prozedur/Prozedur/feeder_audit
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
