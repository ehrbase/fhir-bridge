package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-28T12:53:59.428963+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
public interface Covid19TherapieBeliebigesEreignisChoice {
  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  NullFlavour getArzneimittelNameNullFlavourDefiningCode();

  void setArzneimittelNameNullFlavourDefiningCode(
      NullFlavour arzneimittelNameNullFlavourDefiningCode);

  ArzneimittelNameDefiningCode getArzneimittelNameDefiningCode();

  void setArzneimittelNameDefiningCode(ArzneimittelNameDefiningCode arzneimittelNameDefiningCode);

  NullFlavour getGrundNullFlavourDefiningCode();

  void setGrundNullFlavourDefiningCode(NullFlavour grundNullFlavourDefiningCode);

  List<Cluster> getStrukturierteDosisUndZeitangaben();

  void setStrukturierteDosisUndZeitangaben(List<Cluster> strukturierteDosisUndZeitangaben);

  NullFlavour getStatusNullFlavourDefiningCode();

  void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode);

  GrundDefiningCode getGrundDefiningCode();

  void setGrundDefiningCode(GrundDefiningCode grundDefiningCode);

  StatusDefiningCode2 getStatusDefiningCode();

  void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode);

  List<Cluster> getHerstellungsdetails();

  void setHerstellungsdetails(List<Cluster> herstellungsdetails);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);
}
