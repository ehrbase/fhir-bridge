package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.147500014+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class VerwaltungsorganisationIdentifierElement implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Baum/Person/Verwaltungsorganisation/Identifier/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value;

  /**
   * Path: Person/Personendaten/Person/Verwaltungsorganisation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Person/Personendaten/Person/Verwaltungsorganisation/Identifier
   * Description: Identifikator, der der identifizierten Organisation zugeordnet ist.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als ein Identifikator aufgezeichnet werden kann. Beachten Sie, dass der ID-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält.
   */
  @Path("/value")
  @Choice
  private VerwaltungsorganisationIdentifierChoice value2;

  public void setValue(NullFlavour value) {
     this.value = value;
  }

  public NullFlavour getValue() {
     return this.value ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setValue2(VerwaltungsorganisationIdentifierChoice value2) {
     this.value2 = value2;
  }

  public VerwaltungsorganisationIdentifierChoice getValue2() {
     return this.value2 ;
  }
}
