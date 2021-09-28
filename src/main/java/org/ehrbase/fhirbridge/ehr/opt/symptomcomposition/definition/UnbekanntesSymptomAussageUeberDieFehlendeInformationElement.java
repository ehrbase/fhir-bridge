package org.ehrbase.fhirbridge.ehr.opt.symptomcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-28T13:05:49.072924+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class UnbekanntesSymptomAussageUeberDieFehlendeInformationElement implements LocatableEntity {
  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Aussage über die fehlende Information
   * Description: Beschreibung des Grundes, warum keine Informationen vorhanden sind.
   * Comment: Zum Beispiel: Der Patient ist bewusstlos oder weigert sich Informationen preiszugeben. Die Codierung mit einer Terminologie wird empfohlen, wenn möglich.
   */
  @Path("/value|defining_code")
  private AussageUeberDieFehlendeInformationDefiningCode value;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/Baum/Aussage über die fehlende Information/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: COVID-19 Symptom/Unbekanntes Symptom/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(AussageUeberDieFehlendeInformationDefiningCode value) {
     this.value = value;
  }

  public AussageUeberDieFehlendeInformationDefiningCode getValue() {
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
