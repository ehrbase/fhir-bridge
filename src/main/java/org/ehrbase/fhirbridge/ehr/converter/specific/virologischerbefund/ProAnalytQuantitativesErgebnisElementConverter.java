package org.ehrbase.fhirbridge.ehr.converter.specific.virologischerbefund;

import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisChoice;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.ProAnalytQuantitativesErgebnisElement;
import org.hl7.fhir.r4.model.Observation;

import java.util.ArrayList;
import java.util.List;

public class ProAnalytQuantitativesErgebnisElementConverter {

    public ProAnalytQuantitativesErgebnisElement convert(Observation observation){

        ProAnalytQuantitativesErgebnisElement proAnalytQuantitativesErgebnisElement = new ProAnalytQuantitativesErgebnisElement();
        ProAnalytQuantitativesErgebnisChoice proAnalytQuantitativesErgebnisChoice ;
        /**
         *  Unit is 1..1 so observation.getValueQuantity().hasUnit() should be true. The other option is still implemented.
         */
        if (observation.getValueQuantity().hasUnit()){
            proAnalytQuantitativesErgebnisChoice = new ProAnalytQuantitativesErgebnisChoiceConverter().convertDvQuantity(observation);
        }else {
            proAnalytQuantitativesErgebnisChoice = new ProAnalytQuantitativesErgebnisChoiceConverter().convertDvCount(observation);
        }
        proAnalytQuantitativesErgebnisElement.setValue2(proAnalytQuantitativesErgebnisChoice);

        return proAnalytQuantitativesErgebnisElement;
    }

}
