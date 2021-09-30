package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.AntikoagulanzienObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

public class AntikoagulanzienObservationConverter extends GeccoMedikationObservationConverter<AntikoagulanzienObservation> {

    @Override
    protected AntikoagulanzienObservation convertInternal(MedicationStatement resource) {
        AntikoagulanzienObservation antikoagulanzienObservation = new AntikoagulanzienObservation();
        for (Coding coding: resource.getMedicationCodeableConcept().getCoding()){
            if(coding.getSystem().equals("http://fhir.de/CodeSystem/bfarm/atc")){
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(antikoagulanzienObservation::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(antikoagulanzienObservation::setGrund);
        return antikoagulanzienObservation;
    }

}
