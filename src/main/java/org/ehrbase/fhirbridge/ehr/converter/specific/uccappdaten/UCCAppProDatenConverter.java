package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure.BloodPressureCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.UCCAppPRODatenComposition;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

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
        List<BlutdruckObservation> blutdruecke = new ArrayList<>();
        Observation observation = (Observation) entry.getResource();
        for (Coding coding : observation.getCode().getCoding()) {
            if (coding.getCode().equals("55284-4")) {
                mapBlutdruck(composition, observation, blutdruecke);
            } else if (coding.getCode().equals("3141-9")) {
                mapKoerpergewicht(composition, observation);
            } else if (coding.getCode().equals("8867-4")) {
                mapPulsfrequenz(composition, observation);
            }
        }

        if (blutdruecke.size() != 0) {
            composition.setBlutdruck(blutdruecke);
        }
    }

    private void mapBlutdruck(UCCAppPRODatenComposition uccAppPRODatenComposition, Observation observation, List<BlutdruckObservation> blutdruecke) {
        uccAppPRODatenComposition.setBlutdruck(new BloodPressureCompositionConverter().convert(observation));
    }

    private void mapKoerpergewicht(UCCAppPRODatenComposition uccAppPRODatenComposition, Observation composition) {
    }

    private void mapPulsfrequenz(UCCAppPRODatenComposition uccAppPRODatenComposition, Observation composition) {
    }

}
