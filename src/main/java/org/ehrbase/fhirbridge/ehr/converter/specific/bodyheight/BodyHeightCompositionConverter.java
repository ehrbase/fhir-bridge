package org.ehrbase.fhirbridge.ehr.converter.specific.bodyheight;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.KoerpergroesseComposition;
import org.ehrbase.fhirbridge.ehr.opt.koerpergroessecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class BodyHeightCompositionConverter extends ObservationToCompositionConverter<KoerpergroesseComposition> {

    @Override
    public KoerpergroesseComposition convertInternal(@NonNull Observation resource) {
        KoerpergroesseComposition composition = new KoerpergroesseComposition();
        mapStatus(composition, resource);
        composition.setGroesseLaenge(new GroesseLaengeObservationConverter().convert(resource));
        return composition;
    }

    private void mapStatus(KoerpergroesseComposition composition, Observation obs) {
        String status = obs.getStatusElement().getCode();
        if (status.equals(StatusDefiningCode.FINAL.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.FINAL);
        } else if (status.equals(StatusDefiningCode.GEAENDERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.GEAENDERT);
        } else if (status.equals(StatusDefiningCode.REGISTRIERT.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.REGISTRIERT);
        } else if (status.equals(StatusDefiningCode.VORLAEUFIG.getValue())) {
            composition.setStatusDefiningCode(StatusDefiningCode.VORLAEUFIG);
        } else {
            throw new ConversionException("The status " + obs.getStatus().toString() + " is not valid for body height.");
        }
    }
}
