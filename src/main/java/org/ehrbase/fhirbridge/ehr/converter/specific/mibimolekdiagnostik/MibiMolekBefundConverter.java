package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundJedesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.LabortestBezeichnungDefiningCode;
import org.hl7.fhir.r4.model.Observation;

public class MibiMolekBefundConverter extends ObservationToObservationConverter<BefundObservation> {

    @Override
    protected BefundObservation convertInternal(Observation resource) {
        BefundObservation befundObservation = new BefundObservation();
        BefundJedesEreignisPointEvent befundJedesEreignisPointEvent = new BefundJedesEreignisPointEvent();
        befundJedesEreignisPointEvent.setLabortestBezeichnungDefiningCode(LabortestBezeichnungDefiningCode.DETECTION_OF_VIRUS_PROCEDURE);
        mapProbe(resource, befundJedesEreignisPointEvent);
        return befundObservation;
    }

    private void mapProbe(Observation resource, BefundJedesEreignisPointEvent befundJedesEreignisPointEvent) {
        if(resource.hasSpecimen()){
            if(resource.getSpecimen().hasExtension() && !resource.getSpecimen().getExtension().get(0).getUrl().equals("http://hl7.org/fhir/StructureDefinition/data-absent-reason")) {
                ProbenConverter probenConverter = new ProbenConverter();
                befundJedesEreignisPointEvent.setProbe(probenConverter.convert(resource.getSpecimenTarget()));
            }
        }
    }



}
