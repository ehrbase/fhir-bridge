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
@Archetype("openEHR-EHR-CLUSTER.person.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.157949801+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class KontaktpersonCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Kontaktperson/Name
   * Description: Der unstrukturierte Name der Person.
   * Comment: Der Inhalt dieses Datenelements kann aus einer oder mehreren Komponenten vom CLUSTER.structured_name abgeleitet werden, die zusammen als ein Textstring kombiniert werden. Zum Beispiel: „John Markham“, „Professor Sir John Markham“, „John Markham Jnr MP“.
   */
  @Path("/items[at0001]/value|value")
  private String nameValue;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Name/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour nameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/Name strukturiert
   * Description: Alternative Darstellung des vollständigen Namens der Person durch Trennung in diskrete, strukturierte Komponenten.
   * Comment: Es können beliebige oder alle strukturierte Elemente des Namens als Textzeichenfolge kombiniert und im Datenelement „Name“ präsentieren werden.
   */
  @Path("/items[at0002]")
  private List<Cluster> nameStrukturiert;

  /**
   * Path: Person/Personendaten/Kontaktperson/Rolle (Relationship)
   * Description: Die Beziehung oder die Rolle der Person zur Person in der elektronischen Gesundheitsakte.
   * Comment: Zum Beispiel - der Inhaber einer Patientenverfügung, Kontaktperson in einer Organisation, Verwandter in einem Eintrag zur Familienananese, Probenabnehmer oder Zeuge eines Sturzes oder Unfalls. Wenn die anhand dieses Archetyps beschriebene Person der/die Patient/in der Gesundheitsakte ist, dann ist dieses Datenelement redundant.
   */
  @Path("/items[at0004 and name/value='Rolle (Relationship)']")
  private List<KontaktpersonRolleRelationshipElement> rolleRelationship;

  /**
   * Path: Person/Personendaten/Kontaktperson/Adresse
   * Description: Informationen über den Standort einer Person, eines physischen Gebäudes oder einer Landmarke.
   */
  @Path("/items[openEHR-EHR-CLUSTER.address.v1]")
  private AdresseCluster adresse;

  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation
   * Description: Details zu einer bestimmten Art der elektronischen Kommunikation.
   */
  @Path("/items[openEHR-EHR-CLUSTER.electronic_communication.v1]")
  private List<ElektronischeKommunikationCluster> elektronischeKommunikation;

  /**
   * Path: Person/Personendaten/Kontaktperson/Organisation
   * Description: Eine Einheit, die aus einer oder mehreren Personen besteht und ein bestimmtes Ziel hat.
   * Comment: Zum Beispiel: ein Unternehmen, eine Institution, ein Verband, ein Netzwerk, eine Abteilung, eine Gemeindegruppe, eine Gesundheitspraxisgruppe, ein Kostenträger/Versicherer, ein Pflegeteam oder eine Gruppe von Nachbarn, die Pflege oder Unterstützung leisten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.organisation.v1]")
  private OrganisationCluster organisation;

  /**
   * Path: Person/Personendaten/Kontaktperson/Zusätzliche Angaben
   * Description: Weitere Angaben zur Person.
   */
  @Path("/items[at0008]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Path: Person/Personendaten/Kontaktperson/Foto
   * Description: Foto der Person.
   */
  @Path("/items[at0009]")
  private List<Cluster> foto;

  /**
   * Path: Person/Personendaten/Kontaktperson/Kommentar
   * Description: Zusätzliche Beschreibung zu der Person, die in anderen Bereichen nicht erfasst wird.
   */
  @Path("/items[at0010]/value|value")
  private String kommentarValue;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Kommentar/null_flavour
   */
  @Path("/items[at0010]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

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

  public void setNameStrukturiert(List<Cluster> nameStrukturiert) {
     this.nameStrukturiert = nameStrukturiert;
  }

  public List<Cluster> getNameStrukturiert() {
     return this.nameStrukturiert ;
  }

  public void setRolleRelationship(List<KontaktpersonRolleRelationshipElement> rolleRelationship) {
     this.rolleRelationship = rolleRelationship;
  }

  public List<KontaktpersonRolleRelationshipElement> getRolleRelationship() {
     return this.rolleRelationship ;
  }

  public void setAdresse(AdresseCluster adresse) {
     this.adresse = adresse;
  }

  public AdresseCluster getAdresse() {
     return this.adresse ;
  }

  public void setElektronischeKommunikation(
      List<ElektronischeKommunikationCluster> elektronischeKommunikation) {
     this.elektronischeKommunikation = elektronischeKommunikation;
  }

  public List<ElektronischeKommunikationCluster> getElektronischeKommunikation() {
     return this.elektronischeKommunikation ;
  }

  public void setOrganisation(OrganisationCluster organisation) {
     this.organisation = organisation;
  }

  public OrganisationCluster getOrganisation() {
     return this.organisation ;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
  }

  public void setFoto(List<Cluster> foto) {
     this.foto = foto;
  }

  public List<Cluster> getFoto() {
     return this.foto ;
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
