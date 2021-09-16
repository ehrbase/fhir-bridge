package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

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
    date = "2021-09-09T12:22:43.869516+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class VorliegendesSymptomAnatomischeLokalisationElement implements LocatableEntity {
  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/Anatomische Lokalisation
   * Description: Anatomische Lokalisation des Symptoms/Anzeichens.
   * Comment: Das Auftreten dieses Datenelements wird auf 0...* gesetzt, um bei Bedarf mehrere Körperstellen im Template voneinander zu trennen. Dies ermöglicht die Darstellung klinischer Szenarien, in denen ein Symptom/Krankheitsanzeichen an mehreren Stellen erfasst werden muss oder in denen sowohl die ursprüngliche als auch die distale Stelle bei der Schmerzausbreitung identifiziert werden, aber alle anderen Attribute wie Wirkung und Dauer identisch sind. Wenn die Anforderungen an die Erfassung der Lokalisation zur Laufzeit durch die Anwendung festgelegt werden oder komplexere Modellierungen wie z.B. relative Positionen erforderlich sind, verwenden Sie CLUSTER.anatomical_location oder CLUSTER.relative_location innerhalb des Slots "Spezifische anatomische Lokalisation" in diesem Archetyp. 
   * Wird die anatomische Lokalisation über vordefinierte Codes in den Symptomnamen aufgenommen, wird dieses Datenelement redundant. Wenn die anatomische Lokalisation mit dem Slot "Spezifische anatomische Lokalisation" erfasst wird, ist die Verwendung dieses Datenelements nicht erlaubt - erfassen Sie entweder die grobe "Anatomische Lokalisation" oder die "Spezifische anatomische Lokalisation", nicht beides.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/Event Series/*Any event(en)/Tree/Anatomische Lokalisation/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: COVID-19 Symptom/Vorliegendes Symptom/*Any event(en)/feeder_audit
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
