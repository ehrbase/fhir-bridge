package org.ehrbase.fhirbridge.ehr.converter.clinicalFrailty;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.AbstractCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.KlinischeFrailtySkalaComposition;
import org.ehrbase.fhirbridge.ehr.opt.klinischefrailtyskalacomposition.definition.KlinischeFrailtySkalaCfsObservation;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

/**
 * FHIR 2 openEHR - respiration rate
 */
public class ClinicalFrailtyScaleScoreCompositionConverter extends AbstractCompositionConverter<Observation, KlinischeFrailtySkalaComposition> {

    @Override
    public KlinischeFrailtySkalaComposition convert(@NonNull Observation observation) {
        KlinischeFrailtySkalaComposition result = new KlinischeFrailtySkalaComposition();
        mapCommonAttributes(observation, result);
        result.setKlinischeFrailtySkalaCfs(new KlinischeFrailtySkalaObservationConverter().convert(observation));
        //TODO refactor
        result.setStartTimeValue((observation.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime()));
        return result;
    }

}

