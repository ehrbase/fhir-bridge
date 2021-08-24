package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.hip_metadata.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-24T22:02:30.800944+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class HipMetadataCluster implements LocatableEntity {
  /**
   * Path: Bericht/context/Hip metadata/Kategorie
   * Description: Die Kategorie des Dokuments 
   */
  @Path("/items[at0001]/value|value")
  private String kategorieValue;

  /**
   * Path: Bericht/context/Tree/Hip metadata/Kategorie/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour kategorieNullFlavourDefiningCode;

  /**
   * Path: Bericht/context/Hip metadata/Identifikator
   * Description: Identifikator des Dokuments
   */
  @Path("/items[at0002]")
  private List<HipMetadataIdentifikatorElement> identifikator;

  /**
   * Path: Bericht/context/Hip metadata/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setKategorieValue(String kategorieValue) {
     this.kategorieValue = kategorieValue;
  }

  public String getKategorieValue() {
     return this.kategorieValue ;
  }

  public void setKategorieNullFlavourDefiningCode(NullFlavour kategorieNullFlavourDefiningCode) {
     this.kategorieNullFlavourDefiningCode = kategorieNullFlavourDefiningCode;
  }

  public NullFlavour getKategorieNullFlavourDefiningCode() {
     return this.kategorieNullFlavourDefiningCode ;
  }

  public void setIdentifikator(List<HipMetadataIdentifikatorElement> identifikator) {
     this.identifikator = identifikator;
  }

  public List<HipMetadataIdentifikatorElement> getIdentifikator() {
     return this.identifikator ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
