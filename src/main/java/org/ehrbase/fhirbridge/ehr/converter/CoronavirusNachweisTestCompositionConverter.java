package org.ehrbase.fhirbridge.ehr.converter;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.fhirbridge.camel.component.ehr.composition.CompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition.KoerpertemperaturBeliebigesEreignisPointEvent;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.ErregernameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.ehrbase.fhirbridge.fhir.common.Profile;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CoronavirusNachweisTestCompositionConverter implements CompositionConverter<KennzeichnungErregernachweisSARSCoV2Composition, Observation> {

    private static final Logger LOG = LoggerFactory.getLogger(CoronavirusNachweisTestCompositionConverter.class);

    @Override
    public Observation fromComposition(KennzeichnungErregernachweisSARSCoV2Composition composition) {
        Observation result = new Observation();

        TemporalAccessor temporal;
        KoerpertemperaturBeliebigesEreignisPointEvent event;
        Coding coding;

        // evaluation time -> effective_time
        temporal = composition.getKennzeichnungErregernachweis().getZeitpunktDerKennzeichnungValue();
        result.getEffectiveDateTimeType().setValue(Date.from(Instant.from(temporal)));

        // FIXME: cant map the code back because the composition has a boolean derived from the code in the FHIR resource
        if (composition.getKennzeichnungErregernachweis().isErregernachweisValue()) {
            // This is not right, could not the value that came initially in the FHIR result
            coding = result.getCode().addCoding();
            coding.setSystem("http://loing.org");
            coding.setCode("94532-9");
            coding.setDisplay("SARS coronavirus+SARS-like coronavirus+SARS coronavirus 2+MERS coronavirus RNA [Presence] in Respiratory specimen by NAA with probe detection");
        }

        // set patient
        //result.getSubject().setReference("Patient/"+ subjectId.getValue());

        result.setStatus(Observation.ObservationStatus.FINAL);

        result.getMeta().addProfile(Profile.CORONARIRUS_NACHWEIS_TEST.getUri());
        // this is a workaround, might not work on all cases.
        result.setId(composition.getVersionUid().toString()); // workaround

        return result;
    }

    @Override
    public KennzeichnungErregernachweisSARSCoV2Composition toComposition(Observation observation) {
        if (observation == null) {
            return null;
        }
        KennzeichnungErregernachweisSARSCoV2Composition result = new KennzeichnungErregernachweisSARSCoV2Composition();
        FeederAudit fa = CommonData.constructFeederAudit(observation);
        result.setFeederAudit(fa);
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
        result.setLanguage(Language.EN);
        result.setLocation("test");
        result.setSettingDefiningCode(Setting.SECONDARY_MEDICAL_CARE);
        result.setTerritory(Territory.DE);
        result.setCategoryDefiningCode(Category.EVENT);
        result.setStartTimeValue(OffsetDateTime.now());
        result.setComposer(new PartySelf());

        return result;
    }
}