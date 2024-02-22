package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.214093+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class AntibiogrammCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/Pro Antibiotikum
   * Description: Ergebnis einer Laboranalyse für einen bestimmten Analytwert.
   * Comment: Beispiele: "Natrium", "Leukozytenzahl", "T3". Üblicherweise über eine externe Terminologie codiert.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Antibiotikum']")
  private List<ProAntibiotikumCluster> proAntibiotikum;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProAntibiotikum(List<ProAntibiotikumCluster> proAntibiotikum) {
     this.proAntibiotikum = proAntibiotikum;
  }

  public List<ProAntibiotikumCluster> getProAntibiotikum() {
     return this.proAntibiotikum ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
