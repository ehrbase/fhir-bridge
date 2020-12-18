package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
    date = "2020-12-18T10:30:38.837320+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Infektionskrankheit oder Erreger
   * Description: Identifizierung der Infektionskrankheit oder des Erregers.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Tree/Infektionskrankheit oder Erreger/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/feeder_audit
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
