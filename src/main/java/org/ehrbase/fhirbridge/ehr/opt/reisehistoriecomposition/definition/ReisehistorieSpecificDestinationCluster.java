package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-06T14:29:47.141465200+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ReisehistorieSpecificDestinationCluster implements LocatableEntity {
  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/Country
   * Description: The country visited.
   */
  @Path("/items[at0011]/value|defining_code")
  private CountryDefiningCode countryDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Specific destination/Country/null_flavour
   */
  @Path("/items[at0011]/null_flavour|defining_code")
  private NullFlavour countryNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/State/region
   * Description: The region visited.
   * Comment: Different regions within the same country maybe identified if they potentially pose different health risks.
   */
  @Path("/items[at0012]/value|defining_code")
  private StateRegionDefiningCode stateRegionDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Specific destination/State/region/null_flavour
   */
  @Path("/items[at0012]/null_flavour|defining_code")
  private NullFlavour stateRegionNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/City
   * Description: The city visited.
   * Comment: Different cities within the same country or region maybe identified if they potentially pose different health risks.
   */
  @Path("/items[at0013]/value|value")
  private String cityValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Specific destination/City/null_flavour
   */
  @Path("/items[at0013]/null_flavour|defining_code")
  private NullFlavour cityNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/Date of entry
   * Description: Date of entry to the identified location.
   */
  @Path("/items[at0014]/value|value")
  private TemporalAccessor dateOfEntryValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Specific destination/Date of entry/null_flavour
   */
  @Path("/items[at0014]/null_flavour|defining_code")
  private NullFlavour dateOfEntryNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/Date of exit
   * Description: Date of exit from the identified location.
   */
  @Path("/items[at0015]/value|value")
  private TemporalAccessor dateOfExitValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Specific destination/Date of exit/null_flavour
   */
  @Path("/items[at0015]/null_flavour|defining_code")
  private NullFlavour dateOfExitNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/Additional destination details
   * Description: Additional structured details about the travel to, from and at the destination.
   */
  @Path("/items[at0024]")
  private List<Cluster> additionalDestinationDetails;

  /**
   * Path: Reisehistorie/Reisehistorie/Specific destination/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setCountryDefiningCode(CountryDefiningCode countryDefiningCode) {
     this.countryDefiningCode = countryDefiningCode;
  }

  public CountryDefiningCode getCountryDefiningCode() {
     return this.countryDefiningCode ;
  }

  public void setCountryNullFlavourDefiningCode(NullFlavour countryNullFlavourDefiningCode) {
     this.countryNullFlavourDefiningCode = countryNullFlavourDefiningCode;
  }

  public NullFlavour getCountryNullFlavourDefiningCode() {
     return this.countryNullFlavourDefiningCode ;
  }

  public void setStateRegionDefiningCode(StateRegionDefiningCode stateRegionDefiningCode) {
     this.stateRegionDefiningCode = stateRegionDefiningCode;
  }

  public StateRegionDefiningCode getStateRegionDefiningCode() {
     return this.stateRegionDefiningCode ;
  }

  public void setStateRegionNullFlavourDefiningCode(
      NullFlavour stateRegionNullFlavourDefiningCode) {
     this.stateRegionNullFlavourDefiningCode = stateRegionNullFlavourDefiningCode;
  }

  public NullFlavour getStateRegionNullFlavourDefiningCode() {
     return this.stateRegionNullFlavourDefiningCode ;
  }

  public void setCityValue(String cityValue) {
     this.cityValue = cityValue;
  }

  public String getCityValue() {
     return this.cityValue ;
  }

  public void setCityNullFlavourDefiningCode(NullFlavour cityNullFlavourDefiningCode) {
     this.cityNullFlavourDefiningCode = cityNullFlavourDefiningCode;
  }

  public NullFlavour getCityNullFlavourDefiningCode() {
     return this.cityNullFlavourDefiningCode ;
  }

  public void setDateOfEntryValue(TemporalAccessor dateOfEntryValue) {
     this.dateOfEntryValue = dateOfEntryValue;
  }

  public TemporalAccessor getDateOfEntryValue() {
     return this.dateOfEntryValue ;
  }

  public void setDateOfEntryNullFlavourDefiningCode(
      NullFlavour dateOfEntryNullFlavourDefiningCode) {
     this.dateOfEntryNullFlavourDefiningCode = dateOfEntryNullFlavourDefiningCode;
  }

  public NullFlavour getDateOfEntryNullFlavourDefiningCode() {
     return this.dateOfEntryNullFlavourDefiningCode ;
  }

  public void setDateOfExitValue(TemporalAccessor dateOfExitValue) {
     this.dateOfExitValue = dateOfExitValue;
  }

  public TemporalAccessor getDateOfExitValue() {
     return this.dateOfExitValue ;
  }

  public void setDateOfExitNullFlavourDefiningCode(NullFlavour dateOfExitNullFlavourDefiningCode) {
     this.dateOfExitNullFlavourDefiningCode = dateOfExitNullFlavourDefiningCode;
  }

  public NullFlavour getDateOfExitNullFlavourDefiningCode() {
     return this.dateOfExitNullFlavourDefiningCode ;
  }

  public void setAdditionalDestinationDetails(List<Cluster> additionalDestinationDetails) {
     this.additionalDestinationDetails = additionalDestinationDetails;
  }

  public List<Cluster> getAdditionalDestinationDetails() {
     return this.additionalDestinationDetails ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
