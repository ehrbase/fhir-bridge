package org.ehrbase.fhirbridge.ehr.opt.geccopersonendatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.time.temporal.Temporal;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-CLUSTER.person_birth_data_iso.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-07-15T13:49:30.053562+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public class DatenZurGeburtCluster implements LocatableEntity {
  /**
   * Path: GECCO_Personendaten/Personendaten/Daten zur Geburt/Geburtsdatum
   * Description: Das Geburtsdatum der Person.
   */
  @Path("/items[at0001]/value|value")
  private Temporal geburtsdatumValue;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Daten zur Geburt/Geburtsdatum/null_flavour
   */
  @Path("/items[at0001]/null_flavour|defining_code")
  private NullFlavour geburtsdatumNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Daten zur Geburt/Kodierung für Mehrlingsgeburten
   * Description: Ein Indikator für Mehrlingsgeburten. Er gibt die Anzahl der geborenen Personen an, die aus einer Schwangerschaft resultierten.
   */
  @Path("/items[at0003 and name/value='Kodierung für Mehrlingsgeburten']/value")
  private DvCodedText kodierungFuerMehrlingsgeburten;

  /**
   * Path: GECCO_Personendaten/Personendaten/Baum/Daten zur Geburt/Kodierung für Mehrlingsgeburten/null_flavour
   */
  @Path("/items[at0003 and name/value='Kodierung für Mehrlingsgeburten']/null_flavour|defining_code")
  private NullFlavour kodierungFuerMehrlingsgeburtenNullFlavourDefiningCode;

  /**
   * Path: GECCO_Personendaten/Personendaten/Daten zur Geburt/Länderspezifische Daten
   * Description: Zusätzliche länderspezifische Daten die Geburt betreffend
   */
  @Path("/items[at0006]")
  private Cluster laenderspezifischeDaten;

  /**
   * Path: GECCO_Personendaten/Personendaten/Daten zur Geburt/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setGeburtsdatumValue(Temporal geburtsdatumValue) {
     this.geburtsdatumValue = geburtsdatumValue;
  }

  public Temporal getGeburtsdatumValue() {
     return this.geburtsdatumValue ;
  }

  public void setGeburtsdatumNullFlavourDefiningCode(
      NullFlavour geburtsdatumNullFlavourDefiningCode) {
     this.geburtsdatumNullFlavourDefiningCode = geburtsdatumNullFlavourDefiningCode;
  }

  public NullFlavour getGeburtsdatumNullFlavourDefiningCode() {
     return this.geburtsdatumNullFlavourDefiningCode ;
  }

  public void setKodierungFuerMehrlingsgeburten(DvCodedText kodierungFuerMehrlingsgeburten) {
     this.kodierungFuerMehrlingsgeburten = kodierungFuerMehrlingsgeburten;
  }

  public DvCodedText getKodierungFuerMehrlingsgeburten() {
     return this.kodierungFuerMehrlingsgeburten ;
  }

  public void setKodierungFuerMehrlingsgeburtenNullFlavourDefiningCode(
      NullFlavour kodierungFuerMehrlingsgeburtenNullFlavourDefiningCode) {
     this.kodierungFuerMehrlingsgeburtenNullFlavourDefiningCode = kodierungFuerMehrlingsgeburtenNullFlavourDefiningCode;
  }

  public NullFlavour getKodierungFuerMehrlingsgeburtenNullFlavourDefiningCode() {
     return this.kodierungFuerMehrlingsgeburtenNullFlavourDefiningCode ;
  }

  public void setLaenderspezifischeDaten(Cluster laenderspezifischeDaten) {
     this.laenderspezifischeDaten = laenderspezifischeDaten;
  }

  public Cluster getLaenderspezifischeDaten() {
     return this.laenderspezifischeDaten ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
