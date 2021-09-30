package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.Covid19TherapieObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class Covid19TherapieObservationConverter extends GeccoMedikationObservationConverter<Covid19TherapieObservation> {

    @Override
    protected Covid19TherapieObservation convertInternal(MedicationStatement resource) {
        Covid19TherapieObservation covid19TherapieObservation = new Covid19TherapieObservation();
        for (Coding coding: resource.getMedicationCodeableConcept().getCoding()){
            if(coding.getSystem().equals("http://fhir.de/CodeSystem/dimdi/atc")){
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(covid19TherapieObservation::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(covid19TherapieObservation::setGrund);
        return covid19TherapieObservation;
    }

}
