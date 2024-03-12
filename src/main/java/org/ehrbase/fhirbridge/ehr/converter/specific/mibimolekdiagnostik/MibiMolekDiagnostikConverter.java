package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

import org.hl7.fhir.r4.model.Observation;

public class MibiMolekDiagnostikConverter extends ObservationToCompositionConverter<VirologischerBefundComposition> {
    @Override
    protected VirologischerBefundComposition convertInternal(Observation resource) {
        VirologischerBefundComposition virologischerBefundComposition = new VirologischerBefundComposition();
        virologischerBefundComposition.setStatusValue("final");
        virologischerBefundComposition.setBerichtIdNullFlavourDefiningCode(NullFlavour.NOT_APPLICABLE);
        virologischerBefundComposition.setFallidentifikation(mapFallidentifikation(resource));
        virologischerBefundComposition.setBefund(new MibiMolekBefundConverter().convert(resource));
        return virologischerBefundComposition;
    }

    private FallidentifikationCluster mapFallidentifikation(Observation resource) {
        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
        if (resource.hasEncounter()) {
            fallidentifikationCluster.setFallKennungValue(resource.getEncounter().getIdentifier().getValue());
        } else {
            fallidentifikationCluster.setFallKennungNullFlavourDefiningCode(NullFlavour.NO_INFORMATION);
        }
        return fallidentifikationCluster;
    }
}
