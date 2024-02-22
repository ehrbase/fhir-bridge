package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.229813+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_CODED_TEXT")
public class ErregerdetailsKeimzahlNullFlavourDvCodedText2 implements RMEntity, ErregerdetailsKeimzahlNullFlavourChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Keimzahl/Keimzahl/null_flavour/null_flavour
   */
  @Path("|defining_code")
  private NullFlavour keimzahlNullFlavourDefiningCode;

  public void setKeimzahlNullFlavourDefiningCode(NullFlavour keimzahlNullFlavourDefiningCode) {
     this.keimzahlNullFlavourDefiningCode = keimzahlNullFlavourDefiningCode;
  }

  public NullFlavour getKeimzahlNullFlavourDefiningCode() {
     return this.keimzahlNullFlavourDefiningCode ;
  }
}
