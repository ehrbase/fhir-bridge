package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

public class KoerperlicheAktivitaetToObservation extends CompositionToObservationConverter<MitSensorGemesseneKoerperlicheAktivitaetObservation> {
    @Override
    protected MitSensorGemesseneKoerperlicheAktivitaetObservation convertInternal(Composition composition) {
        MitSensorGemesseneKoerperlicheAktivitaetObservation koerperlicheAktivitaetObservation = new MitSensorGemesseneKoerperlicheAktivitaetObservation();
        mapEreignise(koerperlicheAktivitaetObservation, composition);
        return koerperlicheAktivitaetObservation;
    }

    private void mapEreignise(MitSensorGemesseneKoerperlicheAktivitaetObservation koerperlicheAktivitaetObservation, Composition composition) {
        List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> koerperlicheAktivitaeten = new ArrayList<>();
        for (Composition.SectionComponent section : composition.getSection()) {
            for (Coding coding : section.getCode().getCoding()) {
                if (coding.getCode().equals("activity") && coding.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category")) {
                    mapKoerperlicheAktivitaetErgebnis(section, koerperlicheAktivitaeten);
                }
            }
        }
        koerperlicheAktivitaetObservation.setJedesEreignis(koerperlicheAktivitaeten);
    }

    private void mapKoerperlicheAktivitaetErgebnis(Composition.SectionComponent section, List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> koerperlicheAktivitaeten){
        for(Reference entry: section.getEntry()){
            koerperlicheAktivitaeten.add(new KoerperlicheAktivitaetEreignisIntervalEventConverter().convert((Observation) entry.getResource()));
        }
    }
}
