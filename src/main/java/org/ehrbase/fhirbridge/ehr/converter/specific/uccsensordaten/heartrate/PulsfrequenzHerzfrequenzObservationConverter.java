package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.heartrate;

import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.UCCObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MethodeDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.PulsfrequenzHerzfrequenzObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

public class PulsfrequenzHerzfrequenzObservationConverter extends UCCObservationToObservationConverter<PulsfrequenzHerzfrequenzObservation> {

    @Override
    protected PulsfrequenzHerzfrequenzObservation convertInternal(Composition composition) {
        PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenzObservation = new PulsfrequenzHerzfrequenzObservation();
        pulsfrequenzHerzfrequenzObservation.setMethodeDefiningCode(MethodeDefiningCode.AUTOMATISCH_NICHT_INVASIV);
        mapEreignise(pulsfrequenzHerzfrequenzObservation, composition);
        return pulsfrequenzHerzfrequenzObservation;
    }

    private void mapEreignise(PulsfrequenzHerzfrequenzObservation pulsfrequenzHerzfrequenzObservation, Composition composition) {
        List<PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice> momentaneHerzfrequenzChoices = new ArrayList<>();
        for (Composition.SectionComponent section : composition.getSection()) {
            for (Coding coding : section.getCode().getCoding()) {
                if (coding.getCode().equals("vital-signs") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    mapPulseFrequenzErgebnis(section, momentaneHerzfrequenzChoices);
                }
            }
        }
        pulsfrequenzHerzfrequenzObservation.setMomentaneHerzfrequenz(momentaneHerzfrequenzChoices);
    }

    private void mapPulseFrequenzErgebnis(Composition.SectionComponent section, List<PulsfrequenzHerzfrequenzMomentaneHerzfrequenzChoice> momentaneHerzfrequenzChoices) {
        for(Reference entry: section.getEntry()){
            momentaneHerzfrequenzChoices.add(new MomentaneHerzfrequenzConverter().convert((Observation) entry.getResource()));
            addEventTimings(TimeConverter.convertObservationTime((Observation) entry.getResource()));
        }
    }
}
