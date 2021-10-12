package org.ehrbase.fhirbridge.ehr.opt.geccoprozedurcomposition.definition;

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
@Archetype("openEHR-EHR-CLUSTER.device.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-12T21:07:24.071169+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class MedizingeraetCluster implements LocatableEntity {
  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Gerätename
   * Description: Identifizierung des Medizingerätes, bevorzugt durch einen allgemein
   * gebräuchlichen Namen, einer formellen und vollständig beschreibenden Bezeichnung oder falls notwendig anhand einer Klasse oder Kategorie des Gerätes.
   * Comment: Dieses Datenelement erfasst den Begriff, die Phrase oder die Kategorie, die in der klinischen Praxis verwendet werden. Zum Beispiel: <Markenname> <Maschine> (XYZ-Audiometer); <Markenname> (14G Jelco IV-Katheter); oder <Markenname / Typ> <Implantat>. Die Codierung mit einer Terminologie ist nach Möglichkeit wünschenswert, auch wenn dies lokal sein kann und von den verfügbaren lokalen Lieferungen abhängt.
   */
  @Path("/items[at0001]/value|defining_code")
  private GeraetenameDefiningCode geraetenameDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Tree/Medizingerät/Gerätename/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour geraetenameNullFlavourDefiningCode;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Eigenschaften
   * Description: Weitere Details zu bestimmten Eigenschaften des Medizingerätes.
   */
  @Path("/items[at0009]")
  private List<Cluster> eigenschaften;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Geräteverwaltung
   * Description: Weitere Details zur Verwaltung und Wartung des Geräts.
   * Comment: Zum Beispiel: Eigentümer, Kontaktdaten, Standort, Netzwerkadresse, Ersetzungsdatum, Kalibrierungsdetails usw.
   */
  @Path("/items[at0019]")
  private List<Cluster> geraeteverwaltung;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Komponenten
   * Description: Zusätzliche strukturierte Informationen zu identifizierten Komponenten des Geräts.
   */
  @Path("/items[at0018]")
  private List<Cluster> komponenten;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Erweiterung
   * Description: Zusätzliche Informationen, die zur Erfassung des lokalen Kontexts oder
   * zur Angleichung an andere Referenzmodelle/Formalismen erforderlich sind.
   */
  @Path("/items[at0026]")
  private List<Cluster> erweiterung;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/Multimedia
   * Description: Digitale Repräsentation des Gerätes.
   * Comment: Zum Beispiel: ein technisches Diagramm eines Geräts oder ein digitales Bild.
   */
  @Path("/items[at0027]")
  private List<Cluster> multimedia;

  /**
   * Path: GECCO_Prozedur/Prozedur/Medizingerät/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGeraetenameDefiningCode(GeraetenameDefiningCode geraetenameDefiningCode) {
     this.geraetenameDefiningCode = geraetenameDefiningCode;
  }

  public GeraetenameDefiningCode getGeraetenameDefiningCode() {
     return this.geraetenameDefiningCode ;
  }

  public void setGeraetenameNullFlavourDefiningCode(
      NullFlavour geraetenameNullFlavourDefiningCode) {
     this.geraetenameNullFlavourDefiningCode = geraetenameNullFlavourDefiningCode;
  }

  public NullFlavour getGeraetenameNullFlavourDefiningCode() {
     return this.geraetenameNullFlavourDefiningCode ;
  }

  public void setEigenschaften(List<Cluster> eigenschaften) {
     this.eigenschaften = eigenschaften;
  }

  public List<Cluster> getEigenschaften() {
     return this.eigenschaften ;
  }

  public void setGeraeteverwaltung(List<Cluster> geraeteverwaltung) {
     this.geraeteverwaltung = geraeteverwaltung;
  }

  public List<Cluster> getGeraeteverwaltung() {
     return this.geraeteverwaltung ;
  }

  public void setKomponenten(List<Cluster> komponenten) {
     this.komponenten = komponenten;
  }

  public List<Cluster> getKomponenten() {
     return this.komponenten ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setMultimedia(List<Cluster> multimedia) {
     this.multimedia = multimedia;
  }

  public List<Cluster> getMultimedia() {
     return this.multimedia ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
