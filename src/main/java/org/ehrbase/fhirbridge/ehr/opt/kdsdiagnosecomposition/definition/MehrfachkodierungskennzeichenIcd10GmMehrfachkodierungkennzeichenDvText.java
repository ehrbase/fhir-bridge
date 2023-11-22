package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.609540786+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
@OptionFor("DV_TEXT")
public class MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenDvText implements RMEntity, MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice {
  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM/Mehrfachkodierungkennzeichen/Mehrfachkodierungkennzeichen
   * Description: ICD-10 GM Zusatzkennzeichen nach Kreuz-Stern-System.
   */
  @Path("|value")
  private String mehrfachkodierungkennzeichenValue;

  public void setMehrfachkodierungkennzeichenValue(String mehrfachkodierungkennzeichenValue) {
     this.mehrfachkodierungkennzeichenValue = mehrfachkodierungkennzeichenValue;
  }

  public String getMehrfachkodierungkennzeichenValue() {
     return this.mehrfachkodierungkennzeichenValue ;
  }
}
