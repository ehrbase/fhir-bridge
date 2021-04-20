package org.ehrbase.fhirbridge.ehr.opt.geccovirologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-17T16:15:08.650590500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class LabortestPanelCluster implements LocatableEntity {
  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt
   * Description: Ergebnis einer Laboranalyse für einen bestimmten Analytwert.
   * Comment: Beispiele: "Natrium", "Leukozytenzahl", "T3". Üblicherweise über eine externe Terminologie codiert.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Analyt']")
  private ProAnalytCluster proAnalyt;

  /**
   * Path: GECCO_Virologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProAnalyt(ProAnalytCluster proAnalyt) {
     this.proAnalyt = proAnalyt;
  }

  public ProAnalytCluster getProAnalyt() {
     return this.proAnalyt ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
