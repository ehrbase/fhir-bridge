package org.ehrbase.fhirbridge.ehr.converter.specific.diagnosticreportlab;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.DiagnosticReportToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.observationlab.ObservationLabCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.GECCOLaborbefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.ResourceType;

public class DiagnosticReportLabCompositionConverter extends DiagnosticReportToCompositionConverter<GECCOLaborbefundComposition> {

    @Override
    protected GECCOLaborbefundComposition convertInternal(DiagnosticReport resource) {
        if(resource.hasContained()){
            if (resource.getContained().get(0).getResourceType() != ResourceType.Observation) {
                throw new ConversionException("One contained Observation was expected, contained is there but is not Observation, it is " + resource.getContained().get(0).getResourceType().toString());
            }
            Observation observation = (Observation) resource.getContained().get(0); //TODO fix one to many
            GECCOLaborbefundComposition result = new ObservationLabCompositionConverter("asd").convert(observation);
            LaborergebnisObservation laborbefund = new LaborergebnisObservationConverter().convertInternal(resource);
            result.setLaborergebnis(laborbefund);
            return result;
        }else{
            throw new ConversionException("One contained Observation was expected " + resource.getContained().size() + " were received in DiagnosticReport " + resource.getId());
        }
    }

}
