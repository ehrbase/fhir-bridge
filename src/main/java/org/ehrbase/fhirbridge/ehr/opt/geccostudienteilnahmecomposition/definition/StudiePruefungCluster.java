package org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.study_details.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-09-28T15:24:25.271391+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class StudiePruefungCluster implements LocatableEntity {
  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Titel der Studie/Prüfung
   * Description: Titel des Forschungsvorhabens.
   * Comment: Zum Beispiel: "Eine randomisierte Phase-II-Studie mit nal-Iri plus 5-Fluorouracil im Vergleich zu 5-Fluorouracil bei stationären Patienten mit Cholangio- und Gallenblasenkarzinom, die zuvor mit Gemcitabin oder Gemcitabin-haltigen Therapien behandelt wurden."
   */
  @Path("/items[at0001]/value|defining_code")
  private TitelDerStudiePruefungDefiningCode titelDerStudiePruefungDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Studienteilnahme/Studie/Prüfung/Titel der Studie/Prüfung/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour titelDerStudiePruefungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Beschreibung
   * Description: Kurze Beschreibung des Forschungsvorhabens.
   * Comment: Beschreibung des Forschungsvorhabens in leicht verständlicher Formulierung für Laien.
   */
  @Path("/items[at0004]/value|value")
  private String beschreibungValue;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Item tree/Studienteilnahme/Studie/Prüfung/Beschreibung/null_flavour
   */
  @Path("/items[at0004]/null_flavour|defining_code")
  private NullFlavour beschreibungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Registrierung
   * Description: Registrierung der Studie in Registern.
   * Comment: Wenn die Studie auf der Webseite Clinicaltrials.gov registriert ist, besitzt sie eine US NCT-Nummer. Zum Beispiel: NCT03772405. 
   * Eine EudraCT Nummer wird von der Europäischen Arzneimittelagentur vergeben. Wenn die klinische Prüfung auf der Webseite Current Controlled Trials registriert ist, besitzt sie eine ISRCTN-Nummer (International Standard Randomised Controlled Trial Number).
   */
  @Path("/items[at0033]")
  private List<StudiePruefungRegistrierungCluster> registrierung;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Studienzentrum
   * Description: Detailangaben über die teilnehmende medizinische Einrichtung wie Klinik oder Praxis, die Patienten gemäß den Studienvorgaben rekrutiert und die Daten erhebt.
   * Comment: Zum Beispiel: Name der Einrichtung, Adresse, Name des Prüfers, Kontaktdetails, zuständige Ethikkommission, Datum der Genehmigung der Teilnahme, beteiligte Personen und weitere Details. Hier können auch demographische Archetypen eingefügt werden.
   */
  @Path("/items[at0023]")
  private List<Cluster> studienzentrum;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/Zusätzliche Details
   * Description: Zusätzliche strukturierte Angaben zur Studie.
   * Comment: Zum Beispiel detaillierte Angaben über die Genehmigungsbehörde, zum Studiendesign oder -plan, zum Sponsor, zu den Therapiearmen und/oder zum Studienmedikament oder Ein-/Ausschlusskriterien als Voraussetzung für eine Studienteilnahme.
   */
  @Path("/items[at0014]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: GECCO_Studienteilnahme/GECCO_Studienteilnahme/Studienteilnahme/Studie/Prüfung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTitelDerStudiePruefungDefiningCode(
      TitelDerStudiePruefungDefiningCode titelDerStudiePruefungDefiningCode) {
     this.titelDerStudiePruefungDefiningCode = titelDerStudiePruefungDefiningCode;
  }

  public TitelDerStudiePruefungDefiningCode getTitelDerStudiePruefungDefiningCode() {
     return this.titelDerStudiePruefungDefiningCode ;
  }

  public void setTitelDerStudiePruefungNullFlavourDefiningCode(
      NullFlavour titelDerStudiePruefungNullFlavourDefiningCode) {
     this.titelDerStudiePruefungNullFlavourDefiningCode = titelDerStudiePruefungNullFlavourDefiningCode;
  }

  public NullFlavour getTitelDerStudiePruefungNullFlavourDefiningCode() {
     return this.titelDerStudiePruefungNullFlavourDefiningCode ;
  }

  public void setBeschreibungValue(String beschreibungValue) {
     this.beschreibungValue = beschreibungValue;
  }

  public String getBeschreibungValue() {
     return this.beschreibungValue ;
  }

  public void setBeschreibungNullFlavourDefiningCode(
      NullFlavour beschreibungNullFlavourDefiningCode) {
     this.beschreibungNullFlavourDefiningCode = beschreibungNullFlavourDefiningCode;
  }

  public NullFlavour getBeschreibungNullFlavourDefiningCode() {
     return this.beschreibungNullFlavourDefiningCode ;
  }

  public void setRegistrierung(List<StudiePruefungRegistrierungCluster> registrierung) {
     this.registrierung = registrierung;
  }

  public List<StudiePruefungRegistrierungCluster> getRegistrierung() {
     return this.registrierung ;
  }

  public void setStudienzentrum(List<Cluster> studienzentrum) {
     this.studienzentrum = studienzentrum;
  }

  public List<Cluster> getStudienzentrum() {
     return this.studienzentrum ;
  }

  public void setZusaetzlicheDetails(List<Cluster> zusaetzlicheDetails) {
     this.zusaetzlicheDetails = zusaetzlicheDetails;
  }

  public List<Cluster> getZusaetzlicheDetails() {
     return this.zusaetzlicheDetails ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
