package org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition;

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
@Archetype("openEHR-EHR-ADMIN_ENTRY.travel_event.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-06T14:30:59.956591+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class ReisehistorieAdminEntry implements EntryEntity {
  /**
   * Path: Reisehistorie/Reisehistorie/Reise angetreten?
   * Description: Beschreibung der gesamten Reise, insbesondere über mögliche Gesundheitsrisiken.
   */
  @Path("/data[at0001]/items[at0022 and name/value='Reise angetreten?']/value|defining_code")
  private ReiseAngetretenDefiningCode reiseAngetretenDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Item tree/Reise angetreten?/null_flavour
   */
  @Path("/data[at0001]/items[at0022 and name/value='Reise angetreten?']/null_flavour|defining_code")
  private NullFlavour reiseAngetretenNullFlavourDefiningCode;

  /**
   * Path: Reisehistorie/Reisehistorie/Bestimmtes Reiseziel
   * Description: Angaben zu einem einzelnen Ort, der auf einer Reise besucht wurde.
   */
  @Path("/data[at0001]/items[at0010]")
  private List<ReisehistorieBestimmtesReisezielCluster> bestimmtesReiseziel;

  /**
   * Path: Reisehistorie/Reisehistorie/Zusätzliche Reisedetails
   * Description: Zusätzliche strukturierte Informationen zur gesamten Reise.
   */
  @Path("/data[at0001]/items[at0025]")
  private List<Cluster> zusaetzlicheReisedetails;

  /**
   * Path: Reisehistorie/Reisehistorie/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Reisehistorie/Reisehistorie/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Reisehistorie/Reisehistorie/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setReiseAngetretenDefiningCode(
      ReiseAngetretenDefiningCode reiseAngetretenDefiningCode) {
     this.reiseAngetretenDefiningCode = reiseAngetretenDefiningCode;
  }

  public ReiseAngetretenDefiningCode getReiseAngetretenDefiningCode() {
     return this.reiseAngetretenDefiningCode ;
  }

  public void setReiseAngetretenNullFlavourDefiningCode(
      NullFlavour reiseAngetretenNullFlavourDefiningCode) {
     this.reiseAngetretenNullFlavourDefiningCode = reiseAngetretenNullFlavourDefiningCode;
  }

  public NullFlavour getReiseAngetretenNullFlavourDefiningCode() {
     return this.reiseAngetretenNullFlavourDefiningCode ;
  }

  public void setBestimmtesReiseziel(
      List<ReisehistorieBestimmtesReisezielCluster> bestimmtesReiseziel) {
     this.bestimmtesReiseziel = bestimmtesReiseziel;
  }

  public List<ReisehistorieBestimmtesReisezielCluster> getBestimmtesReiseziel() {
     return this.bestimmtesReiseziel ;
  }

  public void setZusaetzlicheReisedetails(List<Cluster> zusaetzlicheReisedetails) {
     this.zusaetzlicheReisedetails = zusaetzlicheReisedetails;
  }

  public List<Cluster> getZusaetzlicheReisedetails() {
     return this.zusaetzlicheReisedetails ;
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
