package org.ehrbase.fhirbridge.ehr.opt.raucherstatuscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
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
@Archetype("openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-14T11:58:03.158209+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class RaucherstatusEvaluation implements EntryEntity {
  /**
   * Path: Raucherstatus/Raucherstatus/Rauchverhalten
   * Description: Zusammenfassung über das allgemeine Tabakrauchverhalten der Person und die Vorgeschichte.
   * Comment: Verwenden Sie dieses Datenelement um das Tabakrauchverhalten einer Person zu beschreiben, fwenn die erhobenen strukturierten Daten dies nicht angemessen widerspiegeln. Nutzen Sie dieses Datenelement ebenfalls, um unstrukturierte Tabakrauchinformationen aus bestehenden oder älteren klinischen Systemen in ein Archetypenformat zu integrieren.
   */
  @Path("/data[at0001]/items[at0043 and name/value='Rauchverhalten']/value")
  private DvCodedText rauchverhalten;

  /**
   * Path: Raucherstatus/Raucherstatus/Tree/Rauchverhalten/null_flavour
   */
  @Path("/data[at0001]/items[at0043 and name/value='Rauchverhalten']/null_flavour|defining_code")
  private NullFlavour rauchverhaltenNullFlavourDefiningCode;

  /**
   * Path: Raucherstatus/Raucherstatus/Allgemeine Details
   * Description: Zusätzliche strukturierte Details über das gesamte Tabakrauchverhalten.
   */
  @Path("/data[at0001]/items[at0086]")
  private List<Cluster> allgemeineDetails;

  /**
   * Path: Raucherstatus/Raucherstatus/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0021]/items[at0073]")
  private List<Cluster> erweiterung;

  /**
   * Path: Raucherstatus/Raucherstatus/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Raucherstatus/Raucherstatus/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Raucherstatus/Raucherstatus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRauchverhalten(DvCodedText rauchverhalten) {
     this.rauchverhalten = rauchverhalten;
  }

  public DvCodedText getRauchverhalten() {
     return this.rauchverhalten ;
  }

  public void setRauchverhaltenNullFlavourDefiningCode(
      NullFlavour rauchverhaltenNullFlavourDefiningCode) {
     this.rauchverhaltenNullFlavourDefiningCode = rauchverhaltenNullFlavourDefiningCode;
  }

  public NullFlavour getRauchverhaltenNullFlavourDefiningCode() {
     return this.rauchverhaltenNullFlavourDefiningCode ;
  }

  public void setAllgemeineDetails(List<Cluster> allgemeineDetails) {
     this.allgemeineDetails = allgemeineDetails;
  }

  public List<Cluster> getAllgemeineDetails() {
     return this.allgemeineDetails ;
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
