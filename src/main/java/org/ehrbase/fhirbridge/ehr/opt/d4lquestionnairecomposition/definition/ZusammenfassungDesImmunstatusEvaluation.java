package org.ehrbase.fhirbridge.ehr.opt.d4lquestionnairecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-EVALUATION.immunisation_summary.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-18T10:30:38.836771+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.0.0"
)
public class ZusammenfassungDesImmunstatusEvaluation implements EntryEntity {
  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Infektionskrankheit oder Erreger
   * Description: Identifizierung der Infektionskrankheit oder des Erregers.
   * Comment: Es kann mehrere Krankheiten oder Erreger geben, die zusammen geimpft werden - zum Beispiel: Diptherie, Tetanus und Keuchhusten oder Masern, Mumps und Röteln.
   */
  @Path("/data[at0001]/items[at0002 and name/value='Infektionskrankheit oder Erreger']")
  private List<ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement> infektionskrankheitOderErreger;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Datum der letzten Auffrischungsimpfung
   * Description: Das Datum, an dem die letzte Auffrischungsimpfung verabreicht wurde.
   */
  @Path("/data[at0001]/items[at0009]/value|value")
  private TemporalAccessor datumDerLetztenAuffrischungsimpfungValue;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Tree/Datum der letzten Auffrischungsimpfung/null_flavour
   */
  @Path("/data[at0001]/items[at0009]/null_flavour|defining_code")
  private NullFlavour datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Immunsstatus
   * Description: Eine Aussage darüber, ob der Impfstatus aktuell ist.
   */
  @Path("/data[at0001]/items[at0010 and name/value='Immunsstatus']/value|defining_code")
  private ImmunsstatusDefiningCode immunsstatusDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Tree/Immunsstatus/null_flavour
   */
  @Path("/data[at0001]/items[at0010 and name/value='Immunsstatus']/null_flavour|defining_code")
  private NullFlavour immunsstatusNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Haben Sie sich im Zeitraum vom 1. August 2020 bis heute gegen Grippe impfen lassen?
   * Description: Zusätzliche Beschreibung der Zusammenfassung des Immunstatus für eine identifizierte Infektionskrankheit oder Erreger, die nicht in anderen Feldern erfasst wurde.
   */
  @Path("/data[at0001]/items[at0016 and name/value='Haben Sie sich im Zeitraum vom 1. August 2020 bis heute gegen Grippe impfen lassen?']/value|defining_code")
  private AelterOderGleich65JahreAltDefiningCode habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Tree/Haben Sie sich im Zeitraum vom 1. August 2020 bis heute gegen Grippe impfen lassen?/null_flavour
   */
  @Path("/data[at0001]/items[at0016 and name/value='Haben Sie sich im Zeitraum vom 1. August 2020 bis heute gegen Grippe impfen lassen?']/null_flavour|defining_code")
  private NullFlavour habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/Tree
   * Description: @ internal @
   */
  @Path("/protocol[at0013]")
  private ItemTree tree;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/subject
   */
  @Path("/subject")
  private PartyProxy subject;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: Selbstüberwachung/Medikamente / Impfungen/Zusammenfassung des Immunstatus/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setInfektionskrankheitOderErreger(
      List<ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement> infektionskrankheitOderErreger) {
     this.infektionskrankheitOderErreger = infektionskrankheitOderErreger;
  }

  public List<ZusammenfassungDesImmunstatusInfektionskrankheitOderErregerElement> getInfektionskrankheitOderErreger(
      ) {
     return this.infektionskrankheitOderErreger ;
  }

  public void setDatumDerLetztenAuffrischungsimpfungValue(
      TemporalAccessor datumDerLetztenAuffrischungsimpfungValue) {
     this.datumDerLetztenAuffrischungsimpfungValue = datumDerLetztenAuffrischungsimpfungValue;
  }

  public TemporalAccessor getDatumDerLetztenAuffrischungsimpfungValue() {
     return this.datumDerLetztenAuffrischungsimpfungValue ;
  }

  public void setDatumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode(
      NullFlavour datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode) {
     this.datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode = datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode;
  }

  public NullFlavour getDatumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode() {
     return this.datumDerLetztenAuffrischungsimpfungNullFlavourDefiningCode ;
  }

  public void setImmunsstatusDefiningCode(ImmunsstatusDefiningCode immunsstatusDefiningCode) {
     this.immunsstatusDefiningCode = immunsstatusDefiningCode;
  }

  public ImmunsstatusDefiningCode getImmunsstatusDefiningCode() {
     return this.immunsstatusDefiningCode ;
  }

  public void setImmunsstatusNullFlavourDefiningCode(
      NullFlavour immunsstatusNullFlavourDefiningCode) {
     this.immunsstatusNullFlavourDefiningCode = immunsstatusNullFlavourDefiningCode;
  }

  public NullFlavour getImmunsstatusNullFlavourDefiningCode() {
     return this.immunsstatusNullFlavourDefiningCode ;
  }

  public void setHabenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode(
      AelterOderGleich65JahreAltDefiningCode habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode) {
     this.habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode = habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode;
  }

  public AelterOderGleich65JahreAltDefiningCode getHabenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode(
      ) {
     return this.habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenDefiningCode ;
  }

  public void setHabenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode(
      NullFlavour habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode) {
     this.habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode = habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode;
  }

  public NullFlavour getHabenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode(
      ) {
     return this.habenSieSichImZeitraumVom1August2020BisHeuteGegenGrippeImpfenLassenNullFlavourDefiningCode ;
  }

  public void setTree(ItemTree tree) {
     this.tree = tree;
  }

  public ItemTree getTree() {
     return this.tree ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
