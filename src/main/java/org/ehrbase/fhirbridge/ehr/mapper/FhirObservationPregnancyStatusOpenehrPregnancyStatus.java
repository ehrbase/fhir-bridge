package org.ehrbase.fhirbridge.mapping;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.fhirbridge.fhir.Profile;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.*;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.*;
import org.ehrbase.fhirbridge.ehr.opt.schwangerschaftsstatuscomposition.definition.*;
import com.nedap.archie.rm.generic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;
import static java.util.Date.from;

/**
 * FHIR to openEHR - Pregnancy Status
 */
public class FhirObservationPregnancyStatusOpenehrPregnancyStatus {

    private static final Logger logger = LoggerFactory.getLogger(FhirObservationPregnancyStatusOpenehrPregnancyStatus.class);

    private FhirObservationPregnancyStatusOpenehrPregnancyStatus() {}

    /**
     * this maps a FHIR Observation to a SchwangerschaftsstatusComposition.
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the Composition defined by the Schwangerschaftsstatus template.
     */
    public static SchwangerschaftsstatusComposition map(Observation fhirObservation) {

        SchwangerschaftsstatusComposition composition = new SchwangerschaftsstatusComposition();

        // map start time
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        composition.setStartTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());

        // FIXME: map other context status
        // Can't map because of https://github.com/ehrbase/openEHR_SDK/issues/84

        composition.setSchwangerschaftsstatus(mapObservation(fhirObservation));


        // ======================================================================================
        // Required fields by API
        composition.setLanguage(Language.DE);
        composition.setLocation("test");
        composition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        composition.setTerritory(Territory.DE);
        composition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        composition.setStartTimeValue(OffsetDateTime.now());

        PartyIdentified composer = new PartyIdentified();
        DvIdentifier identifier = new DvIdentifier();
        identifier.setId(fhirObservation.getPerformer().get(0).getReference());
        composer.addIdentifier(identifier);
        composition.setComposer(composer);

        return composition;
    }

    /**
     * Util method used from SchwangerschaftsstatusComposition map(Observation)
     * @param fhirObservation the FHIR Observation resource received in the API.
     * @return the Observation defined in the OPT that maps to the FHIR observation
     */
    private static SchwangerschaftsstatusObservation mapObservation(Observation fhirObservation)
    {
        SchwangerschaftsstatusObservation observation = new SchwangerschaftsstatusObservation();

        // mandatory fields
        observation.setSubject(new PartySelf());
        observation.setLanguage(Language.DE);

        // map origin/time
        DateTimeType fhirEffectiveDateTime = fhirObservation.getEffectiveDateTimeType();

        observation.setTimeValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());
        observation.setOriginValue(fhirEffectiveDateTime.getValueAsCalendar().toZonedDateTime());


        // map observation status
        Coding statusCode = fhirObservation.getValueCodeableConcept().getCoding().get(0);

        // TODO: this only considers LOINC cases
        switch (statusCode.getCode())
        {
            case "LA15173-0": // pregnant
                observation.setStatusDefiningcode(StatusDefiningcode.SCHWANGER);
            break;
            case "LA26683-5": // not pregnant
                observation.setStatusDefiningcode(StatusDefiningcode.NICHT_SCHWANGER);
            break;
            case "LA4489-6": // unknown
                observation.setStatusDefiningcode(StatusDefiningcode.UNBEKANNT);
            break;
            default:
                throw new UnprocessableEntityException("Status code "+ statusCode.getCode() +" is not supported");
        }

        return observation;
    }
}