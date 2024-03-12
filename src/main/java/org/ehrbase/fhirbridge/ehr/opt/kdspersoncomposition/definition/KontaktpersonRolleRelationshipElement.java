package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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
    date = "2024-02-21T15:03:27.158718734+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class KontaktpersonRolleRelationshipElement implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Kontaktperson/Rolle (Relationship)
   * Description: Die Beziehung oder die Rolle der Person zur Person in der elektronischen Gesundheitsakte.
   * Comment: Zum Beispiel - der Inhaber einer Patientenverfügung, Kontaktperson in einer Organisation, Verwandter in einem Eintrag zur Familienananese, Probenabnehmer oder Zeuge eines Sturzes oder Unfalls. Wenn die anhand dieses Archetyps beschriebene Person der/die Patient/in der Gesundheitsakte ist, dann ist dieses Datenelement redundant.
   */
  @Path("/value")
  private DvCodedText value;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Rolle (Relationship)/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Person/Personendaten/Kontaktperson/feeder_audit
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
