package org.ehrbase.fhirbridge.ehr.opt.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-09T11:52:54.869758+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class AetiopathogeneseAetiologieDerKrankheitElement implements LocatableEntity {
  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Structure/Ätiopathogenese/Ätiologie der Krankheit/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/Ätiologie der Krankheit
   * Description: Identifizierung der Ursache der Krankheit oder des abnormalen Zustands.
   */
  @Path("/value")
  @Choice
  private List<AetiopathogeneseAetiologieDerKrankheitChoice> value2;

  public void setValue(NullFlavour value) {
     this.value = value;
  }

  public NullFlavour getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue2(List<AetiopathogeneseAetiologieDerKrankheitChoice> value2) {
     this.value2 = value2;
  }

  public List<AetiopathogeneseAetiologieDerKrankheitChoice> getValue2() {
     return this.value2 ;
  }
}
