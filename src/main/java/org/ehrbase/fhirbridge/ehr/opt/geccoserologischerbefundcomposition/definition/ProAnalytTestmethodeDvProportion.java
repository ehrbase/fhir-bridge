package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T18:13:35.188300+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_PROPORTION")
public class ProAnalytTestmethodeDvProportion implements RMEntity, ProAnalytTestmethodeChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Testmethode/Testmethode
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
