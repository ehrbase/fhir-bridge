package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

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
    date = "2021-06-21T14:47:54.282972+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
public class ProLaboranalytKommentarElement implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/Kommentar
   * Description: Kommentar zum Analyt-Ergebnis, soweit noch nicht in anderen Feldern erfasst.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Pro Laboranalyt/Kommentar/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Pro Laboranalyt/feeder_audit
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
