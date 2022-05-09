package org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-05-09T13:01:54.580023755+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.19.0-SNAPSHOT"
)
public interface KoerpergewichtBeliebigesEreignisChoice {
  NullFlavour getGewichtNullFlavourDefiningCode();

  void setGewichtNullFlavourDefiningCode(NullFlavour gewichtNullFlavourDefiningCode);

  ItemTree getStateStructure();

  void setStateStructure(ItemTree stateStructure);

  String getGewichtUnits();

  void setGewichtUnits(String gewichtUnits);

  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);

  Double getGewichtMagnitude();

  void setGewichtMagnitude(Double gewichtMagnitude);
}
