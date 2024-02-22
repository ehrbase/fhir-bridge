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
    date = "2024-02-22T14:23:00.207549+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class KulturCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger
   * Description: Ergebnis einer Laboranalyse für einen bestimmten Analytwert.
   * Comment: Beispiele: "Natrium", "Leukozytenzahl", "T3". Üblicherweise über eine externe Terminologie codiert.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Erreger']")
  private List<ProErregerCluster> proErreger;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setProErreger(List<ProErregerCluster> proErreger) {
     this.proErreger = proErreger;
  }

  public List<ProErregerCluster> getProErreger() {
     return this.proErreger ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
