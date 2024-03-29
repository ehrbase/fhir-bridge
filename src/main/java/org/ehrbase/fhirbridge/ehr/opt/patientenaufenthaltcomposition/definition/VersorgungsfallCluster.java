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
    date = "2021-07-27T16:16:21.020099+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class VersorgungsfallCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/context/Versorgungsfall/Zugehöriger Versorgungsfall (Kennung)
   * Description: Der Bezeichner/die Kennung dieses Falls.
   */
  @Path("/items[at0001 and name/value='Zugehöriger Versorgungsfall (Kennung)']/value|value")
  private String zugehoerigerVersorgungsfallKennungValue;

  /**
   * Path: Patientenaufenthalt/context/Tree/Versorgungsfall/Zugehöriger Versorgungsfall (Kennung)/null_flavour
   */
  @Path("/items[at0001 and name/value='Zugehöriger Versorgungsfall (Kennung)']/null_flavour|defining_code")
  private NullFlavour zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/context/Versorgungsfall/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehoerigerVersorgungsfallKennungValue(
      String zugehoerigerVersorgungsfallKennungValue) {
     this.zugehoerigerVersorgungsfallKennungValue = zugehoerigerVersorgungsfallKennungValue;
  }

  public String getZugehoerigerVersorgungsfallKennungValue() {
     return this.zugehoerigerVersorgungsfallKennungValue ;
  }

  public void setZugehoerigerVersorgungsfallKennungNullFlavourDefiningCode(
      NullFlavour zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode) {
     this.zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode = zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode;
  }

  public NullFlavour getZugehoerigerVersorgungsfallKennungNullFlavourDefiningCode() {
     return this.zugehoerigerVersorgungsfallKennungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
