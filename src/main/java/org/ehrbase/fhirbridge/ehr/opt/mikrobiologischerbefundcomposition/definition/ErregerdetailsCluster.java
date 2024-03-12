package org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.lang.Double;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.erregerdetails.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-22T14:23:00.210719+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class ErregerdetailsCluster implements LocatableEntity {
  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Keim Subtyp
   * Description: Subtyp, welcher zusätzlich zur Speziesidentifizierung zur weiteren Kennzeichnung des Erregers genutzt werden kann, z.B. spa-Typ im Falle von S. aureus oder MLST-Typ.
   * Comment: Bestimmte Keimsubtypen beeinflussen die Wirtsreaktion bzw. Immunantwort. 
   * Beispielsweise ein Resultat einer spa-Typsierung bei einem S. aureus oder anderen Typsierungen wie MLST.
   */
  @Path("/items[at0047]")
  private List<ErregerdetailsKeimSubtypElement> keimSubtyp;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Keimzahl
   * Description: Quantitative Angabe zur Keimzahl, z.B. bei Urinen
   */
  @Path("/items[at0035]/value|magnitude")
  private Double keimzahlMagnitude;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Keimzahl
   * Description: Quantitative Angabe zur Keimzahl, z.B. bei Urinen
   */
  @Path("/items[at0035]/value|units")
  private String keimzahlUnits;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Häufigkeit
   * Description: Semiquantitative Angabe zur Keimzahl.
   */
  @Path("/items[at0003]/value")
  private DvOrdinal haeufigkeit;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Häufigkeit/null_flavour
   */
  @Path("/items[at0003]/null_flavour|defining_code")
  private NullFlavour haeufigkeitNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Virulenzfaktor
   * Description: Angabe zu untersuchten
   * Virulenzeigenschaften oder -genen, z.B. PVL bei S. aureus oder EHEC bei E. coli.
   */
  @Path("/items[at0016]/value|value")
  private String virulenzfaktorValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Virulenzfaktor/null_flavour
   */
  @Path("/items[at0016]/null_flavour|defining_code")
  private NullFlavour virulenzfaktorNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Antibiogramm
   * Description: Laborergebnis als Panel/Profil von Einzelresultaten. Verbreitet im medizinischen Labor.
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0 and name/value='Antibiogramm']")
  private AntibiogrammCluster antibiogramm;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Resistenzmechanismus
   * Description: Angabe der bei dem Erreger vorliegenden Resistenzmechanismen, z.B. ESBL oder Carbapenemase.
   */
  @Path("/items[at0057]")
  private List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/MRE Klasse
   * Description: Angabe zur MRE-Klassifikation des
   * Erregers, z.B. 3MRGN oder 4MRGN. Bei VRE und MRSA kann es zu einer Redundanz zum Resistenzmechanismus "Methicillin-resistenz" kommen, dies ist aber problemlos.
   */
  @Path("/items[at0018]/value")
  private DvCodedText mreKlasse;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/MRE Klasse/null_flavour
   */
  @Path("/items[at0018]/null_flavour|defining_code")
  private NullFlavour mreKlasseNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Kommentar
   * Description: Zusätzliche Infomationen zum Erreger.
   */
  @Path("/items[at0062]/value|value")
  private String kommentarValue;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Kommentar/null_flavour
   */
  @Path("/items[at0062]/null_flavour|defining_code")
  private NullFlavour kommentarNullFlavourDefiningCode;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/Weitere Ergänzungen
   * Description: Der Cluster dient dazu, weitere Ergänzungen zum Archetyp Erregerdetails aufzunehmen.
   */
  @Path("/items[at0059]")
  private List<Cluster> weitereErgaenzungen;

  /**
   * Path: Mikrobiologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Erreger/Erregerdetails/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Mikrobiologischer Befund/Befund/Event Series/Jedes Ereignis/Tree/Kultur/Pro Erreger/Erregerdetails/Keimzahl/Keimzahl/null_flavour
   * Description: Quantitative Angabe zur Keimzahl, z.B. bei Urinen
   */
  @Path("/items[at0035]/null_flavour")
  @Choice
  private ErregerdetailsKeimzahlNullFlavourChoice keimzahlNullFlavour;

  public void setKeimSubtyp(List<ErregerdetailsKeimSubtypElement> keimSubtyp) {
     this.keimSubtyp = keimSubtyp;
  }

  public List<ErregerdetailsKeimSubtypElement> getKeimSubtyp() {
     return this.keimSubtyp ;
  }

  public void setKeimzahlMagnitude(Double keimzahlMagnitude) {
     this.keimzahlMagnitude = keimzahlMagnitude;
  }

  public Double getKeimzahlMagnitude() {
     return this.keimzahlMagnitude ;
  }

  public void setKeimzahlUnits(String keimzahlUnits) {
     this.keimzahlUnits = keimzahlUnits;
  }

  public String getKeimzahlUnits() {
     return this.keimzahlUnits ;
  }

  public void setHaeufigkeit(DvOrdinal haeufigkeit) {
     this.haeufigkeit = haeufigkeit;
  }

  public DvOrdinal getHaeufigkeit() {
     return this.haeufigkeit ;
  }

  public void setHaeufigkeitNullFlavourDefiningCode(
      NullFlavour haeufigkeitNullFlavourDefiningCode) {
     this.haeufigkeitNullFlavourDefiningCode = haeufigkeitNullFlavourDefiningCode;
  }

  public NullFlavour getHaeufigkeitNullFlavourDefiningCode() {
     return this.haeufigkeitNullFlavourDefiningCode ;
  }

  public void setVirulenzfaktorValue(String virulenzfaktorValue) {
     this.virulenzfaktorValue = virulenzfaktorValue;
  }

  public String getVirulenzfaktorValue() {
     return this.virulenzfaktorValue ;
  }

  public void setVirulenzfaktorNullFlavourDefiningCode(
      NullFlavour virulenzfaktorNullFlavourDefiningCode) {
     this.virulenzfaktorNullFlavourDefiningCode = virulenzfaktorNullFlavourDefiningCode;
  }

  public NullFlavour getVirulenzfaktorNullFlavourDefiningCode() {
     return this.virulenzfaktorNullFlavourDefiningCode ;
  }

  public void setAntibiogramm(AntibiogrammCluster antibiogramm) {
     this.antibiogramm = antibiogramm;
  }

  public AntibiogrammCluster getAntibiogramm() {
     return this.antibiogramm ;
  }

  public void setResistenzmechanismus(
      List<ErregerdetailsResistenzmechanismusCluster> resistenzmechanismus) {
     this.resistenzmechanismus = resistenzmechanismus;
  }

  public List<ErregerdetailsResistenzmechanismusCluster> getResistenzmechanismus() {
     return this.resistenzmechanismus ;
  }

  public void setMreKlasse(DvCodedText mreKlasse) {
     this.mreKlasse = mreKlasse;
  }

  public DvCodedText getMreKlasse() {
     return this.mreKlasse ;
  }

  public void setMreKlasseNullFlavourDefiningCode(NullFlavour mreKlasseNullFlavourDefiningCode) {
     this.mreKlasseNullFlavourDefiningCode = mreKlasseNullFlavourDefiningCode;
  }

  public NullFlavour getMreKlasseNullFlavourDefiningCode() {
     return this.mreKlasseNullFlavourDefiningCode ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setKommentarNullFlavourDefiningCode(NullFlavour kommentarNullFlavourDefiningCode) {
     this.kommentarNullFlavourDefiningCode = kommentarNullFlavourDefiningCode;
  }

  public NullFlavour getKommentarNullFlavourDefiningCode() {
     return this.kommentarNullFlavourDefiningCode ;
  }

  public void setWeitereErgaenzungen(List<Cluster> weitereErgaenzungen) {
     this.weitereErgaenzungen = weitereErgaenzungen;
  }

  public List<Cluster> getWeitereErgaenzungen() {
     return this.weitereErgaenzungen ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setKeimzahlNullFlavour(ErregerdetailsKeimzahlNullFlavourChoice keimzahlNullFlavour) {
     this.keimzahlNullFlavour = keimzahlNullFlavour;
  }

  public ErregerdetailsKeimzahlNullFlavourChoice getKeimzahlNullFlavour() {
     return this.keimzahlNullFlavour ;
  }
}
