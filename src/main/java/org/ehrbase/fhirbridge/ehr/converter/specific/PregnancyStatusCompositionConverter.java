package org.ehrbase.fhirbridge.ehr.converter.specific;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.SchwangerschaftsstatusComposition;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

public class PregnancyStatusCompositionConverter extends ObservationToCompositionConverter<SchwangerschaftsstatusComposition> {

    @Override
    public SchwangerschaftsstatusComposition convertInternal(@NonNull Observation resource) {
        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();
        composition.setSchwangerschaftsstatus(mapObservation(resource));
        return composition;
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
                    throw new ConversionException("Status code " + statusCode.getCode() + " is not supported");
            }
        } else {
            result.setStatusDefiningCode(StatusDefiningCode2.UNBEKANNT);
        }

        return result;
    }

}
