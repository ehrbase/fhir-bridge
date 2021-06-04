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
@Archetype("openEHR-EHR-ADMIN_ENTRY.discharge_summary.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-30T13:55:38.292218700+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class EntlassungsdatenAdminEntry implements EntryEntity {
  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Art der Entlassung
   * Description: Grund der Entlassung
   */
  @Path("/data[at0001]/items[at0040]/value|defining_code")
  private ArtDerEntlassungDefiningCode artDerEntlassungDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Tree/Art der Entlassung/null_flavour
   */
  @Path("/data[at0001]/items[at0040]/null_flavour|defining_code")
  private NullFlavour artDerEntlassungNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Klinischer Zustand des Patienten
   * Description: Klinischer Zustand des Patienten.
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Tree/Klinischer Zustand des Patienten/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour klinischerZustandDesPatientenNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Datum/Uhrzeit der Entlassung
   * Description: Datum/Uhrzeit, an dem der Patient entlassen wurde.
   */
  @Path("/data[at0001]/items[at0011 and name/value='Datum/Uhrzeit der Entlassung']/value|value")
  private TemporalAccessor datumUhrzeitDerEntlassungValue;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Tree/Datum/Uhrzeit der Entlassung/null_flavour
   */
  @Path("/data[at0001]/items[at0011 and name/value='Datum/Uhrzeit der Entlassung']/null_flavour|defining_code")
  private NullFlavour datumUhrzeitDerEntlassungNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Zusätzliche Informationen
   * Description: Kommentare
   */
  @Path("/data[at0001]/items[at0050]/value|value")
  private String zusaetzlicheInformationenValue;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Tree/Zusätzliche Informationen/null_flavour
   */
  @Path("/data[at0001]/items[at0050]/null_flavour|defining_code")
  private NullFlavour zusaetzlicheInformationenNullFlavourDefiningCode;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Letzter Patientenstandort
   * Description: *
   */
  @Path("/data[at0001]/items[at0066]")
  private List<Cluster> letzterPatientenstandort;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Zugewiesener Standort (bei Entlassung)
   * Description: Standort umfasst sowohl beiläufige Orte (ein Ort, der für die medizinische Versorgung ohne vorherige Benennung oder Genehmigung genutzt wird) als auch spezielle, offiziell benannte Orte. Die Standorte können privat, öffentlich, mobil oder stationär sein.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.location.v1 and name/value='Zugewiesener Standort (bei Entlassung)']")
  private List<ZugewiesenerStandortBeiEntlassungCluster> zugewiesenerStandortBeiEntlassung;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/Zugewiesene verantwortliche Organisationseinheit (bei Entlassung)
   * Description: Eine fachliche Einheit, Organisation, Abteilung, Zusammenschluss, Gruppierung mit einem gemeinsamen Ziel.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.organization.v0 and name/value='Zugewiesene verantwortliche Organisationseinheit (bei Entlassung)']")
  private List<ZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassungCluster> zugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Stationärer Versorgungsfall/Entlassungsdaten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setArtDerEntlassungDefiningCode(
      ArtDerEntlassungDefiningCode artDerEntlassungDefiningCode) {
     this.artDerEntlassungDefiningCode = artDerEntlassungDefiningCode;
  }

  public ArtDerEntlassungDefiningCode getArtDerEntlassungDefiningCode() {
     return this.artDerEntlassungDefiningCode ;
  }

  public void setArtDerEntlassungNullFlavourDefiningCode(
      NullFlavour artDerEntlassungNullFlavourDefiningCode) {
     this.artDerEntlassungNullFlavourDefiningCode = artDerEntlassungNullFlavourDefiningCode;
  }

  public NullFlavour getArtDerEntlassungNullFlavourDefiningCode() {
     return this.artDerEntlassungNullFlavourDefiningCode ;
  }

  public void setKlinischerZustandDesPatientenDefiningCode(
      KlinischerZustandDesPatientenDefiningCode klinischerZustandDesPatientenDefiningCode) {
     this.klinischerZustandDesPatientenDefiningCode = klinischerZustandDesPatientenDefiningCode;
  }

  public KlinischerZustandDesPatientenDefiningCode getKlinischerZustandDesPatientenDefiningCode() {
     return this.klinischerZustandDesPatientenDefiningCode ;
  }

  public void setKlinischerZustandDesPatientenNullFlavourDefiningCode(
      NullFlavour klinischerZustandDesPatientenNullFlavourDefiningCode) {
     this.klinischerZustandDesPatientenNullFlavourDefiningCode = klinischerZustandDesPatientenNullFlavourDefiningCode;
  }

  public NullFlavour getKlinischerZustandDesPatientenNullFlavourDefiningCode() {
     return this.klinischerZustandDesPatientenNullFlavourDefiningCode ;
  }

  public void setDatumUhrzeitDerEntlassungValue(TemporalAccessor datumUhrzeitDerEntlassungValue) {
     this.datumUhrzeitDerEntlassungValue = datumUhrzeitDerEntlassungValue;
  }

  public TemporalAccessor getDatumUhrzeitDerEntlassungValue() {
     return this.datumUhrzeitDerEntlassungValue ;
  }

  public void setDatumUhrzeitDerEntlassungNullFlavourDefiningCode(
      NullFlavour datumUhrzeitDerEntlassungNullFlavourDefiningCode) {
     this.datumUhrzeitDerEntlassungNullFlavourDefiningCode = datumUhrzeitDerEntlassungNullFlavourDefiningCode;
  }

  public NullFlavour getDatumUhrzeitDerEntlassungNullFlavourDefiningCode() {
     return this.datumUhrzeitDerEntlassungNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheInformationenValue(String zusaetzlicheInformationenValue) {
     this.zusaetzlicheInformationenValue = zusaetzlicheInformationenValue;
  }

  public String getZusaetzlicheInformationenValue() {
     return this.zusaetzlicheInformationenValue ;
  }

  public void setZusaetzlicheInformationenNullFlavourDefiningCode(
      NullFlavour zusaetzlicheInformationenNullFlavourDefiningCode) {
     this.zusaetzlicheInformationenNullFlavourDefiningCode = zusaetzlicheInformationenNullFlavourDefiningCode;
  }

  public NullFlavour getZusaetzlicheInformationenNullFlavourDefiningCode() {
     return this.zusaetzlicheInformationenNullFlavourDefiningCode ;
  }

  public void setLetzterPatientenstandort(List<Cluster> letzterPatientenstandort) {
     this.letzterPatientenstandort = letzterPatientenstandort;
  }

  public List<Cluster> getLetzterPatientenstandort() {
     return this.letzterPatientenstandort ;
  }

  public void setZugewiesenerStandortBeiEntlassung(
      List<ZugewiesenerStandortBeiEntlassungCluster> zugewiesenerStandortBeiEntlassung) {
     this.zugewiesenerStandortBeiEntlassung = zugewiesenerStandortBeiEntlassung;
  }

  public List<ZugewiesenerStandortBeiEntlassungCluster> getZugewiesenerStandortBeiEntlassung() {
     return this.zugewiesenerStandortBeiEntlassung ;
  }

  public void setZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung(
      List<ZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassungCluster> zugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung) {
     this.zugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung = zugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung;
  }

  public List<ZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassungCluster> getZugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung(
      ) {
     return this.zugewieseneVerantwortlicheOrganisationseinheitBeiEntlassung ;
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
