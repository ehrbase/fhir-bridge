package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AceHemmerObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AceHemmerObservationConverter extends GeccoMedikationObservationConverter<AceHemmerObservation> {

    @Override
    protected AceHemmerObservation convertInternal(MedicationStatement resource) {
        AceHemmerObservation aceHemmerObservation = new AceHemmerObservation();
        for (Coding coding: resource.getMedicationCodeableConcept().getCoding()){
            if(coding.getSystem().equals("http://fhir.de/CodeSystem/dimdi/atc")){
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(aceHemmerObservation::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(aceHemmerObservation::setGrund);
        return aceHemmerObservation;
    }

}
