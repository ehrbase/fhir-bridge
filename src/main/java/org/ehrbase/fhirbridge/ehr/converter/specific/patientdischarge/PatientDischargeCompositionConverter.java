package org.ehrbase.fhirbridge.ehr.converter.specific.patientdischarge;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.GECCOEntlassungsdatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccoentlassungsdatencomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PatientDischargeCompositionConverter extends ObservationToCompositionConverter<GECCOEntlassungsdatenComposition> {

    @Override
    public GECCOEntlassungsdatenComposition convertInternal(@NonNull Observation resource) {

        GECCOEntlassungsdatenComposition composition = new GECCOEntlassungsdatenComposition();

        mapStatus(composition, resource);

        composition.setEntlassungsart(new PatientDischargeAdminEntryConverter().convert(resource));

        return composition;
    }

    private void mapStatus(GECCOEntlassungsdatenComposition composition, Observation obs) {
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
            throw new ConversionException("The status " + obs.getStatus().toString() + " is not valid for known exposure.");
        }
    }
}
