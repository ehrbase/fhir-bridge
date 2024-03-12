package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.619445219+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@OptionFor("DV_CODED_TEXT")
public class PrimaercodeSchweregradNullFlavourDvCodedText2 implements RMEntity, PrimaercodeSchweregradNullFlavourChoice {
  /**
   * Path: Diagnose/Primärcode/Structure/Structure/Schweregrad/null_flavour/null_flavour
   */
  @Path("|defining_code")
  private NullFlavour schweregradNullFlavourDefiningCode;

  public void setSchweregradNullFlavourDefiningCode(
      NullFlavour schweregradNullFlavourDefiningCode) {
     this.schweregradNullFlavourDefiningCode = schweregradNullFlavourDefiningCode;
  }

  public NullFlavour getSchweregradNullFlavourDefiningCode() {
     return this.schweregradNullFlavourDefiningCode ;
  }
}
