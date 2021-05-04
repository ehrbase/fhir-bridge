package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.*;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class ClinicalTrialParticipationEvaluationConverter extends ObservationToEvaluationConverter<GeccoStudienteilnahmeEvaluation> {

    public GeccoStudienteilnahmeEvaluation convertInternal(Observation resource) {

        GeccoStudienteilnahmeEvaluation geccoStudienteilnahmeEvaluation = new GeccoStudienteilnahmeEvaluation();

        String code = getSnomedCodeObservation(resource);

        switch(code) {
            case "373066001":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.YES_QUALIFIER_VALUE);
                break;
            case "373067005":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.NO_QUALIFIER_VALUE);
                break;
            case "261665006":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.UNKNOWN_QUALIFIER_VALUE);
                break;
            case "74964007":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.OTHER_QUALIFIER_VALUE);
                break;
            case "385432009":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.NOT_APPLICABLE_QUALIFIER_VALUE);
                break;
            default:
                throw new UnprocessableEntityException("Value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }

        StudienteilnahmeCluster studienteilnahmeCluster = new StudienteilnahmeCluster();
        geccoStudienteilnahmeEvaluation.setStudienteilnahme(studienteilnahmeCluster);

        StudiePruefungCluster studiePruefungCluster = new StudiePruefungCluster();
        studienteilnahmeCluster.setStudiePruefung(studiePruefungCluster);

        studiePruefungCluster.setTitelDerStudiePruefungDefiningCode(TitelDerStudiePruefungDefiningCode.PARTICIPATION_IN_INTERVENTIONAL_CLINICAL_TRIALS);
        studiePruefungCluster.setBeschreibungValue(resource.getValueStringType().getValue());

        studiePruefungCluster.setRegistrierung(convertInternalEvents(resource));

        String code2 = getSnomedCodeObservation(resource);

        switch(code2) {
            case "373066001":
                studienteilnahmeCluster.setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.YES_QUALIFIER_VALUE);
                break;
            case "373067005":
                studienteilnahmeCluster.setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.NO_QUALIFIER_VALUE);
                break;
            case "261665006":
                studienteilnahmeCluster.setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.UNKNOWN_QUALIFIER_VALUE);
                break;
            case "74964007":
                studienteilnahmeCluster.setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.OTHER_QUALIFIER_VALUE);
                break;
            case "385432009":
                studienteilnahmeCluster.setBestaetigteCovid19DiagnoseAlsHauptursacheFuerAufnahmeInStudieDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.NOT_APPLICABLE_QUALIFIER_VALUE);
                break;
            default:
                throw new UnprocessableEntityException("Value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }

        return geccoStudienteilnahmeEvaluation;
    }

    private List<StudiePruefungRegistrierungCluster> convertInternalEvents(Observation resource) {

        StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster = new StudiePruefungRegistrierungCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : resource.getComponent()) {

            if(resource.getValueCodeableConcept().getCoding().get(0).getCode() == "05")
            {
                studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.NCT_NUMBER);
            }
            else if(resource.getValueCodeableConcept().getCoding().get(0).getCode() == "04")
            {
                studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.EUDRACT_NUMBER);
            }
            else
            {
                throw new UnprocessableEntityException("value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
            }

            studiePruefungRegistrierungCluster.setRegistrierungsnummerValue(observationComponent.getValueStringType().getValue());
        }

        return List.of(studiePruefungRegistrierungCluster);
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new UnprocessableEntityException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {
        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());
        return code.getCode();
    }
}