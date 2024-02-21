package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.gender.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.113507309+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class GeschlechtEvaluation implements EntryEntity {
  /**
   * Path: Person/Geschlecht/Administratives Geschlecht
   * Description: Das Geschlecht einer Person, das für administrative Zwecke verwendet wird.
   * Comment: Dieses Element beschreibt das, was die meisten Systeme heutzutage als "Geschlecht" (vgl. engl.: "Sex" oder "Gender") bezeichnen. Zum Beispiel "Männlich", "Weiblich", "Divers". Dieser Archetyp entspricht dem HL7 FHIR Element "Person.gender"'. Die Codierung mit einer Terminologie wird empfohlen, wenn möglich.
   */
  @Path("/data[at0002]/items[at0022]/value")
  private DvCodedText administrativesGeschlecht;

  /**
   * Path: Person/Geschlecht/*Tree(en)/Administratives Geschlecht/null_flavour
   */
  @Path("/data[at0002]/items[at0022]/null_flavour|defining_code")
  private NullFlavour administrativesGeschlechtNullFlavourDefiningCode;

  /**
   * Path: Person/Geschlecht/Zusätzliche Details
   * Description: Zusätzliche strukturierte Angaben zum Geschlecht der Person.
   * Comment: Zusätzliche strukturierte Angaben zum Geschlecht der Person.
   */
  @Path("/data[at0002]/items[at0023]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: Person/Geschlecht/Anderes Geschlecht amtlich
   * Description: Zusätzliche Beschreibung über das Geschlecht, die nicht in anderen Datenelementen erfasst ist.
   */
  @Path("/data[at0002]/items[at0014 and name/value='Anderes Geschlecht amtlich']/value|defining_code")
  private AnderesGeschlechtAmtlichDefiningCode anderesGeschlechtAmtlichDefiningCode;

  /**
   * Path: Person/Geschlecht/*Tree(en)/Anderes Geschlecht amtlich/null_flavour
   */
  @Path("/data[at0002]/items[at0014 and name/value='Anderes Geschlecht amtlich']/null_flavour|defining_code")
  private NullFlavour anderesGeschlechtAmtlichNullFlavourDefiningCode;

  /**
   * Path: Person/Geschlecht/Erweiterung
   * Description: Zusätzliche Informationen, die erforderlich sind, um lokale Inhalte zu erfassen oder mit anderen Referenzmodellen/Formalismen abzugleichen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Äquivalente.
   */
  @Path("/protocol[at0003]/items[at0005]")
  private List<Cluster> erweiterung;

  /**
   * Path: Person/Geschlecht/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Person/Geschlecht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Person/Geschlecht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAdministrativesGeschlecht(DvCodedText administrativesGeschlecht) {
     this.administrativesGeschlecht = administrativesGeschlecht;
  }

  public DvCodedText getAdministrativesGeschlecht() {
     return this.administrativesGeschlecht ;
  }

  public void setAdministrativesGeschlechtNullFlavourDefiningCode(
      NullFlavour administrativesGeschlechtNullFlavourDefiningCode) {
     this.administrativesGeschlechtNullFlavourDefiningCode = administrativesGeschlechtNullFlavourDefiningCode;
  }

  public NullFlavour getAdministrativesGeschlechtNullFlavourDefiningCode() {
     return this.administrativesGeschlechtNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheDetails(List<Cluster> zusaetzlicheDetails) {
     this.zusaetzlicheDetails = zusaetzlicheDetails;
  }

  public List<Cluster> getZusaetzlicheDetails() {
     return this.zusaetzlicheDetails ;
  }

  public void setAnderesGeschlechtAmtlichDefiningCode(
      AnderesGeschlechtAmtlichDefiningCode anderesGeschlechtAmtlichDefiningCode) {
     this.anderesGeschlechtAmtlichDefiningCode = anderesGeschlechtAmtlichDefiningCode;
  }

  public AnderesGeschlechtAmtlichDefiningCode getAnderesGeschlechtAmtlichDefiningCode() {
     return this.anderesGeschlechtAmtlichDefiningCode ;
  }

  public void setAnderesGeschlechtAmtlichNullFlavourDefiningCode(
      NullFlavour anderesGeschlechtAmtlichNullFlavourDefiningCode) {
     this.anderesGeschlechtAmtlichNullFlavourDefiningCode = anderesGeschlechtAmtlichNullFlavourDefiningCode;
  }

  public NullFlavour getAnderesGeschlechtAmtlichNullFlavourDefiningCode() {
     return this.anderesGeschlechtAmtlichNullFlavourDefiningCode ;
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
