package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Boolean;
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
@Archetype("openEHR-EHR-CLUSTER.person_name.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-04T14:52:22.132442200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class PersonennameCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Namensart
   * Description: Art des beschriebenen Namens.
   */
  @Path("/items[at0006]/value|defining_code")
  private NamensartDefiningCode namensartDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Namensart/null_flavour
   */
  @Path("/items[at0006]/null_flavour|defining_code")
  private NullFlavour namensartNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Bevorzugter Name
   * Description: Gibt an, dass dies der Name ist, unter dem eine Person identifiziert werden soll.
   */
  @Path("/items[at0022]/value|value")
  private Boolean bevorzugterNameValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Bevorzugter Name/null_flavour
   */
  @Path("/items[at0022]/null_flavour|defining_code")
  private NullFlavour bevorzugterNameNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name unstrukturiert
   * Description: Name in unstrukturierter Form als Freitext.
   */
  @Path("/items[at0001]/value|value")
  private String nameUnstrukturiertValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name unstrukturiert/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour nameUnstrukturiertNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Titel
   * Description: Das von der Person verwendete Präfix oder Titel.
   */
  @Path("/items[at0002]/items[at0017]/value|value")
  private String titelValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name strukturiert/Titel/null_flavour
   */
  @Path("/items[at0002]/items[at0017]/null_flavour|defining_code")
  private NullFlavour titelNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Vorname
   * Description: Vorname
   */
  @Path("/items[at0002]/items[at0003]/value|value")
  private String vornameValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name strukturiert/Vorname/null_flavour
   */
  @Path("/items[at0002]/items[at0003]/null_flavour|defining_code")
  private NullFlavour vornameNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Weiterer Vorname
   * Description: Zweiter Vorname oder zweite Namen.
   */
  @Path("/items[at0002]/items[at0004]")
  private List<PersonennameWeitererVornameElement> weitererVorname;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Nachname
   * Description: Familienname oder Nachname.
   */
  @Path("/items[at0002]/items[at0005]/value|value")
  private String nachnameValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name strukturiert/Nachname/null_flavour
   */
  @Path("/items[at0002]/items[at0005]/null_flavour|defining_code")
  private NullFlavour nachnameNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Name strukturiert/Suffix
   * Description: Zusätzlicher Begriff, der nach einem Personennamen verwendet wird.
   */
  @Path("/items[at0002]/items[at0018]/value|value")
  private String suffixValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Name strukturiert/Suffix/null_flavour
   */
  @Path("/items[at0002]/items[at0018]/null_flavour|defining_code")
  private NullFlavour suffixNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Gültigkeitszeitraum/upper
   */
  @Path("/items[at0014]/value/upper|value")
  private TemporalAccessor upperValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Gültigkeitszeitraum/lower
   */
  @Path("/items[at0014]/value/lower|value")
  private TemporalAccessor lowerValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Gültigkeitszeitraum/lower_included
   */
  @Path("/items[at0014]/value/lower_included")
  private Boolean lowerIncluded;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/Gültigkeitszeitraum/upper_included
   */
  @Path("/items[at0014]/value/upper_included")
  private Boolean upperIncluded;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Personenname/Gültigkeitszeitraum/null_flavour
   */
  @Path("/items[at0014]/null_flavour|defining_code")
  private NullFlavour gueltigkeitszeitraumNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Personenname/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNamensartDefiningCode(NamensartDefiningCode namensartDefiningCode) {
     this.namensartDefiningCode = namensartDefiningCode;
  }

  public NamensartDefiningCode getNamensartDefiningCode() {
     return this.namensartDefiningCode ;
  }

  public void setNamensartNullFlavourDefiningCode(NullFlavour namensartNullFlavourDefiningCode) {
     this.namensartNullFlavourDefiningCode = namensartNullFlavourDefiningCode;
  }

  public NullFlavour getNamensartNullFlavourDefiningCode() {
     return this.namensartNullFlavourDefiningCode ;
  }

  public void setBevorzugterNameValue(Boolean bevorzugterNameValue) {
     this.bevorzugterNameValue = bevorzugterNameValue;
  }

  public Boolean isBevorzugterNameValue() {
     return this.bevorzugterNameValue ;
  }

  public void setBevorzugterNameNullFlavourDefiningCode(
      NullFlavour bevorzugterNameNullFlavourDefiningCode) {
     this.bevorzugterNameNullFlavourDefiningCode = bevorzugterNameNullFlavourDefiningCode;
  }

  public NullFlavour getBevorzugterNameNullFlavourDefiningCode() {
     return this.bevorzugterNameNullFlavourDefiningCode ;
  }

  public void setNameUnstrukturiertValue(String nameUnstrukturiertValue) {
     this.nameUnstrukturiertValue = nameUnstrukturiertValue;
  }

  public String getNameUnstrukturiertValue() {
     return this.nameUnstrukturiertValue ;
  }

  public void setNameUnstrukturiertNullFlavourDefiningCode(
      NullFlavour nameUnstrukturiertNullFlavourDefiningCode) {
     this.nameUnstrukturiertNullFlavourDefiningCode = nameUnstrukturiertNullFlavourDefiningCode;
  }

  public NullFlavour getNameUnstrukturiertNullFlavourDefiningCode() {
     return this.nameUnstrukturiertNullFlavourDefiningCode ;
  }

  public void setTitelValue(String titelValue) {
     this.titelValue = titelValue;
  }

  public String getTitelValue() {
     return this.titelValue ;
  }

  public void setTitelNullFlavourDefiningCode(NullFlavour titelNullFlavourDefiningCode) {
     this.titelNullFlavourDefiningCode = titelNullFlavourDefiningCode;
  }

  public NullFlavour getTitelNullFlavourDefiningCode() {
     return this.titelNullFlavourDefiningCode ;
  }

  public void setVornameValue(String vornameValue) {
     this.vornameValue = vornameValue;
  }

  public String getVornameValue() {
     return this.vornameValue ;
  }

  public void setVornameNullFlavourDefiningCode(NullFlavour vornameNullFlavourDefiningCode) {
     this.vornameNullFlavourDefiningCode = vornameNullFlavourDefiningCode;
  }

  public NullFlavour getVornameNullFlavourDefiningCode() {
     return this.vornameNullFlavourDefiningCode ;
  }

  public void setWeitererVorname(List<PersonennameWeitererVornameElement> weitererVorname) {
     this.weitererVorname = weitererVorname;
  }

  public List<PersonennameWeitererVornameElement> getWeitererVorname() {
     return this.weitererVorname ;
  }

  public void setNachnameValue(String nachnameValue) {
     this.nachnameValue = nachnameValue;
  }

  public String getNachnameValue() {
     return this.nachnameValue ;
  }

  public void setNachnameNullFlavourDefiningCode(NullFlavour nachnameNullFlavourDefiningCode) {
     this.nachnameNullFlavourDefiningCode = nachnameNullFlavourDefiningCode;
  }

  public NullFlavour getNachnameNullFlavourDefiningCode() {
     return this.nachnameNullFlavourDefiningCode ;
  }

  public void setSuffixValue(String suffixValue) {
     this.suffixValue = suffixValue;
  }

  public String getSuffixValue() {
     return this.suffixValue ;
  }

  public void setSuffixNullFlavourDefiningCode(NullFlavour suffixNullFlavourDefiningCode) {
     this.suffixNullFlavourDefiningCode = suffixNullFlavourDefiningCode;
  }

  public NullFlavour getSuffixNullFlavourDefiningCode() {
     return this.suffixNullFlavourDefiningCode ;
  }

  public void setUpperValue(TemporalAccessor upperValue) {
     this.upperValue = upperValue;
  }

  public TemporalAccessor getUpperValue() {
     return this.upperValue ;
  }

  public void setLowerValue(TemporalAccessor lowerValue) {
     this.lowerValue = lowerValue;
  }

  public TemporalAccessor getLowerValue() {
     return this.lowerValue ;
  }

  public void setLowerIncluded(Boolean lowerIncluded) {
     this.lowerIncluded = lowerIncluded;
  }

  public Boolean isLowerIncluded() {
     return this.lowerIncluded ;
  }

  public void setUpperIncluded(Boolean upperIncluded) {
     this.upperIncluded = upperIncluded;
  }

  public Boolean isUpperIncluded() {
     return this.upperIncluded ;
  }

  public void setGueltigkeitszeitraumNullFlavourDefiningCode(
      NullFlavour gueltigkeitszeitraumNullFlavourDefiningCode) {
     this.gueltigkeitszeitraumNullFlavourDefiningCode = gueltigkeitszeitraumNullFlavourDefiningCode;
  }

  public NullFlavour getGueltigkeitszeitraumNullFlavourDefiningCode() {
     return this.gueltigkeitszeitraumNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
