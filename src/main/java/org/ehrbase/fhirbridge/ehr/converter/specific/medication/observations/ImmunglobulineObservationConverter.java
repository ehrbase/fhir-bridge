package org.ehrbase.fhirbridge.ehr.converter.specific.medication.observations;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.parser.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.converter.specific.medication.GeccoMedikationObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition.ImmunglobulineObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.MedicationStatement;

import java.util.Map;

public class ImmunglobulineObservationConverter extends GeccoMedikationObservationConverter<ImmunglobulineObservation> {
    @Override
    protected ImmunglobulineObservation convertInternal(MedicationStatement resource) {
        ImmunglobulineObservation immunglobulineObservation = new ImmunglobulineObservation();
        for (Coding coding: resource.getMedicationCodeableConcept().getCoding()){
            if(coding.getSystem().equals("http://fhir.de/CodeSystem/dimdi/atc")){
                DvCodedTextParser.parseFHIRCoding(coding).ifPresent(immunglobulineObservation::setArzneimittelName);
            }
        }
        getGrundDefiningCode(resource).ifPresent(immunglobulineObservation::setGrund);
        return immunglobulineObservation;
    }

}
