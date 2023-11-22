package org.ehrbase.fhirbridge.ehr.opt.kdsdiagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.multiple_coding_icd10gm.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2023-11-22T15:55:37.606721202+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.25.0"
)
public class MehrfachkodierungskennzeichenIcd10GmCluster implements LocatableEntity {
  /**
   * Path: Diagnose/Primärcode/Structure/Mehrfachkodierungskennzeichen_ICD-10-GM/Mehrfachkodierungkennzeichen/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour mehrfachkodierungkennzeichenNullFlavourDefiningCode;

  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Diagnose/Primärcode/Mehrfachkodierungskennzeichen_ICD-10-GM/Mehrfachkodierungkennzeichen
   * Description: ICD-10 GM Zusatzkennzeichen nach Kreuz-Stern-System.
   */
  @Path("/items[at0001]/value")
  @Choice
  private MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice mehrfachkodierungkennzeichen;

  public void setMehrfachkodierungkennzeichenNullFlavourDefiningCode(
      NullFlavour mehrfachkodierungkennzeichenNullFlavourDefiningCode) {
     this.mehrfachkodierungkennzeichenNullFlavourDefiningCode = mehrfachkodierungkennzeichenNullFlavourDefiningCode;
  }

  public NullFlavour getMehrfachkodierungkennzeichenNullFlavourDefiningCode() {
     return this.mehrfachkodierungkennzeichenNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setMehrfachkodierungkennzeichen(
      MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice mehrfachkodierungkennzeichen) {
     this.mehrfachkodierungkennzeichen = mehrfachkodierungkennzeichen;
  }

  public MehrfachkodierungskennzeichenIcd10GmMehrfachkodierungkennzeichenChoice getMehrfachkodierungkennzeichen(
      ) {
     return this.mehrfachkodierungkennzeichen ;
  }
}
