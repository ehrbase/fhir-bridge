package org.ehrbase.fhirbridge.ehr.converter.specificconverters.clinicalFrailty;

import org.ehrbase.fhirbridge.ehr.converter.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.hl7.fhir.r4.model.Observation;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter extends ObservationToCompositionConverter<KlinischeFrailtySkalaComposition> {

    @Override
    public KlinischeFrailtySkalaComposition convertInternal(Observation resource) {
        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        result.setKlinischeFrailtySkalaCfs(new KlinischeFrailtySkalaObservationConverter().convert(resource));
        //TODO refactor
        return result;
    }
}

