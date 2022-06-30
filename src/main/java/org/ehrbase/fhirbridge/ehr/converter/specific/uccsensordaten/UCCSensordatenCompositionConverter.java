package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.heartrate.PulsfrequenzHerzfrequenzObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps.KoerperlicheAktivitaetHandyConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps.KoerperlicheAktivitaetUhrConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.UCCAppSensorDatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

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
                    uccAppSensorDatenComposition.setMitSensorGemesseneKoerperlicheAktivitaet(convertKoerperlicheAktivitaet(composition, section.getEntry()));
                }
            }
        }

    }

    private  List<MitSensorGemesseneKoerperlicheAktivitaetObservation> convertKoerperlicheAktivitaet(Composition composition, List<Reference> entries) {
        boolean hasNote = false;
        int handyConverterCount = 0;
        List<MitSensorGemesseneKoerperlicheAktivitaetObservation> koerperlicheAktivitaeten = new ArrayList<>();
        for (Reference entry : entries) {
            Observation observation = (Observation) entry.getResource(); //Always Observation
            if (observation.hasNote()) {
                hasNote = true;
            }else {
                handyConverterCount++;
            }

        }

        if(hasNote){
            koerperlicheAktivitaeten.add(new KoerperlicheAktivitaetHandyConverter().convert(composition));
        }
        if(handyConverterCount>0){
            koerperlicheAktivitaeten.add(new KoerperlicheAktivitaetUhrConverter().convert(composition));
        }
        return koerperlicheAktivitaeten;
    }
}