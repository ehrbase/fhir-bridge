package org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.level_of_exertion.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:12:33.380235503+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public class BelastungsgradCluster2 implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Mittlere Herzfrequenz/Belastungsgrad/Intensität der Übung/Beschreibung
   * Description: Beschreibung der körperlichen Anstrengung.
   */
  @Path("/items[at0010]/items[at0016]/value|value")
  private String beschreibungValue;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/History/Mittlere Herzfrequenz/List/Belastungsgrad/Intensität der Übung/Beschreibung/null_flavour
   */
  @Path("/items[at0010]/items[at0016]/null_flavour|defining_code")
  private NullFlavour beschreibungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Pulsfrequenz/Herzfrequenz/Mittlere Herzfrequenz/Belastungsgrad/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBeschreibungValue(String beschreibungValue) {
     this.beschreibungValue = beschreibungValue;
  }

  public String getBeschreibungValue() {
     return this.beschreibungValue ;
  }

  public void setBeschreibungNullFlavourDefiningCode(
      NullFlavour beschreibungNullFlavourDefiningCode) {
     this.beschreibungNullFlavourDefiningCode = beschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungNullFlavourDefiningCode() {
     return this.beschreibungNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
