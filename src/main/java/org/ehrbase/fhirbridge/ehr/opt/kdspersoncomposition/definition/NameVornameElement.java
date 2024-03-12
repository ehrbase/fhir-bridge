package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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
    date = "2024-02-21T15:03:27.124964750+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class NameVornameElement implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Name/Vorname
   * Description: Ein oder mehrere eindeutige Namen, die verwendet werden, um eine Person innerhalb einer Familiengruppe zu identifizieren.
   * Comment: Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Vorname dargestellt werden können. Darüber hinaus kann dieses Datenelement innerhalb eines Templates geklont und umbenannt werden, um eine getrennte Darstellung verschiedener Arten von Vornamen zu ermöglichen – zum Beispiel „Vorname“, „Zweiter Vorname“, „bevorzugter Name“ oder „Spitzname“, wie für einen konkreten Anwendungsfall erforderlich.
   */
  @Path("/value|value")
  private String value;

  /**
   * Path: Person/Personendaten/Baum/Person/Name/Vorname/null_flavour
   */
  @Path("/null_flavour|defining_code")
  private NullFlavour value2;

  /**
   * Path: Person/Personendaten/Person/Name/feeder_audit
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
