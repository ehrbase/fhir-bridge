package org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
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
    date = "2021-09-13T16:34:18.694775+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class EntlassungsartAdminEntry implements EntryEntity {
  /**
   * Path: Entlassungsdaten/Entlassungsart/Art der Entlassung
   * Description: Grund der Entlassung
   */
  @Path("/data[at0001]/items[at0040]/value|defining_code")
  private ArtDerEntlassungDefiningCode artDerEntlassungDefiningCode;

  /**
   * Path: Entlassungsdaten/Entlassungsart/Tree/Art der Entlassung/null_flavour
   */
  @Path("/data[at0001]/items[at0040]/null_flavour|defining_code")
  private NullFlavour artDerEntlassungNullFlavourDefiningCode;

  /**
   * Path: Entlassungsdaten/Entlassungsart/Letzter Patientenstandort
   * Description: *
   */
  @Path("/data[at0001]/items[at0066]")
  private List<Cluster> letzterPatientenstandort;

  /**
   * Path: Entlassungsdaten/Entlassungsart/Zugewiesener Patientenstandort
   * Description: Für die lokale Verwendung enthält dieses Feld den Typ der Organisationseinheit oder der klinischen Einheit.
   */
  @Path("/data[at0001]/items[at0067]")
  private List<Cluster> zugewiesenerPatientenstandort;

  /**
   * Path: Entlassungsdaten/Entlassungsart/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Entlassungsdaten/Entlassungsart/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Entlassungsdaten/Entlassungsart/feeder_audit
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

  public void setLetzterPatientenstandort(List<Cluster> letzterPatientenstandort) {
     this.letzterPatientenstandort = letzterPatientenstandort;
  }

  public List<Cluster> getLetzterPatientenstandort() {
     return this.letzterPatientenstandort ;
  }

  public void setZugewiesenerPatientenstandort(List<Cluster> zugewiesenerPatientenstandort) {
     this.zugewiesenerPatientenstandort = zugewiesenerPatientenstandort;
  }

  public List<Cluster> getZugewiesenerPatientenstandort() {
     return this.zugewiesenerPatientenstandort ;
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
