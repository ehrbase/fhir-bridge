package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.organization.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:38.276573900+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster implements LocatableEntity {
  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Typ
   * Description: Art der Organisationseinheit.
   * Comment: Zum Beispiel: Fachabteilung im Krankenhaus, Versicherungsunternehmen, Sponsor
   */
  @Path("/items[at0051]/value|value")
  private String typValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Typ/null_flavour
   */
  @Path("/items[at0051]/null_flavour|defining_code")
  private NullFlavour typNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Organisationsschlüssel
   * Description: Eindeutiger Identifikator der Organisationseinheit.
   */
  @Path("/items[at0024]/value|value")
  private String organisationsschluesselValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Organisationsschlüssel/null_flavour
   */
  @Path("/items[at0024]/null_flavour|defining_code")
  private NullFlavour organisationsschluesselNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Name
   * Description: Bezeichnung für die Organisationseinheit
   */
  @Path("/items[at0052]/value|value")
  private String nameValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Name/null_flavour
   */
  @Path("/items[at0052]/null_flavour|defining_code")
  private NullFlavour nameNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Aktiv/Inaktiv
   * Description: Gibt an, ob die Organisationseinheit noch aktiv ist.
   */
  @Path("/items[at0050]/value|value")
  private Boolean aktivInaktivValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Aktiv/Inaktiv/null_flavour
   */
  @Path("/items[at0050]/null_flavour|defining_code")
  private NullFlavour aktivInaktivNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Zusätzliche Beschreibung
   * Description: Zusätzliche Informationen
   */
  @Path("/items[at0046]/value|value")
  private String zusaetzlicheBeschreibungValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Zusätzliche Beschreibung/null_flavour
   */
  @Path("/items[at0046]/null_flavour|defining_code")
  private NullFlavour zusaetzlicheBeschreibungNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/Details
   * Description: Für die Erfassung weiterer Angaben über die Organisationseinheit, zum Beispiel Adresse oder Telekommunikationsdetails.
   */
  @Path("/items[at0047]")
  private List<Cluster> details;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTypValue(String typValue) {
     this.typValue = typValue;
  }

  public String getTypValue() {
     return this.typValue ;
  }

  public void setTypNullFlavourDefiningCode(NullFlavour typNullFlavourDefiningCode) {
     this.typNullFlavourDefiningCode = typNullFlavourDefiningCode;
  }

  public NullFlavour getTypNullFlavourDefiningCode() {
     return this.typNullFlavourDefiningCode ;
  }

  public void setOrganisationsschluesselValue(String organisationsschluesselValue) {
     this.organisationsschluesselValue = organisationsschluesselValue;
  }

  public String getOrganisationsschluesselValue() {
     return this.organisationsschluesselValue ;
  }

  public void setOrganisationsschluesselNullFlavourDefiningCode(
      NullFlavour organisationsschluesselNullFlavourDefiningCode) {
     this.organisationsschluesselNullFlavourDefiningCode = organisationsschluesselNullFlavourDefiningCode;
  }

  public NullFlavour getOrganisationsschluesselNullFlavourDefiningCode() {
     return this.organisationsschluesselNullFlavourDefiningCode ;
  }

  public void setNameValue(String nameValue) {
     this.nameValue = nameValue;
  }

  public String getNameValue() {
     return this.nameValue ;
  }

  public void setNameNullFlavourDefiningCode(NullFlavour nameNullFlavourDefiningCode) {
     this.nameNullFlavourDefiningCode = nameNullFlavourDefiningCode;
  }

  public NullFlavour getNameNullFlavourDefiningCode() {
     return this.nameNullFlavourDefiningCode ;
  }

  public void setAktivInaktivValue(Boolean aktivInaktivValue) {
     this.aktivInaktivValue = aktivInaktivValue;
  }

  public Boolean isAktivInaktivValue() {
     return this.aktivInaktivValue ;
  }

  public void setAktivInaktivNullFlavourDefiningCode(
      NullFlavour aktivInaktivNullFlavourDefiningCode) {
     this.aktivInaktivNullFlavourDefiningCode = aktivInaktivNullFlavourDefiningCode;
  }

  public NullFlavour getAktivInaktivNullFlavourDefiningCode() {
     return this.aktivInaktivNullFlavourDefiningCode ;
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

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
