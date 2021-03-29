package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import ca.uhn.fhir.rest.server.exceptions.UnprocessableEntityException;
import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.BundeslandRegionDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KeineReisehistorieEvaluation;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.LandDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ProblemDiagnoseDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieAdminEntry;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieBestimmtesReisezielCluster;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.UnbekannteReisehistorieEvaluation;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.LOINC;
import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_CITY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_COUNTRY_OF_TRAVEL;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_OF_DEPARTURE_FROM_TRAVEL_DESTINATION;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_DATE_TRAVEL_STARTED;
import static org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel.HistoryOfTravelCode.LOINC_STATE_OF_TRAVEL;

public class HistoryOfTravelConverter extends ObservationToCompositionConverter<ReisehistorieComposition> {

    @Override
    public ReisehistorieComposition convertInternal(@NonNull Observation resource) {
        ReisehistorieComposition composition = new ReisehistorieComposition();

        String code = getSnomedCodeObservation(resource);
        // check for general travel state

        if (code.equals(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE.getCode())) {
            composition.setReisehistorie(new ReisehistorieAdminEntryConverter().convert(resource));
        } else if (code.equals(AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE.getCode())) {
            composition.setKeineReisehistorie(new KeineReisehistorieEvaluationConverter().convert(resource));
        } else if (code.equals(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE.getCode())) {
            composition.setUnbekannteReisehistorie(new UnbekannteReisehistorieEvaluationConverter().convert(resource));
        } else {
            throw new UnprocessableEntityException("Expected snomed-code for history of travel, but got '" + code + "' instead ");
        }
        return (composition);
    }

    private void checkForSnomedSystem(String systemCode) {
        if (!SNOMED.getUrl().equals(systemCode)) {
            throw new UnprocessableEntityException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + systemCode + "'.");
        }
    }

    private String getSnomedCodeObservation(Observation fhirObservation) {
        Coding code = fhirObservation.getValueCodeableConcept().getCoding().get(0);
        checkForSnomedSystem(code.getSystem());
        return code.getCode();
    }

}
