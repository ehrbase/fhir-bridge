package org.ehrbase.fhirbridge.ehr.converter;

import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConversionException;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.sofacomposition.SOFAComposition;
import org.hl7.fhir.r4.model.Observation;

public class TravelHistoryCompositionConverter implements CompositionConverter<ReisehistorieComposition, Observation>
{

    @Override
    public Observation fromComposition(ReisehistorieComposition composition) throws CompositionConversionException {
        //your mapping code
        // return the mapped Observation of body temp
        return null;
    }

    @Override
    public ReisehistorieComposition toComposition(Observation object) throws CompositionConversionException {
        // your mapping code
        // return the mapped KoerpertemperaturComposition of Koerpertemperatur
        return null;
    }
}
