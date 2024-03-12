package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.615507628+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
public class KlinischerStatusCluster implements LocatableEntity {
  /**
   * Path: Diagnose/Primärcode/Klinischer Status/Klinischer Status
   * Description: Eine Kategorie, die die Aufteilung der Problemlisten von Problemen und Diagnosen in aktiv und inaktiv unterstützt.
   * Comment: Die Aktiv/Inaktiv und Aktuell/Vergangen Datenelemente haben einen ähnlichen klinischen Einfluss, repräsentieren aber eine etwas andere Semantik. Beide werden aktiv in verschiedenen klinischen Rahmen benutzt, aber normalerweise nicht zusammen. Wenn ein Aktuell/Vergangen Attribut dokumentiert wird, dann ist dieses Datenelement wahrscheinlich redundant. Eine Ausnahme ist ein Zustand der aktuell, aber inaktiv ist. Ein Beispiel dafür ist Asthma, welches keine akuten Symptome auslöst.
   */
  @Path("/items[at0003 and name/value='Klinischer Status']/value")
  private DvCodedText klinischerStatus;

  /**
   * Path: Diagnose/Primärcode/Structure/Klinischer Status/Klinischer Status/null_flavour
   */
  @Path("/items[at0003 and name/value='Klinischer Status']/null_flavour|defining_code")
  private NullFlavour klinischerStatusNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Klinischer Status/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setKlinischerStatus(DvCodedText klinischerStatus) {
     this.klinischerStatus = klinischerStatus;
  }

  public DvCodedText getKlinischerStatus() {
     return this.klinischerStatus ;
  }

  public void setKlinischerStatusNullFlavourDefiningCode(
      NullFlavour klinischerStatusNullFlavourDefiningCode) {
     this.klinischerStatusNullFlavourDefiningCode = klinischerStatusNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischerStatusNullFlavourDefiningCode() {
     return this.klinischerStatusNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
