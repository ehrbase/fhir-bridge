package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.study_participation.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-04T11:17:24.274245200+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.4.0"
)
public class StudienteilnahmeCluster implements LocatableEntity {
  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung
   * Description: Detaillierte Informationen über eine klinische Prüfung, Beobachtungs-, Register-, Diagnostik-, Therapiestudie oder ein anderes medizinisches Forschungsvorhaben an Menschen.
   */
  @Path("/items[openEHR-EHR-CLUSTER.study_details.v1]")
  private StudiePruefungCluster studiePruefung;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studienzentrum
   * Description: Detailangaben über das Studienzentrum, das für den Patienten zuständig ist.
   * Comment: Zum Beispiel: Name der Einrichtung, Adresse, Name des Prüfers, Kontaktdetails und weitere Details. Hier können Demographische Archetypen eingefügt werden. 
   */
  @Path("/items[at0015]")
  private List<Cluster> studienzentrum;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Bestätigte Covid-19-Diagnose als Hauptursache für Aufnahme in Studie
   * Description: Zusätzliche Informationen zu der Studienteilnahme.
   */
  @Path("/items[at0014 and name/value='Bestätigte Covid-19-Diagnose als Hauptursache für Aufnahme in Studie']/value")
  private DvCodedText bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Studienteilnahme/Bestätigte Covid-19-Diagnose als Hauptursache für Aufnahme in Studie/null_flavour
   */
  @Path("/items[at0014 and name/value='Bestätigte Covid-19-Diagnose als Hauptursache für Aufnahme in Studie']/null_flavour|defining_code")
  private NullFlavour bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setStudiePruefung(StudiePruefungCluster studiePruefung) {
     this.studiePruefung = studiePruefung;
  }

  public StudiePruefungCluster getStudiePruefung() {
     return this.studiePruefung ;
  }

  public void setStudienzentrum(List<Cluster> studienzentrum) {
     this.studienzentrum = studienzentrum;
  }

  public List<Cluster> getStudienzentrum() {
     return this.studienzentrum ;
  }

  public void setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie(
      DvCodedText bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie) {
     this.bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie = bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie;
  }

  public DvCodedText getBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie() {
     return this.bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudie ;
  }

  public void setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode(
      NullFlavour bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode) {
     this.bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode = bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode;
  }

  public NullFlavour getBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode(
      ) {
     return this.bestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
