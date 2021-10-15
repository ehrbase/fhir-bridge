package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
    date = "2021-09-30T13:08:23.815001+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ReisehistorieBestimmtesReisezielCluster implements LocatableEntity {
  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Land
   * Description: Das besuchte Land.
   */
  @Path("/items[at0011]/value")
  private DvCodedText land;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Bestimmtes Reiseziel/Land/null_flavour
   */
  @Path("/items[at0011]/null_flavour|defining_code")
  private NullFlavour landNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Bundesland / Region
   * Description: Die besuchte Region.
   * Comment: Verschiedene Regionen innerhalb desselben Landes können benannt werden, wenn sie möglicherweise unterschiedliche Gesundheitsrisiken darstellen.
   */
  @Path("/items[at0012]/value")
  private DvCodedText bundeslandRegion;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Bestimmtes Reiseziel/Bundesland / Region/null_flavour
   */
  @Path("/items[at0012]/null_flavour|defining_code")
  private NullFlavour bundeslandRegionNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Stadt
   * Description: Die besuchte Stadt.
   * Comment: Verschiedene Städte innerhalb desselben Landes oder derselben Region können benannt werden, wenn sie möglicherweise unterschiedliche Gesundheitsrisiken darstellen.
   */
  @Path("/items[at0013]/value|value")
  private String stadtValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Bestimmtes Reiseziel/Stadt/null_flavour
   */
  @Path("/items[at0013]/null_flavour|defining_code")
  private NullFlavour stadtNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Einreisedatum
   * Description: Datum der Einreise in den identifizierten Ort.
   */
  @Path("/items[at0014]/value|value")
  private TemporalAccessor einreisedatumValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Bestimmtes Reiseziel/Einreisedatum/null_flavour
   */
  @Path("/items[at0014]/null_flavour|defining_code")
  private NullFlavour einreisedatumNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Abfahrtsdatum
   * Description: Datum an dem der identifizierte Ort verlassen wurde.
   */
  @Path("/items[at0015]/value|value")
  private TemporalAccessor abfahrtsdatumValue;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Bestimmtes Reiseziel/Abfahrtsdatum/null_flavour
   */
  @Path("/items[at0015]/null_flavour|defining_code")
  private NullFlavour abfahrtsdatumNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/Zusätzliche Angaben zum Zielort
   * Description: Zusätzliche strukturierte Details zur Reise nach, von und am Zielort.
   */
  @Path("/items[at0024]")
  private List<Cluster> zusaetzlicheAngabenZumZielort;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setLand(DvCodedText land) {
     this.land = land;
  }

  public DvCodedText getLand() {
     return this.land ;
  }

  public void setLandNullFlavourDefiningCode(NullFlavour landNullFlavourDefiningCode) {
     this.landNullFlavourDefiningCode = landNullFlavourDefiningCode;
  }

  public NullFlavour getLandNullFlavourDefiningCode() {
     return this.landNullFlavourDefiningCode ;
  }

  public void setBundeslandRegion(DvCodedText bundeslandRegion) {
     this.bundeslandRegion = bundeslandRegion;
  }

  public DvCodedText getBundeslandRegion() {
     return this.bundeslandRegion ;
  }

  public void setBundeslandRegionNullFlavourDefiningCode(
      NullFlavour bundeslandRegionNullFlavourDefiningCode) {
     this.bundeslandRegionNullFlavourDefiningCode = bundeslandRegionNullFlavourDefiningCode;
  }

  public NullFlavour getBundeslandRegionNullFlavourDefiningCode() {
     return this.bundeslandRegionNullFlavourDefiningCode ;
  }

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setStadtNullFlavourDefiningCode(NullFlavour stadtNullFlavourDefiningCode) {
     this.stadtNullFlavourDefiningCode = stadtNullFlavourDefiningCode;
  }

  public NullFlavour getStadtNullFlavourDefiningCode() {
     return this.stadtNullFlavourDefiningCode ;
  }

  public void setEinreisedatumValue(TemporalAccessor einreisedatumValue) {
     this.einreisedatumValue = einreisedatumValue;
  }

  public TemporalAccessor getEinreisedatumValue() {
     return this.einreisedatumValue ;
  }

  public void setEinreisedatumNullFlavourDefiningCode(
      NullFlavour einreisedatumNullFlavourDefiningCode) {
     this.einreisedatumNullFlavourDefiningCode = einreisedatumNullFlavourDefiningCode;
  }

  public NullFlavour getEinreisedatumNullFlavourDefiningCode() {
     return this.einreisedatumNullFlavourDefiningCode ;
  }

  public void setAbfahrtsdatumValue(TemporalAccessor abfahrtsdatumValue) {
     this.abfahrtsdatumValue = abfahrtsdatumValue;
  }

  public TemporalAccessor getAbfahrtsdatumValue() {
     return this.abfahrtsdatumValue ;
  }

  public void setAbfahrtsdatumNullFlavourDefiningCode(
      NullFlavour abfahrtsdatumNullFlavourDefiningCode) {
     this.abfahrtsdatumNullFlavourDefiningCode = abfahrtsdatumNullFlavourDefiningCode;
  }

  public NullFlavour getAbfahrtsdatumNullFlavourDefiningCode() {
     return this.abfahrtsdatumNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheAngabenZumZielort(List<Cluster> zusaetzlicheAngabenZumZielort) {
     this.zusaetzlicheAngabenZumZielort = zusaetzlicheAngabenZumZielort;
  }

  public List<Cluster> getZusaetzlicheAngabenZumZielort() {
     return this.zusaetzlicheAngabenZumZielort ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
