package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PhaseDefiningCode;

@Entity
@Archetype("openEHR-EHR-CLUSTER.level_of_exertion.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.595855902+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BelastungsgradCluster2 implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Belastungsgrad/Phase
   * Description: Die Phase oder der Kontext zur Übung/körperlichen Anstrengung.
   */
  @Path("/items[at0009]/value|defining_code")
  private PhaseDefiningCode phaseDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/List/Belastungsgrad/Phase/null_flavour
   */
  @Path("/items[at0009]/null_flavour|defining_code")
  private NullFlavour phaseNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Jedes Ereignis/Belastungsgrad/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPhaseDefiningCode(PhaseDefiningCode phaseDefiningCode) {
     this.phaseDefiningCode = phaseDefiningCode;
  }

  public PhaseDefiningCode getPhaseDefiningCode() {
     return this.phaseDefiningCode ;
  }

  public void setPhaseNullFlavourDefiningCode(NullFlavour phaseNullFlavourDefiningCode) {
     this.phaseNullFlavourDefiningCode = phaseNullFlavourDefiningCode;
  }

  public NullFlavour getPhaseNullFlavourDefiningCode() {
     return this.phaseNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
