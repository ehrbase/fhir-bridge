package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzJedesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

public class PulsfrequenzHerzfrequenzToObservation extends CompositionToObservationConverter<PulsfrequenzHerzfrequenzObservation> {

    @Override
    protected PulsfrequenzHerzfrequenzObservation convertInternal(Composition composition) {
        PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenzObservation = new PulsfrequenzHerzfrequenzObservation();
        mapEreignise(pulsfrequenzHerzfrequenzObservation, composition);
        return pulsfrequenzHerzfrequenzObservation;
    }

    private void mapEreignise(PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenzObservation, Composition composition) {
        List<PulsfrequenzHerzfrequenzJedesEreignisChoice> pulsfrequenzHerzfrequenzJedesEreignisChoices = new ArrayList<>();
        for (Composition.SectionComponent section : composition.getSection()) {
            for (Coding coding : section.getCode().getCoding()) {
                if (coding.getCode().equals("vital-signs") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    mapPulseFrequenzErgebnis(section, pulsfrequenzHerzfrequenzJedesEreignisChoices);
                }
            }
        }
        pulsfrequenzHerzfrequenzObservation.setJedesEreignis(pulsfrequenzHerzfrequenzJedesEreignisChoices);

    }

    private void mapPulseFrequenzErgebnis(Composition.SectionComponent section, List<PulsfrequenzHerzfrequenzJedesEreignisChoice> pulsfrequenzHerzfrequenzJedesEreignisChoices) {
        for(Reference entry: section.getEntry()){
            pulsfrequenzHerzfrequenzJedesEreignisChoices.add(new HerzfrequenzJedesEreignisPointEventConverter().convert((Observation) entry.getResource()));
        }
    }
}
