package org.ehrbase.fhirbridge.fhir.observation.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.Map;

public class MibiMolekDiagnostikValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    private void validate(Object payload, Map<String, Object> map) {
        Observation observation = (Observation) payload;
        checkStatus(observation.getStatus());
        checkForSpecimen(observation);
        checkComponent(observation);
        checkValueCodeableConcept(observation);
    }

    private void checkValueCodeableConcept(Observation observation) {
        if (!observation.hasValueCodeableConcept()) {
            throw new UnprocessableEntityException("Molekulare Diagnostik has to include a ValueCodeableConcept.");
        }
    }

    private void checkComponent(Observation observation) {
        boolean containsMikroOrga = false;
        boolean containsPcr = false;
        for (Observation.ObservationComponentComponent component : observation.getComponent()) {
            if (component.getCode().getCoding().get(0).getCode().equals("89248-9")) {
                if (!component.getValueCodeableConcept().getCoding().get(0).getCode().equals("840533007")) {
                    throw new UnprocessableEntityException("Molekulare Diagnostik component NameMikroorganismus supports only code 840533007");
                }
                containsMikroOrga = true;
            }
            if (component.getCode().getCoding().get(0).getCode().equals("398545005")) {
                containsPcr = true;
            }
        }

        if (!containsMikroOrga || !containsPcr) {
            throw new UnprocessableEntityException("Components are missing from Molekulare Diagnostik");
        }
    }


    private void checkStatus(Observation.ObservationStatus status) {
        if (!status.equals(Observation.ObservationStatus.FINAL)) {
            throw new UnprocessableEntityException("For status in Molekulare Diagnostik only final is allowed");
        }
    }

    private void checkForSpecimen(Observation observation) {
        for (Resource resource : observation.getContained()) {
            if (resource.getMeta().getProfile().get(0).equals("http://hl7.org/fhir/StructureDefinition/Specimen")) {
                checkSpecimen((Specimen) resource);
            }
        }
    }

    private void checkSpecimen(Specimen resource) {
        if (resource.hasCollection()) {
            if (!resource.getCollection().hasCollectedDateTimeType() && !resource.getCollection().hasCollectedPeriod()) {
                throw new UnprocessableEntityException("Specimen is missing collection collected time.");
            }
        }else{
            throw new UnprocessableEntityException("Specimen is missing collection");
        }
        if (resource.hasType()) {
            if (!resource.getType().getCoding().get(0).getCode().equals("257261003") &&
                    !resource.getType().getCoding().get(0).getCode().equals("119334006") &&
                    !resource.getType().getCoding().get(0).getCode().equals("119342007") &&
                    !resource.getType().getCoding().get(0).getCode().equals("410580001") &&
                    !resource.getType().getCoding().get(0).getCode().equals("258607008")) {
                throw new UnprocessableEntityException("This type coding is not supported for specimen, check implementation guide.");

            }
        } else {
            throw new UnprocessableEntityException("Type is missing from specimen.");

        }
    }


}
