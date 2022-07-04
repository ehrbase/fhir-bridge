package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ehrbase.fhirbridge.ehr.converter.generic.TimeConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.UCCObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Reference;

import java.util.ArrayList;
import java.util.List;

public class KoerperlicheAktivitaetConverter extends UCCObservationToObservationConverter<MitSensorGemesseneKoerperlicheAktivitaetObservation>{
    String deviceId;

    public KoerperlicheAktivitaetConverter(String deviceId) {
        this.deviceId = deviceId;
    }

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
                    convertEntries(section, koerperlicheAktivitaeten, koerperlicheAktivitaetObservation);
                }
            }
            koerperlicheAktivitaetObservation.setJedesEreignis(koerperlicheAktivitaeten);
        }
    }

    private void convertEntries(Composition.SectionComponent section, List<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisChoice> koerperlicheAktivitaeten, MitSensorGemesseneKoerperlicheAktivitaetObservation koerperlicheAktivitaetObservation) {
        for (Reference entry : section.getEntry()) {
            Observation observation = (Observation) entry.getResource(); //Always Observation
            if (observation.hasNote() && observation.getNote().get(0).getText().contains(deviceId)) {
                koerperlicheAktivitaeten.add(new KoerperlicheAktivitaetEreignisIntervalEventConverter().convert(observation));
                addEventTimings(TimeConverter.convertObservationTime((Observation) entry.getResource()));
                convertInformationAboutTheDevice(observation, koerperlicheAktivitaetObservation);
            }
        }
    }


    private void convertInformationAboutTheDevice(Observation entry, MitSensorGemesseneKoerperlicheAktivitaetObservation koerperlicheAktivitaetObservation) {
        ObjectMapper mapper = new ObjectMapper();
        String x = entry.getNote().get(0).getText().replace("\\\"", "\"");
        NoteDevice noteDevice = null;
        try {
            noteDevice = mapper.readValue(x, NoteDevice.class);
            koerperlicheAktivitaetObservation.setInformationenZuHardUndSoftwareValue(noteDevice.toString());
            koerperlicheAktivitaetObservation.setInformationenZuHardUndSoftwareValue(noteDevice.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}


