package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.String;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.structured_name.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.130145952+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class GeburtsnameCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Geburtsname/Namensart
   * Description: Eine oder mehrere ehrenhafte Anrede(n) am Anfang eines Namens.
   * Comment: Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Titel dargestellt werden können. Die Kodierung mit einer externen Terminologie wird nach Möglichkeit bevorzugt. Beispiel: "Doktor", "Ms", "Mx" oder "Professor Dr".
   */
  @Path("/items[at0001 and name/value='Namensart']/value")
  private DvCodedText namensart;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Namensart/null_flavour
   */
  @Path("/items[at0001 and name/value='Namensart']/null_flavour|defining_code")
  private NullFlavour namensartNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Vollständiger Name
   * Description: Ein oder mehrere eindeutige Namen, die verwendet werden, um eine Person innerhalb einer Familiengruppe zu identifizieren.
   * Comment: Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Vorname dargestellt werden können. Darüber hinaus kann dieses Datenelement innerhalb eines Templates geklont und umbenannt werden, um eine getrennte Darstellung verschiedener Arten von Vornamen zu ermöglichen – zum Beispiel „Vorname“, „Zweiter Vorname“, „bevorzugter Name“ oder „Spitzname“, wie für einen konkreten Anwendungsfall erforderlich.
   */
  @Path("/items[at0002 and name/value='Vollständiger Name']/value|value")
  private String vollstaendigerNameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Vollständiger Name/null_flavour
   */
  @Path("/items[at0002 and name/value='Vollständiger Name']/null_flavour|defining_code")
  private NullFlavour vollstaendigerNameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname
   * Description: Ein oder mehrere Namen, die eine Person mit einer Familiengruppe gemeinsam hat.
   * Comment: Auch bekannt als „Familienname“. Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Familienname dargestellt werden können. Komplexe Namen wie „El Haddad“ oder „van der Heyden“ können mit diesem Benennungsmuster dargestellt werden, wie in ISO 22220 (Anhang F) definiert, aber für den beabsichtigten Anwendungsfall für diesen Archetyp ist es am wahrscheinlichsten, dass der vollständige Nachname als Zeichenfolge aufgezeichnet wird.
   */
  @Path("/items[at0005 and name/value='Familienname']/value|value")
  private String familiennameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname']/null_flavour|defining_code")
  private NullFlavour familiennameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Namenszusatz
   * Description: Ein oder mehrere Namen, die eine Person mit einer Familiengruppe gemeinsam hat.
   * Comment: Auch bekannt als „Familienname“. Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Familienname dargestellt werden können. Komplexe Namen wie „El Haddad“ oder „van der Heyden“ können mit diesem Benennungsmuster dargestellt werden, wie in ISO 22220 (Anhang F) definiert, aber für den beabsichtigten Anwendungsfall für diesen Archetyp ist es am wahrscheinlichsten, dass der vollständige Nachname als Zeichenfolge aufgezeichnet wird.
   */
  @Path("/items[at0005 and name/value='Familienname-Namenszusatz']/value|value")
  private String familiennameNamenszusatzValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Namenszusatz/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Namenszusatz']/null_flavour|defining_code")
  private NullFlavour familiennameNamenszusatzNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Nachname
   * Description: Ein oder mehrere Namen, die eine Person mit einer Familiengruppe gemeinsam hat.
   * Comment: Auch bekannt als „Familienname“. Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Familienname dargestellt werden können. Komplexe Namen wie „El Haddad“ oder „van der Heyden“ können mit diesem Benennungsmuster dargestellt werden, wie in ISO 22220 (Anhang F) definiert, aber für den beabsichtigten Anwendungsfall für diesen Archetyp ist es am wahrscheinlichsten, dass der vollständige Nachname als Zeichenfolge aufgezeichnet wird.
   */
  @Path("/items[at0005 and name/value='Familienname-Nachname']/value|value")
  private String familiennameNachnameValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Nachname/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Nachname']/null_flavour|defining_code")
  private NullFlavour familiennameNachnameNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Familienname-Vorsatzwort
   * Description: Ein oder mehrere Namen, die eine Person mit einer Familiengruppe gemeinsam hat.
   * Comment: Auch bekannt als „Familienname“. Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Familienname dargestellt werden können. Komplexe Namen wie „El Haddad“ oder „van der Heyden“ können mit diesem Benennungsmuster dargestellt werden, wie in ISO 22220 (Anhang F) definiert, aber für den beabsichtigten Anwendungsfall für diesen Archetyp ist es am wahrscheinlichsten, dass der vollständige Nachname als Zeichenfolge aufgezeichnet wird.
   */
  @Path("/items[at0005 and name/value='Familienname-Vorsatzwort']/value|value")
  private String familiennameVorsatzwortValue;

  /**
   * Path: Person/Personendaten/Baum/Person/Geburtsname/Familienname-Vorsatzwort/null_flavour
   */
  @Path("/items[at0005 and name/value='Familienname-Vorsatzwort']/null_flavour|defining_code")
  private NullFlavour familiennameVorsatzwortNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/Suffix
   * Description: Ein oder mehrere Begriffe, die allen anderen Namensbestandteilen nachgestellt werden, normalerweise um eine Person von einem Familienmitglied mit identischen Vor- und Nachnamensbestandteilen zu unterscheiden.
   * Comment: Vorkommen für dieses Datenelement werden auf 0..* gesetzt, damit mehr als ein Suffix aufgezeichnet werden können. Die Codierung mit einer externen Terminologie wird nach Möglichkeit bevorzugt. Zum Beispiel: „Junior (Jr)“, „Senior (Sr)“, " Der Zweite (II)".
   */
  @Path("/items[at0006]")
  private List<GeburtsnameSuffixElement> suffix;

  /**
   * Path: Person/Personendaten/Person/Geburtsname/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNamensart(DvCodedText namensart) {
     this.namensart = namensart;
  }

  public DvCodedText getNamensart() {
     return this.namensart ;
  }

  public void setNamensartNullFlavourDefiningCode(NullFlavour namensartNullFlavourDefiningCode) {
     this.namensartNullFlavourDefiningCode = namensartNullFlavourDefiningCode;
  }

  public NullFlavour getNamensartNullFlavourDefiningCode() {
     return this.namensartNullFlavourDefiningCode ;
  }

  public void setVollstaendigerNameValue(String vollstaendigerNameValue) {
     this.vollstaendigerNameValue = vollstaendigerNameValue;
  }

  public String getVollstaendigerNameValue() {
     return this.vollstaendigerNameValue ;
  }

  public void setVollstaendigerNameNullFlavourDefiningCode(
      NullFlavour vollstaendigerNameNullFlavourDefiningCode) {
     this.vollstaendigerNameNullFlavourDefiningCode = vollstaendigerNameNullFlavourDefiningCode;
  }

  public NullFlavour getVollstaendigerNameNullFlavourDefiningCode() {
     return this.vollstaendigerNameNullFlavourDefiningCode ;
  }

  public void setFamiliennameValue(String familiennameValue) {
     this.familiennameValue = familiennameValue;
  }

  public String getFamiliennameValue() {
     return this.familiennameValue ;
  }

  public void setFamiliennameNullFlavourDefiningCode(
      NullFlavour familiennameNullFlavourDefiningCode) {
     this.familiennameNullFlavourDefiningCode = familiennameNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNullFlavourDefiningCode() {
     return this.familiennameNullFlavourDefiningCode ;
  }

  public void setFamiliennameNamenszusatzValue(String familiennameNamenszusatzValue) {
     this.familiennameNamenszusatzValue = familiennameNamenszusatzValue;
  }

  public String getFamiliennameNamenszusatzValue() {
     return this.familiennameNamenszusatzValue ;
  }

  public void setFamiliennameNamenszusatzNullFlavourDefiningCode(
      NullFlavour familiennameNamenszusatzNullFlavourDefiningCode) {
     this.familiennameNamenszusatzNullFlavourDefiningCode = familiennameNamenszusatzNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNamenszusatzNullFlavourDefiningCode() {
     return this.familiennameNamenszusatzNullFlavourDefiningCode ;
  }

  public void setFamiliennameNachnameValue(String familiennameNachnameValue) {
     this.familiennameNachnameValue = familiennameNachnameValue;
  }

  public String getFamiliennameNachnameValue() {
     return this.familiennameNachnameValue ;
  }

  public void setFamiliennameNachnameNullFlavourDefiningCode(
      NullFlavour familiennameNachnameNullFlavourDefiningCode) {
     this.familiennameNachnameNullFlavourDefiningCode = familiennameNachnameNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameNachnameNullFlavourDefiningCode() {
     return this.familiennameNachnameNullFlavourDefiningCode ;
  }

  public void setFamiliennameVorsatzwortValue(String familiennameVorsatzwortValue) {
     this.familiennameVorsatzwortValue = familiennameVorsatzwortValue;
  }

  public String getFamiliennameVorsatzwortValue() {
     return this.familiennameVorsatzwortValue ;
  }

  public void setFamiliennameVorsatzwortNullFlavourDefiningCode(
      NullFlavour familiennameVorsatzwortNullFlavourDefiningCode) {
     this.familiennameVorsatzwortNullFlavourDefiningCode = familiennameVorsatzwortNullFlavourDefiningCode;
  }

  public NullFlavour getFamiliennameVorsatzwortNullFlavourDefiningCode() {
     return this.familiennameVorsatzwortNullFlavourDefiningCode ;
  }

  public void setSuffix(List<GeburtsnameSuffixElement> suffix) {
     this.suffix = suffix;
  }

  public List<GeburtsnameSuffixElement> getSuffix() {
     return this.suffix ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
