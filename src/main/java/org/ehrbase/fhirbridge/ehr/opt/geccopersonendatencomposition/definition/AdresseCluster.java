package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-03-15T15:57:50.553919+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public class AdresseCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Verwendung
   * Description: Der Zweck der Adresse
   */
  @Path("/items[at0001]/value|defining_code")
  private VerwendungDefiningCode verwendungDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Verwendung/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour verwendungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Typ der Adresse
   * Description: Unterscheidet zwischen physischen Adressen (diejenigen, die Sie besuchen können) und Postadressen (z.B. Postfächer und Pflegeadressen). Die meisten Adressen sind beides.
   */
  @Path("/items[at0006]/value|defining_code")
  private TypDerAdresseDefiningCode typDerAdresseDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Typ der Adresse/null_flavour
   */
  @Path("/items[at0006]/null_flavour|defining_code")
  private NullFlavour typDerAdresseNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Text
   * Description: Eine Darstellung der Adresse im Volltext.
   */
  @Path("/items[at0010]/value|value")
  private String textValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Text/null_flavour
   */
  @Path("/items[at0010]/null_flavour|defining_code")
  private NullFlavour textNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Zeile
   * Description: Diese Komponente enthält die Hausnummer, Wohnungsnummer, Straßenname, Straßenrichtung, Postfachnummer, Zustellhinweise und ähnliche Adressinformationen.
   */
  @Path("/items[at0011]")
  private List<AdresseZeileElement> zeile;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Stadt
   * Description: Der Name der Stadt, des Ortes, des Dorfes oder einer anderen Gemeinde oder eines Lieferzentrums.
   */
  @Path("/items[at0012]/value|value")
  private String stadtValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Stadt/null_flavour
   */
  @Path("/items[at0012]/null_flavour|defining_code")
  private NullFlavour stadtNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Bezirk
   * Description: Der Name des Verwaltungsgebiets (Landkreis).
   */
  @Path("/items[at0013]/value|value")
  private String bezirkValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Bezirk/null_flavour
   */
  @Path("/items[at0013]/null_flavour|defining_code")
  private NullFlavour bezirkNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Postleitzahl
   * Description: Eine Postleitzahl, die eine durch den Postdienst definierte Region bezeichnet.
   */
  @Path("/items[at0014]/value|value")
  private String postleitzahlValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Postleitzahl/null_flavour
   */
  @Path("/items[at0014]/null_flavour|defining_code")
  private NullFlavour postleitzahlNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Land
   * Description: Land - eine Nation, wie allgemein verstanden oder allgemein akzeptiert.
   */
  @Path("/items[at0015]/value|value")
  private String landValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Land/null_flavour
   */
  @Path("/items[at0015]/null_flavour|defining_code")
  private NullFlavour landNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Beginn der Gültigkeitsdauer
   * Description: Der Beginn der Periode. Die Eingrenzung ist inklusive.
   */
  @Path("/items[at0016]/value|value")
  private TemporalAccessor beginnDerGueltigkeitsdauerValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Beginn der Gültigkeitsdauer/null_flavour
   */
  @Path("/items[at0016]/null_flavour|defining_code")
  private NullFlavour beginnDerGueltigkeitsdauerNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/Ende der Gültigkeitsdauer
   * Description: Das Ende der Periode. Wenn das Ende der Periode fehlt, ist die Periode noch nicht abgeschlossen. Der Beginn kann in der Vergangenheit und das Enddatum in der Zukunft liegen, was bedeutet, dass der Zeitraum voraussichtlich zu diesem Zeitpunkt endet.
   */
  @Path("/items[at0017]/value|value")
  private TemporalAccessor endeDerGueltigkeitsdauerValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Adresse/Ende der Gültigkeitsdauer/null_flavour
   */
  @Path("/items[at0017]/null_flavour|defining_code")
  private NullFlavour endeDerGueltigkeitsdauerNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Adresse/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setVerwendungDefiningCode(VerwendungDefiningCode verwendungDefiningCode) {
     this.verwendungDefiningCode = verwendungDefiningCode;
  }

  public VerwendungDefiningCode getVerwendungDefiningCode() {
     return this.verwendungDefiningCode ;
  }

  public void setVerwendungNullFlavourDefiningCode(NullFlavour verwendungNullFlavourDefiningCode) {
     this.verwendungNullFlavourDefiningCode = verwendungNullFlavourDefiningCode;
  }

  public NullFlavour getVerwendungNullFlavourDefiningCode() {
     return this.verwendungNullFlavourDefiningCode ;
  }

  public void setTypDerAdresseDefiningCode(TypDerAdresseDefiningCode typDerAdresseDefiningCode) {
     this.typDerAdresseDefiningCode = typDerAdresseDefiningCode;
  }

  public TypDerAdresseDefiningCode getTypDerAdresseDefiningCode() {
     return this.typDerAdresseDefiningCode ;
  }

  public void setTypDerAdresseNullFlavourDefiningCode(
      NullFlavour typDerAdresseNullFlavourDefiningCode) {
     this.typDerAdresseNullFlavourDefiningCode = typDerAdresseNullFlavourDefiningCode;
  }

  public NullFlavour getTypDerAdresseNullFlavourDefiningCode() {
     return this.typDerAdresseNullFlavourDefiningCode ;
  }

  public void setTextValue(String textValue) {
     this.textValue = textValue;
  }

  public String getTextValue() {
     return this.textValue ;
  }

  public void setTextNullFlavourDefiningCode(NullFlavour textNullFlavourDefiningCode) {
     this.textNullFlavourDefiningCode = textNullFlavourDefiningCode;
  }

  public NullFlavour getTextNullFlavourDefiningCode() {
     return this.textNullFlavourDefiningCode ;
  }

  public void setZeile(List<AdresseZeileElement> zeile) {
     this.zeile = zeile;
  }

  public List<AdresseZeileElement> getZeile() {
     return this.zeile ;
  }

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setStadtNullFlavourDefiningCode(NullFlavour stadtNullFlavourDefiningCode) {
     this.stadtNullFlavourDefiningCode = stadtNullFlavourDefiningCode;
  }

  public NullFlavour getStadtNullFlavourDefiningCode() {
     return this.stadtNullFlavourDefiningCode ;
  }

  public void setBezirkValue(String bezirkValue) {
     this.bezirkValue = bezirkValue;
  }

  public String getBezirkValue() {
     return this.bezirkValue ;
  }

  public void setBezirkNullFlavourDefiningCode(NullFlavour bezirkNullFlavourDefiningCode) {
     this.bezirkNullFlavourDefiningCode = bezirkNullFlavourDefiningCode;
  }

  public NullFlavour getBezirkNullFlavourDefiningCode() {
     return this.bezirkNullFlavourDefiningCode ;
  }

  public void setPostleitzahlValue(String postleitzahlValue) {
     this.postleitzahlValue = postleitzahlValue;
  }

  public String getPostleitzahlValue() {
     return this.postleitzahlValue ;
  }

  public void setPostleitzahlNullFlavourDefiningCode(
      NullFlavour postleitzahlNullFlavourDefiningCode) {
     this.postleitzahlNullFlavourDefiningCode = postleitzahlNullFlavourDefiningCode;
  }

  public NullFlavour getPostleitzahlNullFlavourDefiningCode() {
     return this.postleitzahlNullFlavourDefiningCode ;
  }

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }

  public void setLandNullFlavourDefiningCode(NullFlavour landNullFlavourDefiningCode) {
     this.landNullFlavourDefiningCode = landNullFlavourDefiningCode;
  }

  public NullFlavour getLandNullFlavourDefiningCode() {
     return this.landNullFlavourDefiningCode ;
  }

  public void setBeginnDerGueltigkeitsdauerValue(TemporalAccessor beginnDerGueltigkeitsdauerValue) {
     this.beginnDerGueltigkeitsdauerValue = beginnDerGueltigkeitsdauerValue;
  }

  public TemporalAccessor getBeginnDerGueltigkeitsdauerValue() {
     return this.beginnDerGueltigkeitsdauerValue ;
  }

  public void setBeginnDerGueltigkeitsdauerNullFlavourDefiningCode(
      NullFlavour beginnDerGueltigkeitsdauerNullFlavourDefiningCode) {
     this.beginnDerGueltigkeitsdauerNullFlavourDefiningCode = beginnDerGueltigkeitsdauerNullFlavourDefiningCode;
  }

  public NullFlavour getBeginnDerGueltigkeitsdauerNullFlavourDefiningCode() {
     return this.beginnDerGueltigkeitsdauerNullFlavourDefiningCode ;
  }

  public void setEndeDerGueltigkeitsdauerValue(TemporalAccessor endeDerGueltigkeitsdauerValue) {
     this.endeDerGueltigkeitsdauerValue = endeDerGueltigkeitsdauerValue;
  }

  public TemporalAccessor getEndeDerGueltigkeitsdauerValue() {
     return this.endeDerGueltigkeitsdauerValue ;
  }

  public void setEndeDerGueltigkeitsdauerNullFlavourDefiningCode(
      NullFlavour endeDerGueltigkeitsdauerNullFlavourDefiningCode) {
     this.endeDerGueltigkeitsdauerNullFlavourDefiningCode = endeDerGueltigkeitsdauerNullFlavourDefiningCode;
  }

  public NullFlavour getEndeDerGueltigkeitsdauerNullFlavourDefiningCode() {
     return this.endeDerGueltigkeitsdauerNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
