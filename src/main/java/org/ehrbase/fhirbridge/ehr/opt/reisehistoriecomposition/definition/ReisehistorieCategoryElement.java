package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-06T14:29:47.126460400+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ReisehistorieCategoryElement implements LocatableEntity {
  /**
   * Path: Reisehistorie/context/Category
   * Description: The classification of the register entry (e.g. type of observation of the FHIR profile).
   */
  @Path("/value|defining_code")
  private CategoryDefiningCode value;

  /**
   * Path: Reisehistorie/context/Baum/Category/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Reisehistorie/context/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(CategoryDefiningCode value) {
     this.value = value;
  }

  public CategoryDefiningCode getValue() {
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
