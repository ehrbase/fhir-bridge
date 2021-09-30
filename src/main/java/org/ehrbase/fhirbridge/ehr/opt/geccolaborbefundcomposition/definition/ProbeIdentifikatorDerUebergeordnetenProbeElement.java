package org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-30T14:42:27.451812+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ProbeIdentifikatorDerUebergeordnetenProbeElement implements LocatableEntity {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/Identifikator der übergeordneten Probe
   * Description: Eindeutige Kennung der verwandten Probe(n), bei der die Probe in Teilproben aufgeteilt ist.
   * Comment: Zum Beispiel: eine bestimmte Probe eines Objektträgers für die Histologie würde einen bestimmten Paraffinwachsblock als Ausgangsprobe haben.
   */
  @Path("/value")
  private DvIdentifier value;

  /**
   * Path: Laborbefund/Laborergebnis/Event Series/Jedes Ereignis/Tree/Probe/Identifikator der übergeordneten Probe/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Probe/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setValue(DvIdentifier value) {
     this.value = value;
  }

  public DvIdentifier getValue() {
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
