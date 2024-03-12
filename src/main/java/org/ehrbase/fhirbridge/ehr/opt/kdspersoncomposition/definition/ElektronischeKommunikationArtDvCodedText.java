package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.160468428+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
@OptionFor("DV_CODED_TEXT")
public class ElektronischeKommunikationArtDvCodedText implements RMEntity, ElektronischeKommunikationArtChoice {
  /**
   * Path: Person/Personendaten/Kontaktperson/Elektronische Kommunikation/Art/Art
   * Description: Die Art oder Form der elektronischen Kommunikation.
   * Comment: Der Wertesatz DV_CODED_TEXT unterstützt die Repräsentation der am häufigsten im Gesundheitswesen verwendeten elektronischen Kommunikation. Wenn andere Alternativen erforderlich sind, kann der Datentyp DV_TEXT verwendet werden, um andere Arten elektronischer Kommunikation wie Social Media- oder URLs zur Videokonferenz in einem Template darzustellen.
   */
  @Path("|defining_code")
  private ArtDefiningCode2 artDefiningCode;

  public void setArtDefiningCode(ArtDefiningCode2 artDefiningCode) {
     this.artDefiningCode = artDefiningCode;
  }

  public ArtDefiningCode2 getArtDefiningCode() {
     return this.artDefiningCode ;
  }
}
