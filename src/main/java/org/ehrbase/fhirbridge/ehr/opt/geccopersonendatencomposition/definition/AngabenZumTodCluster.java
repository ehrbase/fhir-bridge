package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.death_details.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-15T13:49:30.059094+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class AngabenZumTodCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Angaben zum Tod/Angaben zum Tod/Sterbedatum
   * Description: Der Zeitpunkt, an dem die Person verstorben ist.
   */
  @Path("/items[at0001]/value|value")
  private TemporalAccessor sterbedatumValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Angaben zum Tod/Angaben zum Tod/Sterbedatum/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour sterbedatumNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Angaben zum Tod/Angaben zum Tod/Spezifische Angaben
   * Description: Spezifische strukturierte Angaben über den Tod.
   */
  @Path("/items[at0005]")
  private List<Cluster> spezifischeAngaben;

  /**
   * Path: GECCO_Personendaten/Personendaten/Angaben zum Tod/Angaben zum Tod/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSterbedatumValue(TemporalAccessor sterbedatumValue) {
     this.sterbedatumValue = sterbedatumValue;
  }

  public TemporalAccessor getSterbedatumValue() {
     return this.sterbedatumValue ;
  }

  public void setSterbedatumNullFlavourDefiningCode(
      NullFlavour sterbedatumNullFlavourDefiningCode) {
     this.sterbedatumNullFlavourDefiningCode = sterbedatumNullFlavourDefiningCode;
  }

  public NullFlavour getSterbedatumNullFlavourDefiningCode() {
     return this.sterbedatumNullFlavourDefiningCode ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
