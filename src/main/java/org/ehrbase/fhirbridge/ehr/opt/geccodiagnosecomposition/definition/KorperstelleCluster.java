package org.ehrbase.fhirbridge.ehr.opt.geccodiagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.anatomical_location.v1")
public class KorperstelleCluster {
  @Path("/items[at0053]")
  private List<Cluster> alternativeStruktur;

  @Path("/items[at0001]/value|defining_code")
  private NameDerKorperstelleDefiningcode nameDerKorperstelleDefiningcode;

  @Path("/items[at0054]")
  private List<Cluster> multimedialeDarstellung;

  @Path("/items[at0002]/value|defining_code")
  private LateralitatDefiningcode lateralitatDefiningcode;

  public void setAlternativeStruktur(List<Cluster> alternativeStruktur) {
     this.alternativeStruktur = alternativeStruktur;
  }

  public List<Cluster> getAlternativeStruktur() {
     return this.alternativeStruktur ;
  }

  public void setNameDerKorperstelleDefiningcode(
      NameDerKorperstelleDefiningcode nameDerKorperstelleDefiningcode) {
     this.nameDerKorperstelleDefiningcode = nameDerKorperstelleDefiningcode;
  }

  public NameDerKorperstelleDefiningcode getNameDerKorperstelleDefiningcode() {
     return this.nameDerKorperstelleDefiningcode ;
  }

  public void setMultimedialeDarstellung(List<Cluster> multimedialeDarstellung) {
     this.multimedialeDarstellung = multimedialeDarstellung;
  }

  public List<Cluster> getMultimedialeDarstellung() {
     return this.multimedialeDarstellung ;
  }

  public void setLateralitatDefiningcode(LateralitatDefiningcode lateralitatDefiningcode) {
     this.lateralitatDefiningcode = lateralitatDefiningcode;
  }

  public LateralitatDefiningcode getLateralitatDefiningcode() {
     return this.lateralitatDefiningcode ;
  }
}
