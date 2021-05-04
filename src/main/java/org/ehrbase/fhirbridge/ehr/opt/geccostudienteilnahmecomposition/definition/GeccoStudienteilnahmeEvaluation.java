package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.gecco_study_participation.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-04T16:51:43.100661+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
public class GeccoStudienteilnahmeEvaluation implements EntryEntity {
  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Bereits an interventionellen klinischen Studien teilgenommen?
   * Description: *
   */
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Bereits an interventionellen klinischen Studien teilgenommen?/null_flavour
   */
  @Path("/data[at0001]/items[at0002]/null_flavour|defining_code")
  private NullFlavour bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme
   * Description: Detaillierte Informationen über die Teilnahme an einer klinischen Prüfung, Beobachtungs-, Register-, Diagnostik-, Therapiestudie oder an einem anderen medizinischen Forschungsvorhaben in der Rolle eines Studienpatienten oder Probanden.
   */
  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.study_participation.v1]")
  private StudienteilnahmeCluster studienteilnahme;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(
      BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode) {
     this.bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode = bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode;
  }

  public BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode getBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(
      ) {
     return this.bereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode ;
  }

  public void setBereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode(
      NullFlavour bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode) {
     this.bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode = bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode;
  }

  public NullFlavour getBereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode(
      ) {
     return this.bereitsAnInterventionellenKlinischenStudienTeilgenommenNullFlavourDefiningCode ;
  }

  public void setStudienteilnahme(StudienteilnahmeCluster studienteilnahme) {
     this.studienteilnahme = studienteilnahme;
  }

  public StudienteilnahmeCluster getStudienteilnahme() {
     return this.studienteilnahme ;
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
