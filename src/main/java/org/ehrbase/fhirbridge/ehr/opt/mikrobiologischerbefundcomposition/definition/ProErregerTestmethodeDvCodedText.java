package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.233981+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_CODED_TEXT")
public class ProErregerTestmethodeDvCodedText implements RMEntity, ProErregerTestmethodeChoice {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   * Comment: Wenn die Testmethode für ein gesamtes Panel gilt, kann die Testmethode mithilfe des Datenelements "Testmethode" im Ergebnis OBSERVATION.laboratory_test_result erfasst werden.
   */
  @Path("")
  private DvCodedText testmethode;

  public void setTestmethode(DvCodedText testmethode) {
     this.testmethode = testmethode;
  }

  public DvCodedText getTestmethode() {
     return this.testmethode ;
  }
}
