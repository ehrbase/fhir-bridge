package org.ehrbase.fhirbridge.ehr.opt.serologischerbefundcomposition.definition;

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
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-22T12:41:59.510682+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class AnatomischeLokalisationCluster implements LocatableEntity {
  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Name der Körperstelle
   * Description: Identifikation einer einzelnen physischen Stelle entweder am oder innerhalb des menschlichen Körpers.
   */
  @Path("/items[at0001]/value|defining_code")
  private NameDerKoerperstelleDefiningCode nameDerKoerperstelleDefiningCode;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Tree/Probe/Anatomische Lokalisation/Name der Körperstelle/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour nameDerKoerperstelleNullFlavourDefiningCode;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Alternative Struktur
   * Description: Zusätzliche Informationen über die anatomische Lage mit alternativen Ansätzen zur Beschreibung der gleichen Körperstelle.
   * Comment: Zum Beispiel, relative oder exakte Positionen unter Verwendung von Koordinaten.
   */
  @Path("/items[at0053]")
  private List<Cluster> alternativeStruktur;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/Multimediale Darstellung
   * Description: Bilder oder andere Medien, die der Identifizierung der Körperstelle dienen.
   */
  @Path("/items[at0054]")
  private List<Cluster> multimedialeDarstellung;

  /**
   * Path: Virologischer Befund/Befund/Jedes Ereignis/Probe/Anatomische Lokalisation/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDerKoerperstelleDefiningCode(
      NameDerKoerperstelleDefiningCode nameDerKoerperstelleDefiningCode) {
     this.nameDerKoerperstelleDefiningCode = nameDerKoerperstelleDefiningCode;
  }

  public NameDerKoerperstelleDefiningCode getNameDerKoerperstelleDefiningCode() {
     return this.nameDerKoerperstelleDefiningCode ;
  }

  public void setNameDerKoerperstelleNullFlavourDefiningCode(
      NullFlavour nameDerKoerperstelleNullFlavourDefiningCode) {
     this.nameDerKoerperstelleNullFlavourDefiningCode = nameDerKoerperstelleNullFlavourDefiningCode;
  }

  public NullFlavour getNameDerKoerperstelleNullFlavourDefiningCode() {
     return this.nameDerKoerperstelleNullFlavourDefiningCode ;
  }

  public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
     this.alternativeStruktur = alternativeStruktur;
  }

  public List<Cluster> getAlternativeStruktur() {
     return this.alternativeStruktur ;
  }

  public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
     this.multimedialeDarstellung = multimedialeDarstellung;
  }

  public List<Cluster> getMultimedialeDarstellung() {
     return this.multimedialeDarstellung ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
