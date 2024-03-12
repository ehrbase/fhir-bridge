package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.Boolean;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-ADMIN_ENTRY.person_data.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.122469587+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class PersonendatenAdminEntry implements EntryEntity {
  /**
   * Path: Person/Personendaten/Person
   * Description: Ein identifizierter Mensch.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person.v1 and name/value='Person']")
  private PersonCluster person;

  /**
   * Path: Person/Personendaten/Daten zur Geburt
   * Description: demografische Daten die Geburt betreffend
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person_birth_data_iso.v0]")
  private DatenZurGeburtCluster datenZurGeburt;

  /**
   * Path: Person/Personendaten/Angaben zum Tod/Verstorben?
   * Description: *
   */
  @Path("/data[at0001]/items[at0024]/items[at0025]/value|value")
  private Boolean verstorbenValue;

  /**
   * Path: Person/Personendaten/Baum/Angaben zum Tod/Verstorben?/null_flavour
   */
  @Path("/data[at0001]/items[at0024]/items[at0025]/null_flavour|defining_code")
  private NullFlavour verstorbenNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Angaben zum Tod/Angaben zum Tod
   * Description: Angaben über den Tod einer Person, die mit übergeordneten Datenelementen für die Registrierung übereinstimmen zB. Sterberegister.
   */
  @Path("/data[at0001]/items[at0024]/items[openEHR-EHR-CLUSTER.death_details.v1]")
  private AngabenZumTodCluster angabenZumTod;

  /**
   * Path: Person/Personendaten/Adresse
   * Description: *
   */
  @Path("/data[at0001]/items[at0005]")
  private List<Cluster> adresse;

  /**
   * Path: Person/Personendaten/Kontaktperson
   * Description: Ein identifizierter Mensch.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person.v1 and name/value='Kontaktperson']")
  private KontaktpersonCluster kontaktperson;

  /**
   * Path: Person/Personendaten/Erweiterungen
   * Description: Details zur Person
   */
  @Path("/data[at0001]/items[at0007]")
  private List<Cluster> erweiterungen;

  /**
   * Path: Person/Personendaten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Person/Personendaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Person/Personendaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPerson(PersonCluster person) {
     this.person = person;
  }

  public PersonCluster getPerson() {
     return this.person ;
  }

  public void setDatenZurGeburt(DatenZurGeburtCluster datenZurGeburt) {
     this.datenZurGeburt = datenZurGeburt;
  }

  public DatenZurGeburtCluster getDatenZurGeburt() {
     return this.datenZurGeburt ;
  }

  public void setVerstorbenValue(Boolean verstorbenValue) {
     this.verstorbenValue = verstorbenValue;
  }

  public Boolean isVerstorbenValue() {
     return this.verstorbenValue ;
  }

  public void setVerstorbenNullFlavourDefiningCode(NullFlavour verstorbenNullFlavourDefiningCode) {
     this.verstorbenNullFlavourDefiningCode = verstorbenNullFlavourDefiningCode;
  }

  public NullFlavour getVerstorbenNullFlavourDefiningCode() {
     return this.verstorbenNullFlavourDefiningCode ;
  }

  public void setAngabenZumTod(AngabenZumTodCluster angabenZumTod) {
     this.angabenZumTod = angabenZumTod;
  }

  public AngabenZumTodCluster getAngabenZumTod() {
     return this.angabenZumTod ;
  }

  public void setAdresse(List<Cluster> adresse) {
     this.adresse = adresse;
  }

  public List<Cluster> getAdresse() {
     return this.adresse ;
  }

  public void setKontaktperson(KontaktpersonCluster kontaktperson) {
     this.kontaktperson = kontaktperson;
  }

  public KontaktpersonCluster getKontaktperson() {
     return this.kontaktperson ;
  }

  public void setErweiterungen(List<Cluster> erweiterungen) {
     this.erweiterungen = erweiterungen;
  }

  public List<Cluster> getErweiterungen() {
     return this.erweiterungen ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
