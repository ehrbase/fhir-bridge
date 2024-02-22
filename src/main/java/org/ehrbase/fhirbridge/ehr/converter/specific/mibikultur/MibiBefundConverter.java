package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.*;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

public class MibiBefundConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        befundObservation.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.MICROBIOLOGY_STUDIES);
        mapKultur(resource, befundObservation);
        mapProbe(resource, befundObservation);
        return befundObservation;
    }

    private void mapProbe(Observation resource, BefundObservation befundObservation) {
        if(resource.hasSpecimen()){
            if(resource.getSpecimen().hasExtension() && !resource.getSpecimen().getExtension().get(0).getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
                ProbenConverter probenConverter = new ProbenConverter();
                befundObservation.setProbe(List.of(probenConverter.convert(resource.getSpecimenTarget())));
            }
        }
    }

    private void mapKultur(Observation resource, BefundObservation befundObservation) {
        KulturCluster kulturCluster = new KulturCluster();
        ProErregerCluster proErregerCluster = new ProErregerCluster();
        proErregerCluster.setNachweisValue("Nachweis");
        //kulturCluster.setProErreger();

    }

}
