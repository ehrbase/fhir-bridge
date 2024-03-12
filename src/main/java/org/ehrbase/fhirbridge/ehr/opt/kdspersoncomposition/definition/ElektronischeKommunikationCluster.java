package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.electronic_communication.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.159781214+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class ElektronischeKommunikationCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Elektronische Kommunikation/Art/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour artNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Baum/Kontaktperson/Elektronische Kommunikation/Daten/null_flavour
   */
  @Path("/items[at0002]/null_flavour|defining_code")
  private NullFlavour datenNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/Zusätzliche Details
   * Description: Weitere Details zur elektronischen Kommunikation.
   */
  @Path("/items[at0013]")
  private List<Cluster> zusaetzlicheDetails;

  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/Art
   * Description: Die Art oder Form der elektronischen Kommunikation.
   * Comment: Der Wertesatz DV_CODED_TEXT unterstützt die Repräsentation der am häufigsten im Gesundheitswesen verwendeten elektronischen Kommunikation. Wenn andere Alternativen erforderlich sind, kann der Datentyp DV_TEXT verwendet werden, um andere Arten elektronischer Kommunikation wie Social Media- oder URLs zur Videokonferenz in einem Template darzustellen.
   */
  @Path("/items[at0001]/value")
  @Choice
  private ElektronischeKommunikationArtChoice art;

  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/Daten
   * Description: Die eindeutige Kombination alphanumerischer Zeichen, die für die Darstellung von "Art" relevant ist.
   * Comment: Zum Beispiel: Vorwahl + Telefon/Pagernummer; Ländervorwahl + Mobiltelefonnummer oder E-Mail-Adresse.
   */
  @Path("/items[at0002]/value")
  @Choice
  private ElektronischeKommunikationDatenChoice daten;

  public void setArtNullFlavourDefiningCode(NullFlavour artNullFlavourDefiningCode) {
     this.artNullFlavourDefiningCode = artNullFlavourDefiningCode;
  }

  public NullFlavour getArtNullFlavourDefiningCode() {
     return this.artNullFlavourDefiningCode ;
  }

  public void setDatenNullFlavourDefiningCode(NullFlavour datenNullFlavourDefiningCode) {
     this.datenNullFlavourDefiningCode = datenNullFlavourDefiningCode;
  }

  public NullFlavour getDatenNullFlavourDefiningCode() {
     return this.datenNullFlavourDefiningCode ;
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

  public void setArt(ElektronischeKommunikationArtChoice art) {
     this.art = art;
  }

  public ElektronischeKommunikationArtChoice getArt() {
     return this.art ;
  }

  public void setDaten(ElektronischeKommunikationDatenChoice daten) {
     this.daten = daten;
  }

  public ElektronischeKommunikationDatenChoice getDaten() {
     return this.daten ;
  }
}
