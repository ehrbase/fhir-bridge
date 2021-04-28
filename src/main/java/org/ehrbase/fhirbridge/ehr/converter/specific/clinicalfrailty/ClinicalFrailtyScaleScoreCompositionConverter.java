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
        mapStatus(result, resource);
        result.setKlinischeFrailtySkalaCfs(new KlinischeFrailtySkalaObservationConverter().convert(resource));
        return result;
    }

    private void mapStatus(KlinischeFrailtySkalaComposition composition, Observation resource) {
        String status = resource.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + resource.getStatus().toString() + " is not valid for clinical frailty.");
        }
    }

}

