package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-27T16:16:21.031560+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class AbteilungsfallCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/context/Abteilungsfall/Zugehöriger Abteilungsfall (Kennung)
   * Description: Der Bezeichner/die Kennung dieses Falls.
   */
  @Path("/items[at0001 and name/value='Zugehöriger Abteilungsfall (Kennung)']/value|value")
  private String zugehoerigerAbteilungsfallKennungValue;

  /**
   * Path: Patientenaufenthalt/context/Tree/Abteilungsfall/Zugehöriger Abteilungsfall (Kennung)/null_flavour
   */
  @Path("/items[at0001 and name/value='Zugehöriger Abteilungsfall (Kennung)']/null_flavour|defining_code")
  private NullFlavour zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/context/Abteilungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehoerigerAbteilungsfallKennungValue(
      String zugehoerigerAbteilungsfallKennungValue) {
     this.zugehoerigerAbteilungsfallKennungValue = zugehoerigerAbteilungsfallKennungValue;
  }

  public String getZugehoerigerAbteilungsfallKennungValue() {
     return this.zugehoerigerAbteilungsfallKennungValue ;
  }

  public void setZugehoerigerAbteilungsfallKennungNullFlavourDefiningCode(
      NullFlavour zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode) {
     this.zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode = zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode;
  }

  public NullFlavour getZugehoerigerAbteilungsfallKennungNullFlavourDefiningCode() {
     return this.zugehoerigerAbteilungsfallKennungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
