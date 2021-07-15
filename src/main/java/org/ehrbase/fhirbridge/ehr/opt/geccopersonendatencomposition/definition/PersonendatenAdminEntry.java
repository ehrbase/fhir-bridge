package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
    date = "2021-07-15T13:49:30.030576+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class PersonendatenAdminEntry implements EntryEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname
   * Description: Details des Personennamens einer Privatperson, eines Versorgers oder einer Dritten Person.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person_name.v0]")
  private List<PersonennameCluster> personenname;

  /**
   * Path: GECCO_Personendaten/Personendaten/Daten zur Geburt
   * Description: demografische Daten die Geburt betreffend
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.person_birth_data_iso.v0]")
  private DatenZurGeburtCluster datenZurGeburt;

  /**
   * Path: GECCO_Personendaten/Personendaten/Angaben zum Tod/Verstorben?
   * Description: *
   */
  @Path("/data[at0001]/items[at0024]/items[at0025]/value|value")
  private Boolean verstorbenValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Angaben zum Tod/Verstorben?/null_flavour
   */
  @Path("/data[at0001]/items[at0024]/items[at0025]/null_flavour|defining_code")
  private NullFlavour verstorbenNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Angaben zum Tod/Angaben zum Tod
   * Description: Angaben über den Tod einer Person, die mit übergeordneten Datenelementen für die Registrierung übereinstimmen zB. Sterberegister.
   */
  @Path("/data[at0001]/items[at0024]/items[openEHR-EHR-CLUSTER.death_details.v1]")
  private AngabenZumTodCluster angabenZumTod;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse
   * Description: Details zur Adresse abgestimmt mit FHIR-Ressource.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.address_cc.v0]")
  private List<AdresseCluster> adresse;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation
   * Description: Angaben zur persönlichen oder organisatorischen Kommunikation.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.telecom_details.v0]")
  private List<EinzelheitenDerKommunikationCluster> einzelheitenDerKommunikation;

  /**
   * Path: GECCO_Personendaten/Personendaten/Ethnischer Hintergrund
   * Description: Detaillierte Beschreibung des ethnischen Hintergrundes einer Person, um Besondheiten, wie Medikamentenverträglichkeit oder Gesundheitsrisiken abzubilden.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.ethnischer_hintergrund.v0]")
  private List<EthnischerHintergrundCluster> ethnischerHintergrund;

  /**
   * Path: GECCO_Personendaten/Personendaten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Personendaten/Personendaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Personendaten/Personendaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPersonenname(List<PersonennameCluster> personenname) {
     this.personenname = personenname;
  }

  public List<PersonennameCluster> getPersonenname() {
     return this.personenname ;
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

  public void setAdresse(List<AdresseCluster> adresse) {
     this.adresse = adresse;
  }

  public List<AdresseCluster> getAdresse() {
     return this.adresse ;
  }

  public void setEinzelheitenDerKommunikation(
      List<EinzelheitenDerKommunikationCluster> einzelheitenDerKommunikation) {
     this.einzelheitenDerKommunikation = einzelheitenDerKommunikation;
  }

  public List<EinzelheitenDerKommunikationCluster> getEinzelheitenDerKommunikation() {
     return this.einzelheitenDerKommunikation ;
  }

  public void setEthnischerHintergrund(List<EthnischerHintergrundCluster> ethnischerHintergrund) {
     this.ethnischerHintergrund = ethnischerHintergrund;
  }

  public List<EthnischerHintergrundCluster> getEthnischerHintergrund() {
     return this.ethnischerHintergrund ;
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
