package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
    date = "2022-04-13T16:38:21.318933747+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BelastungsgradCluster implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Ruhepuls/Belastungsgrad/Intensität der Übung
   * Description: Die körperliche Anstrengung/Intensität, die während der Übung geleistet wird.
   */
  @Path("/items[at0010]")
  private Cluster intensitaetDerUebung;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Ruhepuls/Belastungsgrad/Phase
   * Description: Die Phase oder der Kontext zur Übung/körperlichen Anstrengung.
   */
  @Path("/items[at0009]/value|defining_code")
  private PhaseDefiningCode phaseDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/History/Ruhepuls/List/Belastungsgrad/Phase/null_flavour
   */
  @Path("/items[at0009]/null_flavour|defining_code")
  private NullFlavour phaseNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Ruhepuls/Belastungsgrad/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setIntensitaetDerUebung(Cluster intensitaetDerUebung) {
     this.intensitaetDerUebung = intensitaetDerUebung;
  }

  public Cluster getIntensitaetDerUebung() {
     return this.intensitaetDerUebung ;
  }

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
