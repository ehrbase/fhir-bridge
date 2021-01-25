package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:41.188425+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class AdresseCluster implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Adresse/Adresse/Art
   * Description: Art der Adresse
   */
  @Path("/items[at0001]/items[at0006]/value|defining_code")
  private ArtDefiningCode artDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Tree/Adresse/Adresse/Art/null_flavour
   */
  @Path("/items[at0001]/items[at0006]/null_flavour|defining_code")
  private NullFlavour artNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Adresse/Adresse/Postleitzahl
   * Description: Postleitzahl
   */
  @Path("/items[at0001]/items[at0004]/value|value")
  private String postleitzahlValue;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Tree/Adresse/Adresse/Postleitzahl/null_flavour
   */
  @Path("/items[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour postleitzahlNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Datenspende/Einwilligungserklärung/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDefiningCode(ArtDefiningCode artDefiningCode) {
     this.artDefiningCode = artDefiningCode;
  }

  public ArtDefiningCode getArtDefiningCode() {
     return this.artDefiningCode ;
  }

  public void setArtNullFlavourDefiningCode(NullFlavour artNullFlavourDefiningCode) {
     this.artNullFlavourDefiningCode = artNullFlavourDefiningCode;
  }

  public NullFlavour getArtNullFlavourDefiningCode() {
     return this.artNullFlavourDefiningCode ;
  }

  public void setPostleitzahlValue(String postleitzahlValue) {
     this.postleitzahlValue = postleitzahlValue;
  }

  public String getPostleitzahlValue() {
     return this.postleitzahlValue ;
  }

  public void setPostleitzahlNullFlavourDefiningCode(
      NullFlavour postleitzahlNullFlavourDefiningCode) {
     this.postleitzahlNullFlavourDefiningCode = postleitzahlNullFlavourDefiningCode;
  }

  public NullFlavour getPostleitzahlNullFlavourDefiningCode() {
     return this.postleitzahlNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
