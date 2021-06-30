package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:38.259842400+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class VorherigerPatientenstandortVorAufnahmeCluster implements LocatableEntity {
  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Campus
   * Description: Eine Gruppe von Gebäuden an anderen Orten, wie ein örtlich entfernter Campus, der außerhalb der Einrichtung liegt, aber zur Institution gehört.
   */
  @Path("/items[at0024]/value|value")
  private String campusValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Campus/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour campusNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Gebäudegruppe
   * Description: Ein Gebäude oder Bauwerk. Dazu können Räume, Flure, Flügel, etc. gehören. Es hat möglicherweise keine Wände oder ein Dach, wird aber dennoch als definierter/zugeordneter Raum angesehen.
   */
  @Path("/items[at0025]/value|value")
  private String gebaeudegruppeValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Gebäudegruppe/null_flavour
   */
  @Path("/items[at0025]/null_flavour|defining_code")
  private NullFlavour gebaeudegruppeNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Ebene
   * Description: Die Ebene in einem mehrstöckigen Gebäude/Bauwerk.
   */
  @Path("/items[at0028]/value|value")
  private String ebeneValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Ebene/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour ebeneNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Station
   * Description: Eine Station ist Teil einer medizinischen Einrichtung, die Räume und andere Arten von Orten enthalten kann.
   */
  @Path("/items[at0027]/value|value")
  private String stationValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Station/null_flavour
   */
  @Path("/items[at0027]/null_flavour|defining_code")
  private NullFlavour stationNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Zimmer
   * Description: Ein Ort, der als Zimmer deklariert wurde. Bei einigen Standorten kann das Zimmer im Flur liegen zB: Station XYZ Flur 2. Hierbei liegt der Bettstellplatz 2 auf dem Flur der jeweiligen Station.
   */
  @Path("/items[at0029]/value|value")
  private String zimmerValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Zimmer/null_flavour
   */
  @Path("/items[at0029]/null_flavour|defining_code")
  private NullFlavour zimmerNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Bettstellplatz
   * Description: Beschreibung des Bettstellplatzes z.B. Bett stand neben dem Fenster oder neben der Tür.
   */
  @Path("/items[at0034]/value|value")
  private String bettstellplatzValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Bettstellplatz/null_flavour
   */
  @Path("/items[at0034]/null_flavour|defining_code")
  private NullFlavour bettstellplatzNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Zusätzliche Beschreibung
   * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046]/value|value")
  private String zusaetzlicheBeschreibungValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorheriger Patientenstandort (vor Aufnahme)/Zusätzliche Beschreibung/null_flavour
   */
  @Path("/items[at0046]/null_flavour|defining_code")
  private NullFlavour zusaetzlicheBeschreibungNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Details
   * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/Leitende Organisationseinheit
   * Description: Organisation, die für die Bereitstellung und Instandhaltung verantwortlich ist
   *
   * Die Organisation, die für die Bereitstellung und den Unterhalt des Standorts verantwortlich ist.
   */
  @Path("/items[at0049]")
  private List<Cluster> leitendeOrganisationseinheit;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setCampusValue(String campusValue) {
     this.campusValue = campusValue;
  }

  public String getCampusValue() {
     return this.campusValue ;
  }

  public void setCampusNullFlavourDefiningCode(NullFlavour campusNullFlavourDefiningCode) {
     this.campusNullFlavourDefiningCode = campusNullFlavourDefiningCode;
  }

  public NullFlavour getCampusNullFlavourDefiningCode() {
     return this.campusNullFlavourDefiningCode ;
  }

  public void setGebaeudegruppeValue(String gebaeudegruppeValue) {
     this.gebaeudegruppeValue = gebaeudegruppeValue;
  }

  public String getGebaeudegruppeValue() {
     return this.gebaeudegruppeValue ;
  }

  public void setGebaeudegruppeNullFlavourDefiningCode(
      NullFlavour gebaeudegruppeNullFlavourDefiningCode) {
     this.gebaeudegruppeNullFlavourDefiningCode = gebaeudegruppeNullFlavourDefiningCode;
  }

  public NullFlavour getGebaeudegruppeNullFlavourDefiningCode() {
     return this.gebaeudegruppeNullFlavourDefiningCode ;
  }

  public void setEbeneValue(String ebeneValue) {
     this.ebeneValue = ebeneValue;
  }

  public String getEbeneValue() {
     return this.ebeneValue ;
  }

  public void setEbeneNullFlavourDefiningCode(NullFlavour ebeneNullFlavourDefiningCode) {
     this.ebeneNullFlavourDefiningCode = ebeneNullFlavourDefiningCode;
  }

  public NullFlavour getEbeneNullFlavourDefiningCode() {
     return this.ebeneNullFlavourDefiningCode ;
  }

  public void setStationValue(String stationValue) {
     this.stationValue = stationValue;
  }

  public String getStationValue() {
     return this.stationValue ;
  }

  public void setStationNullFlavourDefiningCode(NullFlavour stationNullFlavourDefiningCode) {
     this.stationNullFlavourDefiningCode = stationNullFlavourDefiningCode;
  }

  public NullFlavour getStationNullFlavourDefiningCode() {
     return this.stationNullFlavourDefiningCode ;
  }

  public void setZimmerValue(String zimmerValue) {
     this.zimmerValue = zimmerValue;
  }

  public String getZimmerValue() {
     return this.zimmerValue ;
  }

  public void setZimmerNullFlavourDefiningCode(NullFlavour zimmerNullFlavourDefiningCode) {
     this.zimmerNullFlavourDefiningCode = zimmerNullFlavourDefiningCode;
  }

  public NullFlavour getZimmerNullFlavourDefiningCode() {
     return this.zimmerNullFlavourDefiningCode ;
  }

  public void setBettstellplatzValue(String bettstellplatzValue) {
     this.bettstellplatzValue = bettstellplatzValue;
  }

  public String getBettstellplatzValue() {
     return this.bettstellplatzValue ;
  }

  public void setBettstellplatzNullFlavourDefiningCode(
      NullFlavour bettstellplatzNullFlavourDefiningCode) {
     this.bettstellplatzNullFlavourDefiningCode = bettstellplatzNullFlavourDefiningCode;
  }

  public NullFlavour getBettstellplatzNullFlavourDefiningCode() {
     return this.bettstellplatzNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheBeschreibungValue(String zusaetzlicheBeschreibungValue) {
     this.zusaetzlicheBeschreibungValue = zusaetzlicheBeschreibungValue;
  }

  public String getZusaetzlicheBeschreibungValue() {
     return this.zusaetzlicheBeschreibungValue ;
  }

  public void setZusaetzlicheBeschreibungNullFlavourDefiningCode(
      NullFlavour zusaetzlicheBeschreibungNullFlavourDefiningCode) {
     this.zusaetzlicheBeschreibungNullFlavourDefiningCode = zusaetzlicheBeschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getZusaetzlicheBeschreibungNullFlavourDefiningCode() {
     return this.zusaetzlicheBeschreibungNullFlavourDefiningCode ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }

  public void setLeitendeOrganisationseinheit(List<Cluster> leitendeOrganisationseinheit) {
     this.leitendeOrganisationseinheit = leitendeOrganisationseinheit;
  }

  public List<Cluster> getLeitendeOrganisationseinheit() {
     return this.leitendeOrganisationseinheit ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
