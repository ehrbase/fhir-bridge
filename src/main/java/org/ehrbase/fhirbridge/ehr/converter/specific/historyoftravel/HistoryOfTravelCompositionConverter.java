package org.ehrbase.fhirbridge.ehr.converter.specific.historyoftravel;

import org.ehrbase.fhirbridge.ehr.converter.ConversionException;
import org.ehrbase.fhirbridge.ehr.converter.generic.ObservationToCompositionConverter;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.ReisehistorieComposition;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDenAusschlussDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.AussageUeberDieFehlendeInformationDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.KategorieDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReiseAngetretenDefiningCode;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.ReisehistorieKategorieElement;
import org.ehrbase.fhirbridge.ehr.opt.reisehistoriecomposition.definition.StatusDefiningCode;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

import static org.ehrbase.fhirbridge.ehr.converter.specific.CodeSystem.SNOMED;

public class HistoryOfTravelCompositionConverter extends ObservationToCompositionConverter<ReisehistorieComposition> {

    @Override
    public ReisehistorieComposition convertInternal(@NonNull Observation resource) {
        ReisehistorieComposition composition = new ReisehistorieComposition();
        mapKategorie(composition, resource);
        setReisehistorieType(composition, resource);
        return (composition);
    }

    private void setReisehistorieType(ReisehistorieComposition composition, Observation resource) {
        String code = getSnomedCodeObservation(resource);
        if (code.equals(ReiseAngetretenDefiningCode.YES_QUALIFIER_VALUE.getCode())) {
            composition.setReisehistorie(new ReisehistorieAdminEntryConverter().convert(resource));
        } else if (code.equals(AussageUeberDenAusschlussDefiningCode.NO_QUALIFIER_VALUE.getCode())) {
            composition.setKeineReisehistorie(new KeineReisehistorieEvaluationConverter().convert(resource));
        } else if (code.equals(AussageUeberDieFehlendeInformationDefiningCode.UNKNOWN_QUALIFIER_VALUE.getCode())) {
            composition.setUnbekannteReisehistorie(new UnbekannteReisehistorieEvaluationConverter().convert(resource));
        } else {
            throw new ConversionException("Expected snomed-code for history of travel, but got '" + code + "' instead ");
        }
    }


    private String getSnomedCodeObservation(Observation fhirObservation) {
        if (fhirObservation.getValueCodeableConcept().getCoding().get(0).getSystem().equals(SNOMED.getUrl())) {
            return fhirObservation.getValueCodeableConcept().getCoding().get(0).getCode();
        } else {
            throw new ConversionException("The system is not correct. " +
                    "It should be '" + SNOMED.getUrl() + "', but it was '" + fhirObservation.getValueCodeableConcept().getCoding().get(0).getSystem() + "'.");
        }
    }

    private void mapKategorie(ReisehistorieComposition composition, Observation resource) {
        ReisehistorieKategorieElement element = new ReisehistorieKategorieElement();
        Coding coding = resource.getCategory().get(0).getCoding().get(0);
        String code = coding.getCode();
        String system = coding.getSystem();

        KategorieDefiningCode expectedKategorie = KategorieDefiningCode.SOCIAL_HISTORY;
        if (!system.equals(expectedKategorie.getTerminologyId())) {
            throw new ConversionException("Categorie can't be set. Wrong terminology! Expected " + expectedKategorie.getTerminologyId() + ". Received" + system + "' instead");
        }

        if (!code.equals(expectedKategorie.getCode())) {
            throw new ConversionException("Categorie can't be set. Wrong code! Expected " + expectedKategorie.getCode() + ". Received" + code + "' instead");
        }
        element.setValue(expectedKategorie.getValue());

        List<ReisehistorieKategorieElement> kategorieList = new ArrayList<>();
        kategorieList.add(element);
        composition.setKategorie(kategorieList);
    }
}
