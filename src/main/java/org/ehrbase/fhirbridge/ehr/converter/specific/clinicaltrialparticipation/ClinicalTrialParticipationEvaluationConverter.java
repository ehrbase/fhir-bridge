package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.GeccoStudienteilnahmeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudienteilnahmeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungRegistrierungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.TitelDerStudiePruefungDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.RegisternameDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class ClinicalTrialParticipationEvaluationConverter extends ObservationToEvaluationConverter<GeccoStudienteilnahmeEvaluation> {

    @Override
    protected GeccoStudienteilnahmeEvaluation convertInternal(Observation resource) {

        GeccoStudienteilnahmeEvaluation geccoStudienteilnahmeEvaluation = new GeccoStudienteilnahmeEvaluation();

        String code_participated = getSnomedCodeObservation(resource);

        boolean hasStudy = false;

        switch(code_participated){
            case "373066001":
                geccoStudienteilnahmeEvaluation.setBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.YES_QUALIFIER_VALUE);
                hasStudy = true;
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

        if(hasStudy){

            StudienteilnahmeCluster studienteilnahmeCluster = new StudienteilnahmeCluster();
            geccoStudienteilnahmeEvaluation.setStudienteilnahme(studienteilnahmeCluster);

            StudiePruefungCluster studiePruefungCluster = new StudiePruefungCluster();
            studienteilnahmeCluster.setStudiePruefung(studiePruefungCluster);

            studiePruefungCluster.setTitelDerStudiePruefungDefiningCode(TitelDerStudiePruefungDefiningCode.PARTICIPATION_IN_INTERVENTIONAL_CLINICAL_TRIALS);

            if(!resource.getCode().getText().isEmpty()){
                studiePruefungCluster.setBeschreibungValue(resource.getCode().getText());
            }

            if(!resource.getComponent().isEmpty()){
                studiePruefungCluster.setRegistrierung(convertInternalEvents(resource));
            }
        }

        return geccoStudienteilnahmeEvaluation;
    }

    private List<StudiePruefungRegistrierungCluster> convertInternalEvents(Observation resource) {

        StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster = new StudiePruefungRegistrierungCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : resource.getComponent()) {

            if(observationComponent.getCode().getCoding().get(0).getCode().equals("04")) {
                studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.EUDRACT_NUMBER);
            }else if(observationComponent.getCode().getCoding().get(0).getCode().equals("05")) {
                studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.NCT_NUMBER);
            }else{
                throw new UnprocessableEntityException("value code " + observationComponent.getCode().getCoding().get(0).getCode() + " is not supported");
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