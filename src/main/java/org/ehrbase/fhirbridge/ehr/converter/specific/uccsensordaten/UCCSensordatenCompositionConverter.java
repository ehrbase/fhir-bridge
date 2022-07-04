package org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.list.SetUniqueList;
import org.ehrbase.fhirbridge.ehr.converter.generic.CompositionToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.heartrate.PulsfrequenzHerzfrequenzObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps.KoerperlicheAktivitaetConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.uccsensordaten.steps.NoteDevice;
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

    private List<MitSensorGemesseneKoerperlicheAktivitaetObservation> convertKoerperlicheAktivitaet(Composition composition, List<Reference> entries) {
        List<MitSensorGemesseneKoerperlicheAktivitaetObservation> koerperlicheAktivitaeten = new ArrayList<>();
        List<String> deviceIds = new ArrayList<>();
        for (Reference entry : entries) {
            Observation observation = (Observation) entry.getResource(); //Always Observation
            if (observation.hasNote()) {
                if(!deviceIds.contains(getDeviceId(observation))){
                    deviceIds.add(getDeviceId(observation));
                }
            }
        }

        for (String deviceId : deviceIds) {
            koerperlicheAktivitaeten.add(new KoerperlicheAktivitaetConverter(deviceId).convert(composition));
        }
        return koerperlicheAktivitaeten;
    }

    String getDeviceId(Observation observation) {
        ObjectMapper mapper = new ObjectMapper();
        String x = observation.getNote().get(0).getText().replace("\\\"", "\"");
        NoteDevice noteDevice = null;
        try {
            noteDevice = mapper.readValue(x, NoteDevice.class);
            return noteDevice.getDevice_id();
        } catch (JsonProcessingException e) {
            throw new UnprocessableEntityException("Could not get DeviceId from Note");
        }
    }
}