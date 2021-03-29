package org.ehrbase.fhirbridge.ehr.converter.specific.coronavirusnachweistest;

import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToEvaluationConverter;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.ErregernameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.hl7.fhir.r4.model.Observation;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

public class KennzeichnungErregernachweisEvaluationConverter extends ObservationToEvaluationConverter<KennzeichnungErregernachweisEvaluation> {
    final List<String> POSITIVE_RESULTS_LOINC_CODE = Arrays.asList(
            "33972-1",
            "33968-9",
            "33970-5",
            "33966-3",
            "33967-1",
            "33975-4",
            "33965-5",
            "33964-8",
            "41459-9",
            "42956-3",
            "41991-1",
            "60275-5",
            "60534-5",
            "41458-1",
            "94532-9"
    );

    @Override
    protected KennzeichnungErregernachweisEvaluation convertInternal(Observation resource) {
        String fhirCode = resource.getCode().getCoding().get(0).getCode();
        KennzeichnungErregernachweisEvaluation evaluation = new KennzeichnungErregernachweisEvaluation();
        evaluation.setZuletztAktualisiertValue(OffsetDateTime.now());
        evaluation.setZeitpunktDerKennzeichnungValue(convertTime(resource));
        boolean pathogenDetection = POSITIVE_RESULTS_LOINC_CODE.contains(fhirCode);
        evaluation.setErregernachweisValue(pathogenDetection); // FIXME: this needs to come from positive or negative result code from FHIR, I didn't validate the list of positive result codes.
        evaluation.setErregernachweisInDerKlinikValue(false); // FIXME: FHIR don't have enough data to know if the pathogen was detected in the clinic.
        // FIXME: Can't map with the code from FHIR since the code in the openEHR template is fixed
        ErregernameDefiningCode code = ErregernameDefiningCode.SARS_COV2; //new ErregernameDefiningcode(fhirValue, null, fhirTerminology, fhirCode);
        evaluation.setErregernameDefiningCode(code);
        return evaluation;
    }
}
