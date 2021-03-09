package org.ehrbase.fhirbridge.ehr.converter;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.OffsetDateTime;

public class PregnancyStatusCompositionConverter extends AbstractCompositionConverter<Observation, SchwangerschaftsstatusComposition> {

    @Override
    public SchwangerschaftsstatusComposition convert(@NonNull Observation observation) {
        SchwangerschaftsstatusComposition result = new SchwangerschaftsstatusComposition();

        // map start time
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        result.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // FIXME: map other context status
        // Can't map because of https://github.com/ehrbase/openEHR_SDK/issues/84

        result.setSchwangerschaftsstatus(mapObservation(observation));


        // ======================================================================================
        // Required fields by API
        result.setStartTimeValue(OffsetDateTime.now());

        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(observation.getPerformer().get(0).getReference());
        composer.addIdentifier(identifier);
        result.setComposer(composer);

        return result;
    }

    private SchwangerschaftsstatusObservation mapObservation(Observation observation) {
        SchwangerschaftsstatusObservation result = new SchwangerschaftsstatusObservation();

        // mandatory fields
        result.setSubject(new PartySelf());
        result.setLanguage(Language.DE);

        // map origin/time
        DateTimeType fhirEffectiveDateTime = observation.getEffectiveDateTimeType();

        result.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        result.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        // map result status
        if (!observation.getValueCodeableConcept().isEmpty() && !observation.getValueCodeableConcept().getCoding().isEmpty()) {

            Coding statusCode = observation.getValueCodeableConcept().getCoding().get(0);

            // TODO: this only considers LOINC cases
            switch (statusCode.getCode()) {
                case "LA15173-0": // pregnant
                    result.setStatusDefiningCode(StatusDefiningCode2.SCHWANGER);
                    break;
                case "LA26683-5": // not pregnant
                    result.setStatusDefiningCode(StatusDefiningCode2.NICHT_SCHWANGER);
                    break;
                case "LA4489-6": // unknown
                    result.setStatusDefiningCode(StatusDefiningCode2.UNBEKANNT);
                    break;
                default:
                    throw new UnprocessableEntityException("Status code " + statusCode.getCode() + " is not supported");
            }
        } else {
            result.setStatusDefiningCode(StatusDefiningCode2.UNBEKANNT);
        }

        return result;
    }

}
