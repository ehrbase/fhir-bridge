package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

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
    date = "2021-10-18T12:28:12.514975+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ProbeProbenentahmebedingungElement implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Probenentahmebedingung
   * Description: Der Kontext, in dem die Probe entnommen wird.
   * Comment: Zum Beispiel: "Fasten", "volle Blase", "steriles Feld" oder spezielle Anweisungen zur Handhabung oder sofortigen Verarbeitung der Probe, zum Beispiel "Nach Erhalt zentrifugieren". Kann auch verwendet werden, um bekannte Abweichungen von Entnahme- oder Handhabungsanweisungen zu dokumentieren, z.B. dass der Patient nicht gefastet hat. Wenn möglich, wird eine Kodierung der Probenahmebedingung mit einer Terminologie bevorzugt. 
   *
   * Ob dieses Element Bedingungen enthält, die während der Probenahme vorhanden sein sollten oder waren, hängt vom Kontext des enthaltenen Archetyps ab, in der Regel ein INSTRUCTION. Der Inhalt dieses Elements im Kontext eines ACTION-Archetyps in einem abgeschlossenen Zustand kann verwendet werden, um zu entscheiden, ob signifikante Störfaktoren im Zusammenhang mit der Entnahme aufgetreten sind, die zum einpflegen des Elements 'Störfaktoren' eines OBSERVATION.laboratory_test_result-Archetyps verwendet werden können.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Probenentahmebedingung/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/feeder_audit
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
