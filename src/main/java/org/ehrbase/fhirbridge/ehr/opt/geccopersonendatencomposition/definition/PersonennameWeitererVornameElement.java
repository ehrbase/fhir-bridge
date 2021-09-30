package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

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
    date = "2021-09-30T15:26:10.398717+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PersonennameWeitererVornameElement implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Weiterer Vorname
   * Description: Zweiter Vorname oder zweite Namen.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name strukturiert/Weiterer Vorname/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/feeder_audit
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
