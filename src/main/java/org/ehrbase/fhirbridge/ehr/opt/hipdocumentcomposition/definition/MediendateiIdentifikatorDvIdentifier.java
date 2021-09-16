package org.ehrbase.fhirbridge.ehr.opt.hipdocumentcomposition.definition;

import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-08-24T22:02:30.843694+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("DV_IDENTIFIER")
public class MediendateiIdentifikatorDvIdentifier implements RMEntity, MediendateiIdentifikatorChoice {
  /**
   * Path: Bericht/context/Mediendatei/Identifikator/Identifikator
   * Description: Eindeutige ID für die Mediendatei.
   */
  @Path("")
  private List<DvIdentifier> identifikator;

  public void setIdentifikator(List<DvIdentifier> identifikator) {
     this.identifikator = identifikator;
  }

  public List<DvIdentifier> getIdentifikator() {
     return this.identifikator ;
  }
}
