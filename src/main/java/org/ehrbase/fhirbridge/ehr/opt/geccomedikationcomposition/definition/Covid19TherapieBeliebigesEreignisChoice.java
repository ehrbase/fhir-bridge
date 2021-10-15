package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T15:28:12.967215+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
public interface Covid19TherapieBeliebigesEreignisChoice {
  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  NullFlavour getArzneimittelNameNullFlavourDefiningCode();

  void setArzneimittelNameNullFlavourDefiningCode(
      NullFlavour arzneimittelNameNullFlavourDefiningCode);

  NullFlavour getGrundNullFlavourDefiningCode();

  void setGrundNullFlavourDefiningCode(NullFlavour grundNullFlavourDefiningCode);

  List<Cluster> getStrukturierteDosisUndZeitangaben();

  void setStrukturierteDosisUndZeitangaben(List<Cluster> strukturierteDosisUndZeitangaben);

  NullFlavour getStatusNullFlavourDefiningCode();

  void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode);

  StatusDefiningCode2 getStatusDefiningCode();

  void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode);

  List<Cluster> getHerstellungsdetails();

  void setHerstellungsdetails(List<Cluster> herstellungsdetails);

  DvCodedText getGrund();

  void setGrund(DvCodedText grund);

  DvCodedText getArzneimittelName();

  void setArzneimittelName(DvCodedText arzneimittelName);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);
}
