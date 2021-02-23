package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.medication_summary.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:41.160390+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class KortisionEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Name des Medikaments
   * Description: Name des Medikaments oder der Medikamentenklasse.
   */
  @Path("/data[at0001]/items[at0002]/value|value")
  private String nameDesMedikamentsValue;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Tree/Name des Medikaments/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour nameDesMedikamentsNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Status
   * Description: Status über den vergangenen und aktuellen Gebrauch eines Medikaments oder einer Medikamentenklasse.
   */
  @Path("/data[at0001]/items[at0023]/value|defining_code")
  private StatusDefiningCode2 statusDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Tree/Status/null_flavour
   */
  @Path("/data[at0001]/items[at0023]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Datum der letzten Aktualisierung
   * Description: Das Datum der letzten Aktualisierung der Zusammenfassung
   */
  @Path("/protocol[at0005]/items[at0006]/value|value")
  private TemporalAccessor datumDerLetztenAktualisierungValue;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Tree/Datum der letzten Aktualisierung/null_flavour
   */
  @Path("/protocol[at0005]/items[at0006]/null_flavour|defining_code")
  private NullFlavour datumDerLetztenAktualisierungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/Erweiterung
   * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
   * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
   */
  @Path("/protocol[at0005]/items[at0019]")
  private List<Cluster> erweiterung;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Kortision/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
     this.nameDesMedikamentsValue = nameDesMedikamentsValue;
  }

  public String getNameDesMedikamentsValue() {
     return this.nameDesMedikamentsValue ;
  }

  public void setNameDesMedikamentsNullFlavourDefiningCode(
      NullFlavour nameDesMedikamentsNullFlavourDefiningCode) {
     this.nameDesMedikamentsNullFlavourDefiningCode = nameDesMedikamentsNullFlavourDefiningCode;
  }

  public NullFlavour getNameDesMedikamentsNullFlavourDefiningCode() {
     return this.nameDesMedikamentsNullFlavourDefiningCode ;
  }

  public void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode) {
     this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode2 getStatusDefiningCode() {
     return this.statusDefiningCode ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setDatumDerLetztenAktualisierungValue(
      TemporalAccessor datumDerLetztenAktualisierungValue) {
     this.datumDerLetztenAktualisierungValue = datumDerLetztenAktualisierungValue;
  }

  public TemporalAccessor getDatumDerLetztenAktualisierungValue() {
     return this.datumDerLetztenAktualisierungValue ;
  }

  public void setDatumDerLetztenAktualisierungNullFlavourDefiningCode(
      NullFlavour datumDerLetztenAktualisierungNullFlavourDefiningCode) {
     this.datumDerLetztenAktualisierungNullFlavourDefiningCode = datumDerLetztenAktualisierungNullFlavourDefiningCode;
  }

  public NullFlavour getDatumDerLetztenAktualisierungNullFlavourDefiningCode() {
     return this.datumDerLetztenAktualisierungNullFlavourDefiningCode ;
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
