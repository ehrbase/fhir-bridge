package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.tobacco_smoking_summary.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.855777+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ZusammenfassungRauchverhaltenEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/Raucher?
   * Description: Aussage zum aktuellen Rauchverhalten bei allen Tabakarten.
   */
  @Path("/data[at0001]/items[at0089 and name/value='Raucher?']/value|defining_code")
  private RaucherDefiningCode raucherDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/Tree/Raucher?/null_flavour
   */
  @Path("/data[at0001]/items[at0089 and name/value='Raucher?']/null_flavour|defining_code")
  private NullFlavour raucherNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/Allgemeine Details
   * Description: Zusätzliche strukturierte Details über das gesamte Tabakrauchverhalten.
   */
  @Path("/data[at0001]/items[at0086]")
  private List<Cluster> allgemeineDetails;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0021]/items[at0073]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung Rauchverhalten/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setRaucherDefiningCode(RaucherDefiningCode raucherDefiningCode) {
     this.raucherDefiningCode = raucherDefiningCode;
  }

  public RaucherDefiningCode getRaucherDefiningCode() {
     return this.raucherDefiningCode ;
  }

  public void setRaucherNullFlavourDefiningCode(NullFlavour raucherNullFlavourDefiningCode) {
     this.raucherNullFlavourDefiningCode = raucherNullFlavourDefiningCode;
  }

  public NullFlavour getRaucherNullFlavourDefiningCode() {
     return this.raucherNullFlavourDefiningCode ;
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
