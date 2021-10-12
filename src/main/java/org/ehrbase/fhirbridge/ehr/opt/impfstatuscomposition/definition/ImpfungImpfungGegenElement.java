package org.ehrbase.fhirbridge.ehr.opt.impfstatuscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-12T15:08:17.348407+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ImpfungImpfungGegenElement implements LocatableEntity {
  /**
   * Path: Impfstatus/Impfung/Impfung gegen
   * Description: Begründung, warum der Prozessschritt für das identifizierte Arzneimittel durchgeführt wurde.
   * Comment: Zum Beispiel: "Verschoben - Patient war zum Zeitpunkt der Arzneimittelgabe nicht verfügbar", "abgesagt - Nebenwirkung". Merke: Dies ist nicht der Grund für die Arzneimittelverordnung, sondern der spezifische Grund, warum ein Behandlungsschritt durchgeführt wurde. Wird oft verwendet, um Abweichungen von der ursprünglichen Verordnung zu dokumentieren.
   */
  @Path("/value")
  private DvCodedText value;

  /**
   * Path: Impfstatus/Impfung/Tree/Impfung gegen/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Impfstatus/Impfung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvCodedText value) {
     this.value = value;
  }

  public DvCodedText getValue() {
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
