package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-28T12:53:59.537835+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public interface AntikoagulanzienBeliebigesEreignisChoice {
  List<Cluster> getHerstellungsdetails();

  void setHerstellungsdetails(List<Cluster> herstellungsdetails);

  List<Cluster> getStrukturierteDosisUndZeitangaben();

  void setStrukturierteDosisUndZeitangaben(List<Cluster> strukturierteDosisUndZeitangaben);

  NullFlavour getStatusNullFlavourDefiningCode();

  void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode);

  GrundDefiningCode getGrundDefiningCode();

  void setGrundDefiningCode(GrundDefiningCode grundDefiningCode);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  ArzneimittelNameDefiningCode4 getArzneimittelNameDefiningCode();

  void setArzneimittelNameDefiningCode(ArzneimittelNameDefiningCode4 arzneimittelNameDefiningCode);

  NullFlavour getGrundNullFlavourDefiningCode();

  void setGrundNullFlavourDefiningCode(NullFlavour grundNullFlavourDefiningCode);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  StatusDefiningCode2 getStatusDefiningCode();

  void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode);

  NullFlavour getArzneimittelNameNullFlavourDefiningCode();

  void setArzneimittelNameNullFlavourDefiningCode(
      NullFlavour arzneimittelNameNullFlavourDefiningCode);
}
