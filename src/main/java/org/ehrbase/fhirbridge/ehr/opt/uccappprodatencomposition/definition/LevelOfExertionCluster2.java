package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.level_of_exertion.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T16:40:29.052589859+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class LevelOfExertionCluster2 implements LocatableEntity {
  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/Level of exertion/Phase
   * Description: The phase or context of exercise.
   */
  @Path("/items[at0009]/value|defining_code")
  private PhaseDefiningCode phaseDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/history/Any event/List/Level of exertion/Phase/null_flavour
   */
  @Path("/items[at0009]/null_flavour|defining_code")
  private NullFlavour phaseNullFlavourDefiningCode;

  /**
   * Path: Self monitoring/Pulse/Heart beat/Any event/Level of exertion/feeder_audit
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
