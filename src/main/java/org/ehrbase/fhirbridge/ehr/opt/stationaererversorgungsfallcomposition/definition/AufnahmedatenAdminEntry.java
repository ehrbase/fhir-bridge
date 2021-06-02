package org.ehrbase.fhirbridge.ehr.opt.stationaererversorgungsfallcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
@Archetype("openEHR-EHR-ADMIN_ENTRY.admission.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:38.244209300+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class AufnahmedatenAdminEntry implements EntryEntity {
  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Aufnahmegrund
   * Description: Der Umstand, unter dem der Patient aufgenommen wird.
   */
  @Path("/data[at0001]/items[at0013]/value|defining_code")
  private AufnahmegrundDefiningCode aufnahmegrundDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Aufnahmegrund/null_flavour
   */
  @Path("/data[at0001]/items[at0013]/null_flavour|defining_code")
  private NullFlavour aufnahmegrundNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Aufnahmeanlass
   * Description: Nähere Beschreibung der Art der Aufnahme, z.B. Unfall oder Notfall.
   */
  @Path("/data[at0001]/items[at0049 and name/value='Aufnahmeanlass']/value|defining_code")
  private AufnahmeanlassDefiningCode aufnahmeanlassDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Aufnahmeanlass/null_flavour
   */
  @Path("/data[at0001]/items[at0049 and name/value='Aufnahmeanlass']/null_flavour|defining_code")
  private NullFlavour aufnahmeanlassNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Identifikationsnummer des Patienten vor der Aufnahme
   * Description: Identifikationsnummer des Patienten vor der Aufnahme
   */
  @Path("/data[at0001]/items[at0023]/value|value")
  private String identifikationsnummerDesPatientenVorDerAufnahmeValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Identifikationsnummer des Patienten vor der Aufnahme/null_flavour
   */
  @Path("/data[at0001]/items[at0023]/null_flavour|defining_code")
  private NullFlavour identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Datum/Uhrzeit der Aufnahme
   * Description: Datum/Zeit, an dem der Patient aufgenommen wurde.
   */
  @Path("/data[at0001]/items[at0071]/value|value")
  private TemporalAccessor datumUhrzeitDerAufnahmeValue;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Tree/Datum/Uhrzeit der Aufnahme/null_flavour
   */
  @Path("/data[at0001]/items[at0071]/null_flavour|defining_code")
  private NullFlavour datumUhrzeitDerAufnahmeNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Zugewiesener Patientenstandort
   * Description: Zugewiesener Patientenstandort
   */
  @Path("/data[at0001]/items[at0131]")
  private List<Cluster> zugewiesenerPatientenstandort;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorheriger Patientenstandort (vor Aufnahme)
   * Description: Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Vorheriger Patientenstandort (vor Aufnahme)']")
  private VorherigerPatientenstandortVorAufnahmeCluster vorherigerPatientenstandortVorAufnahme;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/Vorherige verantwortliche Organisationseinheit (vor Aufnahme)
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Vorherige verantwortliche Organisationseinheit (vor Aufnahme)']")
  private VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster vorherigeVerantwortlicheOrganisationseinheitVorAufnahme;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Stationärer Versorgungsfall/Aufnahmedaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAufnahmegrundDefiningCode(AufnahmegrundDefiningCode aufnahmegrundDefiningCode) {
     this.aufnahmegrundDefiningCode = aufnahmegrundDefiningCode;
  }

  public AufnahmegrundDefiningCode getAufnahmegrundDefiningCode() {
     return this.aufnahmegrundDefiningCode ;
  }

  public void setAufnahmegrundNullFlavourDefiningCode(
      NullFlavour aufnahmegrundNullFlavourDefiningCode) {
     this.aufnahmegrundNullFlavourDefiningCode = aufnahmegrundNullFlavourDefiningCode;
  }

  public NullFlavour getAufnahmegrundNullFlavourDefiningCode() {
     return this.aufnahmegrundNullFlavourDefiningCode ;
  }

  public void setAufnahmeanlassDefiningCode(AufnahmeanlassDefiningCode aufnahmeanlassDefiningCode) {
     this.aufnahmeanlassDefiningCode = aufnahmeanlassDefiningCode;
  }

  public AufnahmeanlassDefiningCode getAufnahmeanlassDefiningCode() {
     return this.aufnahmeanlassDefiningCode ;
  }

  public void setAufnahmeanlassNullFlavourDefiningCode(
      NullFlavour aufnahmeanlassNullFlavourDefiningCode) {
     this.aufnahmeanlassNullFlavourDefiningCode = aufnahmeanlassNullFlavourDefiningCode;
  }

  public NullFlavour getAufnahmeanlassNullFlavourDefiningCode() {
     return this.aufnahmeanlassNullFlavourDefiningCode ;
  }

  public void setIdentifikationsnummerDesPatientenVorDerAufnahmeValue(
      String identifikationsnummerDesPatientenVorDerAufnahmeValue) {
     this.identifikationsnummerDesPatientenVorDerAufnahmeValue = identifikationsnummerDesPatientenVorDerAufnahmeValue;
  }

  public String getIdentifikationsnummerDesPatientenVorDerAufnahmeValue() {
     return this.identifikationsnummerDesPatientenVorDerAufnahmeValue ;
  }

  public void setIdentifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode(
      NullFlavour identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode) {
     this.identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode = identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getIdentifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode() {
     return this.identifikationsnummerDesPatientenVorDerAufnahmeNullFlavourDefiningCode ;
  }

  public void setDatumUhrzeitDerAufnahmeValue(TemporalAccessor datumUhrzeitDerAufnahmeValue) {
     this.datumUhrzeitDerAufnahmeValue = datumUhrzeitDerAufnahmeValue;
  }

  public TemporalAccessor getDatumUhrzeitDerAufnahmeValue() {
     return this.datumUhrzeitDerAufnahmeValue ;
  }

  public void setDatumUhrzeitDerAufnahmeNullFlavourDefiningCode(
      NullFlavour datumUhrzeitDerAufnahmeNullFlavourDefiningCode) {
     this.datumUhrzeitDerAufnahmeNullFlavourDefiningCode = datumUhrzeitDerAufnahmeNullFlavourDefiningCode;
  }

  public NullFlavour getDatumUhrzeitDerAufnahmeNullFlavourDefiningCode() {
     return this.datumUhrzeitDerAufnahmeNullFlavourDefiningCode ;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
     this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
     return this.zugewiesenerPatientenstandort ;
  }

  public void setVorherigerPatientenstandortVorAufnahme(
      VorherigerPatientenstandortVorAufnahmeCluster vorherigerPatientenstandortVorAufnahme) {
     this.vorherigerPatientenstandortVorAufnahme = vorherigerPatientenstandortVorAufnahme;
  }

  public VorherigerPatientenstandortVorAufnahmeCluster getVorherigerPatientenstandortVorAufnahme() {
     return this.vorherigerPatientenstandortVorAufnahme ;
  }

  public void setVorherigeVerantwortlicheOrganisationseinheitVorAufnahme(
      VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster vorherigeVerantwortlicheOrganisationseinheitVorAufnahme) {
     this.vorherigeVerantwortlicheOrganisationseinheitVorAufnahme = vorherigeVerantwortlicheOrganisationseinheitVorAufnahme;
  }

  public VorherigeVerantwortlicheOrganisationseinheitVorAufnahmeCluster getVorherigeVerantwortlicheOrganisationseinheitVorAufnahme(
      ) {
     return this.vorherigeVerantwortlicheOrganisationseinheitVorAufnahme ;
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
