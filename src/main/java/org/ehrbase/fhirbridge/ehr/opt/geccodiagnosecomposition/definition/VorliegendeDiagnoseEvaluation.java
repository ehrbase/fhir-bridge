package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
public class VorliegendeDiagnoseEvaluation {
  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  @Path("/data[at0001]/items[at0077]/value|value")
  private TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue;

  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1 and name/value='Körperstelle']")
  private List<KorperstelleCluster> korperstelle;

  @Path("/data[at0001]/items[at0005]/value|defining_code")
  private SchweregradDefiningcode schweregradDefiningcode;

  @Path("/data[at0001]/items[at0030]/value|value")
  private TemporalAccessor datumZeitpunktDerGenesungValue;

  @Path("/data[at0001]/items[at0069]/value|value")
  private String kommentarValue;

  @Path("/data[at0001]/items[at0002]/value|value")
  private String nameDesProblemsDerDiagnoseValue;

  @Path("/language")
  private Language language;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/data[at0001]/items[at0043]")
  private List<Cluster> spezifischeAngaben;

  public void setStatus(List<Cluster> status) {
     this.status = status;
  }

  public List<Cluster> getStatus() {
     return this.status ;
  }

  public void setDatumZeitpunktDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumZeitpunktDesAuftretensDerErstdiagnoseValue) {
     this.datumZeitpunktDesAuftretensDerErstdiagnoseValue = datumZeitpunktDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumZeitpunktDesAuftretensDerErstdiagnoseValue() {
     return this.datumZeitpunktDesAuftretensDerErstdiagnoseValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setKorperstelle(List<KorperstelleCluster> korperstelle) {
     this.korperstelle = korperstelle;
  }

  public List<KorperstelleCluster> getKorperstelle() {
     return this.korperstelle ;
  }

  public void setSchweregradDefiningcode(SchweregradDefiningcode schweregradDefiningcode) {
     this.schweregradDefiningcode = schweregradDefiningcode;
  }

  public SchweregradDefiningcode getSchweregradDefiningcode() {
     return this.schweregradDefiningcode ;
  }

  public void setDatumZeitpunktDerGenesungValue(TemporalAccessor datumZeitpunktDerGenesungValue) {
     this.datumZeitpunktDerGenesungValue = datumZeitpunktDerGenesungValue;
  }

  public TemporalAccessor getDatumZeitpunktDerGenesungValue() {
     return this.datumZeitpunktDerGenesungValue ;
  }

  public void setKommentarValue(String kommentarValue) {
     this.kommentarValue = kommentarValue;
  }

  public String getKommentarValue() {
     return this.kommentarValue ;
  }

  public void setNameDesProblemsDerDiagnoseValue(String nameDesProblemsDerDiagnoseValue) {
     this.nameDesProblemsDerDiagnoseValue = nameDesProblemsDerDiagnoseValue;
  }

  public String getNameDesProblemsDerDiagnoseValue() {
     return this.nameDesProblemsDerDiagnoseValue ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setSpezifischeAngaben(List<Cluster> spezifischeAngaben) {
     this.spezifischeAngaben = spezifischeAngaben;
  }

  public List<Cluster> getSpezifischeAngaben() {
     return this.spezifischeAngaben ;
  }
}
