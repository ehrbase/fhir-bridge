package org.ehrbase.fhirbridge.ehr.converter.specific.coronavirusnachweistest;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.KennzeichnungErregernachweisSARSCoV2Composition;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.ErregernameDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.kennzeichnungerregernachweissarscov2composition.definition.KennzeichnungErregernachweisEvaluation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;


public class CoronavirusNachweisTestCompositionConverter extends ObservationToCompositionConverter<KennzeichnungErregernachweisSARSCoV2Composition> {

    @Override
    public KennzeichnungErregernachweisSARSCoV2Composition convertInternal(@NonNull Observation resource) {
        KennzeichnungErregernachweisSARSCoV2Composition composition = new KennzeichnungErregernachweisSARSCoV2Composition();
        composition.setKennzeichnungErregernachweis(new KennzeichnungErregernachweisEvaluationConverter().convert(resource));
        return composition;
    }
}
