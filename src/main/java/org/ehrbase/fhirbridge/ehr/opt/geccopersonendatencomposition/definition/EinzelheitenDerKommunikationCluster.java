package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.telecom_details.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-04T14:52:22.375439700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class EinzelheitenDerKommunikationCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Modus
   * Description: Eine Kennzeichnung für einen Telekommunikationskontakt, die dessen Kontext beschreibt, z.B. "Arbeit", "Privat". ENV 13606 - 4:2000 7.11.19.
   */
  @Path("/items[at0010]")
  private List<EinzelheitenDerKommunikationModusElement> modus;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Kontaktdaten
   * Description: Eine oder mehrere Kontaktadressen einer Person oder Einrichtung.
   */
  @Path("/items[at0001]")
  private List<EinzelheitenDerKommunikationKontaktdatenCluster> kontaktdaten;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/Internet-Kommunikation
   * Description: Angaben zur internetbasierten Kommunikation.
   */
  @Path("/items[at0020]")
  private List<EinzelheitenDerKommunikationInternetKommunikationCluster> internetKommunikation;

  /**
   * Path: GECCO_Personendaten/Personendaten/Einzelheiten der Kommunikation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setModus(List<EinzelheitenDerKommunikationModusElement> modus) {
     this.modus = modus;
  }

  public List<EinzelheitenDerKommunikationModusElement> getModus() {
     return this.modus ;
  }

  public void setKontaktdaten(List<EinzelheitenDerKommunikationKontaktdatenCluster> kontaktdaten) {
     this.kontaktdaten = kontaktdaten;
  }

  public List<EinzelheitenDerKommunikationKontaktdatenCluster> getKontaktdaten() {
     return this.kontaktdaten ;
  }

  public void setInternetKommunikation(
      List<EinzelheitenDerKommunikationInternetKommunikationCluster> internetKommunikation) {
     this.internetKommunikation = internetKommunikation;
  }

  public List<EinzelheitenDerKommunikationInternetKommunikationCluster> getInternetKommunikation() {
     return this.internetKommunikation ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
