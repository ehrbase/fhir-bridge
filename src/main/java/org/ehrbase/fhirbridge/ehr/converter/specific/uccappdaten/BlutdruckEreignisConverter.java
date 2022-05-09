package org.ehrbase.fhirbridge.ehr.converter.specific.uccappdaten;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToPointEventConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.uccappprodatencomposition.definition.BloodPressureAnyEventPointEvent;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BlutdruckEreignisConverter extends ObservationToPointEventConverter<BloodPressureAnyEventPointEvent> {

    @Override
    protected BloodPressureAnyEventPointEvent convertInternal(Observation observation) {
        BloodPressureAnyEventPointEvent bloodPressureAnyEventPointEvent = new BloodPressureAnyEventPointEvent();
        setSystolicAndDiastolic(bloodPressureAnyEventPointEvent, observation);
        return bloodPressureAnyEventPointEvent;
    }

    private void setSystolicAndDiastolic(BloodPressureAnyEventPointEvent bloodPressure, Observation resource) {
        for (Observation.ObservationComponentComponent component:resource.getComponent()) {
            for(Coding coding:component.getCode().getCoding()){
                mapSystolicAndDiastolic(coding, bloodPressure, component);
            }
        }
    }

    private void mapSystolicAndDiastolic(Coding coding, BloodPressureAnyEventPointEvent bloodPressure, Observation.ObservationComponentComponent component) {
        if (coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && coding.getCode().equals("8480-6")){
            setSystolisch(component, bloodPressure);
        }else if(coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && coding.getCode().equals("8462-4")){
            setDiastolisch(component, bloodPressure);
        }
    }

    private void setSystolisch(Observation.ObservationComponentComponent component, BloodPressureAnyEventPointEvent bloodPressure) {
        if(component.hasValueQuantity()){
            getValue(component).ifPresent(bloodPressure::setSystolicMagnitude);
            getUnit(component).ifPresent(bloodPressure::setSystolicUnits);
        }else{
            bloodPressure.setSystolicNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private void setDiastolisch(Observation.ObservationComponentComponent component, BloodPressureAnyEventPointEvent bloodPressure) {
        if(component.hasValueQuantity()){
            getValue(component).ifPresent(bloodPressure::setDiastolicMagnitude);
            getUnit(component).ifPresent(bloodPressure::setDiastolicUnits);
        }else{
            bloodPressure.setDiastolicNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private Optional<Double> getValue(Observation.ObservationComponentComponent component) {
        if(component.hasValueQuantity() ){
            return Optional.of(component.getValueQuantity().getValue().doubleValue());
        }else{
            return Optional.empty();
        }
    }

    private Optional<String> getUnit(Observation.ObservationComponentComponent component) {
        if(component.hasValueQuantity() ){
            return Optional.of(component.getValueQuantity().getCode());
        }else{
            return Optional.empty();
        }
    }
}