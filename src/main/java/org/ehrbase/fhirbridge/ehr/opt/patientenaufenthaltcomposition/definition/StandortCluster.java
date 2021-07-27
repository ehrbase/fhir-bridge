package org.ehrbase.fhirbridge.ehr.opt.patientenaufenthaltcomposition.definition;

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
    date = "2021-07-27T16:16:21.061109+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class StandortCluster implements LocatableEntity {
  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Campus
   * Description: Eine Gruppe von Gebäuden an anderen Orten, wie ein örtlich entfernter Campus, der außerhalb der Einrichtung liegt, aber zur Institution gehört.
   */
  @Path("/items[at0024]/value|value")
  private String campusValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Campus/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour campusNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Gebäudegruppe
   * Description: Ein Gebäude oder Bauwerk. Dazu können Räume, Flure, Flügel, etc. gehören. Es hat möglicherweise keine Wände oder ein Dach, wird aber dennoch als definierter/zugeordneter Raum angesehen.
   */
  @Path("/items[at0025]/value|value")
  private String gebaeudegruppeValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Gebäudegruppe/null_flavour
   */
  @Path("/items[at0025]/null_flavour|defining_code")
  private NullFlavour gebaeudegruppeNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Ebene
   * Description: Die Ebene in einem mehrstöckigen Gebäude/Bauwerk.
   */
  @Path("/items[at0028]/value|value")
  private String ebeneValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Ebene/null_flavour
   */
  @Path("/items[at0028]/null_flavour|defining_code")
  private NullFlavour ebeneNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Station
   * Description: Eine Station ist Teil einer medizinischen Einrichtung, die Räume und andere Arten von Orten enthalten kann.
   */
  @Path("/items[at0027]/value|value")
  private String stationValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Station/null_flavour
   */
  @Path("/items[at0027]/null_flavour|defining_code")
  private NullFlavour stationNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Zimmer
   * Description: Ein Ort, der als Zimmer deklariert wurde. Bei einigen Standorten kann das Zimmer im Flur liegen zB: Station XYZ Flur 2. Hierbei liegt der Bettstellplatz 2 auf dem Flur der jeweiligen Station.
   */
  @Path("/items[at0029]/value|value")
  private String zimmerValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Zimmer/null_flavour
   */
  @Path("/items[at0029]/null_flavour|defining_code")
  private NullFlavour zimmerNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Bettplatz
   * Description: Beschreibung des Bettstellplatzes z.B. Bett stand neben dem Fenster oder neben der Tür.
   */
  @Path("/items[at0034 and name/value='Bettplatz']/value|value")
  private String bettplatzValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Bettplatz/null_flavour
   */
  @Path("/items[at0034 and name/value='Bettplatz']/null_flavour|defining_code")
  private NullFlavour bettplatzNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Zusätzliche Beschreibung
   * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
   */
  @Path("/items[at0046]/value|value")
  private String zusaetzlicheBeschreibungValue;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Baum/Standort/Zusätzliche Beschreibung/null_flavour
   */
  @Path("/items[at0046]/null_flavour|defining_code")
  private NullFlavour zusaetzlicheBeschreibungNullFlavourDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Details zum Bett
   * Description: Ein Instrument, ein Gerät, ein Implantat, ein Material oder ähnliches, das für die Bereitstellung von Gesundheitsleistungen verwendet wird. In diesem Zusammenhang umfasst ein medizinisches Gerät eine breite Palette von Geräten, die auf verschiedene physikalische, mechanische, thermische oder ähnliche Weise wirken, schließt jedoch insbesondere Geräte aus, die auf medizinischem Wege wirken, wie zum Beispiel pharmakologische, metabolische oder immunologische Methoden. Der Geltungsbereich umfasst
   * Einweggeräte sowie langlebige oder dauerhafte Geräte, die nachverfolgt,
   * gewartet oder regelmäßig kalibriert werden müssen, wobei zu berücksichtigen ist, dass für jeden Gerätetyp bestimmte Datenaufzeichnungsanforderungen gelten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.device.v1 and name/value='Details zum Bett']")
  private DetailsZumBettCluster detailsZumBett;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/Leitende Organisationseinheit
   * Description: Organisation, die für die Bereitstellung und Instandhaltung verantwortlich ist
   *
   * Die Organisation, die für die Bereitstellung und den Unterhalt des Standorts verantwortlich ist.
   */
  @Path("/items[at0049]")
  private List<Cluster> leitendeOrganisationseinheit;

  /**
   * Path: Patientenaufenthalt/Versorgungsaufenthalt/Standort/feeder_audit
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

  public void setBettplatzValue(String bettplatzValue) {
     this.bettplatzValue = bettplatzValue;
  }

  public String getBettplatzValue() {
     return this.bettplatzValue ;
  }

  public void setBettplatzNullFlavourDefiningCode(NullFlavour bettplatzNullFlavourDefiningCode) {
     this.bettplatzNullFlavourDefiningCode = bettplatzNullFlavourDefiningCode;
  }

  public NullFlavour getBettplatzNullFlavourDefiningCode() {
     return this.bettplatzNullFlavourDefiningCode ;
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

  public void setDetailsZumBett(DetailsZumBettCluster detailsZumBett) {
     this.detailsZumBett = detailsZumBett;
  }

  public DetailsZumBettCluster getDetailsZumBett() {
     return this.detailsZumBett ;
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
