package org.ehrbase.fhirbridge.ehr.converter.specific.clinicaltrialparticipation;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.GeccoStudienteilnahmeEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.RegisternameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudienteilnahmeCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.StudiePruefungRegistrierungCluster;
import org.ehrbase.fhirbridge.ehr.opt.geccostudienteilnahmecomposition.definition.TitelDerStudiePruefungDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class ClinicalTrialParticipationEvaluationConverter extends ObservationToEvaluationConverter<GeccoStudienteilnahmeEvaluation> {

    @Override
    protected GeccoStudienteilnahmeEvaluation convertInternal(Observation resource) {
        GeccoStudienteilnahmeEvaluation geccoStudienteilnahmeEvaluation = new GeccoStudienteilnahmeEvaluation();
        mapParticipated(geccoStudienteilnahmeEvaluation, resource);

        if(geccoStudienteilnahmeEvaluation.getBereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode().equals(BereitsAnInterventionellenKlinischenStudienTeilgenommenDefiningCode.YES_QUALIFIER_VALUE)) {
            geccoStudienteilnahmeEvaluation.setStudienteilnahme(createStudyCluster(resource));
        }

        return geccoStudienteilnahmeEvaluation;
    }

    private void mapParticipated(GeccoStudienteilnahmeEvaluation geccoStudienteilnahmeEvaluation, Observation resource) {
        String codeParticipated = getSnomedCodeObservation(resource);

        switch(codeParticipated){
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
                throw new ConversionException("Value code " + resource.getValueCodeableConcept().getCoding().get(0).getCode() + " is not supported");
        }
    }

    private StudienteilnahmeCluster createStudyCluster(Observation resource){
        StudienteilnahmeCluster studienteilnahmeCluster = new StudienteilnahmeCluster();
        StudiePruefungCluster studiePruefungCluster = new StudiePruefungCluster();
        studienteilnahmeCluster.setStudiePruefung(studiePruefungCluster);
        studiePruefungCluster.setTitelDerStudiePruefungDefiningCode(TitelDerStudiePruefungDefiningCode.PARTICIPATION_IN_INTERVENTIONAL_CLINICAL_TRIALS);

        if(resource.getCode().hasText()) {
            studiePruefungCluster.setBeschreibungValue(resource.getCode().getText());
        }

        if(resource.hasComponent()) {
            studiePruefungCluster.setRegistrierung(createRegistryCluster(resource));
        }

        return studienteilnahmeCluster;
    }

    private List<StudiePruefungRegistrierungCluster> createRegistryCluster(Observation resource) {
        StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster = new StudiePruefungRegistrierungCluster();

        for (Observation.ObservationComponentComponent observationComponent
                : resource.getComponent()) {
            mapRegistername(observationComponent, studiePruefungRegistrierungCluster);
            studiePruefungRegistrierungCluster.setRegistrierungsnummerValue(observationComponent.getValueStringType().getValue());
        }
        return List.of(studiePruefungRegistrierungCluster);
    }

    private void mapRegistername(Observation.ObservationComponentComponent observationComponent, StudiePruefungRegistrierungCluster studiePruefungRegistrierungCluster) {
        if(observationComponent.getCode().getCoding().get(0).getCode().equals("04")) {
            studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.EUDRACT_NUMBER);
        }else if(observationComponent.getCode().getCoding().get(0).getCode().equals("05")) {
            studiePruefungRegistrierungCluster.setRegisternameDefiningCode(RegisternameDefiningCode.NCT_NUMBER);
        }else{
            throw new ConversionException("value code " + observationComponent.getCode().getCoding().get(0).getCode() + " is not supported");
        }
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {
        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());
        return code.getCode();
    }
}