package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.UCCAppPRODatenComposition;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

public class UCCAppProDatenConverter extends CompositionToCompositionConverter<UCCAppPRODatenComposition> {

    @Override
    protected UCCAppPRODatenComposition convertInternal(Composition composition) {
        UCCAppPRODatenComposition uccAppPRODatenComposition = new UCCAppPRODatenComposition();
        mapEntries(uccAppPRODatenComposition, composition);
        return uccAppPRODatenComposition;
    }

    private void mapEntries(UCCAppPRODatenComposition uccAppPRODatenComposition, Composition composition) {
        for (Composition.SectionComponent section : composition.getSection()) {
            for (Coding coding : section.getCode().getCoding()) {
                if (coding.getCode().equals("vital-signs") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    for (Reference entry : section.getEntry()) {
                        mapEntry(uccAppPRODatenComposition, entry);
                    }
                }
            }
        }
    }

    private void mapEntry(UCCAppPRODatenComposition composition, Reference entry) {
        Observation observation = (Observation) entry.getResource();
        for (Coding coding : observation.getCode().getCoding()) {
            if (coding.getCode().equals("55284-4")) {
                mapBlutdruck(observation, composition);
            } else if (coding.getCode().equals("3141-9")) {
                mapKoerpergewicht(observation, composition);
            } else if (coding.getCode().equals("8867-4")) {
                mapPulsfrequenz(observation, composition);
            }
        }
    }

    private void mapBlutdruck(Observation observation, UCCAppPRODatenComposition uccAppPRODatenComposition) {
        uccAppPRODatenComposition.setBlutdruck(new BlutdruckObservationConverter().convert(observation));
    }

    private void mapKoerpergewicht(Observation observation, UCCAppPRODatenComposition uccAppPRODatenComposition) {
        uccAppPRODatenComposition.setKoerpergewicht(new KoerpergewichtConverter().convert(observation));
    }

    private void mapPulsfrequenz(Observation observation, UCCAppPRODatenComposition uccAppPRODatenComposition) {
        uccAppPRODatenComposition.setPulsfrequenzHerzfrequenz(new PulsfrequenzConverter().convert(observation));
    }

}
