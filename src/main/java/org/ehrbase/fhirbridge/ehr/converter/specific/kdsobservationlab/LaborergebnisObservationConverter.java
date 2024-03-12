package org.ehrbase.fhirbridge.ehr.converter.specific.kdsobservationlab;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.LaborbefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.LabortestKategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProLaboranalytCluster;
import org.ehrbase.fhirbridge.ehr.opt.kdslaborberichtcomposition.definition.ProbenmaterialCluster;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ResourceType;
import org.hl7.fhir.r4.model.Specimen;

import java.util.ArrayList;
import java.util.List;


public class LaborergebnisObservationConverter extends ObservationToObservationConverter<LaborbefundObservation> {

    private List<Observation> observationList;

    public LaborergebnisObservationConverter(List<Observation> observationList) {
        this.observationList = observationList;
    }


    @Override
    protected LaborbefundObservation convertInternal(Observation resource) {
        LaborbefundObservation laborergebnisObservation = new LaborbefundObservation();
        List<ProLaboranalytCluster> laboranalytList = new ArrayList<>();
        List<ProbenmaterialCluster> probenmaterialClusterList = new ArrayList<>();
        for (Observation observation : observationList) {
            ProLaboranalytCluster laboranalyt = new LaborAnalytConverter().convert(resource);
            laboranalytList.add(laboranalyt);
            for (Resource specimen : observation.getContained()) {
                if (specimen.getResourceType() == ResourceType.Specimen) {
                    probenmaterialClusterList.add(new SpecimenConverter().convert((Specimen) specimen));
                }
            }
        }
        laborergebnisObservation.setProLaboranalyt(laboranalytList);
        laborergebnisObservation.setProbenmaterial(probenmaterialClusterList);
        laborergebnisObservation.setLabortestKategorieDefiningCode(LabortestKategorieDefiningCode.LABORATORY);
        return laborergebnisObservation;
    }
}
