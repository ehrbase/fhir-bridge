package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.239494+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class BefundAnforderungElement implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/Anforderung
   * Description: Name des ursprünglich angeforderten Tests.
   * Comment: Dieses Datenelement ist zu verwenden, wenn die angeforderte Testung von der tatsächlich vom Labor durchgeführten Testung abweicht.
   */
  @Path("/value")
  private DvCodedText value;

  /**
   * Path: Mikrobiologischer Befund/Befund/Tree/Details der Testanforderung/Anforderung/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Mikrobiologischer Befund/Befund/Details der Testanforderung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvCodedText value) {
     this.value = value;
  }

  public DvCodedText getValue() {
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
