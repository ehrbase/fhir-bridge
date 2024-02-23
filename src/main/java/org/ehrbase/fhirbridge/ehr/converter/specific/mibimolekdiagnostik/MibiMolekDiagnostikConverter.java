package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.VirologischerBefundComposition;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.definition.FallidentifikationCluster;

import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

public class MibiMolekDiagnostikConverter extends ObservationToCompositionConverter<VirologischerBefundComposition> {
    @Override
    protected VirologischerBefundComposition convertInternal(Observation resource) {
        //Schau mal bisschen beim Virobefund package weiter unten gibt sowas Ähnlcihes schon nur andere resource
        // Du musst den SNOMED code 840533007 validieren (es darf nur dieser ausgefüllt werden) und den im template https://ckm.highmed.org/ckm/templates/1246.169.636
        // auf SARS-CoV-2 (COVID-19) RNA [Presence] in Respiratory system specimen by NAA with probe detection 94500-6 mappen
        // Für probe siehe Kultur kannst quasi ähnlcih machen.
        VirologischerBefundComposition virologischerBefundComposition = new VirologischerBefundComposition();
        //virologischerBefundComposition.setBerichtIdValue(resource.getIdentifier().get(0).getValue());
        virologischerBefundComposition.setFallidentifikation(mapFallidentifikation(resource.getEncounter()));
        virologischerBefundComposition.setBefund(new MibiMolekBefundConverter().convert(resource));
        return virologischerBefundComposition;
    }

    private FallidentifikationCluster mapFallidentifikation(Reference encounter) {
        FallidentifikationCluster fallidentifikationCluster = new FallidentifikationCluster();
        fallidentifikationCluster.setFallKennungValue(encounter.getIdentifier().getValue());
        return fallidentifikationCluster;
    }
}
