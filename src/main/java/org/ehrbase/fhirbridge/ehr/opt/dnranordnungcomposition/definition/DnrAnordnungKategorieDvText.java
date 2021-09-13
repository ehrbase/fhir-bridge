package org.ehrbase.fhirbridge.ehr.opt.dnranordnungcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-13T13:48:21.671184+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_TEXT")
public class DnrAnordnungKategorieDvText implements RMEntity, DnrAnordnungKategorieChoice {
  /**
   * Path: DNR-Anordnung/context/Kategorie/Kategorie
   * Description: Die Klassifikation des Registereintrags (z.B. Typ der Observation des FHIR-Profils).
   */
  @Path("|value")
  private String kategorieValue;

  public void setKategorieValue(String kategorieValue) {
     this.kategorieValue = kategorieValue;
  }

  public String getKategorieValue() {
     return this.kategorieValue ;
  }
}
