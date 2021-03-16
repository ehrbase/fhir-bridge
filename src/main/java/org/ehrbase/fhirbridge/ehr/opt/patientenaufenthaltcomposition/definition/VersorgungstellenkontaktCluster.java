package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:02.871982+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class VersorgungstellenkontaktCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/context/Versorgungstellenkontakt/Zugehöriger Versorgungsstellenkontakt (Kennung)
   * Description: Der Bezeichner/die Kennung dieses Falls.
   */
  @Path("/items[at0001 and name/value='Zugehöriger Versorgungsstellenkontakt (Kennung)']/value|value")
  private String zugehoerigerVersorgungsstellenkontaktKennungValue;

  /**
   * Path: Patientenaufenthalt/context/Tree/Versorgungstellenkontakt/Zugehöriger Versorgungsstellenkontakt (Kennung)/null_flavour
   */
  @Path("/items[at0001 and name/value='Zugehöriger Versorgungsstellenkontakt (Kennung)']/null_flavour|defining_code")
  private NullFlavour zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/context/Versorgungstellenkontakt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setZugehoerigerVersorgungsstellenkontaktKennungValue(
      String zugehoerigerVersorgungsstellenkontaktKennungValue) {
     this.zugehoerigerVersorgungsstellenkontaktKennungValue = zugehoerigerVersorgungsstellenkontaktKennungValue;
  }

  public String getZugehoerigerVersorgungsstellenkontaktKennungValue() {
     return this.zugehoerigerVersorgungsstellenkontaktKennungValue ;
  }

  public void setZugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode(
      NullFlavour zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode) {
     this.zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode = zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode;
  }

  public NullFlavour getZugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode() {
     return this.zugehoerigerVersorgungsstellenkontaktKennungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
