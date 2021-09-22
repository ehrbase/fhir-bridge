package org.ehrbase.fhirbridge.ehr.converter.specific.bloodpressure;

import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem;
import org.ehrbase.fhirbridge.ehr.opt.blutdruckcomposition.definition.BlutdruckObservation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BlutdruckObservationConverter extends ObservationToObservationConverter<BlutdruckObservation> {
    @Override
    protected BlutdruckObservation convertInternal(Observation resource) {
        BlutdruckObservation bloodPressure = new BlutdruckObservation();
        setSystolicAndDiastolic(bloodPressure, resource);
        return bloodPressure;
    }

    private void setSystolicAndDiastolic(BlutdruckObservation bloodPressure, Observation resource) {
        for (Observation.ObservationComponentComponent component:resource.getComponent()) {
            for(Coding coding:component.getCode().getCoding()){
                mapSystolicAndDiastolic(coding, bloodPressure, component);
            }
        }
    }

    private void mapSystolicAndDiastolic(Coding coding, BlutdruckObservation bloodPressure, Observation.ObservationComponentComponent component) {
        if (coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && coding.getCode().equals("8480-6")){
            setSystolisch(component, bloodPressure);
        }else if(coding.getSystem().equals(CodeSystem.LOINC.getUrl()) && coding.getCode().equals("8462-4")){
            setDiastolisch(component, bloodPressure);
        }
    }

    private void setSystolisch(Observation.ObservationComponentComponent component, BlutdruckObservation bloodPressure) {
        if(component.hasValueQuantity()){
            getValue(component).ifPresent(bloodPressure::setSystolischMagnitude);
            getUnit(component).ifPresent(bloodPressure::setSystolischUnits);
        }else{
            bloodPressure.setSystolischNullFlavourDefiningCode(NullFlavour.UNKNOWN);
        }
    }

    private void setDiastolisch(Observation.ObservationComponentComponent component, BlutdruckObservation bloodPressure) {
        if(component.hasValueQuantity()){
            getValue(component).ifPresent(bloodPressure::setDiastolischMagnitude);
            getUnit(component).ifPresent(bloodPressure::setDiastolischUnits);
        }else{
            bloodPressure.setDiastolischNullFlavourDefiningCode(NullFlavour.UNKNOWN);
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

