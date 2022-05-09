package org.ehrbase.fhirbridge.ehr.converter.specific.sensordaten;


import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToIntervalEventConverter;
import org.ehrbase.fhirbridge.ehr.opt.uccappsensordatencomposition.definition.MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent;
import org.hl7.fhir.r4.model.Observation;

public class KoerperlicheAktivitaetEreignisIntervalEventConverter extends ObservationToIntervalEventConverter<MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent> {

    @Override
    protected MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent convertInternal(Observation observation) {
        MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent koerperlicheAktivitaetJedesEreignisIntervalEvent = new MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent();
        mapSteps(observation, koerperlicheAktivitaetJedesEreignisIntervalEvent);
        return koerperlicheAktivitaetJedesEreignisIntervalEvent;
    }

    private void mapSteps(Observation observation, MitSensorGemesseneKoerperlicheAktivitaetJedesEreignisIntervalEvent koerperlicheAktivitaetJedesEreignisIntervalEvent) {
        for(Observation.ObservationComponentComponent component: observation.getComponent()){
            if(component.hasValueQuantity() && component.hasCode()){
                koerperlicheAktivitaetJedesEreignisIntervalEvent.setAnzahlDerZurueckgelegtenSchritteMagnitude(component.getValueQuantity().getValue().longValue());
            }else{
                throw new ConversionException("ValueQuantity is missing!");
            }
        }
    }
}
