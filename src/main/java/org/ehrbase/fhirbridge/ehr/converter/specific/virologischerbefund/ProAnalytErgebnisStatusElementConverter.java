package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusElement;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytErgebnisStatusChoice;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class ProAnalytErgebnisStatusElementConverter {

    public ProAnalytErgebnisStatusElement convert(Observation observation){

        ProAnalytErgebnisStatusElement proAnalytErgebnisStatusElement = new ProAnalytErgebnisStatusElement();
        ProAnalytErgebnisStatusChoice  proAnalytErgebnisStatusChoice ;

        if(!observation.getStatus().equals(Observation.ObservationStatus.UNKNOWN)){
            proAnalytErgebnisStatusChoice = new ProAnalytErgebnisStatusChoiceConverter().convertDvCodedText(observation);
        }else{
            proAnalytErgebnisStatusChoice = new ProAnalytErgebnisStatusChoiceConverter().convertDvText(observation);
        }

        proAnalytErgebnisStatusElement.setValue2(proAnalytErgebnisStatusChoice);

        return proAnalytErgebnisStatusElement;

    }

}
