package org.ehrbase.fhirbridge.ehr.converter.specific.clinicalfrailty;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter extends ObservationToCompositionConverter<KlinischeFrailtySkalaComposition> {

    @Override
    public KlinischeFrailtySkalaComposition convertInternal(Observation resource) {
        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        if(resource.hasValueCodeableConcept()){
            result.setKlinischeFrailtySkalaCfs(new KlinischeFrailtySkalaObservationConverter().convert(resource));
        }
        return result;
    }

}

