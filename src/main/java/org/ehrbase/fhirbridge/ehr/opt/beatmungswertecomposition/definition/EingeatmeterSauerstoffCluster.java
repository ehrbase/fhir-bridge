package org.ehrbase.fhirbridge.ehr.opt.beatmungswertecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.inspired_oxygen.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-14T15:32:26.823244+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class EingeatmeterSauerstoffCluster implements LocatableEntity {
  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/Eingeatmeter Sauerstoff/Inspiratorische Sauerstofffraktion
   * Description: Prozentualer Anteil des Sauerstoffs in der eingeatmeten Luft.
   * Comment: Zum Beispiel: '24 %'
   */
  @Path("/items[at0053 and name/value='Inspiratorische Sauerstofffraktion']/value")
  private DvProportion inspiratorischeSauerstofffraktion;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Event Series/Jedes Ereignis/Tree/Eingeatmeter Sauerstoff/Inspiratorische Sauerstofffraktion/null_flavour
   */
  @Path("/items[at0053 and name/value='Inspiratorische Sauerstofffraktion']/null_flavour|defining_code")
  private NullFlavour inspiratorischeSauerstofffraktionNullFlavourDefiningCode;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/Eingeatmeter Sauerstoff/Details zur Sauerstoffzufuhr
   * Description: Weitere Details über die Methode der Sauerstoffzufuhr.
   * Comment: Zum Beispiel Details der Befeuchtung oder der assistierten Beatmung.
   */
  @Path("/items[at0058]")
  private List<Cluster> detailsZurSauerstoffzufuhr;

  /**
   * Path: Beatmungswerte/Beobachtungen am Beatmungsgerät/Jedes Ereignis/Eingeatmeter Sauerstoff/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setInspiratorischeSauerstofffraktion(DvProportion inspiratorischeSauerstofffraktion) {
     this.inspiratorischeSauerstofffraktion = inspiratorischeSauerstofffraktion;
  }

  public DvProportion getInspiratorischeSauerstofffraktion() {
     return this.inspiratorischeSauerstofffraktion ;
  }

  public void setInspiratorischeSauerstofffraktionNullFlavourDefiningCode(
      NullFlavour inspiratorischeSauerstofffraktionNullFlavourDefiningCode) {
     this.inspiratorischeSauerstofffraktionNullFlavourDefiningCode = inspiratorischeSauerstofffraktionNullFlavourDefiningCode;
  }

  public NullFlavour getInspiratorischeSauerstofffraktionNullFlavourDefiningCode() {
     return this.inspiratorischeSauerstofffraktionNullFlavourDefiningCode ;
  }

  public void setDetailsZurSauerstoffzufuhr(List<Cluster> detailsZurSauerstoffzufuhr) {
     this.detailsZurSauerstoffzufuhr = detailsZurSauerstoffzufuhr;
  }

  public List<Cluster> getDetailsZurSauerstoffzufuhr() {
     return this.detailsZurSauerstoffzufuhr ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
