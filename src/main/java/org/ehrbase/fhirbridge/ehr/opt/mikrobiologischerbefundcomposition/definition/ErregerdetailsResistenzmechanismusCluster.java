package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

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
    date = "2024-02-22T14:23:00.226919+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class ErregerdetailsResistenzmechanismusCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Resistenzmechanismus/Resistenzmechanismus Bezeichnung
   * Description: Bezeichnung des Resistenzmechanismus.
   * Comment: Ein bestes Beispiel wäre, die Untersuchung eines „Staphylococcus aureus“ der hier die Bezeichnung des Keims ist.
   */
  @Path("/items[at0017]/value|value")
  private String resistenzmechanismusBezeichnungValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Resistenzmechanismus/Resistenzmechanismus Bezeichnung/null_flavour
   */
  @Path("/items[at0017]/null_flavour|defining_code")
  private NullFlavour resistenzmechanismusBezeichnungNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Resistenzmechanismus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setResistenzmechanismusBezeichnungValue(String resistenzmechanismusBezeichnungValue) {
     this.resistenzmechanismusBezeichnungValue = resistenzmechanismusBezeichnungValue;
  }

  public String getResistenzmechanismusBezeichnungValue() {
     return this.resistenzmechanismusBezeichnungValue ;
  }

  public void setResistenzmechanismusBezeichnungNullFlavourDefiningCode(
      NullFlavour resistenzmechanismusBezeichnungNullFlavourDefiningCode) {
     this.resistenzmechanismusBezeichnungNullFlavourDefiningCode = resistenzmechanismusBezeichnungNullFlavourDefiningCode;
  }

  public NullFlavour getResistenzmechanismusBezeichnungNullFlavourDefiningCode() {
     return this.resistenzmechanismusBezeichnungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
