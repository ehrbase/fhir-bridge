package org.ehrbase.fhirbridge.fhir.observation.validator;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Specimen;
import org.openehealth.ipf.commons.ihe.fhir.FhirTransactionValidator;

import java.util.List;
import java.util.Map;

public class MibiKulturValidator implements FhirTransactionValidator {
    @Override
    public void validateRequest(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    @Override
    public void validateResponse(Object payload, Map<String, Object> map) {
        validate(payload, map);
    }

    public void validate(Object payload, Map<String, Object> map) {
        Observation observation = (Observation) payload;
        checkValuableCodeConcept(observation);
        checkStatus(observation);
        checkContained(observation);
        checkComponent(observation);
        checkEncounter(observation);
    }

    private void checkEncounter(Observation observation) {
        if (observation.hasEncounter()) {
            if (!observation.getEncounter().hasIdentifier()) {
                throw new UnprocessableEntityException("Encounter is missing identifier");
            } else if (!observation.getEncounter().getIdentifier().hasSystem() || !observation.getEncounter().getIdentifier().hasValue()) {
                throw new UnprocessableEntityException("Encounter is missing identifier.system and/or identifier.value");
            }
        } else {
            throw new UnprocessableEntityException("Encounter is missing");
        }
    }

    private void checkComponent(Observation observation) {
        if (observation.hasComponent()) {
            if (observation.getComponent().size() == 1) {
                if (!observation.getComponent().get(0).hasValueCodeableConcept()) {
                    throw new UnprocessableEntityException("Kultur component has to include a valueCodeableConcept!");
                }
            } else {
                throw new UnprocessableEntityException("Kultur has to include only one component not multiple");
            }
        } else {
            throw new UnprocessableEntityException("Kultur has to include a component!");
        }
    }

    private void checkValuableCodeConcept(Observation observation) {
        if (observation.hasValueCodeableConcept()) {
            if (!observation.getValueCodeableConcept().getCoding().get(0).getCode().equals("260373001")) {
                throw new UnprocessableEntityException("Only detected Kulturen are allowed!");
            }
        } else {
            throw new UnprocessableEntityException("Kultur has to include a ValueCodeableConcept with the Outcome of the Test!");

        }
    }

    private void checkStatus(Observation observation) {
        if (!observation.getStatus().equals(Observation.ObservationStatus.FINAL)) {
            throw new UnprocessableEntityException("For Status in Kultur only final is allowed");
        }
    }

    private void checkContained(Observation observation) {

        if (observation.hasContained()) {
            checkForExistenceOfMREMRGNAndEmpfindlichkeit(observation);
        } else {
            throw new UnprocessableEntityException("No contained resources provided, MRE/MRGN and Empfindlichkeit are missing.");
        }
        checkEmpfindlichkeit(observation);
        checkMREMRGNCoding(observation);
    }

    private void checkForExistenceOfMREMRGNAndEmpfindlichkeit(Observation observation) {
        boolean hasMREOrMRGN = false;
        boolean hasEmpfindlichkeit = false;
        boolean hasSpecimen = false;
        for (Resource resource : observation.getContained()) {
            if (resource.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-empfindlichkeit")) {
                hasEmpfindlichkeit = true;
            }
            if (resource.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mre-klasse") || resource.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mrgn-klasse")) {
                if (hasMREOrMRGN) {
                    throw new UnprocessableEntityException("Both MRGN and MRE are provided, has to be either one of those not both.");
                }
                hasMREOrMRGN = true;
            }
            if (resource.getMeta().getProfile().get(0).equals("http://hl7.org/fhir/StructureDefinition/Specimen")) {
                checkSpecimen((Specimen) resource);
                hasSpecimen = true;
            }
        }
        if (!hasMREOrMRGN) {
            throw new UnprocessableEntityException("Either MRE or MRGN is missing from the contained of Kultur.");
        }
        if (!hasEmpfindlichkeit) {
            throw new UnprocessableEntityException("Empfindlichkeit is missing from the contained of Kultur.");
        }
        if (!hasSpecimen) {
            throw new UnprocessableEntityException("Specimen is missing in Kultur.");
        }
    }

    private void checkSpecimen(Specimen resource) {
        if (resource.hasCollection()) {
            if (!resource.getCollection().hasCollectedDateTimeType() && !resource.getCollection().hasCollectedPeriod()) {
                throw new UnprocessableEntityException("Specimen is missing collection collected time.");
            }
        } else {
            throw new UnprocessableEntityException("Specimen is missing collection");
        }
    }

    private void checkMREMRGNCoding(Observation kultur) {
        for (Resource contained : kultur.getContained()) {
            if (contained.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mre-klasse")) {
                Observation mreKlassen = (Observation) contained;
                checkMRE(mreKlassen);
            }
            if (contained.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-mrgn-klasse")) {
                Observation mrgn = (Observation) contained;
                if (!mrgn.hasValueCodeableConcept()) {
                    throw new UnprocessableEntityException("Missing valueCodeableConcept in MRGN");
                }
            }
        }
    }

    private void checkMRE(Observation mreKlassen) {
        if (mreKlassen.hasValueCodeableConcept()) {
            if (!mreKlassen.getValueCodeableConcept().getCoding().get(0).getCode().equals("115329001") && !mreKlassen.getValueCodeableConcept().getCoding().get(0).getCode().equals("113727004")) {
                throw new UnprocessableEntityException("MRE contains coding that is not supported, has to be either SNOMED: 115329001 (Methicillin resistant Staphylococcus aureus ) or 113727004 (Vancomycin resistant Enterococcus ).");
            }
        } else {
            throw new UnprocessableEntityException("Missing valueCodeableConcept in MRE");
        }
    }

    private void checkEmpfindlichkeit(Observation kultur) {
        for (Resource contained : kultur.getContained()) {
            if (contained.getMeta().getProfile().get(0).equals("https://www.medizininformatik-initiative.de/fhir/modul-mikrobio/StructureDefinition/mii-pr-mikrobio-empfindlichkeit")) {
                Observation empfindlichkeit = (Observation) contained;
                if (!empfindlichkeit.getStatus().equals(Observation.ObservationStatus.FINAL)) {
                    throw new UnprocessableEntityException("For status of Empfindlichkeit only final is allowed");
                }
                if (empfindlichkeit.hasInterpretation()) {
                    checkInterpretationCodings(empfindlichkeit.getInterpretation());
                } else {
                    throw new UnprocessableEntityException("Interpretation missing in Empfindlichkeit");
                }
            }
        }
    }

    private void checkInterpretationCodings(List<CodeableConcept> interpretation) {
        for (CodeableConcept codeableConcept : interpretation) {
            if (!codeableConcept.getCoding().get(0).getSystem().equals("http://snomed.info/sct")) {
                throw new UnprocessableEntityException("Only EUCAST SNOMED is supported.");
            }
        }
    }
}