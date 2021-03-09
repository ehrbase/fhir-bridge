package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.ErregernameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;


public class CoronavirusNachweisTestCompositionConverter extends AbstractCompositionConverter<Observation, KennzeichnungErregernachweisSARSCoV2Composition> {

    @Override
    public KennzeichnungErregernachweisSARSCoV2Composition convert(@NotNull Observation observation) {

        KennzeichnungErregernachweisSARSCoV2Composition result = new KennzeichnungErregernachweisSARSCoV2Composition();

        List<String> positiveResultLoincCodes = Arrays.asList(
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

        // ========================================================================================
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();
        String fhirValue = observation.getCode().getCoding().get(0).getDisplay(); // FIXME: the code and value should be assigned to the pathogen name node in the Compo, but the template binds just one specific value there (hardcoded)
        String fhirCode = observation.getCode().getCoding().get(0).getCode();
        String fhirTerminology = observation.getCode().getCoding().get(0).getSystem();
        KennzeichnungErregernachweisEvaluation evaluation = new KennzeichnungErregernachweisEvaluation();
        evaluation.setLanguage(Language.DE);
        evaluation.setSubject(new PartySelf());
        evaluation.setZuletztAktualisiertValue(OffsetDateTime.now());
        evaluation.setZeitpunktDerKennzeichnungValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        boolean pathogenDetection = positiveResultLoincCodes.contains(fhirCode);
        evaluation.setErregernachweisValue(pathogenDetection); // FIXME: this needs to come from positive or negative result code from FHIR, I didn't validate the list of positive result codes.

        evaluation.setErregernachweisInDerKlinikValue(false); // FIXME: FHIR don't have enough data to know if the pathogen was detected in the clinic.

        // FIXME: Can't map with the code from FHIR since the code in the openEHR template is fixed
        ErregernameDefiningCode code = ErregernameDefiningCode.SARS_COV2; //new ErregernameDefiningcode(fhirValue, null, fhirTerminology, fhirCode);
        evaluation.setErregernameDefiningCode(code);

        result.setKennzeichnungErregernachweis(evaluation);

        // ======================================================================================
        // Required fields by API
        result.setStartTimeValue(OffsetDateTime.now());

        return result;
    }
}
