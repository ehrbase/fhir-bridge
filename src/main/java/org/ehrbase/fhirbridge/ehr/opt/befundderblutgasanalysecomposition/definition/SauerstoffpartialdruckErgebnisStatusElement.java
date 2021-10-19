package org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-19T12:08:14.442204+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class SauerstoffpartialdruckErgebnisStatusElement implements LocatableEntity {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffpartialdruck/Ergebnis-Status
   * Description: Status des Analyt-Ergebniswertes.
   * Comment: Die Werte wurden speziell so ausgewählt, dass sie mit denen im HL7 FHIR-Diagnosebericht übereinstimmen, der ursprünglich aus der HL7v2-Praxis abgeleitet wurde. Andere lokale Codes / Begriffe können über die Textauswahl verwendet werden.
   *
   * Dieses Element ermöglicht mehrere Vorkommen, um Anwendungsfälle zu unterstützen, wo mehr als eine Art von Status implementiert werden muss.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Event Series/Jedes Ereignis/Tree/Sauerstoffpartialdruck/Ergebnis-Status/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Sauerstoffpartialdruck/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
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
