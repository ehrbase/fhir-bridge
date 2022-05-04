package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.UCCAppSensorDatenComposition;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;

public class UCCSensordatenCompositionConverter extends CompositionToCompositionConverter<UCCAppSensorDatenComposition> {

    @Override
    protected UCCAppSensorDatenComposition convertInternal(Composition composition) {
        UCCAppSensorDatenComposition uccAppSensorDatenComposition = new UCCAppSensorDatenComposition();
        convertSections(uccAppSensorDatenComposition, composition);
        return uccAppSensorDatenComposition;
    }

    private void convertSections(UCCAppSensorDatenComposition uccAppSensorDatenComposition, Composition composition) {
        for (Composition.SectionComponent section : composition.getSection()) {
            for (Coding coding : section.getCode().getCoding()) {
                if (coding.getCode().equals("vital-signs") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    System.out.println("placeholder");
                    //     uccAppSensorDatenComposition.setPulsfrequenzHerzfrequenz(new PulsfrequenzHerzfrequenzToObservation().convert());
                //   uccAppSensorDatenComposition.setPulsfrequenzHerzfrequenz();
                }
            }
        }

    }
}