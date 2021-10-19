package org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-19T12:08:14.407907+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_PROPORTION")
public class KohlendioxidpartialdruckTestmethodeDvProportion implements RMEntity, KohlendioxidpartialdruckTestmethodeChoice {
  /**
   * Path: Befund der Blutgasanalyse/Laborergebnis/Jedes Ereignis/Kohlendioxidpartialdruck/Testmethode/Testmethode
   * Description: Die Beschreibung der Methode, mit der der Test nur für diesen Analyten durchgeführt wurde.
   */
  @Path("")
  private DvProportion testmethode;

  public void setTestmethode(DvProportion testmethode) {
     this.testmethode = testmethode;
  }

  public DvProportion getTestmethode() {
     return this.testmethode ;
  }
}
