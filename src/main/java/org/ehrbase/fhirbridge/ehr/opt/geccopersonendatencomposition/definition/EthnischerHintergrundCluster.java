package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-04T14:52:22.424444600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class EthnischerHintergrundCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Ethnischer Hintergrund/Ethnischer Hintergrund
   * Description: Der ethnische Hintergrund einer Person.
   */
  @Path("/items[at0002]/value|defining_code")
  private EthnischerHintergrundDefiningCode ethnischerHintergrundDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Ethnischer Hintergrund/Ethnischer Hintergrund/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour ethnischerHintergrundNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Ethnischer Hintergrund/Details
   * Description: Zusätzliche strukturierte Angaben zum ethnischen Hintergrund einer Person.
   */
  @Path("/items[at0003]")
  private List<Cluster> details;

  /**
   * Path: GECCO_Personendaten/Personendaten/Ethnischer Hintergrund/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setEthnischerHintergrundDefiningCode(
      EthnischerHintergrundDefiningCode ethnischerHintergrundDefiningCode) {
     this.ethnischerHintergrundDefiningCode = ethnischerHintergrundDefiningCode;
  }

  public EthnischerHintergrundDefiningCode getEthnischerHintergrundDefiningCode() {
     return this.ethnischerHintergrundDefiningCode ;
  }

  public void setEthnischerHintergrundNullFlavourDefiningCode(
      NullFlavour ethnischerHintergrundNullFlavourDefiningCode) {
     this.ethnischerHintergrundNullFlavourDefiningCode = ethnischerHintergrundNullFlavourDefiningCode;
  }

  public NullFlavour getEthnischerHintergrundNullFlavourDefiningCode() {
     return this.ethnischerHintergrundNullFlavourDefiningCode ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
