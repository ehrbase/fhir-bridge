package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.MikrobiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.FallidentifikationCluster;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.List;
import java.util.Optional;


public class MibiKulturConverter extends ObservationToCompositionConverter<MikrobiologischerBefundComposition> {

    @Override
    protected MikrobiologischerBefundComposition convertInternal(Observation resource) {
        MikrobiologischerBefundComposition mikrobiologischerBefundComposition = new MikrobiologischerBefundComposition();
        mikrobiologischerBefundComposition.setStatusValue("Endbefund");
        mikrobiologischerBefundComposition.setBerichtIdValue(resource.getIdentifier().get(0).getValue());
        mikrobiologischerBefundComposition.setFallidentifikation(mapFallidentifikation(resource.getEncounter()));
        mikrobiologischerBefundComposition.setBefund(new MibiBefundConverter().convert(resource));
        return mikrobiologischerBefundComposition;
    }

    private FallidentifikationCluster mapFallidentifikation(Reference encounter) {
        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
        fallidentifikationCluster.setFallKennungValue(encounter.getIdentifier().getValue());
        return fallidentifikationCluster;
    }

}
