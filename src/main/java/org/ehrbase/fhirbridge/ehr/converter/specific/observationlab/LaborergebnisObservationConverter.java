package org.ehrbase.fhirbridge.ehr.converter.specific.observationlab;

import org.ehrbase.fhirbridge.ehr.converter.DvCodedTextParser;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.LaborergebnisObservation;
import org.ehrbase.fhirbridge.ehr.opt.geccolaborbefundcomposition.definition.ProLaboranalytCluster;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Specimen;

import java.util.List;


public class LaborergebnisObservationConverter extends ObservationToObservationConverter<LaborergebnisObservation> {

    @Override
    protected LaborergebnisObservation convertInternal(Observation resource) {
        LaborergebnisObservation laborergebnisObservation = new LaborergebnisObservation();
        List<ProLaboranalytCluster> laboranalytList = new LaborAnalytConverter().convert(resource);
        setKategorieValue(laborergebnisObservation, resource);
        setProbe(resource, laborergebnisObservation);
        laborergebnisObservation.setProLaboranalyt(laboranalytList);
        return laborergebnisObservation;
    }

    private void setProbe(Observation resource, LaborergebnisObservation laborergebnisObservation) {
        if (resource.hasSpecimen()) {
            for (Resource containedResource : resource.getContained()) {
                if (containedResource.getResourceType() == ResourceType.Specimen) {
                    laborergebnisObservation.setProbe(List.of(new SpecimenConverter().convert((Specimen) containedResource)));
                }
            }
        }
    }

    private void setKategorieValue(LaborergebnisObservation observation, Observation resource) {
        for (CodeableConcept codeableConcept : resource.getCategory()) {
            convertKategorieValue(codeableConcept, observation);
        }
    }

    private void convertKategorieValue(CodeableConcept codeableConcept, LaborergebnisObservation observation) {
        DvCodedTextParser.getInstance()
                .parseFHIRCoding(codeableConcept.getCodingFirstRep())
                .ifPresent(observation::setLabortestKategorie);
    }
}
