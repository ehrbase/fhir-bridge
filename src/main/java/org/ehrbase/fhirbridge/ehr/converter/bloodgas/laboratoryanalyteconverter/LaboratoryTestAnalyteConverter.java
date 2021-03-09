package org.ehrbase.fhirbridge.ehr.converter.bloodgas.laboratoryanalyteconverter;

import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.fhirbridge.ehr.opt.geccoradiologischerbefundcomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Observation;

abstract class LaboratoryTestAnalyteConverter {
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
                throw new IllegalStateException("Invalid Code " + fhirObservation.getStatusElement().getCode() + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    protected Double mapValue() {
        return fhirObservation.getValueQuantity().getValue().doubleValue();
    }

    abstract EnumValueSet mapUntersuchterAnalyt();

}

