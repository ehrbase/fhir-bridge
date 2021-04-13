package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.gender.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-15T15:57:50.628643+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class GeschlechtEvaluation implements EntryEntity {
  /**
   * Path: GECCO_Personendaten/Geschlecht/Administratives Geschlecht
   * Description: Das Geschlecht einer Person, das für administrative Zwecke verwendet wird.
   * Comment: Dieses Element beschreibt das, was die meisten Systeme heutzutage als "Geschlecht" (vgl. engl.: "Sex" oder "Gender") bezeichnen. Zum Beispiel "Männlich", "Weiblich", "Divers". Dieser Archetyp entspricht dem HL7 FHIR Element "Person.gender"'. Die Kodierung mit einer Terminologie wird empfohlen, wenn möglich.
   */
  @Path("/data[at0002]/items[at0022]/value|defining_code")
  private AdministrativesGeschlechtDefiningCode administrativesGeschlechtDefiningCode;

  /**
   * Path: GECCO_Personendaten/Geschlecht/*Tree(en)/Administratives Geschlecht/null_flavour
   */
  @Path("/data[at0002]/items[at0022]/null_flavour|defining_code")
  private NullFlavour administrativesGeschlechtNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Geschlecht/Geschlecht bei der Geburt
   * Description: Das Geschlecht einer Person, bestimmt durch anatomische Merkmale, welches bei der Geburt festgestellt und eingetragen wurden.
   * Comment: Zum Beispiel: "Männlich", "Weiblich", "Divers". Die Kodierung mit einer Terminologie wird empfohlen, wenn möglich. Falls notwendig, benutzen Sie das Element "Kommentar" oder den SLOT "Details" um genauere Angaben zum Geschlecht der Person zu machen.
   */
  @Path("/data[at0002]/items[at0019 and name/value='Geschlecht bei der Geburt']/value|defining_code")
  private GeschlechtBeiDerGeburtDefiningCode geschlechtBeiDerGeburtDefiningCode;

  /**
   * Path: GECCO_Personendaten/Geschlecht/*Tree(en)/Geschlecht bei der Geburt/null_flavour
   */
  @Path("/data[at0002]/items[at0019 and name/value='Geschlecht bei der Geburt']/null_flavour|defining_code")
  private NullFlavour geschlechtBeiDerGeburtNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Geschlecht/Zusätzliche Details
   * Description: Zusätzliche strukturierte Angaben zum Geschlecht der Person.
   * Comment: Zusätzliche strukturierte Angaben zum Geschlecht der Person.
   */
  @Path("/data[at0002]/items[at0023]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: GECCO_Personendaten/Geschlecht/Erweiterung
   * Description: Zusätzliche Informationen, die erforderlich sind, um lokale Inhalte zu erfassen oder mit anderen Referenzmodellen/Formalismen zu harmonisieren.
   * Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten zur Anpassung an FHIR- oder CIMI-Äquivalente.
   */
  @Path("/protocol[at0003]/items[at0005]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Personendaten/Geschlecht/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Personendaten/Geschlecht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Personendaten/Geschlecht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAdministrativesGeschlechtDefiningCode(
      AdministrativesGeschlechtDefiningCode administrativesGeschlechtDefiningCode) {
     this.administrativesGeschlechtDefiningCode = administrativesGeschlechtDefiningCode;
  }

  public AdministrativesGeschlechtDefiningCode getAdministrativesGeschlechtDefiningCode() {
     return this.administrativesGeschlechtDefiningCode ;
  }

  public void setAdministrativesGeschlechtNullFlavourDefiningCode(
      NullFlavour administrativesGeschlechtNullFlavourDefiningCode) {
     this.administrativesGeschlechtNullFlavourDefiningCode = administrativesGeschlechtNullFlavourDefiningCode;
  }

  public NullFlavour getAdministrativesGeschlechtNullFlavourDefiningCode() {
     return this.administrativesGeschlechtNullFlavourDefiningCode ;
  }

  public void setGeschlechtBeiDerGeburtDefiningCode(
      GeschlechtBeiDerGeburtDefiningCode geschlechtBeiDerGeburtDefiningCode) {
     this.geschlechtBeiDerGeburtDefiningCode = geschlechtBeiDerGeburtDefiningCode;
  }

  public GeschlechtBeiDerGeburtDefiningCode getGeschlechtBeiDerGeburtDefiningCode() {
     return this.geschlechtBeiDerGeburtDefiningCode ;
  }

  public void setGeschlechtBeiDerGeburtNullFlavourDefiningCode(
      NullFlavour geschlechtBeiDerGeburtNullFlavourDefiningCode) {
     this.geschlechtBeiDerGeburtNullFlavourDefiningCode = geschlechtBeiDerGeburtNullFlavourDefiningCode;
  }

  public NullFlavour getGeschlechtBeiDerGeburtNullFlavourDefiningCode() {
     return this.geschlechtBeiDerGeburtNullFlavourDefiningCode ;
  }

  public void setZusaetzlicheDetails(List<Cluster> zusaetzlicheDetails) {
     this.zusaetzlicheDetails = zusaetzlicheDetails;
  }

  public List<Cluster> getZusaetzlicheDetails() {
     return this.zusaetzlicheDetails ;
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
