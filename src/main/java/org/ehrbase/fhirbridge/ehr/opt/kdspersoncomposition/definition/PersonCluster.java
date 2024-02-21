package org.ehrbase.fhirbridge.ehr.opt.kdspersoncomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.person.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2024-02-21T15:03:27.123114619+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.18.0"
)
public class PersonCluster implements LocatableEntity {
  /**
   * Path: Person/Personendaten/Person/Name
   * Description: Getrennte Bestandteile des Namens einer Person.
   */
  @Path("/items[openEHR-EHR-CLUSTER.structured_name.v1 and name/value='Name']")
  private NameCluster name;

  /**
   * Path: Person/Personendaten/Person/Geburtsname
   * Description: Getrennte Bestandteile des Namens einer Person.
   */
  @Path("/items[openEHR-EHR-CLUSTER.structured_name.v1 and name/value='Geburtsname']")
  private GeburtsnameCluster geburtsname;

  /**
   * Path: Person/Personendaten/Person/Versicherten ID_GKV
   * Description: Mit der Person verbundener Identifikator.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als einer Identifikator dargestellt werden kann. Beachten Sie, dass der DV_IDENTIFIER-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält. Zum Beispiel - Sozialversicherungsnummer, Führerschein oder Passnummer.
   */
  @Path("/items[at0003 and name/value='Versicherten ID_GKV']/value")
  private DvIdentifier versichertenIdGkv;

  /**
   * Path: Person/Personendaten/Baum/Person/Versicherten ID_GKV/null_flavour
   */
  @Path("/items[at0003 and name/value='Versicherten ID_GKV']/null_flavour|defining_code")
  private NullFlavour versichertenIdGkvNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/PID
   * Description: Mit der Person verbundener Identifikator.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als einer Identifikator dargestellt werden kann. Beachten Sie, dass der DV_IDENTIFIER-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält. Zum Beispiel - Sozialversicherungsnummer, Führerschein oder Passnummer.
   */
  @Path("/items[at0003 and name/value='PID']")
  private List<PersonPidElement> pid;

  /**
   * Path: Person/Personendaten/Person/Versicherungsnummer PKV
   * Description: Mit der Person verbundener Identifikator.
   * Comment: Kardinalität für dieses Datenelement wird auf 0..* gesetzt, damit mehr als einer Identifikator dargestellt werden kann. Beachten Sie, dass der DV_IDENTIFIER-Datentyp mehrere Unterkomponenten zum Aufzeichnen des ID-Werts, -Typs, -Ausstellers und -Zuweisers enthält. Zum Beispiel - Sozialversicherungsnummer, Führerschein oder Passnummer.
   */
  @Path("/items[at0003 and name/value='Versicherungsnummer PKV']/value")
  private DvIdentifier versicherungsnummerPkv;

  /**
   * Path: Person/Personendaten/Baum/Person/Versicherungsnummer PKV/null_flavour
   */
  @Path("/items[at0003 and name/value='Versicherungsnummer PKV']/null_flavour|defining_code")
  private NullFlavour versicherungsnummerPkvNullFlavourDefiningCode;

  /**
   * Path: Person/Personendaten/Person/Straßenanschrift
   * Description: Informationen über den Standort einer Person, eines physischen Gebäudes oder einer Landmarke.
   */
  @Path("/items[openEHR-EHR-CLUSTER.address.v1 and name/value='Straßenanschrift']")
  private List<StrassenanschriftCluster> strassenanschrift;

  /**
   * Path: Person/Personendaten/Person/Postfach
   * Description: Informationen über den Standort einer Person, eines physischen Gebäudes oder einer Landmarke.
   */
  @Path("/items[openEHR-EHR-CLUSTER.address.v1 and name/value='Postfach']")
  private PostfachCluster postfach;

  /**
   * Path: Person/Personendaten/Person/Elektronische Kommunikation
   * Description: Angaben zu einer oder mehreren Arten der elektronischen Kommunikation für die Person.
   */
  @Path("/items[at0006]")
  private List<Cluster> elektronischeKommunikation;

  /**
   * Path: Person/Personendaten/Person/Verwaltungsorganisation
   * Description: Eine Einheit, die aus einer oder mehreren Personen besteht und ein bestimmtes Ziel hat.
   * Comment: Zum Beispiel: ein Unternehmen, eine Institution, ein Verband, ein Netzwerk, eine Abteilung, eine Gemeindegruppe, eine Gesundheitspraxisgruppe, ein Kostenträger/Versicherer, ein Pflegeteam oder eine Gruppe von Nachbarn, die Pflege oder Unterstützung leisten.
   */
  @Path("/items[openEHR-EHR-CLUSTER.organisation.v1 and name/value='Verwaltungsorganisation']")
  private VerwaltungsorganisationCluster verwaltungsorganisation;

  /**
   * Path: Person/Personendaten/Person/Zusätzliche Angaben
   * Description: Weitere Angaben zur Person.
   */
  @Path("/items[at0008]")
  private List<Cluster> zusaetzlicheAngaben;

  /**
   * Path: Person/Personendaten/Person/Foto
   * Description: Foto der Person.
   */
  @Path("/items[at0009]")
  private List<Cluster> foto;

  /**
   * Path: Person/Personendaten/Person/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setName(NameCluster name) {
     this.name = name;
  }

  public NameCluster getName() {
     return this.name ;
  }

  public void setGeburtsname(GeburtsnameCluster geburtsname) {
     this.geburtsname = geburtsname;
  }

  public GeburtsnameCluster getGeburtsname() {
     return this.geburtsname ;
  }

  public void setVersichertenIdGkv(DvIdentifier versichertenIdGkv) {
     this.versichertenIdGkv = versichertenIdGkv;
  }

  public DvIdentifier getVersichertenIdGkv() {
     return this.versichertenIdGkv ;
  }

  public void setVersichertenIdGkvNullFlavourDefiningCode(
      NullFlavour versichertenIdGkvNullFlavourDefiningCode) {
     this.versichertenIdGkvNullFlavourDefiningCode = versichertenIdGkvNullFlavourDefiningCode;
  }

  public NullFlavour getVersichertenIdGkvNullFlavourDefiningCode() {
     return this.versichertenIdGkvNullFlavourDefiningCode ;
  }

  public void setPid(List<PersonPidElement> pid) {
     this.pid = pid;
  }

  public List<PersonPidElement> getPid() {
     return this.pid ;
  }

  public void setVersicherungsnummerPkv(DvIdentifier versicherungsnummerPkv) {
     this.versicherungsnummerPkv = versicherungsnummerPkv;
  }

  public DvIdentifier getVersicherungsnummerPkv() {
     return this.versicherungsnummerPkv ;
  }

  public void setVersicherungsnummerPkvNullFlavourDefiningCode(
      NullFlavour versicherungsnummerPkvNullFlavourDefiningCode) {
     this.versicherungsnummerPkvNullFlavourDefiningCode = versicherungsnummerPkvNullFlavourDefiningCode;
  }

  public NullFlavour getVersicherungsnummerPkvNullFlavourDefiningCode() {
     return this.versicherungsnummerPkvNullFlavourDefiningCode ;
  }

  public void setStrassenanschrift(List<StrassenanschriftCluster> strassenanschrift) {
     this.strassenanschrift = strassenanschrift;
  }

  public List<StrassenanschriftCluster> getStrassenanschrift() {
     return this.strassenanschrift ;
  }

  public void setPostfach(PostfachCluster postfach) {
     this.postfach = postfach;
  }

  public PostfachCluster getPostfach() {
     return this.postfach ;
  }

  public void setElektronischeKommunikation(List<Cluster> elektronischeKommunikation) {
     this.elektronischeKommunikation = elektronischeKommunikation;
  }

  public List<Cluster> getElektronischeKommunikation() {
     return this.elektronischeKommunikation ;
  }

  public void setVerwaltungsorganisation(VerwaltungsorganisationCluster verwaltungsorganisation) {
     this.verwaltungsorganisation = verwaltungsorganisation;
  }

  public VerwaltungsorganisationCluster getVerwaltungsorganisation() {
     return this.verwaltungsorganisation ;
  }

  public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
     this.zusaetzlicheAngaben = zusaetzlicheAngaben;
  }

  public List<Cluster> getZusaetzlicheAngaben() {
     return this.zusaetzlicheAngaben ;
  }

  public void setFoto(List<Cluster> foto) {
     this.foto = foto;
  }

  public List<Cluster> getFoto() {
     return this.foto ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
