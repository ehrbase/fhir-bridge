package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.PointEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.576299187+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
@OptionFor("POINT_EVENT")
public class KoerpergewichtBeliebigesEreignisPointEvent implements PointEventEntity, KoerpergewichtBeliebigesEreignisChoice {
  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/Gewicht
   * Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double gewichtMagnitude;

  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/Gewicht
   * Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String gewichtUnits;

  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/Simple/Gewicht/null_flavour
   */
  @Path("/data[at0001]/items[at0004]/null_flavour|defining_code")
  private NullFlavour gewichtNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/State structure
   * Description: @ internal @
   */
  @Path("/state[at0008]")
  private ItemTree stateStructure;

  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Selbstüberwachung/Körpergewicht/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  public void setGewichtMagnitude(Double gewichtMagnitude) {
     this.gewichtMagnitude = gewichtMagnitude;
  }

  public Double getGewichtMagnitude() {
     return this.gewichtMagnitude ;
  }

  public void setGewichtUnits(String gewichtUnits) {
     this.gewichtUnits = gewichtUnits;
  }

  public String getGewichtUnits() {
     return this.gewichtUnits ;
  }

  public void setGewichtNullFlavourDefiningCode(NullFlavour gewichtNullFlavourDefiningCode) {
     this.gewichtNullFlavourDefiningCode = gewichtNullFlavourDefiningCode;
  }

  public NullFlavour getGewichtNullFlavourDefiningCode() {
     return this.gewichtNullFlavourDefiningCode ;
  }

  public void setStateStructure(ItemTree stateStructure) {
     this.stateStructure = stateStructure;
  }

  public ItemTree getStateStructure() {
     return this.stateStructure ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }
}
