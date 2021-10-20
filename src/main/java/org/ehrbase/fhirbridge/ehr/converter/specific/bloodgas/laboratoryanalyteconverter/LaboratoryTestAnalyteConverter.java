package org.ehrbase.fhirbridge.ehr.converter.specific.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;
import org.ehrbase.fhirbridge.ehr.converter.InvalidStatusCodeException;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;

abstract class LaboratoryTestAnalyteConverter<L extends LocatableEntity> {
    protected final Observation fhirObservation;

    protected LaboratoryTestAnalyteConverter(Observation fhirObservation) {
        this.fhirObservation = fhirObservation;
    }

    protected String mapErgebnisStatus() {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningCode.REGISTRIERT.getValue();
            case "final":
                return StatusDefiningCode.FINAL.getValue();
            case "amended":
                return StatusDefiningCode.GEAENDERT.getValue();
            case "preliminary":
                return StatusDefiningCode.VORLAEUFIG.getValue();
            default:
              throw new InvalidStatusCodeException(fhirObservation.getStatusElement().getCode());
        }
    }

    protected Double mapValue() {
        return fhirObservation.getValueQuantity().getValue().doubleValue();
    }

    abstract void convertAnalytErgebnis(L locatableEntity);

}

