package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Long;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-22T17:35:13.491738+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("INTERVAL_EVENT")
public class BefundJedesEreignisIntervalEvent implements IntervalEventEntity, BefundJedesEreignisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Bezeichnung
   * Description: Name der Laboruntersuchung, die an der/den Probe(n) durchgeführt wurde.
   * Comment: Ein Laborergebnis kann sich auf ein einzelnes Analyt oder eine Analytgruppe beziehen. Dazu zählen auch komplette Panel an Parametern. 
   * Es wird dringend empfohlen, die "Labortest-Bezeichnung" anhand einer Terminologie zu kodiereren, wie zum Beispiel LOINC oder SNOMED CT. Beispiel: "Glukose", "Harnstoff", "Abstrich", "Cortisol", "Leberbiopsie". Der Name kann u.U. auch das Probenmaterial oder den Patientenstatus (z.B. "Blutzuckerspiegel nüchtern") oder andere Informationen beinhalten wie "Kalium (Blutgas)".
   */
  @Path("/data[at0003]/items[at0005]/value|defining_code")
  private LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Tree/Labortest-Bezeichnung/null_flavour
   */
  @Path("/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour labortestBezeichnungNullFlavourDefiningCode;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Probendetail
   * Description: Angaben über die Beschaffenheit der analysierten Probe.
   * Comment: Wenn der Probentyp mit einem Code in der Testbezeichnung ausreichend spezifiziert ist, sind diese zusätzlichen Daten nicht erforderlich. Die Verknüpfung von Ergebnissen mit bestimmten Proben kann sowohl in einem CLUSTER.Probe als auch in den verschiedenen CLUSTER Archetypen mit Hilfe von Elementen mit der Bezeichnung "Probe" dokumentiert werden.
   */
  @Path("/data[at0003]/items[at0065]")
  private List<Cluster> probendetail;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel
   * Description: Laborergebnis als Panel/Profil von Einzelresultaten. Verbreitet im medizinischen Labor.
   */
  @Path("/data[at0003]/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]")
  private LabortestPanelCluster labortestPanel;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Strukturierte Testdiagnostik
   * Description: Eine strukturierte oder komplexe Diagnose für die Laboruntersuchung.
   * Comment: Zum Beispiel: Anatomische Pathologiediagnosen, die aus mehreren verschiedenen Schwerpunkten wie Morphologie, Ätiologie und Funktion zusammengesetzt sind.
   */
  @Path("/data[at0003]/items[at0122]")
  private List<Cluster> strukturierteTestdiagnostik;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Multimedia-Darstellung
   * Description: Bild, Video oder Diagramm zur Visualisierung des Testergebnisses.
   * Comment: Mehrere Formate sind erlaubt - diese sollten aber einen äquivalenten klinischen Inhalt darstellen.
   */
  @Path("/data[at0003]/items[at0118]")
  private List<Cluster> multimediaDarstellung;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Strukturierte Erfassung der Störfaktoren
   * Description: Einzelheiten zu Problemen oder Umständen, die sich auf die genaue Interpretation der Messung oder des Prüfergebnisses auswirken.
   * Comment: Zum Beispiel: Letzte normale Menstruationsperiode (LNMP).
   */
  @Path("/state[at0112]/items[at0114]")
  private List<Cluster> strukturierteErfassungDerStoerfaktoren;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  public void setLabortestBezeichnungDefiningCode(
      LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode) {
     this.labortestBezeichnungDefiningCode = labortestBezeichnungDefiningCode;
  }

  public LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode() {
     return this.labortestBezeichnungDefiningCode ;
  }

  public void setLabortestBezeichnungNullFlavourDefiningCode(
      NullFlavour labortestBezeichnungNullFlavourDefiningCode) {
     this.labortestBezeichnungNullFlavourDefiningCode = labortestBezeichnungNullFlavourDefiningCode;
  }

  public NullFlavour getLabortestBezeichnungNullFlavourDefiningCode() {
     return this.labortestBezeichnungNullFlavourDefiningCode ;
  }

  public void setProbendetail(List<Cluster> probendetail) {
     this.probendetail = probendetail;
  }

  public List<Cluster> getProbendetail() {
     return this.probendetail ;
  }

  public void setLabortestPanel(LabortestPanelCluster labortestPanel) {
     this.labortestPanel = labortestPanel;
  }

  public LabortestPanelCluster getLabortestPanel() {
     return this.labortestPanel ;
  }

  public void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik) {
     this.strukturierteTestdiagnostik = strukturierteTestdiagnostik;
  }

  public List<Cluster> getStrukturierteTestdiagnostik() {
     return this.strukturierteTestdiagnostik ;
  }

  public void setMultimediaDarstellung(List<Cluster> multimediaDarstellung) {
     this.multimediaDarstellung = multimediaDarstellung;
  }

  public List<Cluster> getMultimediaDarstellung() {
     return this.multimediaDarstellung ;
  }

  public void setStrukturierteErfassungDerStoerfaktoren(
      List<Cluster> strukturierteErfassungDerStoerfaktoren) {
     this.strukturierteErfassungDerStoerfaktoren = strukturierteErfassungDerStoerfaktoren;
  }

  public List<Cluster> getStrukturierteErfassungDerStoerfaktoren() {
     return this.strukturierteErfassungDerStoerfaktoren ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setSampleCount(Long sampleCount) {
     this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
     return this.sampleCount ;
  }
}
