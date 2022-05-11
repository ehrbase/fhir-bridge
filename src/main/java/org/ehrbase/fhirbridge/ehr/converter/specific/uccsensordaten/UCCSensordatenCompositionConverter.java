package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten;

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
                    uccAppSensorDatenComposition.setPulsfrequenzHerzfrequenz(new PulsfrequenzHerzfrequenzObservationConverter().convert(composition));
                }
                if (coding.getCode().equals("activity") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    uccAppSensorDatenComposition.setMitSensorGemesseneKoerperlicheAktivitaet(new KoerperlicheAktivitaetConverter().convert(composition));
                }
            }
        }

    }
}