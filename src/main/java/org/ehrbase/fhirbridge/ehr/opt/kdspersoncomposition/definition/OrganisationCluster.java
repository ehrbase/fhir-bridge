package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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
@Archetype("openEHR-EHR-CLUSTER.organisation.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.162524944+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class OrganisationCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Namenszeile
   * Description: Der Name oder Bezeichnung der Organisation in unstrukturierter Form.
   * Comment: Zum Beispiel: „Royal Children's Hospital“, "ABC-Krankenpflegedienst", „YNWA Oslo“ oder „JB Smith Rechtsanwälte“.
   */
  @Path("/items[at0001]/value|value")
  private String namenszeileValue;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Organisation/Namenszeile/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour namenszeileNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Identifier
   * Description: Identifikator, der der identifizierten Organisation zugeordnet ist.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als ein Identifikator aufgezeichnet werden kann. Beachten Sie, dass der ID-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält.
   */
  @Path("/items[at0003]")
  private List<VerwaltungsorganisationIdentifierElement> identifier;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Funktion
   * Description: Die Beziehung oder Rolle der Organisation zum Pflegebedürftigen oder Patient.
   * Comment: Zum Beispiel: Onkologe, ambulanter Palliativdienst oder informelles Supportnetzwerk.
   */
  @Path("/items[at0004]")
  private List<OrganisationFunktionElement> funktion;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Adresse
   * Description: Angaben zu einer Adresse der Organisation.
   */
  @Path("/items[at0005]")
  private List<Cluster> adresse;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Elektronische Kommunikation
   * Description: Details zu einer oder mehreren Arten der elektronischen Kommunikation für die Organisation.
   */
  @Path("/items[at0022]")
  private List<Cluster> elektronischeKommunikation;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Kontaktperson
   * Description: Details zu einer oder mehreren Personen innerhalb der Organisation.
   */
  @Path("/items[at0002]")
  private List<Cluster> kontaktperson;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Muttergesellschaft
   * Description: Eine größere Organisation, von der diese Organisation ein Sub- oder Tochterunternehmen ist.
   */
  @Path("/items[at0021]")
  private List<Cluster> muttergesellschaft;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Zusätzliche Details
   * Description: Weitere Angaben zur Organisation.
   */
  @Path("/items[at0017]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/Kommentar
   * Description: Zusätzlicher Text zu der Organisation, der in anderen Bereichen nicht erfasst wird.
   */
  @Path("/items[at0019]/value|value")
  private String kommentarValue;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Organisation/Kommentar/null_flavour
   */
  @Path("/items[at0019]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNamenszeileValue(String namenszeileValue) {
     this.namenszeileValue = namenszeileValue;
  }

  public String getNamenszeileValue() {
     return this.namenszeileValue ;
  }

  public void setNamenszeileNullFlavourDefiningCode(
      NullFlavour namenszeileNullFlavourDefiningCode) {
     this.namenszeileNullFlavourDefiningCode = namenszeileNullFlavourDefiningCode;
  }

  public NullFlavour getNamenszeileNullFlavourDefiningCode() {
     return this.namenszeileNullFlavourDefiningCode ;
  }

  public void setIdentifier(List<VerwaltungsorganisationIdentifierElement> identifier) {
     this.identifier = identifier;
  }

  public List<VerwaltungsorganisationIdentifierElement> getIdentifier() {
     return this.identifier ;
  }

  public void setFunktion(List<OrganisationFunktionElement> funktion) {
     this.funktion = funktion;
  }

  public List<OrganisationFunktionElement> getFunktion() {
     return this.funktion ;
  }

  public void setAdresse(List<Cluster> adresse) {
     this.adresse = adresse;
  }

  public List<Cluster> getAdresse() {
     return this.adresse ;
  }

  public void setElektronischeKommunikation(List<Cluster> elektronischeKommunikation) {
     this.elektronischeKommunikation = elektronischeKommunikation;
  }

  public List<Cluster> getElektronischeKommunikation() {
     return this.elektronischeKommunikation ;
  }

  public void setKontaktperson(List<Cluster> kontaktperson) {
     this.kontaktperson = kontaktperson;
  }

  public List<Cluster> getKontaktperson() {
     return this.kontaktperson ;
  }

  public void setMuttergesellschaft(List<Cluster> muttergesellschaft) {
     this.muttergesellschaft = muttergesellschaft;
  }

  public List<Cluster> getMuttergesellschaft() {
     return this.muttergesellschaft ;
  }

  public void setZusaetzlicheDetails(List<Cluster> zusaetzlicheDetails) {
     this.zusaetzlicheDetails = zusaetzlicheDetails;
  }

  public List<Cluster> getZusaetzlicheDetails() {
     return this.zusaetzlicheDetails ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
