package org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

public class SchwangerschaftsstatusObservationConverter extends ObservationToObservationConverter<SchwangerschaftsstatusObservation> {
    @Override
    protected SchwangerschaftsstatusObservation convertInternal(Observation resource) {
        SchwangerschaftsstatusObservation schwangerschaftsstatusObservation = new SchwangerschaftsstatusObservation();
        convertStatus(resource, schwangerschaftsstatusObservation);
        return schwangerschaftsstatusObservation;
    }

    private void convertStatus(Observation resource, SchwangerschaftsstatusObservation schwangerschaftsstatusObservation) {
        if (resource.hasValueCodeableConcept() && resource.getValueCodeableConcept().hasCoding()) {
            schwangerschaftsstatusObservation.setStatusDefiningCode(mapStatus(resource));
        }else{
            schwangerschaftsstatusObservation.setStatusNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private StatusDefiningCode2 mapStatus(Observation resource) {
        for (Coding coding : resource.getValueCodeableConcept().getCoding()) {
            if (coding.hasCode()) {
                return convertStatusCode(coding);
            }
        }
        return StatusDefiningCode2.UNBEKANNT;
    }

    private StatusDefiningCode2 convertStatusCode(Coding coding) {
        if (coding.getSystem().equals(CodeSystem.SNOMED.getUrl())) {
            return convertStatusSnomed(coding);
        } else if (coding.getSystem().equals(CodeSystem.LOINC.getUrl())) {
            return convertStatusLoinc(coding);
        } else {
            // not accessible due to HAPI FHIR - therefore not tested
            throw new ConversionException("Code system is not of type LOINC and SNOMED " + coding.getCode() + " and therefore not supported");
        }
    }

    private StatusDefiningCode2 convertStatusLoinc(Coding coding) {
        if ("LA15173-0".equals(coding.getCode())) {
            return StatusDefiningCode2.SCHWANGER;
        } else if ("LA26683-5".equals(coding.getCode())) {
            return StatusDefiningCode2.NICHT_SCHWANGER;
        } else if ("LA4489-6".equals(coding.getCode())) {
            return StatusDefiningCode2.UNBEKANNT;
        } else {
            throw new ConversionException("Status code " + coding.getCode() + " is not supported");
        }
    }

    private StatusDefiningCode2 convertStatusSnomed(Coding coding) {
        if ("77386006".equals(coding.getCode())) {
            return StatusDefiningCode2.SCHWANGER;
        } else if ("60001007".equals(coding.getCode())) {
            return StatusDefiningCode2.NICHT_SCHWANGER;
        } else if ("261665006".equals(coding.getCode())) {
            return StatusDefiningCode2.UNBEKANNT;
        } else {
            // (not thrown as valid LOINC Code [1...1] is listed above invalid SNOMED code [0...1])
            throw new ConversionException("Status code " + coding.getCode() + " is not supported");
        }
    }

}
