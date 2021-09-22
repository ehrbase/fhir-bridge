package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-14T12:58:22.454659+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class DnrAnordnungKategorieElement implements LocatableEntity {
  /**
   * Path: DNR-Anordnung/context/Kategorie
   * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("/value|defining_code")
  private KategorieDefiningCode value;

  /**
   * Path: DNR-Anordnung/context/Baum/Kategorie/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: DNR-Anordnung/context/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(KategorieDefiningCode value) {
     this.value = value;
  }

  public KategorieDefiningCode getValue() {
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
