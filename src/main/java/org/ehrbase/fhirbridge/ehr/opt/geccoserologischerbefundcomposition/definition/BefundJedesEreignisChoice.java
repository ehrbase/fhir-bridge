package org.ehrbase.fhirbridge.ehr.opt.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-07T15:42:10.519813+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public interface BefundJedesEreignisChoice {
  NullFlavour getLabortestBezeichnungNullFlavourDefiningCode();

  void setLabortestBezeichnungNullFlavourDefiningCode(
      NullFlavour labortestBezeichnungNullFlavourDefiningCode);

  List<Cluster> getProbendetail();

  void setProbendetail(List<Cluster> probendetail);

  List<Cluster> getStrukturierteErfassungDerStoerfaktoren();

  void setStrukturierteErfassungDerStoerfaktoren(
      List<Cluster> strukturierteErfassungDerStoerfaktoren);

  LabortestBezeichnungDefiningCode getLabortestBezeichnungDefiningCode();

  void setLabortestBezeichnungDefiningCode(
      LabortestBezeichnungDefiningCode labortestBezeichnungDefiningCode);

  List<Cluster> getMultimediaDarstellung();

  void setMultimediaDarstellung(List<Cluster> multimediaDarstellung);

  List<Cluster> getStrukturierteTestdiagnostik();

  void setStrukturierteTestdiagnostik(List<Cluster> strukturierteTestdiagnostik);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  LabortestPanelCluster getLabortestPanel();

  void setLabortestPanel(LabortestPanelCluster labortestPanel);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);
}
