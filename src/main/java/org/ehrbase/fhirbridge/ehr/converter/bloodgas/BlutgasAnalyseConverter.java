package org.ehrbase.fhirbridge.ehr.converter.bloodgas;

import com.nedap.archie.rm.generic.PartySelf;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.BefundDerBlutgasanalyseComposition;
import org.ehrbase.fhirbridge.ehr.opt.befundderblutgasanalysecomposition.definition.StatusDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.CategoryDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Language;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.SettingDefiningcode;
import org.ehrbase.fhirbridge.ehr.opt.shareddefinition.Territory;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Observation;

import java.util.Optional;

public class BlutgasAnalyseConverter {

    public BlutgasAnalyseConverter() {
    }

    private StatusDefiningcode mapStatus(Observation fhirObservation) {
        switch (fhirObservation.getStatusElement().getCode()) {
            case "registered":
                return StatusDefiningcode.REGISTRIERT;
            case "final":
                return StatusDefiningcode.FINAL;
            case "amended":
                return StatusDefiningcode.GEANDERT;
            case "preliminary":
                return StatusDefiningcode.VORLAUFIG;
            default:
                throw new IllegalStateException("Invalid Code " + fhirObservation.getStatusElement().getCode() + "" +
                        " for mapping of 'status', valid codes are: registered, final, amended and preliminary");
        }
    }

    private String mapKategorie(Observation fhirObservation) {
        Optional<String> categoryCode;
        for (CodeableConcept codingEntry : fhirObservation.getCategory()) {
            categoryCode = getObservationCategory(codingEntry);
            if (categoryCode.isPresent()) {
                return categoryCode.get();
            }
        }
        throw new IllegalArgumentException("Category code is not defined in blood gas panel, therefore the bundle is incomplete. Please add category observation category to the panel");

    }

    private Optional<String> getObservationCategory(CodeableConcept codings) {
        for (Coding categoryEntry : codings.getCoding()) {
            if (isObservationCategory(categoryEntry)) {
                return Optional.of(categoryEntry.getCode());
            }
        }
        return Optional.empty();
    }

    private boolean isObservationCategory(Coding categories) {
        return categories.getSystem().equals("http://terminology.hl7.org/CodeSystem/observation-category");
    }


    private void setMandatoryFields(BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition) {
        befundDerBlutgasanalyseComposition.setLanguage(Language.DE);
        befundDerBlutgasanalyseComposition.setLocation("test");
        befundDerBlutgasanalyseComposition.setSettingDefiningcode(SettingDefiningcode.SECONDARY_MEDICAL_CARE);
        befundDerBlutgasanalyseComposition.setTerritory(Territory.DE);
        befundDerBlutgasanalyseComposition.setCategoryDefiningcode(CategoryDefiningcode.EVENT);
        befundDerBlutgasanalyseComposition.setComposer(new PartySelf());
    }

    public BefundDerBlutgasanalyseComposition convert(BloodGasPanel bloodGasPanelBundle) {
        Observation bloodGasPanel = bloodGasPanelBundle.getBloodGasPanel();
        BefundDerBlutgasanalyseComposition befundDerBlutgasanalyseComposition = new BefundDerBlutgasanalyseComposition();
        setMandatoryFields(befundDerBlutgasanalyseComposition);

        befundDerBlutgasanalyseComposition.setStatusDefiningcode(mapStatus(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setKategorieValue(mapKategorie(bloodGasPanel));
        befundDerBlutgasanalyseComposition.setStartTimeValue(bloodGasPanel.getEffectiveDateTimeType().getValueAsCalendar().toZonedDateTime());
        befundDerBlutgasanalyseComposition.setLaborergebnis(LaborergebnisBefundConverter.map(bloodGasPanelBundle));
        return befundDerBlutgasanalyseComposition;
    }
}
