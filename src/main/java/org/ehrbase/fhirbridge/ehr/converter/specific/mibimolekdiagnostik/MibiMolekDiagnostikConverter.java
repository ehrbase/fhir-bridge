package org.ehrbase.fhirbridge.ehr.converter.specific.mibimolekdiagnostik;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.virologischerbefundcomposition.VirologischerBefundComposition;
import org.hl7.fhir.r4.model.Observation;

public class MibiMolekDiagnostikConverter extends ObservationToCompositionConverter<VirologischerBefundComposition> {
    @Override
    protected VirologischerBefundComposition convertInternal(Observation resource) {
        //Schau mal bisschen beim Virobefund package weiter unten gibt sowas Ähnlcihes schon nur andere resource
        // Du musst den SNOMED code 840533007 validieren (es darf nur dieser ausgefüllt werden) und den im template https://ckm.highmed.org/ckm/templates/1246.169.636
        // auf SARS-CoV-2 (COVID-19) RNA [Presence] in Respiratory system specimen by NAA with probe detection 94500-6 mappen
        // Für probe siehe Kultur kannst quasi ähnlcih machen.
        return null;
    }
}
