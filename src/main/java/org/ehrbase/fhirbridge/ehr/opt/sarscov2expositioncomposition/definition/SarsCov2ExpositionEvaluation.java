package org.ehrbase.fhirbridge.ehr.opt.sarscov2expositioncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.infectious_exposure.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-14T13:56:15.921082+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class SarsCov2ExpositionEvaluation implements EntryEntity {
  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Infektionserreger
   * Description: Identifizierung des Organismus, Stoffes, der Symptome oder des Zustands, dem die Person ausgesetzt war.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String infektionserregerValue;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Tree/Infektionserreger/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour infektionserregerNullFlavourDefiningCode;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Exposition vorhanden?
   * Description: Beschreibung der Gesamtexposition.
   */
  @Path("/data[at0001]/items[at0003 and name/value='Exposition vorhanden?']/value")
  private DvCodedText expositionVorhanden;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Tree/Exposition vorhanden?/null_flavour
   */
  @Path("/data[at0001]/items[at0003 and name/value='Exposition vorhanden?']/null_flavour|defining_code")
  private NullFlavour expositionVorhandenNullFlavourDefiningCode;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Beschreibung der Exposition
   * Description: Angaben zur Expositionsmethode.
   * Comment: Zum Beispiel: Luftübertragung, direkter Kontakt oder Nadelstich.
   */
  @Path("/data[at0001]/items[at0005]/items[at0006]/value|value")
  private String beschreibungDerExpositionValue;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Tree/Spezifische Details zur Exposition/Beschreibung der Exposition/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/items[at0006]/null_flavour|defining_code")
  private NullFlavour beschreibungDerExpositionNullFlavourDefiningCode;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Datum/Uhrzeit der Exposition
   * Description: Datum und Zeit der Exposition.
   * Comment: Kann in Template geklont und für ein bestimmtes Datum/eine bestimmte Uhrzeit bei Expositionsbeginn und -ende umbenannt werden.
   */
  @Path("/data[at0001]/items[at0005]/items[at0007]/value|value")
  private TemporalAccessor datumUhrzeitDerExpositionValue;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Tree/Spezifische Details zur Exposition/Datum/Uhrzeit der Exposition/null_flavour
   */
  @Path("/data[at0001]/items[at0005]/items[at0007]/null_flavour|defining_code")
  private NullFlavour datumUhrzeitDerExpositionNullFlavourDefiningCode;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Angaben zur anatomischen Stelle
   * Description: Strukturierte Angaben über die exponierte anatomische Stelle.
   */
  @Path("/data[at0001]/items[at0005]/items[at0019]")
  private List<Cluster> angabenZurAnatomischenStelle;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Strukturierte Angaben zum Ort
   * Description: Strukturierte Angaben über den physischen Ort der Exposition.
   */
  @Path("/data[at0001]/items[at0005]/items[at0026]")
  private List<Cluster> strukturierteAngabenZumOrt;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Persönliche Schutzausrüstung
   * Description: Beschreibung der von der exponierten Person zum Zeitpunkt der Exposition verwendeter PSA.
   */
  @Path("/data[at0001]/items[at0005]/items[at0022]")
  private List<Cluster> persoenlicheSchutzausruestung;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Angaben zur Person als Quelle
   * Description: Strukturierte Angaben über die Person, von der die Exposition ausgeht.
   */
  @Path("/data[at0001]/items[at0005]/items[at0015]")
  private List<Cluster> angabenZurPersonAlsQuelle;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Spezifische Details zur Exposition/Einflussfaktoren
   * Description: Faktoren, die zu dem infektiösen Kontakt beigetragen haben können.
   */
  @Path("/data[at0001]/items[at0005]/items[at0016]")
  private List<Cluster> einflussfaktoren;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Angaben zur exponierten Person
   * Description: Strukturierte Angaben über die Person, die exponiert oder infiziert wurde.
   */
  @Path("/data[at0001]/items[at0017]")
  private List<Cluster> angabenZurExponiertenPerson;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen.
   */
  @Path("/protocol[at0031]/items[at0032]")
  private List<Cluster> erweiterung;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: SARS-CoV-2 Exposition/SARS-CoV-2 Exposition/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setInfektionserregerValue(String infektionserregerValue) {
     this.infektionserregerValue = infektionserregerValue;
  }

  public String getInfektionserregerValue() {
     return this.infektionserregerValue ;
  }

  public void setInfektionserregerNullFlavourDefiningCode(
      NullFlavour infektionserregerNullFlavourDefiningCode) {
     this.infektionserregerNullFlavourDefiningCode = infektionserregerNullFlavourDefiningCode;
  }

  public NullFlavour getInfektionserregerNullFlavourDefiningCode() {
     return this.infektionserregerNullFlavourDefiningCode ;
  }

  public void setExpositionVorhanden(DvCodedText expositionVorhanden) {
     this.expositionVorhanden = expositionVorhanden;
  }

  public DvCodedText getExpositionVorhanden() {
     return this.expositionVorhanden ;
  }

  public void setExpositionVorhandenNullFlavourDefiningCode(
      NullFlavour expositionVorhandenNullFlavourDefiningCode) {
     this.expositionVorhandenNullFlavourDefiningCode = expositionVorhandenNullFlavourDefiningCode;
  }

  public NullFlavour getExpositionVorhandenNullFlavourDefiningCode() {
     return this.expositionVorhandenNullFlavourDefiningCode ;
  }

  public void setBeschreibungDerExpositionValue(String beschreibungDerExpositionValue) {
     this.beschreibungDerExpositionValue = beschreibungDerExpositionValue;
  }

  public String getBeschreibungDerExpositionValue() {
     return this.beschreibungDerExpositionValue ;
  }

  public void setBeschreibungDerExpositionNullFlavourDefiningCode(
      NullFlavour beschreibungDerExpositionNullFlavourDefiningCode) {
     this.beschreibungDerExpositionNullFlavourDefiningCode = beschreibungDerExpositionNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungDerExpositionNullFlavourDefiningCode() {
     return this.beschreibungDerExpositionNullFlavourDefiningCode ;
  }

  public void setDatumUhrzeitDerExpositionValue(TemporalAccessor datumUhrzeitDerExpositionValue) {
     this.datumUhrzeitDerExpositionValue = datumUhrzeitDerExpositionValue;
  }

  public TemporalAccessor getDatumUhrzeitDerExpositionValue() {
     return this.datumUhrzeitDerExpositionValue ;
  }

  public void setDatumUhrzeitDerExpositionNullFlavourDefiningCode(
      NullFlavour datumUhrzeitDerExpositionNullFlavourDefiningCode) {
     this.datumUhrzeitDerExpositionNullFlavourDefiningCode = datumUhrzeitDerExpositionNullFlavourDefiningCode;
  }

  public NullFlavour getDatumUhrzeitDerExpositionNullFlavourDefiningCode() {
     return this.datumUhrzeitDerExpositionNullFlavourDefiningCode ;
  }

  public void setAngabenZurAnatomischenStelle(List<Cluster> angabenZurAnatomischenStelle) {
     this.angabenZurAnatomischenStelle = angabenZurAnatomischenStelle;
  }

  public List<Cluster> getAngabenZurAnatomischenStelle() {
     return this.angabenZurAnatomischenStelle ;
  }

  public void setStrukturierteAngabenZumOrt(List<Cluster> strukturierteAngabenZumOrt) {
     this.strukturierteAngabenZumOrt = strukturierteAngabenZumOrt;
  }

  public List<Cluster> getStrukturierteAngabenZumOrt() {
     return this.strukturierteAngabenZumOrt ;
  }

  public void setPersoenlicheSchutzausruestung(List<Cluster> persoenlicheSchutzausruestung) {
     this.persoenlicheSchutzausruestung = persoenlicheSchutzausruestung;
  }

  public List<Cluster> getPersoenlicheSchutzausruestung() {
     return this.persoenlicheSchutzausruestung ;
  }

  public void setAngabenZurPersonAlsQuelle(List<Cluster> angabenZurPersonAlsQuelle) {
     this.angabenZurPersonAlsQuelle = angabenZurPersonAlsQuelle;
  }

  public List<Cluster> getAngabenZurPersonAlsQuelle() {
     return this.angabenZurPersonAlsQuelle ;
  }

  public void setEinflussfaktoren(List<Cluster> einflussfaktoren) {
     this.einflussfaktoren = einflussfaktoren;
  }

  public List<Cluster> getEinflussfaktoren() {
     return this.einflussfaktoren ;
  }

  public void setAngabenZurExponiertenPerson(List<Cluster> angabenZurExponiertenPerson) {
     this.angabenZurExponiertenPerson = angabenZurExponiertenPerson;
  }

  public List<Cluster> getAngabenZurExponiertenPerson() {
     return this.angabenZurExponiertenPerson ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
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
