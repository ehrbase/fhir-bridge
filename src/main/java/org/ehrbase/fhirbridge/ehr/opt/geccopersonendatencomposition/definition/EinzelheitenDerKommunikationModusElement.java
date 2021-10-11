package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T14:49:10.196059+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class EinzelheitenDerKommunikationModusElement implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Modus
   * Description: Eine Kennzeichnung für einen Telekommunikationskontakt, die dessen Kontext beschreibt, z.B. "Arbeit", "Privat". ENV 13606 - 4:2000 7.11.19.
   */
  @Path("/value|defining_code")
  private ModusDefiningCode value;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Einzelheiten der Kommunikation/Modus/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(ModusDefiningCode value) {
     this.value = value;
  }

  public ModusDefiningCode getValue() {
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
