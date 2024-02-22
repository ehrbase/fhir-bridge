package org.ehrbase.fhirbridge.ehr.converter.specific.mibikultur;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.MikrobiologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.mikrobiologischerbefundcomposition.definition.FallidentifikationCluster;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;


public class MibiKulturNachweisConverter extends ObservationToCompositionConverter<MikrobiologischerBefundComposition> {

    @Override
    protected MikrobiologischerBefundComposition convertInternal(Observation resource) {
        MikrobiologischerBefundComposition mikrobiologischerBefundComposition = new MikrobiologischerBefundComposition();
        mikrobiologischerBefundComposition.setStatusValue("Endbefund");
        mikrobiologischerBefundComposition.setBerichtIdNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
        if(resource.hasEncounter()){
            mikrobiologischerBefundComposition.setFallidentifikation(mapFallidentifikation(resource.getEncounter()));
        }else{
            FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
            fallidentifikationCluster.setFallKennungNullFlavourDefiningCode(NullFlavour.NO_INFORMATION);
            mikrobiologischerBefundComposition.setFallidentifikation(fallidentifikationCluster);
        }
        mikrobiologischerBefundComposition.setBefund(new MibiBefundConverter().convert(resource));
        return mikrobiologischerBefundComposition;
    }

    private FallidentifikationCluster mapFallidentifikation(Reference encounter) {
        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
        fallidentifikationCluster.setFallKennungValue(encounter.getIdentifier().getValue());
        return fallidentifikationCluster;
    }

}
