package org.ehrbase.fhirbridge.ehr.converter.specific.pregnancystatus;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToObservationConverter;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.SchwangerschaftsstatusObservation;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.StatusDefiningCode2;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;

public class SchwangerschaftsstatusObservationConverter extends ObservationToObservationConverter<SchwangerschaftsstatusObservation> {
    @Override
    protected SchwangerschaftsstatusObservation convertInternal(Observation resource) {
        SchwangerschaftsstatusObservation result = new SchwangerschaftsstatusObservation();
        // map result status
        if (!resource.getValueCodeableConcept().isEmpty() && !resource.getValueCodeableConcept().getCoding().isEmpty()) {

            Coding statusCode = resource.getValueCodeableConcept().getCoding().get(0);

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
