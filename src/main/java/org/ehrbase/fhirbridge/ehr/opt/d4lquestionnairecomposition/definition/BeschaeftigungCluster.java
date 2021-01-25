package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-01-25T13:06:40.844533+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class BeschaeftigungCluster implements LocatableEntity {
  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigung/Berufsbereich
   * Description: Die Hauptberufsbezeichnung oder die Rolle der Person.
   */
  @Path("/items[at0005 and name/value='Berufsbereich']/value|defining_code")
  private BerufsbereichDefiningCode berufsbereichDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Tree/Beschäftigung/Berufsbereich/null_flavour
   */
  @Path("/items[at0005 and name/value='Berufsbereich']/null_flavour|defining_code")
  private NullFlavour berufsbereichNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigung/Angaben zur Organisation
   * Description: Angaben zum Arbeitgeber oder zur Einrichtung.
   */
  @Path("/items[at0004]")
  private List<Cluster> angabenZurOrganisation;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigung/Zusätzliche Angaben
   * Description: Weitere Angaben zu einer Beschäftigung.
   * Comment: Zum Beispiel: Standort und Bedingungen am Arbeitsplatz oder Kampfzonenerfahrung.
   */
  @Path("/items[at0018]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Path: Selbstüberwachung/Allgemeine Angaben/Zusammenfassung der Beschäftigung/Beschäftigung/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setBerufsbereichDefiningCode(BerufsbereichDefiningCode berufsbereichDefiningCode) {
     this.berufsbereichDefiningCode = berufsbereichDefiningCode;
  }

  public BerufsbereichDefiningCode getBerufsbereichDefiningCode() {
     return this.berufsbereichDefiningCode ;
  }

  public void setBerufsbereichNullFlavourDefiningCode(
      NullFlavour berufsbereichNullFlavourDefiningCode) {
     this.berufsbereichNullFlavourDefiningCode = berufsbereichNullFlavourDefiningCode;
  }

  public NullFlavour getBerufsbereichNullFlavourDefiningCode() {
     return this.berufsbereichNullFlavourDefiningCode ;
  }

  public void setAngabenZurOrganisation(List<Cluster> angabenZurOrganisation) {
     this.angabenZurOrganisation = angabenZurOrganisation;
  }

  public List<Cluster> getAngabenZurOrganisation() {
     return this.angabenZurOrganisation ;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
