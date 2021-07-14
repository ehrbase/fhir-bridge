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
                throw new ConversionException("One contained Observation was expected, the contained is present but no observation is within. Instance: " + resource.getContained().get(0).getResourceType().toString());
            }
            Observation observation = (Observation) resource.getContained().get(0); //TODO fix one to many
            GECCOLaborbefundComposition result = new ObservationLabCompositionConverter().convert(observation);
            LaborergebnisObservation laborergebnisObservation = result.getLaborergebnis();
            injectSchlussfolgerung(laborergebnisObservation, resource);
            result.setLaborergebnis(laborergebnisObservation);
            return result;
        }else{
            throw new ConversionException("One contained Observation was expected " + resource.getContained().size() + " were received in DiagnosticReport " + resource.getId());
        }
    }

    private void injectSchlussfolgerung(LaborergebnisObservation laborergebnisObservation, DiagnosticReport resource) {
        if(resource.hasConclusion()){
            laborergebnisObservation.setSchlussfolgerungValue(resource.getConclusion());
        }
    }

}
